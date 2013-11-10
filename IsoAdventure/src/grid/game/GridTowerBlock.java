package grid.game;

public class GridTowerBlock extends GenericMapStructure{
	
	GridTowerBlock(int towerHeight){
		setNum_tiles(towerHeight);
		for (int n = 0; n < getNum_tiles(); n++) {
			setTiles(n, BlockGraphicType.GENERIC_WALL.getTileType());
			setHeight(n, n * 50);
		}
	}
	
}
