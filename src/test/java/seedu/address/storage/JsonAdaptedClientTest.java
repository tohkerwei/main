// TODO: create tests for other attributes
package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedClient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Address;
// import seedu.address.model.client.Birthday;
// import seedu.address.model.client.CurrentWeight;
import seedu.address.model.client.Email;
// import seedu.address.model.client.Gender;
// import seedu.address.model.client.Height;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
// import seedu.address.model.client.Remark;
// import seedu.address.model.client.Sport;
// import seedu.address.model.client.TargetWeight;
// import seedu.address.model.tag.Tag;

public class JsonAdaptedClientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_BIRTHDAY = BENSON.getBirthday().toString();
    private static final String VALID_CURRENT_WEIGHT = BENSON.getCurrentWeight().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_TARGET_WEIGHT = BENSON.getTargetWeight().toString();
    private static final String VALID_HEIGHT = BENSON.getHeight().toString();
    private static final String VALID_REMARK = BENSON.getRemark().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream().map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedSport> VALID_SPORTS = BENSON.getSports().stream().map(JsonAdaptedSport::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validClientDetails_returnsClient() throws Exception {
        JsonAdaptedClient client = new JsonAdaptedClient(BENSON);
        assertEquals(BENSON, client.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(INVALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(null, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT, VALID_REMARK,
                VALID_SPORTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, INVALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT, VALID_REMARK,
                VALID_SPORTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, null, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT, VALID_REMARK,
                VALID_SPORTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, null,
                VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT, VALID_REMARK,
                VALID_SPORTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, invalidTags, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS);
        assertThrows(IllegalValueException.class, client::toModelType);
    }

}
