package shootinggame.game;

import org.Framework.R;

import shootinggame.Framework.AppManager;
import shootinggame.Framework.SpriteAnimation;

public class Enemy_1 extends Enemy {
	
	public Enemy_1(){
		super(AppManager.getInstance().getBitmap(R.drawable.enemy1));
		this.InitSpriteData(61, 59, 3, 1);
		hp = 10;
		speed = 2.5f;
		
		movetype = Enemy.MOVE_PATTERN_1;
	}
	
	@Override
	public void Update(long GameTime){
		super.Update(GameTime);
		
		m_BoundBox.set(m_x,m_y,m_x+61,m_y+59);		
	}
	
}
