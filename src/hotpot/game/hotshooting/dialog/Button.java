package hotpot.game.hotshooting.dialog;

import hotpot.game.framework.Graphics;
import hotpot.game.framework.Input.TouchEvent;
import android.graphics.Color;
import android.graphics.Rect;

public class Button {

	private Rect r;
	private ButtonListener listener;
	Graphics g;
	private String text;
	
	public Button(Graphics g) {
		this.g = g;
		r = new Rect();
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public void setPosition(int left, int top, int width, int height){
		r.set(left, top, left+width, top+height);
	}
	public void setOnClickListener(ButtonListener listener){
		this.listener = listener;
	}
	
	public boolean runIfClicked(TouchEvent event){
		if(event.type == TouchEvent.TOUCH_UP && r.contains(event.x, event.y)){
			onClick();
			return true;
		}
		return false;
	}
	
	private void onClick(){
		listener.onClick();
	}

	public void draw() {
		g.drawRect(r.left, r.top, r.right, r.bottom, Color.WHITE);
		
	}	

}
