package sample.datas.vo;

import java.io.File;
import java.util.Comparator;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/10/26 10:01
 */
public class XlsInfo {
    public static final XlsInfo[] EMPTY = new XlsInfo[0];
    public static final Comparator<? super XlsInfo> NAME_SORT = (Comparator<XlsInfo>) XlsInfo::compareTo;
    public static final Comparator<? super XlsInfo> MODIFY_TIME_SORT = (Comparator<XlsInfo>) XlsInfo::compareToModifyTime;

    private static int compareToModifyTime(XlsInfo xlsInfo, XlsInfo xlsInfo1) {
        long l = xlsInfo.lastModified() - xlsInfo1.lastModified();
        if (l > 0) {
            return 1;
        } else if (l == 0) {
            return 0;
        }
        return -1;
    }

    private int compareTo(XlsInfo o2) {
        return this.url.compareTo(o2.url);
    }

    public final File file;
    public final String fileName;
    private long modifiedTime;
    public final String url;

    public boolean isNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(boolean needUpdate) {
        this.needUpdate = needUpdate;
    }

    private boolean needUpdate = true;

    public XlsInfo(File file) {
        this.file = file;
        modifiedTime = file.lastModified();
        fileName = file.getName();
        url = file.getAbsolutePath();
    }

    public long lastModified() {
        return modifiedTime;
    }

    public void updateModifyTime(long l) {
        modifiedTime = l;
        setNeedUpdate(true);
    }

    public void updateLuaCreateTime() {
        setNeedUpdate(false);
    }
}
