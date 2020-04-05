package seedu.address.logic.commands;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String USER_GUIDE_URL = "https://ay1920s2-cs2103t-f11-2.github.io/main/UserGuide.html";

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "You should have been redirected to the FitBiz user guide"
            + " website. If not, please visit the following URL:\n"
            + USER_GUIDE_URL;

    /**
     * Opens the provided {@code url} in the user's default web browser.
     *
     * @param url website url to open
     */
    public static void openUrlInDefaultWebBrowser(String url) {
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);

        switch (os) {
        case "win": // windows
        case "mac": // macOS
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            break;
        case "lin": // linux
        case "uni": // unix
            try {
                new ProcessBuilder("x-www-browser", url).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        default:
            break;
        }
    }

    @Override
    public CommandResult execute(Model model) {
        openUrlInDefaultWebBrowser(USER_GUIDE_URL);
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false);
    }
}
