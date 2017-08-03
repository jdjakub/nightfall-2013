import org.jsfml.graphics.*;

class BasicAnimation implements IAnimation {
	protected int frame;
	protected int length;
	protected float secPerFrame;
	private float frameElapsed;
	private boolean visible;
	
	protected void nextFrame() {
		frame++;
		if (frame >= length) {
			frame = 0;
		}
	}
	
	public BasicAnimation(float animLengthSec, int length) {
		secPerFrame = animLengthSec / length;
		this.length = length;
		visible = true;
	}
	
	public void reset() {
		frame = 0;
		frameElapsed = 0;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void update(float deltaSec) {
		frameElapsed += deltaSec;
		if (frameElapsed >= secPerFrame) {
			nextFrame();
			frameElapsed = 0f;
		}
	}
	
	public int getCurrentFrame() {
		return frame;
	}
}