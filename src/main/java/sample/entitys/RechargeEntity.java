package sample.entitys;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class RechargeEntity {

   private String orderId;
   private String operatorOrderId;
   private long combineId;
   private int operatorId;
   private int serverId;
   private int userId;
   private int itemId;
   private int rmb;
   private int gold;
   private int time;
   private int processTime;
   private byte[] misc1;
   private byte[] misc2;

}