package hotpot.game.hotshooting.controller;

import java.util.ArrayList;

import hotpot.game.hotshooting.Enemy;
import hotpot.game.hotshooting.Enemy.State;
import hotpot.game.hotshooting.shot.Bullet;
import hotpot.game.hotshooting.view.ViewEnemy;
import android.graphics.Rect;

public class EnemyController {

	public Enemy enemy;
	public ViewEnemy viewEnemy;
	
	public EnemyController( Enemy e, ViewEnemy ve) {
		enemy = e;
		viewEnemy = ve;
	}

	/** 
	 * 弾との当たり判定
	 * @param b
	 * @return
	 */
	public boolean isHit(Bullet b) {
		if (enemy.state == Enemy.State.ALIVE
				&& new Rect(b.x, b.y, b.x + 16, b.y + 16).intersect(
						enemy.x + 20, enemy.y + 20, enemy.x + 60,
						enemy.y + 60)) {
			return true;
		}else{
			return false;
		}
		

	}

	/**
	 * 敵が弾に当たったときの判定
	 * @return
	 */
	public int hitBullet() {
		return enemy.hitBullet();
	}

	/**
	 * 敵の状態を取得する
	 * @return
	 */
	public State getState() {
		return enemy.state;
	}

	/**
	 * コントローラが削除されるときに呼ぶ
	 */
	public void destroy() {
		enemy = null;
		viewEnemy = null;
	}

	/**
	 * 弾を生成する
	 * @return
	 */
	public ArrayList<Bullet> createBullet() {
		if (getState() == Enemy.State.ALIVE && enemy.shotable) {
			return enemy.shot();
		}
		return new ArrayList<Bullet>();
	}

	/**
	 * 敵を移動させる
	 */
	public void move() {
		if (enemy.state == Enemy.State.ALIVE && enemy.x > -16) {
			enemy.move();
		} else {
			enemy.state = Enemy.State.DIE;
		}
	}

	/**
	 * 敵オブジェクトの描画
	 */
	public void draw() {
		viewEnemy.draw(enemy);
	}
	
}
