package app.game.service.notice;

import org.apache.commons.lang.StringUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/08/17 19:50
 */
public class MyRowInfo {
    public static int msgIdIndex;
    public static int moduleIdIndex;
    public static int serverMsgIndex;
    public static int idIndex;
    public final String[] arrayList;
    private int key;

    public MyRowInfo(String[] arrayList) {
        this.arrayList = arrayList;
    }

    public int getModuleId() {
        if (StringUtils.isEmpty(arrayList[moduleIdIndex])) {
            return 0;
        }
        return Integer.parseInt(arrayList[moduleIdIndex]);
    }

    public int getMsgId() {
        if (StringUtils.isEmpty(arrayList[msgIdIndex])) {
            return 0;
        }
        return Integer.parseInt(arrayList[msgIdIndex]);
    }

    public boolean getCellIsEmpty(int cellIndex) {
        return StringUtils.isEmpty(arrayList[cellIndex]);
    }

    public void setMsgId(int value) {
        arrayList[msgIdIndex] = String.valueOf(value);
    }

    public void setId(int value) {
        arrayList[idIndex] = String.valueOf(value);
    }

    public void setModuleId(int moduleId) {
        arrayList[moduleIdIndex] = String.valueOf(moduleId);
    }

    public void setServerMsg(String value) {
        arrayList[serverMsgIndex] = value;
    }

    public int getKey() {
        return key;
    }


    @Override
    public String toString() {
        return getModuleId() + "-" + getMsgId();
    }

    public static int createKey(int moduleId, int msgId) {
        return (moduleId << 16) | msgId;
    }

    public void validKey() {
        key = createKey(getModuleId(), getMsgId());
    }
}
