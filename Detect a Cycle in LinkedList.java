-----------------------------------Detect a Cycle in LinkedList.----------------------------------------------
  Problem Statement: Given head, the head of a linked list, determine if the linked list has a cycle in it(Return true) or not(Return false). There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
1.Approach HASHING
We have to keep track of all the nodes we have visited till now so that once we visit the same node again we can say that a cycle is detected.
•	Use a hash table for storing nodes. 
•	Start iterating through the lists.
•	If the current node is present in the hash table already, this indicates the cycle is present in the linked list and returns true.
•	Else move insert the node in the hash table and move ahead.
•	If we reach the end of the list, which is NULL, then we can say that the given list does not have a cycle in it and hence we return false.
<code>
 import java.util.*;
class Node {
        int num;
        Node next;
        Node(int val) {
            num = val;
            next = null;
        }
}

//utility function to insert node in the list
class solution{
static Node insertNode(Node head,int val) {
    Node newNode = new Node(val);
    
    if(head == null) {
        head = newNode;
        return head;
    }
    
    Node temp = head;
    while(temp.next != null) temp = temp.next;
    
    temp.next = newNode;
    return head;
}

//utility function to create cycle
static void createCycle(Node head,int a,int b) {
    int cnta = 0,cntb = 0; where cnt=count
    Node p1 = head;
    Node p2 = head;
    while(cnta != a || cntb != b) {
        if(cnta != a) 
        {p1 = p1.next; ++cnta;}
        if(cntb != b) 
        {p2 = p2.next; ++cntb;}
    }
    p2.next = p1;
}

//utility function to detect cycle
static boolean cycleDetect(Node head) {
    HashSet <Node> hashTable=new HashSet<>();
    while(head != null) {
        if(hashTable.contains(head)) return true;
        hashTable.add(head);
        head = head.next;
    }
    return false;
}

public static void main(String args[]) {
    Node head = null;
    head=insertNode(head,1);
    head=insertNode(head,2);
    head=insertNode(head,3);
    head=insertNode(head,4);
    createCycle(head,1,3);//creating cycle in the list
    if(cycleDetect(head) == true)
    System.out.println("Cycle detected");
    else
    System.out.println("Cycle not detected");
    
}
}
Output: Cycle detected
Time Complexity: O(N)[Reason: Entire list is iterated once].
  
2.	Approach SLOW AND FAST POINTER
We will use two pointers with different steps forward-
 We will take two pointers, namely fast and slow. Fast pointer takes 2 steps   ahead and slow pointer takes 2 points ahead.
•	Iterate through the list until the fast pointer is equal to NULL. This is because NULL indicates that there is no cycle present in the given list.
•	Cycle can be detected when fast and slow pointers collide.
<code>
import java.util.*;
class Node {
        int num;
        Node next;
        Node(int val) {
            num = val;
            next = null;
        }
}

//utility function to insert node in the list
class solution{
static Node insertNode(Node head,int val) {
    Node newNode = new Node(val);
    
    if(head == null) {
        head = newNode;
        return head;
    }
    
    Node temp = head;
    while(temp.next != null) temp = temp.next;
    
    temp.next = newNode;
    return head;
}

//utility function to create cycle
static void createCycle(Node head,int a,int b) {
    int cnta = 0,cntb = 0;
    Node p1 = head;
    Node p2 = head;
    while(cnta != a || cntb != b) {
        if(cnta != a) where cnt=count 
        {p1 = p1.next; ++cnta;}
        if(cntb != b) 
        {p2 = p2.next; ++cntb;}
    }
    p2.next = p1;
}

//utility function to detect cycle
static boolean cycleDetect(Node head) {
    if(head == null) return false;
    Node fast = head;
    Node slow = head;
        
    while(fast.next != null && fast.next.next != null) {
        fast = fast.next.next;
        slow = slow.next;
        if(fast == slow) return true;
    }
    return false;
}

public static void main(String args[]) {
    Node head = null;
    head=insertNode(head,1);
    head=insertNode(head,2);
    head=insertNode(head,3);
    head=insertNode(head,4);
    createCycle(head,1,3);//creating cycle in the list
    if(cycleDetect(head) == true)
    System.out.println("Cycle detected");
    else
    System.out.println("Cycle not detected");
    
}
}
Output: Cycle detected
Time Complexity: O(N)[Reason: In the worst case, all the nodes of the list are visited].
