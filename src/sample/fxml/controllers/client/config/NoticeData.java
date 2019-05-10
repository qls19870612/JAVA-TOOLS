package sample.fxml.controllers.client.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sample.utils.Empty;
import sample.utils.StringUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 21:08
 */
public class NoticeData {
    private static final Pattern argsReg = Pattern.compile("\\{(.?)}");
    public final int moduleId;
    public final int msgId;
    public final String msg;
    public final String[] argTypes;
    public final int key;

    public NoticeData(Map<String, String> config) {
        moduleId = Integer.parseInt(config.get("moduleId"));
        msgId = Integer.parseInt(config.get("msgId"));
        String fixMsg = config.get("fixMsg");
        if (StringUtils.isEmpty(fixMsg)) {
            msg = config.get("serverMsg");
        } else {
            msg = fixMsg;
        }
        Matcher matcher = argsReg.matcher(msg);
        ArrayList<String> argList = new ArrayList<>(4);
        while (matcher.find()) {
            String group = matcher.group(1);
            argList.add(group);
        }
        argTypes = argList.toArray(Empty.STRING_ARRAY);
        key = getKey(moduleId, msgId);

    }

    @Override
    public String toString() {
        return "NoticeData{" + "moduleId=" + moduleId + ", msgId=" + msgId + ", msg='" + msg + '\'' + ", argTypes=" + Arrays.toString(argTypes) +
                ", key=" + key + '}';
    }

    public static final int getKey(int moduleId, int msgId) {
        return moduleId * 1000 + msgId;
    }
}
