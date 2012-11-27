package hotpot.game.hotshooting;

import hotpot.game.hotshooting.move.Line;
import hotpot.game.hotshooting.move.Move;
import hotpot.game.hotshooting.move.Sine;
import hotpot.game.hotshooting.shot.Bullet;
import hotpot.game.hotshooting.shot.DownLeftShot;
import hotpot.game.hotshooting.shot.Normal;
import hotpot.game.hotshooting.shot.UpLeftShot;

import java.util.ArrayList;

public class Enemy {

	public enum State {
		ALIVE, DIE
	}

	enum MovePattern {
		LINE, SIN
	}

	public int x;
	public int y;
	public State state;
	public int hp;
	public int frameCount;
	public boolean shotable = false;
	Move moveObj;

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;

		state = State.ALIVE;
		hp = 10;
		frameCount = 0;
		moveObj = new Sine(x, y);
		move();

	}

	/**
	 * 脂肪時にスコアを返す
	 * 
	 * @return
	 */
	public int hitBullet() {
		hp--;
		if (hp < 1) {
			state = State.DIE;
			return 10;
		}
		return 0;
	}

	public void setMovePattern(MovePattern p) {
		if (p == MovePattern.LINE) {
			moveObj = new Line(x, y);
		} else if (p == MovePattern.SIN) {
			moveObj = new Sine(x, y);
		} else {
			moveObj = new Sine(x, y);
		}
	}

	public void move() {
		moveObj.move();
		this.x = moveObj.x;
		this.y = moveObj.y;
		frameCount++;

		if (frameCount % 80 == 0) {
			setMovePattern(MovePattern.LINE);
		}

		// 弾の生成
		if (frameCount % 100 == 50) {
			shotable = true;
		} else {
			shotable = false;
		}

	}

	/**
	 * 弾を生成する
	 * 
	 * @return　弾オブジェクトのリスト
	 */
	public ArrayList<Bullet> shot() {
		ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
		Bullet bullet = new Normal(x, y + 16);
		Bullet bullet2 = new UpLeftShot(x, y + 16);
		Bullet bullet3 = new DownLeftShot(x, y + 16);
		bulletList.add(bullet);
		bulletList.add(bullet2);
		bulletList.add(bullet3);
		return bulletList;
	}

}
