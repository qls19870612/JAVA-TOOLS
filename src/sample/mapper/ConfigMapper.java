package sample.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import sample.enums.ConfigType;
import sample.utils.Utils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/10/22 17:16
 */


public interface ConfigMapper {
    @ResultType(String.class)
    @Select("select value from config where key=#{key}")
    public String getConfig(@Param("key") String key);

    default String getConfig(ConfigType key) {
        String config = getConfig(key.name());
        if (config == null) {
            return "";
        }
        return config;
    }


    //    @Insert("update config set key=#{key},value=#{value}")
    //    public int setConfig(@Param("key") String key, @Param("value") String value);
    @Insert("insert into config (key,value) values (#{key},#{value}) on CONFLICT(key) DO UPDATE SET value=#{value}")
    public int setConfig(@Param("key") String key, @Param("value") String value);

    default int setConfig(ConfigType key, @Param("value") String value) {
        return setConfig(key.name(), value);
    }

    default int getInt(ConfigType type) {
        String config = getConfig(type);
        return Utils.safeParseInt(config, 0);
    }

    default int getInt(ConfigType type, int defaultValue) {
        String config = getConfig(type);
        return Utils.safeParseInt(config, defaultValue);
    }

    default int setInt(ConfigType key, int value) {
        return setConfig(key, String.valueOf(value));
    }
}
