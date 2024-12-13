package cristoffer85.exam.snofjallbywithptbackend.WEATHER.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PowderTrackerService {

    private final OkHttpClient client;

    public PowderTrackerService() {
        this.client = new OkHttpClient();
    }

    public String getCurrentConditions() throws IOException {
        Request request = new Request.Builder()
                .url("https://ski-resort-forecast.p.rapidapi.com/Salen/snowConditions?units=m")
                .get()
                .addHeader("X-RapidAPI-Key", "71467c3251mshe7a05d529b2b97bp1973c1jsn4f20d53a2d86")
                .addHeader("X-RapidAPI-Host", "ski-resort-forecast.p.rapidapi.com")
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        }
    }

    public String get5DayConditions() throws IOException {
        String apiKey = "794f149884fe6112fc95454ea8bda6b3";
        double lat = 13.27;
        double lon = 61.16;
        String units = "metric";
        String url = String.format("https://api.openweathermap.org/data/2.5/forecast?lat=%f&lon=%f&appid=%s&units=%s", lat, lon, apiKey, units);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        }
    }
}