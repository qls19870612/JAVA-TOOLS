package sample.fxml.renders;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import sample.fxml.componet.NumberTextField;
import sample.fxml.controllers.client.handlers.gm.CmdParam;
import sample.utils.StringUtils;
import sample.utils.Utils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/27 14:54
 */
public class CmdParamsRender extends Group {
    private static final Logger logger = LoggerFactory.getLogger(CmdParamsRender.class);
    private final Label label;
    private TextField textInput;
    private NumberTextField numberTextField;
    private ComboBox<Boolean> combox;
    private static Deque<CmdParamsRender> pool = new ArrayDeque<>();
    private CmdParam param;
    private double offsetX = 210;

    public static final CmdParamsRender create() {
        if (pool.size() > 0) {
            return pool.pollLast();
        }
        return new CmdParamsRender();
    }

    public static final void recycle(CmdParamsRender render) {

        render.reset();
        pool.addLast(render);
    }


    public CmdParamsRender() {
        super();
        this.label = new Label();
        this.label.setLayoutX(50);
        label.setPrefSize(100, 22);
        Tooltip toolTip = new Tooltip();
        Utils.hackTooltipStartTiming(toolTip);
        label.setTooltip(toolTip);
        this.getChildren().add(label);
    }

    private void reset() {
        this.param = null;
        this.getChildren().clear();
        if (numberTextField != null) {
            numberTextField.setText("");
        }
        if (textInput != null) {
            textInput.setText("");
        }
        if (combox != null) {
            combox.getItems().clear();
            combox.getSelectionModel().clearSelection();
        }

    }

    public void updateData(CmdParam param) {
        this.param = param;
        if (label.getParent() == null) {
            this.getChildren().add(label);
        }
        label.setText(param.paramsName);
        label.getTooltip().setText(param.paramsName);
        CmdParamsRender render = this;
        switch (param.type) {
            case INT:
            case LONG:
                if (numberTextField == null) {
                    numberTextField = new NumberTextField();
                    numberTextField.prefWidth(60);
                    numberTextField.textProperty().addListener((observable, oldValue, newValue) -> render.updateParamsInputValue(newValue));
                    numberTextField.onKeyReleasedProperty()
                            .addListener((observable, oldValue, newValue) -> logger.debug("changed oldValue:{},newValue:{}", oldValue, newValue));

                }

                numberTextField.setLayoutX(offsetX);
                this.getChildren().add(numberTextField);
                if (param.getInputValue() != null) {

                    numberTextField.setText(param.getInputValue());
                } else {
                    numberTextField.setText("");
                    numberTextField.setPromptText(param.defaultValue.toString());
                }
                break;
            case STRING:
                if (textInput == null) {
                    textInput = new TextField();
                    textInput.prefWidth(60);
                    textInput.textProperty().addListener((observable, oldValue, newValue) -> render.updateParamsInputValue(newValue));
                }
                textInput.setLayoutX(offsetX);
                this.getChildren().add(textInput);
                if (param.getInputValue() != null) {
                    textInput.setText(param.getInputValue());
                } else {
                    textInput.setText("");
                    textInput.setPromptText(param.defaultValue.toString());
                }
                break;
            case BOOLEAN:
                if (combox == null) {
                    combox = new ComboBox<>();
                    combox.setEditable(false);
                    combox.getItems().add(false);
                    combox.getItems().add(true);
                    ReadOnlyObjectProperty<Boolean> booleanReadOnlyObjectProperty = combox.getSelectionModel().selectedItemProperty();
                    booleanReadOnlyObjectProperty.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                        if (newValue != null && newValue) {
                            render.updateParamsInputValue("1");
                        } else {
                            render.updateParamsInputValue("0");
                        }
                    });
                }
                combox.setLayoutX(offsetX);
                this.getChildren().add(combox);
                if (param.getInputValue() != null) {
                    if ("1".equals(param.getInputValue())) {
                        combox.getSelectionModel().select(1);
                    } else {
                        combox.getSelectionModel().select(0);
                    }
                } else {

                    if ((Boolean) param.defaultValue) {
                        combox.getSelectionModel().select(1);
                    } else {
                        combox.getSelectionModel().select(0);
                    }
                }
                break;

        }
    }

    private void updateParamsInputValue(String newValue) {
        if (param == null) {
            return;
        }
        param.setInputValue(newValue);
    }

    public boolean isValid() {
        if (this.param.defaultValue == null || "".equals(this.param.defaultValue)) {
            //没有默认值
            switch (param.type) {
                case STRING:
                    return StringUtils.isNotEmpty(textInput.getText());
                case INT:
                case LONG:
                    return StringUtils.isNotEmpty(numberTextField.getText());
            }
        }
        return true;
    }

    public String getFiled() {
        return param.paramsName;
    }

    public String getValue() {
        switch (param.type) {
            case LONG:
            case INT:
                if (StringUtils.isNotEmpty(numberTextField.getText())) {
                    return numberTextField.getNumber().toString();
                }
                return param.defaultValue.toString();
            case STRING:
                if (StringUtils.isNotEmpty(textInput.getText())) {
                    return textInput.getText();
                }
                return param.defaultValue.toString();
            case BOOLEAN:
                Boolean selectedItem = combox.getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    return "0";
                }
                return selectedItem ? "1" : "0";
        }
        return "";
    }
}
