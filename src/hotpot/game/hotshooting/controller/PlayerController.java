package hotpot.game.hotshooting.controller;

import hotpot.game.framework.Input.TouchEvent;
import hotpot.game.hotshooting.Player;
import hotpot.game.hotshooting.shot.Bullet;
import hotpot.game.hotshooting.shot.PlayerShotA;
import hotpot.game.hotshooting.view.PlayerView;

import java.util.ArrayList;

import android.graphics.Color;

public class PlayerController {

	/** 移動中かどうか */
	boolean moveMode = false;

	/** 移動開始位置 */
	int moveStartX;
	int moveStartY;

	/** 移動先 */
	int moveX;
	int moveY;

	/** 移動速度 */
	int maxSpeed = 60;

	public int frameCount = 0;

	public Player player;
	public PlayerView vPlayer;

	public PlayerController(Player p, PlayerView pv) {
		player = p;
		vPlayer = pv;
		moveStartX = 0;
		moveStartY = 0;
		frameCount = 0;
	}

	public void setMovePoint(TouchEvent event) {
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

	public void move() {
		if (moveMode) {
			player.move(moveX, moveY);
		}
		frameCount++;
	}

	public ArrayList<Bullet> createBullet() {
		if (frameCount % 10 == 0) {
			if (player.state == Player.State.ALIVE) {
				createBulletA();
			}
		}

		return new ArrayList<Bullet>();
	}

	private ArrayList<Bullet> createBulletA() {
		ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
		if (frameCount < 1000) {
			bulletList.add(new PlayerShotA(player.x + 16, player.y + 8));
		} else {
			bulletList.add(new PlayerShotA(player.x + 16, player.y));
			bulletList.add(new PlayerShotA(player.x + 16, player.y + 16));
		}
		return bulletList;
	}

	public void hitBullet() {
		player.hitBullet();
	}

	public boolean isHit(BulletController b) {
		if (player.state == Player.State.ALIVE && b.isHit(player)) {
			return true;
		}

		return false;

	}

	public void draw() {
		if (player.state == Player.State.ALIVE) {
			vPlayer.draw(player.x, player.y);
		}

	}
}
