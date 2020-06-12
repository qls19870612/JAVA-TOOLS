package sample.utils;

import com.google.common.collect.Maps;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;

import game.collection.IntValueIntHashMap;
import game.collection.IntValueIntHashMap.Entry;
import sample.config.AppConfig;
import sample.file.FileOperator;
import sample.file.FileOperator.Filter;
import sample.fxml.componet.AlertBox;

import static sample.fxml.controllers.XlsController.xml2XlsPath;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/04/28 18:01
 */
public class Xml2XlsUtils {

    private static Map<String, Integer> map = Maps.newHashMap();

    static {
        int CELL_TYPE_NUMERIC = 0;
        int CELL_TYPE_STRING = 1;
        int CELL_TYPE_FORMULA = 2;
        int CELL_TYPE_BLANK = 3;
        int CELL_TYPE_BOOLEAN = 4;
        int CELL_TYPE_ERROR = 5;
        map.put("String", CELL_TYPE_STRING);
        map.put("Number", CELL_TYPE_NUMERIC);
        //        map.put("String",CELL_TYPE_STRING);
        //        map.put("String",CELL_TYPE_STRING);

    }

    private static final Logger logger = LoggerFactory.getLogger(Xml2XlsUtils.class);
    private static final float SZIE_CONVERT = 1.5F;

    public static void convert(String xmlPath) {
        File root = new File(xmlPath);
        ArrayList<File> files = FileOperator.getAllFiles(root, new Filter<File>() {
            @Override
            public boolean accept(File entry) {
                if (!entry.getName().endsWith(".xml")) {
                    return false;
                }

                return !entry.getPath().contains("\\scene");
            }
        });
        for (File file : files) {
            String content = FileOperator.readFiles(file);


            try {
                InputSource inputSource = new InputSource(new StringReader(content));

                Document document = AppConfig.db.parse(inputSource);
                Element templates = (Element) document.getElementsByTagName("Worksheet").item(0);
                if (templates == null) {
                    continue;
                }

                String shellName = templates.getAttributes().getNamedItem("ss:Name").getNodeValue();
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet(shellName);


                logger.debug("convert shellName:{}", shellName);
                Node tableEle = templates.getElementsByTagName("Table").item(0);
                NamedNodeMap attributes = tableEle.getAttributes();
                setSheetAttr(sheet, attributes);
                NodeList tableList = tableEle.getChildNodes();
                int length = tableList.getLength();
                IntValueIntHashMap colWidthMap = new IntValueIntHashMap();
                IntValueIntHashMap colTypeMap = new IntValueIntHashMap();
                int rowCount = 0;

                HSSFRow typeRow = null;
                HSSFRow filedNameRow = null;
                for (int i = 0; i < length; i++) {
                    Node row = tableList.item(i);
                    if (row.getNodeType() == Node.ELEMENT_NODE) {
                        String nodeName = row.getNodeName();

                        if ("Column".equals(nodeName)) {
                            //<Column ss:Index="3" ss:AutoFitWidth="0" ss:Width="113.25"/>
                            NamedNodeMap colAttr = row.getAttributes();
                            int index = getIntAttr(colAttr, "ss:Index");
                            float fWidth = getFloatAttr(colAttr, "ss:Width") * SZIE_CONVERT;
                            int width = (int) fWidth;
                            //                                sheet.setColumnWidth(index - 1, width);
                            colWidthMap.put(index - 1, width);

                        } else if ("Row".equals(nodeName)) {
                            Element rowEle = (Element) row;
                            if (rowCount == 1) {
                                typeRow = sheet.createRow(rowCount++);
                            }
                            if (rowCount == 0 || rowCount==2) {
                                filedNameRow = createRow(shellName, rowEle, sheet, rowCount++, null);

                            } else {
                                if (colTypeMap.size() != filedNameRow.getLastCellNum()) {

                                    createRow(shellName, rowEle, sheet, rowCount++, colTypeMap);
                                } else {
                                    createRow(shellName, rowEle, sheet, rowCount++, null);

                                }

                            }
                        } else {
                            logger.debug("convert nodeName:{},getNodeName:{}", nodeName, row.getNodeName());
                        }

                    }

                }
                for (Entry entry : colWidthMap.entrySet()) {
                    sheet.setColumnWidth(entry.getKey(), entry.getValue());
                    sheet.autoSizeColumn(entry.getKey(), true);

                }
                fillTypeRow(shellName, typeRow, colTypeMap);
                writeToXls(new File(xml2XlsPath + "/" + file.getName().replace(".xml", ".xls")), workbook);


            } catch (Exception e) {
                e.printStackTrace();
                logger.error("转换失败 getPath:{}", file.getPath());
                AlertBox.showAlert(e.getMessage());
                return;
            }
        }


        AlertBox.showAlert("全部转换完成");
    }

    private static void fillTypeRow(String shellName, HSSFRow typeRow, IntValueIntHashMap colTypeMap) {
        for (Entry entry : colTypeMap.entrySet()) {
            int col = entry.getKey();
            int type = entry.getValue();
            HSSFCell cell = typeRow.createCell(col);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            switch (type) {
                case Cell.CELL_TYPE_NUMERIC:
                    cell.setCellValue("int");
                    break;
                case Cell.CELL_TYPE_STRING:
                    cell.setCellValue("String");
                    break;
                default:
                    logger.error("fillTypeRow shellName:{},col:{},type:{}", shellName, col, type);
            }
        }
    }

