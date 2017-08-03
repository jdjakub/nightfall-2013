import org.jsfml.graphics.*;
import org.jsfml.window.event.*;
import org.jsfml.system.*;

abstract class BaseScreenElement implements IScreenElement {
	private boolean visible;
	protected IntRect bounds;
	
	public BaseScreenElement() {
		visible = true;
		bounds = new IntRect(0, 0, 0, 0);
	}
	
	public IntRect getBounds() {
		return bounds;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setPosition(int x, int y) {
		bounds = new IntRect(x, y, bounds.width, bounds.height);
	}
	
	public void setDimensions(int w, int h) {
		bounds = new IntRect(bounds.left, bounds.top, w, h);
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean init() {
		return true;
	}
	
	public void update(float deltaSec) {}
	
	public void onEvent(Event ev) {}
	
	public abstract void render(RenderWindow wnd);
}