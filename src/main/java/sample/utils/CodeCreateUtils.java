package sample.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.initializer.io.loader.FileLoader;
import game.initializer.io.loader.FileLoaderOS;
import sample.config.AppConfig;
import sample.datas.vo.CodeFieldsInfo;
import sample.datas.vo.CodeInfo;
import sample.datas.vo.CreateFieldInfo;
import sample.datas.vo.FieldInfo;
import sample.datas.vo.JavaSrcFileReadInfo;
import sample.datas.vo.raw.RawFieldInfo;
import sample.datas.vo.raw.RawJavaFileInfo;
import sample.datas.vo.raw.RawManaualParam;
import sample.file.FileOperator;

import static sample.Controller.log;
import static sample.utils.ClassParserUtils.getClassNameByFullPackName;
import static sample.utils.ProjectSrcParserUtils.findParserClassMap;

/**
 * @描述
 * @创建人 liangsong
 * @创建时间 $date$
 */
public class CodeCreateUtils {
    private static HashMap<String, String> dateTypeMap;
    private static final Pattern functionPatter = Pattern.compile(" public\\s+static\\s+([\\w\\[\\]]+)\\s+(\\w+)\\s*\\(");
    public static String defaultValuePackageName;

    public static void parseDataTypeMap() {

        String defaultJavaContent = FileOperator.getConfig("config/Defaults.java");

        Matcher matcher = functionPatter.matcher(defaultJavaContent);
        dateTypeMap = new HashMap<>();
        while (matcher.find()) {
            dateTypeMap.put(matcher.group(1).toLowerCase(), matcher.group(2));
        }
        String packageName = ClassParserUtils.getPackageName(defaultJavaContent);

        String className = ClassParserUtils.getClassName(defaultJavaContent);
        defaultValuePackageName = packageName + "." + className;
    }

    public static String getTypeMap(String type) {
        if (dateTypeMap == null) {
            CodeCreateUtils.parseDataTypeMap();
        }
        return dateTypeMap.get(type.toLowerCase());
    }

