package ucu.lab7.delivery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for the DeliveryController.
 * This test uses the actual implementations of Item, Flower, etc.
 * from the 'ucu.lab7.item' and 'ucu.lab7.flower' packages.
 */
@WebMvcTest(DeliveryController.class)
public class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetDeliveryMethods() throws Exception {
        mockMvc.perform(get("/delivery/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$", containsInAnyOrder("DHL", "POST")));
    }

    @Test
    public void testPostDeliveryWithDHL() throws Exception {
        String method = "dhl";

        mockMvc.perform(post("/delivery/test")
                .param("method", method))
                .andExpect(status().isOk())
                .andExpect(content().string("Delivery method " + method.toUpperCase() + " tested successfully!"));
    }

    @Test
    public void testPostDeliveryWithPost() throws Exception {
        String method = "post";

        mockMvc.perform(post("/delivery/test")
                .param("method", method))
                .andExpect(status().isOk())
                .andExpect(content().string("Delivery method " + method.toUpperCase() + " tested successfully!"));
    }

    @Test
    public void testPostDeliveryWithInvalidMethod() throws Exception {
        String method = "fedex";

        mockMvc.perform(post("/delivery/test")
                .param("method", method))
                // Expect the HTTP status to be 400 Bad Request
                // (This now passes because of the @ExceptionHandler in the controller)
                .andExpect(status().isBadRequest())
                // Also verify the controller returns the correct error message
                .andExpect(content().string("Unknown delivery method: " + method));
    }
}
