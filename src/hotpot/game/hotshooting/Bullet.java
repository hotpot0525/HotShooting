package hotpot.game.hotshooting;

import android.graphics.Rect;


public class Bullet {
	
	enum State{
		ALIVE, DIE
	}
	
	enum ShotType {
		NoShot, Player1, Enemy1
	}

	int x;
	int y;
	public State state;
	public ShotType shotType;
	
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		state = State.ALIVE;
	}

	public void setShotType(ShotType type){
		shotType = type;
		
	}

	public void hitEnemy() {
		state = State.DIE;
	}
	
	public void move(ShotType type){
		
		if(shotType == null){
			shotType = ShotType.NoShot;
		}
		
		if(type == ShotType.Enemy1){
			x -= 2;			
		}else if(type == ShotType.Player1){
			x += 5;
		}else if(type == ShotType.NoShot){
			// 何もしない
		}
	}
	
}
