package sample.fxml.controllers;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.HeapChannelBufferFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.InetSocketAddress;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.concurrent.Executors;

import game.collection.IntHashMap;
import game.collection.IntHashMap.Entry;
import game.sink.server.CheckSumStream;
import io.ytcode.reflect.clazz.Classes;
import io.ytcode.reflect.resource.Resources;
import io.ytcode.reflect.resource.Scanner;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.file.FileOperator;
import sample.fxml.controllers.gm.Client;
import sample.fxml.controllers.gm.Modules;
import sample.fxml.controllers.gm.SingleDecoderHandler;
import sample.fxml.controllers.gm.TimeService;
import sample.fxml.controllers.gm.handlers.GmHandler.GmCmd;
import sample.fxml.controllers.gm.handlers.GmHandler.GmModule;
import sample.fxml.controllers.gm.handlers.base.Handler;
import sample.fxml.controllers.gm.handlers.base.HandlerBase;
import sample.fxml.renders.CmdItemRender;
import sample.fxml.renders.GmProxyItemRender;
import sample.fxml.renders.ModuleItemRender;
import sample.utils.StringUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/25 15:03
 */
public class GMProxyController {
    private static final Logger logger = LoggerFactory.getLogger(GMProxyController.class);
    public TextField userIdTF;
    public Button loginBtn;


    public ListView<String> proxyListView;
    public ListView<GmModule> moduleListView;
    public ListView<GmCmd> cmdListView;
    private ClientBootstrap socket;
    private int msgCount = -1;
    private CheckSumStream stream;
    private Client role;
    private boolean inited;
    private boolean isLogined;
    private Client client;
    private HandlerBase[] handlers;

    public TimeService getTimeService() {
        return timeService;
    }

    private TimeService timeService;

    public static GMProxyController THIS;

    public void loginBtnClick(MouseEvent mouseEvent) {
        if (StringUtils.isEmpty(userIdTF.getText())) {
            userIdTF.setText(userIdTF.getPromptText());
        }
        socket = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        socket.setOption("bufferFactory", HeapChannelBufferFactory.getInstance(ByteOrder.LITTLE_ENDIAN));
        socket.setOption("tcpNoDelay", true);
        socket.setOption("keepAlive", false);
        socket.setOption("sendBufferSize", 8192 * 4);
        socket.setOption("receiveBufferSize", 8192 * 4);

        socket.setPipelineFactory(() -> {
            ChannelPipeline pipeline = Channels.pipeline();
            pipeline.addLast("dsf", new SingleDecoderHandler(GMProxyController.this));
            return pipeline;
        });
        socket.connect(new InetSocketAddress(9999));


    }

    public void onSocketClose() {
        setIsLogined(false);
    }


    public void onEnterScene() {
        setIsLogined(true);
    }

    public void refreshProxy() {
        if (!isLogined) {
            return;
        }
        if (proxyListView.getItems().size() <= 0) {

            return;

        }
        String selectedItem = proxyListView.getSelectionModel().getSelectedItem();
        if (StringUtils.isNotEmpty(selectedItem)) {

            client.sendGmMsg("proxy " + selectedItem);
        } else {
            client.sendGmMsg("proxy " + proxyListView.getItems().get(0));
        }
    }

    private void setIsLogined(boolean b) {
        isLogined = b;

        Platform.runLater(() -> {
            if (b) {
                loginBtn.setText("已登录");
                loginBtn.setDisable(true);
            } else {
                loginBtn.setText("登录");
                loginBtn.setDisable(false);
            }
        });
    }


    public void onSelect() {
        if (inited) {
            return;
        }
        THIS = this;
        timeService = new TimeService();
        initGMHandlers();
        inited = true;
        moduleListView.setCellFactory(param -> new ModuleItemRender());

        cmdListView.setCellFactory(param -> new CmdItemRender());

        proxyListView.setCellFactory(param -> new GmProxyItemRender());
        proxyListView.setEditable(true);

        proxyListView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            if (event.getClickCount() == 2) {

                if (event.getTarget() instanceof ListView) {

                    proxyListView.getItems().add(String.valueOf(proxyListView.getItems().size()));
                    proxyListView.edit(proxyListView.getItems().size() - 1);
                } else if (event.getTarget() instanceof GmProxyItemRender) {
                    GmProxyItemRender target = (GmProxyItemRender) event.getTarget();

                    if (target.getIndex() >= proxyListView.getItems().size()) {

                        proxyListView.getItems().add(String.valueOf(proxyListView.getItems().size()));
                        proxyListView.edit(proxyListView.getItems().size() - 1);
                    }
                }
            }


        });
        proxyListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> refreshProxy());
        proxyListView.addEventHandler(ListView.editCommitEvent(), event -> Platform.runLater(this::saveProxyList));

        proxyListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        readProxyList();
        moduleListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        moduleListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cmdListView.getItems().clear();
            ObservableList<GmModule> selectedItems = moduleListView.getSelectionModel().getSelectedItems();
            for (GmModule selectedItem : selectedItems) {

                cmdListView.getItems().addAll(selectedItem.cmds);
            }

        });
    }

    private void initGMHandlers() {
        Resources rs = Scanner.pkgs(Modules.class.getPackage().getName()).scan();
        Classes clss = rs.classes().subTypeOf(HandlerBase.class).filter(input -> !input.isInterface() && !Modifier.isAbstract(input.getModifiers()));
        IntHashMap<HandlerBase> handlerBases = new IntHashMap<>();
        int maxModuleId = 0;
        for (Class<?> cls : clss) {
            Handler annotation = cls.getAnnotation(Handler.class);
            if (annotation != null) {
                Class<HandlerBase> handlerBaseClass = (Class<HandlerBase>) cls;
                HandlerBase handlerBase = null;
                try {
                    handlerBase = handlerBaseClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (handlerBase == null) {
                    continue;
                }
                maxModuleId = Math.max(maxModuleId, annotation.moduleId());
                handlerBases.put(annotation.moduleId(), handlerBase);
            }
        }
        handlers = new HandlerBase[maxModuleId + 1];
        for (Entry<HandlerBase> handlerBaseEntry : handlerBases.entrySet()) {
            handlers[handlerBaseEntry.getKey()] = handlerBaseEntry.getValue();
        }


    }

    public HandlerBase getHandler(int moduleId) {
        if (moduleId < 0 || moduleId >= handlers.length) {
            return null;
        }
        return handlers[moduleId];
    }

    private void readProxyList() {
        String s = FileOperator.readFiles(new File("proxyClientList.txt"));
        if (StringUtils.isEmpty(s)) {
            return;
        }
        String[] split = s.split("\r\n");
        proxyListView.getItems().addAll(split);

    }

    private void saveProxyList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : proxyListView.getItems()) {
            stringBuilder.append(s);
            stringBuilder.append("\r\n");
        }
        FileOperator.writeFile(new File("proxyClientList.txt"), stringBuilder.toString());
    }

    public void onCloseApp() {
        saveProxyList();
    }

    public void setClient(Client client) {

        this.client = client;
    }

    public void updateModules(ArrayList<GmModule> modules) {
        moduleListView.getItems().addAll(modules);
        refreshProxy();
    }

    public void sendGm(String s) {
        this.client.sendGmMsg(s);
    }
}
