package ucu.lab7.delivery;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ucu.lab7.flower.Flower;
import ucu.lab7.flower.FlowerColor;
import ucu.lab7.flower.FlowerType;
import ucu.lab7.item.Item;

import java.util.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @GetMapping("/")
    public List<String> getDeliveryMethods() {
        return List.of("DHL", "POST");
    }

    @PostMapping("/test")
    public String testDelivery(@RequestParam String method) {
        LinkedList<Item> items = new LinkedList<>();
        items.add(new Flower(12, 25.1, FlowerColor.RED, FlowerType.CHAMOMILE));
        items.add(new Flower(12, 2.1, FlowerColor.BLUE, FlowerType.ROSE, "with description"));

        Delivery delivery;
        switch (method.toLowerCase()) {
            case "dhl" -> delivery = new DHLDeliveryStrategy();
            case "post" -> delivery = new PostDeliveryStrategy();
            default -> throw new IllegalArgumentException("Unknown delivery method: " + method);
        }

        delivery.deliver(items);
        return "Delivery method " + method.toUpperCase() + " tested successfully!";
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