package sample.entitys;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UsersEntity {

   private int id;
   private String name;
   private int heroIdx;
   private String data;
   private long combineId;
   private int operatorId;
   private int serverId;
   private long createTime;
   private long updateTime;
   private String heroName;
   private String heroData;
   private String minorData;
   private String heroRelation;
   private long heroCreateTime;
   private String heroViewCache;
   private boolean isOnline;
   private int easyInfo;
   private byte[] mood;
   private int totalRechargeRmb;
   private long auctionCollectableJinzi;
   private long auctionCollectableExp;
   private long userInfo;
   private int accountInfo;
   private int loginBanEndTime;
   private int talkBanEndTime;

}