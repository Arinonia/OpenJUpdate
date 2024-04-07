package fr.arinonia.openjupdate.service;

import fr.arinonia.openjupdate.models.SetupCheck;
import fr.arinonia.openjupdate.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class SetupService {

    private final UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Value("${app.storage.directory}")
    private String storageDirectoryPath;


    public SetupService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<SetupCheck> performSetupChecks() {
        final List<SetupCheck> checks = new ArrayList<>();
        final boolean dbConnection = checkDatabaseConnection();
        final boolean filePermissions = checkFilePermissions(this.storageDirectoryPath);
        checks.add(new SetupCheck("Database Connection", dbConnection));
        checks.add(new SetupCheck("File Permissions", filePermissions));
        return checks;
    }

    private boolean checkFilePermissions(final String pathStr) {
        final Path path = Paths.get(pathStr).toAbsolutePath();
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (final IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        try {
            final Path tempFile = Files.createTempFile(path, "test-write", ".tmp");
            Files.deleteIfExists(tempFile);
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //I guess it's useless because you should get an error if you run the project without a database
    private boolean checkDatabaseConnection() {
        try {
            this.entityManager.createNativeQuery("SELECT 1").getSingleResult();
            return true;
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isCmsSetUp() {
        try {
            return this.userRepository.count() > 0;
        } catch (final Exception ignored) {
            return false;
        }
    }
}
