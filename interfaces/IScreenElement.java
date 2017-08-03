import org.jsfml.window.event.*;
import org.jsfml.graphics.*;
import org.jsfml.system.*;

interface IScreenElement {
	// ACCESSORS
	public IntRect getBounds();
	public boolean isVisible();

	// SETTERS
	public void setPosition(int x, int y);
	public void setDimensions(int w, int h);
	public void setVisible(boolean visible);

	// OTHER
	public boolean init();
	public void update(float deltaSec);
	public void render(RenderWindow wnd);
	public void onEvent(Event ev);
}