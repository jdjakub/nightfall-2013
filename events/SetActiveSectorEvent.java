class SetActiveSectorEvent implements IEvent {
	public ISector sector;

	public SetActiveSectorEvent(ISector sector) {
		this.sector = sector;
	}
	
	public void dispatch(EventHandler handler) {
		handler.handle(this);
	}
	
	public String toString() {
		if (sector == null) {
			return "Deselect active sector";
		} else {
			return "Select sector: ("+sector.getX()+", "+sector.getY()+") ("+sector.getName()+")";
		}
	}
}