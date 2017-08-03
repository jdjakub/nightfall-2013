class DatabattleModeMove extends DatabattleModeBase {
	protected void onGridClickSpecific(int x, int y, ISector selected, ISectorReadAccess grid, ISector active) {
		IEventSystem evSys = Services.getEventSystem();
		
		if (selected == null) {
			int dx = active.getX()-x;
			int dy = active.getY()-y;
			int relDist = Math.abs(dx) + Math.abs(dy);
			
			if (relDist == 1 && grid.isOn(x, y)) {
				int move = active.getMove();
				if (move != 0) {
					evSys.queueEvent(new MoveSectorEvent(active, x, y));
					evSys.queueEvent(new UpdateSectorInfoEvent(active));
					
					if (move == 1) {
						ProgAction[] acts = active.getActions();
						if (acts != null) {
							ctx.switchMode(new DatabattleModeAction(ctx, acts[0]));
						}
					}
				} 
			}/* else {
				evSys.queueEvent(new SetActiveSectorEvent(null));
				evSys.queueEvent(new UpdateSectorInfoEvent(null));
				ctx.switchMode(new DatabattleModeFree(ctx));
			}*/
		} else {
			ISector newActive = null;
			if (selected.isSelectable()) {
				newActive = selected;
			}
			evSys.queueEvent(new SetActiveSectorEvent(newActive));
			evSys.queueEvent(new UpdateSectorInfoEvent(newActive));
			IDatabattleMode newMode = newActive == null ? new DatabattleModeFree(ctx) : new DatabattleModeMove(ctx);
			ctx.switchMode(newMode);
		}
	}

	public DatabattleModeMove(IDatabattleContext ctx) {
		super(ctx);
	}
	
	public void onInit() {
		IEventSystem evSys = Services.getEventSystem();
		/*ISector active = ctx.getActive();
		
		evSys.queueEvent(new SetActiveSectorEvent(active));
		evSys.queueEvent(new UpdateSectorInfoEvent(active));*/
	}
}