    public static final ThreadLocal<SimpleDateFormat> dateTimeFormatter = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    //    SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Set<String> exceptParamsHashmap = new HashSet<String>() {
        {
            add("cfgKeys");
            add("desc");
            add("parsers");
        }
    };
    public static final Pattern importPattern = Pattern.compile("import\\s+([\\s\\S]+?);");
    private static final Pattern injectFieldPattern = Pattern.compile("[\\r\\n]+\\s*@InitReq\\((.+)\\)([\\s\\S]+?)\\(\\);");
    public static final Pattern fieldPattern = Pattern.compile("(?:\\S+\\s+)+?(\\S+)\\s*=");
    public static final Pattern paramsPattern = Pattern.compile("([_\\w\\d]+)\\s*=\\s*([^,\\)]+)");

    //解析类型映射 解析类名，解析出来的数据类型
    public static HashMap<String, JavaSrcFileReadInfo> parserClassTypeMap;

    public static void createCode(CodeInfo item) {
        String path = null;
        path = getEncodePath(item.codePath + "\\" + item.className + ".java");
        File codeFile = new File(path);
        parserClassTypeMap = findParserClassMap();
        if (codeFile.exists()) {
            //            ArrayList<FieldInfo> newFieldInfos = getFieldInfo(item);
            CodeFieldsInfo codeFieldsInfo = getCodeFieldInfo(item);
            if (codeFieldsInfo.fieldInfos == null) {
                return;
            }
            RawJavaFileInfo rawJavaFileInfo = parseRawFile(codeFile);
            if (StringUtils.isEmpty(rawJavaFileInfo.fileContent)) {
                createNewJaveFile(item);
            } else {
                modifyRawFile(rawJavaFileInfo, codeFieldsInfo);
            }
            log("修改文件完毕!", path);
        } else {
            createNewJaveFile(item);
            log("创建文件完毕!", path);
        }
    }

    private static void modifyRawFile(RawJavaFileInfo rawJavaFileInfo, CodeFieldsInfo codeFieldsInfo) {
        ArrayList<FieldInfo> newFieldInfos = codeFieldsInfo.fieldInfos;
        HashMap<String, FieldInfo> newFieldHashmap = new HashMap<>();
        for (FieldInfo fieldInfo : newFieldInfos) {
            newFieldHashmap.put(fieldInfo.getFieldName(), fieldInfo);
        }
        Matcher matcher = injectFieldPattern.matcher(rawJavaFileInfo.fileContent);
        StringBuffer importStringBuffer = new StringBuffer();
        StringBuffer fileContentStringBuffer = new StringBuffer();
        boolean hasField = false;
        while (matcher.find()) {
            hasField = true;
            String paramStr = matcher.group(1);
            String fieldStr = matcher.group(2);
            Matcher fieldPatternMatter = fieldPattern.matcher(fieldStr);
            fieldPatternMatter.find();
            String fieldName = fieldPatternMatter.group(1);
            if (newFieldHashmap.containsKey(fieldName)) {
                FieldInfo fieldInfo = newFieldHashmap.remove(fieldName);
                String rawManaualParam = getRawManaualParma(rawJavaFileInfo.fieldInfos.get(fieldName));
                CreateFieldInfo createFieldInfo = filedInfoToCode(fieldInfo, rawManaualParam);
                matcher.appendReplacement(fileContentStringBuffer, "\n\n\t" + createFieldInfo.fieldStr);

                appendParseImports(importStringBuffer, createFieldInfo, rawJavaFileInfo.imports);
                appendDefaultValueImports(importStringBuffer, createFieldInfo.defaultValue, rawJavaFileInfo.imports);
            } else {
                matcher.appendReplacement(fileContentStringBuffer, "");
            }
        }
        //剩余未处理的新字段
        StringBuffer newFieldStringBuffer = new StringBuffer();
        Collection<FieldInfo> remainNewFields = newFieldHashmap.values();
        for (FieldInfo remainNewField : remainNewFields) {
            CreateFieldInfo createFieldInfo = filedInfoToCode(remainNewField, "");
            newFieldStringBuffer.append("\n\n\t" + createFieldInfo.fieldStr);
            appendParseImports(importStringBuffer, createFieldInfo, rawJavaFileInfo.imports);
            appendDefaultValueImports(importStringBuffer, createFieldInfo.defaultValue, rawJavaFileInfo.imports);
        }
        if (!hasField) {
            addToClassHead(rawJavaFileInfo.fileContent, fileContentStringBuffer, newFieldStringBuffer);
        } else {
            fileContentStringBuffer.append(newFieldStringBuffer);
            matcher.appendTail(fileContentStringBuffer);
        }


        String newJavaFileContent = fileContentStringBuffer.toString();
        StringBuffer addImportContentBuffer = new StringBuffer();
        matcher = importPattern.matcher(newJavaFileContent);
        while (matcher.find()) {
            matcher.appendReplacement(addImportContentBuffer, matcher.group());
        }
        if (importStringBuffer.length() > 0) {
            addImportContentBuffer.append("\n");
            addImportContentBuffer.append(importStringBuffer);
        }
        matcher.appendTail(addImportContentBuffer);
        //        System.out.println("最终文件内容:\n" + addImportContentBuffer.toString());
        FileOperator.writeFile(rawJavaFileInfo.file, addImportContentBuffer.toString());

    }

    private static void addToClassHead(String fileContent, StringBuffer fileContentStringBuffer, StringBuffer newFieldStringBuffer) {
        int index = fileContent.indexOf("public class ");
        System.out.println("index:" + index);
        Pattern br = Pattern.compile("([\\r\\n]\\s*)+");
        Matcher matcher = br.matcher(fileContent);
        matcher.find(index);
        matcher.appendReplacement(fileContentStringBuffer, "\n" + newFieldStringBuffer.toString() + "\n\n\t");
        matcher.appendTail(fileContentStringBuffer);
    }


    /**
     * 由原字段的信息，获得原字段手动添加字段的字符串格式
     * @param rawFieldInfo
     * @return
     */
    private static String getRawManaualParma(RawFieldInfo rawFieldInfo) {
        if (rawFieldInfo != null) {
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList<RawManaualParam> manaualParams = rawFieldInfo.manaualParams;
            for (RawManaualParam manaualParam : manaualParams) {
                stringBuffer.append(", ");
                stringBuffer.append(manaualParam.paramName);
                stringBuffer.append(" = ");
                stringBuffer.append(manaualParam.paramValue);
            }
            return stringBuffer.toString();
        }
        return "";
    }


    private static RawJavaFileInfo parseRawFile(File codeFile) {
        String rawFileContent = FileOperator.readFiles(codeFile);
        RawJavaFileInfo rawJavaFileInfo = new RawJavaFileInfo(codeFile.getName());
        rawJavaFileInfo.file = codeFile;
        rawJavaFileInfo.fileContent = rawFileContent;
        parseImports(rawFileContent, rawJavaFileInfo);
        parseFields(rawFileContent, rawJavaFileInfo);
        return rawJavaFileInfo;

    }

    private static void parseFields(String rawFileContent, RawJavaFileInfo rawJavaFileInfo) {

        Matcher matcher = injectFieldPattern.matcher(rawFileContent);
        while (matcher.find()) {
            String paramStr = matcher.group(1);
            String fieldStr = matcher.group(2);
            RawFieldInfo rawFieldInfo = new RawFieldInfo();

            Matcher fieldPatternMatter = fieldPattern.matcher(fieldStr);
            fieldPatternMatter.find();
            String fieldName = fieldPatternMatter.group(1);
            rawFieldInfo.fieldName = fieldName;

            Matcher paramsMather = paramsPattern.matcher(paramStr);
            while (paramsMather.find()) {
                String paramName = paramsMather.group(1);
                //                System.out.println("paramName:"  + paramName + " value:" + paramsMather.group(2) + ">" + paramsMather.group());
                if (!exceptParamsHashmap.contains(paramName)) {
                    String paramValue = paramsMather.group(2);
                    RawManaualParam rawManaualParam = new RawManaualParam();
                    rawManaualParam.paramName = paramName;
                    rawManaualParam.paramValue = paramValue;
                    rawFieldInfo.manaualParams.add(rawManaualParam);
                }
            }
            rawJavaFileInfo.fieldInfos.put(rawFieldInfo.fieldName, rawFieldInfo);
        }
    }

    private static void parseImports(String rawFileContent, RawJavaFileInfo rawJavaFileInfo) {
        Matcher matcher = importPattern.matcher(rawFileContent);
        while (matcher.find()) {
            String importStr = matcher.group(1);

            String importClassName = getClassNameByFullPackName(importStr);
            rawJavaFileInfo.imports.add(importClassName);
        }
    }

    private static void createNewJaveFile(CodeInfo item) {

        CodeFieldsInfo codeFieldsInfo = getCodeFieldInfo(item);
        if (codeFieldsInfo.fieldInfos != null) {
            createClassCode(item, codeFieldsInfo);
        }
    }

    private static CodeFieldsInfo getCodeFieldInfo(CodeInfo item) {

        CodeFieldsInfo ret = new CodeFieldsInfo();

        ArrayList<FieldInfo> parentFieldInfos = new ArrayList<>();
        ret.parentFieldInfos = parentFieldInfos;
        int count = 0;
        CodeInfo parentItem = item;
        String extClassPackageName = null;
        while (parentItem != null && StringUtils.isNotEmpty(parentItem.extClassName)) {
            parentItem = AppConfig.getItemItemInfo(parentItem.extClassName);
            if (parentItem != null) {
                if (extClassPackageName == null) {
                    extClassPackageName = parentItem.packageName;
                }
                ArrayList<FieldInfo> fieldInfos = getFieldInfo(parentItem);
                parentFieldInfos.addAll(fieldInfos);
            }
            count++;
            if (count > 4) {
                break;
            }
        }
        HashMap<String, FieldInfo> parentFieldInfoMap = fieldsArrayToHash(parentFieldInfos);
        ArrayList<FieldInfo> fieldInfos = getFieldInfo(item);
        //删除父类已经有的字段
        int fieldInfosLen = fieldInfos.size();
        for (int i = fieldInfosLen - 1; i >= 0; i--) {
            FieldInfo fieldInfo = fieldInfos.get(i);
            if (parentFieldInfoMap.containsKey(fieldInfo.getFieldName())) {
                fieldInfos.remove(i);
            }
        }
        ret.fieldInfos = fieldInfos;
        ret.extClassName = item.extClassName;
        ret.extClassPackageName = extClassPackageName;
        return ret;
    }

    public static String getEncodePath(String url) {

        try {
            url = URLDecoder.decode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static ArrayList<FieldInfo> getFieldInfo(CodeInfo item) {
        File file = new File(item.xlsPath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        String xlsFolder = file.getParentFile().getPath();
        System.out.println("xlsFolder:" + xlsFolder);
        FileLoader fileLoader = FileLoaderOS.of(xlsFolder);
        byte[] fileContent = new byte[0];
        String fileName = item.xlsPath.replace(xlsFolder + "\\", "");
        System.out.println("fileName:" + fileName);
        fileName = getEncodePath(fileName);
        System.out.println("fileName1:" + fileName);
        fileContent = fileLoader.fileToBytes(fileName, true);

        InputStream is = new ByteArrayInputStream(fileContent);
        Workbook workbook = null;
        ArrayList<FieldInfo> fieldInfos = null;
        try {
            workbook = new HSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(item.sheetIndex);
            int rowNum = sheet.getLastRowNum() + 1;
            if (rowNum < 3) {
                throw new RuntimeException("表： " + item.xlsPath + " 中 sheet:" + sheet.getSheetName() + " 的数据至少3行");
            }
            Row fileds = sheet.getRow(0);
            if (fileds == null) {
                return new ArrayList<>();
            }
            Row types = sheet.getRow(1);
            Row comments = sheet.getRow(2);
            fieldInfos = createFileInfos(fileds, types, comments);
            fieldInfos.sort(FieldInfo.compare);

        } catch (IOException e) {
            e.printStackTrace();
            log("警告", "配置文件解析失败:" + e.getMessage());
        }
        return fieldInfos;
    }

    private static HashMap<String, FieldInfo> fieldsArrayToHash(ArrayList<FieldInfo> fieldInfos) {
        HashMap<String, FieldInfo> hashMap = new HashMap<>();
        for (FieldInfo fieldInfo : fieldInfos) {
            hashMap.put(fieldInfo.getFieldName(), fieldInfo);
        }
        return hashMap;
    }

    private static void createClassCode(CodeInfo item, CodeFieldsInfo codeFieldsInfo) {
        ArrayList<FieldInfo> fieldInfos = codeFieldsInfo.fieldInfos;

        StringBuffer fields = new StringBuffer();
        Set<String> packageNameMap = new HashSet<String>();
        StringBuffer importBuffer = new StringBuffer();
        for (FieldInfo fieldInfo : fieldInfos) {
            CreateFieldInfo createFieldInfo = filedInfoToCode(fieldInfo, "");
            fields.append(createFieldInfo.fieldStr);
            fields.append("\n\n\t");

            appendParseImports(importBuffer, createFieldInfo, packageNameMap);
            appendDefaultValueImports(importBuffer, createFieldInfo.defaultValue, packageNameMap);
        }
        //        String code = StringUtils.replaceKey(AppConfig.classTemplate,"package",)
        CharSequence packageName = item.packageName;
        String code = AppConfig.classTemplate.replace("$package", packageName);
        String classStr = item.className;
        if (StringUtils.isNotEmpty(codeFieldsInfo.extClassName)) {
            classStr = classStr + " extends " + codeFieldsInfo.extClassName;
            appendImport(codeFieldsInfo.extClassPackageName + "." + codeFieldsInfo.extClassName, importBuffer, packageNameMap);
            code = code.replace("$super", "\n\t\tsuper(config);");

        } else {
            code = code.replace("$super", "\n\t\tsuper(config);");
        }
        code = code.replace("$className1", classStr);
        code = code.replace("$className2", item.className);

        code = code.replace("$imports", importBuffer.toString());
        code = code.replace("$fields", fields.toString());
        code = code.replace("$fileName", item.xlsPath);
        Date date = new Date();
        String dateStr = dateTimeFormatter.get().format(date);
        code = code.replace("$date", dateStr);

        File file = new File(item.codePath + "\\" + item.className + ".java");
        if (file.exists() == false) {
            file.getParentFile().mkdirs();
        }
        FileOperator.writeFile(file, code);
    }


    private static void appendDefaultValueImports(StringBuffer importStringBuffer, String value, Set<String> imports) {
        if (imports.contains("*")) {
            return;
        }
        if (imports.contains(value) == false) {
            imports.add(value);
            importStringBuffer.append("import static ");
            importStringBuffer.append(defaultValuePackageName + "." + value);
            importStringBuffer.append(";\n");
        }
    }

    private static void appendParseImports(StringBuffer importBuffer, CreateFieldInfo createFieldInfo, Set<String> parserClassSet) {

        if (createFieldInfo.parserClassName != null) {

            appendImport(createFieldInfo.parseTypePackageName, importBuffer, parserClassSet);
            appendImport(createFieldInfo.parserPackageName, importBuffer, parserClassSet);
            return;
        }
        if (createFieldInfo.enumPackageName != null) {
            appendImport(createFieldInfo.enumPackageName, importBuffer, parserClassSet);
        }

    }

    private static void appendImport(String packageName, StringBuffer importBuffer, Set<String> packageNameSet) {
        if (packageNameSet.contains(packageName) == false) {
            packageNameSet.add(packageName);
            importBuffer.append("import ");
            importBuffer.append(packageName);
            importBuffer.append(";\n");
        }

    }

    private static ArrayList<FieldInfo> createFileInfos(Row fileds, Row types, Row comments) {
        ArrayList<FieldInfo> ret = new ArrayList<FieldInfo>();
        //        Preconditions.checkArgument(fileds.getLastCellNum() == types.getLastCellNum() && fileds.getLastCellNum() == comments.getLastCellNum(),
        //                "表头字段，类型，注释必需一致");
        int cellNum = fileds.getLastCellNum();
        for (int i = 0; i < cellNum; i++) {
            Cell cell = types.getCell(i);
            if (cell == null) {
                continue;
            }
            String typeCol = cell.getStringCellValue();
            if (StringUtils.isEmpty(typeCol)) {
                //表里没有类型，忽略，不生成字段
                continue;
            }
            cell = fileds.getCell(i);
            if (cell == null) {
                continue;
            }
            String filedCol = cell.getStringCellValue();
            if (StringUtils.isEmpty(filedCol)) {
                continue;
            }
            filedCol = filedCol.trim();
            if (StringUtils.isEmpty(filedCol)) {
                continue;
            }
            if ("id".equals(filedCol) || "name".equals(filedCol)) {
                //id和名字在基类里
                continue;
            }
            cell = comments.getCell(i);
            String commentCol;
            if (cell == null) {
                commentCol = "";
            } else {
                commentCol = cell.getStringCellValue();
            }
            //            System.out.println("表头信息:"+filedCol+","+typeCol+","+commentCol);
            FieldInfo fieldInfo = new FieldInfo(filedCol, typeCol, commentCol, i);
            //            fieldInfo.setInfo(filedCol,typeCol,commentCol);
            ret.add(fieldInfo);
        }
        mergerFiels(ret);
        return ret;
    }

    private static void mergerFiels(ArrayList<FieldInfo> fieldInfos) {
        HashMap<String, FieldInfo> sampleParsersHashmap = new HashMap<>();
        HashMap<String, FieldInfo> sampleSuffixHashmap = new HashMap<>();
        int len = fieldInfos.size();
        for (int i = 0; i < len; i++) {
            FieldInfo fieldInfo = fieldInfos.get(i);
            boolean isMergerFlag = false;
            if (!StringUtils.isEmpty(fieldInfo.getNameSuffix())) {
                FieldInfo sameTypeCol = sampleSuffixHashmap.get(fieldInfo.getTypeCol());
                if (sameTypeCol != null) {
                    sameTypeCol.merger(fieldInfo, FieldMergerEnum.SUFFIX);
                    isMergerFlag = true;
                } else {
                    //有前辍的第一个存起来
                    sampleSuffixHashmap.put(fieldInfo.getTypeCol(), fieldInfo);
                }
            } else if (parserClassTypeMap.get(fieldInfo.getFieldType()) != null) {
                //是解析类
                FieldInfo sameParsers = sampleParsersHashmap.get(fieldInfo.getFieldType());
                if (sameParsers != null) {
                    sameParsers.merger(fieldInfo, FieldMergerEnum.PARSER);
                    isMergerFlag = true;
                } else {
                    sampleParsersHashmap.put(fieldInfo.getFieldType(), fieldInfo);
                }
            }
            if (isMergerFlag) {
                fieldInfos.remove(i);
                i--;
                len--;
            }
        }
    }

    private static CreateFieldInfo filedInfoToCode(FieldInfo fieldInfo, String manaualParmaStr) {
        String defaultValue = getTypeMap(fieldInfo.getFieldType());
        CreateFieldInfo ret = new CreateFieldInfo();
        String classType = fieldInfo.getFieldType();
        if (defaultValue == null) {
            defaultValue = "defaultObj";
            JavaSrcFileReadInfo classTypeInfo = parserClassTypeMap.get(classType);
            if (classTypeInfo != null) {

                classType = classTypeInfo.parseType;
                ret.parserPackageName = classTypeInfo.packageName;
                ret.parseTypePackageName = classTypeInfo.parseTypePackageName;
                ;
                ret.parserClassName = classTypeInfo.className;
                manaualParmaStr = ", parsers = " + fieldInfo.getFieldType() + ".class" + manaualParmaStr;
            } else {
                classType = fieldInfo.getFieldType();
                if (ProjectSrcParserUtils.enumHashMap.containsKey(fieldInfo.getFieldType().toLowerCase())) {
                    ret.enumPackageName = ProjectSrcParserUtils.enumHashMap.get(fieldInfo.getFieldType().toLowerCase());
                }
            }

        }
        String fieldName = fieldInfo.getFieldName();
        ret.defaultValue = defaultValue;
        String comments = fieldInfo.getComments();
        String fieldStr =
                StringUtils.replace(AppConfig.fieldTemplate, fieldInfo.getCfgKeys(), comments, defaultValue, fieldName, manaualParmaStr, classType);
        ret.fieldStr = fieldStr;
        //        System.out.println("filedInfoToCode:" + fieldStr);
        return ret;
    }


    private static String getFirstCharLower(String classType) {
        return Character.toLowerCase(classType.charAt(0)) + classType.substring(1);
    }


}
