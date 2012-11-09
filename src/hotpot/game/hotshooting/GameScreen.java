package hotpot.game.hotshooting;

import hotpot.game.framework.Game;
import hotpot.game.framework.Graphics;
import hotpot.game.framework.Input.TouchEvent;
import hotpot.game.framework.Screen;

import java.util.List;

import android.graphics.Color;
import android.util.Log;

public class GameScreen extends Screen {

	Player player;
	boolean moveMode = false;
	int moveStartX;
	int moveStartY;
	int maxSpeed = 40;

	public GameScreen(Game game) {
		super(game);
		player = new Player(30, 120);
		moveStartX = 0;
		moveStartY = 0;
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> eventList = game.getInput().getTouchEvents();
		for (TouchEvent event : eventList) {
			Log.e("touchvent", "type:" + event.type);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (event.y > 320) {
					moveMode = true;
					moveStartX = event.x;
					moveStartY = event.y;
				}
			}

			if (event.type == TouchEvent.TOUCH_DRAGGED) {
				if (moveMode) {
					int moveX = Math
							.max(Math.min(event.x - moveStartX, maxSpeed),
									-maxSpeed);
					int moveY = Math
							.max(Math.min(event.y - moveStartY, maxSpeed),
									-maxSpeed);

					player.x += 1 * (moveX) / 10;
					player.y += 1 * (moveY) / 10;

					player.x = Math.max(0, Math.min(player.x, 320-16));
					player.y = Math.max(0, Math.min(player.y, 320-16));

				}
			}

			if (event.type == TouchEvent.TOUCH_UP) {
				if (moveMode) {
					moveMode = false;
					moveStartX = 0;
					moveStartY = 0;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 320, 480, Color.WHITE);
		g.drawRect(0, 320, 320, 160, Color.GRAY);
		g.drawRect(player.x, player.y, 16, 14, Color.BLUE);

		g.drawRect(moveStartX, moveStartY, 16, 16, Color.RED);
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
