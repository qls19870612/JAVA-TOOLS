package sample.datas.db_entry;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/10/24 20:23
 */
@Getter
@Setter
public class PublishLog {
    private int id;
    private String versionName;
    private String serverFolder;
    private int updateDate;
}
