package shootinggame.game;

import org.Framework.R;

import android.graphics.Bitmap;
import shootinggame.Framework.AppManager;

public class Item_AddPower extends Item {

	public Item_AddPower(int x,int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.item3));
		this.InitSpriteData(51, 51, 3, 4);		
		
		m_x = x;
		m_y = y;		
	}
	@Override
	void GetItem(){
		GameState.getInstance().m_power += 1;
		Player.m_power_s -= 100;
	}

}
