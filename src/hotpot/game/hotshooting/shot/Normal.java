package hotpot.game.hotshooting.shot;

public class Normal extends Bullet {

	public Normal(int x, int y) {
		super(x, y);
	}

	@Override
	public void move() {
		x -= 2;
	}

}
