package com.epam.resourceprocessor.processor;

import com.epam.resourceprocessor.exception.BadRequestException;
import com.mpatric.mp3agic.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class SongProcessor implements ResourceProcessor<SongMetadata> {
    @Override
    public Optional<SongMetadata> process(File file) {
        try {
            Mp3File mp3File = new Mp3File(file);
            return extractMetadata(mp3File);
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            log.error(e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }

    private Optional<SongMetadata> extractMetadata(Mp3File mp3File) {
        String duration = String.format("%d:%d",
                mp3File.getLengthInSeconds() / 60, mp3File.getLengthInSeconds() % 60);

        if (mp3File.hasId3v1Tag()) {
            ID3v1 tag = mp3File.getId3v1Tag();
            return Optional.of(new SongMetadata(
                    null, tag.getTrack(), tag.getArtist(),
                    tag.getAlbum(), duration, tag.getYear()
            ));
        }

        if (mp3File.hasId3v2Tag()) {
            ID3v2 tag = mp3File.getId3v2Tag();
            return Optional.of(new SongMetadata(
                    null, tag.getTrack(), tag.getArtist(),
                    tag.getAlbum(), duration, tag.getYear()
            ));
        }

        return Optional.of(new SongMetadata(
                null, "No name", "No artist", "No album", duration, ""
        ));
    }
}