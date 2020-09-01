package shootinggame.game;

import org.Framework.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import shootinggame.Framework.SpriteAnimation;

public class Player extends SpriteAnimation {
	long LastShoot = System.currentTimeMillis();

public static int m_power_s = 1000; // 공격 속도
	Rect m_BoundBox = new Rect();
	
	int m_Life = 6;
	int m_power = 10;
	
	public Player(Bitmap bitmap) {
		super(bitmap);
		this.InitSpriteData(104, 62, 3, 6);
		this.SetPosition(135, 450);
	}
	
	
	public int GetPower(){
		return m_power;
	}
	public void SetPower(int m_power){
		m_power = m_power;
	}
	
	private boolean bMove = false;
	private int m_dirX = 0;
	private int m_dirY = 0;
	
	public int getLife(){
		return m_Life;
	}
	public void destroyPlayer(){
		m_Life--;
	}
	public void AddLife(){
		m_Life++;
	}
	
	public void startMove(int dirX,int dirY){
		bMove = true;
		m_dirX = dirX;
		m_dirY = dirY;		
	}

	public void stopMove(){
		bMove =false;
		m_dirX = 0;
		m_dirY = 0;
	}

	@Override
	public void Update(long GameTime){
		 super.Update(GameTime);
		 Attack();

		if(bMove){
			this.m_x += m_dirX;
			this.m_y += m_dirY;
		}
		
		m_BoundBox.set(m_x,m_y,m_x+42,m_y+44);	
	}
	
	void Attack(){
		if(System.currentTimeMillis() - LastShoot >= m_power_s){
			LastShoot = System.currentTimeMillis();
			GameState.getInstance().m_pmslist.add(new Missile_Player(m_x-10, m_y+20));
		}
	}

}
