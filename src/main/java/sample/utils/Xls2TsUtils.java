package sample.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Pattern;

import game.initializer.io.loader.FileLoader;
import game.initializer.io.loader.FileLoaderOS;
import sample.datas.vo.XlsInfo;
import sample.file.FileOperator;
import sample.fxml.controllers.XlsController;
import sample.fxml.controllers.ts.JavaAndTsDialectProvider;
import sample.fxml.controllers.ts.TsClassTemplate;
import sample.fxml.controllers.ts.TsClassTemplate.FieldInfo;

import static sample.Controller.log;
import static sample.utils.CodeCreateUtils.getEncodePath;
import static sample.utils.Xls2TxtUtils.getCellValue;
import static sample.utils.Xls2TxtUtils.getSheetNum;
import static sample.utils.Xls2TxtUtils.getStringFromCellValue;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/10/26 10:16
 */
public class Xls2TsUtils {
    private static final Logger logger = LoggerFactory.getLogger(Xls2TsUtils.class);

    public static VelocityEngine velocityEngine = null;
    private static JavaAndTsDialectProvider dialectProvider;

    public static void main(String[] args) {
        XlsController.tsPath = "D:\\SVN\\client\\client\\SummonWorld\\assets\\resources\\config\\dataXML";
        File file = new File("D:\\SVN\\server\\new_card_client\\configxml\\dataXML\\towerShopItem.xls");
        XlsController.xlsPath = file.getParent();
        XlsInfo xlsInfo = new XlsInfo(file);
        createTs(xlsInfo);
    }

