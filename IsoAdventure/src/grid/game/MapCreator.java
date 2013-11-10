package grid.game;

import java.util.Random;

public class MapCreator {
	private MapStructure[][] map;
	private int mapWidth;
	private int mapHeight;
	private Random numGenerator = new Random();
	private int activeX;
	private int activeY;
	
	private Pathfinding pathFinder;

	public MapCreator(int givenMapHeight, int givenMapWidth) {
		map = new MapStructure[givenMapHeight + 2][givenMapWidth + 2];
		mapWidth = map.length - 2;
		mapHeight = map[0].length - 2;
		populate();
		setupActiveBlock();
		pathFinder = new Pathfinding();
	}

	private void populate() {

		fillGroundLevel();
		//createSurroundingWalls();
		//createCentralWalls();

		for (int i = 0; i < 1; i++) {
			createTowerBlock(randomXCoordinate(), TowerLength.getRandom()
					.getSize(), randomYCoordinate(), TowerLength.getRandom()
					.getSize(), randomTowerHeight());
		}
		setAStarTest(7, 7);
	}

	private void fillGroundLevel() {
		mapFullIteration(0, map.length, 0, map[0].length, new GridGroundBlock());
	}

	private void setAStarTest(int x, int y){
		map[x][y] = new PlayerPosition();
	}
	
	private void setupActiveBlock() {
		activeX = numGenerator.nextInt(mapWidth - 1) + 1;
		activeY = numGenerator.nextInt(mapHeight - 1) + 1;
		map[activeX][activeY].toggleIsActive();
	}

	private void createSurroundingWalls() {
		mapXIteration(1, mapWidth, 1, new GridWallBlock());
		mapXIteration(1, mapWidth, (mapHeight), new GridWallBlock());

		mapYIteration(1, mapHeight, 1, new GridWallBlock());
		mapYIteration(1, mapHeight, (mapWidth), new GridWallBlock());

	}

	private void createCentralWalls() {
		int lowXThird = (mapWidth) / 3;
		int lowYThird = (mapHeight) / 3;
		int highXThird = (((mapWidth + 2) / 3) * 2);
		int highYThird = (((mapHeight + 2) / 3) * 2);

		mapXIteration(lowXThird, (highXThird), lowYThird,
				new GridCenterWallBlock());
		mapXIteration(lowXThird, (highXThird), highYThird,
				new GridCenterWallBlock());

		mapYIteration(lowYThird, highYThird, lowXThird,
				new GridCenterWallBlock());
		mapYIteration(lowYThird, highYThird, highXThird,
				new GridCenterWallBlock());
	}

	private void createTowerBlock(int xCoordinate, int towerXLength,
			int yCoordinate, int towerYLength, int towerHeight) {

		if (yCoordinate < towerYLength && yCoordinate - towerYLength <= 1) {

			yCoordinate = (towerYLength - yCoordinate) + (yCoordinate + 1);
		} else if (yCoordinate - towerYLength <= 1) {

			towerYLength = towerYLength - 1;
		}

		if (towerYLength == 0) {

			yCoordinate += 1;
			towerYLength += 1;
		}

		if (towerXLength + xCoordinate >= (mapWidth - 1)) {
			xCoordinate = xCoordinate - towerXLength;
		}

		towerXLength = xCoordinate + towerXLength;
		towerYLength = yCoordinate - towerYLength;

		mapFullIterationReverseJ(xCoordinate, towerXLength, yCoordinate,
				towerYLength, new GridTowerBlock(towerHeight));
	}

	private void mapXIteration(int forStart, int forEnd, int staticCoordinate,
			GenericMapStructure genericMapStructure) {
		for (int n = forStart; n <= forEnd; n++) {
			map[n][staticCoordinate] = discoverStructureType(genericMapStructure);
		}
	}

	private void mapYIteration(int forStart, int forEnd, int staticCoordinate,
			GenericMapStructure genericMapStructure) {
		for (int n = forStart; n <= forEnd; n++) {
			map[staticCoordinate][n] = discoverStructureType(genericMapStructure);
		}
	}

	private void mapFullIteration(int forOneStart, int forOneEnd,
			int forTwoStart, int forTwoEnd,
			GenericMapStructure genericMapStructure) {
		for (int i = forOneStart; i < forOneEnd; i++) {
			for (int j = forTwoStart; j < forTwoEnd; j++) {
				genericMapStructure = new GridGroundBlock();
				map[i][j] = genericMapStructure;
			}
		}
	}

	private void mapFullIterationReverseJ(int forOneStart, int forOneEnd,
			int forTwoStart, int forTwoEnd,
			GenericMapStructure genericMapStructure) {
		for (int i = forOneStart; i <= forOneEnd; i++) {
			for (int j = forTwoStart; j >= forTwoEnd; j--) {
				genericMapStructure = new GridTowerBlock(
						genericMapStructure.getNum_tiles());
				map[i][j] = genericMapStructure;
			}
		}
	}

	private GenericMapStructure discoverStructureType(
			GenericMapStructure genericMapStructure) {
		if (genericMapStructure.getTiles(0) == BlockGraphicType.GENERIC_WALL
				.getTileType()) {
			genericMapStructure = new GridCenterWallBlock();
		}
		/*
		 * // For LATER 'Types' of wall. if (genericMapStructure.getTiles(0) ==
		 * BlockGraphicType.------SOME WALL TYPE-----.getTileType()) {
		 * genericMapStructure = new GridWallBlock(); }
		 */
		return genericMapStructure;
	}

	protected MapStructure[][] getMap() {
		return map;
	}

	protected int getMapHeight() {
		return mapHeight;
	}

	protected int getMapWidth() {
		return mapWidth;
	}

	protected void setCoords(int x, int y, MapStructure value){
		map[x][y] = value;
	}
	
	protected MapStructure getValue(int x, int y){
		return map[x][y];
	}
	
	private int randomXCoordinate() {
		return numGenerator.nextInt(mapWidth - 3) + 2;

	}

	private int randomYCoordinate() {
		return numGenerator.nextInt(mapHeight - 3) + 2;
	}

	private int randomTowerHeight() {
		return numGenerator.nextInt(9) + 2;
	}

	protected int getActiveX() {
		return activeX;
	}

	protected int getActiveY() {
		return activeY;
	}
	
	protected void findPath(int givenX, int givenY){
		pathFinder.findPath(givenX, givenY, 7, 7, map);
	}
}
