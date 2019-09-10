public class BinarySearchTree{
    Node root;

    private static class Node{
        int data;
        Node left;
        Node right;
        //constructor for a Node in the BST
        private Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    //constructor for a BST
    public BinarySearchTree(Node root){
        this.root = root;
    }

    //Basic Utility Functions
    public void printTree(Node root){
        if(root == null){
            return;
        }
        Node current = root;
        System.out.println(current.data+"->");
        if(current.left != null){
            this.printTree(current.left);
        }else {
            return;
        }
        
        if(current.right != null){
            this.printTree(current.right);
        }else{
            return;
        }
    }
    
    
    //Binary Tree Problems
    public void build123(){
        if(this.root == null){
            this.root = new Node(2);
        }else{
            return;
        }

        if(this.root.left == null){
            this.root.left = new Node(1);
        }

        if(this.root.right == null){
            this.root.right = new Node(3);
        }
    }

    public static void main(String[] args){
        BinarySearchTree bst = new BinarySearchTree(null); 
        bst.build123();
        bst.printTree(bst.root);
    }
}