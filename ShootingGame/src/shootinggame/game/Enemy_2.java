package shootinggame.game;

import org.Framework.R;

import android.graphics.Bitmap;
import shootinggame.Framework.AppManager;

public class Enemy_2 extends Enemy {

	public Enemy_2(){
		super(AppManager.getInstance().getBitmap(R.drawable.enemy2));
		this.InitSpriteData(61, 59, 3, 1);
		hp = 10;
		speed = 2.5f;
		
		movetype = Enemy.MOVE_PATTERN_2;
	}
	
	@Override
	public void Update(long GameTime){
		super.Update(GameTime);

		m_BoundBox.set(m_x,m_y,m_x+61,m_y+59);	
	}

}
