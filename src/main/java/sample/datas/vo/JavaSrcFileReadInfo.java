package sample.datas.vo;

/**
 * @描述
 * @创建人 liangsong
 * @创建时间 2018/7/9/009 15:38
 */
public class JavaSrcFileReadInfo {
    public String path;
    public long lastModifyTime;
    public String parseType;

    public String className;
    public String fileName;
    //带类名
    public String parseTypePackageName;
    //带类名
    public String packageName;
    public void reset()
    {
        lastModifyTime = 0;
        parseType = null;
        className = null;
        fileName = null;
        parseTypePackageName = null;
        packageName = null;
    }
}
