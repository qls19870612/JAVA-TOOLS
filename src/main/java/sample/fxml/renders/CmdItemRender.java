package sample.fxml.renders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sample.fxml.componet.AlertBox;
import sample.fxml.controllers.GMProxyController;
import sample.fxml.controllers.client.handlers.gm.CmdParam;
import sample.fxml.controllers.client.handlers.gm.GmCmd;
import sample.fxml.memu.GmCmdMenu;
import sample.utils.Utils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/27 14:40
 */
public class CmdItemRender extends ListCell<GmCmd> {
    private static final Logger logger = LoggerFactory.getLogger(CmdItemRender.class);

    public GmCmd getGmCmd() {
        return item;
    }

    private GmCmd item;

    ArrayList<CmdParamsRender> getRenders() {
        return renders;
    }

    private ArrayList<CmdParamsRender> renders = new ArrayList<>();
    private int count = 1;
    private VBox vBox;
    private Label label;
    public static final int ITEM_HEIGHT = 24;
    private Button button;
    private Group group;

    public CmdItemRender() {
        super();

    }

    @Override
    protected void updateItem(GmCmd item, boolean empty) {
        this.item = item;
        super.updateItem(item, empty);

        for (CmdParamsRender render : renders) {
            vBox.getChildren().remove(render);
            CmdParamsRender.recycle(render);
        }
        renders.clear();
        if (item != null) {
            if (vBox == null) {
                label = new Label();
                Tooltip tooltip = new Tooltip();
                Utils.hackTooltipStartTiming(tooltip);
                label.setTooltip(tooltip);


                label.setPrefSize(200, ITEM_HEIGHT);
                label.setTextFill(Color.DARKGREEN);
                label.setMinHeight(ITEM_HEIGHT);
                label.setMinWidth(100);
                button = new Button("发送");
                button.setLayoutX(210);
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    onSendBtnClick();
                });
                group = new Group();
                group.getChildren().addAll(label, button);
                vBox = new VBox();
                vBox.setLayoutX(10);
                vBox.getChildren().add(group);

            }

            if (vBox.getParent() == null) {
                this.getChildren().add(vBox);
            }
            label.setText(item.comment + " [" + item.cmdName + "] ");
            label.getTooltip().setText(label.getText());


            ArrayList<CmdParam> params = item.params;
            count = 1;
            for (CmdParam param : params) {
                CmdParamsRender render = CmdParamsRender.create();
                renders.add(render);
                render.updateData(param);
                count++;
                vBox.getChildren().add(render);
            }
            setGraphic(vBox);
            Node render = this;
            this.setOnMouseClicked(event -> {

                if (event.getButton() == MouseButton.SECONDARY) {

                    GmCmdMenu.getInstance().show(render, event.getScreenX(), event.getScreenY());
                }
            });

        } else {
            count = 1;
            if (vBox != null && vBox.getParent() != null) {
                this.getChildren().remove(vBox);
            }
        }
        if (getIndex() % 2 == 1) {
            setStyle("-fx-background-color:ALICEBLUE");
        } else {
            setStyle("-fx-background-color:IVORY");
        }

    }

    void onSendBtnClick() {


        StringBuilder cmd = new StringBuilder(item.cmdName);
        for (CmdParamsRender render : renders) {
            if (!render.isValid()) {
                AlertBox.showAlert(render.getFiled() + " 字段必需填写值");
                return;
            }
            cmd.append(" ");
            cmd.append(render.getValue());
        }

        GMProxyController.THIS.sendGm(cmd.toString());
    }

    @Override
    protected double computePrefHeight(double width) {
        return count * ITEM_HEIGHT + 10;

    }

    @Override
    protected double computeMinHeight(double width) {
        return count * ITEM_HEIGHT + 10;
    }
}
