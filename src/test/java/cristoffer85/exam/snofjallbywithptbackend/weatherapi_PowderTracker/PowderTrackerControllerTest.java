package cristoffer85.exam.snofjallbywithptbackend.weatherapi_PowderTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class PowderTrackerControllerTest {

    @InjectMocks
    private PowderTrackerController powderTrackerController;

    @Mock
    private PowderTrackerService powderTrackerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCurrentConditions() throws Exception {
        // Arrange
        String expectedResponse = "Current conditions...";
        when(powderTrackerService.getCurrentConditions()).thenReturn(expectedResponse);

        // Act
        String result = powderTrackerController.getCurrentConditions();

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse, result);
    }

    @Test
    public void testGetHourlyConditions() throws Exception {
        // Arrange
        String expectedResponse = "Hourly conditions...";
        when(powderTrackerService.getHourlyConditions()).thenReturn(expectedResponse);

        // Act
        String result = powderTrackerController.gethourlyConditions();

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse, result);
    }

    @Test
    public void testGet5DayConditions() throws Exception {
        // Arrange
        String expectedResponse = "5 day conditions...";
        when(powderTrackerService.get5DayConditions()).thenReturn(expectedResponse);

        // Act
        String result = powderTrackerController.get5DayConditions();

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse, result);
    }
}