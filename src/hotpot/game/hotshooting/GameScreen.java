package hotpot.game.hotshooting;

import hotpot.game.framework.Game;
import hotpot.game.framework.Graphics;
import hotpot.game.framework.Input.TouchEvent;
import hotpot.game.framework.Screen;
import hotpot.game.hotshooting.Enemy.State;
import hotpot.game.hotshooting.controller.EnemyController;
import hotpot.game.hotshooting.shot.Bullet;
import hotpot.game.hotshooting.shot.PlayerShotA;
import hotpot.game.hotshooting.view.ViewEnemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.graphics.Rect;

public class GameScreen extends Screen {

	Player player;
	boolean moveMode = false;
	int moveStartX;
	int moveStartY;
	int moveX;
	int moveY;
	int maxSpeed = 60;
	public int score;

	int frameCount = 0;

	public ArrayList<EnemyController> cEnemyList;

	/** 自分の弾のリスト **/
	ArrayList<Bullet> bulletList;

	/** 敵の弾のリスト **/
	ArrayList<Bullet> enemBulletList;

	/** ランダム地 */
	Random rand;

	public GameScreen(Game game) {
		super(game);
		player = new Player(30, 120);
		moveStartX = 0;
		moveStartY = 0;

		frameCount = 0;
		score = 0;
		bulletList = new ArrayList<Bullet>();
		enemBulletList = new ArrayList<Bullet>();
		rand = new Random();

		cEnemyList = new ArrayList<EnemyController>();


	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> eventList = game.getInput().getTouchEvents();

		// イベント処理
		for (TouchEvent event : eventList) {

			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (event.y > 320) {
					moveMode = true;
					moveStartX = event.x;
					moveStartY = event.y;
				}
			}
			if (event.type == TouchEvent.TOUCH_DRAGGED) {

				moveX = Math.max(Math.min(event.x - moveStartX, maxSpeed),
						-maxSpeed);
				moveY = Math.max(Math.min(event.y - moveStartY, maxSpeed),
						-maxSpeed);
			}
			if (event.type == TouchEvent.TOUCH_UP) {
				if (moveMode) {
					moveMode = false;
					moveStartX = 0;
					moveStartY = 0;
				}
			}
		}

		// プレイヤーの移動
		if (moveMode) {
			player.move(moveX, moveY);
		}

		// 自分の弾の生成
		if (frameCount % 10 == 0) {
			if (player.state == Player.State.ALIVE) {
				createBullet();
			}
		}

		// 敵の生成
		if (frameCount % 100 == 0) {
			cEnemyList.add(new EnemyController(new Enemy(320, rand.nextInt(320 - 16)), new ViewEnemy(game.getGraphics())));
		}

		// 自分の弾の移動
		for (Bullet b : bulletList) {
			if (b.state == Bullet.State.ALIVE && b.x < 320 + 16) {
				b.move();
			} else {
				b.state = Bullet.State.DIE;
			}
		}

		// 自分の弾と敵の当たり判定
		for (Bullet b : bulletList) {
			if (b.state == Bullet.State.DIE) {
				continue;
			}
			
			for(EnemyController ec: cEnemyList){
				if(ec.isHit(b)){
					b.hitEnemy();
					score += ec.hitBullet();
				}
			}
		}

		// 敵の弾とプレイヤーの当たり判定
		for (Bullet b : enemBulletList) {
			if (b.state == Bullet.State.DIE) {
				continue;
			}

			if (player.state == Player.State.ALIVE
					&& new Rect(b.x, b.y, b.x + 16, b.y + 16).intersect(
							player.x, player.y, player.x + 16, player.y + 16)) {
				b.hitEnemy();
				player.hitBullet();
			}

		}

		// 敵オブジェクトの破棄
		for(int i = cEnemyList.size() -1; i > -1; --i){
			if (cEnemyList.get(i).getState() != Enemy.State.ALIVE) {
				cEnemyList.get(i).destroy();
				cEnemyList.remove(i);
			}			
		}
		
		// プレイヤー弾のオブジェクト破棄
		for (int i = bulletList.size() - 1; i > -1; --i) {
			if (bulletList.get(i).state != Bullet.State.ALIVE) {
				bulletList.remove(i);
			}
		}
		// 敵の弾のオブジェクト破棄
		for (int i = enemBulletList.size() - 1; i > -1; --i) {
			if (enemBulletList.get(i).state != Bullet.State.ALIVE) {
				enemBulletList.remove(i);
			}
		}

		// 敵の弾の生成
		for(EnemyController ec : cEnemyList){
			ArrayList<Bullet> newBulletList = ec.createBullet();
			for (Bullet b : newBulletList) {
				enemBulletList.add(b);
			}			
		}

		// 敵の移動
		for(EnemyController ec : cEnemyList){
			ec.move();
		}

		// 敵の弾の移動
		for (Bullet b : enemBulletList) {
			if (b.state == Bullet.State.ALIVE && b.x > -16) {
				b.move();
			} else {
				b.state = Bullet.State.DIE;
			}
		}

		// ゲームカウント
		frameCount++;
	}

	private void createBullet() {
		if (frameCount < 1000) {
			bulletList.add(new PlayerShotA(player.x + 16, player.y + 8));
		} else {
			bulletList.add(new PlayerShotA(player.x + 16, player.y));
			bulletList.add(new PlayerShotA(player.x + 16, player.y + 16));
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 320, 480, Color.WHITE);
		g.drawRect(0, 320, 320, 160, Color.GRAY);

		// プレイヤーの描画
		if (player.state == Player.State.ALIVE) {
			g.drawRect(player.x, player.y, 16, 14, Color.BLUE);
		}

		// 操作の中心点の描画
		g.drawRect(moveStartX, moveStartY, 16, 16, Color.RED);

		// 自分の弾の描画
		for (Bullet b : bulletList) {
			if (b.state == Bullet.State.ALIVE) {
				g.drawRect(b.x, b.y, 4, 4, Color.GREEN);
			}
		}

		// 敵の弾の描画
		for (Bullet b : enemBulletList) {
			if (b.state == Bullet.State.ALIVE) {
				g.drawRect(b.x, b.y, 4, 4, Color.RED);
			}
		}

		// 敵の描画
		for(EnemyController ec : cEnemyList){
			ec.draw();
		}

		// HP
		g.drawText("HP:" + Integer.toString(player.hp), 10, 10, Color.BLACK);
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
