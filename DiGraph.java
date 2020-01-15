package A6_Dijkstra;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


public class DiGraph implements DiGraphInterface {
	
	public int numNodes;
	public int numEdges;
	Set<Long> nodeIds;
	Set<Long> edgeIds;
	Set<Edge> edges;
	Map<String, Node> nodeMap;


  public DiGraph ( ) { // default constructor
	  nodeMap = new HashMap<String, Node>();
		edgeIds = new HashSet<Long>();
		nodeIds = new HashSet<Long>();
		edges = new HashSet<Edge>();


  }
  

@Override
public boolean addNode(long idNum, String label) {
	if(idNum<0 || label==null || nodeIds.contains(idNum)|| nodeMap.containsKey(label)){
		return false; //idNum/label/duplicate ID/ duplicate label tests
	}else{//updating maps/sets/etc.
		Node n = new Node(idNum, label);
		nodeMap.put(label, n);
		nodeIds.add(idNum);
		numNodes++;
		return true;

	}
}

@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	Edge edge = new Edge(idNum, sLabel, dLabel, weight, eLabel);
	if(edgeIds.contains(idNum)|| idNum<0 || weight<0){//duplicate tests
		return false;
	}
	if(nodeMap.containsKey(sLabel)==false || nodeMap.containsKey(dLabel)==false){//if nodes specified aren't in map return false
		return false;
	}
	
	for(Edge e : nodeMap.get(dLabel).inEdges){
		if(e.sameEdge(edge)){
			return false;
		}
	}
	//updating all of the maps/sets/etc.
	edgeIds.add(idNum);
	nodeMap.get(sLabel).outEdges.add(edge);
	nodeMap.get(dLabel).inEdges.add(edge);
	nodeMap.get(dLabel).numInDegrees++;
	edges.add(edge);
	numEdges++;
	return true;
}

@Override
public boolean delNode(String label) {
	Node node = nodeMap.get(label);
	if(node==null) {//make sure node is in the map
		return false;
	}
	//delete edges that connect to the node to delete.
	
	for (int i = 0; i < node.inEdges.size(); i++) {
		delEdge(node.inEdges.get(0).getSLabel(), node.inEdges.get(0).dLabel);
	}
	
	for (int i = 0; i < node.outEdges.size(); i++) {
		delEdge(node.outEdges.get(0).getSLabel(), node.inEdges.get(0).dLabel);
	}
	//updating maps/sets/etc.
	nodeMap.remove(label);
	numNodes--;
	nodeIds.remove(node.idNum);
	return true;	
}

@Override
public boolean delEdge(String sLabel, String dLabel) {
	Edge edge = new Edge(1, sLabel, dLabel, 1, null);
	//figure out if vertex match sLabel and dLabel, if not return false
	Node sourceNode= nodeMap.get(sLabel);
	Node destNode = nodeMap.get(dLabel);
	if(sourceNode==null || destNode==null){
		return false;
	}//actual deletion && updating maps/sets/etc.
	for(Edge e : sourceNode.outEdges){
		if(e.sameEdge(edge)){
			sourceNode.outEdges.remove(e);
			destNode.inEdges.remove(e);
			nodeMap.get(dLabel).numInDegrees--;
			edgeIds.remove(e.idNum);
			edges.remove(e);
			numEdges--;
			return true;
		}
	}
	return false;
}

@Override
public long numNodes() {
	return numNodes;
}

@Override
public long numEdges() {
	return numEdges;
}

@Override
public String[] topoSort() {
	String[] topoArray = new String[numNodes];
	ArrayList<String> topoArrayList = new ArrayList<String>();
	Queue<Node> topoQueue = new LinkedList<Node>();
	for (Node node : nodeMap.values()) {//first in-degree check
		if (node.getNumInDegrees() == 0) {
			topoQueue.add(node);
		}
	}
		while (!topoQueue.isEmpty()) {
			Node temp = topoQueue.poll();
			topoArrayList.add(temp.label);
			
			for (Edge edge : temp.outEdges) {
				nodeMap.get(edge.dLabel).numInDegrees--;//updating other in-degrees
				if (nodeMap.get(edge.getDLabel()).getNumInDegrees() == 0) {//other in-degree checks
					topoQueue.add(nodeMap.get(edge.getDLabel()));
				}
			}
		}
	
	if (topoArrayList.size() != nodeMap.size()) {
		return null;
	}
	topoArrayList.toArray(topoArray);
	return topoArray;
}


public ShortestPathInfo[] shortestPath(String label) {
	ShortestPathInfo[] pathArray = new ShortestPathInfo[numNodes];
	int i = 0;
	//quick check to make sure node is in map
	Node node = nodeMap.get(label);
	if (node == null) {
		return null;
	}
	MinBinHeap pathHeap = new MinBinHeap();
	EntryPair source = new EntryPair(label, 0);
	node.currCheapestPath = 0;
	node.prevNode = null;
	pathHeap.insert(source);
	for (Node initDist : nodeMap.values()) {
		initDist.currCheapestPath = Integer.MAX_VALUE;
	}
	while (pathHeap.size() != 0) {
		String nString = pathHeap.getMin().getValue();
		Node n = nodeMap.get(nString);
		if (n.knownDist == false) {
		n.knownDist = true;
		n.currCheapestPath = pathHeap.getMin().getPriority();
		ShortestPathInfo spi = new ShortestPathInfo(pathHeap.getMin().getValue(), pathHeap.getMin().getPriority());
		pathArray[i] = spi;
		i++;
		pathHeap.delMin();
		for (Edge edge : n.outEdges) {
			Node nd = nodeMap.get(edge.getDLabel());
			if (nd.knownDist == false) { //how to check and see if dest node is already in the spi array?		
			//how to get info on weight for the second/third, etc. time that the node is a destination node?
			//i.e. nd's label has been used already, need to get it's currentCheapestPath but don't know how to get the info.
			if (nd.currCheapestPath > (int) (n.currCheapestPath + edge.getWeight())) {//if the node adjacent to current node's current path is larger and needs to be updated
				nd.currCheapestPath = (int) (n.currCheapestPath + edge.getWeight());
				nd.prevNode = n;
				EntryPair rep = new EntryPair(nd.getLabel(), nd.currCheapestPath);
				pathHeap.insert(rep);
					} 
				}
			}
		} else {
			pathHeap.delMin();
		}
	}
	
	if (pathArray[pathArray.length-1] == null) {
		for (Node leftover : nodeMap.values()) {
			if (leftover.knownDist == false) {
				ShortestPathInfo lspi = new ShortestPathInfo(leftover.getLabel(), -1);
				pathArray[i] = lspi;
				i++;
			}
		}
	}
	return pathArray;
}




  //helper methods for testing
public String[] getNodes() {
	String[] nodes = new String[nodeMap.size()];
	int i = 0;
	for (Node node : nodeMap.values()) {
		nodes[i] = node.getLabel();
		i++;
	}
		return nodes;
}

public String[] getSourceEdges() {
	String[] sourceEdges = new String[nodeMap.size()];
	return sourceEdges;
}
}