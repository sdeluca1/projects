package BST_A2;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  
  BST_Node(String data){ this.data=data; }

  // --- used for testing  ----------------------------------------------
  //

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  public boolean containsNode(String s){ 
	  if (s.equals(this.getData())) {
		  return true;
	  } else if (s.compareTo(this.getData()) < 0) {
		  if (left == null) {
			  return false;
		  } else {
			  return left.containsNode(s);
		  }
	  } else if (s.compareTo(this.getData()) > 0) {
		  if (right == null) {
			  return false;
		  } else {
			  return right.containsNode(s);
		  }
	  }
	  
	  return false;
	  }
  
  public boolean insertNode(String s){ 
	  if (s.equals(this.getData())) {
		  return false;
	  } else if (s.compareTo(this.getData()) < 0) {
		  if (left == null) {
			  left = new BST_Node(s);
			  return true;
		  } else {
			  return left.insertNode(s);
		  }
	  } else if (s.compareTo(this.getData()) > 0) {
		  if (right == null) {
			  right = new BST_Node(s);
			  return true;
		  } else {
			  return right.insertNode(s);
		  }
	  }
	  return false; 
	  }
  
  public boolean removeNode(String s, BST_Node parent){ 
	  
	  if (s.compareTo(this.getData()) < 0) {
		  if (left != null) {
			  return left.removeNode(s, this);
		  } else {
			  return false;
		  }
	  } else if (s.compareTo(this.getData()) > 0) {
		  if (right != null) {
			  return right.removeNode(s, this);
		  } else {
			  return false;
		  }
	  } else {
		  if (left == null && right == null) {
			 // System.out.println(parent.data);
			  if (this.data.compareTo(parent.data) < 0) { //if child is left child
				  parent.left = null;
			  } else if (this.data.compareTo(parent.data) > 0) {//if child is right child
				  parent.right = null;	 
			  }
			  
		  }
		  else if (left != null && right != null) {
			  BST_Node temp = findMin(right);
		
			  
			  removeNode(temp.data, parent);
			  this.data = temp.data;
		  } else if (parent.left == this) {
			  if (left != null) {
			
				  parent.left = left;
			  } else {
		
				  parent.left = right;
			  }
			
		  } else if (parent.right == this) {
			  if (left != null) {
				
				  parent.right = left;
			  } else {
			
				  parent.right = right;
			  }
		  }
		  return true;
	  }
  }
  
  public BST_Node findMin(BST_Node n){ 
	  BST_Node current = n;
	  while(current.left != null) {
		  current = current.left;
	  }
	  
	  return current;
	  }
  
  public BST_Node findMax(BST_Node n){ 
	
	  BST_Node current = n;
	  while (current.right != null) {
		  current = current.right;
	  }
	  return current; 
	  }
  
  public int getHeight(BST_Node node){ 
	  int leftHeight = 0;
	  int rightHeight = 0;
	  if (node.left == null && node.right == null) {
		  return 0;
	  }
	  
	  if (node.left != null) {
		  leftHeight = getHeight(node.left);
	  }
	  if (node.right != null) {
		  rightHeight = getHeight(node.right);
	  }
	  if (leftHeight > rightHeight) {
		  return leftHeight + 1;
	  } else {
		  return rightHeight + 1;
	  }
  }
  
  public String minVal() { //helper method for remove, different way of calculating min
	  if (left == null) {
		  return left.getData();
	  } else {
		  return left.minVal();
	  }
  }
  
  public void setLeftChild(BST_Node n) {
	  n = left;
  }
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }

}