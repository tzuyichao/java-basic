package rsb.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.stream.Stream;

@Slf4j
public class Demo {
    public static void workWithCustomerService(Class<?> label, CustomerService customerService) {
        log.info("=========================================");
        log.info(label.getName());
        log.info("=========================================");

        Stream.of("A", "B", "C").map(customerService::save)
                .forEach(customer -> log.info("saved {}", customer));

        customerService.findAll().forEach(
                customer -> {
                    long customerId = customer.getId();
                    Customer byId = customerService.findById(customerId);
                    log.info("found {}", byId);
                    Assert.notNull(byId, "the resulting customer should not be null");
                    Assert.isTrue(byId.equals(customer), "we should be able to query for this result");
                }
        );
    }
}
