package sample.entitys;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuctionBuyEntity implements Serializable {

    public AuctionBuyEntity() {

    }

    private Long id;
    private Integer goodsId;
    private String goodsName;
    private Long goodsType;
    private Integer goodsQuality;
    private Integer goodsLevel;
    private Integer goodsStar;
    private Integer count;
    private Integer singlePrice;
    private Integer totalPrice;
    private Integer taxPrice;
    private Long buyerCombineId;
    private String buyerName;
    private Long createTime;
    private Long showTime;
    private Long expireTime;
    private boolean flag;

}