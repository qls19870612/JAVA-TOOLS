package sample.datas.vo.raw;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @描述
 * @创建人 liangsong
 * @创建时间 2018/ 07/2018/7/9/009 10:22
 */
public class RawJavaFileInfo {
    public String fileContent;
    public File file;

    public RawJavaFileInfo(String fileNmae)
    {
        this.fileName = fileNmae;
    }
    public String fileName;
    public Set<String> imports = new HashSet<>();
    public HashMap<String,RawFieldInfo> fieldInfos = new HashMap<String, RawFieldInfo>();

}
