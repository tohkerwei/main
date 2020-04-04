package seedu.address.logic.commands;

import java.io.IOException;

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
        try {
            new ProcessBuilder("x-www-browser", url).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CommandResult execute(Model model) {
        openUrlInDefaultWebBrowser(USER_GUIDE_URL);
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false);
    }
}
