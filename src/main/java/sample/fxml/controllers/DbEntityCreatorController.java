package sample.fxml.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.ITab;
import sample.db.EntryCreator;
import sample.db.TableField;
import sample.db.TableStruct;
import sample.enums.ConfigType;
import sample.fxml.componet.fxml.FileSelector;
import sample.fxml.componet.fxml.InputComponent;
import sample.fxml.config.DbConfig;
import sample.fxml.renders.DbConfigRender;
import sample.fxml.renders.TableFieldRender;
import sample.fxml.renders.TableRender;
import sample.mapper.ConfigMapper;
import sample.mapper.DbConfigMapper;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/21 14:52
 */
public class DbEntityCreatorController implements ITab {
    private static final Logger logger = LoggerFactory.getLogger(DbEntityCreatorController.class);
    public FileSelector srcFolderSelector;
    public InputComponent packageNameInput;
    public InputComponent classNamePrefixInput;
    public InputComponent classNameSuffixInput;
    public InputComponent dbPasswordInput;
    public InputComponent dbNameInput;
    public InputComponent dbUserNameInput;
    public InputComponent dbUrlInput;
    public Button createBtn;
    public ComboBox<String> dbComboBox;
    public AnchorPane settingPanel;
    public AnchorPane createPanel;
    public ListView<TableStruct> tableList;
    public ListView<TableField> fieldList;
    public ListView<DbConfig> dbConfigList;
    public Label tableNameLabel;

    private EntryCreator entryCreator;
    private boolean inited;

    @Autowired
    public DbConfigMapper dbConfigMapper;
    @Autowired
    public ConfigMapper configMapper;
    public static DbEntityCreatorController THIS;
    private int configSelectedIndex;
    private TableStruct selectTableStruct;

    @Override
    public void onSelect() {
        if (!inited) {
            inited = true;
            THIS = this;
            init();
        }

    }

    private void init() {

        tableList.setCellFactory(param -> new TableRender());
        tableList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        fieldList.setCellFactory(param -> new TableFieldRender());
        fieldList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        dbConfigList.setCellFactory(param -> new DbConfigRender());
        entryCreator = new EntryCreator();
        dbComboBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {

        });
        tableList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateField(newValue);
        });
        configSelectedIndex = configMapper.getInt(ConfigType.DB_CONFIG_SELECT_INDEX);
        updateDbList();

        dbConfigList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                configSelectedIndex = dbConfigList.getSelectionModel().getSelectedIndex();
                configMapper.setInt(ConfigType.DB_CONFIG_SELECT_INDEX, configSelectedIndex);
                updateConfig(newValue);
            }
        });
    }

    private void updateDbList() {
        DbConfig[] dbConfigDatas = dbConfigMapper.getDbConfigList();
        dbConfigList.getItems().setAll(dbConfigDatas);
        if (dbConfigDatas.length > 0) {
            if (configSelectedIndex == -1) {
                configSelectedIndex = 0;
            } else if (configSelectedIndex >= dbConfigDatas.length) {
                configSelectedIndex = dbConfigDatas.length - 1;
            }
            dbConfigList.getSelectionModel().select(configSelectedIndex);
            updateConfig(dbConfigDatas[configSelectedIndex]);
        } else {
            configSelectedIndex = -1;
        }
    }

    private void updateConfig(DbConfig newValue) {
        srcFolderSelector.setPath(newValue.getPath());
        packageNameInput.setInputText(newValue.getPackageName());
        classNamePrefixInput.setInputText(newValue.getClassNamePrefix());
        classNameSuffixInput.setInputText(newValue.getClassNameSuffix());
        dbUrlInput.setInputText(newValue.getDbUrl());
        dbNameInput.setInputText(newValue.getDbName());
        dbUserNameInput.setInputText(newValue.getDbUserName());
        dbPasswordInput.setInputText(newValue.getDbPassword());
        dbComboBox.getSelectionModel().select(newValue.getDbType());
    }

    private void updateField(TableStruct newValue) {
        if (newValue == null) {
            return;
        }
        tableNameLabel.setText(newValue.getCompoundLabel());
        selectTableStruct = newValue;
        fieldList.getItems().setAll(newValue.fields);
    }

    @Override
    public void onAppClose() {

    }

    public void onCreateBtnClick(MouseEvent mouseEvent) {

        try {
            DbConfig dbConfig = new DbConfig(srcFolderSelector.getPath(), packageNameInput.getInputText(), classNamePrefixInput.getInputText(),
                    classNameSuffixInput.getInputText(), dbUrlInput.getInputText(), dbNameInput.getInputText(), dbUserNameInput.getInputText(),
                    dbPasswordInput.getInputText(), getDbType());
            dbConfigMapper.insert(dbConfig);
            updateDbList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDbType() {
        return dbComboBox.getSelectionModel().getSelectedItem().toString();
    }

    public void onSettingBtnClick(MouseEvent mouseEvent) {
        createPanel.setVisible(false);
        settingPanel.setVisible(true);
        //        logger.debug("onSettingBtnClick createPanel.getHeight:{}", createPanel.getHeight());
    }

    public void returnBtnClick(MouseEvent mouseEvent) {
        createPanel.setVisible(true);
        settingPanel.setVisible(false);
        try {
            entryCreator.initDbStruct(dbUrlInput.getInputText(), dbNameInput.getInputText(), dbUserNameInput.getInputText(),
                    dbPasswordInput.getInputText(), getDbType());
            Collection<TableStruct> values = entryCreator.structHashMap.values();
            tableList.getItems().setAll(values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //        logger.debug("returnBtnClick createPanel.getHeight:{}", createPanel.getHeight());
    }

    public void deleteDbConfig(DbConfig item) {
        dbConfigMapper.deleteConfig(item.getId());
        updateDbList();
    }

    public void createAllTableEntity(MouseEvent mouseEvent) {

        for (TableStruct tableStruct : tableList.getItems()) {
            if (tableStruct.isSelected()) {
                entryCreator
                        .createEntity(tableStruct, srcFolderSelector.getPath(), packageNameInput.getInputText(), classNamePrefixInput.getInputText(),
                                classNameSuffixInput.getInputText());
            }
        }
    }

    public void createSelectTableEntity(MouseEvent mouseEvent) {
        if (selectTableStruct == null) {
            return;
        }
        entryCreator
                .createEntity(selectTableStruct, srcFolderSelector.getPath(), packageNameInput.getInputText(), classNamePrefixInput.getInputText(),
                        classNameSuffixInput.getInputText(), true);
    }

    public void onTableSelect(TableRender tableRender) {

        boolean selected = tableRender.checkBox.isSelected();
        tableRender.getItem().setSelected(selected);
        ObservableList<TableStruct> selectedItems = tableList.getSelectionModel().getSelectedItems();
        if (selectedItems.size() > 1) {

            for (TableStruct selectedItem : selectedItems) {
                if (selectedItem != null) {
                    selectedItem.setSelected(selected);
                }
            }
        }
        tableList.refresh();
    }

    public void onTableFieldSelect(TableFieldRender tableFieldRender) {
        boolean selected = tableFieldRender.checkBox.isSelected();
        tableFieldRender.getItem().setSelected(selected);
        ObservableList<TableField> selectedItems = fieldList.getSelectionModel().getSelectedItems();
        if (selectedItems.size() > 1) {

            for (TableField selectedItem : selectedItems) {
                if (selectedItem != null) {
                    selectedItem.setSelected(selected);

                }
            }
        }
        fieldList.refresh();
    }
}
