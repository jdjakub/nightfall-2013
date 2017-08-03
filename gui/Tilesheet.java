import org.jsfml.graphics.*;

import java.io.IOException;
import java.nio.file.Paths;

class Tilesheet {
	private Texture tex;
	private int tileWidth;
	private int tileHeight;

	public Tilesheet(Texture tex, int tileW, int tileH)  {
		this.tex = tex;
		tileWidth = tileW;
		tileHeight = tileH;
	}
	
	public int getWidth() {
		return tex.getSize().x / tileWidth;
	}
	
	public int getHeight() {
		return tex.getSize().y / tileHeight;
	}
	
	public int getTileWidth() {
		return tileWidth;
	}
	
	public int getTileHeight() {
		return tileHeight;
	}
	
	public Tilesheet setTileWidth(int w) {
		tileWidth = w;
		return this;
	}
	
	public Tilesheet setTileHeight(int h) {
		tileHeight = h;
		return this;
	}
	
	public Sprite makeSprite(int tileX, int tileY) {
		Sprite spr = new Sprite(tex);
		return changeSprite(spr, tileX, tileY);
	}
	
	public Sprite makeSprite(int imgIndex) {
		Sprite spr = new Sprite(tex);
		return changeSprite(spr, imgIndex);
	}
	
	public Sprite changeSprite(Sprite spr, int tileX, int tileY) {
		int x = tileX*tileWidth;
		int y = tileY*tileHeight;
		int w = tileWidth;
		int h = tileHeight;
		spr.setTextureRect(new IntRect(x, y, w, h));
		return spr;
	}
	
	public Sprite changeSprite(Sprite spr, int index) {
		int tileX = index % getWidth();
		int tileY = index / getHeight();
		return changeSprite(spr, tileX, tileY);
	}
}