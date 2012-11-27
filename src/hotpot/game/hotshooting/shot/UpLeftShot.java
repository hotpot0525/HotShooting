package hotpot.game.hotshooting.shot;

public class UpLeftShot extends Bullet{

	public UpLeftShot(int x, int y) {
		super(x, y);
	}

	@Override
	public void move() {
		x-=2;
		y-=2;
	}
}
