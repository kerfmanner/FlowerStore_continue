package ucu.lab7.flower;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
public class FlowerStats {

    private FlowerColor color;
    private FlowerType flowerType;

    public FlowerStats() {
        this.color = null;
        this.flowerType = null;
    }

    public FlowerStats(FlowerStats stats) {
        this.color = stats.color;
        this.flowerType = stats.flowerType;
    }

    public boolean matching(FlowerStats stats) {
        if (stats == null)
            return false;
        if (flowerType != null && flowerType != stats.flowerType)
            return false;
        if (color != null && color != stats.color)
            return false;
        return true;
    }

    public boolean matching(FlowerPack pack) {
        return this.matching(pack.getStats());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof FlowerStats))
            return false;
        FlowerStats that = (FlowerStats) o;
        return color == that.color && flowerType == that.flowerType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, flowerType);
    }

    @Override
    public String toString() {
        return "FlowerStats{" +
                "color=" + color +
                ", flowerType=" + flowerType +
                '}';
    }
}
