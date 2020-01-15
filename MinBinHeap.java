package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 10000; 

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000);
  }
    
  @Override
  public EntryPair[] getHeap() { 
    return this.array;
  }

@Override
public void insert(EntryPair entry) {
	

	array[size+1] = entry;
	bubbleUp(size+1);
	size++;
	
}



@Override
public void delMin() {
	// if leaf fits into root
	if (size == 1) {
		size--;
		return;
	}
	if (array[size].getPriority() < array[2].getPriority() &&
		array[size].getPriority() < array[3].getPriority()) {
		array[1] = array[size];
		array[size] = null;
		size--;
		return;
	} else {
	array[1] = array[size];
	size--;
	bubbleDown(1);
	}
	
	
}

@Override
public EntryPair getMin() {
	if (size() == 0) {
		return null;
	}
		return array[1];

}

@Override
public int size() {
	if (size == 0) {
		return 0;
	}
	return size;
}

@Override
public void build(EntryPair[] entries) {
	size = entries.length;
	for (int i = 0; i < entries.length; i++) {
		array[i+1] = entries[i];	
	}
	for (int i = size/2; i > 0; i--) {
		bubbleDown(i);
	}
}

private void bubbleUp(int index) {	
	if (index < 2) { //base case, can't bubble up if index is < 2
		return;
	}
	EntryPair temp = new EntryPair("", 0);
		if (array[index/2].getPriority() > array[index].getPriority()) { //then swap
				temp = array[index/2]; //temp = parent
				array[index/2] = array[index]; //parent = current
				array[index] = temp; //current = temp
			}
		bubbleUp(index/2);
		return;

}
	


private void bubbleDown(int index) {
	EntryPair temp = new EntryPair("", 0);
	//if node at index has no children, return
	if (index * 2 > size) {
		return;
	}//if there is a left child but not a right child, and if priority of node at index
	 // is higher than the left child
	if (index * 2 <= size  && index*2 + 1 > size) {
		if (array[index].getPriority() < array[index*2].getPriority()) {
			return;
		}else {//if there is a left child but not a right child, and priority of node
			//at index is lower than left child
			temp = array[index];
			array[index] = array[index*2];
			array[index*2] = temp;
			return;
		}
	}
	//if priority of node at index is higher than both children, don't bubble down and return.
	if (array[index].getPriority() < array[index*2].getPriority() &&
		array[index].getPriority() < array[index*2 + 1].getPriority()) {
		return;
	}
		
		
		//if left child is higher priority than right child
		if (array[index*2].getPriority() < array[index*2 + 1].getPriority()) {
			temp = array[index];
			array[index] = array[index*2];
			array[index*2] = temp;
			bubbleDown(index*2);
			return;
		} //if right child is higher priority than left child
		else if (array[index*2].getPriority() > array[index*2 + 1].getPriority()) {
			temp = array[index];
			array[index] = array[index*2 + 1];
			array[index*2 + 1] = temp;
			bubbleDown(index*2+1);
			return;
		}
}

private int getLeftChildIndex(int currentIndex) {
	return 2 * currentIndex;
}

private int getRightChildIndex(int currentIndex) {
	return 2 * currentIndex + 1;
}

private int getParentIndex(int currentIndex) {
	return (currentIndex) / 2;
}

private String getParentValue(int parentPriority) {
	EntryPair entry = new EntryPair("", 0);
	entry.priority = parentPriority;
	for (int i = 0; i < array.length; i++) {
		if (array[i].getPriority() == parentPriority) {
			entry.value = array[i].getValue();
		}
	}
	return entry.getValue();
}
}