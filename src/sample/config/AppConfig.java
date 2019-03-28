package sample.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import sample.datas.vo.CodeInfo;
import sample.file.FileOperator;
import sample.utils.ClassParserUtils;

import static sample.datas.vo.CodeInfo.EMPTY_CODE_INFO;

public class AppConfig {
    private static DocumentBuilderFactory dbFactory = null;
    private static DocumentBuilder db = null;
    private static Document document = null;
    private static HashMap<String, String> dateTypeMap;
    public static String fieldTemplate;
    public static String classTemplate;
    public static String baseCodePath;
    /**
     * key:CodeInfo.className
     * value:CodeInfo
     */
    public static HashMap<String, CodeInfo> codeInfos;
    public static String defaultValuePackageName;

    private static final Pattern functionPatter = Pattern.compile(" public\\s+static\\s+([\\w\\[\\]]+)\\s+(\\w+)\\s*\\(");

    private static long lastConfigModifyTime = 0;
    public static String xlsPath;
    public static String genMsgPath;
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    public static String luaPath;
    public static int selectTab = 0;
    public static boolean readLuaUpdateCfg;
    public static int gmPort;
    public static String gmIp;

    public static boolean parseClassConfig() {
        boolean isUpdate = false;
        File configFile = new File("config/classConfig.xml");
        if (!configFile.exists()) {
            return false;
        }
        if (configFile.lastModified() == lastConfigModifyTime) {
            return false;
        }
        lastConfigModifyTime = configFile.lastModified();
        System.out.println("修改:" + lastConfigModifyTime);
        try {
            String content = FileOperator.readFiles(configFile);
            InputSource inputSource = new InputSource(new StringReader(content));
            document = db.parse(inputSource);

            baseCodePath = document.getElementsByTagName("baseCodePath").item(0).getChildNodes().item(0).getNodeValue();
            NodeList configItem = document.getElementsByTagName("configitem");
            codeInfos = getCodeInfos(configItem);
            isUpdate = true;
        } catch (Exception e) {
            e.printStackTrace();
            document = null;
        }
        return isUpdate;
    }

    public static void parserTemplate() {
        File configFile = new File("config/config.xml");
        if (!configFile.exists()) {
            return;
        }

        try {
            String content = FileOperator.readFiles(configFile);
            InputSource inputSource = new InputSource(new StringReader(content));
            document = db.parse(inputSource);
            Element templates = (Element) document.getElementsByTagName("templates").item(0);

            fieldTemplate = getCDATA(templates, "field");
            classTemplate = getCDATA(templates, "class");
            xlsPath = document.getElementsByTagName("xlsPath").item(0).getChildNodes().item(0).getNodeValue();
            luaPath = document.getElementsByTagName("luaPath").item(0).getChildNodes().item(0).getNodeValue();
            genMsgPath = document.getElementsByTagName("genMsgPath").item(0).getChildNodes().item(0).getNodeValue();
            gmIp = document.getElementsByTagName("gm_ip").item(0).getChildNodes().item(0).getNodeValue();
            gmPort = Integer.parseInt(document.getElementsByTagName("gm_port").item(0).getChildNodes().item(0).getNodeValue());
            readLuaUpdateCfg =
                    document.getElementsByTagName("readLuaUpdateCfg").item(0).getChildNodes().item(0).getNodeValue().equals("1") ? true : false;
            selectTab = Integer.parseInt(document.getElementsByTagName("selectTab").item(0).getChildNodes().item(0).getNodeValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initFactory() {

        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setIgnoringComments(true);
            dbFactory.setIgnoringElementContentWhitespace(true);
            db = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    private static HashMap<String, CodeInfo> getCodeInfos(NodeList chidren) {
        HashMap<String, CodeInfo> ret = new HashMap<>();
        //        NodeList chidren = configItem.getChildNodes();
        int index = 0;
        for (int i = 0; i < chidren.getLength(); i++) {
            Node node = chidren.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                CodeInfo codeInfo = new CodeInfo(node, index++);
                ret.put(codeInfo.className, codeInfo);
            }
        }
        return ret;
    }

    public static String getNodeValue(Node node, String nodeName) {
        NodeList nodeList = ((Element) node).getElementsByTagName(nodeName);
        if (nodeList == null) {
            return null;
        }
        node = nodeList.item(0);
        if (node == null) {
            return null;
        }
        return node.getChildNodes().item(0).getNodeValue();
    }

    private static String getCDATA(Element templates, String field) {
        Node node = templates.getElementsByTagName(field).item(0);
        return node.getChildNodes().item(0).getNodeValue();
    }

    public static void parseDataTypeMap() {

        String defaultJavaContent = FileOperator.readFiles(new File("config/Defaults.java"));
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
        return dateTypeMap.get(type.toLowerCase());
    }


    public static CodeInfo[] getCodeInfos() {
        if (codeInfos != null) {
            CodeInfo[] codeInfos = AppConfig.codeInfos.values().toArray(EMPTY_CODE_INFO);
            Arrays.sort(codeInfos, CodeInfo.COMPARATOR);
            return codeInfos;
        }
        return null;
    }

    public static CodeInfo getItemItemInfo(String className) {
        if (codeInfos != null) {
            return codeInfos.get(className);
        }
        return null;
    }
}
