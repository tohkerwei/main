package seedu.address.commons.trie;

import java.util.ArrayList;

public class SimilarWordsResult {
    public final String longestPrefix;
    public final ArrayList<String> list;

    public SimilarWordsResult(String longestPrefix, ArrayList<String> list) {
        this.longestPrefix = longestPrefix;
        this.list = list;
    }
}