import org.jsfml.graphics.*;
import org.jsfml.window.event.*;
import org.jsfml.system.*;

class DatabattleGridControl extends BaseScreenElement {
	private static final int HOR_SEP = 2;
	private static final int VER_SEP = 2;
	
	private IDatabattleContext ctx;
	private ISectorReadAccess grid;
	private Tilesheet progTiles;
	private Tilesheet envTiles;
	private Image tailCols;
	private int cellWidth;
	private int cellHeight;
	
	private Sprite env;
	private Sprite prog;
	private Sprite tail;
	private Sprite tailShadow;
	
	private ISector active;
	private IGridOverlay overlay;
	
	private IAnimation activeAnim;
	
	private void renderTiles(RenderWindow wnd) {
		envTiles.changeSprite(env, 0);
		for (TileInfo ti : grid) {
			int x = ti.x;
			int y = ti.y;
			
			if (grid.isOn(x, y)) {
				env.setPosition(toScreenX(x), toScreenY(y));
				wnd.draw(env);
			}
		}
	}
	
	private void renderSectors(RenderWindow wnd) {
		for (TileInfo ti : grid) {
			int x = ti.x;
			int y = ti.y;
			
			ISector curr = grid.getSector(x, y);
			if (curr != null) {
				if (curr.getTypeId() != Sector.tail.getTypeId()) {
					renderChain(curr, wnd);
				}
			}
		}
	}
	
	private void renderOverlays(RenderWindow wnd) {
		if (overlay != null) {
			overlay.render(wnd);
		}
		if (activeAnim.isVisible()) {
			env.setPosition(toScreenX(active.getX()), toScreenY(active.getY()));
			wnd.draw(envTiles.changeSprite(env, 8+activeAnim.getCurrentFrame()));
		}
	}
	
	private void renderChain(ISector head, RenderWindow wnd) {
		int imgIndex = head.getRenderInfo().imgIndex;
		progTiles.changeSprite(prog, imgIndex);
		prog.setPosition(toScreenX(head.getX()), toScreenY(head.getY()));
		wnd.draw(prog);
		
		Color tailCol = tailCols.getPixel(imgIndex, 0);
		Color tailShadowCol = tailCols.getPixel(imgIndex, 1);
		
		for (ISector t = head.getChild(); t != null; t = t.getChild()) {
			float screenX = toScreenX(t.getX());
			float screenY = toScreenY(t.getY());
			tail.setColor(tailCol);
			tail.setPosition(screenX, screenY);
			tailShadow.setColor(tailShadowCol);
			tailShadow.setPosition(screenX, screenY);
			
			wnd.draw(tail);
			wnd.draw(tailShadow);
		}
	}
	
	public DatabattleGridControl(IDatabattleContext ctx, Tilesheet progTiles, Tilesheet envTiles, Image tailCols) {
		this.ctx = ctx;
		grid = ctx.getGrid();
		this.progTiles = progTiles;
		this.envTiles = envTiles;
		this.tailCols = tailCols;
		setPosition(216, 0x10);
		int cellWidth = progTiles.getTileWidth() + HOR_SEP;
		int cellHeight = progTiles.getTileHeight() + VER_SEP;
		setDimensions(grid.getGridWidth() * cellWidth, grid.getGridHeight() * cellHeight);
		
		int tailIdx = Sector.tail.getRenderInfo().imgIndex;
		env = envTiles.makeSprite(1, 0);
		prog = progTiles.makeSprite(0, 0);
		tail = progTiles.makeSprite(tailIdx);
		tailShadow = progTiles.makeSprite(tailIdx - 1);
		
		activeAnim = new BasicAnimation(0.75f, 6);
		activeAnim.setVisible(false);
	}
	
	public void update(float deltaSec) {
		if (activeAnim.isVisible()) {
			activeAnim.update(deltaSec);
		}
	}
	
	public void render(RenderWindow wnd) {
		renderTiles(wnd);
		renderSectors(wnd);
		renderOverlays(wnd);
	}
	
