package cristoffer85.exam.flakevilleserver.SKILIFT.controller;

import cristoffer85.exam.flakevilleserver.SKILIFT.model.SkiLift;
import cristoffer85.exam.flakevilleserver.SKILIFT.service.SkiLiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skilifts")
public class SkiLiftController {

    @Autowired
    private SkiLiftService skiLiftService;

    @GetMapping("/getAllLifts")
    public List<SkiLift> getAllLifts() {
        return skiLiftService.getAllLifts();
    }

    @PostMapping("/startLift/{id}")
    public SkiLift startLift(@PathVariable Long id) {
        return skiLiftService.startLift(id);
    }

    @PostMapping("/stopLift/{id}")
    public SkiLift stopLift(@PathVariable Long id) {
        return skiLiftService.stopLift(id);
    }
}
