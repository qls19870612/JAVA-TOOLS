package app.game.service.notice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *没有注解是哪个模块，moduleId = 0(通用)
 * 创建人  liangsong
 * 创建时间 2018/08/17 10:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ModuleNoticeEnum {
    /**
     * 模块ID
     * @return
     */
    int value();
}
