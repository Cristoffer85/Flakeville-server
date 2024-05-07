package cristoffer85.exam.snofjallbywithptbackend.SKILIFT.controller;

import cristoffer85.exam.snofjallbywithptbackend.SKILIFT.model.SkiLift;
import cristoffer85.exam.snofjallbywithptbackend.SKILIFT.service.SkiLiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skilifts")
@CrossOrigin("*")
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