    private static float getFloatAttr(NamedNodeMap colAttr, String s) {
        Node namedItem = colAttr.getNamedItem(s);
        if (namedItem != null) {
            return Float.parseFloat(namedItem.getNodeValue());
        }
        return 0;
    }

    private static int getIntAttr(NamedNodeMap colAttr, String s) {
        Node namedItem = colAttr.getNamedItem(s);
        if (namedItem != null) {
            return Integer.parseInt(namedItem.getNodeValue());
        }
        return 0;
    }

    private static String getStrAttr(NamedNodeMap attributes, String s) {
        Node namedItem = attributes.getNamedItem(s);
        if (namedItem != null) {
            return namedItem.getNodeValue();
        }
        return "";
    }

    public static void writeToXls(File file, HSSFWorkbook workbook) throws IOException {

        try {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("writeToXls getMessage:{}", e.getMessage());
        }
    }


    private static HSSFRow createRow(String shellName, Element row, HSSFSheet sheet, int rowIndex, IntValueIntHashMap colTypeMap) {

        HSSFRow hssfRow = sheet.createRow(rowIndex);

        NodeList cellList = row.getElementsByTagName("Cell");

        int cellCount = cellList.getLength();
        if (cellCount > 200) {
            logger.error("createRow cellCount:{}", cellCount);
            cellCount = 200;
        }
        int cellIndex = -1;
        for (int i = 0; i < cellCount; i++) {
            cellIndex++;
            Node cellNode = cellList.item(i);
            Node item = cellNode.getChildNodes().item(0);
            if (item == null) {
                continue;
            }
            int index = getIntAttr(cellNode.getAttributes(), "ss:Index");
            if (index > 0) {
                cellIndex = index - 1;
            }
            String typeStr = getStrAttr(item.getAttributes(), "ss:Type");
            Integer type = map.get(typeStr);
            if (colTypeMap != null) {
                if (!colTypeMap.containsKey(cellIndex)) {
                    colTypeMap.put(cellIndex, type);
                } else {
                    int oldType = colTypeMap.get(cellIndex);
                    if (oldType != type) {
                        if (type == 0) {
                            colTypeMap.put(cellIndex, type);
                        } else {

                            logger.error("createRow shellName:{},cellIndex:{},rowIndex:{}", shellName, cellIndex, rowIndex);
                            logger.error("createRow oldType:{},type:{}", oldType, type);
                        }
                    }
                }
            }
            String data;
            if (item.getFirstChild() != null) {

                data = item.getFirstChild().getNodeValue();
            } else {
                data = "";
            }

            HSSFCell hssfCell = hssfRow.createCell(cellIndex);

            hssfCell.setCellType(type);

            ExcelUtils.writeCell(hssfCell, data);
        }
        return hssfRow;
    }


    private static void setSheetAttr(HSSFSheet sheet, NamedNodeMap attributes) {
        //  <Table ss:ExpandedColumnCount="7" ss:ExpandedRowCount="72" x:FullColumns="1"
        //   x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="13.5">
        String ExpandedColumnCount = attributes.getNamedItem("ss:ExpandedColumnCount").getNodeValue();
        String ExpandedRowCount = attributes.getNamedItem("ss:ExpandedRowCount").getNodeValue();
        String FullColumns = attributes.getNamedItem("x:FullColumns").getNodeValue();
        String FullRows = attributes.getNamedItem("x:FullRows").getNodeValue();
        String DefaultColumnWidth = attributes.getNamedItem("ss:DefaultColumnWidth").getNodeValue();
        String DefaultRowHeight = attributes.getNamedItem("ss:DefaultRowHeight").getNodeValue();


        sheet.setDefaultRowHeightInPoints(Float.parseFloat(DefaultColumnWidth));
        sheet.setDefaultRowHeightInPoints(Float.parseFloat(DefaultRowHeight));
        //        sheet.createRow(Integer.parseInt(ExpandedRowCount));
    }

    private static CellInfo getCellInfo(Node cell) {
        NodeList datas = cell.getChildNodes();

        CellInfo cellInfo = null;
        int dataLen = datas.getLength();
        for (int k = 0; k < dataLen; k++) {
            Node data = datas.item(k);
            if ("Data".equals(data.getNodeName())) {
                String type = data.getAttributes().getNamedItem("ss:Type").getNodeValue();
                Node item = data.getChildNodes().item(0);
                if (item == null) {
                    cellInfo = new CellInfo(type, "");
                    continue;
                }
                String nodeValue = item.getNodeValue();
                cellInfo = new CellInfo(type, nodeValue);
                break;
            }
        }
        if (cellInfo == null) {
            logger.error("getCellInfo getNodeValue:{}", cell.getNodeValue());
        }
        return cellInfo;
    }

    public static class CellInfo {
        final int type;
        final String sType;
        final String value;

        public CellInfo(String sType, String value) {
            this.type = map.get(sType);
            this.sType = sType;
            this.value = value;
        }
    }
}
