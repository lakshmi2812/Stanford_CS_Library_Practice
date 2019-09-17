import java.lang.*;
import java.util.ArrayList;

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

    //printTreeInorder() is called printTree() in the pdf. This method prints the node sof the BST in the increasing order
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

    //printPostorder() -> prints the values of the left and right nodes of a node before printing its own value
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

    //hasPathSum() -> if there is a path in the BST with the given sum, return true, else return false
    public boolean hasPathSum(Node root, int inputSum){
        if(root == null){
            return (inputSum == 0);
        }else{
            int subSum = inputSum - root.data;
            return(hasPathSum(root.left,subSum) || hasPathSum(root.right, subSum));
        }
    }

    //print all the paths from the root node to leaf node for the given BST
    // public void printPaths(Node root){
    //     if(root == null){
    //         return;
    //     }
    //     System.out.print(root.data+" ");
    //     if(root.left != null || root.right != null){
    //         if(root.left != null){
    //             printPaths(root.left);
    //         }
    //         if(root.right != null){
    //             printPaths(root.right);
    //         }
    //     }else{
    //         System.out.println("");
    //         System.out.print(root.data + " ");
    //     }
    // }

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

    public void printPaths(Node root){
        ArrayList<Integer> al = new ArrayList<Integer>();
        //call recursive helper function printPathsRecur
        printPathsRecur(root, al);
    }


    

    //mirror() -> create a mirror image of the current tree(by swapping the left and right nodes for each node)
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

    //doubleTree() -> create a duplicate of each node and place it to its left
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

    //sameTree() -> given two trees, find out if they are identical(same structure and same data in all nodes)
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
    }
}