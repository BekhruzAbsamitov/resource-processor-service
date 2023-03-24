package com.epam.resourceprocessor.client.resource;

import java.io.File;
import java.util.Optional;

public interface ResourceServiceClient {

    Optional<File> downloadFile(Integer resourceId);

}
