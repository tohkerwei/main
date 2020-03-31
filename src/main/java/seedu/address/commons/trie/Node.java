package seedu.address.commons.trie;

import java.util.HashMap;

public class Node {
    private char letter;
    private HashMap<Character, Node> children;
    private boolean isWordEnd;

    public Node() {
        children = new HashMap<>();
        isWordEnd = false;
    }

    public Node(char letter) {
        this.letter = letter;
        children = new HashMap<>();
        isWordEnd = false;
    }

    public Node getChild(char letter) {
        return children.get(letter);
    }

    public boolean hasChild(char letter) {
        return children.containsKey(letter);
    }

    public boolean hasSingleChild() {
        return children.size() == 1;
    }

    /**
     * Returns the only child of this node. {@code children} must only have a single
     * child to start with.
     */
    public Node getSingleChild() {
        assert hasSingleChild();
        for (Node node : children.values()) {
            return node;
        }
        return null;
    }

    public void addChild(char letter, Node child) {
        children.put(letter, child);
    }

    public void setIsWordEnd(boolean bool) {
        isWordEnd = bool;
    }

    public boolean isWordEnd() {
        return isWordEnd;
    }

    public char getLetter() {
        return letter;
    }
}