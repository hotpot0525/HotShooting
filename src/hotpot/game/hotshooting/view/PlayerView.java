package hotpot.game.hotshooting.view;

import hotpot.game.framework.Graphics;
import android.graphics.Color;

public class PlayerView {

	Graphics g;

	public PlayerView(Graphics g) {
		this.g = g;
	}

	public void draw(int x, int y) {

		g.drawRect(x, y, 16, 14, Color.BLUE);

	}

}
