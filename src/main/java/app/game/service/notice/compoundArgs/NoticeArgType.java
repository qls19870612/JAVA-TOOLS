package app.game.service.notice.compoundArgs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import app.game.service.notice.ArgType;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/09/17 14:05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface NoticeArgType {
    ArgType value();

    String patternStr();
}
