package demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class BasicControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new BasicController()).build();
    }

    @Test
    void testBasic() throws Exception {
        this.mockMvc
                .perform(get("/welcome").accept(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Welcome to Spring MVC"));
    }
}
