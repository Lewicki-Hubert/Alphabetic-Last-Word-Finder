import java.io.*;
import java.util.Scanner;

class TreeNode {
    String label;
    TreeNode left;
    TreeNode right;

    public TreeNode(String label) {
        this.label = label;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    // Metoda do dodawania węzłów na podstawie ciągu liter
    public void addNode(String path, String label) {
        if (root == null) {
            root = new TreeNode(null);
            return;
        }
        TreeNode rootSave = root;

        for (int i = 0; i < path.length(); i++) {
            char direction = path.charAt(i);
            if (direction == 'L') {
                if (root.left == null) {
                    root.left = new TreeNode(null);
                }
                root = root.left;
            } else if (direction == 'R') {
                if (root.right == null) {
                    root.right = new TreeNode(null);
                }
                root = root.right;
            }
        }

        root.label = label;
        root = rootSave;

    }

    String result = "";

    // Metoda do znajdowania i wypisywania ostatniego słowa alfabetycznego
    private void findAllWords(TreeNode node, String currentWord) {
        if (node == null) return;
        currentWord = node.label + currentWord;
        if (node.left == null && node.right == null) {
            if (currentWord.compareTo(result) > 0) {
                result = currentWord;
            }
        }
        findAllWords(node.left, currentWord);
        findAllWords(node.right, currentWord);
    }

    // Metoda do znajdowania i wypisywania ostatniego słowa alfabetycznego
    public void printResult() {
        findAllWords(root, "");
        System.out.println(result);
    }

}

public class Program {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Main <input_file>");
            System.exit(1);
        }

        String inputFile = args[0];
        BinaryTree tree = new BinaryTree();

        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String label = parts[0];
                String path;
                if (parts.length == 2) {
                    path = parts[1];
                } else {
                    path = "";
                }

                tree.addNode(path, label);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        tree.printResult();
    }
}