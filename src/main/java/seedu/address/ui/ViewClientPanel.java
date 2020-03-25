package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;

/**
 * Panel containing the list of clients.
 */
public class ViewClientPanel extends UiPart<Region> {
    private static final String FXML = "ViewClientPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ViewClientPanel.class);

    @FXML
    private ListView<Client> clientView;

    public ViewClientPanel(ObservableList<Client> clientList) {
        super(FXML);
        clientView.setItems(clientList);
        clientView.setCellFactory(listView -> new ViewClientCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Client} using
     * a {@code ClientCard}.
     */
    class ViewClientCell extends ListCell<Client> {
        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);

            if (empty || client == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ViewClientCard(client).getRoot());
            }
        }
    }

}
