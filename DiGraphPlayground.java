package A6_Dijkstra;

public class DiGraphPlayground {

  public static void main (String[] args) {
  
      // thorough testing is your responsibility
      //
      // you may wish to create methods like 
      //    -- print
      //    -- sort
      //    -- random fill
      //    -- etc.
      // in order to convince yourself your code is producing
      // the correct behavior
	  //exTest();
	  //addNodeTest();
	  //delEdgeTest();
	  //topoSortTest2();
	  shortestPathTest0();
	  shortestPathTest1();
	  shortestPathTest2();
	  shortestPathTest3();
	  shortestPathTest4();
	  shortestPathTest5();
    }
  
  	public static void shortestPathTest0() {
  		DiGraph d = new DiGraph();
  		d.addNode(0, "a");
  		d.addNode(1, "b");
  		d.addNode(2, "c");
  		d.addEdge(0, "a", "b", 3, null);
  		d.addEdge(1, "b", "c", 4, null);
  		d.addEdge(2, "a", "c", 5, null);
  		printShortest(d.shortestPath("a"));
  	}
  	
  	public static void shortestPathTest1() {
  		DiGraph d = new DiGraph();
  		d.addNode(0, "a");
  		d.addNode(1, "b");
  		d.addNode(2, "c");
  		d.addEdge(0, "a", "b", 3, null);
  		d.addEdge(1, "b", "c", 4, null);
  		d.addEdge(2, "a", "c", 10, null);
  		printShortest(d.shortestPath("a"));
  	}

  	public static void shortestPathTest2() {
  		DiGraph d = new DiGraph();
  		d.addNode(0, "a");
  		d.addNode(1, "b");
  		d.addNode(2, "c");
  		d.addNode(3, "d");
  		d.addEdge(0, "a", "b", 1, null);
  		d.addEdge(1, "b", "c", 2, null);
  		d.addEdge(2, "c", "a", 3, null);
  		d.addEdge(3, "c", "d", 2, null);
  		d.addEdge(4, "d", "b", 1, null);
  		printShortest(d.shortestPath("a"));
  	}
  	
  	public static void shortestPathTest3() {
  		DiGraph d = new DiGraph();
  		d.addNode(1, "p");
  		d.addNode(4, "a");
  		d.addNode(3, "g");
  		d.addNode(2, "e");
  		d.addEdge(0, "p", "a", 10, null);
  		d.addEdge(1, "a", "g", 12, null);
  		d.addEdge(2, "g", "e", 1, null);
  		d.addEdge(3, "e", "p", 6, null);
  		d.addEdge(4, "p", "g", 4, null);
  		d.addEdge(5, "a", "e", 100, null);
  		d.addEdge(6, "a", "p", 9, null);
  		d.addEdge(7, "e", "a", 3, null);
  		d.addEdge(8, "g", "a", 15, null);
  		d.addEdge(9, "p", "e", 8, null);
  		d.addEdge(10, "g", "p", 2, null);
  		printShortest(d.shortestPath("p"));
  	}
  	
  	public static void shortestPathTest4() {
  		DiGraph d = new DiGraph();
  		d.addNode(0, "0");
  		d.addNode(1, "1");
  		d.addNode(2, "2");
  		d.addNode(3, "3");
  		d.addNode(4, "4");
  		d.addNode(5, "5");
  		d.addNode(6, "6");
  		d.addEdge(0, "4", "5", 2, null);
  		d.addEdge(1, "0", "5", 3, null);
  		d.addEdge(2, "3", "2", 6, null);
  		d.addEdge(3, "6", "1", 4, null);
  		d.addEdge(4, "4", "0", 1, null);
  		printShortest(d.shortestPath("0"));
  	}
  	
  	
  	public static void shortestPathTest5() {
  		DiGraph d = new DiGraph();
  		d.addNode(0, "a");
  		d.addNode(1, "b");
  		d.addNode(2, "c");
  		d.addNode(3, "d");
  		d.addNode(4, "e");
  		d.addEdge(0, "a", "b", 1, null);
  		d.addEdge(1, "b", "c", 1, null);
  		d.addEdge(2, "a", "c", 3, null);
  		d.addEdge(3, "c", "d", 2, null);
  		d.addEdge(4, "c", "e", 5, null);
  		d.addEdge(5, "b", "d", 2, null);
  		d.addEdge(6, "d", "e", 3, null);
  		d.addEdge(7, "b", "e", 7, null);
  		d.addEdge(8, "a", "e", 9, null);
  		d.addEdge(9, "a", "d", 5, null);
  		printShortest(d.shortestPath("a"));
  	}
  	
