package hotpot.game.hotshooting;

import hotpot.game.framework.Game;
import hotpot.game.framework.Graphics;
import hotpot.game.framework.Input.TouchEvent;
import hotpot.game.framework.Screen;
import hotpot.game.hotshooting.controller.BulletController;
import hotpot.game.hotshooting.controller.EnemyController;
import hotpot.game.hotshooting.controller.PlayerController;
import hotpot.game.hotshooting.shot.Bullet;
import hotpot.game.hotshooting.view.BulletView;
import hotpot.game.hotshooting.view.PlayerView;
import hotpot.game.hotshooting.view.ViewEnemy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.util.Log;

public class GameScreen extends Screen {
	
	public int score;
	int frameCount = 0;

	public ArrayList<EnemyController> cEnemyList;
	public ArrayList<BulletController> cBulletList;
	public PlayerController cPlayer;
	public ArrayList<BulletController> pcBulletList;

	/** ランダム地 */
	Random rand;

	public GameScreen(Game game) {
		super(game);
		frameCount = 0;
		score = 0;
		rand = new Random();

		cEnemyList = new ArrayList<EnemyController>();
		cBulletList = new ArrayList<BulletController>();
		cPlayer = new PlayerController(new Player(30, 120), new PlayerView(
				game.getGraphics()));
		pcBulletList = new ArrayList<BulletController>();
		
		Gc.set(cEnemyList, cBulletList, pcBulletList);

	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> eventList = game.getInput().getTouchEvents();

		// イベント処理
		for (TouchEvent event : eventList) {
			cPlayer.setMovePoint(event);
		}

		turnOfPlayer();
		turnOfEnemy();

		Gc.gc();

		// ゲームカウント
		frameCount++;
		cPlayer.countUp();
		

	}

	public void isGameOver(){
		game.setScreen(new TopScreen(game));
	}
	
	/**
	 * 敵の行動
	 */
	private void turnOfEnemy() {
		// 敵の生成
		// TODO コントローラで生成タイミングを決める
		if (frameCount % 100 == 0) {
			cEnemyList.add(new EnemyController(new Enemy(320, rand
					.nextInt(320 - 16)), new ViewEnemy(game.getGraphics())));
		}
		// 敵の弾の生成
		for (EnemyController ec : cEnemyList) {
			ArrayList<Bullet> newBulletList = ec.createBullet();
			for (Bullet b : newBulletList) {
				cBulletList.add(new BulletController(b, new BulletView(game
						.getGraphics())));
			}
		}

		// 敵の移動
		for (EnemyController ec : cEnemyList) {
			ec.move();
		}
		// 敵の弾の移動
		for (BulletController bc : cBulletList) {
			bc.move();
		}

		checkHitToEnemyWithBullet(cEnemyList, pcBulletList);
		checkHitToPlayerWithBullet(cPlayer, cBulletList);
	}

	/**
	 * 見方の行動
	 */
	private void turnOfPlayer() {
		// プレイヤーの移動
		cPlayer.move();

		// 自分の弾の生成
		for (Bullet b : cPlayer.createBullet()) {

			pcBulletList.add(new BulletController(b, new BulletView(game
					.getGraphics())));
		}

		// 自分の弾の移動
		for (BulletController bc : pcBulletList) {
			bc.move();
		}
		checkHitToPlayerWithBullet(cPlayer, cBulletList);
		checkHitToEnemyWithBullet(cEnemyList, pcBulletList);
	}

	/**
	 * 敵の弾と見方の当たり判定
	 * 
	 * @param cPlayer2
	 * @param cBulletList2
	 */
	private void checkHitToPlayerWithBullet(PlayerController cPlayer,
			ArrayList<BulletController> cBulletList) {
		// 敵の弾とプレイヤーの当たり判定
		for (BulletController b : cBulletList) {
			if (b.getState() == Bullet.State.DIE)
				continue;
			if (cPlayer.isHit(b)) {
				b.hit();
				cPlayer.hitBullet();
			}
		}
	}

	/**
	 * 見方の弾と敵の当たり判定
	 * 
	 * @param cEnemyList
	 * @param pcBulletList
	 */
	private void checkHitToEnemyWithBullet(
			ArrayList<EnemyController> cEnemyList,
			ArrayList<BulletController> pcBulletList) {
		for (BulletController b : pcBulletList) {
			if (b.getState() == Bullet.State.DIE)
				continue;
			for (EnemyController ec : cEnemyList) {
				if (ec.isHit(b.bullet)) {
					b.hit();
					score += ec.hitBullet();
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 320, 480, Color.WHITE);
		g.drawRect(0, 320, 320, 160, Color.GRAY);

		// プレイヤーの描画
		cPlayer.draw();

		// 操作の中心点の描画
		g.drawRect(cPlayer.moveStartX, cPlayer.moveStartY, 16, 16, Color.RED);

		// 自分の弾の描画
		for (BulletController b : pcBulletList) {
			b.draw();
		}

		// 敵の弾の描画
		for (BulletController b : cBulletList) {
			b.draw();
		}

		// 敵の描画
		for (EnemyController ec : cEnemyList) {
			ec.draw();
		}

		// HP
		g.drawText("HP:" + Integer.toString(cPlayer.player.hp), 10, 10,
				Color.BLACK);
		// スコア
		g.drawText("Score:" + Integer.toString(score), 100, 10, Color.BLACK);

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
