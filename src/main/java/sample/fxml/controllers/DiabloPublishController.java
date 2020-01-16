package sample.fxml.controllers;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import sample.Controller;
import sample.ITab;
import sample.entitys.PublishLogEntity;
import sample.file.FileOperator;
import sample.fxml.componet.AlertBox;
import sample.fxml.componet.fxml.FileSelector;
import sample.fxml.componet.fxml.InputComponent;
import sample.fxml.componet.fxml.MaskPanel;
import sample.fxml.renders.PublishLogRender;
import sample.interfaces.AutowireInterface;
import sample.mapper.DiabloPublishMapper;
import sample.utils.Empty;
import sample.utils.StringUtils;
import sample.utils.TimeUtils;
import sample.utils.Utils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/10/22 10:57
 */
public class DiabloPublishController implements ITab, AutowireInterface {

    private static final Logger logger = LoggerFactory.getLogger(DiabloPublishController.class);

    @FXML
    public FileSelector projectFileSelector;
    public FileSelector serverFolderSelector;
    public InputComponent versionNameInput;
    public Button publishBtn;
    public ListView<PublishLogEntity> publishHistoryList;
    public FileSelector svnFileSelector;
    public MaskPanel maskPanel;
    public Button logBtn;
    public Button kfPublishBtn;
    public FileSelector projectFolderSelector;
    private boolean inited;
    //    @Autowired
    //    TableMangerService tableMangerService;
    @Autowired
    DiabloPublishMapper diabloPublishMapper;

    public static DiabloPublishController THIS;


    @Override
    public void onSelect() {
        if (!inited) {

            inited = true;
            publishHistoryList.setCellFactory(param -> new PublishLogRender());
            THIS = this;
            updateList();

        }
    }

    private void updateList() {
        PublishLogEntity[] publishLogs = diabloPublishMapper.getPublishLogs();
        publishHistoryList.getItems().setAll(publishLogs);
    }

    @Override
    public void onAppClose() {

    }


