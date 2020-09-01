package shootinggame.game;

import org.Framework.R;

import android.graphics.Bitmap;
import shootinggame.Framework.AppManager;
import shootinggame.Framework.SpriteAnimation;

public class Effect_Explosion extends SpriteAnimation {

	public Effect_Explosion(int x , int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.explosion));
		this.InitSpriteData(104	, 66, 3, 6);
		
		m_x = x;
		m_y = y;
		
		mbReply = false;
	}

}
