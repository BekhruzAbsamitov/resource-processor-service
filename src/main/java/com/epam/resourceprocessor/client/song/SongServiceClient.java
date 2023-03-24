package com.epam.resourceprocessor.client.song;

import com.epam.resourceprocessor.payload.SongID;
import com.epam.resourceprocessor.processor.SongMetadata;

public interface SongServiceClient {

    SongID createSong(SongMetadata songMetadata);
}
