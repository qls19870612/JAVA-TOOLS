package sample.entitys;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class AuctionLogEntity {

   private long id;
   private long time;
   private long sellerCombineId;
   private String sellerName;
   private long buyerCombineId;
   private String buyerName;
   private int amount;
   private int tax;
   private int goodsId;
   private byte[] goodsClientProto;
   private boolean logType;

}