    public static boolean createTs(XlsInfo item) {


        try {
            if (velocityEngine == null) {
                Properties properties = new Properties();
                //设置velocity资源加载方式为class
                properties.setProperty("resource.loader", "class");
                //设置velocity资源加载方式为file时的处理类
                properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

                velocityEngine = new VelocityEngine();
                velocityEngine.init(properties);
                dialectProvider = new JavaAndTsDialectProvider();
            }
            File file = item.file;
            FileLoader fileLoader = FileLoaderOS.of(file.getPath().replace(file.getName(), ""));

            String fileName = getEncodePath(file.getName());
            byte[] fileContent = fileLoader.fileToBytes(fileName, true);

            InputStream is = new ByteArrayInputStream(fileContent);
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            int sheetNum = getSheetNum(workbook);
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                createTsFile(file, sheetNum, sheet);
            }
            item.updateLuaCreateTime();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("createTs getMessage:{}", e.getMessage());
            log("警告", "配置文件解析失败:" + e.getMessage());
        }
        return false;
    }

    private static void createTsFile(File file, int sheetNum, Sheet sheet) {
        String sheetName = sheet.getSheetName();
        if (sheetName.startsWith("Sheet") || Pattern.compile("[\u4e00-\u9fa5]").matcher(sheetName).find()) {
            return;
        }
        String filePath = file.getParent();
        //        String item = shellContentToBuff(sheet, filePath, file.getName());
        TsClassTemplate tsClassTemplate = createTemplateInfo(sheetName, sheet, filePath, file.getName());
        if (tsClassTemplate == null) {
            logger.error("createShellFile 不能读取表头结构 getName:{}", file.getName());
            return;
        }
        if (tsClassTemplate.isKeyValueFormat()) {
            createKvJson(tsClassTemplate, file);
            return;
        }

        VelocityContext context = new VelocityContext();
        context.put("ClassName", tsClassTemplate.ClassName);
        context.put("fileName", tsClassTemplate.fileName);
        context.put("list", tsClassTemplate.list);
        context.put("subClasses", tsClassTemplate.subClasses);
        StringWriter readWriter = new StringWriter();
        try {

            velocityEngine.mergeTemplate("config/tsTemplate.vm", "UTF-8", context, readWriter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        String baseShortPath = file.getParentFile().getAbsolutePath().replace(XlsController.xlsPath, "");
        String tsPath = baseShortPath + "/ts/" + sheetName + ".ts";
        FileOperator.writeFile(new File(XlsController.tsPath + tsPath), readWriter.toString());
        String pretty = JSON.toJSONString(tsClassTemplate.datas, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        String jsonPath = baseShortPath + "/json/" + sheetName + ".json";
        FileOperator.writeFile(new File(XlsController.tsPath + jsonPath), pretty);


    }

    /**
     * 只有key,value字符的，只创建json
     * @param tsClassTemplate
     * @param file
     */
    private static void createKvJson(TsClassTemplate tsClassTemplate, File file) {
        JSONObject kvJson = new JSONObject();
        for (Object o : tsClassTemplate.datas.getJSONArray(tsClassTemplate.ClassName)) {
            JSONObject object = (JSONObject) o;
            kvJson.put(object.getString("key"), object.get("value"));
        }
        String pretty = JSON.toJSONString(kvJson, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        String baseShortPath = file.getParentFile().getAbsolutePath().replace(XlsController.xlsPath, "");
        String jsonPath = baseShortPath + "/json/" + tsClassTemplate.ClassName + ".json";
        FileOperator.writeFile(new File(XlsController.tsPath + jsonPath), pretty);
    }

    private static TsClassTemplate createTemplateInfo(String sheetName, Sheet sheet, String filePath, String fileName) {
        int rowCount = sheet.getLastRowNum() + 1;
        TsClassTemplate template = new TsClassTemplate();

        template.ClassName = sheetName;
        template.fileName = fileName;
        Row filedNameRow = sheet.getRow(0);
        Row typeRow = sheet.getRow(1);
        Row commentRow = sheet.getRow(2);
        if (filedNameRow == null) {
            logger.error("createTemplateInfo 第一行必需为字段名");
            return null;
        }
        setupFiledInfo(template, filedNameRow, typeRow, commentRow);
        JSONObject jsonObject = new JSONObject();
        //        JsonArray rows = new JsonArray();
        JSONArray rows = new JSONArray();
        jsonObject.put(sheetName, rows);
        for (int i = 3; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                logger.debug("空行,fileName:{},filePath:{}\\{} rowCount:{} i:{}", fileName, filePath, rowCount, i);
                continue;
            }
            JSONObject rowObj = new JSONObject();

            for (int j = 0; j < template.getColMax(); j++) {
                FieldInfo fieldInfo = template.getFiledInfo(j);
                if (fieldInfo == null) {
                    continue;
                }
                Cell cell = row.getCell(j);
                Object value = getCellValue(cell);
                if (fieldInfo.isArray()) {
                    //处理数组类型字段（数组内又是一个实体类)
                    Object o = rowObj.get(fieldInfo.getArrayFieldName());
                    JSONArray arr;
                    if (o instanceof JSONArray) {
                        arr = (JSONArray) o;
                    } else {
                        arr = new JSONArray();
                        rowObj.put(fieldInfo.getArrayFieldName(), arr);
                    }
                    while (arr.size() <= fieldInfo.getArrayIndex()) {
                        arr.add(new JSONObject());
                    }
                    JSONObject object = arr.getJSONObject(fieldInfo.getArrayIndex());
                    value = convertValue(fieldInfo, value);
                    object.put(fieldInfo.getSubFieldName(), value);
                    //                    logger.debug("createTemplateInfo getArrayIndex:{},size:{},getSubFieldName:{},value:{}", fieldInfo.getArrayIndex(),arr.size(),fieldInfo.getSubFieldName(),value);
                } else {

                    value = convertValue(fieldInfo, value);
                    rowObj.put(fieldInfo.name, value);
                }

            }
            rows.add(rowObj);
        }
        template.datas = jsonObject;
        return template;
    }

    private static Object convertValue(FieldInfo fieldInfo, Object value) {
        if (value == null) {
            if (fieldInfo.isNumber()) {
                value = 0;
            }
        } else if (value instanceof String) {

            if (fieldInfo.isNumber()) {

                String value1 = (String) value;

                if (StringUtils.isEmpty(value1)) {
                    value = 0;
                } else {
                    value1 = value1.replaceAll(" ", "");
                    if (StringUtils.isEmpty(value1)) {
                        value = 0;
                    } else {
                        value = Double.valueOf(value1);
                    }
                }
            }
        }
        return value;
    }

    private static void setupFiledInfo(TsClassTemplate template, Row fileds, Row types, Row comments) {
        int colMax = fileds.getLastCellNum();
        template.setColMax(colMax);
        for (int col = 0; col < colMax; col++) {

            Cell cell;

            cell = fileds.getCell(col);
            if (cell == null) {
                continue;
            }
            String filedCol = getStringFromCellValue(cell);
            if (sample.utils.StringUtils.isEmpty(filedCol)) {
                continue;
            }
            String typeCol = getType(types, col);
            cell = comments.getCell(col);
            String commentCol = getStringFromCellValue(cell);

            //            System.out.println("表头信息:"+filedCol+","+typeCol+","+commentCol);
            template.addFiled(filedCol, typeCol, commentCol, col);
        }
        template.toFiledListMap();
    }

    private static String getType(Row types, int i) {
        if (types == null) {
            return "any";
        }
        Cell cell = types.getCell(i);
        if (cell == null) {
            return "any";
        }
        String typeCol = getStringFromCellValue(cell);
        if (sample.utils.StringUtils.isEmpty(typeCol)) {
            //表里没有类型，忽略，不生成字段
            return "any";
        }

        return dialectProvider.getTsClass(typeCol);
    }


}
