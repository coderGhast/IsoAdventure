package grid.game;

public class AStarNode {

	private int xCoord;
	private int yCoord;
	private int h;
	private int g;
	private int f;
	private AStarNode parent;
	private int cost;
	protected MapStructure mapPiece;

	public AStarNode(int x, int y, MapStructure structure) {
		xCoord = x;
		yCoord = y;
		mapPiece = structure;
	}

	protected void calculateF() {
		
	}

	
	
	protected void calculateH(){
	
		
		
	}
	
	protected int getxCoord() {
		return xCoord;
	}

	protected void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	protected int getyCoord() {
		return yCoord;
	}

	protected void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	protected int getH() {
		return h;
	}

	protected void setH(int h) {
		this.h = h;
	}

	protected int getG() {
		return g;
	}

	protected void setG(int g) {
		this.g = g;
	}

	protected int getF() {
		return f;
	}

	protected void setF(int f) {
		this.f = f;
	}

	protected AStarNode getParent() {
		return parent;
	}

	protected void setParent(AStarNode parent) {
		this.parent = parent;
	}

	protected int getCost() {
		return cost;
	}

	protected void setCost(int cost) {
		this.cost = cost;
	}

	protected MapStructure getMapPiece() {
		return mapPiece;
	}

	protected void setMapPiece(MapStructure mapPiece) {
		this.mapPiece = mapPiece;
	}

}
