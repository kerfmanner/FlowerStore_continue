package ucu.lab7.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @GetMapping("/")
    public List<String> getPaymentMethods() {
        return List.of("PAYPAL", "CREDIT", "CASH");
    }

    @GetMapping("/{method}")
    public String makePayment(@PathVariable("method") String method,
                              @RequestParam(required = false, defaultValue = "0") Double amount) {
        String normalized = method.toLowerCase(Locale.ROOT);

        switch (normalized) {
            case "paypal" -> {
                return "Processed payment via PayPal" +
                        (amount > 0 ? " of $" + amount : "");
            }
            case "credit" -> {
                return "Processed payment via Credit Card" +
                        (amount > 0 ? " of $" + amount : "");
            }
            case "cash" -> {
                return "Processed payment in Cash" +
                        (amount > 0 ? " of $" + amount : "");
            }
            default -> throw new IllegalArgumentException("Unknown payment method: " + method);
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
