package app;

import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SpringBootIntegrationTest {
    @Autowired
    private DSLContext dsl;

    @Test
    public void givenAutowiredDSLContext_assertInstanceInject() {
        assertThat(dsl).isNotNull();
    }
}
