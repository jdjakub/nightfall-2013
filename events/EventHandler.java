abstract class EventHandler {
	public void handle(GridTileSetEvent ev) {}
	//public void handle(MouseClickedEvent ev) {}
	public void handle(GridClickedEvent ev) {}
	public void handle(SetActiveSectorEvent ev) {}
	public void handle(MoveSectorEvent ev) {}
	//public void handle(SwitchDatabattleModeEvent ev) {}
	public void handle(DatabattleInitEvent ev) {}
	public void handle(UpdateSectorInfoEvent ev) {}
	public void handle(ActionSelectedEvent ev) {}
}