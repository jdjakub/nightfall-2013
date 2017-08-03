class ActionSelectedEvent implements IEvent {
	public final ProgAction action;
	
	public ActionSelectedEvent(ProgAction act) {
		action = act;
	}
	
	public void dispatch(EventHandler handler) {
		handler.handle(this);
	}
	
	public String toString() {
		if (action == null) return "Action unselected";
		else return "Action selected: "+action.getName();
	}
}