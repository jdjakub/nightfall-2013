class DatabattleGameState extends EventHandler implements IGameState, IDatabattleContext {
	private IDatabattleBuilder builder;
	private ISectorWriteAccess grid;
	private IDatabattleMode mode;
	
	private ISector active;

	public DatabattleGameState(String filename) {
		IEventSystem evSys = Services.getEventSystem();
		evSys.registerHandler(GridTileSetEvent.class, this);
		evSys.registerHandler(SetActiveSectorEvent.class, this);
		evSys.registerHandler(GridClickedEvent.class, this);
		evSys.registerHandler(MoveSectorEvent.class, this);
		
		builder = new FileDatabattleBuilder(filename);
	}

	public boolean init() {
		IEventSystem evSys = Services.getEventSystem();
		
		if (!builder.build()) return false;
		grid = builder.getGrid();
		builder = null;
		
		mode = new DatabattleModeFree(this);
		
		evSys.queueEvent(new DatabattleInitEvent(this));
		return true;
	}
	
	public void onChange() {
		IEventSystem evSys = Services.getEventSystem();
		evSys.unregisterHandler(GridTileSetEvent.class, this);
		evSys.unregisterHandler(SetActiveSectorEvent.class, this);
		evSys.unregisterHandler(GridClickedEvent.class, this);
		evSys.unregisterHandler(MoveSectorEvent.class, this);
	}
	
	public void update(float deltaSec) {}
	
	public void switchMode(IDatabattleMode mode) {
		System.out.println("Switched to "+mode.getClass().getName());
		this.mode = mode;
		mode.onInit();
	}
	
	public ISectorReadAccess getGrid() {
		return grid;
	}
	
	public ISector getActive() {
		return active;
	}
	
	public void handle(GridClickedEvent ev) {
		int x = ev.x;
		int y = ev.y;
		
		if (grid.isValidCoord(x, y)) {
			mode.onGridClick(x, y);
		}
	}
	
	public void handle(SetActiveSectorEvent ev) {
		active = ev.sector;
	}
	
	public void handle(GridTileSetEvent ev) {
		int x = ev.x;
		int y = ev.y;
		
		if (grid.isValidCoord(x, y)) {
			grid.set(x, y, 1);
		}
	}
	
	public void handle(MoveSectorEvent ev) {
		int tx = ev.tx;
		int ty = ev.ty;
		ISector curr = ev.head;
		ISector last = curr;
		
		do {
			int oldX = curr.getX();
			int oldY = curr.getY();
			grid.moveSector(curr, tx, ty);
			tx = oldX;
			ty = oldY;
			ISector child = curr.getChild();
			if (child == null) {
				last = curr;
			}
			curr = child;
		} while (curr != null);
		
		if (ev.head.getCurrSize() < ev.head.getMaxSize()) {
			ISector tail = Sector.tail.clone();
			grid.setSector(tx, ty, tail);
			last.setChild(tail);
			tail.setParent(last);
			ev.head.setCurrSize(ev.head.getCurrSize()+1);
		}
		
		ev.head.setMove(ev.head.getMove()-1);
	}
}