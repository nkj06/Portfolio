package shootinggame.game;

import org.Framework.R;

import shootinggame.Framework.AppManager;

public class Item_AddLife extends Item {
	
	public Item_AddLife(int x,int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.item2));
		this.InitSpriteData(51, 51, 3, 4);		
		
		m_x = x;
		m_y = y;
	}

	@Override
	void GetItem(){
		GameState.getInstance().GetPlayer().AddLife();
	}
}
