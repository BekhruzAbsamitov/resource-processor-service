package com.epam.resourceprocessor.client.song;

import com.epam.resourceprocessor.payload.SongID;
import com.epam.resourceprocessor.processor.SongMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class SongServiceClientImpl implements SongServiceClient {

    private final static String URL = "http://localhost:8081/api/v1/song/songs";
    private final RestTemplate restTemplate;

    @Override
    public SongID createSong(SongMetadata songMetadata) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SongMetadata> request = new HttpEntity<>(songMetadata, headers);
        return restTemplate.postForObject(URL, request, SongID.class);
    }
}
