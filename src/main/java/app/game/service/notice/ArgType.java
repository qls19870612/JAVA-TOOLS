package app.game.service.notice;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/09/17 14:07
 */
public enum ArgType {
    STRING(1),//不能从0开始
    INT(2), //
    LONG(3),//
    HERO(4), //角色名字
    POSITION(5),//位置
    GOODS(6), // 带参数的道具
    PRIZE(7), // 奖励的PrizeProto
    FUNCTION(8), // 功能开放ID
    ;

    final int value;

    ArgType(int i) {
        this.value = i;
    }
}
