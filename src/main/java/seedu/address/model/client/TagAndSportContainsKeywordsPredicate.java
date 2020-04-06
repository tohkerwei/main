package seedu.address.model.client;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author tohkerwei
 * Tests that a {@code Client}'s {@code Name} matches any of the keywords given.
 */
public class TagAndSportContainsKeywordsPredicate implements Predicate<Client> {

    private final List<String> tagKeywords;
    private final List<String> sportKeywords;

    public TagAndSportContainsKeywordsPredicate(List<String> tagKeywords, List<String> sportKeywords) {

        this.tagKeywords = tagKeywords;
        this.sportKeywords = sportKeywords;
    }

    public boolean test(Client client) {
        boolean hasTag;
        boolean hasSport;
        if (tagKeywords.isEmpty()) {
            hasTag = true;
        } else {
            hasTag = tagKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(setTagToString(client.getTags()), keyword));
        }

        if (sportKeywords.isEmpty()) {
            hasSport = true;
        } else {
            hasSport = sportKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(setSportToString(client.getSports()), keyword));
        }

        return hasTag && hasSport;
    }

    /**
     * Converts the tags of a client into a string
     * @param tagsSet set of tags of a client
     * @return string containing tags of a client
     */
    public String setTagToString(Set<Tag> tagsSet) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> tagArray = new ArrayList<>();
        for (Tag tag : tagsSet) {
            tagArray.add(tag.tagName);
        }
        for (int i = 0; i < tagsSet.size() ; i ++) {
            sb.append(tagArray.get(i));
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * Converts the sports of a client into a string
     * @param sportSet set of sports of a client
     * @return string containing sports of a client
     */
    public String setSportToString(Set<Sport> sportSet) {
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
                || (other instanceof TagAndSportContainsKeywordsPredicate // instanceof handles nulls
                && tagKeywords.equals(((TagAndSportContainsKeywordsPredicate) other).tagKeywords)
                || sportKeywords.equals(((TagAndSportContainsKeywordsPredicate) other).sportKeywords)); // state check
    }
}
