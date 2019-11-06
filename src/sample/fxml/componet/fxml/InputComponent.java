package sample.fxml.componet.fxml;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import sample.enums.ConfigType;
import sample.interfaces.AutowireInterface;
import sample.mapper.ConfigMapper;
import sample.utils.StringUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/10/24 18:03
 */
public class InputComponent extends AbstractInputComponent implements AutowireInterface {
    private ConfigType configType;
    @Autowired
    private ConfigMapper configMapper;

    public InputComponent() {
        super();
    }

    @FXML
    public void setConfigType(ConfigType configType) {
        this.configType = configType;
        //        TableMangerService bean = SpringUtil.getBean(TableMangerService.class);
        //        configMapper = bean.configMapper;
        String config = configMapper.getConfig(configType);
        if (StringUtils.isNotEmpty(config)) {
            textField.setText(config);
        }
        button.setText("清除");
        textField.setPromptText("");
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

    public String getInputText() {
        return textField.getText();
    }

    @Override
    protected void onBtnClick(MouseEvent mouseEvent) {

        textField.setText("");
        configMapper.setConfig(configType.name(), "");

    }

    @Override
    protected void onTextChange(String oldValue, String newValue) {
        configMapper.setConfig(configType.name(), newValue);
    }
}
