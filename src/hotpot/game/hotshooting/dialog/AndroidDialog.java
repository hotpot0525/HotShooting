package hotpot.game.hotshooting.dialog;

import hotpot.game.framework.Graphics;
import hotpot.game.framework.Input.TouchEvent;
import android.graphics.Color;
import android.graphics.Rect;

public class AndroidDialog implements Dialog {

	private Rect dialogRect;

	protected String title;
	protected String message;
	protected boolean visible = true;

	protected Button positiveButton;
	protected Button naturalButton;
	protected Button negativeButton;

	protected Graphics g;

	public AndroidDialog(Graphics g) {
		this.g = g;
		dialogRect = new Rect(g.getWidth() / 4, g.getHeight() / 4,
				g.getWidth() / 4 * 3, g.getHeight() / 4 * 3);
	}

	public void show(Graphics g) {
		if(!visible) return;
		
		g.drawRect(dialogRect.left, dialogRect.top, dialogRect.right,
				dialogRect.bottom, Color.BLUE);
		if (positiveButton != null) {
			positiveButton.draw();
		}
		if (naturalButton != null) {
			positiveButton.draw();
		}
		if (naturalButton != null) {
			positiveButton.draw();
		}
	}

	public boolean runIfCliked(TouchEvent event) {
		visible = false;
		 if(positiveButton.runIfClicked(event)){
			 return true;
		 }
		 if(naturalButton.runIfClicked(event)){
			 return true;
		 }
		 if(negativeButton.runIfClicked(event)){
			 return true;
		 }
		 visible = true;
		 return false;
	}

	@Override
	public Dialog setTitle(String title) {
		this.title = title;
		return this;
	}

	@Override
	public Dialog setMessage(String message) {
		this.message = message;
		return this;
	}

	@Override
	public Dialog setPositiveText(String text) {
		createButtonInstance(negativeButton);
		int left = dialogRect.left + dialogRect.width() / 5*4;
		int top = dialogRect.top+ dialogRect.height() / 5*4;
		int width = dialogRect.width() / 5*5;
		int height = dialogRect.height() / 5*5;
		positiveButton.setPosition(left, top, width, height);
		
		positiveButton.setText(text);
		return this;
	}

	@Override
	public Dialog setNaturalText(String text) {
		createButtonInstance(negativeButton);
		int left = dialogRect.left + dialogRect.width() / 5*3;
		int top = dialogRect.top+ dialogRect.height() / 5*3;
		int width = dialogRect.width() / 5*4;
		int height = dialogRect.height() / 5*4;
		naturalButton.setPosition(left, top, width, height);
		
		naturalButton.setText(text);
		return this;
	}

	@Override
	public Dialog setNegativeText(String text) {
		createButtonInstance(negativeButton);
		int left = dialogRect.left + dialogRect.width() / 5*1;
		int top = dialogRect.top+ dialogRect.height() / 5*1;
		int width = dialogRect.width() / 5*2;
		int height = dialogRect.height() / 5*2;
		naturalButton.setPosition(left, top, width, height);
		
		naturalButton.setText(text);
		return this;
	}

	@Override
	public Dialog setPositiveListener(ButtonListener listener) {
		if(createButtonInstance(negativeButton)){
			// デフォルトテキスト
			positiveButton.setText("OK");
		}
		positiveButton.setOnClickListener(listener);
		return this;
	}

	@Override
	public Dialog setNaturalListener(ButtonListener listener) {
		if(createButtonInstance(negativeButton)){
			// デフォルトテキスト
			naturalButton.setText("OK");
		}
		naturalButton.setOnClickListener(listener);
		return this;
	}

	@Override
	public Dialog setNegativeListener(ButtonListener listener) {
		if(createButtonInstance(negativeButton)){
			// デフォルトテキスト
			negativeButton.setText("CANCEL");
		}
		negativeButton.setOnClickListener(listener);
		return this;
	}

	
	/**
	 * ボタンオブジェクトを作成したらtrueを返す
	 * @param button
	 * @return
	 */
	private boolean createButtonInstance(Button button) {
		if(button == null){
			button = new Button(g);
			return true;
		}
		return false;
	}
}
