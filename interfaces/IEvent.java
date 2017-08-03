interface IEvent {
	public void dispatch(EventHandler handler);
	public String toString();
}