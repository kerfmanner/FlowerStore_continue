package ucu.lab7.flower;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ucu.lab7.item.Item;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Flower extends Item {

    private FlowerStats stats;
    private double price;
    private double sepalLength;

    public Flower(String description) {
        setDescription(description);
        stats = new FlowerStats();
        price = 0.0;
        sepalLength = 0.0;
    }

    public Flower() {
        this("No description");
    }

    public Flower(double price, double sepalLength, FlowerColor color, FlowerType type, String description) {
        this.sepalLength = sepalLength;
        this.price = price;
        this.stats = new FlowerStats(color, type);
        setDescription(description);
    }

    public Flower(double price, double sepalLength, FlowerColor color, FlowerType type) {
        this(price, sepalLength, color, type, "No description");
    }

    public Flower(Flower flower, String description) {
        this.sepalLength = flower.sepalLength;
        this.price = flower.price;
        this.stats = new FlowerStats(flower.stats);
        setDescription(description);
    }

    public Flower(Flower flower) {
        this(flower, "No description");
    }

    public void setFlowerType(FlowerType type) {
        stats.setFlowerType(type);
    }

    public FlowerType getFlowerType() {
        return stats.getFlowerType();
    }

    public void setColor(FlowerColor color) {
        stats.setColor(color);
    }

    public FlowerColor getColor() {
        return stats.getColor();
    }

    @Override
    public double price() {
        return this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Flower))
            return false;
        Flower f = (Flower) o;
        return Double.compare(f.price, price) == 0 &&
                Double.compare(f.sepalLength, sepalLength) == 0 &&
                Objects.equals(f.stats, stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stats, price, sepalLength);
    }
}
