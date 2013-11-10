package grid.game;

public class GridCenterWallBlock extends GenericMapStructure{

	GridCenterWallBlock() {
		setNum_tiles(3);
		for (int n = 0; n < getNum_tiles(); n++) {
			setTiles(n, BlockGraphicType.GENERIC_WALL.getTileType());
			setHeight(n, n * 50);
		}
	}
}