  public static void printShortest(ShortestPathInfo[] s) {
	  for (int i = 0; i < s.length; i++) {
		  System.out.println(s[i]);
	  }
  }
  
  
  
  	public static void topoSortTest2() {
  		DiGraph d = new DiGraph();
  		d.addNode(0, "0");
  		d.addNode(1, "1");
  		d.addNode(2, "2");
  		d.addNode(3, "3");
  		d.addNode(4, "4");
  		d.addNode(5, "5");
  		d.addNode(6, "6");
  		d.addEdge(0, "4", "5", 0, null);
  		d.addEdge(1, "0", "5", 0, null);
  		d.addEdge(2, "3", "2", 0, null);
  		d.addEdge(3, "6", "1", 0, null);
  		d.addEdge(4, "4", "0", 0, null);
  		printTOPO(d.topoSort());
  	}
  
  	public static void topoSortTest() {
  		DiGraph d = new DiGraph();
  		d.addNode(0, "a");
  		d.addNode(1, "b");
  		d.addNode(2, "c");
  		d.addNode(3, "d");
  		d.addEdge(0, "a", "b", 0, null);
  		d.addEdge(1, "b", "c", 0, null);
  		d.addEdge(2, "a", "d", 0, null);
  		d.addEdge(3, "d", "c", 0, null);
  		printTOPO(d.topoSort());
  	}
  
  	public static void delEdgeTest() {
  		DiGraph d = new DiGraph();
  		System.out.println(d.delEdge("f", "s"));
  		d.addNode(1, "f");
  		d.addNode(3,"s");
  		d.addEdge(0, "f", "s", 0, null);
  		System.out.println(d.delEdge("f", "s"));
  		System.out.println(d.delEdge("f", "s"));
  		System.out.println(d.addEdge(0, "f", "s", 0, null));
  		System.out.println(d.delEdge("f", "s"));
  		
  	}
  
  
  	public static void addNodeTest() {
  		DiGraph d = new DiGraph();
  		d.addNode(1, "f");
  		printNodes(d);
  	}
  
  
  
    public static void exTest(){
      DiGraph d = new DiGraph();
      d.addNode(1, "f");
      d.addNode(3, "s");
      d.addNode(7, "t");
      d.addNode(0, "fo");
      d.addNode(4, "fi");
      d.addNode(6, "si");
      d.addEdge(0, "f", "s", 0, null);
      d.addEdge(1, "f", "si", 0, null);
      d.addEdge(2, "s", "t", 0, null);
      d.addEdge(3, "fo", "fi", 0, null);
      d.addEdge(4, "fi", "si", 0, null);
      System.out.println("numEdges: "+d.numEdges());
      System.out.println("numNodes: "+d.numNodes());
      printTOPO(d.topoSort());
      
    }
    
    public static void printNodes(DiGraph d) {
    	for (String node : d.getNodes()) {
    		System.out.println(node);
    	}
    }
    
    public static void printEdges(DiGraph d) {
    	for (String edge : d.getSourceEdges()) {
    		
    	}
    }
    
    
    public static void printTOPO(String[] toPrint){
      System.out.print("TOPO Sort: ");
      for (String string : toPrint) {
      System.out.print(string+" ");
    }
      System.out.println();
    }

}