package sample.fxml.config;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/22 11:49
 */
@Getter
@Setter
public class DbConfig {
    private int id;
    private String path;
    private String packageName;
    private String classNamePrefix;
    private String classNameSuffix;
    private String dbUrl;
    private String dbName;
    private String dbUserName;
    private String dbPassword;
    private String dbType;

    public DbConfig() {
    }

    public DbConfig(String path, String packageName, String classNamePrefix, String classNameSuffix, String dbUrl, String dbName, String dbUserName,
            String dbPassword, String dbType) {
        this.path = path;
        this.packageName = packageName;
        this.classNamePrefix = classNamePrefix;
        this.classNameSuffix = classNameSuffix;
        this.dbUrl = dbUrl;
        this.dbName = dbName;
        this.dbUserName = dbUserName;
        this.dbPassword = dbPassword;
        this.dbType = dbType;
    }

    @Override
    public String toString() {
        return "DbConfig{" + "path='" + path + '\'' + ", packageName='" + packageName + '\'' + ", classNamePrefix='" + classNamePrefix + '\'' +
                ", classNameSuffix='" + classNameSuffix + '\'' + ", dbUrl='" + dbUrl + '\'' + ", dbName='" + dbName + '\'' + ", dbUserName='" +
                dbUserName + '\'' + ", dbPassword='" + dbPassword + '\'' + ", dbType='" + dbType + '\'' + '}';
    }
}
