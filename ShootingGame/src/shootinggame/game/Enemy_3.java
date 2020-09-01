package shootinggame.game;

import org.Framework.R;

import shootinggame.Framework.AppManager;

public class Enemy_3 extends Enemy {

	public Enemy_3(){
		super(AppManager.getInstance().getBitmap(R.drawable.enemy3));
		this.InitSpriteData(61, 59, 3, 1);
		hp = 10;
		speed = 2.5f;
		
		movetype = Enemy.MOVE_PATTERN_3;
	}
	
	@Override
	public void Update(long GameTime){
		super.Update(GameTime);

		m_BoundBox.set(m_x,m_y,m_x+61,m_y+59);	
	}

}
