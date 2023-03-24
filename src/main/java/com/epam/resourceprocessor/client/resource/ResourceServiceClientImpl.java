package com.epam.resourceprocessor.client.resource;

import com.epam.resourceprocessor.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResourceServiceClientImpl implements ResourceServiceClient {

    private final RestTemplate restTemplate;
    private final static String URL = "http://localhost:8080/api/v1/resource-service/resources/{id}";

    @Override
    public Optional<File> downloadFile(Integer resourceId) {
        byte[] result = restTemplate.getForObject(URL, byte[].class, resourceId);

        Path path = Paths.get("src/main/resources/song.mp3");
        try  {
            Files.write(path, result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Optional.of(path.toFile());
    }
}
