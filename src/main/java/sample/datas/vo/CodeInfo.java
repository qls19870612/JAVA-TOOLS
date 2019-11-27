package sample.datas.vo;

import org.w3c.dom.Node;

import java.util.Comparator;

import sample.config.AppConfig;

/**
 * @描述
 * @创建人 liangsong
 * @创建时间 $date$
 */
public class CodeInfo {

    public static final CodeInfo[] EMPTY_CODE_INFO = new CodeInfo[0];
    public static final Comparator<? super CodeInfo> COMPARATOR = (Comparator<CodeInfo>) (o1, o2) -> o1.index - o2.index;
    public final String codePath;
    public final String className;
    public final String xlsPath;
    public final String extClassName;
    public final String packageName;
    public final int index;

    public CodeInfo(Node element, int index) {
        this.index = index;
        String path = AppConfig.getNodeValue(element, "codePath");
        if (path.startsWith("\\")) {
            path = path.substring(1);
        }
        path = path.replaceAll("\\.", "\\\\");
        codePath = path;
        className = AppConfig.getNodeValue(element, "className");
        xlsPath = AppConfig.getNodeValue(element, "xlsPath");
        extClassName = AppConfig.getNodeValue(element, "extClassName");
        packageName = codePath.replaceAll("\\\\", ".");
    }
}
