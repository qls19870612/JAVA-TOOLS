package sample.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import sample.entitys.LuaCacheEntity;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/28 20:56
 */
public interface LuaCacheMapper {


    @Insert({"insert into lua_cache (path,updateDate) values ",

            "<foreach item='item' index='index' collection='cacheEntities' open='(' separator=',' close=')'>",

            "#{path},#{updateDate}",

            "</foreach> "})
    int updateLuaCache(@Param("luaCacheArr") LuaCacheEntity[] cacheEntities);

    @Delete("delete from lua_cache")
    int clearAll();
}
