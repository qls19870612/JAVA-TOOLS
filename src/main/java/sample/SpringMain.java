package sample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/10/23 10:57
 */
@PropertySource("/config/application.properties")
@EnableAutoConfiguration
@MapperScan(basePackages = {"sample.mapper"})
@ComponentScan(basePackages = {"sample"})
@EnableScheduling
public class SpringMain {

}
