package sample.fxml.controllers.client.msgs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sample.fxml.controllers.client.Modules;


/**
 * @author Liwei
 *
 */
public class HeroMiscModuleWrittenMessages {
    private static final Logger logger = LoggerFactory.getLogger(HeroMiscModuleWrittenMessages.class);
    public static final int MODULE_ID = Modules.HERO_MISC_MODULE_ID;

    /**
     * 属性值变化收到此消息，附带以下信息
     *
     * bytes SpriteStatProto
     */
    static final int S2C_CHANGE_STAT = 501;

    /**
     * 等级变化收到此消息，广播, 不发给自己, 附带以下信息
     * string heroId
     * varint32 最新等级
     */
    public static final int S2C_CHANGE_LEVEL = 502;


    /**
     * 增加经验
     * varint64 当前经验值
     * varint32 等级
     * varint64 增加的经验值
     * varint32 加成百分比，0表示基础经验值和增加的经验值一样，没有加成
     */
    public static final int S2C_ADD_EXP = 503;


    /**
     * 通用货币数值改变时收到此消息，附带以下信息
     * varint32 类型
     * varint64 当前值
     *
     * bool 增加(true)还是减少(false)
     * if(增加){
     *      varint64 增加的量
     * } else {
     *      varint64 减少的量
     * }
     */
    public static final int S2C_CHANGE_AMOUNT = 504;


    /**
     * 战斗力变化
     * varint64 当前战斗力
     */
    public static final int S2C_FIGHT_AMOUNT_CHANGE = 506;


    /**
     * 每日重置通用消息
     */
    public static final int S2C_DAILY_RESET = 507;

    /**
     * 每周重置通用消息
     */
    public static final int S2C_WEEKLY_RESET = 508;

    /**
     * 每月重置通用消息
     */
    public static final int S2C_MONTHLY_RESET = 509;

    /**
     * 魅力值变化
     * varin32 当前魅力值
     */
    public static final int S2C_GLAMOUR_CHANGE = 510;


    /**
     * 充值成功
     * varint32 充值项id
     * varint32 已购买次数
     */
    public static final int S2C_RECHARGE_SUCCESS = 511;

    /**
     * 绑钻礼包时间变化
     * varint32 id
     * varint32 结束时间
     */
    public static final int S2C_RECHARGE_DAILY_DIAMOND_TIME_CHANGE = 512;


    /**
     * 累计充值天数变化
     * varint32 当前天数
     */
    public static final int S2C_RECHARGE_DAYS_CHANGE = 513;

    /**
     * 有新的每日累充可以领取了
     * varint32 id
     * varint32 level
     */
    public static final int S2C_NEW_DAILY_RECHARGE = 514;


    /**
     * 总充值额改变
     * varint64 当前充值额
     * varint32 总充值次数
     */
    public static final int S2C_TOTAL_RECHARGE_AMOUNT_CHANGE = 515;


    /**
     * 某人战力达到多少
     * utf 角色名 heroName
     * utf 角色ID heroId
     * varint32 战斗力阶段id fightAmountStageId
     */
    public static final int S2C_BROADCAST_FIGHT_AMOUNT_ARRIVED = 516;

    /**
     * 消费非绑定钻石改变
     * varint64 总消费非绑定钻石数
     * varint32 当前消费非绑定钻石
     */
    public static final int S2C_TOTAL_CONSUME_RECHARGE_AMOUNT_CHANGE = 517;


    /**
     * 太长的消息会由这个消息分片发送处理
     * varint32 总数量
     * varint32 当前的idx 从1开始
     * bytes 消息体
     */
    public static final int S2C_TOO_LONG_MESSAGE = 518;


}
