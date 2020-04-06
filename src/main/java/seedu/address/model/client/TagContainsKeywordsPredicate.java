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
public class TagContainsKeywordsPredicate implements Predicate<Client> {

    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public boolean test(Client client) {
        System.out.println(setToString(client.getTags()));
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(setToString(client.getTags()), keyword));
    }

    /**
     * Converts the tags of a client into a string
     * @param tagsSet set of tags of a client
     * @return string containing tags of a client
     */
    public String setToString(Set<Tag> tagsSet) {
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.client.TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((seedu.address.model.client.TagContainsKeywordsPredicate) other).keywords)); // state check
    }
}
