package ucu.lab7.flower;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class FlowerRepository {
    private final List<Flower> flowers = new ArrayList<>();

    public FlowerRepository() {
        flowers.add(new Flower(12, 25.1, FlowerColor.RED, FlowerType.CHAMOMILE));
        flowers.add(new Flower(10, 30.5, FlowerColor.WHITE, FlowerType.ROSE));
    }

    public List<Flower> findAll() {
        return new ArrayList<>(flowers);
    }

    public void save(Flower flower) {
        flowers.add(flower);
    }
}
