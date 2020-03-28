package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.exercise.Exercise;

/**
 * Panel containing the schedule panel.
 */
public class ExerciseListPanel extends UiPart<Region> {
    private static final String FXML = "ExerciseListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExerciseListPanel.class);

    @FXML
    private ListView<Exercise> exerciseListView;

    public ExerciseListPanel() {
        super(FXML);
    }

    public void update(ObservableList<Exercise> exerciseList){
        exerciseListView.setItems(exerciseList);
        exerciseListView.setCellFactory(listView -> new ExerciseListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Client} using
     * a {@code ClientCard}.
     */
    class ExerciseListViewCell extends ListCell<Exercise> {
        @Override
        protected void updateItem(Exercise exercise, boolean empty) {
            super.updateItem(exercise, empty);

            if (empty || exercise == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExerciseCard(exercise, getIndex() + 1).getRoot());
            }
        }
    }

}
