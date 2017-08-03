interface IDatabattleContext {
	public void switchMode(IDatabattleMode mode);
	public ISectorReadAccess getGrid();
	public ISector getActive();
}