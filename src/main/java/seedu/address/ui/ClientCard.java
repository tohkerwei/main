package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.client.Client;

/**
 * An UI component that displays information of a {@code Client}.
 */
public class ClientCard extends UiPart<Region> {

    private static final String FXML = "ClientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Client client;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label gender;
    @FXML
    private Label birthday;
    @FXML
    private Label currentWeight;
    @FXML
    private Label targetWeight;
    @FXML
    private Label height;
    @FXML
    private Label remark;
    @FXML
    private FlowPane sports;
    @FXML
    private FlowPane tags;

    public ClientCard(Client client, int displayedIndex) {
        super(FXML);
        this.client = client;
        id.setText(displayedIndex + ". ");
        name.setText(client.getName().fullName);
        String phoneLabel = "Phone: ";
        String phoneAttributeForDisplay = getAttributeForDisplay(client.getPhone().value);
        String fullPhoneForDisplay = phoneLabel + phoneAttributeForDisplay;
        phone.setText(fullPhoneForDisplay);
        String addressLabel = "Address: ";
        String addressAttributeForDisplay = getAttributeForDisplay(client.getAddress().value);
        String fullAddressForDisplay = addressLabel + addressAttributeForDisplay;
        address.setText(fullAddressForDisplay);
        String emailLabel = "Email: ";
        String emailAttributeForDisplay = getAttributeForDisplay(client.getEmail().value);
        String fullEmailForDisplay = emailLabel + emailAttributeForDisplay;
        email.setText(fullEmailForDisplay);
        String genderLabel = "Gender: ";
        String genderAttributeForDisplay = getAttributeForDisplay(client.getGender().value);
        String fullGenderForDisplay = genderLabel + genderAttributeForDisplay;
        gender.setText(fullGenderForDisplay);
        String birthdayLabel = "Birthday: ";
        String birthdayAttributeForDisplay = getAttributeForDisplay(client.getBirthdayString());
        String fullBirthdayForDisplay = birthdayLabel + birthdayAttributeForDisplay;
        birthday.setText(fullBirthdayForDisplay);
        String heightLabel = "Height: ";
        String heightAttributeForDisplay = getAttributeForDisplay(client.getHeight().value);
        String fullHeightForDisplay = heightLabel + heightAttributeForDisplay;
        height.setText(fullHeightForDisplay);
        String currentWeightLabel = "Current Weight: ";
        String currentWeightAttributeForDisplay = getAttributeForDisplay(client.getCurrentWeight().value);
        String fullCurrentWeightForDisplay = currentWeightLabel + currentWeightAttributeForDisplay;
        currentWeight.setText(fullCurrentWeightForDisplay);
        String targetWeightLabel = "Target Weight: ";
        String targetWeightAttributeForDisplay = getAttributeForDisplay(client.getTargetWeight().value);
        String fullTargetWeightForDisplay = targetWeightLabel + targetWeightAttributeForDisplay;
        targetWeight.setText(fullTargetWeightForDisplay);
        String remarkLabel = "Remark: ";
        String remarkAttributeForDisplay = getAttributeForDisplay(client.getRemark().value);
        String fullRemarkForDisplay = remarkLabel + remarkAttributeForDisplay;
        remark.setText(fullRemarkForDisplay);
        client.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        client.getSports().stream()
            .sorted(Comparator.comparing(sport -> sport.sportName))
            .forEach(sport -> sports.getChildren().add(new Label(sport.sportName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientCard)) {
            return false;
        }

        // state check
        ClientCard card = (ClientCard) other;
        return id.getText().equals(card.id.getText())
                && client.equals(card.client);
    }

    private String getAttributeForDisplay(String string) {
        String emptyString = "";
        return !string.equals(emptyString) ? string : "-";
    }
}
