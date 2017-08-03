import org.jsfml.graphics.*;
import org.jsfml.window.event.*;
import org.jsfml.system.*;

class CommandButtonControl extends TextButtonControl {
	private static final Color BASE_COLOUR = new Color(0x80, 0x80, 0x80, 0xFF);
	private static final Color ACTIVE_COLOUR = new Color(0x80, 0x80, 0x80, 0x80);

	private RectangleShape rect;

	public CommandButtonControl(ConstFont font, int textSize) {
		super(font, textSize);
		rect = new RectangleShape();
	}
	
	public boolean init() {
		if (!super.init()) return false;
		rect.setFillColor(BASE_COLOUR);
		return true;
	}
	
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		rect.setPosition(new Vector2f(x, y));
	}
	
	public void setDimensions(int w, int h) {
		super.setDimensions(w, h);
		rect.setSize(new Vector2f(w, h));
	}
	
	public void render(RenderWindow wnd) {
		wnd.draw(rect);
		super.render(wnd);
	}
	
	public void onPress() {
		rect.setFillColor(ACTIVE_COLOUR);
	}
	
	public void onRelease() {
		rect.setFillColor(BASE_COLOUR);
	}
}