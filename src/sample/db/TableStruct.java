package sample.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import sample.utils.StringUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/11/17 20:38
 */
public class TableStruct {
    private static final Logger logger = LoggerFactory.getLogger(TableStruct.class);
    public final String prepareSql;
    public final String upLowerTableName;
    public String tableName;
    public final TableField[] fields;
    private final char[] fieldStrBytes;
    private Map<String, TableField> fieldMap;

    public TableStruct(String tableName, TableField[] fields) {
        this.tableName = tableName;
        this.fields = fields;
        this.prepareSql = null;
        this.fieldStrBytes = null;
        upLowerTableName = StringUtils.toUpLowerString(tableName, true);
    }

    public final Map<String, TableField> getFieldMap() {
        if (fieldMap == null) {
            fieldMap = new HashMap<>();
            for (TableField field : fields) {
                fieldMap.put(field.fieldName, field);
            }
        }
        return fieldMap;
    }


}
