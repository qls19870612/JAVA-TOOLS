package sample.fxml.renders;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.fxml.componet.NumberTextField;
import sample.fxml.controllers.gm.handlers.GmHandler.CmdParam;
import sample.utils.StringUtils;

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
        this.getChildren().add(label);
    }

    private void reset() {
        this.getChildren().clear();
        this.param = null;
    }

    public void updateData(CmdParam param) {
        this.param = param;
        if (label.getParent() == null) {
            this.getChildren().add(label);
        }
        label.setText(param.paramsName);
        switch (param.type) {
            case INT:
            case LONG:
                if (numberTextField == null) {
                    numberTextField = new NumberTextField();
                    numberTextField.prefWidth(60);
                }
                numberTextField.setLayoutX(offsetX);
                this.getChildren().add(numberTextField);
                numberTextField.setText("");
                numberTextField.setPromptText(param.defaultValue.toString());
                break;
            case STRING:
                if (textInput == null) {
                    textInput = new TextField();
                    textInput.prefWidth(60);
                }
                textInput.setLayoutX(offsetX);
                this.getChildren().add(textInput);
                textInput.setText("");
                textInput.setPromptText(param.defaultValue.toString());
                break;
            case BOOLEAN:
                if (combox == null) {
                    combox = new ComboBox<>();
                    combox.setEditable(false);
                    combox.getItems().add(false);
                    combox.getItems().add(true);
                }
                combox.setLayoutX(offsetX);
                this.getChildren().add(combox);
                if ((Boolean) param.defaultValue) {
                    combox.getSelectionModel().select(1);
                } else {
                    combox.getSelectionModel().select(0);
                }
                break;

        }
    }

    public boolean isValid() {
        if (this.param.defaultValue == null) {
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
                return combox.getSelectionModel().getSelectedItem() ? "1" : "0";
        }
        return "";
    }
}
