package sample.fxml.controllers.ts;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import lombok.Getter;
import lombok.Setter;
import sample.utils.StringUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/04/28 10:42
 */
public class TsClassTemplate {
    public String ClassName;
    public String fileName;
    public ArrayList<FieldInfo> list = Lists.newArrayList();
    public HashMap<Integer, FieldInfo> map = Maps.newHashMap();
    public JSONObject datas;
    private int colMax;
    @Getter
    private boolean keyValueFormat;
    public ArrayList<SubClassInfo> subClasses = Lists.newArrayList();
    HashMap<String, SubClassInfo> subClassesMap = Maps.newHashMap();

    public FieldInfo addFiled(String name, String type, String comment, int col) {
        FieldInfo e = new FieldInfo(name, type, comment, col);
        list.add(e);
        return e;
    }

    public void toFiledListMap() {

        for (FieldInfo fieldInfo : list) {
            map.put(fieldInfo.col, fieldInfo);
            if (fieldInfo.isArray()) {
                SubClassInfo subClassInfo = subClassesMap.get(fieldInfo.arrayClass);
                if (subClassInfo == null) {
                    subClassInfo = new SubClassInfo(fieldInfo.arrayClass,fieldInfo.arrayFieldName);
                    subClassesMap.put(fieldInfo.arrayClass, subClassInfo);
                    subClasses.add(subClassInfo);
                }
                if (!subClassInfo.hasField(fieldInfo.subFieldName)) {
                    subClassInfo.addFiled(fieldInfo);
                }
            }
        }
        if (list.size() == 2) {
            for (FieldInfo fieldInfo : list) {
                if (!fieldInfo.name.equals("key") && !fieldInfo.name.equals("value")) {
                    return;
                }
            }
            keyValueFormat = true;
        }


    }


    public FieldInfo getFiledInfo(int col) {
        return map.get(col);
    }

    public void setColMax(int cellNum) {
        this.colMax = cellNum;
    }

    public int getColMax() {
        return colMax;
    }

    @Getter
    @Setter
    public static class FieldInfo {
        private static final Logger logger = LoggerFactory.getLogger(FieldInfo.class);
        public final String name;
        public final String type;
        public final String comment;
        public final int col;//在表格中的第几列

        private String arrayClass;
        private String arrayFieldName;
        private String subFieldName;
        private String subFieldType;
        private int arrayIndex = -1;

        public FieldInfo(String name, String type, String comment, int col) {
            this.name = name;
            this.type = type;
            this.comment = comment;
            this.col = col;
            if (isArray()) {
                String[] split = type.split(";");
                subFieldType = split[0];
                arrayClass = split[1];
                String[] s = name.split("_");
                if (s.length >= 3) {

                    arrayFieldName = s[0];
                    arrayIndex = Integer.parseInt(s[1]);
                    subFieldName = StringUtils.joinArray(s, 2, "_");
                } else {
                    String err = "字段名不对";
                    logger.error("FiledInfo name:{},type:{},err:{}", name, type, err);
                }

            }

        }

        public boolean isNumber() {
            return type.startsWith("number");
        }

        public boolean isArray() {
            return type.contains(";");
        }

    }

    @Getter
    @Setter
    public class SubClassInfo {
        public final String className;
        public final String arrayFieldName;
        public ArrayList<FieldInfo> list = Lists.newArrayList();
        public HashSet<String> filedNameSet = Sets.newHashSet();

        public SubClassInfo(String className, String arrayFieldName) {
            this.className = className;
            this.arrayFieldName = arrayFieldName;
        }

        public boolean hasField(String filedName) {
            return filedNameSet.contains(filedName);
        }

        public void addFiled(FieldInfo fieldInfo) {
            if (hasField(fieldInfo.getSubFieldName())) {
                return;
            }
            filedNameSet.add(fieldInfo.getSubFieldName());
            list.add(fieldInfo);
        }

    }
}















