package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import seedu.address.model.client.Client;

/**
 * A ui for which shows both detailed client details and exercises done by client.
 */
public class ClientViewDisplay {

    private final ClientView clientView;

    public ClientViewDisplay(ClientView clientView){
        requireNonNull(clientView);
        this.clientView = clientView;
    }

    public ClientView getClientView(){
        return clientView;
    }

    public void updateClientView(Client client) {
        clientView.update(client);
    }
}
