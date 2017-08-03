class CmdDebug implements IGameCommand {
	public float getDelay() {
		return 1.0f;
	}
	
	public void execute() {
		System.out.println("Debug command executed.");
	}
	
	public String toString() {
		return "Debug command";
	}
}