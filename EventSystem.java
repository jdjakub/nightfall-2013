import java.util.*;

class EventSystem implements IEventSystem {
	private HashMap<Class<?>, LinkedList<EventHandler>> handlersMap;
	private LinkedList<IEvent> eventQueue = new LinkedList<IEvent>();
	
	public EventSystem() {
		handlersMap = new HashMap<Class<?>, LinkedList<EventHandler>>();
	}
	
	public void registerHandler(Class<?> evt, EventHandler handler) {
		LinkedList<EventHandler> list = handlersMap.get(evt);
		if (list == null) {
			list = new LinkedList<EventHandler>();
			handlersMap.put(evt, list);
		}
		list.add(handler);
	}
	
	public void unregisterHandler(Class<?> evt, EventHandler handler) {
		handlersMap.get(evt).remove(handler);
	}
	
	public void queueEvent(IEvent evt) {
		eventQueue.add(evt);
	}
	
	public void triggerEvent(IEvent evt) {
		LinkedList<EventHandler> handlerList = handlersMap.get(evt.getClass());
		
		if (handlerList != null) {
			for (EventHandler handler : handlerList) {
				evt.dispatch(handler);
			}
		}
	}
	
	public void update(float deltaSec) {
		//To avoid ConcurrentModificationException
		LinkedList<IEvent> queue = eventQueue;
		eventQueue = new LinkedList<IEvent>();
		
		for (IEvent evt : queue) {
			triggerEvent(evt);
		}
		//eventQueue.clear();
	}
}