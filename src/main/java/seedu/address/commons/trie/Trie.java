package seedu.address.commons.trie;

public class Trie {

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