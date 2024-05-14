package cristoffer85.exam.snofjallbywithptbackend.WEATHER.controller;

import cristoffer85.exam.snofjallbywithptbackend.WEATHER.service.PowderTrackerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/skiResort")
public class PowderTrackerController {

    private final PowderTrackerService powderTrackerService;

    public PowderTrackerController(PowderTrackerService powderTrackerService) {
        this.powderTrackerService = powderTrackerService;
    }

    @GetMapping("/currentConditions")
    public String getCurrentConditions() throws IOException {
        return powderTrackerService.getCurrentConditions();
    }

    @GetMapping("/5DayConditions")
    public String get5DayConditions() throws IOException {
        return powderTrackerService.get5DayConditions();
    }
}
