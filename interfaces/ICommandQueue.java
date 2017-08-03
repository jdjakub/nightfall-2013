/*
 * The Command Queue is different to the Event System.
 * The Command Queue relies on the event system, and is
 * intended to provide a way to have delayed operations
 * eg AI movement or sector deletion that isn't so blindingly
 * fast you can't see it happen
 */

interface ICommandQueue {
	public void addCommand(IGameCommand cmd);
	public void update(float deltaSec);
}