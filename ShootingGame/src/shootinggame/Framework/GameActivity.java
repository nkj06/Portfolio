package shootinggame.Framework;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;



public class GameActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    	AppManager.getInstance().setActivity(this);
        setContentView(new GameView(this));
    }
}