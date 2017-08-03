class CmdDeleteLastSector implements IGameCommand {
	private static final float DELAY = 0.2f;

	ISector target;
	
	public CmdDeleteLastSector(ISector targ) {
		target = targ;
	}
	
	public float getDelay() {
		return DELAY;
	}
	
	public void execute() {
		System.out.println("Executing...");
	}
	
	public String toString() {
		return "Delete last sector of ("+target.getX()+", "+target.getY()+")";
	}
}