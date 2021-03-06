// Generated by tool. DO NOT EDIT!

package sample.fxml.controllers.client.msgs;

import org.jboss.netty.buffer.ChannelBuffer;

import sample.fxml.controllers.client.Modules;

import static sample.utils.BufferUtil.computeUTF;
import static sample.utils.BufferUtil.newDynamicMessage;
import static sample.utils.BufferUtil.newFixedSizeMessage;
import static sample.utils.BufferUtil.onlySendHeadAndAByteMessage;
import static sample.utils.BufferUtil.writeUTF;


public class LoginModuleMessages {

    public static final int MODULE_ID = Modules.LOGIN_MODULE_ID;

    /**
     * 登陆账号
     *
     * string 账号名
     * varint32 平台id
     * varint32 区服id
     * String 设备ID
     * String 渠道标识 channel
     * String 签名 sign
     * String 时间戳 time
     */
    public static final int C2S_ACCOUNT_LOGIN = 1;

    /**
     * 登陆成功
     * bytes HeroProto 角色信息
     */
    public static final int S2C_ACCOUNT_LOGIN_OK = 1;

    public static ChannelBuffer accountLoginOk() {
        return newDynamicMessage(MODULE_ID, S2C_ACCOUNT_LOGIN_OK);
    }

    /**
     * 角色重连
     *
     * string 重连key
     */
    public static final int C2S_RECONNECT_LOGIN = 2;

    /**
     * 角色重连的key
     *
     * string 重连key key
     */
    public static final int S2C_RECONNECT_LOGIN_OK = 2;

    public static ChannelBuffer reconnectLoginOk() {
        return newDynamicMessage(MODULE_ID, S2C_RECONNECT_LOGIN_OK);
    }

    /**
     * 1 重连key失效请重新登录
     */
    public static final int S2C_RECONNECT_LOGIN_ERR = 3;

    public static ChannelBuffer ERR_RECONNECT_LOGIN_INVALID_KEY = onlySendHeadAndAByteMessage(MODULE_ID, S2C_RECONNECT_LOGIN_ERR, 1);

    /**
     * 随机名字
     * bool 是否男性
     */
    public static final int C2S_RANDOM_NAME = 3;

    /**
     * 随机名字
     * string 名字
     */
    public static final int S2C_RANDOM_NAME_OK = 4;

    public static ChannelBuffer randomNameOk(String name) {
        ChannelBuffer buffer = newFixedSizeMessage(MODULE_ID, S2C_RANDOM_NAME_OK, computeUTF(name));
        writeUTF(buffer, name);
        return buffer;
    }

    /**
     * 创建账号,创建账号成功后，会发送账号登录成功协议S2C_ACCOUNT_LOGIN_OK
     * varint32 sex 1:男,2：女
     * varint32 name 名字
     */
    public static final int C2S_ACCOUNT_CREATE = 4;

}