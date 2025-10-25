package ucu.lab7.payment;

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
 * Integration test for the PaymentController.
 * We use @WebMvcTest to load only the web layer (and not the full application
 * context),
 * focusing specifically on the PaymentController.
 */
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    // MockMvc allows us to send HTTP requests to our controller without a running
    // server.
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPaymentMethods() throws Exception {
        mockMvc.perform(get("/payment/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$", containsInAnyOrder("PAYPAL", "CREDIT")));
    }

    @Test
    public void testPostPaymentWithCreditCard() throws Exception {
        double amount = 125.50;
        String method = "credit";
        // Perform a POST request to "/payment/test"
        mockMvc.perform(post("/payment/test")
                .param("method", method)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Payment of " + amount + " UAH via " + method.toUpperCase() + " processed successfully!"));
    }

    @Test
    public void testPostPaymentWithPayPal() throws Exception {
        double amount = 77.00;
        String method = "paypal";

        // Perform a POST request to "/payment/test"
        mockMvc.perform(post("/payment/test")
                .param("method", method)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Payment of " + amount + " UAH via " + method.toUpperCase() + " processed successfully!"));
    }

    @Test
    public void testPostPaymentWithInvalidMethod() throws Exception {
        double amount = 10.00;
        String method = "bitcoin"; // An unsupported method

        mockMvc.perform(post("/payment/test")
                .param("method", method)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Unknown payment method: " + method));
    }
}
