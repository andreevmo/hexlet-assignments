package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@AutoConfigureMockMvc

// BEGIN
@Testcontainers
@Transactional
// END
public class AppTest {

    @Autowired
    private MockMvc mockMvc;

    // BEGIN
    @Container
    private static final PostgreSQLContainer<?> DB = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("test_db")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("init.sql");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", DB::getJdbcUrl);
        registry.add("spring.datasource.username", DB::getUsername);
        registry.add("spring.datasource.password", DB::getPassword);
    }

    @Test
    void testGetPeople() throws Exception {
        mockMvc.perform(get("/people"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"firstName\": \"John\", \"lastName\": \"Smith\"}, \n" +
                        "{\"firstName\": \"Jack\", \"lastName\": \"Doe\"}, \n" +
                        "{\"firstName\": \"Jassica\", \"lastName\": \"Simpson\"}, \n" +
                        "{\"firstName\": \"Robert\", \"lastName\": \"Lock\"}]"));
    }

    @Test
    void testGetPerson() throws Exception {
        mockMvc.perform(get("/people/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"firstName\": \"John\", \"lastName\": \"Smith\"}"));
    }

    @Test
    void testUpdatePerson() throws Exception {
        mockMvc.perform(patch("/people/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"Max\", \"lastName\": \"Andreev\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/people/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"firstName\": \"Max\", \"lastName\": \"Andreev\"}"));
    }

    @Test
    void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/people/1"))
                .andExpect(status().isOk());

        MockHttpServletResponse response = mockMvc.perform(get("/people"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).doesNotContain("John", "Smith");
    }
    // END

    @Test
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
            .perform(
                post("/people")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
            )
            .andReturn()
            .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }
}
