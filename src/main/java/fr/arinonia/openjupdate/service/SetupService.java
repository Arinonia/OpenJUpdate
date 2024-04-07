package fr.arinonia.openjupdate.service;

import fr.arinonia.openjupdate.models.SetupCheck;
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

    @Value("${app.storage.directory}")
    private String storageDirectoryPath;

    public List<SetupCheck> performSetupChecks() {
        final List<SetupCheck> checks = new ArrayList<>();
        final boolean dbConnection = checkDatabaseConnection();
        final boolean filePermissions = checkFilePermissions(this.storageDirectoryPath);
        final boolean externalAPI = checkExternalAPIAccess();
        checks.add(new SetupCheck("Database Connection", dbConnection));
        checks.add(new SetupCheck("File Permissions", filePermissions));
        checks.add(new SetupCheck("External API Access", externalAPI));
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

    private boolean checkDatabaseConnection() {
        return true;
    }

    private boolean checkExternalAPIAccess() {
        return false;
    }

    public boolean isCmsSetUp() {
        return false;
    }
}
