package com.epam.resourceprocessor.processor;

import java.io.File;
import java.util.Optional;

public interface ResourceProcessor<T> {
    Optional<T> process(File file);
}
