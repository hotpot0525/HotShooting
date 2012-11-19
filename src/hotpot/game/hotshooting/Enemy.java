package hotpot.game.hotshooting;

import java.util.Random;

public class Enemy {

	enum State {
		ALIVE, DIE
	}
	
	public int x;
	public int y;
	public State state;
	public int defaultY;
	public int defaultFrame;
	public int hp;
	public int frameCount;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;

		state = State.ALIVE;
		Random random = new Random();
		defaultY = random.nextInt(300);
		defaultFrame = random.nextInt(30);
		hp = 5;
		frameCount = 0;
		move();

	}

	/**
	 * 脂肪時にスコアを返す
	 * @return
	 */
	public int hitBullet() {
		hp--;
		if(hp < 1){
			state = State.DIE;	
			return 10;	
		}
		return 0;
	}
	
	public void move(){
		x--;
		y = (int) (Math
				.sin((defaultFrame + frameCount) * 3.14 / 30)
				* 30
				+ defaultY + 30);
		frameCount++;
	}
	
	
}
