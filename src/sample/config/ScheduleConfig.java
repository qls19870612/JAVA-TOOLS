package sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/19 16:21
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(2));
    }
}
