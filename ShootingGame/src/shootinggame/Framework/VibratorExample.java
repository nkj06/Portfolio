package shootinggame.Framework;

import android.app.Activity;
import android.os.Bundle;

public class VibratorExample extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new GameView(this));
	}
}
