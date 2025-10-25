package ucu.lab7.flower;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flower")
public class FlowerController {
	@GetMapping("/")
	public List<Flower> hello() {
		List<Flower> lst = new ArrayList<>();
		lst.add(new Flower(12, 25.1, FlowerColor.RED, FlowerType.CHAMOMILE));
		return lst;
	}
}
