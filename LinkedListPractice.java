import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

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
            return 0;
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

        //data is less than this.head.data
        if(data < this.head.data){
            this.push(data);
            return;
        }
        while(current.next != null){
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

    //7.insertSort()
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
            //newLL.printList();
        }

        if(current.next == null){
            newLL.sortedInsert(current.data);
        }
        //point the original head to the new list
        this.head = newLL.head;
    }

    //8.append() -> appens the new list to the end of the current list
    public void append(Node ll2Head){
        if(this.head == null){
            this.head = ll2Head;
        }
        Node current = this.head;
        while(current.next != null){
            current = current.next;
        }
        current.next = ll2Head;
    }

    //9.frontBackSplit() -> split a linkedlist into two lists
    //if list's length is odd, the extra element should go to the first list
    public LinkedListPractice frontBackSplit(){
        LinkedListPractice ll2 = new LinkedListPractice();
        int length = this.length();
        if(length < 2){
            return null;
        }
        //if length is even
        if(length % 2 == 0){
            Node current = this.head;
            int forMax = length/2 - 1;
            for(int i = 0; i < forMax; i++){
                current = current.next;
            }
            ll2.head = current.next;
            current.next = null;
        }

        //if length is odd
        if(length % 2 == 1){
            Node _current = this.head;
            int _forMax = (length+1)/2 - 1;
            for(int j=0; j<_forMax; j++){
                _current = _current.next;
            }
            ll2.head = _current.next;
            _current.next = null;
        }
        return ll2;
    }

    //10.removeDuplicates() -> removes duplicate nodes from the linkedlist
    public void removeDuplicates(){
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        Node current = this.head;
        hm.put(this.head.data, 1);
        while(current.next != null){
            if(hm.containsKey(current.next.data)){
                //remmove the node
                if(current.next.next != null){
                    current.next = current.next.next;
                }else{
                    current.next = null;
                }
            }else{
                //add the node to the hashMap
                hm.put(current.next.data, 1);
                current = current.next;
            }
        }
    }

    //11.moveNode() -> takes a second linkedlist and remves its head and pushes this head to the front(head) of the first linkedlist
    public void moveNode(LinkedListPractice ll2){
        Node newHead = ll2.head;
        this.push(newHead.data);
        ll2.head = ll2.head.next;
        System.out.println("The second list after moveNode operation: ");
        ll2.printList();
    }

    //12.alternatingSplit() -> takes a list and splits it into 2 lists based on alternating elements
    public LinkedListPractice alternatingSplit(){
        Node current = this.head;
        int length = this.length();
        LinkedListPractice ll2 = new LinkedListPractice();
        //if linkedlist is empty
        if(this.head == null){
            return null;
        }
        //if list has only on element
        if(length == 1){
            return null;
        }
        //Node current2 = ll2.head;
        Node current2 = null;
        while(current.next != null){
            System.out.println(current.data);
            if(ll2.head == null){
                ll2.head = current.next;
                current2 = ll2.head;
                if(current.next.next != null){
                    current.next = current.next.next;
                }else{
                    current.next = null;
                }
            }else{
                //instead of doing below step, you can create a new node with current.next's data as its next will be automatically null
                current2.next = current.next;
                current2 = current2.next;
                if(current.next.next != null){
                    current.next = current.next.next;
                }else{
                    current.next = null;
                }
            }
            if(current.next != null){
                current = current.next;
            }
            System.out.println("current2:"+current2.data);
        }
        current2.next = null;
        return ll2;
    }

    //helper method pushToEnd
    public void pushToEnd(int data){
        Node newNode = new Node(data);
        Node current = this.head;
        if(this.head == null){
            return;
        }
        while(current.next != null){
            current = current.next;
        }
        current.next = newNode;
    }

    //13.shuffleMerge() -> merge two linkedlists, taking nodes alternatingly from each list. 
    //If you run out of nodes in either list,take all remaining node from the other list.
    public LinkedListPractice shuffleMerge(LinkedListPractice list2){
        Node current1 = null;
        Node current2 = null;
        //list2 is empty or null        
        if(list2 ==  null || list2.head == null){
            return this;
        }else{
            current2 = list2.head;
        }
        if(this.head != null){
            current1 = this.head;
        }else{
            //list1 is empty
            return list2;
        }
        LinkedListPractice list3 = new LinkedListPractice();
        Node current3 = null;
        String pre = "";

        //edge cases
        //list2 is empty

        while(current1 != null && current2 != null){
            if(list3.head == null){
                list3.head = new Node(current1.data);
                pre = "list1";
                current1 = current1.next;
                current3 = list3.head;
            }else{
                if(pre == "list2"){
                    current3.next = new Node(current1.data);
                    pre = "list1";
                    current1 = current1.next;
                    current3 = current3.next;
                }else{
                    current3.next = new Node(current2.data);
                    pre = "list2";
                    current2 = current2.next;
                    current3 = current3.next;
                }
            }
        }

        while(current1 != null){
            current3.next = new Node(current1.data);
            current1 = current1.next;
            current3 = current3.next;
        }

        while(current2 != null){
            current3.next = new Node(current2.data);
            current2 = current2.next;
            current3 = current3.next;
        }

        return list3;
    }

    //14.sortedMerge() -> merge two sorted lists into a new list
    public LinkedListPractice sortedMerge(LinkedListPractice list1, LinkedListPractice list2){
        Node current1 = null;
        Node current2 = null;
        //list2 is empty or null        
        if(list2 ==  null || list2.head == null){
            return list1;
        }else{
            current2 = list2.head;
        }
        //list1 is empty or null
        if(list1 ==  null || list1.head == null){
            return list2;
        }else{
            current1 = list1.head;
        }
        LinkedListPractice list3 = new LinkedListPractice();
        Node current3 = null;
        while(current1 != null && current2 != null){
            if(list3.head == null){
                if(current1.data <= current2.data){
                    list3.head = new Node(current1.data);
                    current1 = current1.next;
                    current3 = list3.head;
                }else{
                    list3.head = new Node(current2.data);
                    current2 = current2.next;
                    current3 = list3.head;
                }
            }else{
                if(current1.data <= current2.data){
                    current3.next = new Node(current1.data);
                    current1 = current1.next;
                    current3 = current3.next;
                }else{
                    current3.next = new Node(current2.data);
                    current2 = current2.next;
                    current3 = current3.next;
                }
            }
        }
        while(current1 != null){
            current3.next = new Node(current1.data);
            current1 = current1.next;
            current3 = current3.next;
        }

        while(current2 != null){
            current3.next = new Node(current2.data);
            current2 = current2.next;
            current3 = current3.next;
        }
        return list3;
    }

    //15.mergeSort() -> sort the elements of a linkedlist in increasing order
    public LinkedListPractice mergeSort(LinkedListPractice myList){
        if(myList == null || myList.head == null){
            return null;
        }
        int length = myList.length();
        //base case
        if(length == 1){
            return myList;
        }

        //split and merge
        LinkedListPractice newList = myList.frontBackSplit();
        // System.out.println("New List: ");
        // newList.printList();
        // System.out.println("My List: ");
        // myList.printList();
        return sortedMerge(mergeSort(newList), mergeSort(myList));
        //return null;
    }

    //16.sortedIntersect() -> given two sorted linkedlists(made of integers), form a new list containing the common elements in the 2 lists
    public LinkedListPractice sortedInsert(LinkedListPractice list1, LinkedListPractice list2){
        LinkedListPractice commonList = new LinkedListPractice();
        if(list1 == null || list1.head == null || list2 == null || list2.head == null){
            return null;
        }

        Node current1 = list1.head;
        Node current2 = list2.head;
        Node current3 = null;

        list1.removeDuplicates();
        list2.removeDuplicates();

        while(current1 != null && current2 != null){
            if(current1.data < current2.data){
                //current1 is less
                current1 = current1.next;
            }else if(current1.data > current2.data){
                //current2 is less
                current2 = current2.next;
            }else{
                //both are equal - common element!
                if(commonList.head == null){
                    commonList.head = new Node(current1.data);
                    current3 = commonList.head;
                }else{
                    current3.next = new Node(current1.data);
                    current3 = current3.next;
                }
                current1 = current1.next;
                current2 = current2.next;
            }
        }
        return commonList;
    }

    //17.reverse() -> given a linkedlist, reverse the nodes of the list such that the tail is the new head
    public void reverse(){
        if(this.head == null || this == null){
            return;
        }
        LinkedListPractice dummyList = new LinkedListPractice();
        Node dummyNode = new Node(-1);
        dummyList.head = dummyNode;
        Node h2 = dummyList.head;
        Node h1 = this.head;
        Node temp = null;
        while(h1 != null){
            temp = h1;
            h1 = h1.next;
            temp.next = h2.next;
            h2.next = temp;
        }
        this.head = temp;
    }

    //18. 

    public static void main(String[] args){
        LinkedListPractice ll = new LinkedListPractice();
        LinkedListPractice ll2 = new LinkedListPractice();
        ll2.buildOneTwoThree();
        System.out.println("********");
        // ll2.printList();
        int[] arr = {34,1,12,15,90};
        for(int i = 0; i < arr.length; i++){
            ll.insertNth(i,arr[i]);
        }
        //ll.sortedInsert(6);
        //ll.push(34);
        //ll.push(3);
        //ll.insertNth(0, 79);
        ll.printList();
        System.out.println("The linked list after splitting: ");
        LinkedListPractice llHalf;
        llHalf = ll.frontBackSplit();
        System.out.println("First Half:->");
        ll.printList();
        System.out.println("Second Half:->");
        llHalf.printList();
        ll.insertSort();
        ll.append(ll2.head);
        ll.printList();
        System.out.println("The length of the linkedlist is: "+ll.length());
        System.out.println("The number of times the given integer(3) occurs in the list is: "+ll.count(3));
        System.out.println("The data at the nth(4) index is: "+ll.getNth(4));
        System.out.println("The popped element: "+ll.pop());
        ll.push(34);
        ll.push(3);
        ll.push(1);
        ll.push(2);
        System.out.println("Initial list:-> ");
        ll.printList();
        System.out.println("List after removing duplicates:-> ");
        ll.removeDuplicates();
        ll.printList();
        ll.deleteList();
        ll.printList();
        //int[] arr1 = {34,1,12,15, 90};
        int[] arr1 = {1,3,5};
        for(int i = 0; i < arr1.length; i++){
            ll.insertNth(i,arr1[i]);
        }
        //int[] arr2 = {2,4,5,6,8,10};
        int[] arr2 ={20};
        LinkedListPractice list2 = new LinkedListPractice();
        for(int i = 0; i < arr2.length; i++){
            list2.insertNth(i,arr2[i]);
        }
        System.out.println("List2: ->");
        list2.printList();
        ll.printList();
        ll.moveNode(ll2);
        System.out.println("Linkedlist after moveNode operation: ");
        ll.printList();
        System.out.println("********");
        // LinkedListPractice llAltSplit = ll.alternatingSplit();
        // System.out.println("Original linkedlist after splitting:-> ");
        // //ll.pushToEnd(6758);
        // ll.printList();
        // System.out.println("Linkedlist alternate after splitting:-> ");
        // llAltSplit.printList();
        System.out.println("Linkedlist after shuffleMerge:-> ");
        LinkedListPractice ll3 = ll.shuffleMerge(list2);
        ll3.printList();
        LinkedListPractice lln = new LinkedListPractice();
        //int[] arrn = {1,3,5};
        int[] arrn = {90};
        for(int i = 0; i < arrn.length; i++){
            lln.insertNth(i,arrn[i]);
        }
        LinkedListPractice list3 = ll.sortedMerge(lln, list2);
        System.out.println(" ******Sorted Merge ********");
        list3.printList();
        LinkedListPractice list = new LinkedListPractice();
        int[] arrNew = {90,20,80,10,5};
         for(int i = 0; i < arrNew.length; i++){
            list.insertNth(i,arrNew[i]);
        }
        System.out.println("$$$$$$$$$$$");
        list.printList();
        LinkedListPractice sortedList = list.mergeSort(list);
        System.out.println("List after sorting: ->");
        sortedList.printList();
        sortedList.reverse();
        System.out.println("Sorted list after reversing!: ->");
        sortedList.printList();
        LinkedListPractice list100 = new LinkedListPractice();
        int[] arr100 = {105,200,300,405,505};
        for(int i = 0; i < arr100.length; i++){
            list100.insertNth(i,arr100[i]);
        }
        LinkedListPractice list200 = new LinkedListPractice();
        int[] arr200 = {55,65,95,200,400,405};
        for(int i = 0; i < arr200.length; i++){
            list200.insertNth(i,arr200[i]);
        }
        LinkedListPractice commonList = ll.sortedInsert(list100, list200);
        System.out.println(" &&&&&& Common List &&&&&&&");
        commonList.printList();
    }
}