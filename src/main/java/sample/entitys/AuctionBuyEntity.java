package sample.entitys;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class AuctionBuyEntity {

   private long id;
   private int goodsId;
   private String goodsName;
   private long goodsType;
   private int goodsQuality;
   private int goodsLevel;
   private int goodsStar;
   private int count;
   private int singlePrice;
   private int totalPrice;
   private int taxPrice;
   private long buyerCombineId;
   private String buyerName;
   private long createTime;
   private long showTime;
   private long expireTime;

}