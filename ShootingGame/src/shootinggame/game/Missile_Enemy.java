package shootinggame.game;

import org.Framework.R;

import shootinggame.Framework.AppManager;

public class Missile_Enemy extends Missile {
	Missile_Enemy(int x,int y){
		super(AppManager.getInstance().getBitmap(R.drawable.missile_2));
		this.SetPosition(x, y);
	}
	
	public void Update(){
		m_y+=4;
		if(m_y > 500)
			state = STATE_OUT;
		
		m_BoundBox.set(m_x,m_y,m_x+43,m_y+43);
		
	}
}
