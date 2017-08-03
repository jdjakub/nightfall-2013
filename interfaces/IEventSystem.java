interface IEventSystem {
	public void registerHandler(Class<?> evt, EventHandler handler);
	public void unregisterHandler(Class<?> evt, EventHandler handler);
	public void queueEvent(IEvent evt);
	public void triggerEvent(IEvent evt);
	public void update(float deltaSec);
}