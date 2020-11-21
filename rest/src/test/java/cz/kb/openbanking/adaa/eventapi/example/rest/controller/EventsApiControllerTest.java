package cz.kb.openbanking.adaa.eventapi.example.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import cz.kb.openbanking.adaa.client.api.AccountApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Tests for {@link EventsApiControllerTest}.
 *
 * @author <a href="mailto:michal_madle@kb.cz">Michal Madle</a>
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EventsApiController.class)
public class EventsApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountApi accountApi;

    /**
     * Tests correct request with all required headers.
     * Expectation: HTTP 204 NO_CONTENT.
     *
     * @throws Exception exception
     */
    @Test
    public void test204NoContent() throws Exception {
        mockMvc
            .perform(
                post("/subscriptions/" + UUID.randomUUID() + "/events")
                    .header("x-correlation-id", UUID.randomUUID().toString())
                    .header("Accept-Language", "CZ")
                    .characterEncoding(StandardCharsets.UTF_8.name())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"eventCount\":1}")
            )
            .andDo(print())
            .andExpect(status().isNoContent());
    }

    /**
     * Tests request without required header 'x-correlation-id'.
     * Expectation: HTTP 400 BAD_REQUEST.
     *
     * @throws Exception exception
     */
    @Test
    public void test400BadRequest() throws Exception {
        mockMvc
            .perform(post("/subscriptions/" + UUID.randomUUID() + "/events"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }
}
