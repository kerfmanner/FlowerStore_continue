package ucu.lab7.delivery;

import java.util.LinkedList;
import ucu.lab7.item.Item;

public class DHLDeliveryStrategy implements Delivery {
    @Override
    public void deliver(LinkedList<Item> items) {
        System.out.println("Delivering via DHL:");
        items.forEach(i -> System.out.println("- " + i.getDescription() + " | " + i.price() + " UAH"));
        System.out.println("DHL delivery completed.");
    }
}
