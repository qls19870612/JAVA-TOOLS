package sample.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import sample.datas.db_entry.PublishLog;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/10/23 15:43
 */
public interface DiabloPublishMapper {
    @Insert("insert into publish_log (versionName,serverFolder,updateDate) values (#{versionName},#{serverFolder},#{updateDate}) on CONFLICT" +
            "(versionName) DO UPDATE SET " + "serverFolder=#{serverFolder},updateDate=#{updateDate},isDelete=0")
    public int updatePublishLog(@Param("versionName") String versionName, @Param("serverFolder") String serverFolder, @Param("updateDate") int date);

    @Select("select * from publish_log where isDelete=0 order by updateDate desc")
    PublishLog[] getPublishLogs();

    @Update("update publish_log set isDelete=1 where id=#{id}")
    void deleteLog(@Param("id") int id);
}
