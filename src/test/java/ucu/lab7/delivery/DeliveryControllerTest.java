package ucu.lab7.delivery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DeliveryController.class)
class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /delivery/ should return all available delivery methods")
    void testGetDeliveryMethods() throws Exception {
        mockMvc.perform(get("/delivery/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", is("DHL")))
                .andExpect(jsonPath("$[1]", is("POST")));
    }

    @Test
    @DisplayName("GET /delivery/dhl should process DHL delivery with default item count")
    void testDeliverDhlDefaultItems() throws Exception {
        mockMvc.perform(get("/delivery/dhl"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Processed delivery via DHL for 2 items!")));
    }

    @Test
    @DisplayName("GET /delivery/post?itemsCount=5 should process POST delivery for 5 items")
    void testDeliverPostWithCustomItems() throws Exception {
        mockMvc.perform(get("/delivery/post").param("itemsCount", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Processed delivery via POST for 5 items!")));
    }

    @Test
    @DisplayName("GET /delivery/unknown should return 400 for invalid method")
    void testInvalidDeliveryMethod() throws Exception {
        mockMvc.perform(get("/delivery/fedex"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Unknown delivery method: fedex")));
    }
}
