package app.game.module;

import com.google.inject.Inject;

import app.game.service.guicejob.AutoBindType;
import app.game.service.guicejob.GuiceAutoBind;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(onConstructor = @__(@Inject))
@GuiceAutoBind(bindType = AutoBindType.MODULE)
public class Modules {
    public static final int LOGIN_MODULE_ID = 1;
    public static final int MISC_MODULE_ID = 2;
    public static final int FUNCTION_MODULE_ID = 3;
    public static final int HERO_MISC_MODULE_ID = 4;
    public static final int GM_MODULE_ID = 5;
    public static final int GOODS_MODULE_ID = 6;
    public static final int NOTICE_MODULE_ID = 7;
    public static final int MAIL_MODULE_ID = 8;

    // 最大不要超过这个值（可以等于）,真的找不到模块id来用的时候，扩大1倍这个值
    public static final int MAX_MODULE_ID = 127;

    //    public final BossModule bossModule;
 

}
