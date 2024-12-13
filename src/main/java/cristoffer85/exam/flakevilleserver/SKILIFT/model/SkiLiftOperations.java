package cristoffer85.exam.flakevilleserver.SKILIFT.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SkiLiftOperations {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
