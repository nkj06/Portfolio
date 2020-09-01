package shootinggame.game;

import org.Framework.R;

import shootinggame.Framework.AppManager;

public class Missile_Player extends Missile {
	
	Missile_Player(int x,int y){
		super(AppManager.getInstance().getBitmap(R.drawable.missile_1));
		this.SetPosition(x+20, y);
	}
	
	public void Update(){
		m_y-=2;
		if(m_y < 10)
			state = STATE_OUT;
		
		m_BoundBox.set(m_x,m_y,m_x+1,m_y+1);
		
	}
}
