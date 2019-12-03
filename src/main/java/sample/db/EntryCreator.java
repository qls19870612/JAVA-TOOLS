package sample.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;

import javafx.collections.ObservableList;
import sample.Controller;
import sample.db.mysql.MysqlDialectProvider;
import sample.db.sqlite.SqlLiteDialectProvider;
import sample.file.FileOperator;
import sample.utils.TimeUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/21 11:186
 */

public class EntryCreator {
    private static final Logger logger = LoggerFactory.getLogger(EntryCreator.class);
    private String javaEntryTemplate;
    private AbstractDialectProvider provider;
    public HashMap<String, TableStruct> structHashMap;

    public EntryCreator() {

        javaEntryTemplate = FileOperator.getConfig("config/javaEntryTemplate.template");

    }

    public void initDbStruct(String dbUrl, String dbName, String userName, String password, String dbType) throws Exception {

        switch (dbType) {
            case "Mysql":
                provider = new MysqlDialectProvider();
                break;
            case "Sqlite":

                provider = new SqlLiteDialectProvider();
                break;
            default:
                throw new Exception("未实现的数据库");
        }
        Connection connection = provider.getConnection(dbUrl, userName, password);
        structHashMap = DbUtils.getTableField(connection, dbName);

    }

    public void createEntity(ObservableList<TableStruct> items, String sourceFolder, String packageName, String prefix, String suffix) {
        for (TableStruct tableStruct : items) {
            createEntity(tableStruct, sourceFolder, packageName, prefix, suffix);
        }
        Controller.log("生成成功" + TimeUtils.printTime2(System.currentTimeMillis()));
    }

    public void createEntity(TableStruct tableStruct, String sourceFolder, String packageName, String prefix, String suffix,
            boolean justSelectField) {
        TableField[] fields = tableStruct.fields;

        StringBuilder filedStr = new StringBuilder();
        String importStr = "";
        HashSet<String> repeateNames = new HashSet<>();
        for (TableField field : fields) {
            if (justSelectField) {
                if (!field.isSelected()) {
                    continue;
                }
            }
            filedStr.append("   private ");
            String javaClass = provider.getJavaClass(field);
            if (importStr.length() == 0 && "Date".equals(javaClass)) {
                importStr = "import java.util.Date;";
            }
            filedStr.append(javaClass);
            filedStr.append(" ");
            if (repeateNames.contains(field.upLowerFieldName)) {
                if (repeateNames.contains(field.fieldName)) {
                    repeateNames.add(field.fieldName + "1");
                    filedStr.append(field.fieldName).append("1");

                } else {

                    filedStr.append(field.fieldName);
                    repeateNames.add(field.fieldName);
                }
            } else {

                filedStr.append(field.upLowerFieldName);
                repeateNames.add(field.upLowerFieldName);
            }
            filedStr.append(";//");
            filedStr.append(field.desc);
            filedStr.append(FileOperator.NEX_LINE);

        }

        String className = prefix + tableStruct.upLowerTableName + suffix;
        String entityContent = javaEntryTemplate.replaceAll("\\$package", packageName);
        entityContent = entityContent.replaceAll("\\$import", importStr);
        entityContent = entityContent.replaceAll("\\$className", className);
        entityContent = entityContent.replaceAll("\\$filed", filedStr.toString());
        File file = new File(sourceFolder);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOperator.writeFile(new File(sourceFolder + "/" + className + ".java"), entityContent);
    }

    public void createEntity(TableStruct tableStruct, String sourceFolder, String packageName, String prefix, String suffix) {
        createEntity(tableStruct, sourceFolder, packageName, prefix, suffix, false);
    }

}
