package cristoffer85.exam.snofjallbywithptbackend.WEATHER.service;

import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PowderTrackerServiceTest {

    @InjectMocks
    PowderTrackerService powderTrackerService;

    @Mock
    OkHttpClient client;

    @Mock
    Call call;

    @Mock
    Response response;

    @Mock
    ResponseBody responseBody;

    @Test
    void getCurrentConditions() throws IOException {
        lenient().when(client.newCall(any(Request.class))).thenReturn(call);
        lenient().when(call.execute()).thenReturn(response);
        lenient().when(response.body()).thenReturn(responseBody);
        lenient().when(responseBody.string()).thenReturn("test response");

        String result = powderTrackerService.getCurrentConditions();

        assertNotNull(result);
    }

    @Test
    void get5DayConditions() throws IOException {
        lenient().when(client.newCall(any(Request.class))).thenReturn(call);
        lenient().when(call.execute()).thenReturn(response);
        lenient().when(response.body()).thenReturn(responseBody);
        lenient().when(responseBody.string()).thenReturn("test response");

        String result = powderTrackerService.get5DayConditions();

        assertNotNull(result);
    }
}