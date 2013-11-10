package grid.game;

public interface MapStructure {

	public int getNum_tiles();

	public void setNum_tiles(int num_tiles);

	public int getTiles(int location);

	public void setTiles(int location, int tile);

	public int[] getHeight();

	public void setHeight(int location, int heightArg);

	public void toggleIsActive();

	public boolean getIsActive();
	
	public boolean getIsPath();

	public boolean equals(GenericMapStructure o);

	public void togglePath();

}
