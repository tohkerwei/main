package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.address.model.exercise.Exercise;

/**
 * An UI component that displays information of a {@code Client}.
 */
public class ExerciseListTable extends UiPart<Region> {

    private static final String FXML = "ExerciseListTable.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX. As a consequence, UI elements' variable names cannot be
     * set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    @FXML
    public TableView<Exercise> tableView;
    @FXML
    private TableColumn<Exercise, String> exerciseName;
    @FXML
    private TableColumn<Exercise, String> exerciseDate;

    public ExerciseListTable(ObservableList<Exercise> e) {
        super(FXML);
        exerciseName.setCellValueFactory(new PropertyValueFactory<Exercise, String>("exerciseName"));
        exerciseDate.setCellValueFactory(new PropertyValueFactory<Exercise, String>("exerciseDate"));
        tableView.setItems(e);
    }
}
