class TreeNode{
    int data;
    TreeNode left, right;

    TreeNode(int a)
    {
        data=a;
        left=null;
        right=null;
    }
}
class TreeOperation
{
    static TreeNode insert(TreeNode root,int a)
    {
        TreeNode tnode = new TreeNode(a);
        if(root==null)
        {
            return tnode;
        }
        else if(root.data<a)
        {
            root.right=insert(root.right,a);
        }
        else
        {
            root.left=insert(root.left,a);
        }
        return root;
    }
    static void display(TreeNode root)
    {
        if(root==null)
        {
            return;
        }
        display(root.left);
        System.out.print(root.data + " ");
        display(root.right);
    }
}
class BinarySearchTreeOp02 {
    TreeNode root;

    public BinarySearchTreeOp02() {
        this.root = null;
    }

    public void search(int key) {
        TreeNode current = root;
        while (current != null) {		//	key 30    current 50 == root
            if (key == current.data) {
                System.out.println("\n"+key +" found....");
                return;
            } else if (key < current.data) {		//	key 80    current 50 == root
                current = current.left;
            } else {
                current = current.right;
            }
        }

    }
}
public class Task6 {
    public static void main(String[] args)
    {
        TreeNode obj = null;
        obj= TreeOperation.insert(obj,1222);
        obj=TreeOperation.insert(obj,21);
        obj=TreeOperation.insert(obj,322);
        obj=TreeOperation.insert(obj,41);
        obj=TreeOperation.insert(obj,5);

        TreeOperation.display(obj);

        BinarySearchTreeOp02 obj1= new BinarySearchTreeOp02();
        obj1.root=obj;

       obj1.search(322);


    }
}




