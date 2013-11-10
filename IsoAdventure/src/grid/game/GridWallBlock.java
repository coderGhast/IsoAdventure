package grid.game;

public class GridWallBlock extends GenericMapStructure {

	GridWallBlock() {
		setNum_tiles(2);
		for (int n = 0; n < getNum_tiles(); n++) {
			setTiles(n, BlockGraphicType.GENERIC_WALL.getTileType());
			setHeight(n, n * 50);
		}
	}

}
