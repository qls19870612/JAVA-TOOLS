package sample.mapper;

import org.apache.ibatis.annotations.Update;


/**
 *
 * 创建人  liangsong
 * 创建时间 2019/10/22 20:19
 */
public interface TableCreatorMapper {
    @Update("create table if not exists config (id INTEGER PRIMARY KEY AUTOINCREMENT,key VARCHAR(45) NOT NULL UNIQUE,value VARCHAR(1024) NULL)")
    public void createConfig();

    @Update({

            "create table if not exists publish_log ",

            "(id INTEGER PRIMARY KEY AUTOINCREMENT,",

            "versionName VARCHAR(100) NOT NULL UNIQUE,",

            "serverFolder VARCHAR(150) NULL,",

            "updateDate INTEGER default 0,",

            "isDelete INTEGER default 0",

            ")"

    })
    public void createDiabloPublish();

    public default void createTables() {
        createConfig();
        createDiabloPublish();
    }
}

