class NightfallLogic {

	private IGameState state;

	public NightfallLogic() {
	}
	
	public boolean init(String[] args) {
		
		/*for (int y=0; y<grid.getGridHeight(); y++) {
			for (int x=0; x<grid.getGridWidth(); x++) {
				int n = (int) Math.rint(Math.random()*50);
				boolean b = n % 2 == 0 ? false : true;
				grid.set(x, y, b);
				ISector sec = Sector.create(n, x, y);
				if (sec != null) {
					grid.set(x, y, true);
					if (sec.getTypeId() != Sector.tail.getTypeId()) {
						grid.setSector(x, y, sec);
					}
				}
			}
		}*/
		state = new DatabattleGameState(args[0]);
		if (!state.init()) return false;
		
		/*ICommandQueue cq = Services.getCommandQueue();
		cq.addCommand(new CmdDebug());
		cq.addCommand(new CmdDebug());
		cq.addCommand(new CmdDebug());
		cq.addCommand(new CmdDebug());
		cq.addCommand(new CmdDebug());*/
		return true;
	}
	
	public void update(float deltaSec) {
		Services.getCommandQueue().update(deltaSec);
		Services.getEventSystem().update(deltaSec);
		state.update(deltaSec);
	}
}