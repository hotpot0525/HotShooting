package hotpot.game.hotshooting.shot;

public class PlayerShotA extends Bullet {

	public PlayerShotA(int x, int y) {
		super(x, y);
	}

	@Override
	public void move() {
		x += 5;
	}
}
