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
 * Panel containing the schedule panel.
 */
public class SchedulePanel extends UiPart<Region> {
    private static final String FXML = "SchedulePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SchedulePanel.class);

    @FXML
    private ListView<Client> schedule;

    public SchedulePanel(ObservableList<Client> clientList) {
        super(FXML);
        schedule.setItems(clientList);
        schedule.setCellFactory(listView -> new ScheduleCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Client} using
     * a {@code ClientCard}.
     */
    class ScheduleCell extends ListCell<Client> {
        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);

            if (empty || client == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ScheduleCard(client).getRoot());
            }
        }
    }

}
