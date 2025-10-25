package ucu.lab7.delivery;

import java.util.LinkedList;

import ucu.lab7.item.Item;

public interface Delivery {

    public void deliver(LinkedList<Item> items);

}
