package sample.entitys;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class ActivityGoodsEntity {

   private String orderId;
   private int operatorId;
   private long combineId;
   private int activityId;
   private int num;
   private long time;

}