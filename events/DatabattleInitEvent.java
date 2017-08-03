class DatabattleInitEvent implements IEvent {
	public IDatabattleContext ctx;
	
	public DatabattleInitEvent(IDatabattleContext ctx) {
		this.ctx = ctx;
	}
	
	public void dispatch(EventHandler handler) {
		handler.handle(this);
	}
	
	public String toString() {
		return "Databattle initialised";
	}
}