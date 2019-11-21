package sample.fxml.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import sample.ITab;
import sample.db.EntryCreator;
import sample.fxml.componet.fxml.FileSelector;
import sample.fxml.componet.fxml.InputComponent;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/21 14:52
 */
public class DbEntityCreatorController implements ITab {
    private static final Logger logger = LoggerFactory.getLogger(DbEntityCreatorController.class);
    public FileSelector srcFolderSelector;
    public InputComponent packageNameInput;
    public InputComponent packageNamePrefixInput;
    public InputComponent packageNameSuffixInput;
    public InputComponent dbPasswordInput;
    public InputComponent dbNameInput;
    public InputComponent dbUserNameInput;
    public InputComponent dbUrlInput;
    public Button createBtn;
    public ComboBox dbComboBox;

    private EntryCreator entryCreator;

    @Override
    public void onSelect() {

        entryCreator = new EntryCreator();
        dbComboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            }
        });
    }

    @Override
    public void onAppClose() {

    }

    public void onCreateBtnClick(MouseEvent mouseEvent) {
        //        String string = StringUtils.toUpLowerString(dbUserNameInput.getInputText(), true);
        //        logger.debug("onCreateBtnClick string:{}", string);
        try {
            entryCreator.createEntity(srcFolderSelector.getPath(), packageNameInput.getInputText(), packageNamePrefixInput.getInputText(),
                    packageNameSuffixInput.getInputText(), dbUrlInput.getInputText(), dbNameInput.getInputText(), dbUserNameInput.getInputText(),
                    dbPasswordInput.getInputText(), dbComboBox.getSelectionModel().getSelectedItem().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
