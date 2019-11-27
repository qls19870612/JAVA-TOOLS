package sample.fxml.componet.fxml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import sample.enums.ConfigType;
import sample.file.FileOperator;
import sample.interfaces.AutowireInterface;
import sample.mapper.ConfigMapper;
import sample.utils.StringUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/10/24 14:13
 */
public class FileSelector extends AbstractInputComponent implements AutowireInterface {
    private static final Logger logger = LoggerFactory.getLogger(FileSelector.class);
    private final Button openFolderBtn;
    private ConfigType configType;
    @Autowired
    private ConfigMapper configMapper;


    private boolean isFolder = false;

    public FileSelector() {
        super();
        openFolderBtn = new Button("打开");
        openFolderBtn.setMinWidth(70);
        this.getChildren().add(2, openFolderBtn);
        openFolderBtn.setOnMouseClicked(this);
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    @FXML
    public void setConfigType(ConfigType configType) {
        this.configType = configType;
        String config = configMapper.getConfig(configType);
        if (StringUtils.isNotEmpty(config)) {
            textField.setText(config);
        }
    }

    @FXML
    public ConfigType getConfigType() {
        return this.configType;
    }

    public void setLabel(String value) {
        label.setText(value);
    }

    public String getLabel() {
        return label.getText();
    }

    public String getPath() {
        return textField.getText();
    }

    public void setPath(String path) {
        textField.setText(path);
    }

    @Override
    protected void onBtnClick(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == button) {
            onSelectFile();

        } else if (mouseEvent.getSource() == openFolderBtn) {
            if (isFolder) {
                FileOperator.openFile(getPath());
            } else {

                FileOperator.openFileAndSelect(getPath());
            }
        }


    }

    private void onSelectFile() {
        File file;
        if (isFolder) {
            DirectoryChooser directoryChooser = new DirectoryChooser();

            File oldFile = new File(getPath());
            if (oldFile.exists()) {
                if (!oldFile.isDirectory()) {
                    oldFile = oldFile.getParentFile();
                }
                directoryChooser.setInitialDirectory(oldFile);
            }
            file = directoryChooser.showDialog(textField.getScene().getWindow());
        } else {
            FileChooser fileChooser = new FileChooser();
            File oldFile = new File(getPath());
            if (oldFile.exists()) {
                if (!oldFile.isDirectory()) {
                    oldFile = oldFile.getParentFile();
                }
                fileChooser.setInitialDirectory(oldFile);
            }
            file = fileChooser.showOpenDialog(textField.getScene().getWindow());
        }


        if (file != null) {
            textField.setText(file.getPath());
            configMapper.setConfig(configType.name(), file.getPath());
        }
    }

    @Override
    protected void onTextChange(String oldValue, String newValue) {
        configMapper.setConfig(configType.name(), newValue);
    }

    public String getFileName() {
        if (StringUtils.isEmpty(getPath())) {
            return "";
        }
        return new File(getPath()).getName();
    }
}
