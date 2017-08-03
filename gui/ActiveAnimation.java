import org.jsfml.graphics.*;

abstract class ActiveAnimation extends BaseAnimation {
	

	int frame;
	
	protected void nextFrame() {
		frame++;
		if 
	}
	
	public BaseAnimation(float fps) {
		secPerFrame = 1.0/fps;
	}
	
	public void update(float deltaSec) {
		frameElapsed += deltaSec;
		if (frameElapsed >= secPerFrame) {
			nextFrame();
			frameElapsed = 0f;
		}
	}
	
	public abstract void render(RenderWindow wnd);
}