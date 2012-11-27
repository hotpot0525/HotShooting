package hotpot.game.hotshooting;

import hotpot.game.framework.Screen;
import hotpot.game.framework.impl.AndroidGame;

public class HotShootingGame extends AndroidGame {
    /** Called when the activity is first created. */

    @Override
    public Screen getStartScreen() {
//        return new GameScreen(this);
    	return new LoadingScreen(this);
    }

}