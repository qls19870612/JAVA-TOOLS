package sample.fxml.controllers.ts;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import game.collection.IntHashMap;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/04/28 10:42
 */
public class TsClassTemplate {
    public String ClassName;
    public String fileName;
    public ArrayList<FiledInfo> list = Lists.newArrayList();
    public HashMap<Integer, FiledInfo> map = Maps.newHashMap();
    public JSONObject datas;
    private int colMax;

    public void addFiled(String name, String type, String comment, int col) {
        list.add(new FiledInfo(name, type, comment, col));
    }

    public void toFiledListMap() {
        for (FiledInfo filedInfo : list) {
            map.put(filedInfo.col, filedInfo);
        }
    }
    public FiledInfo getFiledInfo(int col)
    {
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
    public static class FiledInfo {
        public final String name;
        public final String type;
        public final String comment;
        public final int col;//在表格中的第几列

        public FiledInfo(String name, String type, String comment, int col) {
            this.name = name;
            this.type = type;
            this.comment = comment;
            this.col = col;
        }

        public boolean isNumber() {
            return "number".equals(type);
        }
    }
}
