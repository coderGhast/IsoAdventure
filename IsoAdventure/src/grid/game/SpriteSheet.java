package grid.game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteSheet {

	private BufferedImage spriteSheet;
	private BufferedImageLoader buffer;
	private ArrayList<BufferedImage> sprites;
	private static final int IMAGE_WIDTH = 64;
	private static final int IMAGE_HEIGHT = 32;
	private static final int SPRIETSHEET_ROWS = 10;
	private static final int SPRITESHEET_COLUMNS = 4;

	public SpriteSheet() {
		sprites = new ArrayList<BufferedImage>();
		buffer = new BufferedImageLoader();
		try {
			spriteSheet = buffer.loadImage("spritesheet.png");
			for (int i = 0; i < SPRITESHEET_COLUMNS; i++) {
				for(int j = 0; j < SPRIETSHEET_ROWS; j++ ){
				sprites.add(grabSprite((IMAGE_WIDTH*j), (IMAGE_HEIGHT*i), IMAGE_WIDTH, IMAGE_HEIGHT));
				}
			}
		} catch (IOException e) {
			System.out.println("Unable to load in sprite sheet");
			e.printStackTrace();
		}
	}

	private BufferedImage grabSprite(int x, int y, int width, int height) {
		BufferedImage sprite = spriteSheet.getSubimage(x, y, width, height);
		return sprite;
	}

	protected BufferedImage getSprite(int index) {
		return sprites.get(index);
	}

}
