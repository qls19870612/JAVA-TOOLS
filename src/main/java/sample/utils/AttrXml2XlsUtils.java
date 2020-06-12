package sample.utils;

import com.google.common.collect.Maps;
import com.google.inject.internal.cglib.core.$LocalVariablesSorter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import game.collection.IntValueIntHashMap;
import game.collection.IntValueIntHashMap.Entry;
import sample.config.AppConfig;
import sample.file.FileOperator;
import sample.file.FileOperator.Filter;
import sample.fxml.componet.AlertBox;

import static sample.fxml.controllers.XlsController.attrXml2XlsPath;
import static sample.fxml.controllers.XlsController.attrXmlPath;
import static sample.fxml.controllers.XlsController.xlsPath;
import static sample.fxml.controllers.XlsController.xml2XlsPath;
import static sample.utils.Xml2XlsUtils.writeToXls;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/05/26 9:37
 */
public class AttrXml2XlsUtils {
    private static Map<String, Integer> map = Maps.newHashMap();

    static {
        int CELL_TYPE_NUMERIC = 0;
        int CELL_TYPE_STRING = 1;
        int CELL_TYPE_FORMULA = 2;
        int CELL_TYPE_BLANK = 3;
        int CELL_TYPE_BOOLEAN = 4;
        int CELL_TYPE_ERROR = 5;
        map.put("String", CELL_TYPE_STRING);
        map.put("int", CELL_TYPE_NUMERIC);
        //        map.put("String",CELL_TYPE_STRING);
        //        map.put("String",CELL_TYPE_STRING);

    }

    public static final int HEAD_ROW = 3;
    private static final Logger logger = LoggerFactory.getLogger(AttrXml2XlsUtils.class);

    public static void convert(String attrXmlPath) {
        File root = new File(attrXmlPath);
        ArrayList<File> files = FileOperator.getAllFiles(root, new Filter<File>() {
            @Override
            public boolean accept(File entry) {
                if (!entry.getName().endsWith(".xml")) {
                    return false;
                }

                return true;
            }
        });
        for (File file : files) {
            String content = FileOperator.readFiles(file);
            try {
                convert(file, content);
            } catch (Exception e) {
                logger.debug("convert getAbsolutePath:{}", file.getAbsolutePath());
                e.printStackTrace();
                String message = e.getMessage();
                if (!org.apache.commons.lang.StringUtils.isEmpty(message) && message.startsWith("前言中不允许有内容")) {
                    String s = null;
                    try {
                        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
                        bytes = Arrays.copyOfRange(bytes, 3, bytes.length);
                        s = new String(bytes, StandardCharsets.UTF_8);
                        convert(file, s);
                    } catch (Exception ex) {
                        logger.error("convert getMessage:{}", message);
                        logger.error("convert getAbsolutePath:{}", file.getAbsolutePath());
                    }
                }
            }
        }


        AlertBox.showAlert("全部转换完成");
    }

