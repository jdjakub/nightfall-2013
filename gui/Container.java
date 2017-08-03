import org.jsfml.graphics.*;
import org.jsfml.window.event.*;
import org.jsfml.system.*;

import java.util.LinkedList;
import java.util.Iterator;

abstract class Container extends BaseScreenElement {
	protected LinkedList<IScreenElement> elems;
	
	protected void onClickDown(IScreenElement target, int x, int y) {}
	
	protected void onClickUp(IScreenElement target, int x, int y) {}
	
	protected void addChild(IScreenElement ch) {
		elems.add(ch);
	}
	
	public Container() {
		elems = new LinkedList<IScreenElement>();
	}
	
	public boolean init() {
		for (IScreenElement elem : elems) {
			if (!elem.init()) return false;
		}
		return true;
	}
	
	public void update(float deltaSec) {
		for (IScreenElement elem : elems) {
			elem.update(deltaSec);
		}
	}
	
	public void render(RenderWindow wnd) {
		for (IScreenElement elem : elems) {
			if (elem.isVisible()) {
				elem.render(wnd);
			}
		}
	}
	
	public void onEvent(Event ev) {
		switch (ev.type) {
			case MOUSE_BUTTON_RELEASED:
			{
				MouseButtonEvent mev = ev.asMouseButtonEvent();
				IScreenElement elem = null;
				for (Iterator<IScreenElement> it=elems.descendingIterator(); it.hasNext();) {
					IScreenElement tmp = it.next();
					if (tmp.getBounds().contains(mev.position)) {
						elem = tmp;
						break;
					}
				}
				onClickUp(elem, mev.position.x, mev.position.y);
				break;
			}
			case MOUSE_BUTTON_PRESSED:
			{
				MouseButtonEvent mev = ev.asMouseButtonEvent();
				for (Iterator<IScreenElement> it=elems.descendingIterator(); it.hasNext();) {
					IScreenElement elem = it.next();
					if (elem.getBounds().contains(mev.position)) {
						onClickDown(elem, mev.position.x, mev.position.y);
						break;
					}
				}
				break;
			}
		}
	}
}