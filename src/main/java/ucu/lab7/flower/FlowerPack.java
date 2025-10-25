package ucu.lab7.flower;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import ucu.lab7.item.Item;

@Getter
@Setter
public class FlowerPack extends Item {
    private Flower flower;
    private int count;

    public FlowerPack(Flower flower, int count, String description) {
        this.flower = new Flower(flower);
        this.count = count;
        setDescription(description);
    }

    public FlowerPack(Flower flower, int count) {
        this(flower, count, "No description");
    }

    public FlowerPack(FlowerPack pack, int amount) {
        this(pack, amount, "No description");
    }

    public FlowerPack(FlowerPack pack, int amount, String description) {
        this.flower = pack.getFlower();
        pack.reduceCount(amount);
        this.count = amount;
        setDescription(description);
    }

    public double getPrice() {
        return flower.getPrice() * count;
    }

    public FlowerStats getStats() {
        return flower.getStats();
    }

    @SneakyThrows
    public void reduceCount(int amount) {
        if (amount > this.count) {
            throw new IllegalArgumentException("Cannot reduce into negative.");
        }
        this.count -= amount;
    }

    @Override
    public double price() {
        return getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof FlowerPack))
            return false;
        FlowerPack that = (FlowerPack) o;
        return count == that.count &&
                Objects.equals(flower, that.flower) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(flower, count, getDescription());
    }

    @Override
    public String toString() {
        return "FlowerPack{" +
                "flower=" + flower +
                ", count=" + count +
                ", description='" + getDescription() + '\'' +
                '}';
    }
}
