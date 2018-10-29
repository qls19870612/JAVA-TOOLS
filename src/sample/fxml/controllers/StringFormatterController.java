package sample.fxml.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import sample.config.AppConfig;
import sample.utils.StringUtils;

/**

 *@描述
 *@创建人 liangsong

 *@创建时间 2018/7/13/013 10:52
 */
public class StringFormatterController {
    public TextField uplowerTF;
    public Label afterConvertTF;
    public TextField upperCaseTF;
    public Label afterConvertUpperCaseTF;

    public void init() throws NoSuchMethodException {
        addListener(uplowerTF, afterConvertTF, StringUtils.class.getMethod("toUpLowerString", String.class));
        addListener(upperCaseTF, afterConvertUpperCaseTF, StringUtils.class.getMethod("toUpCase", String.class));

    }

    private void addListener(TextField textField, Label afterTf, Method formatMethod) {
        textField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                textField.setText("");
            }
        });
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //                String string = StringUtils.toUpLowerString(newValue);
                String string = null;
                try {
                    string = (String) formatMethod.invoke(null, newValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                afterTf.setText(string);
                if (StringUtils.isNotEmpty(string)) {
                    copyTxt(afterTf);
                }
            }
        });

    }

    public void onCopyBtnClick(MouseEvent mouseEvent) {

        copyTxt(afterConvertTF);
    }

    private void copyTxt(Label afterConvertTF) {
        Map<DataFormat, Object> context = new HashMap<>();
        context.put(DataFormat.PLAIN_TEXT, afterConvertTF.getText());
        Clipboard.getSystemClipboard().setContent(context);
    }

    public void onCopyUpperCaseBtnClick(MouseEvent mouseEvent) {
        copyTxt(afterConvertUpperCaseTF);
    }

    public void onOpenBtnClick(MouseEvent mouseEvent) {
        String genMsgPath = AppConfig.genMsgPath;
        String[] split = genMsgPath.split(" ");
        try {
            Runtime.getRuntime().exec("Explorer.exe  /select," + split[split.length - 1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onCreateMsgBtnClick(MouseEvent mouseEvent) {
        try {
            String genMsgPath = AppConfig.genMsgPath;
            Process ps = Runtime.getRuntime().exec(genMsgPath);
            //            ps.waitFor();
            //
            //            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            //            StringBuffer sb = new StringBuffer();
            //            String line;
            //            while ((line = br.readLine()) != null) {
            //                sb.append(line).append("\n");
            //            }
            //            String result = sb.toString();
            //            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
