package hotpot.game.hotshooting.controller;

import hotpot.game.hotshooting.Enemy;
import hotpot.game.hotshooting.Player;
import hotpot.game.hotshooting.shot.Bullet;
import hotpot.game.hotshooting.shot.Bullet.State;
import hotpot.game.hotshooting.view.BulletView;
import android.graphics.Color;
import android.graphics.Rect;

public class BulletController {

	public Bullet bullet;
	public BulletView vBullet;

	public BulletController(Bullet bullet, BulletView vBullet) {
		this.bullet = bullet;
		this.vBullet = vBullet;
	}

	/**
	 * 弾オブジェクトの状態を取得する
	 * 
	 * @return
	 */
	public State getState() {
		return bullet.state;
	}

	/**
	 * プレイヤーとの衝突判定
	 * 
	 * @param player
	 * @return
	 */
	public boolean isHit(Player player) {
		return new Rect(bullet.x, bullet.y, bullet.x + 16, bullet.y + 16)
				.intersect(player.x, player.y, player.x + 16, player.y + 16);

	}

	/**
	 * 弾オブジェクトが別のオブジェクトに衝突したとき
	 */
	public void hit() {
		bullet.hitEnemy();

	}

	/**
	 * コントローラが破棄されるときの処理
	 */
	public void destroy() {
		bullet = null;
		vBullet = null;
	}

	/**
	 * 弾を移動させる
	 */
	public void move() {
		if (bullet.state == Bullet.State.ALIVE && -16 < bullet.x
				&& bullet.x < 320 + 16) {
			bullet.move();
		} else {
			bullet.state = Bullet.State.DIE;
		}
	}

	/**
	 * 弾を描画する
	 */
	public void draw() {
		if (bullet.state == Bullet.State.ALIVE) {
			vBullet.draw(bullet);
		}
	}

}
