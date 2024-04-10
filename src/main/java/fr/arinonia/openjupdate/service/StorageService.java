package fr.arinonia.openjupdate.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    private Path storageDirectory;
    private Path jobsDirectory;


    @Value("${app.storage.directory}")
    private String storageDir;

    @PostConstruct
    public void init() {
        String userHome = System.getProperty("user.home");
        this.storageDirectory = Paths.get(userHome, storageDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.storageDirectory);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage directory", e);
        }
    }

    public Path createJobDirectory(final String jobName) throws IOException {
        final String normalizedJobName = jobName.replaceAll("[^a-zA-Z0-9.-]", "_");
        final Path jobDirectory = this.jobsDirectory.resolve(normalizedJobName);
        Files.createDirectories(jobDirectory);
        return jobDirectory;
    }
}