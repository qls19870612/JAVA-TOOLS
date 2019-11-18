package sample.fxml.controllers;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

import org.iq80.snappy.SnappyInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import sample.ITab;
import sample.config.AppConfig;
import sample.file.FileOperator;
import sample.utils.StringUtils;

/**

 *@描述
 *@创建人 liangsong

 *@创建时间 2018/7/13/013 10:52
 */
public class StringFormatterController implements ITab {
    private static final Logger logger = LoggerFactory.getLogger(StringFormatterController.class);
    public TextField uplowerTF;
    public Label afterConvertTF;
    public TextField upperCaseTF;
    public Label afterConvertUpperCaseTF;
    public TextArea srcStr;
    public TextArea toStr;
    private boolean inited;

    public void onSelect() {
        if (inited) {
            return;

        }
        inited = true;

        try {
            addListener(uplowerTF, afterConvertTF, StringUtils.class.getMethod("toUpLowerString", String.class));
            addListener(upperCaseTF, afterConvertUpperCaseTF, StringUtils.class.getMethod("toUpCase", String.class));
            addListener(srcStr, toStr, StringUtils.class.getMethod("convertToString", String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onAppClose() {

    }

    private void addListener(TextInputControl textField, Label afterTf, Method formatMethod) {
        textField.setOnMouseClicked(event -> textField.setText(""));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            //                String string = StringUtils.toUpLowerString(newValue);
            String string = null;
            try {
                string = (String) formatMethod.invoke(null, newValue);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            afterTf.setText(string);
            if (StringUtils.isNotEmpty(string)) {
                copyTxt(afterTf);
            }
        });

    }

    private void addListener(TextInputControl src, TextArea target, Method formatMethod) {
        src.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                src.setText("");
            }
        });
        src.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //                String string = StringUtils.toUpLowerString(newValue);
                String string = null;
                try {
                    string = (String) formatMethod.invoke(null, newValue);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                target.setText(string);
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

    private boolean hasLogFile(List<File> files) {
        for (File file : files) {
            if (file.isDirectory()) {
                File[] a = file.listFiles();
                if (a != null) {
                    boolean b = hasLogFile(Arrays.asList(a));
                    if (b) {
                        return true;
                    }
                }
            } else if (file.getName().endsWith(".log")) {
                return true;
            }
        }
        return false;

    }

    public void onDragDropped(DragEvent dragEvent) {
        logger.debug("onDragDropped dragEvent.getEventType:{}", dragEvent.getEventType());
        unCompress(dragEvent.getDragboard().getFiles());
    }

    private void unCompress(List<File> files) {
        for (File file : files) {
            if (file.isDirectory()) {
                File[] a = file.listFiles();
                if (a != null) {
                    unCompress(Arrays.asList(a));
                }
            } else if (file.getName().endsWith(".log")) {
                try {
                    File newDir = new File(file.getParent() + "/uncompress");
                    if (!newDir.exists()) {
                        newDir.mkdir();
                    }
                    uncompress(newDir, file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void uncompress(File newDir, File logFile) throws IOException {
        byte[] bytes = Files.toByteArray(logFile);


        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        SnappyInputStream snappyFramedInputStream = new SnappyInputStream(byteArrayInputStream, true);
        byte[] uncompress = ByteStreams.toByteArray(snappyFramedInputStream);
        byteArrayInputStream.close();
        snappyFramedInputStream.close();
        String string = new String(uncompress);
        File file = new File(newDir.getPath() + "/" + logFile.getName());

        FileOperator.writeFile(file, string);
    }

    public void onDragOver(DragEvent dragEvent) {
        if (!hasLogFile(dragEvent.getDragboard().getFiles())) {
            return;
        }
        dragEvent.acceptTransferModes(TransferMode.MOVE);

        dragEvent.consume();


    }
}
