package seedu.address.commons.trie;

import java.util.ArrayList;
import java.util.Stack;

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
                Node newChild = new Node(letter, current);
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
    private Node getLongestPrefixNode(String word) {
        Node current = root;

        // this loop should end prematurely if word is not a proper substring
        for (char letter : word.toCharArray()) {
            if (!current.hasChild(letter)) {
                return null;
            }
            current = current.getChild(letter);
        }

        while (current.hasSingleChild()) {
            current = current.getSingleChild();
        }

        return current;
    }

    public SimilarWordsResult listAllSimilarWords(String word) {
        Node longestPrefixNode = getLongestPrefixNode(word);

        ArrayList<String> list = new ArrayList<>();
        Node subtrie = longestPrefixNode;

        // case 1: longest prefix does not exist
        if (subtrie == null) {
            return new SimilarWordsResult(EMPTY_STRING, list);
        }

        // case 2: longest prefix is actually the completed word
        String longestPrefixString = longestPrefixNode.constructWord();
        if (subtrie.isWordEnd()) {
            list.add(longestPrefixString);
            return new SimilarWordsResult(longestPrefixString, list);
        }

        // case 3: longest prefix is not a completed word
        // dfs approach
        Stack<Node> stack = new Stack<>();
        stack.push(subtrie);
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (current.isWordEnd()) {
                list.add(current.constructWord());
            } else {
                stack.addAll(current.getChildren().values());
            }
        }

        return new SimilarWordsResult(longestPrefixString, list);
    }
}