package A6_Dijkstra;

public class Edge {
	long idNum;
	long weight;
	String eLabel;
	String dLabel;
	String sLabel;
	
	public Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		this.idNum = idNum;
		this.eLabel = eLabel;
		this.dLabel = dLabel;
		this.sLabel = sLabel;
		this.weight = weight;
	}
	
	public long getIdNum() {
		return idNum;
	}
	
	public String getELabel() {
		return eLabel;
	}
	
	public String getDLabel() {
		return dLabel;
	}
	
	public String getSLabel() {
		return sLabel;
	}
	
	public long getWeight() {
		return weight;
	}
	
	public boolean sameEdge(Edge edge) {
		if (edge.sLabel.equals(this.sLabel) && edge.dLabel.equals(this.dLabel)) {
			return true;
		}
		return false;
	}
	
}
