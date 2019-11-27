package sample.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map.Entry;

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
    private final String javaEntryTemplate;

    public EntryCreator() {
        javaEntryTemplate = FileOperator.readFiles(new File("config/javaEntryTemplate.template"));
    }

    public void createEntity(String sourceFolder, String packageName, String prefix, String suffix, String dbUrl, String dbName, String userName,
            String password, String dbType) throws Exception {

        AbstractDialectProvider provider;
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
        HashMap<String, TableStruct> structHashMap = DbUtils.getTableField(connection, dbName);
        for (Entry<String, TableStruct> entry : structHashMap.entrySet()) {

            TableStruct tableStruct = entry.getValue();
            TableField[] fields = tableStruct.fields;

            StringBuilder filedStr = new StringBuilder();
            String importStr = "";

            for (TableField field : fields) {

                filedStr.append("   private ");
                String javaClass = provider.getJavaClass(field);
                if (importStr.length() != 0 && "Date".equals(javaClass)) {
                    importStr = "import java.util.Date";
                }
                filedStr.append(javaClass);
                filedStr.append(" ");
                filedStr.append(field.upLowerFieldName);
                filedStr.append(";");
                filedStr.append(FileOperator.NEX_LINE);

            }

            String className = prefix + tableStruct.upLowerTableName + suffix;
            String entityContent = javaEntryTemplate.replaceAll("\\$package", packageName);
            entityContent = entityContent.replaceAll("\\$import", importStr);
            entityContent = entityContent.replaceAll("\\$className", className);
            entityContent = entityContent.replaceAll("\\$filed", filedStr.toString());

            FileOperator.writeFile(new File(sourceFolder + "/" + className + ".java"), entityContent);
        }
        Controller.log("生成成功" + TimeUtils.printTime2(System.currentTimeMillis()));
    }

    public HashMap<String, TableStruct> getDbStruct(String dbUrl, String dbName, String userName, String password, String dbType) throws Exception {

        AbstractDialectProvider provider;
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
        HashMap<String, TableStruct> structHashMap = DbUtils.getTableField(connection, dbName);

        return structHashMap;
    }
}
