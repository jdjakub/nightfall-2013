class Services {
	private static IEventSystem evtSystem;
	private static ICommandQueue cmdQueue;
	private static IResourceManager resMgr;

	public static boolean init(IEventSystem evtSys, ICommandQueue queue, IResourceManager rm) {
		evtSystem = evtSys;
		cmdQueue = queue;
		resMgr = rm;
		if (!rm.init()) return false;
		return true;
	}
	
	public static IEventSystem getEventSystem() {
		return evtSystem;
	}
	
	public static ICommandQueue getCommandQueue() {
		return cmdQueue;
	}
	
	public static IResourceManager getResourceManager() {
		return resMgr;
	}
}