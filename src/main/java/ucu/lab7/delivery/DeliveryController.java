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

    @GetMapping("/{method}")
    public String deliver(@PathVariable("method") String method,
                          @RequestParam(required = false, defaultValue = "2") int itemsCount) {
        String normalized = method.toLowerCase(Locale.ROOT);

        LinkedList<Item> items = new LinkedList<>();
        for (int i = 0; i < itemsCount; i++) {
            items.add(new Flower(12, 10.0 + i, FlowerColor.RED, FlowerType.ROSE, "Test flower " + (i + 1)));
        }

        Delivery delivery;
        switch (normalized) {
            case "dhl" -> delivery = new DHLDeliveryStrategy();
            case "post" -> delivery = new PostDeliveryStrategy();
            default -> throw new IllegalArgumentException("Unknown delivery method: " + method);
        }

        delivery.deliver(items);
        return "Processed delivery via " + method.toUpperCase(Locale.ROOT)
                + " for " + itemsCount + " items!";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