    public void onPublishBtnClick(MouseEvent mouseEvent) {
        try {
            publish(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void publish(boolean withKf) throws Exception {
        String projectFileSelectorPath = projectFileSelector.getPath();
        if (StringUtils.isEmpty(projectFileSelectorPath)) {
            AlertBox.showAlert("请选择需要更新的项目文件");
            return;
        }
        File projectFile = new File(projectFileSelectorPath);
        if (!projectFile.exists() || projectFile.isDirectory()) {
            AlertBox.showAlert("请选择正确的项目文件");
            return;
        }

        String path = serverFolderSelector.getPath();
        if (StringUtils.isEmpty(path)) {
            AlertBox.showAlert("发布服务器目录不能空");
            return;
        }

        File serverFolder = new File(path);
        if (!serverFolder.exists() || !serverFolder.isDirectory()) {
            AlertBox.showAlert("请选择正确的服务器目录");
            return;
        }
        ArrayList<File> addConfigFiles = FileCleanController.srcToTargetIfNotExistsFile(projectFolderSelector, serverFolderSelector, "config");
        if (addConfigFiles == null) {
            return;
        }
        maskPanel.clear();
        if (addConfigFiles.isEmpty()) {

            maskPanel.append("未发现新增配置文件");
        } else {
            maskPanel.append("新增配置文件[");
            for (File addConfigFile : addConfigFiles) {
                maskPanel.append(addConfigFile.getAbsolutePath());
            }
            maskPanel.append("]");
        }
        updateServerFolder(serverFolder);
        String versionName = versionNameInput.getInputText();

        int systemSecond = TimeUtils.getSystemSecond();

        String projectFileName = projectFile.getName();
        String newFileName = getVersionProjectName(versionName, projectFileName);

        File newProjectFile = new File(serverFolder.getAbsolutePath() + "/" + newFileName);

        FileUtils.copyFile(projectFile, newProjectFile);

        String batFileContent = getBatFileContent(newFileName, "");

        File batFile = getBatFile(serverFolder, versionName);
        FileOperator.writeFile(batFile, batFileContent);
        ArrayList<File> files = new ArrayList<>(addConfigFiles);
        files.add(newProjectFile);
        files.add(batFile);

        if (withKf) {
            String masterBatFileContent = getBatFileContent(newFileName, "master 20000");

            File masterBatFile = getMasterBatFile(serverFolder, versionName);
            FileOperator.writeFile(masterBatFile, masterBatFileContent);
            String combatBatFileContent = getBatFileContent(newFileName, "combat 20001");
            File combatBatFile = getCombatBatFile(serverFolder, versionName);
            FileOperator.writeFile(combatBatFile, combatBatFileContent);
            files.add(masterBatFile);
            files.add(combatBatFile);
        }

        diabloPublishMapper.updatePublishLog(versionName, serverFolder.getPath(), systemSecond);

        updateList();


        addFileAndCommit(serverFolder, versionName, files.toArray(Empty.FILES));
        Controller.log("发布成功" + versionName + " " + TimeUtils.printTime2(System.currentTimeMillis()));
    }

    private String getBatFileContent(String newFileName, String suffix) {
        return "@echo start" + FileOperator.NEX_LINE + "title=" + newFileName + " " + suffix + FileOperator.NEX_LINE +
                "jdk1.8.0_171\\bin\\java.exe -jar " + newFileName + " " + suffix + FileOperator.NEX_LINE + "pause";
    }

    public void onKfPublishBtnClick(MouseEvent mouseEvent) {
        try {
            publish(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void updateServerFolder(File serverFolder) throws Exception {
        String path = getSvnExePath();

        String commit = getSvnCmd(path, "update", serverFolder.getPath());
        String s = FileOperator.runBat(commit);
        maskPanel.append(s);
    }

    private void addFileAndCommit(File serverFolder, String comment, File... files) throws Exception {
        String path = getSvnExePath();
        StringBuilder paths = new StringBuilder();
        for (File f : files) {
            paths.append(f.getPath());
            paths.append(" ");
        }
        String add = getSvnCmd(path, "add", paths.toString());
        String s = FileOperator.runBat(add);
        maskPanel.append(s);
        commitServer(serverFolder, comment);

    }

    public void deleteFileAndCommit(File serverFolder, String comment, File... files) throws Exception {
        maskPanel.clear();
        String path = getSvnExePath();
        StringBuilder paths = new StringBuilder();
        for (File f : files) {
            paths.append(f.getPath());
            paths.append(" ");
        }
        String add = getSvnCmd(path, "delete", paths.toString());
        String s = FileOperator.runBat(add);
        maskPanel.append(s);
        commitServer(serverFolder, comment);
        Controller.log(comment + " " + TimeUtils.printTime2(System.currentTimeMillis()));
    }

    private void commitServer(File serverFolder, String comment) throws Exception {
        String path = getSvnExePath();

        String commit = getSvnCmd(path, "commit", "-m " + comment + " " + serverFolder.getPath());
        String s = FileOperator.runBat(commit);
        maskPanel.append(s);

    }

    private String getSvnExePath() {
        String path = svnFileSelector.getPath();
        if (StringUtils.isEmpty(path)) {
            throw new RuntimeException("未设置svn目录，不更新服务器目录 path:" + path);
        }
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            throw new RuntimeException("设置的是非svn执行文件 path:" + path);
        }
        return path;
    }

    private String getSvnCmd(String svnExePath, String command, String param) {
        return svnExePath + " " + command + " " + param;//+ " " + svnPath
    }

    public File getBatFile(File serverFolder, String versionName) {
        return getBatFile(serverFolder, versionName, "");
    }

    public File getMasterBatFile(File serverFolder, String versionName) {
        return getBatFile(serverFolder, versionName, "-master");
    }

    public File getCombatBatFile(File serverFolder, String versionName) {
        return getBatFile(serverFolder, versionName, "-combat");
    }

    public File getBatFile(File serverFolder, String versionName, String suffix) {

        String newBatFileName = versionName + "-start" + suffix + ".bat";
        return new File(serverFolder.getAbsolutePath() + "/" + newBatFileName);
    }

    public String getVersionProjectName(String versionName, String projectFileName) throws BadHanyuPinyinOutputFormatCombination {
        int lastIndexOf = projectFileName.lastIndexOf(".");
        String versionPinYin = PinyinHelper.toHanYuPinyinString(versionName, Utils.outputFormat, "", true).toLowerCase();
        return projectFileName.substring(0, lastIndexOf) + "-" + versionPinYin + projectFileName.substring(lastIndexOf);
    }

    public void onLogBtnClick(MouseEvent mouseEvent) {
        maskPanel.setVisible(!maskPanel.isVisible());
    }


    public void resolve(PublishLogEntity item) {
        serverFolderSelector.setPath(item.getServerFolder());
        versionNameInput.setInputText(item.getVersionName());
    }
}
