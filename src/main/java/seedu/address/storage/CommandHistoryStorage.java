package seedu.address.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This represents the storage for the command history.
 */
public class CommandHistoryStorage {

    private Path commandHistoryPath = Paths.get("data", "command.txt");

    /**
     * Creates an empty new file for use in this class later.
     */
    private void createNewFile() {
        File file = commandHistoryPath.toFile();
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    /**
     * Returns an ArrayList of each command string from storage.
     */
    public ArrayList<String> readCommandHistory() {
        ArrayList<String> commands = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(commandHistoryPath);

            String line = reader.readLine();
            while (line != null) {
                commands.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            // create new file
            createNewFile();
        }
        return commands;
    }

    /**
     * Saves the given command history to the storage.
     *
     * @param history cannot be null.
     */
    public void saveCommandHistory(ArrayList<String> history) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(commandHistoryPath);

            for (String command : history) {
                writer.write(command);
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
    }
}
