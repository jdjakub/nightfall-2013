import org.jsfml.graphics.*;
import org.jsfml.window.event.*;
import org.jsfml.system.*;

import java.util.LinkedList;

class DatabattleScreen extends BaseScreen {
	private static final int SIDE_WND_WIDTH = 200;
	private static final int SIDE_FULL_CONTROL_WIDTH = SIDE_WND_WIDTH - 2*8;

	private Sprite backg;
	
	//private MiniWindow progListWnd;
	private DbEvtHandler handler;
	private IDatabattleContext ctx;
	
	private ProgInfoWindow progInfoWnd;
	private DatabattleGridControl grid;

	public DatabattleScreen(IDatabattleContext ctx) {
		this.ctx = ctx;
		handler = new DbEvtHandler();
	}
	
	public boolean init() {
		handler.registerAll();
		IResourceManager rm = Services.getResourceManager();
		//Texture guiTex = rm.getTexture(Resource.TEX_GUI);
		Tilesheet progTiles = new Tilesheet(rm.getTexture(Resource.TEX_PROGRAMS), 32, 32);
		Tilesheet envTiles = new Tilesheet(rm.getTexture(Resource.TEX_ENVIRONMENT), 32, 32);
		Image tailCols = rm.getImage(Resource.IMG_TAILS);
		ConstFont normalFont = rm.getFont(Resource.FNT_NORMAL);
		ConstFont emphFont = rm.getFont(Resource.FNT_EMPHASIS);
		
		backg = new Sprite(rm.getTexture(Resource.TEX_DATABATTLE_BACKG));
		
		/*progListWnd = new MiniWindow(normalFont, 10);
		progListWnd.setPosition(8, 8);
		progListWnd.setDimensions(SIDE_WND_WIDTH, 200);
		progListWnd.setTitle("program.list");
		addChild(progListWnd);*/
		
		progInfoWnd = new ProgInfoWindow(progTiles, normalFont, emphFont);
		addChild(progInfoWnd);
		
		grid = new DatabattleGridControl(ctx, progTiles, envTiles, tailCols);
		addChild(grid);
		
		/*progList = new ScrollListControl(guiTex, normalFont, 10);
		progList.setPosition(8+8, 8+0x10);
		progList.setDimensions(SIDE_WND_WIDTH-0x10, 200-0x20);
		String[] items = new String[20];
		for (int i=0; i<items.length; i++) {items[i] = "List item "+i;}
		progList.setItems(items);
		addChild(progList);*/
	
		if (!super.init()) return false;
		return true;
	}
	
	public void update(float deltaSec) {
		super.update(deltaSec);
		//grid.update(deltaSec);
	}

	public void render(RenderWindow wnd) {
		wnd.draw(backg);
		super.render(wnd);
	}
	
	public void onEvent(Event ev) {
		for (IScreenElement elem : elems) {
			elem.onEvent(ev);
		}
	}
	
	public void onChange() {
		handler.unregisterAll();
	}
	
	class DbEvtHandler extends EventHandler {
		public void registerAll() {
			IEventSystem evSys = Services.getEventSystem();
		
			evSys.registerHandler(SetActiveSectorEvent.class, this);
			evSys.registerHandler(UpdateSectorInfoEvent.class, this);
			evSys.registerHandler(MoveSectorEvent.class, this);
			evSys.registerHandler(ActionSelectedEvent.class, this);
		}
		
		public void unregisterAll() {
			IEventSystem evSys = Services.getEventSystem();
		
			evSys.unregisterHandler(SetActiveSectorEvent.class, this);
			evSys.unregisterHandler(UpdateSectorInfoEvent.class, this);
			evSys.unregisterHandler(MoveSectorEvent.class, this);
			evSys.unregisterHandler(ActionSelectedEvent.class, this);
		}
		
		public void handle(SetActiveSectorEvent ev) {
			grid.onSetActive(ev.sector);
		}
		
		public void handle(UpdateSectorInfoEvent ev) {
			progInfoWnd.reset(ev.sector);
		}
		
		public void handle(MoveSectorEvent ev) {
			grid.onMoveSector(ev.head);
		}
		
		public void handle(ActionSelectedEvent ev) {
			grid.onActionSelected(ev.action);
		}
	}
}