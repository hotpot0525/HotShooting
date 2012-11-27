package hotpot.game.hotshooting.shot;

public class DownLeftShot extends Bullet{

	public DownLeftShot(int x, int y) {
		super(x, y);
	}

	@Override
	public void move() {
		x-=2;
		y+=2;
	}
}
