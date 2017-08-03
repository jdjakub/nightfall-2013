class DatabattleModeEnemyTurn extends DatabattleModeBase {
	public DatabattleModeEnemyTurn(IDatabattleContext ctx) {
		super(ctx);
	}
	
	public void onGridClick(int x, int y) {
		ISector selected = ctx.getGrid().getSector(x, y);
		IEventSystem evSys = Services.getEventSystem();
		
		evSys.queueEvent(new SetActiveSectorEvent(selected, true));
	}
}