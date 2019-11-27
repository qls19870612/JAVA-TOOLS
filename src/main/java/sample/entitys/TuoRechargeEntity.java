package sample.entitys;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class TuoRechargeEntity {

   private long combineId;
   private int operatorId;
   private int serverId;
   private int userId;
   private int gold;
   private int time;

}