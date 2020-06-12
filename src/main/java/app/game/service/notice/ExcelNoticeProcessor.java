package app.game.service.notice;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

import game.collection.IntHashMap;
import game.initializer.io.loader.FileLoader;
import game.initializer.io.loader.FileLoaderOS;
import io.ytcode.reflect.clazz.Classes;
import io.ytcode.reflect.resource.Scanner;
import sample.config.AppConfig;

/**
 *用来创建消息提示的 把IMsgModuleMessage接口对应的枚举值更新到X-消息提示.xls文件中，供前端调用
 * 创建人  liangsong
 * 创建时间 2018/08/17 18:47
 */
public class ExcelNoticeProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ExcelNoticeProcessor.class);
    public static final String moduleId = "moduleId";
    public static final String msgId = "msgId";
    public static final String serverMsg = "serverMsg";
    public static final String id = "q_id";
    public static final int HEAD_LINE = 3;
    private final ArrayList<MyRowInfo> rowInfos = new ArrayList<>();
    private final IntHashMap<MyRowInfo> rowInfoMap = new IntHashMap<>();
    private int colNum;
    private int rowCount;
    private final IntHashMap<MyRowInfo> newAddDataAtThisTime = new IntHashMap<>();

    public static void main(String[] args) throws IllegalAccessException {
        new ExcelNoticeProcessor().updateNotice();

    }

    private void updateNotice() throws IllegalAccessException {
        File file = new File(AppConfig.noticePath);
        try {
            FileLoader fileLoader = FileLoaderOS.of(file.getPath().replace(file.getName(), ""));
            String filePath = file.getPath();
            logger.debug("updateNotice filePath:{}", filePath);
            String fileName = file.getName();
            byte[] fileContent = fileLoader.fileToBytes(fileName, true);

            InputStream is = new ByteArrayInputStream(fileContent);
            HSSFWorkbook workbook = new HSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);
            updateShellFile(sheet);
            newAddDataAtThisTime.clear();
            addNewMsgAndUpdateOldMsg();
            sortMsgs();
            writeToXls(file, workbook, sheet);


        } catch (IOException e) {
            e.printStackTrace();
            logger.error("警告", "配置文件解析失败:" + e.getMessage());
        }

    }

    private void writeToXls(File file, HSSFWorkbook workbook, Sheet sheet) throws IOException {
        clearWorkBookContent(sheet);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(workbook.createDataFormat().getFormat("@"));

        int newRowLine = rowInfos.size();
        for (int i = 0; i < newRowLine; i++) {
            Row row = sheet.createRow(i + HEAD_LINE);
            MyRowInfo myRowInfo = rowInfos.get(i);
            myRowInfo.setId(i + 1);

            int colCount = myRowInfo.arrayList.length;
            for (int j = 0; j < colCount; j++) {
                if (myRowInfo.getCellIsEmpty(j)) {
                    continue;
                }
                Cell cell = row.createCell(j);
                cell.setCellStyle(cellStyle);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                ExcelUtils.writeCell(cell, myRowInfo.arrayList[j]);
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private void clearWorkBookContent(Sheet sheet) {

        for (int i = HEAD_LINE; i < rowCount; i++) {
            sheet.removeRow(sheet.getRow(i));
        }
    }

    private void sortMsgs() {
        rowInfos.sort(Comparator.comparingInt(MyRowInfo::getKey));
    }

    private void addNewMsgAndUpdateOldMsg() throws IllegalAccessException {
        Classes classes = Scanner.pkgs("app.game").scan().classes().subTypeOf(IModuleNotice.class);
        for (Class<?> cls : classes) {
            ModuleNoticeEnum annotation = cls.getAnnotation(ModuleNoticeEnum.class);//没有注解是哪个模块，moduleId = 0(通用)
            int moduleId = 0;
            if (annotation != null) {
                moduleId = annotation.value();
            }
            Field[] fields = cls.getFields();

            for (Field field : fields) {
                Object o = field.get(cls);
                initNotice((IModuleNotice) o, moduleId);
            }
        }
    }


    private void updateShellFile(Sheet sheet) {
        rowCount = sheet.getLastRowNum() + 1;

        if (rowCount < HEAD_LINE) {
            return;//不足3行不处理
        }
        Row row = sheet.getRow(0);
        //        moduleId	msgId	serverMsg

        HashSet<String> mustHaveSet = new HashSet<>();
        mustHaveSet.add(moduleId);
        mustHaveSet.add(msgId);
        mustHaveSet.add(serverMsg);
        mustHaveSet.add(id);

        colNum = row.getLastCellNum();

        for (int i = 0; i < colNum; i++) {

            String head = ExcelUtils.getCellValue(row.getCell(i));

            if (StringUtils.isEmpty(head)) {
                head = String.valueOf(i);
            }
            mustHaveSet.remove(head);
            switch (head) {
                case moduleId:
                    MyRowInfo.moduleIdIndex = i;
                    break;
                case msgId:
                    MyRowInfo.msgIdIndex = i;
                    break;
                case serverMsg:
                    MyRowInfo.serverMsgIndex = i;
                    break;
                case id:
                    MyRowInfo.idIndex = i;
                    break;
            }

        }

        if (mustHaveSet.size() > 0) {
            logger.debug("updateShellFile mustHaveSet.toArray():{}", mustHaveSet.toArray());
            return;
        }
        rowInfos.clear();
        rowInfoMap.clear();
        for (int i = HEAD_LINE; i < rowCount; i++) {
            row = sheet.getRow(i);
            String[] arrayList = new String[colNum];
            for (int j = 0; j < colNum; j++) {

                Cell cell = row.getCell(j);
                String gridValue = ExcelUtils.getCellValue(cell);
                arrayList[j] = gridValue;
            }
            MyRowInfo myRowInfo = new MyRowInfo(arrayList);
            myRowInfo.validKey();
            rowInfos.add(myRowInfo);
            MyRowInfo oldRow = rowInfoMap.putIfAbsent(myRowInfo.getKey(), myRowInfo);
            if (oldRow != null) {
                throw new RuntimeException("数据重复: old:" + oldRow + " new:" + myRowInfo);
            }
        }
    }


    private void initNotice(IModuleNotice o, int moduleId) {
        logger.info("getName(),moduleId:{},enumName:{},msgId:{}", o.getClass().getName(), moduleId, ((Enum) o).name(), o.getMsgId());

        int noticeKey = MyRowInfo.createKey(moduleId, o.getMsgId());
        MyRowInfo myRowInfo;
        myRowInfo = rowInfoMap.get(noticeKey);
        if (myRowInfo == null) {
            String[] arrayList = new String[colNum];


            myRowInfo = new MyRowInfo(arrayList);
            myRowInfo.setModuleId(moduleId);
            myRowInfo.setMsgId(o.getMsgId());
            myRowInfo.validKey();
            rowInfos.add(myRowInfo);
            MyRowInfo oldData = newAddDataAtThisTime.putIfAbsent(myRowInfo.getKey(), myRowInfo);
            if (oldData != null) {
                throw new RuntimeException("本次添加消息有重复: old:" + oldData + " new:" + myRowInfo);
            }
        }
        myRowInfo.setServerMsg(o.getMsgInfo());
    }
}