package A6_Dijkstra;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.concurrent.ConcurrentHashMap;

public class Node {
	long idNum;
	String label;
	long numInDegrees = 0;
	ArrayList<Edge> outEdges;
	ArrayList<Edge> inEdges;
	boolean knownDist;
	int currCheapestPath = 0;
	Node prevNode;
	
	public Node(long idNum, String label) {
		this.idNum = idNum;
		this.label = label;
		this.outEdges = new ArrayList< Edge>();
		this.inEdges = new ArrayList< Edge>();
		this.knownDist = false;
	}
	
	public long getIdNum() {
		return idNum;
	}
	
	public String getLabel() {
		return label;
	}
	
	public long getNumInDegrees() {
		return numInDegrees;
	}
	
}
