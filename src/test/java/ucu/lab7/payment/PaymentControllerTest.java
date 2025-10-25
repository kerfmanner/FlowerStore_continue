package ucu.lab7.payment;

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

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /payments/ should return all available payment methods")
    void testGetPaymentMethods() throws Exception {
        mockMvc.perform(get("/payments/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", is("PAYPAL")))
                .andExpect(jsonPath("$[1]", is("CREDIT")))
                .andExpect(jsonPath("$[2]", is("CASH")));
    }

    @Test
    @DisplayName("GET /payments/paypal?amount=100 should process PayPal payment")
    void testMakePaymentPaypal() throws Exception {
        mockMvc.perform(get("/payments/paypal").param("amount", "100"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Processed payment via PayPal of $100")));
    }

    @Test
    @DisplayName("GET /payments/credit?amount=50 should process Credit Card payment")
    void testMakePaymentCredit() throws Exception {
        mockMvc.perform(get("/payments/credit").param("amount", "50"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Processed payment via Credit Card of $50")));
    }

    @Test
    @DisplayName("GET /payments/cash should process Cash payment with default amount")
    void testMakePaymentCashDefaultAmount() throws Exception {
        mockMvc.perform(get("/payments/cash"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Processed payment in Cash")));
    }

    @Test
    @DisplayName("GET /payments/bitcoin should return 400 for invalid payment method")
    void testInvalidPaymentMethod() throws Exception {
        mockMvc.perform(get("/payments/bitcoin"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Unknown payment method: bitcoin")));
    }
}
