package com.fitness.FitnessApp.Service;

import com.fitness.FitnessApp.Model.Exercise;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExerciseService {

    private final WebClient webClient;

    @Value("${spring.datasource.apiKey}")
    private String apiKey;

    private final String url = "https://exercises-by-api-ninjas.p.rapidapi.com/v1/exercises";

    public ExerciseService(WebClient.Builder builder) {
        webClient  = builder.baseUrl(url).build();
    }

    public Exercise[] getExercises(String exName, String exType, String muscle, String difficulty) {

            return webClient
                    .get()
                    .uri("?name=" + exName + "&type=" + exType + "&muscle=" + muscle + "&difficulty=" + difficulty)
                    .accept(MediaType.APPLICATION_JSON)
                    .header("X-RapidAPI-Key",
                            apiKey)
                    .header("X-RapidAPI-Host",
                            "exercises-by-api-ninjas.p.rapidapi.com")
                    .retrieve()
                    .bodyToMono(Exercise[].class)
                    .block();
    }
}