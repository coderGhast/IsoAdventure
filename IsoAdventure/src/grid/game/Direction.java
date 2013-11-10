package grid.game;

public enum Direction {
	NORTH(1), EAST(2), SOUTH(3), WEST(4);
	
	private int directionNumberValuesForKeys;
	
	Direction(int dir){
		directionNumberValuesForKeys = dir;
	}
	
	protected int getDirection(){
		return directionNumberValuesForKeys;
	}
}
