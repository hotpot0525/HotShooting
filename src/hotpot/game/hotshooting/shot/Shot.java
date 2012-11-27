package hotpot.game.hotshooting.shot;

public abstract class Shot {
	int x, y;

	public Shot(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void shot();
}
