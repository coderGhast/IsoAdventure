package grid.game;

public enum BlockGraphicType {
	ACTIVE(30, 30), PLAYER_POSITION(31,31),
	
	GRASS_PLAIN(0, 1), GRASS_ROCKS(1, 2), 
	
	GENERIC_WALL(101, 10), 
	WALL_LEFT(10, 10), WALL_RIGHT(14, 10), 
	WALL_LEFT_CORNER_EDGE(11, 10), WALL_LEFT_CORNER_MEETING(12, 10),
	WALL_RIGHT_CORNER_EDGE(15,10), WALL_RIGHT_CORNER_MEETING(16, 10),
	WALL_LEFT_WINDOW(13, 10),
	
	ROOF_FLOOR(20, 10), 
	ROOF_BOTTOM_LEFT_WALL(22, 10), ROOF_TOP_LEFT_WALL(24, 10), 
	ROOF_BOTTOM_RIGHT_WALL(21, 10), ROOF_TOP_RIGHT_WALL(23, 10);
	
	private int graphicType;
	private int tileType;

	BlockGraphicType(int graphicType, int tileType) {
		this.graphicType = graphicType;
		this.tileType = tileType;
	}

	protected int getGraphicType() {
		return graphicType;
	}
	
	protected int getTileType(){
		return tileType;
	}

}
