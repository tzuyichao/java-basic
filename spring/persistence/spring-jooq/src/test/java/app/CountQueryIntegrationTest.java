package app;

import static com.baeldung.jooq.introduction.db.public_.tables.Author.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.impl.DSL;
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

    @Test
    public void givenValidData_whenCountWithGroupBy_thenSuccess() {
        final Result<Record2<String, Integer>> result = dsl.select(AUTHOR.FIRST_NAME, DSL.count())
                .from(AUTHOR)
                .groupBy(AUTHOR.FIRST_NAME).fetch();
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).get(0)).isEqualTo("Bert");
        assertThat(result.get(0).get(1)).isEqualTo(1);
    }

    @Test
    public void givenValidData_whenFetchCountWithConditionInVarargs_thenSuccess() {
        Condition fistCond = AUTHOR.FIRST_NAME.endsWithIgnoreCase("Bryan");
        Condition secondCond = AUTHOR.ID.notEqual(1);

        int count = dsl.fetchCount(AUTHOR, fistCond, secondCond);
        assertThat(count).isEqualTo(1);
    }
}
