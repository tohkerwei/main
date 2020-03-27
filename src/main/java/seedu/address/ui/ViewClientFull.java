package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import seedu.address.model.client.Client;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ViewClientFull {

    private final ViewClient viewClient;

    public ViewClientFull(ViewClient viewClient){
        requireNonNull(viewClient);
        this.viewClient = viewClient;
    }

    public ViewClient getViewClient(){
        return viewClient;
    }

    public void updateViewClient(Client client) {
        viewClient.update(client);
    }
}