    private static void convert(File file, String content) throws IOException, SAXException {


        InputSource inputSource = new InputSource(new StringReader(content));
        Document document = AppConfig.db.parse(inputSource);
        NodeList childNodes = document.getChildNodes();

        HashMap<String, Integer> attrColMap = new HashMap<>();
        ArrayList<CellHeaderInfo> headerInfos = new ArrayList<>();

        AtomicInteger currCol = new AtomicInteger(0);
        Node item = findFirstElement(childNodes);
        if (item == null) {
            return;
        }
        NodeList itemChildNodes = item.getChildNodes();
        int childNodesLength = itemChildNodes.getLength();

        HSSFWorkbook workbook = new HSSFWorkbook();
        String shellName = file.getName().replace(".xml", "");
        shellName = StringUtils.toUpLowerString(shellName, true);
        HSSFSheet sheet = workbook.createSheet(shellName);
        int rowCount = HEAD_ROW - 1;
        for (int i = 0; i < childNodesLength; i++) {
            Node attrItem = itemChildNodes.item(i);
            if (attrItem.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            rowCount++;
            HSSFRow row = sheet.createRow(rowCount);
            addCol(attrColMap, headerInfos, currCol, sheet, row, attrItem, -1);
            ////////////////////属性列表子项/////////////////////
            NodeList subNodeList = attrItem.getChildNodes();
            int length = subNodeList.getLength();
            int subIndex = 0;
            for (int j = 0; j < length; j++) {
                Node subItem = subNodeList.item(j);
                if (subItem.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                addCol(attrColMap, headerInfos, currCol, sheet, row, subItem, subIndex++);
            }


        }
        HSSFRow row0 = sheet.createRow(0);
        HSSFRow row1 = sheet.createRow(1);
        HSSFRow row2 = sheet.createRow(2);
        for (CellHeaderInfo headerInfo : headerInfos) {
            HSSFCell cell0 = row0.createCell(headerInfo.col);
            HSSFCell cell1 = row1.createCell(headerInfo.col);
            HSSFCell cell2 = row2.createCell(headerInfo.col);
            cell0.setCellType(Cell.CELL_TYPE_STRING);
            cell1.setCellType(Cell.CELL_TYPE_STRING);
            cell2.setCellType(Cell.CELL_TYPE_STRING);
            cell0.setCellValue(headerInfo.filedName);
            cell1.setCellValue(headerInfo.type);
            cell2.setCellValue("");

        }
        String shortPath = file.getAbsolutePath().replace(attrXmlPath, "");
        File targetXls = new File(attrXml2XlsPath + "/" + shortPath.replace(".xml", ".xls"));
        writeToXls(targetXls, workbook);

    }

    private static void addCol(HashMap<String, Integer> attrColMap, ArrayList<CellHeaderInfo> headerInfos, AtomicInteger currCol, HSSFSheet sheet,
            HSSFRow row, Node attrItem, int subIndex) {
        NamedNodeMap attributes = attrItem.getAttributes();

        int attrLen = attributes.getLength();
        for (int j = 0; j < attrLen; j++) {
            Node attrNode = attributes.item(j);
            String nodeName;
            if (subIndex < 0) {
                nodeName = attrNode.getNodeName();
            } else {
                nodeName = attrItem.getNodeName() + "_" + subIndex + "_" + attrNode.getNodeName();
            }
            String nodeValue = attrNode.getNodeValue();
            CellHeaderInfo cellHeaderInfo;
            Integer col = attrColMap.get(nodeName);
            if (col == null) {
                boolean isNumber = StringUtils.strIsNumber(nodeValue);//不是数字，就是字符串
                String type = isNumber ? "int" : "String";
                String finnalType = type;
                if (subIndex >= 0) {
                    String filedClassName = attrItem.getNodeName();
                    filedClassName = StringUtils.toUpLowerString(filedClassName,true);
                    finnalType += ";" + filedClassName;
                }
                cellHeaderInfo = new CellHeaderInfo(currCol.get(), nodeName, finnalType, map.get(type));
                headerInfos.add(cellHeaderInfo);
                col = currCol.get();
                attrColMap.put(nodeName, currCol.getAndIncrement());
            } else {
                cellHeaderInfo = headerInfos.get(col);
                if (cellHeaderInfo.type.startsWith("int")) {
                    boolean isNumber = StringUtils.strIsNumber(nodeValue);//不是数字，就是字符串
                    if (!isNumber) {
                        int index = cellHeaderInfo.type.indexOf(";");
                        if (index > 0) {
                            cellHeaderInfo.type = "String" + cellHeaderInfo.type.substring(index);
                        } else {
                            cellHeaderInfo.type = "String";
                        }
                        cellHeaderInfo.cellType = map.get(cellHeaderInfo.type);
                    }
                }

            }

            if (!"".equals(nodeValue)) {
//                logger.debug("addCol row:{} col:{},nodeValue:{},nodeName:{}", row.getRowNum(), col, nodeValue, nodeName);
                HSSFCell cell = row.createCell(col);
                cell.setCellType(cellHeaderInfo.cellType);

                ExcelUtils.writeCell(cell, nodeValue);
            }

        }
    }

    private static Node findFirstElement(NodeList childNodes) {

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                return item;
            }
        }
        return null;
    }

    public static class CellHeaderInfo {
        private final int col;
        private final String filedName;
        private String type;
        private int cellType;

        public CellHeaderInfo(int col, String filedName, String type, int cellType) {
            this.col = col;
            this.filedName = filedName;
            this.type = type;
            this.cellType = cellType;

        }
    }
}
