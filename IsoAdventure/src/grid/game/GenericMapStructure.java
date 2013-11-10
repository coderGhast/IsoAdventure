package grid.game;

public abstract class GenericMapStructure implements MapStructure {
	protected int num_tiles;
	protected int[] tiles = new int[10]; // assuming a max of 10 tiles per map
											// cord
	protected int[] height = new int[10]; // also assuming a max of 10
	protected boolean isActive;
	protected boolean isPath;

	public int getNum_tiles() {
		return num_tiles;
	}

	public void setNum_tiles(int num_tiles) {
		this.num_tiles = num_tiles;
	}

	public int getTiles(int location) {
		return tiles[location];
	}

	public void setTiles(int location, int tile) {
		tiles[location] = tile;
	}

	public int[] getHeight() {
		return height;
	}

	public void setHeight(int location, int heightArg) {
		height[location] = heightArg;
	}

	public void toggleIsActive() {
		if (isActive) {
			isActive = false;
		} else {
			isActive = true;
		}
	}
	
	public void togglePath(){
		if (isPath) {
			isPath = false;
		} else {
			isPath = true;
		}
	}

	public boolean getIsActive() {
		return isActive;
	}
	
	public boolean getIsPath() {
		return isPath;
	}

	@Override
	public boolean equals(GenericMapStructure o) {
		if (o.getTiles(o.getNum_tiles() - 1) == getTiles(o.getNum_tiles() - 1)) {
			return true;
		} else {
			return false;
		}
	}

}
