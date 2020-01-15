package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  
  public BST(){ size=0; root=null; }
  
  @Override
  //used for testing
  public BST_Node getRoot(){ 
	  return root; 
	  }

@Override
public boolean insert(String s) { 
	
	BST_Node newNode = new BST_Node(s);
	if (root == null) {
		root = newNode;
		size++;
		return true;
	} else {
		size++;
		return root.insertNode(newNode.getData());
	}
}

@Override
public boolean remove(String s) {
	BST_Node node = new BST_Node(s);
	if (root == null) {
		return false;
	} else {
		if (root.getData().compareTo(s) == 0) { //if removing the root
			//BST_Node temRoot = new BST_Node("");
			//temRoot.setLeftChild(root);
			//boolean result = root.removeNode(s, temRoot);
			size--;
			if (root.right == null && root.left == null) {
			root = null;	
			return true;
			} else if (root.right == null) {
				root = root.left;
				return true;
			} else if (root.left == null) {
				root = root.right;
				return true;
			} else if (root.right != null && root.left != null) {
				root = null;
				return true;
			}
			
		}
	}
	size--;
	return root.removeNode(s, null);
}

@Override
public String findMin() {
	if (size == 0) {
		return null;
	}
	BST_Node newNode = root;
	return newNode.findMin(newNode).getData();
}

@Override
public String findMax() {
	if (size == 0) {
		return null;
	}
	BST_Node newNode = root;
	return newNode.findMax(newNode).getData();
}

@Override
public boolean empty() {
	if (size == 0 || root == null) {
		return true;
	}
	return false;
}

@Override
public boolean contains(String s) {
	if (size == 0 || root == null) {
		return false;
	}
	return root.containsNode(s);
}

@Override
public int size() {
	return size;
}

@Override
public int height() {
	if (this.empty()) {
		return -1;
	}
	BST_Node node = root;
	return root.getHeight(root);
}


}