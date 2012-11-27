package hotpot.game.hotshooting.move;

import java.util.Random;

public class Sine extends Move {

	public int defaultY;
	public int defaultFrame;
	public int frameCount;

	public Sine(int x, int y) {
		super(x, y);
		Random random = new Random();
		defaultFrame = random.nextInt(30);
		frameCount = 0;
		defaultY = random.nextInt(300);

	}

	@Override
	public void move() {

		x--;
		y = (int) (Math.sin((defaultFrame + frameCount) * 3.14 / 30) * 30
				+ defaultY + 30);
		frameCount++;
	}

}
