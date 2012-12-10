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
		int left = 40;
		int top = 150;
		int right = left + 230;
		int bottom = top + 110;
		dialogRect = new Rect(left, top, right, bottom);
	}

	public void show() {
		if (!visible)
			return;

		g.drawRect(dialogRect.left, dialogRect.top, dialogRect.width(),
				dialogRect.height(), Color.BLUE);
		if (title != null)
			g.drawText(title, dialogRect.left + 50, dialogRect.top + 30,
					Color.WHITE);
		if (message != null)
			g.drawText(message, dialogRect.left + 10, dialogRect.top + 50,
					Color.WHITE);
		if (positiveButton != null) {
			positiveButton.draw();
		}
		if (naturalButton != null) {
			naturalButton.draw();
		}
		if (negativeButton != null) {
			negativeButton.draw();
		}
	}

	public boolean runIfCliked(TouchEvent event) {
		visible = false;
		if (positiveButton.runIfClicked(event)) {
			return true;
		}
		if (naturalButton.runIfClicked(event)) {
			return true;
		}
		if (negativeButton.runIfClicked(event)) {
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
		if (positiveButton == null)
			positiveButton = new Button(g);
		int left = dialogRect.left + 150;
		int top = dialogRect.top + 70;
		int width = 60;
		int height = 30;
		positiveButton.setPosition(left, top, width, height);

		positiveButton.setText(text);
		return this;
	}

	@Override
	public Dialog setNaturalText(String text) {
		if (naturalButton == null)
			naturalButton = new Button(g);
		int left = dialogRect.left + 80;
		int top = dialogRect.top + 70;
		int width = 60;
		int height = 30;
		naturalButton.setPosition(left, top, width, height);

		naturalButton.setText(text);
		return this;
	}

	@Override
	public Dialog setNegativeText(String text) {
		if (negativeButton == null)
			negativeButton = new Button(g);
		int left = dialogRect.left + 10;
		int top = dialogRect.top + 70;
		int width = 60;
		int height = 30;
		negativeButton.setPosition(left, top, width, height);

		negativeButton.setText(text);
		return this;
	}

	@Override
	public Dialog setPositiveListener(ButtonListener listener) {
		if (positiveButton == null) {
			positiveButton = new Button(g);
			// デフォルトテキスト
			positiveButton.setText("OK");
		}
		positiveButton.setOnClickListener(listener);
		return this;
	}

	@Override
	public Dialog setNaturalListener(ButtonListener listener) {
		if (naturalButton == null) {
			naturalButton = new Button(g);
			// デフォルトテキスト
			naturalButton.setText("OK");
		}
		naturalButton.setOnClickListener(listener);
		return this;
	}

	@Override
	public Dialog setNegativeListener(ButtonListener listener) {
		if (negativeButton == null) {
			negativeButton = new Button(g);
			// デフォルトテキスト
			negativeButton.setText("CANCEL");
		}
		negativeButton.setOnClickListener(listener);
		return this;
	}

}
