package sample.entitys;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class PublishLogEntity {

   private int id;
   private String versionName;
   private String serverFolder;
   private int updateDate;
   private int isDelete;

}