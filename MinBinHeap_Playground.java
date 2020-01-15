package MinBinHeap_A3;

public class MinBinHeap_Playground {
  public static void main(String[] args){   
    //TestBuild();
	//insertTest();
  //buildTest();
	// sizeTest();
	 //delMinTest();
	  //delMinTest2();
	  everythingTest();
  }
  
  public static void everythingTest() {
	  MinBinHeap mbh = new MinBinHeap();
	  EntryPair ep1 = new EntryPair("c", 1);
	  EntryPair ep2 = new EntryPair("d", 6);
	  EntryPair ep3 = new EntryPair("e", 5);
	  EntryPair ep4 = new EntryPair("f", 4);
	  EntryPair ep5 = new EntryPair("g", 7);
	  EntryPair ep6 = new EntryPair("h", 2);
	  EntryPair ep7 = new EntryPair("i", 9);
	  EntryPair ep8 = new EntryPair("j", 0);
	  EntryPair ep9 = new EntryPair("p", 0);
	  EntryPair ep10 = new EntryPair("q", 10);
	  mbh.insert(ep1);
	  mbh.insert(ep2);
	  mbh.insert(ep3);
	  mbh.insert(ep4);
	  mbh.insert(ep5);
	  mbh.insert(ep6);
	  mbh.insert(ep7);
	  mbh.insert(ep8);
	  printHeap(mbh.getHeap(), mbh.size());
	  mbh.delMin();
	  printHeap(mbh.getHeap(), mbh.size());
	  mbh.delMin();
	  printHeap(mbh.getHeap(), mbh.size());
	  mbh.delMin();
	  printHeap(mbh.getHeap(), mbh.size());
	  System.out.println(mbh.size());
	  System.out.println(mbh.getMin());
	  mbh.insert(ep9);
	  mbh.insert(ep10);
	  System.out.println(mbh.getMin());
	  System.out.println(mbh.size());	  
	  
			  
  }
  
  
  public static void delMinTest2() {
	  MinBinHeap mbh = new MinBinHeap();
	  EntryPair ep1 = new EntryPair("a", 1);
	  mbh.insert(ep1);
	  printHeap(mbh.getHeap(), mbh.size());
	  mbh.delMin();
	  printHeap(mbh.getHeap(), mbh.size());
	  mbh.insert(ep1);
	  printHeap(mbh.getHeap(), mbh.size());
  }
  public static void delMinTest() {
	  MinBinHeap mbh = new MinBinHeap();
	  EntryPair ep1 = new EntryPair("a", 4);
	  EntryPair ep2 = new EntryPair("b", 1);
	  EntryPair ep3 = new EntryPair("c", 2);
	  EntryPair ep4 = new EntryPair("d", 2);
	  EntryPair ep5 = new EntryPair("e", 1);
			  
	  mbh.insert(ep1); 
	  mbh.insert(ep2);
	  mbh.insert(ep3);
	  mbh.delMin();
	  mbh.insert(ep4);
	  mbh.insert(ep5);
	  printHeap(mbh.getHeap(), mbh.size());
	  mbh.delMin();
	  printHeap(mbh.getHeap(), mbh.size());
	  mbh.delMin();
	  printHeap(mbh.getHeap(), mbh.size());
	  
  }
  
  
  public static void buildTest() {
	  MinBinHeap mbh = new MinBinHeap();
	  EntryPair[] collection = new EntryPair[5];
	  collection[0] = new EntryPair("a", 5);
	  collection[1] = new EntryPair("b", 4);
	  collection[2] = new EntryPair("c", 3);
	  collection[3] = new EntryPair("d", 2);
	  collection[4] = new EntryPair("e", 1);
	  mbh.build(collection);
	  printHeapCollection(collection);
      printHeap(mbh.getHeap(), mbh.size());
			  
  }
  public static void sizeTest() {
	  MinBinHeap mbh = new MinBinHeap();
	  EntryPair ep1 = new EntryPair("A", 1);
	  EntryPair ep2 = new EntryPair("B", 2);
	  mbh.insert(ep1);
	  mbh.insert(ep2);
  }
  public static void insertTest() {
	  MinBinHeap mbh = new MinBinHeap();
	  EntryPair ep1 = new EntryPair("a", 5);
	  EntryPair ep2 = new EntryPair("b", 4);
	  EntryPair ep3 = new EntryPair("c", 3);
	  EntryPair ep4 = new EntryPair("d", 2);
	  EntryPair ep5 = new EntryPair("e", 1);
	  mbh.insert(ep1);
	  mbh.insert(ep2);
	  mbh.insert(ep3);
	  mbh.insert(ep4);
	  mbh.insert(ep5);
	  printHeap(mbh.getHeap(), mbh.size());
  }
  
  public static void TestBuild(){ 
    // constructs a new minbinheap, constructs an array of EntryPair, 
    // passes it into build function. Then print collection and heap.
    MinBinHeap mbh= new MinBinHeap();
    EntryPair[] collection= new EntryPair[8];
    collection[0]=new EntryPair("i",3);
    collection[1]=new EntryPair("b",5);
    collection[2]=new EntryPair("c",1);
    collection[3]=new EntryPair("d",0);
    collection[4]=new EntryPair("e",46);
    collection[5]=new EntryPair("f",5);
    collection[6]=new EntryPair("g",6);
    collection[7]=new EntryPair("h",17);
    mbh.build(collection);
    printHeapCollection(collection);
    printHeap(mbh.getHeap(), mbh.size());
  }
  
  public static void printHeapCollection(EntryPair[] e) { 
    //this will print the entirety of an array of entry pairs you will pass 
    //to your build function.
    System.out.println("Printing Collection to pass in to build function:");
    for(int i=0;i < e.length;i++){
      System.out.print("("+e[i].value+","+e[i].priority+")\t");
    }
    System.out.print("\n");
  }
  
  public static void printHeap(EntryPair[] e,int len) { 
    //pass in mbh.getHeap(),mbh.size()... this method skips over unused 0th index....
    System.out.println("Printing Heap");
    System.out.println(len);
    for(int i=1;i < len+1;i++){
      System.out.print("("+e[i].value+","+e[i].priority+")\t");
    }
    System.out.print("\n");
   
  }
}