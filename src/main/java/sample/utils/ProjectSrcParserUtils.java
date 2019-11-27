package sample.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sample.config.AppConfig;
import sample.datas.vo.JavaSrcFileReadInfo;
import sample.file.FileOperator;

import static sample.Controller.log;
import static sample.utils.ClassParserUtils.getPackageNoClassName;
import static sample.utils.CodeCreateUtils.importPattern;

/**
 * @描述
 * @创建人 liangsong
 * @创建时间 2018/7/10/010 22:28
 */
public class ProjectSrcParserUtils {
    //key enumName.toLowerCase(),value:packageMame.className.enumName
    public static HashMap<String, String> enumHashMap = new HashMap<String, String>();
    //key:文件路径 ,value:文件修改时间，缓存，如果文件读取过就不再读了
    private static HashMap<String, JavaSrcFileReadInfo> readHistoryHashmap = new HashMap<>();
    private static Pattern parserClassPattern = Pattern.compile("public\\s+class\\s+(\\w+)[\\s\\S]+implements\\s+Parser\\s*\\{");

    public static HashMap<String, JavaSrcFileReadInfo> findParserClassMap() {

        HashMap<String, JavaSrcFileReadInfo> ret = new HashMap<>();
        File projectSrcFolder = new File(AppConfig.baseCodePath);
        ArrayList<File> javaFiles = FileOperator.getAllFiles(projectSrcFolder, ".java");
        for (File javaFile : javaFiles) {
            JavaSrcFileReadInfo readInfo = null;
            if (readHistoryHashmap.containsKey(javaFile.getPath())) {
                readInfo = readHistoryHashmap.get(javaFile.getPath());
                if (javaFile.lastModified() == readInfo.lastModifyTime) {
                    //已经读取过文件，且文件没有发生过修改，则直接使用原来解析的
                    if (readInfo.className != null) {
                        ret.put(readInfo.className, readInfo);
                    }
                    continue;
                }
                readInfo.reset();
            }
            if (readInfo == null) {
                readInfo = new JavaSrcFileReadInfo();
                readInfo.path = javaFile.getPath();
                readHistoryHashmap.put(javaFile.getPath(), readInfo);
            }
            String fileContent = FileOperator.readFiles(javaFile);
            readInfo.lastModifyTime = javaFile.lastModified();
            ArrayList<String> enums = ClassParserUtils.getEnumNames(fileContent);
            String className = null;
            String packageName = null;
            if (enums.size() > 0) {
                //枚举类型处理
                className = ClassParserUtils.getClassName(fileContent);
                packageName = ClassParserUtils.getPackageName(fileContent);
                for (String anEnum : enums) {
                    String enumFullName = null;
                    if (className != null) {
                        enumFullName = packageName + "." + className + "." + anEnum;
                    } else {
                        enumFullName = packageName + "." + anEnum;
                    }
                    enumHashMap.put(anEnum.toLowerCase(), enumFullName);
                }
            }
            Matcher matcher = parserClassPattern.matcher(fileContent);
            if (matcher.find()) {
                //解析器类处理
                className = matcher.group(1);
                Pattern parserTypePatter = Pattern.compile("public\\s+(\\w+)\\s+parse\\s*\\(");
                matcher = parserTypePatter.matcher(fileContent);
                if (matcher.find()) {
                    readInfo.parseType = matcher.group(1);

                    if (packageName == null) {
                        packageName = ClassParserUtils.getPackageName(fileContent);
                    }
                    readInfo.packageName = packageName + "." + className;
                    String noExtName = FileOperator.getFileNameNoEx(javaFile.getName());
                    readInfo.className = className;
                    readInfo.fileName = noExtName;
                    if (ret.containsKey(noExtName)) {
                        log("警告", "解析类型重名，会导致配置文件自动生成,包导入错误");
                    }
                    ret.put(className, readInfo);
                    JavaSrcFileReadInfo parseType = readInfo;
                    File parserFile = new File(parseType.path);
                    File parserTypeFile = new File(parserFile.getParentFile().getPath() + "/" + parseType.parseType + ".java");
                    if (parserTypeFile.exists()) {
                        parseType.parseTypePackageName = getPackageNoClassName(parseType.packageName) + "." + parseType.parseType;
                    } else {
                        matcher = importPattern.matcher(fileContent);
                        while (matcher.find()) {
                            String[] packageArr = matcher.group(1).split("\\.");
                            if (packageArr[packageArr.length - 1].equals(parseType.parseType)) {
                                parseType.parseTypePackageName = matcher.group(1);
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
}
