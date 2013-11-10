package grid.game;

public enum TowerLength {
	SINGLE(0), TINY(1), SMALL(2), MEDIUM(3), LARGE(4);

	
	private final int size;
	
	TowerLength(int size){
		this.size = size;
	}
	
	protected static TowerLength getRandom() {
		return values()[(int) (Math.random() * values().length)];
	}
	
	protected final int getSize(){
		return size;
	}

}
