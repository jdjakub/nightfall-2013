import org.jsfml.graphics.*;
import org.jsfml.window.event.*;
import org.jsfml.system.*;

class StaticTextControl extends BaseScreenElement {
	private Text text;
	
	public StaticTextControl(ConstFont font, int size) {
		super();
		text = new Text("", font, size);
	}
	
	public void setColor(Color col) {
		text.setColor(col);
	}
	
	public void setText(String str) {
		StringBuilder strb = new StringBuilder(str);
		int totWidth = 0;
		ConstFont f = text.getFont();
		int size = text.getCharacterSize();
		int lastSpaceIndex = -1;
		int lastLineBreak = -1;
		
		//System.out.println("bounds.w = "+bounds.w);
		
		for (int i=0; i<strb.length(); i++) {
			char c = strb.charAt(i);
			if (c == '\n') {
				totWidth = 0;
				lastLineBreak = i;
			} else if (Character.isWhitespace(c)) {
				//System.out.println("Last space at "+i);
				lastSpaceIndex = i;
			}
			
			Glyph g =  f.getGlyph((int) c, size, false);	//  Last param is currently false due to laziness
			totWidth += g.advance;
			//System.out.println(c+" ["+totWidth+"]");
			if (totWidth > bounds.width) {
				//System.out.println("Break!");
				if (lastSpaceIndex <= lastLineBreak) {
					strb.insert(i, '\n');
					lastLineBreak = i;
				} else {
					strb.setCharAt(lastSpaceIndex, '\n');
					lastLineBreak = lastSpaceIndex;
					i = lastSpaceIndex;
				}
				totWidth = 0;
			}
			
			text.setString(strb.toString());
		}
	}
	
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		text.setPosition(x, y);
	}
	
	public IntRect getTextBounds() {
		return new IntRect(text.getLocalBounds());
	}
	
	public void render(RenderWindow wnd) {
		wnd.draw(text);
	}
}