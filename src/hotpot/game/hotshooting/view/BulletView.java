package hotpot.game.hotshooting.view;

import hotpot.game.framework.Graphics;
import hotpot.game.hotshooting.Enemy;
import hotpot.game.hotshooting.shot.Bullet;

import java.util.concurrent.CountDownLatch;

import android.graphics.Color;

public class BulletView {

	public enum Visibility {
		VISIBLE, GONE
	}

	CountDownLatch sig;

	public int frameCount;
	public Graphics g;
	public Visibility visibility;

	public BulletView(Graphics g) {
		this.g = g;
		frameCount = 0;
		visibility = Visibility.VISIBLE;
	}

	public void draw(Bullet bullet) {

		if (visibility == Visibility.VISIBLE) {
			g.drawRect(bullet.x, bullet.y, 4, 4, Color.RED);
		}

		if (bullet.state == Bullet.State.DIE) {
			visibility = Visibility.GONE;
		}

		frameCount++;
	}

	public void destory() {
		g = null;
	}
}
