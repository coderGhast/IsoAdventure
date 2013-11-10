package grid.game;

import java.util.Random;

public class GridGroundBlock extends GenericMapStructure{
	private Random numGen;
	GridGroundBlock(){
		numGen = new Random();
		setNum_tiles(1);
		if(numGen.nextInt(20)+1 >= 4){
			setTiles(0, BlockGraphicType.GRASS_PLAIN.getTileType());
		}else{
			setTiles(0, BlockGraphicType.GRASS_ROCKS.getTileType());
		}
		setHeight(0, 0);
	}
	
	
}
