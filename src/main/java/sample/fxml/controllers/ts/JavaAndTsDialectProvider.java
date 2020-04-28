package sample.fxml.controllers.ts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.HashMap;

import sample.db.TableField;


/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/21 11:14
 */
public class JavaAndTsDialectProvider {
    private static final Logger logger = LoggerFactory.getLogger(JavaAndTsDialectProvider.class);



    protected HashMap<String, FieldTypeMap> typeMap = new HashMap<>();

    public JavaAndTsDialectProvider() {
        initTypeMap();
    }

    protected void initTypeMap(){
        addTypeMap("String","string");
        addTypeMap("boolean","boolean");
        addTypeMap("int","number");
        addTypeMap("long","number");
    }

    protected void addTypeMap(String javaType, String tsType) {
        FieldTypeMap value = new FieldTypeMap( tsType,javaType);
        typeMap.put(value.javaType, value);
    }

    public String getTsClass(String javaType) {
        FieldTypeMap fieldTypeMap = typeMap.get(javaType);
        if (fieldTypeMap == null) {
            logger.warn("getJavaClass javaType:{}", javaType);
            return "any";
        }
        return fieldTypeMap.tsType;
    }
}
