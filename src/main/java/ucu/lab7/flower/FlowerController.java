package ucu.lab7.flower;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@RestController
@RequestMapping("/flower")
public class FlowerController {
    private final FlowerService flowerService;

    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "Spring dependency injection is safe — FlowerService is managed by Spring and immutable in context"
    )
    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping("/")
    public List<Flower> getFlowers() {
        return flowerService.getAllFlowers();
    }
}
