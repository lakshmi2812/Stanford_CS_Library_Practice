public class LinkedListPractice{
    Node head;

    //static class Node
    private static class Node{
        //instance variables
        int data;
        Node next;

        //constructor for Node
        private Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    //constructor for LinkedListPractice
    public LinkedListPractice(){
        this.head = null;
    }

    //*********/ Basic utility functions /************/

    //length() -> returns the length of the linkedlist
    public int length(){
        if(this.head == null){
            return -1;
        }
        int count = 0;
        Node current = this.head;
        while(current != null){
            count++;
            current = current.next;
        }
        return count;
    }

    //push() -> push a node at the head of the linkedlist
    public void push(int data){
        if(this.head == null){
            Node newNode1 = new Node(data);
            this.head = newNode1;
        }else{
            Node current = this.head;
            Node newNode = new Node(data);
            this.head = newNode;
            this.head.next = current;
        }
    }

    //buildOneTwoThree() -> builds a linkedlist with data nodes 1, 2 and 3
    public void buildOneTwoThree(){
        Node one = new Node(1);;
        Node two = new Node(2);
        Node three = new Node(3);
        this.head = one;
        Node current = this.head;
        current.next = two;
        current = current.next;
        current.next = three;
    }

    //printList() -> prints the linkedlist
    public void printList(){
        if(this.head == null){
            System.out.println("The linkedlist is empty!");
        }
        Node current = this.head;
        while(current != null){
            System.out.println(current.data);
            current = current.next;
        }
    }

    ///**************/ LinkedList Problems /***************//
    
    //1. count() -> counts the number of times a given integer occurs in the list
    public int count(int value){
        Node current = this.head;
        int count = 0;
        while(current != null){
            if(current.data == value){
                count++;
            }
            current = current.next;
        }
        return count;
    }

    //2. getNth() -> take an integer index(linkedlist has zero-based index) and returns the data stored in the Node at that index
    public int getNth(int index){
        int length = this.length();
        if(index < 0 || index > length){
            return -1;
        }
        Node current = this.head;
        int count = 0;
        while(count < index && current.next != null){
            current = current.next;
            count++;
        }
        return current.data;
    }

    //3. deleteList() -> delete the entire linkedlist
    public void deleteList(){
        this.head = null;
    } 

    //4. pop() -> remove the head node from the linkedlist and return the head node's data
    public int pop(){
        if(this.head == null){
            return -1;
        }

        Node current = this.head;
        this.head = this.head.next;
        return current.data;
    }

    //5. insertNth() -> insert a node at the given index in the linkedlist
    //params - index -> the index at which the node has to be inserted, data -> the data of the new node 
    public void insertNth(int index, int data){
        if(this.head == null){
            Node newNode = new Node(data);
            this.head = newNode; 
            return;
        }
        int length = this.length();
        Node newNode = new Node(data);
        int count = 0;
        if(index < 0 || index > length){
            return;
        }

        if(index == 0){
            this.push(data);
            return;
        }

        if(index == length){
            Node current = this.head;
            while(current.next != null){
                current = current.next;
            }
            current.next = newNode;
            return;
        }

        Node _current = this.head;
        while(count < index-1){
            _current = _current.next;
            count++;
        }
        newNode.next = _current.next;
        _current.next = newNode;
    }

    //6. sortedInsert() -> insert a Node with the given data in an already sorted linkedlist  
    public void sortedInsert(int data){
        Node newNode = new Node(data);
        Node current = this.head;
        if(this.head == null){
            this.head = newNode;
            return;
        }
        while(current.next != null){
            //data is less than this.head.data
            if(data < this.head.data){
                this.push(data);
                return;
            }

            //data is in between
            if(data >= current.data && data <= current.next.data){
                Node temp = current.next;
                current.next = newNode;
                newNode.next = temp;
                return;
            }

            current = current.next;
        }
        current.next = newNode;
    }

    public void insertSort(){
        //initialize an empty list
        LinkedListPractice newLL = new LinkedListPractice();
        if(this.head == null){
            return;
        }
        
        //sortedInsert each element of the original list to the new list
        Node current = this.head;
        //this condition avoids null pointer exception
        while(current.next != null){
            newLL.sortedInsert(current.data);
            current = current.next;
        }

        //point the original head to the new list
        this.head = newLL.head;
    }


    public static void main(String[] args){
        LinkedListPractice ll = new LinkedListPractice();
        //ll.buildOneTwoThree();
        int[] arr = {34,1,15,90,12};
        for(int i = 0; i < arr.length; i++){
            ll.insertNth(i,arr[i]);
        }
        //ll.sortedInsert(6);
        //ll.push(34);
        //ll.push(3);
        //ll.insertNth(0, 79);
        //ll.printList();
        ll.insertSort();
        ll.printList();
        System.out.println("The length of the linkedlist is: "+ll.length());
        System.out.println("The number of times the given integer(3) occurs in the list is: "+ll.count(3));
        System.out.println("The data at the nth(4) index is: "+ll.getNth(4));
        System.out.println("The popped element: "+ll.pop());
        ll.deleteList();
        ll.printList();
    }
}