package hotpot.game.hotshooting.controller;

import hotpot.game.hotshooting.shot.Bullet;
import hotpot.game.hotshooting.view.BulletView;

public class BulletController {

	public Bullet bullet;
	public BulletView vBullet;

	public BulletController(Bullet bullet, BulletView vBullet) {
		this.bullet = bullet;
		this.vBullet = vBullet;
	}
	
	

}
