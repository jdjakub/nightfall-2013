class DatabattleModeFree extends DatabattleModeBase {
	protected void onGridClickSpecific(int x, int y, ISector selected, ISectorReadAccess grid, ISector active) {
		IEventSystem evSys = Services.getEventSystem();
		
		if (selected != null) {
			if (selected.isSelectable()) {
				evSys.queueEvent(new SetActiveSectorEvent(selected));
				evSys.queueEvent(new UpdateSectorInfoEvent(selected));
			}
			if (selected.getMove() != 0) {
				//Not done yet
				ctx.switchMode(new DatabattleModeMove(ctx));
			}
		}
	}

	public DatabattleModeFree(IDatabattleContext ctx) {
		super(ctx);
	}
}