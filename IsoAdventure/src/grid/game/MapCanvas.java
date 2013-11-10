package grid.game;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MapCanvas extends JPanel {
	private MapCreator map;
	private int mapWidth;
	private int mapHeight;
	private static final int POLYGON_WIDTH = 64;
	private static final int POLYGON_HEIGHT = 32;
	private static final int POLYGON_DIVISOR = 4;
	private static final int HALF_POLYGON_HEIGHT = POLYGON_HEIGHT / 2;
	private int activeX;
	private int activeY;

	private SpriteSheet spriteSheet;

	public MapCanvas(final int givenHeight, final int givenWidth) {
		spriteSheet = new SpriteSheet();
		map = new MapCreator(givenHeight, givenWidth);
		mapWidth = map.getMapWidth();
		mapHeight = map.getMapHeight();
		this.setBackground(Color.WHITE);
		activeX = map.getActiveX();
		activeY = map.getActiveY();
	}

	public final void paintComponent(final Graphics g) {
		super.paintComponent(g);
		int w = getWidth( );
		int h = getHeight( );
		 
		Graphics2D g2d = (Graphics2D)g;
		// Paint a gradient from top to bottom
		GradientPaint gp = new GradientPaint(
		    0, 0, Color.WHITE,
		    0, h, new Color(155, 209, 246) );

		g2d.setPaint( gp );
		g2d.fillRect( 0, 0, w, h );
		
		drawMap(g);
	}

	public final void drawMap(final Graphics g) {
		drawGroundLevel(g);
		drawHeightLevel(g);
	}

	private void drawGroundLevel(final Graphics g) {
		int x;
		int y;
		for (int i = 1; i <= mapWidth; i++) {
			x = (this.getSize().width) / 2 - (i * (POLYGON_WIDTH / 2));
			y = (this.getSize().width) / (POLYGON_HEIGHT / POLYGON_DIVISOR)
					+ (i * (POLYGON_HEIGHT / 2));
			for (int j = 1; j <= mapHeight; j++) {
				int tileToDraw = map.getValue(i, j).getTiles(0);//map[i][j].getTiles(0);
				int heightToDraw =  map.getValue(i, j).getNum_tiles();

				if (map.getValue(i,j).getIsActive()) {
					if (heightToDraw <= 1) {
						g.drawImage(spriteSheet
								.getSprite(BlockGraphicType.ACTIVE
										.getGraphicType()), x - 2, y - 2, null);
					}
				} else if(map.getValue(i, j).getIsPath()){
					if (heightToDraw <= 1) {
						g.drawImage(spriteSheet
								.getSprite(BlockGraphicType.PLAYER_POSITION
										.getGraphicType()), x - 2, y - 2, null);
					}
				} else {
					if (heightToDraw > 1) {
						checkCorners(i, j, x, y, 1, g);
					}
					if (tileToDraw >= 1 && tileToDraw <= 9 || tileToDraw == 31) {
						switch (tileToDraw) {
						case (1):
							g.drawImage(spriteSheet
									.getSprite(BlockGraphicType.GRASS_PLAIN
											.getGraphicType()), x - 2, y - 2,
									null);
							break;
						case (2):
							g.drawImage(spriteSheet
									.getSprite(BlockGraphicType.GRASS_ROCKS
											.getGraphicType()), x - 2, y - 2,
									null);
							break;
						case (31):
							g.drawImage(spriteSheet.getSprite(BlockGraphicType.PLAYER_POSITION.getGraphicType()), x -2, y -2, null);
						break;
						default:
							break;
						}
					}

				}
				x = x + POLYGON_WIDTH / 2;
				y = y + POLYGON_HEIGHT / 2;
			}

		}
	}

	private void drawHeightLevel(final Graphics g) {
		int x;
		int y;
		for (int i = 1; i <= mapWidth; i++) {
			x = (this.getSize().width) / 2 - (i * (POLYGON_WIDTH / 2));
			y = (this.getSize().width) / (POLYGON_HEIGHT / POLYGON_DIVISOR)
					+ (i * (POLYGON_HEIGHT / 2));
			for (int j = 1; j <= mapHeight; j++) {
				int tileToDraw = map.getValue(i,j).getTiles(0);
				int heightToDraw = map.getValue(i,j).getNum_tiles();

				if (heightToDraw > 1) {
					int calculable = (POLYGON_HEIGHT * heightToDraw) / 2;
					for (int h = 1; h < heightToDraw; h++) {

						drawAllWalls(x, y, h, g);
						checkCorners(i, j, x, y, h, g);
						drawOnTopOfRoof(
								spriteSheet.getSprite(BlockGraphicType.ROOF_FLOOR
										.getGraphicType()), i, j, x, y,
								calculable, g);
						checkIfBlockPath(i, j, x, y, calculable, g);
						checkIfBlockActive(i, j, x, y, calculable, g);
						drawRoofWalls(i, j, x, y, heightToDraw, calculable, g);
					}
				}
				x = x + POLYGON_WIDTH / 2;
				y = y + POLYGON_HEIGHT / 2;
			}
		}
	}

	protected final void moveActive(final int givenX, final int givenY,
			final int givenOldX, final int givenOldY) {
		if (givenX >= 1 && givenX <= mapWidth && givenY >= 1
				&& givenY <= mapHeight) {
			map.getValue(givenX, givenY).toggleIsActive();
			map.getValue(givenOldX, givenOldY).toggleIsActive();
			activeX = givenX;
			activeY = givenY;
			map.findPath(activeX, activeY);
			repaint();
		}
	}

	protected final void drawOnTopOfRoof(final Image image, final int i,
			final int j, final int x, final int y, final int calculable,
			final Graphics g) {
		g.drawImage(image, x - 2,
				((y + (HALF_POLYGON_HEIGHT)) - calculable) - 2, null);
	}

	protected final void drawWall(final Image image, final int x, final int y,
			final int h, final Graphics g) {
		int calculable = (POLYGON_HEIGHT * h) / 2;

		if (h >= 2) {
			g.drawImage(image, x - 2,
					((y + (HALF_POLYGON_HEIGHT)) - calculable) - 2, null);
		} else {
			g.drawImage(image, x - 2, y - 2, null);
		}
	}

	protected final void drawAllWalls(final int x, final int y, int h,
			Graphics g) {
		drawWall(spriteSheet.getSprite(BlockGraphicType.WALL_RIGHT
				.getGraphicType()), x, y, h, g);
		drawWall(spriteSheet.getSprite(BlockGraphicType.WALL_LEFT
				.getGraphicType()), x, y, h, g);
	}

	protected final void drawRoofWalls(int i, int j, int x, int y,
			int heightToDraw, int calculable, Graphics g) {
		if (i >= 1 && map.getValue(i - 1, j).getNum_tiles() != heightToDraw || i == 0) {
			drawOnTopOfRoof(
					spriteSheet.getSprite(BlockGraphicType.ROOF_TOP_RIGHT_WALL
							.getGraphicType()), i, j, x, y
							- (POLYGON_HEIGHT / POLYGON_DIVISOR), calculable, g);
		}
		if (j >= 1 && map.getValue(i, j-1).getNum_tiles() != heightToDraw || j == 0) {
			drawOnTopOfRoof(
					spriteSheet.getSprite(BlockGraphicType.ROOF_TOP_LEFT_WALL
							.getGraphicType()), i, j, x, y
							- (POLYGON_HEIGHT / POLYGON_DIVISOR), calculable, g);
		}
		if (i <= (mapWidth - 1) && map.getValue(i + 1, j).getNum_tiles() != heightToDraw
				|| i == mapWidth) {
			drawOnTopOfRoof(
					spriteSheet.getSprite(BlockGraphicType.ROOF_BOTTOM_LEFT_WALL
							.getGraphicType()), i, j, x, y, calculable, g);
		}

		if (j <= (mapHeight - 1)
				&& map.getValue(i, j +1).getNum_tiles() != heightToDraw
				|| j == mapHeight) {
			drawOnTopOfRoof(
					spriteSheet.getSprite(BlockGraphicType.ROOF_BOTTOM_RIGHT_WALL
							.getGraphicType()), i, j, x, y, calculable, g);
		}
	}

	protected final void checkCorners(int i, int j, int x, int y, int h,
			Graphics g) {
		checkForLeftWallCorners(i, j, x, y, h, g);
		checkForRightWallCorners(i, j, x, y, h, g);
	}

	protected final void checkForLeftWallCorners(int i, int j, int x, int y,
			int h, Graphics g) {
		if (map.getValue(i+1, j).getTiles(h) != BlockGraphicType.GENERIC_WALL
				.getTileType()
				&& map.getValue(i, j -1).getTiles(h) != BlockGraphicType.GENERIC_WALL
						.getTileType()) {
			drawWall(spriteSheet.getSprite(BlockGraphicType.WALL_LEFT_CORNER_EDGE
					.getGraphicType()), x, y, h, g);
		}
		if (map.getValue(i + 1, j).getTiles(h) != BlockGraphicType.GENERIC_WALL.getTileType() 
				&& map.getValue(i, j + 1).getTiles(h) != BlockGraphicType.GENERIC_WALL.getTileType()){
			drawWall(spriteSheet.getSprite(BlockGraphicType.WALL_LEFT_CORNER_MEETING
					.getGraphicType()), x, y, h, g);
				}
	}

	protected final void checkForRightWallCorners(int i, int j, int x, int y,
			int h, Graphics g) {
		if (map.getValue(i - 1, j).getTiles(h) != BlockGraphicType.GENERIC_WALL
				.getTileType()
				&& map.getValue(i, j + 1).getTiles(h) != BlockGraphicType.GENERIC_WALL
						.getTileType()) {
			drawWall(spriteSheet.getSprite(BlockGraphicType.WALL_RIGHT_CORNER_EDGE
					.getGraphicType()), x, y, h, g);
		}
		if (map.getValue(i + 1, j).getTiles(h) != BlockGraphicType.GENERIC_WALL.getTileType() 
				&& map.getValue(i, j + 1).getTiles(h) != BlockGraphicType.GENERIC_WALL.getTileType()){
			drawWall(spriteSheet.getSprite(BlockGraphicType.WALL_RIGHT_CORNER_MEETING
					.getGraphicType()), x, y, h, g);
				}
	}

	protected final boolean checkIfBlockActive(final int i, final int j,
			final int x, final int y, final int calculable, final Graphics g) {
		if (map.getValue(i, j).getIsActive() == true) {
			drawOnTopOfRoof(spriteSheet.getSprite(BlockGraphicType.ACTIVE
					.getGraphicType()), i, j, x, y, calculable, g);
			if (map.getValue(i, j).getNum_tiles() > 1) {
				if (map.getValue(i, j + 1).getNum_tiles() <= 1) {
					checkCorners(i, j, x, y, 1, g);

				}
				if (map.getValue(i + 1, j).getNum_tiles() <= 1) {
					checkCorners(i, j, x, y, 1, g);
				}
			}
			return true;
		} else {
			return false;

		}
	}
	
	protected final void checkIfBlockPath(final int i, final int j,
			final int x, final int y, final int calculable, final Graphics g) {
		if (map.getValue(i, j).getIsPath() == true) {
			drawOnTopOfRoof(spriteSheet.getSprite(BlockGraphicType.PLAYER_POSITION
					.getGraphicType()), i, j, x, y, calculable, g);
			if (map.getValue(i, j).getNum_tiles() > 1) {
				if (map.getValue(i, j + 1).getNum_tiles() <= 1) {
					checkCorners(i, j, x, y, 1, g);

				}
				if (map.getValue(i + 1, j).getNum_tiles() <= 1) {
					checkCorners(i, j, x, y, 1, g);
				}
			}

		}
	}

	public final void printMap() {
		for (int i = 1; i <= mapHeight; i++) {
			System.out.println();
			for (int j = 1; j <= mapWidth; j++) {
				System.out.print(map.getValue(j, i).getTiles(0));
			}
		}
	}

	protected int getActiveX() {
		return activeX;
	}

	protected int getActiveY() {
		return activeY;
	}

	protected int getPolygonHeight() {
		return POLYGON_HEIGHT;
	}

	protected int getPolygonWidth() {
		return POLYGON_WIDTH;
	}
}
