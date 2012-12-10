package hotpot.game.hotshooting;

import hotpot.game.framework.Game;
import hotpot.game.framework.Graphics;
import hotpot.game.framework.Input.TouchEvent;
import hotpot.game.framework.Screen;
import hotpot.game.framework.impl.AndroidAudio;
import hotpot.game.hotshooting.dialog.AndroidDialog;

import java.util.List;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Rect;

public class TopScreen extends Screen {

	public Rect start;
	public Rect ranking;
	public Rect help;

	public TopScreen(Game game) {
		super(game);
		start = new Rect(80, 220, 230, 260);
		ranking = new Rect(80, 290, 230, 330);
		help = new Rect(80, 360, 230, 400);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> eventList = game.getInput().getTouchEvents();
		for (TouchEvent event : eventList) {
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (start.contains(event.x, event.y)) {
					game.setScreen(new GameScreen(game));
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		// 拝啓
		g.drawRect(0, 0, g.getWidth(), g.getHeight(), Color.WHITE);

		// スタート
		g.drawRect(start.left, start.top, start.width(), start.height(),
				Color.GREEN);

		// ランキング
		g.drawRect(ranking.left, ranking.top, ranking.width(),
				ranking.height(), Color.YELLOW);

		// ヘルプ
		g.drawRect(help.left, help.top, help.width(), help.height(), Color.RED);

		new AndroidDialog(g).setNaturalText("hoge").setNegativeText("huga")
				.setPositiveText("aho").setMessage("hoge").setTitle("taitol").show();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
