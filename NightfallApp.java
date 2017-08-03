import org.jsfml.graphics.*;
import org.jsfml.window.event.*;
import org.jsfml.window.*;
import org.jsfml.system.*;

import java.io.IOException;

public class NightfallApp {
	public static final NightfallApp inst = new NightfallApp();

	private final HumanGameView humanView;
	public final NightfallLogic lg;
	
	private RenderWindow wnd;
	private Clock clock;
	
	public NightfallApp() {
		if (!Services.init(	new DebugEventSystem(new EventSystem()),
							new DebugCommandQueue(new CommandQueue()),
							new ResourceManager())) {
			System.exit(1);
		}
		
		wnd = new RenderWindow();
	
		humanView = new HumanGameView(wnd);
		lg = new NightfallLogic();
		clock = new Clock();
		
		wnd.create(new VideoMode(800, 600), "Nightfall, at last?!", WindowStyle.TITLEBAR | WindowStyle.CLOSE);
		wnd.setFramerateLimit(60);
	}
	
	public boolean init(String[] args) {
		if (!lg.init(args)) return false;
		if (!humanView.init()) return false;
		return true;
	}
	
	public void begin() {
		while (wnd.isOpen()) {
			for (Event ev : wnd.pollEvents()) {
				switch (ev.type) {
					case CLOSED:
						wnd.close();
						break;
					default:
						humanView.onEvent(ev);
				}
			}
			float deltaSec = clock.restart().asSeconds();
			
			wnd.clear(Color.BLACK);
			lg.update(deltaSec);
			humanView.update(deltaSec);
			humanView.render();
			wnd.display();
		}
	}
	
	public static void main(String[] args) {
		if (inst.init(args)) inst.begin();
	}
}