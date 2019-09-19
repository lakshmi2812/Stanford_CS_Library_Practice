import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

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

    //printTree() -> prints nodes in the tree by successively printing each level of the tree
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

    //insert() -> insert a new Node with the given data into the BST
    public void insert(int data){
        // Node newNode = new Node(data);
        // if(root == null){
        //     root = newNode;
        //     return;
        // }
        // Node current = root;
        // if(data <= current.data){
        //     insert(current.left, data);
        // }else{
        //     insert(current.right, data);
        // }
        Node newNode = new Node(data);
        if(this.root == null){
            this.root = newNode;
            return;
        }

        Node current = this.root;
        while(current != null){
            if(data <= current.data){
                if(current.left == null){
                    current.left = newNode;
                    return;
                }else{
                    current = current.left;
                }
            }
            if(data > current.data){
                if(current.right == null){
                    current.right = newNode;
                    return;
                }else{
                    current = current.right;
                }
            }
        }
        current = new Node(data);
    }

    //lookup() -> look for a node containing the given data in the BST. Return true if a node with the given data exists and false otherwise
    
    
    //********/ Binary Tree Problems /**********/

    //1.build123() -> Build a linkedlist with nodes 1,2,3
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

    //2.size() -> return the number of nodes in the BST
    public int size(Node root){
        if(root == null){
            return 0;
        }
        int count = 1;
        Node current = root;
        if(current.right != null){
            count += this.size(current.right);
        }
        if(current.left != null){
            count += this.size(current.left);
        }
        return count;
    }

    //3.maxDepth() -> return the maximum depth(#oflevels) of the BST as an integer
    public int maxDepth(Node root){
        if(this.root == null){
            return 0;
        }
        int depth = 1;
        Node current = root;
        if(current.left != null || current.right != null){
            depth += Math.max(this.maxDepth(current.left), this.maxDepth(root.right));
        }
        return depth;
    }

    //4.minValue() -> return the minimum value present in the BST
    public int minValue(Node root){
        if(root == null){
            return 0;
        }

        Node current = root;
        int minVal = current.data;
        while(current.left != null){
            current = current.left;
        }
        return current.data;
    }

    //helper function for isBST()
    public int maxValue(Node root){
        if(root == null){
            return 0;
        }

        Node current = root;
        int maxVal = current.data;
        while(current.right != null){
            current = current.right;
        }
        return current.data;
    }

    //5.printTreeInorder() is called printTree() in the pdf. This method prints the node sof the BST in the increasing order
    //left,node and right nodes in that order
    public void printTreeInorder(Node root){
        if(root == null){
            return;
        }
        Node current = root;
        if(current.left != null){
            printTreeInorder(current.left);
        }
        System.out.println(" "+current.data);
        if(current.right != null){
            printTreeInorder(current.right);
        }
    }

    //6.printPostorder() -> prints the values of the left and right nodes of a node before printing its own value
    public void printPostorder(Node root){
        if(root == null){
            return;
        }
        Node current = root;
        if(current.left != null){
            printTreeInorder(current.left);
        }
        if(current.right != null){
            printTreeInorder(current.right);
        }
        System.out.println(" "+current.data);
    }

    //7.hasPathSum() -> if there is a path in the BST with the given sum, return true, else return false
    public boolean hasPathSum(Node root, int inputSum){
        if(root == null){
            return (inputSum == 0);
        }else{
            int subSum = inputSum - root.data;
            return(hasPathSum(root.left,subSum) || hasPathSum(root.right, subSum));
        }
    }

    //helper function to display data in the arraylist(helper for printPaths)
    public void display(ArrayList al){
        for(int i = 0; i < al.size(); i++){
            System.out.println(al.get(i) + " ");
        }
    }

    //recursive helper function for printPaths
    public void printPathsRecur(Node root, ArrayList path){
        if(root == null){
            return;
        }

        //add the root to the arraylist
        path.add(root.data);
        if(root.left != null){
            printPathsRecur(root.left, path);
        }else{
            //root.left is null
            if(root.right == null){
                //root.right is also null
                //its a leaf node. so, print the path
                this.display(path);
                System.out.println("");
                path.remove(path.size()-1);
                return;
            }
        }

        if(root.right != null){
            printPathsRecur(root.right, path);
        }else{
            //root.right is null
            if(root.left == null)
            //if root.left is also null
            //its a leaf node. so, print the path
            this.display(path);
            System.out.println("");
            path.remove(path.size()-1);
            return;
        }
    }

    //8.print all the paths from the root node to leaf node for the given BST
    public void printPaths(Node root){
        ArrayList<Integer> al = new ArrayList<Integer>();
        //call recursive helper function printPathsRecur
        printPathsRecur(root, al);
    }   

    //9.mirror() -> create a mirror image of the current tree(by swapping the left and right nodes for each node)
    public void mirror(Node root){
        if(root == null){
            return;
        }

        Node current = root;
        Node temp = current.left;
        current.left = current.right;
        current.right = temp;
        if(current.left != null){
            mirror(current.left);
        }
        if(current.right != null){
            mirror(current.right);
        }
    }

    //10.doubleTree() -> create a duplicate of each node and place it to its left
    public void doubleTree(Node root){
        if(root == null){
            return;
        }
        Node current = root;
        Node newNode = new Node(current.data);
        if(current.left != null){
            Node temp = current.left;
            current.left = newNode;
            current.left.left = temp;
            doubleTree(current.left.left);
        }else{
            current.left = newNode;
        }
       doubleTree(current.right);
    }

    //11.sameTree() -> given two trees, find out if they are identical(same structure and same data in all nodes)
    public boolean sameTree(Node root1, Node root2){
        //base case
        if(root1 == null && root2 == null){
            return true;
        }

        if(root1 != null && root2 != null){
            if(root1.data == root2.data){
                return (sameTree(root1.left, root2.left) && sameTree(root1.right, root2.right));
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    //12.countTrees() -> given a positive integer numKeys, count the number of structurally unique BSTs 
    //that can be formed with the keys 1...numKeys
    public int countTrees(int numKeys){
        //base case
        if(numKeys <= 1){
            return 1;
        }
        int sum = 0; int left; int right;
        //'i' represents the current root of the BST
        for(int i = 1; i <= numKeys; i++){
            int leftNum = i - 1;
            left = countTrees(leftNum);
            right = countTrees(numKeys - leftNum - 1);
            sum += left * right;
        }
        return sum;
    }

    //Dynamic Programming solution for countTrees()
    public int countTreesDynamic(int numKeys){
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        //base case
        if(numKeys <= 1){
            return 1;
        }

        int sum = 0; int left; int right;

        for(int i = 1; i <= numKeys; i++){
            int leftNum = i - 1;
            if(hm.containsKey(leftNum)){
                left = hm.get(leftNum);
            }else{
                left = countTrees(leftNum);
                hm.put(leftNum, left);
            }

            int rightNum = numKeys - leftNum - 1;
            if(hm.containsKey(rightNum)){
                right = hm.get(rightNum);
            }else{
                right = countTrees(rightNum);
                hm.put(rightNum, right);
            }
            sum += left * right;
        }

        return sum;
    }


    //13.isBST() -> given a binary tree, return true if it is a Binary Search Tree and false otherwise
    //A Binary Tree is said to be a Binary Search Tree if all the nodes to the left of the root are less than
    //the root and all the nodes to the right of the tree are greater than the root

    public boolean isBST(Node root){
        if(root == null){
            return true;
        }

        if(root.left != null && maxValue(root.left) > root.data){
            return false;
        }

        if(root.right != null && minValue(root.right) <= root.data){
            return false;
        }

        if(!isBST(root.left) || !isBST(root.right)){
            return false;
        }

        return true;
    }


    public static void main(String[] args){
        BinarySearchTree bst = new BinarySearchTree(null); 
        BinarySearchTree bst2 = new BinarySearchTree(null);
        bst2.build123();
        bst.build123();
        System.out.println("Are BST and BST2 same 1? "+bst.sameTree(bst.root, bst2.root));
        //bst.printTree(bst.root);
        // System.out.println("Size of the current binary tree is: ->"+bst.size(bst.root));
        // System.out.println("Depth of the current binary tree is: ->"+bst.maxDepth(bst.root));
        // System.out.println("The minimum value of the current binary tree is: ->"+bst.minValue(bst.root));
        bst.insert(5);bst2.insert(5);
        // System.out.println("Inserting node 5 into BST: ->");
        // System.out.println("Size of the current binary tree is: ->"+bst.size(bst.root));
        //bst.printTree(bst.root);
        bst.printTreeInorder(bst.root);
        System.out.println("Post order traversal of the tree: ");
        bst.printPostorder(bst.root);
        bst.insert(4); bst2.insert(4);
        bst.insert(9); bst2.insert(9);
        bst.insert(11); bst2.insert(11);
        System.out.println("Inorder traversal of BST**: ");
        bst.printTreeInorder(bst.root);
        System.out.println("Inorder traversal of BST2**: ");
        bst2.printTreeInorder(bst2.root);
        System.out.println("Are BST and BST2 same again 2? "+bst.sameTree(bst.root, bst2.root));
        System.out.println("HAS PATH SUM? "+bst.hasPathSum(bst.root, 30));
        // bst.mirror(bst.root);
        // System.out.println("BST after mirror method: ");
        //bst.printTreeInorder(bst.root);
        bst.printPaths(bst.root);
        System.out.println("BST after doubling: ");
        bst.doubleTree(bst.root);
        bst.printTreeInorder(bst.root);
        System.out.println("Are BST and BST2 same again 3? "+bst.sameTree(bst.root, bst2.root));

        //Testing for isBST()
        BinarySearchTree bt1 = new BinarySearchTree(null);
        int[] arr1 = {5,2,7,8,3};
        bt1.root = new Node(5);
        bt1.root.left = new Node(2);
        bt1.root.right = new Node(7);
        bt1.root.left.left = new Node(8);
        bt1.root.left.right = new Node(3);
        System.out.println("BT1 inorder:");
        bt1.printTreeInorder(bt1.root);
        System.out.println("IS BT1 A BST??: "+bt1.isBST(bt1.root));
        System.out.println("IS bst A BST??: "+bt1.isBST(bst.root));

        //Testing for countTrees
        System.out.println("Count Trees for n=5: "+bst.countTrees(5));
        System.out.println("Count Trees Dynamic for n=5: "+bst.countTreesDynamic(5));
    }
}