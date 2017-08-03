import java.util.*;

class CommandQueue implements ICommandQueue {
	private LinkedList<IGameCommand> queue;
	private float sleepTime;
	
	public CommandQueue() {
		queue = new LinkedList<IGameCommand>();
		sleepTime = 0.0f;
	}
	
	public void addCommand(IGameCommand cmd) {
		queue.add(cmd);
	}
	
	public void update(float deltaSec) {
		if (sleepTime > 0.0f) {
			sleepTime -= deltaSec;
			return;
		}
		if (queue.isEmpty()) return;
		
		IGameCommand next = queue.removeFirst();
		next.execute();
		sleepTime = next.getDelay();
	}
}