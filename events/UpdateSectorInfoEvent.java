class UpdateSectorInfoEvent implements IEvent {
	public ISector sector;
	
	public UpdateSectorInfoEvent(ISector sector) {
		this.sector = sector;
	}
	
	public void dispatch(EventHandler handler) {
		handler.handle(this);
	}
	
	public String toString() {
		if (sector == null) return "Clear sector info";
		else return "Update sector info panel: "+sector.getName()+" at ("+sector.getX()+", "+sector.getY()+")";
	}
}