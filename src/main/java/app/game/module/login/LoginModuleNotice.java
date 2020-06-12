package app.game.module.login;

import app.game.module.Modules;
import app.game.service.notice.IMsgModuleMessage;
import app.game.service.notice.ModuleNoticeEnum;

/**
 * fangshuai
 * 2018/11/26
 */
@ModuleNoticeEnum(Modules.LOGIN_MODULE_ID)
public enum LoginModuleNotice implements IMsgModuleMessage {
    WRONG_CHECK_SUM(1, "校验码错误"),

    WRONG_OFFSET(2, "偏移位错误"),

    SERVER_RESTART(3, "服务器重启"),

    TOO_LONG_FRAME(4, "数据太大"),

    INNER_ERROR(5, "服务器内部错误"),

    NAME_TOO_LONG(6, "名字太长"),

    INVALID_NAME(7, "名字非法"),

    REPEAT_NAME(8, "名字重复"),

    ACCOUNT_NOT_EXIT(9, "账号不存在"),

    ACCOUNT_LOGIN_NOT_LOGIN_MESSAGES(10, "还没有登录成功，但是发了非登录模块的消息"),//
    ACCOUNT_LOGIN_ALREADY_LOGIN(11, "已经登录了"),//
    LOGIN_SOMEWHERE(12, "其他地方登录了"),//
    NOT_LOGGED_IN_FOR_A_LONG_TIME(13, "长时间未登录"),//
    ACCOUNT_LOGIN_ERROR_SERVER(14, "你要登录的区服不存在,如果是测试阶段,改登录时的参数,正式时不该有这个问题"),//
    ACCOUNT_LOGIN_INNER_ERROR(15, "服务器内部错误,稍后再试 "),//
    ACCOUNT_LOGIN_BAN(16, "你被ban了,请联系客服"),//
    ACCOUNT_LOGIN_LOGIN_SOMEWHERE(17, "其他地方登录了"),//
    RECONNECT_LOGIN_INVALID_KEY(18, "重连key失效请重新登录"),//
    ACCOUNT_CREATE_ALREADY(30, "账号已经创建过了"),//
    ACCOUNT_CREATE_INVALID_SEX(31, "账号创建时性别不对"),//

    ;


    private final int msgId;
    private final String msgInfo;

    LoginModuleNotice(int msgId, String msgInfo) {
        this.msgId = msgId;
        this.msgInfo = msgInfo;
    }

    @Override
    public int getMsgId() {
        return msgId;
    }

    @Override
    public String getMsgInfo() {
        return msgInfo;
    }
}
