package hotpot.game.hotshooting;

import hotpot.game.hotshooting.controller.BulletController;
import hotpot.game.hotshooting.controller.EnemyController;
import hotpot.game.hotshooting.shot.Bullet;

import java.util.ArrayList;

public class Gc {
	private static ArrayList<EnemyController> cEnemyList = new ArrayList<EnemyController>();
	private static ArrayList<BulletController> pcBulletList = new ArrayList<BulletController>();
	private static ArrayList<BulletController> cBulletList = new ArrayList<BulletController>();

	
	public static void set(ArrayList<EnemyController> cEnemyList2,
			ArrayList<BulletController> cBulletList2,
			ArrayList<BulletController> pcBulletList2) {
		cEnemyList = cEnemyList2;
		pcBulletList = pcBulletList2;
		cBulletList = cBulletList2;
		
	}	
	
	/**
	 * 不要オブジェクトの削除
	 */
	public static void gc() {
		// 敵オブジェクトの破棄
		for (int i = cEnemyList.size() - 1; i > -1; --i) {
			if (cEnemyList.get(i).getState() != Enemy.State.ALIVE) {
				cEnemyList.get(i).destroy();
				cEnemyList.remove(i);
			}
		}

		// プレイヤー弾のオブジェクト破棄
		for (int i = pcBulletList.size() - 1; i > -1; --i) {
			if (pcBulletList.get(i).getState() != Bullet.State.ALIVE) {
				pcBulletList.remove(i);
			}
		}

		// 敵の弾のオブジェクト破棄
		for (int i = cBulletList.size() - 1; i > -1; --i) {
			if (cBulletList.get(i).getState() != Bullet.State.ALIVE) {
				cBulletList.get(i).destroy();
				cBulletList.remove(i);
			}
		}
	}


}
