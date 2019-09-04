public class LinkedList{
    //assume this to be the linkedlist class
    Node head; 
    //constructor
    public LinkedList(){
        this.head = null;
    }

    //length() -> returns the number of nodes in the list
    public int length(){
        Node current = this.head;
        int count = 0;
        while(current != null){
            count++;
            current = current.next;
        }
        return count;
    } 

    //buildOneTwoThree -> Builds a LinkedList from the given array
    public String buildOneTwoThree(int[] list){
        if(list.length == 0){
            return "Array should contain atleast one element";
        }
        this.head = new Node(list[0]);
        Node current = this.head;
        for(int i = 1; i < list.length; i++){
            current.next = new Node(list[i]);
            current = current.next;
        }
        return "Build List successful!";
    }

    //push() -> push a Node to the beginning of the list(at the head)
    public void push(int data){
        if(this.head == null){
            this.head = new Node(data);
        }
        Node current = this.head;
        Node newNode = new Node(data);
        this.head = newNode;
        this.head.next = current;
    }

    //printList() -> prints all the nodes in the LinkedList
    public void printList(){
        if(this.head == null){
            System.out.println("List is empty!");
        }else{
            Node current = this.head;
            while(current != null){
                System.out.println(current.data);
                current = current.next;
            }
        }        
    }

    //count() -> takes an integer as arg and returns the number of times the given integer appears in the list
    public int count(int num){
        Node current = this.head;
        int count = 0;
        while(current != null){
            if(current.data == num){
                count++;
            }
            current = current.next;
        }
        return count;
    }

    //getNth() -> takes an index and returns a node data at that index
    public int getNth(int index){
        int listLength = this.length();
        if(index < 0 || index > listLength){
            return -1;
        }
        int count = 0;
        Node current = this.head;
        while(count < index){
            current = current.next;
            count++;
        }
        return current.data;
    }

    //delete() -> deletes the entire linkedlist
    public void delete(){
        this.head = null;
    }
    
    //pop() -> deletes the first element from the linkedlist and returns its data
    public int pop(){
        if(this.head == null){
            return -1;
        }
        Node current = this.head;
        this.head = this.head.next;
        return current.data;
    }

    public static void main(String[] args){
        Node n = new Node(3);
        LinkedList ll = new LinkedList();
        //System.out.println("LinkedList:->"+ll.head.data);
        int[] arr = {3, 19, 121, 45, 19, 19};
        System.out.println(ll.buildOneTwoThree(arr));
        System.out.println("Length of ll is:->"+ll.length());
        ll.push(100);
        ll.printList();
        System.out.println("The number of times given num(19) occurs is: "+ll.count(19));
        System.out.println("The data at index(3) is: "+ ll.getNth(9));
        System.out.println("The popped element in linkedlist: "+ ll.pop());
        System.out.print("The linkedlist after popping: ");
        ll.printList();
    }
}