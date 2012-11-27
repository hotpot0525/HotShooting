package hotpot.game.hotshooting.view;

import java.util.concurrent.CountDownLatch;

import hotpot.game.framework.Graphics;
import hotpot.game.hotshooting.Assets;
import hotpot.game.hotshooting.Enemy;
import hotpot.game.hotshooting.Enemy.State;

public class ViewEnemy {

	enum Type {
		V1, V2, V3
	}

	public enum Visibility {
		VISIBLE, GONE
	}

	CountDownLatch sig;

	public int frameCount;
	public Graphics g;
	public Type type;
	public Visibility visibility;

	public ViewEnemy(Graphics g) {
		this.g = g;
		frameCount = 0;
		type = Type.V1;
		sig = new CountDownLatch(33);
		visibility = Visibility.VISIBLE;
	}

	public void draw(Enemy enem) {

		// if (visibility == Visibility.VISIBLE) {
		if (type == Type.V1) {
			g.drawPixmap(Assets.monster5, enem.x, enem.y, 160, 80, 80, 80);
		} else if (type == Type.V2) {
			g.drawPixmap(Assets.monster5, enem.x, enem.y, 240, 80, 80, 80);
		} else if (type == Type.V3) {
			g.drawPixmap(Assets.monster5, enem.x, enem.y, 80, 160, 80, 80);
		}

		if (sig.getCount() == 0) {
			if (type == Type.V1) {
				type = Type.V2;
				sig = new CountDownLatch(50);
			} else if (type == Type.V2) {
				type = Type.V3;
				sig = new CountDownLatch(100);
			} else if (type == Type.V3) {
				type = Type.V1;
				sig = new CountDownLatch(150);
			}
		}
		// }else{
		// if(sig != null && sig.getCount() > 0){
		// g.drawPixmap(Assets.effect0, enem.x, enem.y, 0, 0, 16, 16);
		// sig = new CountDownLatch(100);
		// }else{

		// }
		// }

		if (enem.state == Enemy.State.DIE) {
			visibility = Visibility.GONE;
		}

		sig.countDown();
		frameCount++;
	}

	public void destory() {
		g = null;
	}
}
