package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.exercise.UniqueExerciseList;
import seedu.address.model.schedule.ScheduleList;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book. Guarantees: details are present and
 * not null, field values are validated, immutable.
 */
public class Client {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    // Start of new/optional data fields
    private final Birthday birthday;
    private final CurrentWeight currentWeight;
    private final Gender gender;
    private final TargetWeight targetWeight;
    private final Height height;
    private final Set<Sport> sports = new HashSet<>();
    private final Remark remark;
    private final UniqueExerciseList exerciseList;
    private final PersonalBest personalBest;
    private final ScheduleList scheduleList;

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Gender gender, Phone phone, Email email, Address address, Set<Tag> tags, Birthday birthday,
            CurrentWeight currentWeight, TargetWeight targetWeight, Height height, Remark remark, Set<Sport> sports,
            UniqueExerciseList exerciseList, PersonalBest personalBest, ScheduleList scheduleList) {
        requireAllNonNull(name, gender, phone, email, address, tags, birthday, currentWeight, targetWeight, height,
                remark, sports, exerciseList, personalBest, scheduleList);
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.birthday = birthday;
        this.currentWeight = currentWeight;
        this.targetWeight = targetWeight;
        this.height = height;
        this.sports.addAll(sports);
        this.remark = remark;
        this.exerciseList = exerciseList;
        this.personalBest = personalBest;
        this.scheduleList = scheduleList;
    }

    public Name getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Remark getRemark() {
        return remark;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public String getBirthdayString() {
        return birthday.toString();
    }

    public CurrentWeight getCurrentWeight() {
        return currentWeight;
    }

    public TargetWeight getTargetWeight() {
        return targetWeight;
    }

    public Height getHeight() {
        return height;
    }

    public ScheduleList getScheduleList() {
        return scheduleList;
    }

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable sport set, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Sport> getSports() {
        return Collections.unmodifiableSet(sports);
    }

    public String getSportsString() {
        return getSports().stream().map(sport -> sport.getSportName()).collect(Collectors.joining(", "));
    }

    public UniqueExerciseList getExerciseList() {
        return exerciseList;
    }

    public PersonalBest getPersonalBest() {
        return personalBest;
    }

    /**
     * Returns true if both clients of the same name have at least one other
     * identity field that is the same. This defines a weaker notion of equality
     * between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null && otherClient.getName().equals(getName())
                && (otherClient.getPhone().equals(getPhone()) || otherClient.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both clients have the same identity and data fields. This
     * defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        // TODO: add checks for new/optional attributes
        return otherClient.getName().equals(getName()) && otherClient.getPhone().equals(getPhone())
                && otherClient.getEmail().equals(getEmail()) && otherClient.getAddress().equals(getAddress())
                && otherClient.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, gender, phone, email, address, tags, birthday, currentWeight, targetWeight, height,
                remark, sports);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Gender: ").append(getGender())
                .append(" Phone: ").append(getPhone())
                .append(" Email: ").append(getEmail())
                .append(" Address: ").append(getAddress())
                .append(" Birthday: ").append(getBirthday())
                .append(" Current Weight: ").append(getCurrentWeight())
                .append(" Target Weight: ").append(getTargetWeight())
                .append(" Height: ").append(getHeight())
                .append(" Remark: ").append(getRemark())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        builder.append(" Sports: ");
        getSports().forEach(builder::append);
        return builder.toString();
    }

}
