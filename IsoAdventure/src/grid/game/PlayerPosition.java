package grid.game;

public class PlayerPosition extends GenericMapStructure {

	PlayerPosition() {
		setNum_tiles(1);
		setTiles(0, BlockGraphicType.PLAYER_POSITION.getTileType());
		setHeight(0, 0);
	}
}
