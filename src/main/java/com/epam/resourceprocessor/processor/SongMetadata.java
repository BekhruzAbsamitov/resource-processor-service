package com.epam.resourceprocessor.processor;

public record SongMetadata(
        Integer resourceId,
        String name,
        String artist,
        String album,
        String length,
        String year
) {
}
