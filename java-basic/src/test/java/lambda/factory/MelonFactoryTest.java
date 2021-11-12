package lambda.factory;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MelonFactoryTest {
    @Test
    public void testDefaultConstructorPath() {
        Fruit obj = MelonFactory.newInstance(Gac.class);
        assertThat(obj)
                .isNotNull();
    }

    @Test
    public void testTriArgumentsPath() {
        Fruit obj = MelonFactory.newInstance("Gac", 2000, "red");
        assertThat(obj)
                .isNotNull()
                .satisfies(fruit -> {
                    Melon melon = (Melon) fruit;
                    assertThat(melon.getType()).isEqualTo("Gac");
                    assertThat(melon.getWeight()).isEqualTo(2000);
                    assertThat(melon.getColor()).isEqualTo("red");
                });
    }
}
