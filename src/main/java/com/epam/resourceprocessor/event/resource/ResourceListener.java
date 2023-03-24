package com.epam.resourceprocessor.event.resource;

import com.epam.resourceprocessor.client.resource.ResourceServiceClient;
import com.epam.resourceprocessor.client.song.SongServiceClient;
import com.epam.resourceprocessor.event.EventListener;
import com.epam.resourceprocessor.payload.SongID;
import com.epam.resourceprocessor.processor.ResourceProcessor;
import com.epam.resourceprocessor.processor.SongMetadata;
import com.epam.resourceprocessor.processor.SongProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResourceListener implements EventListener<Integer> {

    private final ResourceServiceClient resourceServiceClient;
    private final SongServiceClient songServiceClient;
    private final ResourceProcessor<SongMetadata> songMetadataResourceProcessor;

    @Override
    public void handleEvent(Integer id) {
        SongID songId = resourceServiceClient.downloadFile(id)
                .map(songMetadataResourceProcessor::process)
                .map(Optional::orElseThrow)
                .map(songServiceClient::createSong)
                .orElse(new SongID(-1));

        log.info("song created, id: {}", songId.id());
    }
}
