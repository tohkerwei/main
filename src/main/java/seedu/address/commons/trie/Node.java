package seedu.address.commons.trie;

import java.util.HashMap;

/**
 * This class represents a single node of the trie data structure.
 */
public class Node {
    private char letter;
    private Node parent;
    private HashMap<Character, Node> children;
    private boolean isWordEnd;

    public Node() {
        children = new HashMap<>();
        isWordEnd = false;
        parent = null;
    }

    public Node(char letter, Node parent) {
        this.letter = letter;
        this.parent = parent;
        children = new HashMap<>();
        isWordEnd = false;
    }

    public Node getChild(char letter) {
        return children.get(letter);
    }

    public HashMap<Character, Node> getChildren() {
        return children;
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

    public String constructWord() {
        if (parent == null) {
            return "";
        }
        return parent.constructWord() + this.getLetter();
    }
}