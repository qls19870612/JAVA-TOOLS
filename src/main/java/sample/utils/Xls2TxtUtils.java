package sample.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
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
import java.math.MathContext;
import java.text.NumberFormat;
import java.util.Iterator;

import game.initializer.io.loader.FileLoader;
import game.initializer.io.loader.FileLoaderOS;
import sample.file.FileOperator;

import static sample.Controller.log;
import static sample.fxml.controllers.XlsController.EMPTY_LINE;
import static sample.utils.CodeCreateUtils.getEncodePath;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/10/26 11:51
 */
public class Xls2TxtUtils {
    private static final Logger logger = LoggerFactory.getLogger(Xls2TxtUtils.class);


    public static void createTxt(File file) {
        try {
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
                createShellFile(filePath, sheetNum, sheet);
            }


        } catch (IOException e) {
            e.printStackTrace();
            log("警告", "配置文件解析失败:" + e.getMessage());
        }
    }

    private static void createShellFile(String filePath, int sheetNum, Sheet sheet) {
        int rowNum = sheet.getLastRowNum() + 1;
        if (rowNum < 3) {
            return;
        }


        String string = shellContentToBuff(sheet, filePath);
        String sheetName = sheet.getSheetName();
        String txtPath;
        if (sheetNum > 1) {
            txtPath = filePath.replace(".xls", "_") + sheetName + ".txt";
        } else {
            txtPath = filePath.replace(".xls", ".txt");
        }
        //        logger.debug("{} stringBuffer:\n{}",filePath ,string);
        FileOperator.writeFile(new File(txtPath), string);
    }

    private static String shellContentToBuff(Sheet sheet, String filePath) {
        int rowCount = sheet.getLastRowNum() + 1;
        int firstEmptyLine = EMPTY_LINE;
        int emptyLineCharIndex = 0;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                logger.debug("filePath:{} rowCount:{} i:{}", filePath, rowCount, i);
                if (firstEmptyLine == EMPTY_LINE) {
                    firstEmptyLine = i;
                    emptyLineCharIndex = stringBuffer.length();
                }
                continue;
            }
            firstEmptyLine = EMPTY_LINE;
            int colCount = row.getLastCellNum() + 1;
            if (i > 0) {
                stringBuffer.append("\r\n");
            }

            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                if (j > 0) {
                    stringBuffer.append("\t");
                }
                String value = getStringFromCellValue(cell);

                stringBuffer.append(value);
            }
        }
        if (firstEmptyLine != EMPTY_LINE) {
            logger.warn("{} 文件末尾有空行:{}", firstEmptyLine);
            return stringBuffer.substring(0, emptyLineCharIndex);
        }
        return stringBuffer.toString();
    }

    public static String getStringFromCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        int cellType = cell.getCellType();
        String value;
        switch (cellType) {
            case Cell.CELL_TYPE_NUMERIC:
                MathContext mc = MathContext.DECIMAL64;
                double numericCellValue = ((HSSFCell) cell).getNumericCellValue();
                //double v = new BigDecimal(numericCellValue).round(mc).floatValue();
                double v = numericCellValue;
                long intv = (long) v;
                if (intv < v) {
                    NumberFormat instance = NumberFormat.getInstance();
                    instance.setGroupingUsed(false);
                    instance.setMaximumFractionDigits(10);
                    value = instance.format(v);
                } else {
                    value = String.valueOf(intv);
                }
                //                logger.debug("getCellValue value:{}", value);
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                try {
                    value = ((long) cell.getNumericCellValue()) + "";

                } catch (Exception e) {
                    value = cell.getStringCellValue();
                    logger.warn("cell.getCellFormula():{}" + cell.getCellFormula());
                }
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue() ? "1" : "0";
                logger.debug("value:{}", value);
                break;
            case Cell.CELL_TYPE_ERROR:
                value = "";
                logger.warn("cell.getStringCellValue():{}" + cell.getErrorCellValue());
                break;
            default:
                logger.debug("default cellType:{}", cellType);
                value = cell.getStringCellValue();
        }


        if (value.length() > 0) {
            value = value.replaceAll("\\n", " ");
        }
        return value;
    }
    public static Object getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        int cellType = cell.getCellType();
        Object value;
        switch (cellType) {
            case Cell.CELL_TYPE_NUMERIC:
                MathContext mc = MathContext.DECIMAL64;
                double numericCellValue = ((HSSFCell) cell).getNumericCellValue();
                //double v = new BigDecimal(numericCellValue).round(mc).floatValue();
//                double v = numericCellValue;
//                long intv = (long) v;
//                if (intv < v) {
//                    NumberFormat instance = NumberFormat.getInstance();
//                    instance.setGroupingUsed(false);
//                    instance.setMaximumFractionDigits(10);
//                    value = instance.format(v);
//                } else {
//                    value = String.valueOf(intv);
//                }
                value = numericCellValue;
                //                logger.debug("getCellValue value:{}", value);
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                try {
                    value = ((long) cell.getNumericCellValue())  ;
                } catch (Exception e) {
                    value = cell.getStringCellValue();
                    logger.warn("cell.getCellFormula():{}" + cell.getCellFormula());
                }
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue() ? 1 : 0;
                logger.debug("value:{}", value);
                break;
            case Cell.CELL_TYPE_ERROR:
                value = "";
                logger.warn("cell.getStringCellValue():{}" , cell.getErrorCellValue());
                break;
            default:
                logger.debug("default cellType:{}", cellType);
                value = cell.getStringCellValue();
        }


        if (value instanceof String) {
            value = ((String) value).replaceAll("\\n", " ");
        }
        return value;
    }

    public static int getSheetNum(HSSFWorkbook workbook) {
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        int sheetNum = 0;
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            int rowNum = sheet.getLastRowNum() + 1;
            if (rowNum < 3) {
                continue;
            }
            sheetNum++;
        }
        return sheetNum;
    }

}
