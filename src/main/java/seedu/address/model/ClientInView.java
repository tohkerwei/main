package seedu.address.model;

import seedu.address.model.client.Client;

public class ClientInView {

    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean hasClientInView() {
        return client != null;
    }
}