package sample.log;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/01/07 21:11
 */
public class MyTimeBasedRollingPolicy<E> extends SizeAndTimeBasedRollingPolicy<E> {
    public MyTimeBasedRollingPolicy() {
        super();
    }

    @Override
    public boolean isStarted() {
        return false;
    }
}
