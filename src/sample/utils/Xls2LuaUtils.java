package sample.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Pattern;

import game.initializer.io.loader.FileLoader;
import game.initializer.io.loader.FileLoaderOS;
import sample.datas.vo.XlsInfo;
import sample.file.FileOperator;
import sample.fxml.controllers.XlsController;

import static sample.Controller.log;
import static sample.config.AppConfig.luaPath;
import static sample.utils.CodeCreateUtils.dateTimeFormatter;
import static sample.utils.CodeCreateUtils.getEncodePath;
import static sample.utils.Xls2TxtUtils.getCellValue;
import static sample.utils.Xls2TxtUtils.getSheetNum;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/10/26 10:16
 */
public class Xls2LuaUtils {
    private static final Logger logger = LoggerFactory.getLogger(Xls2LuaUtils.class);
    private static final char TABLE = '\t';

    public static boolean createLua(XlsInfo item) {
        if (!item.isNeedUpdate()) {
            return false;
        }
        try {
            File file = item.file;
            FileLoader fileLoader = FileLoaderOS.of(file.getPath().replace(file.getName(), ""));
            String filePath = file.getPath();
            System.out.println("filePath:" + filePath);
            String fileName = getEncodePath(file.getName());
            byte[] fileContent = fileLoader.fileToBytes(fileName, true);

            InputStream is = new ByteArrayInputStream(fileContent);
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            int sheetNum = getSheetNum(workbook);
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                createShellFile(file, sheetNum, sheet);
            }
            item.updateLuaCreateTime();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            log("警告", "配置文件解析失败:" + e.getMessage());
        }
        return false;
    }

    private static void createShellFile(File file, int sheetNum, Sheet sheet) {
        String sheetName = sheet.getSheetName();
        if (sheetName.startsWith("Sheet") || Pattern.compile("[\u4e00-\u9fa5]").matcher(sheetName).find()) {
            return;
        }
        String filePath = file.getParent();
        String item = shellContentToBuff(sheet, filePath, file.getName());
        String date = dateTimeFormatter.get().format(new Date());
        String content = XlsController.luaTemplate.replace("&date&", date);
        content = content.replace("&fileName&", file.getName());
        content = content.replace("&item&", item);
        String txtPath = luaPath + "/" + sheetName + ".lua";
        FileOperator.writeFile(new File(txtPath), content);

    }

    private static String shellContentToBuff(Sheet sheet, String filePath, String fileName) {
        int rowCount = sheet.getLastRowNum() + 1;

        StringBuilder stringBuffer = new StringBuilder();
        String[] header = null;
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                logger.debug("空行filePath:{}\\{} rowCount:{} i:{}", fileName, filePath, rowCount, i);

                continue;
            }


            int colCount = row.getLastCellNum() + 1;
            if (i == 0) {
                header = new String[colCount];
            }
            if (i == 1 || i == 2) {
                continue;
            }

            boolean isAdd = false;
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                String value = getCellValue(cell);
                if (i == 0) {
                    header[j] = StringUtils.trim(value);
                    continue;
                } else {

                    assert header != null;
                    if (j >= header.length) {
                        break;
                    }
                }

                if (cell == null || StringUtils.isEmpty(header[j])) {
                    continue;
                }
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                    case Cell.CELL_TYPE_NUMERIC:
                    case Cell.CELL_TYPE_FORMULA:
                        break;
                    default:
                        if (StringUtils.isEmpty(value)) {
                            continue;
                        }
                        value = "'" + value + "'";

                }

                if (j == 0) {
                    stringBuffer.append(TABLE);
                    stringBuffer.append('{');
                }
                stringBuffer.append(header[j]);
                stringBuffer.append(" ");
                stringBuffer.append("=");
                stringBuffer.append(" ");
                stringBuffer.append(value);
                stringBuffer.append(",");
                isAdd = true;
            }
            if (isAdd) {
                //                stringBuffer.replace(stringBuffer.length() - 1, stringBuffer.length(), "");
                stringBuffer.append("},\n");
            }
        }
        if (stringBuffer.length() > 0) {
            return stringBuffer.substring(0, stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }


}
