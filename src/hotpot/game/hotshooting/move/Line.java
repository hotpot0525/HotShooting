package hotpot.game.hotshooting.move;

public class Line extends Move{

	public Line(int x, int y){
		super(x,y);

	}

	@Override
	public void move() {
		x--;
		if(x < 160){
			if(y < 240){
				y--;
			}else{
				y++;
			}
		}
	}

}
