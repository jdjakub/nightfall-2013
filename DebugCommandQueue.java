class DebugCommandQueue implements ICommandQueue {
	private ICommandQueue cmdQueue;
	
	public DebugCommandQueue(ICommandQueue cq) {
		cmdQueue = cq;
	}
	
	public void addCommand(IGameCommand cmd) {
		cmdQueue.addCommand(cmd);
		System.out.println("Command added: "+cmd);
	}
	
	public void update(float deltaSec) {
		cmdQueue.update(deltaSec);
	}
}