package ucu.lab7.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping("/")
    public List<String> getPaymentMethods() {
        // Returns available payment strategies
        return List.of("PAYPAL", "CREDIT");
    }

    @PostMapping("/test")
    public String testPayment(@RequestParam String method, @RequestParam double amount) {
        Payment payment;

        switch (method.toLowerCase()) {
            case "paypal" -> payment = new PayPalPaymentStrategy();
            case "credit" -> payment = new CreditCardPaymentStrategy();
            default -> throw new IllegalArgumentException("Unknown payment method: " + method);
        }

        payment.pay(amount);
        return "Payment of " + amount + " UAH via " + method.toUpperCase() + " processed successfully!";
    }

    /**
     * Handles IllegalArgumentException thrown by this controller.
     * Returns an HTTP 400 Bad Request status with the exception message.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
