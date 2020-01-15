package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {

	Node sentinel;
	int size = 0;
	
	
	public LinkedListImpl() {
		sentinel = new Node(0);
	}

	@Override
	public void clear() { //done
		sentinel.next = null;
		sentinel.prev = null;
		size = 0;
	}

	@Override
	public int size() { //done
		return size;
	}

	@Override
	public boolean isEmpty() { //done
		if (sentinel.next == null || size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean insert(double elt, int index) {		
		if (index > size() || index < 0) {
			return false;
		}
		
		Node newNode = new Node(elt);
		Node nodeBefore = sentinel;
		
		//int i = 0;
		
		if (isEmpty() == true) {
			sentinel.next = newNode;
			sentinel.prev = newNode;
			newNode.next = sentinel;
			newNode.prev = sentinel;
			size++;
			return true;
		} 
		
		for(int i = 0; i < index; i++) {
			nodeBefore = nodeBefore.next;
		}
		
		Node nodeAfter = nodeBefore.next;
		
		newNode.prev = nodeBefore;
		newNode.next = nodeAfter;
		nodeAfter.prev = newNode;
		nodeBefore.next = newNode;
		size++;
		
		return true;
	}

	@Override
	public boolean remove(int index) {
		if (index > size || index < 0) {
			return false;
		}
		Node nodeBefore = sentinel;
		Node nodeCurr = sentinel;

		if (index == 0) {
			nodeCurr = nodeCurr.next;
			Node nodeAft = nodeCurr.next;
			nodeBefore = nodeCurr.prev;
			nodeBefore.next = nodeAft;
			nodeAft.prev = nodeBefore;
			size--;
			return true;
		}
		
		for (int i = 0; i <= index; i++) {
			nodeCurr = nodeCurr.next;	
		}
		Node nodeAfter = nodeCurr.next;
		nodeBefore = nodeCurr.prev;
		nodeBefore.next = nodeAfter;
		nodeAfter.prev = nodeBefore;
	
			
		size--;	
		return true;
	}

	@Override
	public double get(int index) { //done
		Node sent = sentinel;
		Node temp = sent.next;
		if (index > size - 1 || size < 0) {
			return Double.NaN;
		} else {
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
			return temp.getData();
		}
	}
	
	public Node getRoot() {
		return sentinel;
	}
}