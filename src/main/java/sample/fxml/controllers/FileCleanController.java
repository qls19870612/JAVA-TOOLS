package sample.fxml.controllers;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import sample.Controller;
import sample.ITab;
import sample.file.FileOperator;
import sample.fxml.componet.AlertBox;
import sample.fxml.componet.fxml.FileSelector;
import sample.fxml.componet.fxml.InputComponent;
import sample.utils.FileRenameUtils;
import sample.utils.StringUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/01/08 20:54
 */
public class FileCleanController implements ITab {
    private static final Logger logger = LoggerFactory.getLogger(FileCleanController.class);
    public FileSelector srcFolderSelector;
    public FileSelector targetFolderSelector;
    public TextArea infoTA;
    public InputComponent srcMatchRegTI;
    public InputComponent replaceTI;

    public void onCleanBtnClick(MouseEvent mouseEvent) {
        if (!srcFolderSelector.isExistsDirectory()) {
            AlertBox.showAlert("源文件夹不存在");
            return;
        }
        if (!targetFolderSelector.isExistsDirectory()) {
            AlertBox.showAlert("目标文件夹不存在");
            return;
        }
        String srcDirPath = srcFolderSelector.getDirectoryAbsolutePath();
        String targetDirPath = targetFolderSelector.getDirectoryAbsolutePath();
        ArrayList<File> srcAllFiles = FileOperator.getAllFiles(new File(srcDirPath), entry -> !entry.isDirectory());
        ArrayList<File> targetAllFiles = FileOperator.getAllFiles(new File(targetDirPath), entry -> !entry.isDirectory());
        //key 文件名字 value:文件短路径(不包括文件名)
        HashMap<String, String> srcFileFolderMap = getFileShortPathMap(srcDirPath, srcAllFiles);
        StringBuilder srcNoFile = null;
        for (File file : targetAllFiles) {
            String targetFileName = file.getName();
            if (srcFileFolderMap.containsKey(targetFileName)) {
                String s = srcFileFolderMap.get(file.getName());
                if (s.contains(",")) {
                    //包含多个文件，不移动，不知道该移动 到哪里
                    continue;
                }
                String shortPath = getShortPath(targetDirPath, file);
                if (shortPath.equals(s)) {
                    srcFileFolderMap.remove(file.getName());
                    continue;//短路径一样，不移动文件
                }
                File newPath = new File(targetDirPath + s);
                if (!newPath.exists()) {
                    newPath.mkdirs();

                }
                File dest = new File(newPath + "\\" + file.getName());
                file.renameTo(dest);
                srcFileFolderMap.remove(file.getName());
            } else {
                if (srcNoFile == null) {
                    srcNoFile = new StringBuilder("源目录不存在文件不移动:\n");
                }
                srcNoFile.append(file.getAbsolutePath() + "\n");
            }
        }
        StringBuilder builder = new StringBuilder("重复文件未移动:\n");
        StringBuilder targetNoFile = null;
        for (Entry<String, String> entry : srcFileFolderMap.entrySet()) {
            if (entry.getValue().contains(",")) {
                builder.append(entry.getKey());
                builder.append("[");
                builder.append(entry.getValue());
                builder.append("]\n");
            } else {
                if (targetNoFile == null) {
                    targetNoFile = new StringBuilder("目标目录不存在文件未移动:\n");
                }
                targetNoFile.append(entry.getKey());
                targetNoFile.append("[");
                targetNoFile.append(entry.getValue());
                targetNoFile.append("]\n");

            }
        }
        if (srcNoFile != null) {
            builder.append("\n");
            builder.append(srcNoFile);
        }
        if (targetNoFile != null) {
            builder.append("\n");
            builder.append(targetNoFile);
        }
        infoTA.setText(builder.toString());


    }

    private HashMap<String, String> getFileShortPathMap(String srcDirPath, ArrayList<File> srcAllFiles) {
        HashMap<String, String> fileFolderMap = new HashMap<>();
        for (File srcAllFile : srcAllFiles) {
            String name = srcAllFile.getName();
            String shortPath = getShortPath(srcDirPath, srcAllFile);
            if (fileFolderMap.containsKey(name)) {
                String s = fileFolderMap.get(name);
                s = s + "," + shortPath;
                fileFolderMap.put(name, s);
            } else {
                fileFolderMap.put(name, shortPath);
            }
        }
        return fileFolderMap;
    }

    private static HashMap<String, String> getFileShortPathWithNameMap(String basePath, ArrayList<File> srcAllFiles) {
        HashMap<String, String> fileFolderMap = new HashMap<>();
        for (File srcAllFile : srcAllFiles) {
            String name = srcAllFile.getName();
            String shortPath = srcAllFile.getAbsolutePath().replace(basePath, "");
            fileFolderMap.put(shortPath, name);
        }
        return fileFolderMap;
    }

