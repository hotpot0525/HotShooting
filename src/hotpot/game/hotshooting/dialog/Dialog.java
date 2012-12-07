package hotpot.game.hotshooting.dialog;

import hotpot.game.framework.Graphics;

public interface Dialog {
	
	public void show(Graphics g);
	
	public Dialog setTitle(String title);
	
	public Dialog setMessage(String message);
	
	public Dialog setPositiveText(String text);
	
	public Dialog setNaturalText(String text);
	
	public Dialog setNegativeText(String text);
	
	public Dialog setPositiveListener(ButtonListener listener);
	
	public Dialog setNaturalListener(ButtonListener listener);
	
	public Dialog setNegativeListener(ButtonListener listener);
}
