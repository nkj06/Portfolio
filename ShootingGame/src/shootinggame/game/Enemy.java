package shootinggame.game;

import android.graphics.Bitmap;
import android.graphics.Rect;
import shootinggame.Framework.AppManager;
import shootinggame.Framework.SpriteAnimation;

public class Enemy extends SpriteAnimation {

	public static final int STATE_NORMAL = 0;
	public static final int STATE_OUT = 1;
	
	public int state =  STATE_NORMAL;

	Rect m_BoundBox = new Rect();
	
	public static final int MOVE_PATTERN_1 = 0;
	public static final int MOVE_PATTERN_2 = 1;
	public static final int MOVE_PATTERN_3 = 2;

	protected int movetype;
	
	protected int hp;
	protected float speed;
	

	long LastShoot = System.currentTimeMillis();
	
	public Enemy(Bitmap bitmap) {
		super(bitmap);
	}
	public void Damage(int damage){
		hp -= damage; 
	}
	public int GetHP(){
		return hp;
	}
	
	void Move(){
		if(movetype == MOVE_PATTERN_1){
			if(m_y<=200){
				m_y+= speed;
			}
			else {
				m_y+= speed *2;
			}
			
		}
		else if(movetype == MOVE_PATTERN_2){
			if(m_y<=200){
				m_y+= speed;
			}
			else {
				m_x+= speed; 
				m_y+= speed;   
			}
			
		}
		else if(movetype == MOVE_PATTERN_3){
			if(m_y<=200){
				m_y+= speed; 
			}
			else {
				m_x-= speed; 
				m_y+= speed;   
			}
			
		}
		if(m_y > 420)
			state = STATE_OUT;
		
	}
	
	void Attack(){
		if(System.currentTimeMillis() - LastShoot >= 600){
			LastShoot = System.currentTimeMillis();
			GameState.getInstance().m_enemmslist.add(new Missile_Enemy(m_x+10, m_y+50));
		}
	}
	
	@Override
	public void Update(long GameTime){
		super.Update(GameTime);
		Attack();
		Move();
	}
	
}
