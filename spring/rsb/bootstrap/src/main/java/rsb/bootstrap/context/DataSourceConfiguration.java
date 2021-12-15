package rsb.bootstrap.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import rsb.bootstrap.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Driver;

@Slf4j
@Configuration
public class DataSourceConfiguration {
    @Configuration
    @Profile("prod")
    @PropertySource("application-prod.properties")
    public static class productionConfiguration {
        @Bean
        DataSource productionDataSource(
                @Value("${spring.datasource.url}") String url,
                @Value("${spring.datasource.username}") String username,
                @Value("${spring.datasource.password}") String password,
                @Value("${spring.datasource.driver-class-name}") Class<Driver> driverClass) {
            DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
            dataSource.setDriverClassName(driverClass.getName());
            return dataSource;
        }
    }

    @Bean
    DataSourcePostProcessor dataSourcePostProcessor() {
        return new DataSourcePostProcessor();
    }

    private static class DataSourcePostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if(bean instanceof DataSource) {
                log.info("bean: {}, bean name: {}", bean, beanName);
                DataSourceUtils.initializeDdl(DataSource.class.cast(bean));
            }
            return bean;
        }
    }
}
