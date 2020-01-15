package LinkedList_A1;

public class Node { //this is the Node object, these are the Objects in the list
  public double data;
  public Node next; //links this node to the next Node in the List
  public Node prev; //links this node to the preceeding Node in the List (ie this Node is the prev Node's next node)
  public Node(double data){
    this.data=data;
    this.next=null;
    this.prev=null;
  }
  public String toString(){
    return "data: "+data+"\thasNext: "+(next!=null)+"\t\thasPrev: "+(prev!=null);
  }
  
    Below are "getters" for our testing purposes.
  
  public boolean isNode(){
    return true;
  }
  public double getData(){
    return data;
  }
  public Node getNext(){
    return next;
  }
  public Node getPrev(){
    return prev;
  }
}