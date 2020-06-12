package app.game.service.notice;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/08/17 19:36
 */
public class ExcelUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        int cellType = cell.getCellType();
        String value;
        switch (cellType) {
            case Cell.CELL_TYPE_NUMERIC:
                value = new BigDecimal(cell.getNumericCellValue()).toPlainString();
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = cell.getCellFormula();
                logger.warn("cell.getCellFormula():{}" + cell.getCellFormula());
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

    public static void writeCell(Cell cell, String s) {
        if (cell == null) {
            return;
        }
        if (StringUtils.isEmpty(s)) {
            return;
        }

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                cell.setCellValue(Double.valueOf(s));
                break;
            case Cell.CELL_TYPE_STRING:
                cell.setCellValue(s);
                break;
            case Cell.CELL_TYPE_FORMULA:

                break;
            case Cell.CELL_TYPE_BLANK:
                cell.setCellValue(s);
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                boolean b = s.equals("1") ? true : false;
                cell.setCellValue(b);
                break;
            case Cell.CELL_TYPE_ERROR:
                break;
            default:
                logger.debug("default cellType:{}", cell.getCellType());

        }
    }
}
