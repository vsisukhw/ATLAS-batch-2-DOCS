package Day18;

import java.util.*;

class Node {
    int data;
    Node left, right;
    Node(int value) {
        data = value;
        left = right = null;
    }
}

public class Task10 {

    // Function to print corner nodes of each level
    static void printCornerNodes(Node root) {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int n = queue.size(); // number of nodes at current level

            for (int i = 0; i < n; i++) {
                Node current = queue.poll();

                // Check if it's the first or last node of this level
                if (i == 0 || i == n - 1) {
                    System.out.print(current.data + " ");
                }


                // Enqueue left and right children
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }
            System.out.println();
        }
    }

    // Sample usage
    public static void main(String[] args) {
        /*
              1
            /   \
           2     3
          / \   / \
         4   5 6   7
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println("Corner nodes of each level:");
        printCornerNodes(root);
    }
}
