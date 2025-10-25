package ucu.lab7.payment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Payment strategy implementations.
 */
public class PaymentStrategyTest {

    // We need to capture System.out to verify the print statements.
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testCreditCardPayment() {
        Payment creditCard = new CreditCardPaymentStrategy();
        double amount = 150.75;
        String expectedOutput = String.format("Processing Credit Card payment: %s UAH%n", amount);

        creditCard.pay(amount);
        assertEquals(expectedOutput, outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void testPayPalPayment() {
        Payment payPal = new PayPalPaymentStrategy();
        double amount = 88.20;
        String expectedOutput = String.format("Processing PayPal payment: %s UAH%n", amount);

        payPal.pay(amount);

        assertEquals(expectedOutput, outContent.toString().replace("\r\n", "\n"));
    }
}
