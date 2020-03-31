package seedu.address.commons.trie;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class represents the trie data structure.
 */
public class Trie {

    private final static class LongestPrefixResult {
        public final String word;
        public final Node node;

        public LongestPrefixResult(String word, Node node) {
            this.word = word;
            this.node = node;
        }
    }

    private static final String EMPTY_STRING = "";

    private Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node current = root;

        for (char letter : word.toCharArray()) {
            if (current.hasChild(letter)) {
                current = current.getChild(letter);
            } else {
                Node newChild = new Node(letter);
                current.addChild(letter, newChild);
                current = newChild;
            }
        }

        current.setIsWordEnd(true);
    }

    /**
     * Returns the longest prefix of the argument {@code word}, or an empty string
     * if no such prefix can be found.
     *
     * @param word String to search for
     * @return the longest prefix or an empty string if it does not exist
     */
    private LongestPrefixResult getLongestPrefix(String word) {
        String longestPrefix = EMPTY_STRING;

        Node current = root;

        // this loop should end prematurely if word is not a proper substring
        for (char letter : word.toCharArray()) {
            if (!current.hasChild(letter)) {
                return new LongestPrefixResult(EMPTY_STRING, null);
            }
            current = current.getChild(letter);
            longestPrefix += current.getLetter();
        }

        while (current.hasSingleChild()) {
            current = current.getSingleChild();
            longestPrefix += current.getLetter();
        }

        return new LongestPrefixResult(longestPrefix, current);
    }

    public SimilarWordsResult listAllSimilarWords(String word) {
        LongestPrefixResult longestPrefix = getLongestPrefix(word);

        ArrayList<String> list = new ArrayList<>();
        Node subtrie = longestPrefix.node;

        // case 1: longest prefix does not exist
        if (subtrie == null) {
            return new SimilarWordsResult(longestPrefix.word, list);
        }

        // case 2: longest prefix is actually a completed word
        if (subtrie.isWordEnd()) {
            list.add(longestPrefix.word);
            return new SimilarWordsResult(longestPrefix.word, list);
        }

        // case 3: longest prefix is not a completed word
        // dfs approach
        Stack<Node> stack = new Stack<>();
        stack.addAll(subtrie.getChildren().values());
        String currWord = longestPrefix.word;
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            currWord += curr.getLetter();
            if (curr.isWordEnd()) {
                list.add(currWord);
                currWord = longestPrefix.word; // reset currWord
            } else {
                stack.addAll(curr.getChildren().values());
            }
        }

        return new SimilarWordsResult(longestPrefix.word, list);
    }
}