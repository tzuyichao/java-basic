package rsb.bootstrap.context;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import rsb.bootstrap.CustomerService;
import rsb.bootstrap.Demo;
import rsb.bootstrap.SpringUtils;
import rsb.bootstrap.TransactionTemplateCustomerService;

import javax.sql.DataSource;

@Configuration
@Import(DataSourceConfiguration.class)
public class Application {
    @Bean
    PlatformTransactionManager transactionManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }

    @Bean
    TransactionTemplate transactionTemplate(PlatformTransactionManager tm) {
        return new TransactionTemplate(tm);
    }

    @Bean
    TransactionTemplateCustomerService customerService(DataSource ds, TransactionTemplate tt) {
        return new TransactionTemplateCustomerService(ds, tt);
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringUtils.run(Application.class, "prod");

        CustomerService customerService = context.getBean(CustomerService.class);

        Demo.workWithCustomerService(Application.class, customerService);
    }
}
