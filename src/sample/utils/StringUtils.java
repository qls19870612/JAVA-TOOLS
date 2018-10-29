package sample.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @描述
 * @创建人 liangsong
 * @创建时间 2018/ 07/2018/7/7/007 18:31
 */
public class StringUtils {
    private static Pattern pattern = Pattern.compile("\\$(\\d+)");
    private static Pattern worldPattern = Pattern.compile("[a-zA-Z0-9]+");
    private static Pattern UPPERCASE = Pattern.compile("[A-Z]+");
    private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

    public static String replace(String content, String... args) {

        Matcher matcher = pattern.matcher(content);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            int index = Integer.parseInt(matcher.group(1));
            matcher.appendReplacement(stringBuffer, args[index]);
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static String replaceKey(String content, String key, String value) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Pattern p = Pattern.compile("\\$" + key);
            Matcher m = p.matcher(content);
            boolean isFind = false;
            while (m.find()) {
                isFind = true;
                m.appendReplacement(stringBuffer, value);
            }
            logger.error("replaceKey isFind:{}", isFind);

            m.appendTail(stringBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /**
     * 字符串转驼峰
     * @param s
     * @param separator 字符串分割符
     * @return
     */
    public static String toHump(String s, String separator) {
        String[] nameArr = s.split(separator);
        StringBuffer stringBuffer = new StringBuffer();
        int nameArrLen = nameArr.length;
        for (int i = 0; i < nameArrLen; i++) {
            if (nameArr[i].length() > 0) {
                if (stringBuffer.length() == 0) {
                    stringBuffer.append(Character.toLowerCase(nameArr[i].charAt(0)));
                } else {
                    stringBuffer.append(Character.toUpperCase(nameArr[i].charAt(0)));
                }
                stringBuffer.append(nameArr[i].substring(1));
            }
        }
        return stringBuffer.toString();
    }

    public static String toUpCase(String s) {
        return toUpCase(s, "_");
    }

    public static String toUpCase(String s, String separator) {
        Matcher mather = UPPERCASE.matcher(s);
        StringBuffer stringBuffer = new StringBuffer();
        int startIndex = 0;
        while (mather.find()) {
            String string = s.substring(startIndex, mather.start());
            appendUpper(stringBuffer, string, separator);
            stringBuffer.append(separator);
            startIndex = mather.start();
        }
        if (startIndex < s.length() - 1) {
            String string = s.substring(startIndex, s.length());
            appendUpper(stringBuffer, string, separator);
        }
        return stringBuffer.toString();
    }

    private static void appendUpper(StringBuffer stringBuffer, String string, String separator) {
        while (string.endsWith(separator)) {
            string = string.substring(0, string.length() - separator.length());
        }
        stringBuffer.append(string.toUpperCase());
    }

    public static boolean isEmpty(String string) {
        return string == null || string.equals("");
    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    public static String toUpLowerString(String newValue) {
        Matcher matcher = worldPattern.matcher(newValue);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String worlds = matcher.group();
            if (stringBuffer.length() == 0) {
                stringBuffer.append(Character.toLowerCase(worlds.charAt(0)));
            } else {
                stringBuffer.append(Character.toUpperCase(worlds.charAt(0)));
            }
            stringBuffer.append(worlds.substring(1).toLowerCase());
        }

        return stringBuffer.toString();
    }
}
