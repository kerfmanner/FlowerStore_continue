package ucu.lab7.delivery;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import ucu.lab7.item.Item;
import ucu.lab7.flower.Flower;
import ucu.lab7.flower.FlowerColor;
import ucu.lab7.flower.FlowerType;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Delivery strategy implementations.
 * This test uses the actual Item and Flower classes.
 */
public class DeliveryStrategyTest {

    // Capture System.out
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private LinkedList<Item> testItems;

    @BeforeEach
    public void setUpStreamsAndData() {
        System.setOut(new PrintStream(outContent));

        testItems = new LinkedList<>();
        testItems.add(new Flower(50.0, 15.0, FlowerColor.RED, FlowerType.ROSE, "A beautiful Red Rose"));
        testItems.add(new Flower(25.5, 10.0, FlowerColor.BLUE, FlowerType.TULIP, "A lovely Blue Tulip"));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testDHLDelivery() {
        Delivery dhl = new DHLDeliveryStrategy();
        String nl = System.lineSeparator();
        String expectedOutput = "Delivering via DHL:" + nl +
                "- A beautiful Red Rose | 50.0 UAH" + nl +
                "- A lovely Blue Tulip | 25.5 UAH" + nl +
                "DHL delivery completed." + nl;

        dhl.deliver(testItems);

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testPostDelivery() {
        Delivery post = new PostDeliveryStrategy();
        String nl = System.lineSeparator();
        String expectedOutput = "Delivering via National Post:" + nl +
                "- A beautiful Red Rose | 50.0 UAH" + nl +
                "- A lovely Blue Tulip | 25.5 UAH" + nl +
                "Post delivery completed." + nl;

        post.deliver(testItems);

        assertEquals(expectedOutput, outContent.toString());
    }
}
