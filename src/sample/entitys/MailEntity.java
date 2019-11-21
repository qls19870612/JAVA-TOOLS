package sample.entitys;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class MailEntity {

   private long id;
   private long combineId;
   private String title;
   private String content;
   private String data;
   private long createTime;
   private long expireTime;
   private long durationTime;

}