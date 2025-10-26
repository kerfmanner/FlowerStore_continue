package ucu.lab7.flower;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FlowerService {
    private final FlowerRepository flowerRepository;

    public FlowerService(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    public List<Flower> getAllFlowers() {
        return flowerRepository.findAll();
    }

    public void addFlower(Flower flower) {
        flowerRepository.save(flower);
    }
}
