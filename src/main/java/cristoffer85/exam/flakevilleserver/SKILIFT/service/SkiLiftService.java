package cristoffer85.exam.flakevilleserver.SKILIFT.service;

import cristoffer85.exam.flakevilleserver.SKILIFT.model.SkiLift;
import cristoffer85.exam.flakevilleserver.SKILIFT.model.SkiLiftOperations;
import cristoffer85.exam.flakevilleserver.SKILIFT.repository.SkiLiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SkiLiftService {

    @Autowired
    private SkiLiftRepository skiLiftRepository;

    public List<SkiLift> getAllLifts() {
        return skiLiftRepository.findAll();
    }

    public SkiLift startLift(Long id) {
        SkiLift skiLift = skiLiftRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("SkiLift not found with id " + id));
        skiLift.setOperating(true);
        SkiLiftOperations operation = new SkiLiftOperations();
        operation.setStartTime(LocalDateTime.now());
        if (skiLift.getOperations() == null) {
            skiLift.setOperations(new ArrayList<>());
        }
        skiLift.getOperations().add(operation);
        return skiLiftRepository.save(skiLift);
    }

    public SkiLift stopLift(Long id) {
        SkiLift skiLift = skiLiftRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("SkiLift not found with id " + id));
        skiLift.setOperating(false);
        SkiLiftOperations operation = skiLift.getOperations().get(skiLift.getOperations().size() - 1);
        operation.setEndTime(LocalDateTime.now());
        return skiLiftRepository.save(skiLift);
    }
}