    private String getShortPath(String baseDirPath, File file) {

        return file.getParent().replace(baseDirPath, "");
    }

    @Override
    public void onSelect() {

    }

    @Override
    public void onAppClose() {

    }

    public void onMoveBtnClick(MouseEvent mouseEvent) {
        infoTA.clear();
        ArrayList<File> addFiles = srcToTargetIfNotExistsFile(srcFolderSelector, targetFolderSelector, "");
        if (addFiles != null) {
            if (addFiles.isEmpty()) {

                infoTA.setText("未发现新增文件");
            } else {
                infoTA.appendText("目标目录新增文件");
                for (File addFile : addFiles) {
                    infoTA.appendText(addFile.getAbsolutePath());
                }
            }
        }

    }

    public static ArrayList<File> srcToTargetIfNotExistsFile(FileSelector srcFolderSelector, FileSelector targetFolderSelector, String subFolder) {
        if (!srcFolderSelector.isExistsDirectory()) {
            AlertBox.showAlert("源文件夹不存在:" + srcFolderSelector.getDirectoryAbsolutePath());
            return null;
        }
        if (!targetFolderSelector.isExistsDirectory()) {
            AlertBox.showAlert("目标文件夹不存在:" + targetFolderSelector.getDirectoryAbsolutePath());
            return null;
        }
        String srcDirPath = srcFolderSelector.getDirectoryAbsolutePath();
        String targetDirPath = targetFolderSelector.getDirectoryAbsolutePath();

        File srcFolder;
        File targetFolder;

        if (StringUtils.isNotEmpty(subFolder)) {
            srcDirPath = srcDirPath + File.separator + subFolder;
            targetDirPath = targetDirPath + File.separator + subFolder;
            srcFolder = new File(srcDirPath);
            targetFolder = new File(targetDirPath);
            if (!srcFolder.exists()) {
                AlertBox.showAlert("源文件夹不存在:" + srcDirPath);
                return null;
            }

            if (!targetFolder.exists()) {
                AlertBox.showAlert("源文件夹不存在:" + targetDirPath);
                return null;
            }
        } else {
            srcFolder = new File(srcDirPath);
            targetFolder = new File(targetDirPath);
        }


        ArrayList<File> srcAllFiles = FileOperator.getAllFiles(srcFolder, entry -> !entry.isDirectory() && !entry.getAbsolutePath().contains("限时活动"));
        ArrayList<File> targetAllFiles =
                FileOperator.getAllFiles(targetFolder, entry -> !entry.isDirectory() && !entry.getAbsolutePath().contains("限时活动"));
        //key 文件名字 value:文件短路径(不包括文件名)
        HashMap<String, String> targetFileFolderMap = getFileShortPathWithNameMap(targetDirPath, targetAllFiles);
        ArrayList<File> addFiles = new ArrayList<>();
        for (File file : srcAllFiles) {
            String srcShortPath = file.getAbsolutePath().replace(srcDirPath, "");
            if (!targetFileFolderMap.containsKey(srcShortPath)) {


                String toPathFile = targetDirPath + srcShortPath;
                File dest = new File(toPathFile);
                addFiles.add(dest);

                try {
                    FileUtils.copyFile(file, dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return addFiles;
        //        if (srcNoFile != null) {
        //            return srcNoFile.toString();
        //
        //        }
        //        return "未发现新增文件";
    }

    public void onStartRename(MouseEvent mouseEvent) {
        if (!srcFolderSelector.isExistsDirectory()) {
            AlertBox.showAlert("源文件夹不存在");
            return;
        }
        if (!targetFolderSelector.isExistsDirectory()) {
            AlertBox.showAlert("目标文件夹不存在");
            return;
        }
        if (StringUtils.isEmpty(srcMatchRegTI.getInputText())) {
            AlertBox.showAlert("没有设置源文件匹配正则");
            return;
        }
        if (StringUtils.isEmpty(replaceTI.getInputText())) {
            AlertBox.showAlert("没有目标文件匹配规则");
            return;
        }
        if (!replaceTI.getInputText().contains("$")) {
            AlertBox.showAlert("目标文件匹配规则必需有'$'符号，否则配置成一样的啦!");
            return;
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    FileRenameUtils.rename(srcFolderSelector.getPath(), targetFolderSelector.getPath(), srcMatchRegTI.getInputText(),
                            replaceTI.getInputText());
                } catch (Exception e) {
                    logger.error("onStartRename getMessage:{}", e.getMessage());
                    AlertBox.showAlert("执行出错:" + e.getMessage());
                }
                Controller.log("全部重命名或复制完成");

            }
        });
        thread.start();


    }
}
