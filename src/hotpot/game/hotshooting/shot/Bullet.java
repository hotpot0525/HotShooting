package hotpot.game.hotshooting.shot;



public abstract class Bullet {
	
	public enum State{
		ALIVE, DIE
	}
	
	enum ShotType {
		NoShot, Player1, Enemy1
	}

	public int x;
	public int y;
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
	public abstract void move();
	
}
