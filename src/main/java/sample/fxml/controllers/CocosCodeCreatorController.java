package sample.fxml.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.org.apache.bcel.internal.generic.NEW;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.collection.IntHashMap;
import game.collection.IntHashMap.Entry;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import sample.ITab;
import sample.file.FileOperator;
import sample.fxml.componet.AlertBox;
import sample.fxml.componet.fxml.FileSelector;
import sample.fxml.controllers.cocos.CocosNode;
import sample.utils.PrefabToViewTsUtils;
import sample.utils.StringUtils;

import static sample.fxml.controllers.cocos.CocosNode.getNodeId;
import static sample.fxml.controllers.cocos.CocosNode.getNodeName;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/06/20 18:02
 */
public class CocosCodeCreatorController implements ITab {
    private static final Logger logger = LoggerFactory.getLogger(CocosCodeCreatorController.class);
    public FileSelector prefabFolder;
    public FileSelector tsFolder;
    private List<File> _lastFiles;

    @Override
    public void onSelect() {

    }

    @Override
    public void onAppClose() {

    }

    private boolean hasFile(List<File> files) {
        for (File file : files) {
            if (file.isDirectory()) {
                File[] a = file.listFiles();
                if (a != null) {
                    boolean b = hasFile(Arrays.asList(a));
                    if (b) {
                        return true;
                    }
                }
            } else if (file.getName().endsWith(".prefab")) {
                return true;
            }
        }
        return false;

    }

    public void onDragOver(DragEvent dragEvent) {
        if (!hasFile(dragEvent.getDragboard().getFiles())) {
            return;
        }
        dragEvent.acceptTransferModes(TransferMode.MOVE);

        dragEvent.consume();
    }

    public void onDragDropped(DragEvent dragEvent) {
        if (!prefabFolder.isExistsDirectory()||!tsFolder.isExistsDirectory()) {
            AlertBox.showAlert("请先设置一个已经存在的路径");
            return;
        }
        List<File> files = dragEvent.getDragboard().getFiles();
        createFiles(files);

    }

    private void createFiles(List<File> files) {
        try {
            scanDir(files);
        }
        catch (Exception e)
        {
            logger.error("onDragDropped getMessage:{}", e.getMessage());
        }
    }

    private void scanDir(List<File> files) {
        _lastFiles = files;
        for (File file : files) {
            if (file.isDirectory()) {
                File[] a = file.listFiles();
                if (a != null) {
                    scanDir(Arrays.asList(a));
                }
            } else if (file.getName().endsWith(".prefab")) {
                String prefabAbsolutePath = prefabFolder.getDirectoryAbsolutePath();
                if (!file.getAbsolutePath().startsWith(prefabAbsolutePath)) {
                    AlertBox.showAlert("有个prefab文件不在设置的目录下面");
                    throw new RuntimeException("有个prefab文件不在设置的目录下面 file:" + file.getAbsolutePath() + " prefabAbsolutePath:" + prefabAbsolutePath);
                }
                format(prefabAbsolutePath,tsFolder.getDirectoryAbsolutePath(), file);
            }
        }
    }

    public static void main(String[] args) {
        File file= new File("D:\\SVN\\client\\client\\SummonWorld\\assets\\resources\\prefab\\modules\\vip");
        ArrayList<File> allFiles = FileOperator.getAllFiles(file, ".prefab");
        for (File subFile : allFiles) {
            replaceCocosFieldName(subFile,"_","\\$");
        }
    }
    public static void replaceCocosFieldName(File file,String oldPrefix,String newPrefix ){
        String s = FileOperator.readFiles(file);
        Object parse;
        try {
            parse = JSONObject.parse(s);
        } catch (Exception e) {
            logger.error("createTs 不是json格式 getMessage:{},getAbsolutePath:{},s:{}", e.getMessage(), file.getAbsolutePath(), s);
            return;
        }

        if (parse instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) parse;

            ArrayList<CocosNode> list = new ArrayList<>();

            for (Object o : jsonArray) {
                if (!(o instanceof JSONObject)) {
                    continue;
                }
                JSONObject object = (JSONObject) o;

                CocosNode cocosNode = new CocosNode(object);


                if (cocosNode.name.startsWith(oldPrefix)) {
                    list.add(cocosNode);
                }
            }
            list.sort((o1, o2) -> o2.name.length()-o1.name.length());
            for (CocosNode cocosNode : list) {
              s=s.replaceAll(cocosNode.name,cocosNode.name.replace(oldPrefix,newPrefix));
            }
            JSONArray objects = JSONObject.parseArray(s);
            String pretty = JSON.toJSONString(objects, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat);
            logger.debug("format parse:{}", pretty);
            FileOperator.writeFile(file,pretty);
        } else {
            if (parse != null) {

                logger.error("createTs getName:{}", parse.getClass().getName());
            } else {
                logger.error("createTs 文件居然不是jsonArray格式 getAbsolutePath:{},s:{}", file.getAbsolutePath(), s);
            }
        }

    }
    private void format(String prefabAbsolutePath, String tsAbsolutePath, File file) {

        String s = FileOperator.readFiles(file);
        Object parse;
        try {
            parse = JSONObject.parse(s);
        } catch (Exception e) {
            logger.error("createTs 不是json格式 getMessage:{},getAbsolutePath:{},s:{}", e.getMessage(), file.getAbsolutePath(), s);
            return;
        }
        String subPath = file.getAbsolutePath().replace(prefabAbsolutePath,"");
        File newDir = new File(tsAbsolutePath + subPath.replace(file.getName(),"/base"));

        if (!newDir.exists()) {
            newDir.mkdirs();
        }
        if (parse instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) parse;
            HashMap<String, CocosNode> nameMap = new HashMap<>();
            IntHashMap<CocosNode> idMap = new IntHashMap<>();
            ArrayList<CocosNode> list = new ArrayList<>();

            for (Object o : jsonArray) {
                if (!(o instanceof JSONObject)) {
                    continue;
                }
                JSONObject object = (JSONObject) o;

                CocosNode cocosNode = new CocosNode(object);
                list.add(cocosNode);
                if (cocosNode.id > 0) {
                    idMap.put(cocosNode.id, cocosNode);
                }
                if (cocosNode.isBind()) {
                    nameMap.put(cocosNode.name, cocosNode);
                    object.put("_name",cocosNode.name.replace("_","$"));
                }
            }

            for (Map.Entry<String, CocosNode> entry : nameMap.entrySet()) {
                entry.getValue().initChildren(idMap,list);
            }
            Collection<CocosNode> values = nameMap.values();
            ArrayList<CocosNode> filedNameArr = new ArrayList<>(values);
            filedNameArr.sort(Comparator.comparing(o -> o.name));
            PrefabToViewTsUtils.createTsByTemplate(newDir, file, filedNameArr);
            logger.debug("format parse:{}", ((JSONArray) parse).toJSONString());
        } else {
            if (parse != null) {

                logger.error("createTs getName:{}", parse.getClass().getName());
            } else {
                logger.error("createTs 文件居然不是jsonArray格式 getAbsolutePath:{},s:{}", file.getAbsolutePath(), s);
            }
        }

    }


    public void onClick(MouseEvent mouseEvent) {
        File file = new File("D:\\SVN\\client\\client\\SummonWorld\\assets\\resources\\prefab\\modules\\vip\\");
        scanDir(Arrays.asList(file.listFiles()));
    }

    public void reCreateLastFiles(MouseEvent mouseEvent) {
        if (_lastFiles != null) {
            createFiles(_lastFiles);
        }
        else {
            AlertBox.showAlert("没有进行上次生成操作!");
        }
    }
}
