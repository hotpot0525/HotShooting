package hotpot.game.hotshooting;

import hotpot.game.framework.Game;
import hotpot.game.framework.Graphics;
import hotpot.game.framework.Input.TouchEvent;
import hotpot.game.framework.Screen;

import java.util.List;

import android.graphics.Color;

public class GameScreen extends Screen {

	public GameScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> eventList = game.getInput().getTouchEvents();
		for(TouchEvent event : eventList){
			if(event.x < 320){
				//hoge
			}
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 320, 480, Color.WHITE);
		g.drawRect(30, 120, 48, 48, Color.BLUE);


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