	public void onEvent(Event ev) {
		switch (ev.type) {
			case MOUSE_BUTTON_PRESSED:
			{
				MouseButtonEvent mev = ev.asMouseButtonEvent();
				if (bounds.contains(mev.position)) {
					int localX = mev.position.x - bounds.left;
					int localY = mev.position.y - bounds.top;
					onClick(localX, localY);
				}
				break;
			}
		}
	}
	
	public float toScreenX(int gridX) {
		return gridX*(progTiles.getTileWidth() + HOR_SEP) + bounds.left;
	}
	
	public float toScreenY(int gridY) {
		return gridY*(progTiles.getTileHeight() + VER_SEP) + bounds.top;
	}
	
	public void onSetActive(ISector active) {
		this.active = active;
		activeAnim.setVisible(active != null);
		activeAnim.reset();
		
		if (active == null) {
			overlay = null;
		} else {
			overlay = new MoveGridOverlay(env, active.getX(), active.getY(), active.getMove());
		}
	}
	
	public void onActionSelected(ProgAction action) {
		overlay = action == null ? null : new ActionGridOverlay(env, action, active.getX(), active.getY());
	}
	
	public void onMoveSector(ISector sec) {
		overlay = new MoveGridOverlay(env, sec.getX(), sec.getY(), sec.getMove());
	}
	
	public void onClick(int localX, int localY) {
		//Translate to grid coords
		int gridX = localX / (progTiles.getTileWidth()+HOR_SEP);
		int gridY = localY / (progTiles.getTileHeight()+VER_SEP);
		//System.out.println("Local X: "+localX+" Local Y: "+localY);
		
		Services.getEventSystem().queueEvent(new GridClickedEvent(gridX, gridY));
	}
	
	class MoveGridOverlay implements IGridOverlay {
		private Sprite spr;
		
		private int cx;
		private int cy;
		private int range;
		
		private void renderSpecialArrow(RenderWindow wnd, int relX, int relY) {
			int imgX = 4;
			
			if (relX == 0) {
				imgX = relY+1;
			} else if (relY == 0) {
				imgX = 2 - relX;
			}
			envTiles.changeSprite(spr, imgX, 3);
			wnd.draw(spr);
			envTiles.changeSprite(spr, 4, 3);
		}
		
		public MoveGridOverlay(Sprite spr, int cx, int cy, int range) {
			this.spr = spr;
			this.cx = cx;
			this.cy = cy;
			this.range = range;
		}
		
		public void render(RenderWindow wnd) {
			if (range == 0) return;
			
			envTiles.changeSprite(spr, 4, 3);
			for (TileInfo ti : grid) {
				int x = ti.x;
				int y = ti.y;
				
				int relX = x - cx;
				int relY = y - cy;
				int relDist = Math.abs(relX) + Math.abs(relY);
				
				if (relDist <= range && relDist != 0 && grid.isOn(x, y) && grid.getSector(x, y) == null) {
					spr.setPosition(toScreenX(x), toScreenY(y));
					if (relDist == 1) {
						renderSpecialArrow(wnd, relX, relY);
					} else {
						wnd.draw(spr);
					}
				}
			}
		}
	}
	
	class ActionGridOverlay implements IGridOverlay {
		private Sprite spr;
		private ProgAction action;
		
		private int cx;
		private int cy;
		
		public ActionGridOverlay(Sprite spr, ProgAction action, int cx, int cy) {
			this.spr = spr;
			this.action = action;
			this.cx = cx;
			this.cy = cy;
			System.out.println("Action grid overlay created: "+cx+", "+cy+", "+action.getName()+", using tile at ("+action.getType().x+", "+2+")");
		}
		
		public void render(RenderWindow wnd) {
			envTiles.changeSprite(spr, action.getType().x, 2);
		
			for (TileInfo ti : grid) {
				int x = ti.x;
				int y = ti.y;
				
				int relX = x - cx;
				int relY = y - cy;
				
				if (action.isValidTarget(relX, relY)) {
					spr.setPosition(toScreenX(x), toScreenY(y));
					wnd.draw(spr);
				}
			}
		}
	}
}