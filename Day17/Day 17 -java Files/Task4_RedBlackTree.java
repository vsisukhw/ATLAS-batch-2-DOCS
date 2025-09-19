package Day_17;

class Node1 {
    int data;
    Node1 left, right, parent;
    boolean isRed;

    Node1(int data) {
        this.data = data;
        this.isRed = true; // new node is red
    }
}

public class Task4_RedBlackTree {

    private Node1 root;

    public void insert(int data) {
        Node1 newNode = new Node1(data);
        root = bstInsert(root, newNode);
        fixTree(newNode);
    }

    private Node1 bstInsert(Node1 root, Node1 node) {
        if (root == null) return node;

        if (node.data < root.data) {
            root.left = bstInsert(root.left, node);
            root.left.parent = root;
        } else if (node.data > root.data) {
            root.right = bstInsert(root.right, node);
            root.right.parent = root;
        }

        return root;
    }

    private void fixTree(Node1 node) {
        while (node != root && node.parent.isRed) {
            Node1 parent = node.parent;
            Node1 grandparent = parent.parent;

            if (parent == grandparent.left) {
                Node1 uncle = grandparent.right;

                if (uncle != null && uncle.isRed) {
                    // Case 3: Parent and Uncle are red
                    parent.isRed = false;
                    uncle.isRed = false;
                    grandparent.isRed = true;
                    node = grandparent;
                } else {
                    if (node == parent.right) {
                        // Left-Right case
                        node = parent;
                        rotateLeft(node);
                    }
                    // Left-Left case
                    parent.isRed = false;
                    grandparent.isRed = true;
                    rotateRight(grandparent);
                }
            } else {
                Node1 uncle = grandparent.left;

                if (uncle != null && uncle.isRed) {
                    parent.isRed = false;
                    uncle.isRed = false;
                    grandparent.isRed = true;
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        node = parent;
                        rotateRight(node);
                    }
                    parent.isRed = false;
                    grandparent.isRed = true;
                    rotateLeft(grandparent);
                }
            }
        }
        root.isRed = false;
    }

    private void rotateLeft(Node1 x) {
        Node1 y = x.right;
        x.right = y.left;
        if (y.left != null) y.left.parent = x;
        y.parent = x.parent;

        if (x.parent == null)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    private void rotateRight(Node1 x) {
        Node1 y = x.left;
        x.left = y.right;
        if (y.right != null) y.right.parent = x;
        y.parent = x.parent;

        if (x.parent == null)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;

        y.right = x;
        x.parent = y;
    }

    public void inorder() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(Node1 node) {
        if (node != null) {
            inorderHelper(node.left);
            System.out.print(node.data + (node.isRed ? "R " : "B "));
            inorderHelper(node.right);
        }
    }

    public static void main(String[] args) {
        Task4_RedBlackTree tree = new Task4_RedBlackTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(15);
        tree.insert(25);
        tree.inorder();
    }
}
