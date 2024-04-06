package cristoffer85.exam.snofjallbywithptbackend.weatherapi_PowderTracker;

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
                .url("https://ski-resort-forecast.p.rapidapi.com/Lindvallen/snowConditions?units=m")
                .get()
                .addHeader("X-RapidAPI-Key", "71467c3251mshe7a05d529b2b97bp1973c1jsn4f20d53a2d86")
                .addHeader("X-RapidAPI-Host", "ski-resort-forecast.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }

    public String getHourlyConditions() throws IOException {
        Request request = new Request.Builder()
                .url("https://ski-resort-forecast.p.rapidapi.com/Lindvallen/hourly?units=m&el=top&c=true")
                .get()
                .addHeader("X-RapidAPI-Key", "71467c3251mshe7a05d529b2b97bp1973c1jsn4f20d53a2d86")
                .addHeader("X-RapidAPI-Host", "ski-resort-forecast.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }

    public String get5DayConditions() throws IOException {
        Request request = new Request.Builder()
                .url("https://ski-resort-forecast.p.rapidapi.com/Lindvallen/forecast?units=m&el=top")
                .get()
                .addHeader("X-RapidAPI-Key", "71467c3251mshe7a05d529b2b97bp1973c1jsn4f20d53a2d86")
                .addHeader("X-RapidAPI-Host", "ski-resort-forecast.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }
}