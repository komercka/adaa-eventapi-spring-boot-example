package cz.kb.openbanking.adaa.eventapi.example.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Tests for {@link DiagnosticsApiController}.
 *
 * @author <a href="mailto:michal_madle@kb.cz">Michal Madle</a>
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DiagnosticsApiController.class)
public class DiagnosticsApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Tests correct request with all required headers.
     * Expectation: HTTP 200 OK and 'version' in body.
     *
     * @throws Exception the exception
     */
    @Test
    public void testVersion200Ok() throws Exception {
        mockMvc
            .perform(get("/version").header("x-correlation-id", UUID.randomUUID().toString()))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.version").isNotEmpty());
    }

    /**
     * Tests request without required header 'x-correlation-id'.
     * Expectation: HTTP 400 BAD_REQUEST.
     *
     * @throws Exception the exception
     */
    @Test
    public void testVersion400BadRequest() throws Exception {
        mockMvc
            .perform(get("/version"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }
}
