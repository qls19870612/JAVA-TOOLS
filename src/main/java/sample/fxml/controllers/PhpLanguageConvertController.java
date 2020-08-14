package sample.fxml.controllers;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import sample.ITab;
import sample.file.FileOperator;
import sample.file.FileOperator.Filter;
import sample.fxml.componet.AlertBox;
import sample.fxml.componet.fxml.FileSelector;

import static sample.file.FileOperator.NEX_LINE;
import static sample.file.FileOperator.get_charset;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/08/07 15:32
 */
public class PhpLanguageConvertController implements ITab {
    private static final Logger logger = LoggerFactory.getLogger(PhpLanguageConvertController.class);
    private static final String PHP_LANG_FILE_EXT_NAME = ".inc.php";
    public FileSelector convertToFolder;
    public static Pattern langPattern = Pattern.compile("^\\s*(\\d+)\\s*=>(\\s*.*),");
    public static Pattern paramAPattern = Pattern.compile("'?\\s*\\.?\\$a(\\d)\\s*\\.*\\s*'?",Pattern.DOTALL);
    private File toFileRoot;

    @Override
    public void onSelect() {

    }

    @Override
    public void onAppClose() {

    }


    private void convertOneFile(File file, File toFileRoot) {
        String code = null;
        try {
            code = get_charset(file);
        } catch (Exception e) {
            //            System.out.println(e.getMessage());
            return;
        }
//        String phpContent = FileOperator.readFiles(file);
        BufferedReader reader = null;

        FileInputStream fileInput = null;
        InputStreamReader input = null;
        try {
            fileInput = new FileInputStream(file.getPath());
            input = new InputStreamReader(fileInput, code);
            reader = new BufferedReader(input);
            String tempStr = null;
            JSONObject jsonObject = new JSONObject(true);
            while ((tempStr = reader.readLine()) != null) {
//
                Matcher matcher = langPattern.matcher(tempStr);
                if (matcher.find()) {
                    String group1 = matcher.group(1);
                    String group2 = matcher.group(2);

//                    logger.debug("convertOneFile group1:{},group2:{}", group1,group2);
                    Matcher paramMather = paramAPattern.matcher(group2);
                    String s = paramMather.replaceAll("{a$1}");
                    s = s.trim();
                    if (s.startsWith("'")) {
                        s = s.substring(1);
                    }
                    if(s.endsWith("'")){
                        s = s.substring(0,s.length()-1);
                    }
                    jsonObject.put(group1,s);
//                    logger.debug("convertOneFile s:{}", s);

                }
            }
            String s =  JSONObject.toJSONString(jsonObject, SerializerFeature.PrettyFormat);
            logger.debug("convertOneFile s:{}", s);
            String newFileName = file.getName().substring(0,
                    file.getName().length() - PHP_LANG_FILE_EXT_NAME.length()) + ".json";
            FileOperator.writeFile(new File(toFileRoot.getAbsolutePath() + "/" + newFileName),s);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fileInput != null) {
                    fileInput.close();
                }
                if (input != null) {
                    input.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean hasFile(List<File> files) {
        for (File file : files) {
            if (file.isDirectory()) {
                File[] a = file.listFiles();
                if (a != null) {
                    boolean b = hasFile(Arrays.asList(a));
                    if (b) {
                        return true;
                    }
                }
            } else if (file.getName().endsWith(PHP_LANG_FILE_EXT_NAME)) {
                return true;
            }
        }
        return false;

    }

    public void onDragDropped(DragEvent dragEvent) {

        if (!convertToFolder.isExistsDirectory()) {
            AlertBox.showAlert("没有设置需要转换到哪个目录！");
            return;
        }
        toFileRoot = new File(convertToFolder.getPath());
        List<File> files = dragEvent.getDragboard().getFiles();
        createFiles(files);

    }

    private void createFiles(List<File> files) {
        try {
            scanDir(files);
        }
        catch (Exception e)
        {
            logger.error("onDragDropped getMessage:{}", e.getMessage());
        }
    }

    private void scanDir(List<File> files) {

        for (File file : files) {
            if (file.isDirectory()) {
                File[] a = file.listFiles();
                if (a != null) {
                    scanDir(Arrays.asList(a));
                }
            } else if (file.getName().endsWith(PHP_LANG_FILE_EXT_NAME)) {
                convertOneFile(file,toFileRoot);
            }
        }
    }
    public void onDragOver(DragEvent dragEvent) {
        if (!hasFile(dragEvent.getDragboard().getFiles())) {
            return;
        }
        dragEvent.acceptTransferModes(TransferMode.MOVE);

        dragEvent.consume();
    }

     
}










