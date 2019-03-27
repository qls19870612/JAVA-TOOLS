package sample.fxml.controllers.gm.handlers.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/27 10:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Handler {
    int moduleId();
}