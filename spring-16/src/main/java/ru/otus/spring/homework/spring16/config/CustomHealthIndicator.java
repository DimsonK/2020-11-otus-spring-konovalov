package ru.otus.spring.homework.spring16.config;

import lombok.SneakyThrows;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @SneakyThrows
    @Override
    public Health health() {
        if (isReachable("http://localhost:8080/")) {
            return Health.up()
                    .status(Status.UP)
                    .withDetail("message", "Пока всё идет нормально!")
                    .build();
        } else {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Караул!")
                    .build();
        }
    }

    public boolean isReachable(String targetUrl) throws IOException {
        HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(targetUrl).openConnection();
        httpUrlConnection.setRequestMethod("HEAD");
        try {
            int responseCode = httpUrlConnection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (UnknownHostException noInternetConnection) {
            return false;
        }
    }
}
