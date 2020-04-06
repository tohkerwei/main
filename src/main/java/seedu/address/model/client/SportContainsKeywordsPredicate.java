package seedu.address.model.client;

import seedu.address.commons.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author tohkerwei
 * Tests that a {@code Client}'s {@code Name} matches any of the keywords given.
 */
public class SportContainsKeywordsPredicate implements Predicate<Client> {

    private final List<String> keywords;

    public SportContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public boolean test(Client client) {
        System.out.println(setToString(client.getSports()));
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(setToString(client.getSports()), keyword));
    }

    /**
     * Converts the sports of a client into a string
     * @param sportSet set of sports of a client
     * @return string containing sports of a client
     */
    public String setToString(Set<Sport> sportSet) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> sportArray = new ArrayList<>();
        for (Sport sport : sportSet) {
            sportArray.add(sport.sportName);
        }
        for (int i = 0; i < sportSet.size() ; i ++) {
            sb.append(sportArray.get(i));
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.client.SportContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((seedu.address.model.client.SportContainsKeywordsPredicate) other).keywords)); // state check
    }
}
