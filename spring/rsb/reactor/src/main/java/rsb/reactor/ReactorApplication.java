package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Bean;

import static org.springframework.boot.logging.LoggingSystem.SYSTEM_PROPERTY;

@Slf4j
@SpringBootApplication
public class ReactorApplication {
	@Bean
	public CustomerService customerService() {
		log.info("logging system setting: {}", System.getProperty(SYSTEM_PROPERTY));
		LoggingSystem loggingSystem = LoggingSystem.get(this.getClass().getClassLoader());
		log.info("default logging System: {}", loggingSystem);
		return new CustomerService();
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactorApplication.class, args);
	}

}
