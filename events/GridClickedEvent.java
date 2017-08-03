class GridClickedEvent implements IEvent {
	public int x;
	public int y;
	
	public GridClickedEvent(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void dispatch(EventHandler handler) {
		handler.handle(this);
	}
	
	public String toString() {
		return "Grid clicked at ("+x+", "+y+")";
	}
}