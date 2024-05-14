package cristoffer85.exam.snofjallbywithptbackend.SKILIFT.service;

import cristoffer85.exam.snofjallbywithptbackend.SKILIFT.model.SkiLift;
import cristoffer85.exam.snofjallbywithptbackend.SKILIFT.model.SkiLiftOperations;
import cristoffer85.exam.snofjallbywithptbackend.SKILIFT.repository.SkiLiftRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SkiLiftServiceTest {

    @InjectMocks
    SkiLiftService skiLiftService;

    @Mock
    SkiLiftRepository skiLiftRepository;

    @Test
    void getAllLifts() {
        List<SkiLift> skiLifts = new ArrayList<>();
        skiLifts.add(new SkiLift());
        skiLifts.add(new SkiLift());

        when(skiLiftRepository.findAll()).thenReturn(skiLifts);

        List<SkiLift> result = skiLiftService.getAllLifts();

        assertEquals(skiLifts, result);
        verify(skiLiftRepository, times(1)).findAll();
    }

    @Test
    void startLift() {
        SkiLift skiLift = new SkiLift();
        skiLift.setOperating(false);
        skiLift.setOperations(new ArrayList<>());

        when(skiLiftRepository.findById(anyLong())).thenReturn(Optional.of(skiLift));
        when(skiLiftRepository.save(any(SkiLift.class))).thenReturn(skiLift);

        SkiLift result = skiLiftService.startLift(1L);

        assertTrue(result.isOperating());
        assertEquals(1, result.getOperations().size());
        assertNotNull(result.getOperations().get(0).getStartTime());
        verify(skiLiftRepository, times(1)).findById(anyLong());
        verify(skiLiftRepository, times(1)).save(any(SkiLift.class));
    }

    @Test
    void stopLift() {
        SkiLift skiLift = new SkiLift();
        skiLift.setOperating(true);
        skiLift.setOperations(new ArrayList<>());
        skiLift.getOperations().add(new SkiLiftOperations());

        when(skiLiftRepository.findById(anyLong())).thenReturn(Optional.of(skiLift));
        when(skiLiftRepository.save(any(SkiLift.class))).thenReturn(skiLift);

        SkiLift result = skiLiftService.stopLift(1L);

        assertFalse(result.isOperating());
        assertNotNull(result.getOperations().get(0).getEndTime());
        verify(skiLiftRepository, times(1)).findById(anyLong());
        verify(skiLiftRepository, times(1)).save(any(SkiLift.class));
    }
}