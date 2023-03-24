package com.epam.resourceprocessor;

import com.epam.resourceprocessor.event.resource.ResourceListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ResourceProcessorServiceApplication {

    @Autowired
    ResourceListener resourceListener;

    public static void main(String[] args) {
        SpringApplication.run(ResourceProcessorServiceApplication.class, args);
    }

    @GetMapping("/song-id/{id}")
    public void handleEvent(@PathVariable Integer id) {
        resourceListener.handleEvent(id);
    }
}
