package SPLT_A4;

public class SPLT implements SPLT_Interface {
	public BST_Node root;
	int size;
	  
	public SPLT(){
		size = 0;
		root = null;
	}
	  
	@Override
	//used for testing
	public BST_Node getRoot(){
		return root;
	}

	@Override
	public boolean insert(String s) { 
		if (empty()) {
			root = new BST_Node(s);
			size += 1;
			return true;
		} else {
			root = root.insertNode(s);
			if(root.justMade == false) {
				return false;
			} else {
				size++;
				root.justMade = false;
				return true;
			}
			
		}
	}

	@Override
	public void remove(String s) {
		if (!contains(s)) {
			return;
		} else {
			if (root.data.equals(s) && size == 1) { //if only root in tree
				root = null;
				size -= 1;
				return;
			} else {
				size -= 1;
				root = root.removeNode(s);
				return;
			}
		}
	}

	@Override
	public String findMin() {
		if (empty()) {
			return null;
		} else {
			root = root.findMin();
			return root.data;
		}
	}

	@Override
	public String findMax() {
		if (empty()) {
			return null;
		} else {
			root = root.findMax();
			return root.data;
		}
	}

	@Override
	public boolean empty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(String s) {
		if (empty()) {
			return false;
		}
		root = root.containsNode(s);
		return root.contains;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		if (empty()) {
			return -1;
		} else {
			return root.getHeight();
		}
	}
}
