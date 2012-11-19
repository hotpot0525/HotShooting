package hotpot.game.hotshooting;

public class Player {

	enum State{
		ALIVE, DIE
	}
	
	public int x;
	public int y;
	public State state;
	public int frameCount;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		state = State.ALIVE;
		frameCount = 0;
	}

	public void hitBullet() {
		state = State.DIE;
	}
	
	public void frameCountUp(){
		frameCount++;
	}
}
