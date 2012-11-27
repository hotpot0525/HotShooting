package hotpot.game.hotshooting.move;

public abstract class Move {
	public int x,y;
	
	public Move(int x, int y){
		this.x = x; this.y = y;
	}
	
	public abstract void move();
}
