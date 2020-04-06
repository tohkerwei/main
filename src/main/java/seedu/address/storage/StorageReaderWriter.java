package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a utility class to handle reading from and writing to storage.
 */
public class StorageReaderWriter {

    private Path filePath;

    public StorageReaderWriter(Path filePath) {
        this.filePath = filePath;
        createNewFile();
    }

    /**
     * Creates an empty new file for future use if the file does not currently
     * exists.
     */
    private void createNewFile() {
        File file = filePath.toFile();

        if (file.exists()) {
            return;
        }

        file.getParentFile().mkdirs();

        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    /**
     * Returns a {@code List} of strings from the storage file located at
     * {@code filePath}, where each item in the list represents a new line.
     */
    public List<String> readFromStorage() {
        ArrayList<String> commands = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(filePath);

            String line = reader.readLine();
            while (line != null) {
                commands.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
        return commands;
    }

    /**
     * Overwrites (not append) the given {@code lines} to the storage file located
     * at {@code filePath}.
     *
     * @param lines cannot be null.
     */
    public void writeToStorage(List<String> lines) {
        requireNonNull(lines);
        try {
            BufferedWriter writer = Files.newBufferedWriter(filePath);

            for (String command : lines) {
                writer.write(command);
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    /**
     * Clears the storage file located at {@code filePath}.
     */
    public void clearStorage() {
        try {
            BufferedWriter writer = Files.newBufferedWriter(filePath);

            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
    }
}
