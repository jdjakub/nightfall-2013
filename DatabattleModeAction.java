class DatabattleModeAction extends DatabattleModeBase {
	private ProgAction activeAction;

	protected void onGridClickSpecific(int x, int y, ISector selected, ISectorReadAccess grid, ISector active) {
		IEventSystem evSys = Services.getEventSystem();
		
		int relX = x - active.getX();
		int relY = y - active.getY();
		
		if (activeAction.isValidTarget(relX, relY)) {
			// Player clicked within range
			System.out.println("Selection within range of "+activeAction.getName()+" ["+activeAction.getDesc()+"].");
			//activeAction.execute(ctx, active, x, y);
		} else if (selected != null && selected.isSelectable()) {
			evSys.queueEvent(new SetActiveSectorEvent(selected));
			evSys.queueEvent(new UpdateSectorInfoEvent(selected));
			ctx.switchMode(new DatabattleModeMove(ctx));
		}
	}
	
	public DatabattleModeAction(IDatabattleContext ctx, ProgAction act) {
		super(ctx);
		activeAction = act;
	}
	
	public void onInit() {
		Services.getEventSystem().queueEvent(new ActionSelectedEvent(activeAction));
	}
}