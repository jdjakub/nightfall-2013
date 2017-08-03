import org.jsfml.graphics.*;
import org.jsfml.window.event.*;
import org.jsfml.system.*;

abstract class TextButtonControl extends BaseScreenElement {
	protected StaticTextControl text;
	
	private void recalcTextStuff() {
		IntRect textBounds = text.getTextBounds();
		int newYPad = (bounds.height - textBounds.height) / 2;
		int newXPad = (bounds.width - textBounds.width) / 2;
		text.setPosition(bounds.left+newXPad, bounds.top+newYPad);
	}

	public TextButtonControl(ConstFont font, int textSize) {
		super();
		text = new StaticTextControl(font, textSize);
	}
	
	public boolean init() {
		text.setColor(Color.WHITE);
		return true;
	}
	
	public void setText(String str) {
		text.setText(str);
		recalcTextStuff();
	}
	
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		text.setPosition(x, y);
		recalcTextStuff();
	}
	
	public void setDimensions(int w, int h) {
		super.setDimensions(w, h);
		text.setDimensions(w, h);
		recalcTextStuff();
	}
	
	public void render(RenderWindow wnd) {
		text.render(wnd);
	}
}