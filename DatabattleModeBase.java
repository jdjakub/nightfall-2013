abstract class DatabattleModeBase implements IDatabattleMode {
	protected IDatabattleContext ctx;
	
	protected abstract void onGridClickSpecific(int x, int y, ISector selected, ISectorReadAccess grid, ISector active);
	
	public DatabattleModeBase(IDatabattleContext ctx) {
		this.ctx = ctx;
	}
	
	public void onInit() {}
	
	public final void onGridClick(int x, int y) {
		ISector active = ctx.getActive();
		IEventSystem evSys = Services.getEventSystem();
		ISectorReadAccess grid = ctx.getGrid();
		ISector selected = grid.getSector(x, y);
		
		if (selected == active) {
			evSys.queueEvent(new SetActiveSectorEvent(null));
			evSys.queueEvent(new UpdateSectorInfoEvent(null));
			ctx.switchMode(new DatabattleModeFree(ctx));
		} else {
			onGridClickSpecific(x, y, selected, grid, active);
		}
	}
}