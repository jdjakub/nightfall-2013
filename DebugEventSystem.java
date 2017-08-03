class DebugEventSystem implements IEventSystem {
	private IEventSystem evSys;
	
	public DebugEventSystem(IEventSystem sys) {
		evSys = new EventSystem();
	}

	public void registerHandler(Class<?> evt, EventHandler handler) {
		evSys.registerHandler(evt, handler);
		System.out.println("Handler for "+evt.getName()+" registered.");
	}
	
	public void unregisterHandler(Class<?> evt, EventHandler handler) {
		evSys.unregisterHandler(evt, handler);
		System.out.println("Handler for "+evt.getName()+" registered.");
	}
	
	public void queueEvent(IEvent evt) {
		evSys.queueEvent(evt);
		System.out.println("Queued event: "+evt);
	}
	
	public void triggerEvent(IEvent evt) {
		evSys.triggerEvent(evt);
		System.out.println("Triggered event: "+evt);
	}
	
	public void update(float deltaSec) {
		evSys.update(deltaSec);
	}
}