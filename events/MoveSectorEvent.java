class MoveSectorEvent implements IEvent {
	public ISector head;
	public int tx;
	public int ty;
	
	public MoveSectorEvent(ISector head, int tx, int ty) {
		this.head = head;
		this.tx = tx;
		this.ty = ty;
	}
	
	public void dispatch(EventHandler handler) {
		handler.handle(this);
	}
	
	public String toString() {
		return "Move sector at ("+head.getX()+", "+head.getY()+") ("+head.getName()+") to ("+tx+", "+ty+")";
	}
}