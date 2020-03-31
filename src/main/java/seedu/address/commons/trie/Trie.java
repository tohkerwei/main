package seedu.address.commons.trie;

/**
 * This class represents the trie data structure.
 */
public class Trie {

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
    public String getLongestPrefix(String word) {
        String longestPrefix = EMPTY_STRING;

        Node current = root;

        // this loop should end prematurely if word is not a proper substring
        for (char letter : word.toCharArray()) {
            if (!current.hasChild(letter)) {
                return EMPTY_STRING;
            }
            current = current.getChild(letter);
            longestPrefix += current.getLetter();
        }

        while (current.hasSingleChild()) {
            current = current.getSingleChild();
            longestPrefix += current.getLetter();
        }

        return longestPrefix;
    }

    public boolean find(String word) {
        Node current = root;

        for (char letter : word.toCharArray()) {
            if (!current.hasChild(letter)) {
                return false;
            }
            current = current.getChild(letter);
        }

        return current.isWordEnd();
    }
}