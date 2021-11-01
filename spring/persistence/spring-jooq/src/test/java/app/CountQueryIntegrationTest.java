package app;

import static com.baeldung.jooq.introduction.db.public_.tables.Author.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CountQueryIntegrationTest {
    @Autowired
    private DSLContext dsl;

    @Test
    public void givenValidData_whenSimpleSelect_thenSuccess() {
        int count = dsl.select().from(AUTHOR).execute();
        assertThat(count).isEqualTo(3);
    }
}
