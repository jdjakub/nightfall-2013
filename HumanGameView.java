import org.jsfml.graphics.*;
import org.jsfml.window.event.*;

//import java.util.LinkedList;

class HumanGameView extends EventHandler implements IGameView {
	private RenderWindow wnd;
	
	private BaseScreen scr;
	
	public HumanGameView(RenderWindow wnd) {
		this.wnd = wnd;
		IEventSystem evSys = Services.getEventSystem();
		
		evSys.registerHandler(DatabattleInitEvent.class, this);
	}
	
	public boolean init() {
		return true;
	}
	
	public void update(float deltaSec) {
		scr.update(deltaSec);
	}
	
	public void render() {
		scr.render(wnd);
	}
	
	public void onEvent(Event ev) {
		if (scr != null) {
			scr.onEvent(ev);
		}
	}
	
	public void handle(DatabattleInitEvent ev) {
		scr = new DatabattleScreen(ev.ctx);
		if (!scr.init()) System.err.println("ERROR/WARNING: scr.init() failed...");
	}
}