package cristoffer85.exam.snofjallbywithptbackend.weatherapi_PowderTracker;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/skiResort")
@CrossOrigin("*")
public class PowderTrackerController {

    private final PowderTrackerService powderTrackerService;

    public PowderTrackerController(PowderTrackerService powderTrackerService) {
        this.powderTrackerService = powderTrackerService;
    }

    @GetMapping("/currentConditions")
    public String getCurrentConditions() throws IOException {
        return powderTrackerService.getCurrentConditions();
    }

    @GetMapping("/hourlyConditions")
    public String gethourlyConditions() throws IOException {
        return powderTrackerService.getHourlyConditions();
    }

    @GetMapping("/5DayConditions")
    public String get5DayConditions() throws IOException {
        return powderTrackerService.get5DayConditions();
    }
}
