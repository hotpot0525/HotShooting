package hotpot.game.hotshooting;

public class Player {
	
	enum State{
		ALIVE, DIE
	}
	
	public int hp;
	public int x;
	public int y;
	public State state;
	public int frameCount;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		state = State.ALIVE;
		frameCount = 0;
		hp = 100;
	}
	
	public void move(int moveX, int moveY){
		x += 1 * (moveX) / 20;
		y += 1 * (moveY) / 20;

		x = Math.max(0, Math.min(x, 320 - 16));
		y = Math.max(0, Math.min(y, 320 - 16));		
	}

	public void hitBullet() {
		hp -=10;
		if(hp <= 0){
			state = State.DIE;			
		}
	}
	
	public void frameCountUp(){
		frameCount++;
	}
}
