package sample.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import game.initializer.io.loader.FileLoader;
import game.initializer.io.loader.FileLoaderOS;

import static sample.utils.CodeCreateUtils.getEncodePath;
import static sample.utils.Xls2TxtUtils.getSheetNum;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/06/04 19:03
 */
public class XlsDoubleColCompareUtils {

    public static void compare(File file) throws IOException {
        FileLoader fileLoader = FileLoaderOS.of(file.getPath().replace(file.getName(), ""));

        String fileName = getEncodePath(file.getName());
        byte[] fileContent = fileLoader.fileToBytes(fileName, true);

        InputStream is = new ByteArrayInputStream(fileContent);
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        int sheetNum = getSheetNum(workbook);
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            compareSheet(file, sheetNum, sheet);
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file.getPath().replace(".xls","_hand.xls"));
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private static void compareSheet(File file, int sheetNum, Sheet sheet) {
        HashMap<String,Integer> firstColMap = new HashMap<>();
        HashMap<String,Integer> secondColMap = new HashMap<>();
        for(int i = 0; i < 10000; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                break;
            }
            Cell cell0 = row.getCell(0);
            Cell cell1 = row.getCell(1);
            if (cell0 == null && cell1 == null) {
                break;
            }
            String cellValue0 = ExcelUtils.getCellValue(cell0);
            String cellValue1 = ExcelUtils.getCellValue(cell1);
            if (StringUtils.isNotEmpty(cellValue0)) {
                firstColMap.put(cellValue0,i);
            }
            if (StringUtils.isNotEmpty(cellValue1)) {
                secondColMap.put(cellValue1,i);
            }
            if (cell1 !=null) {

                cell1.setCellType(Cell.CELL_TYPE_STRING);
                cell1.setCellValue("");
            }
        }
        ArrayList<String> notExitRows = new ArrayList<>();
        for (Entry<String, Integer> entry : firstColMap.entrySet()) {

            Row row = sheet.getRow(entry.getValue());
            if (secondColMap.containsKey(entry.getKey())) {
                Cell cell = row.getCell(1);
                if (cell == null) {
                    cell = row.createCell(1);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                }
                cell.setCellValue(entry.getKey());

            }
            else {
                notExitRows.add(entry.getKey());

            }

        }
        notExitRows.sort(String::compareTo);
        int thirdColCount = 0;
        for (String notExitRow : notExitRows) {
            Row sheetRow = sheet.getRow(thirdColCount++);

            Cell cell = sheetRow.getCell(2);
            if (cell == null) {
                cell = sheetRow.createCell(2);
                cell.setCellType(Cell.CELL_TYPE_STRING);
            }
            cell.setCellValue(notExitRow);
        }
    }
}
