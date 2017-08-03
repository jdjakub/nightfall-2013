class GridTileSetEvent implements IEvent {
	public final int x;
	public final int y;
	public final boolean val;

	public GridTileSetEvent(int x, int y, boolean val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}

	public final void dispatch(EventHandler handler) {
		handler.handle(this);
	}
	
	public String toString() {
		return "set ("+x+", "+y+") "+val;
	}
}