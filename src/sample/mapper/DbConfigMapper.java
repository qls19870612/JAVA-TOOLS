package sample.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import sample.fxml.config.DbConfig;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/22 12:00
 */
public interface DbConfigMapper {
    @Insert("insert into db_config(path,packageName,classNamePrefix,classNameSuffix,dbUrl,dbName,dbUserName,dbPassword,dbType) values(#{path}," +
            "#{packageName},#{classNamePrefix},#{classNameSuffix},#{dbUrl},#{dbName},#{dbUserName},#{dbPassword},#{dbType})")
    public int insert(DbConfig dbConfig);

    @Select("select * from db_config")
    DbConfig[] getDbConfigList();

    @Delete("delete from db_config where id=#{id}")
    int deleteConfig(@Param("id") int id);
}
