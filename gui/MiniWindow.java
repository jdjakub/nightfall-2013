import org.jsfml.graphics.*;
import org.jsfml.window.event.*;
import org.jsfml.system.*;

import java.util.LinkedList;

class MiniWindow extends BaseScreenElement {
	private static final Color BACK_FILL = new Color(0, 0, 0, 0x80);
	private static final Color BORDER_COL = new Color(0x80, 0x80, 0x80, 0xFF);

	private static final int TITLE_XOFFS = 2;
	private static final int TITLE_YOFFS = 2;

	private RectangleShape rect;
	private Text title;
	
	public MiniWindow(ConstFont font, int titleSize) {
		rect = new RectangleShape();
		title = new Text("MiniWindow", font, titleSize);
	}
	
	public boolean init() {
		title.setColor(Color.WHITE);
		title.setPosition(bounds.left+TITLE_XOFFS, bounds.top+TITLE_YOFFS);
		
		rect.setFillColor(BACK_FILL);
		rect.setOutlineThickness(2);
		rect.setOutlineColor(BORDER_COL);
		return true;
	}
	
	public void render(RenderWindow wnd) {
		wnd.draw(rect);
		wnd.draw(title);
	}

	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		rect.setPosition(new Vector2f(x, y));
		title.setPosition(bounds.left+TITLE_XOFFS, bounds.top+TITLE_YOFFS);
	}
	
	public void setDimensions(int w, int h) {
		super.setDimensions(w, h);
		rect.setSize(new Vector2f(w, h));
	}
	
	public void setTitle(String title) {
		this.title.setString(title);
	}
}