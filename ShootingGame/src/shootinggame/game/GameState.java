package shootinggame.game;

import java.util.ArrayList;
import java.util.Random;

import org.Framework.R;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import shootinggame.Framework.AppManager;
import shootinggame.Framework.Collision;
import shootinggame.Framework.CollisionManager;
import shootinggame.Framework.GraphicObject;
import shootinggame.Framework.IState;

@TargetApi(Build.VERSION_CODES.ECLAIR)
public class GameState implements IState {

	int move_touch_id = -1;
	int attack_touch_id = -1;

	private Player m_player;

	private GraphicObject m_keypad;

	private BackGround m_background;

	ArrayList<Missile> m_pmslist = new ArrayList<Missile>();
	ArrayList<Missile> m_enemmslist = new ArrayList<Missile>();

	ArrayList<Item> m_itemlist = new ArrayList<Item>();

	ArrayList<Enemy> m_enemlist = new ArrayList<Enemy>();

	ArrayList<Effect_Explosion> m_explist = new ArrayList<Effect_Explosion>();

	private int m_scroll = 0;
	private final static int SCROLL_SPEED = 1;
	private int playState = 0;

	public int score = 0;
	public int m_power = 0;

	long LastRegenEnemy = System.currentTimeMillis();

	Random randEnem = new Random();
	Random randItem = new Random();

	private static GameState s_instance;

	public Player GetPlayer() {
		return m_player;
	}

	public static GameState getInstance() {
		if (s_instance == null) {
			s_instance = new GameState();
		}
		return s_instance;
	}

	private GameState() {

	}

	@Override
	public void Destroy() {
		m_enemlist.clear();
		m_enemmslist.clear();
		m_pmslist.clear();
	}

	public void CreateItem(int x, int y) {

		int rand = randItem.nextInt(100);

		if (rand >= 90)
			m_itemlist.add(new Item_AddLife(x, y));
		else if (rand <= 25)
			m_itemlist.add(new Item_AddPower(x, y));
		else
			m_itemlist.add(new Item_AddScore(x, y));

	}

	@Override
	public void Init() {
		m_player = new Player(AppManager.getInstance().getBitmap(
				R.drawable.player));
		m_keypad = new GraphicObject(AppManager.getInstance().getBitmap(
				R.drawable.keypad));
		m_background = new BackGround(1);
		m_keypad.SetPosition(0, 460 - 120);
	}

	public void CheckCollision() {
		for (int i = m_pmslist.size() - 1; i >= 0; i--) {
			for (int j = m_enemlist.size() - 1; j >= 0; j--) {
				if (CollisionManager.CheckBoxToBox(m_pmslist.get(i).m_BoundBox, m_enemlist.get(j).m_BoundBox)) {
					m_explist.add(new Effect_Explosion(m_enemlist.get(j).GetX(), m_enemlist.get(j).GetY()));
					CreateItem(m_enemlist.get(j).GetX(), m_enemlist.get(j).GetY());

					m_pmslist.remove(i);

					m_enemlist.get(j).Damage(m_player.GetPower());
					if (m_enemlist.get(j).GetHP() <= 0)
						m_enemlist.remove(j);

					return;
				}
			}
		}

		for (int i = m_enemlist.size() - 1; i >= 0; i--) {
			if (CollisionManager.CheckBoxToBox(m_player.m_BoundBox,m_enemlist.get(i).m_BoundBox)) {
				m_explist.add(new Effect_Explosion(m_enemlist.get(i).GetX(), m_enemlist.get(i).GetY()));
				m_enemlist.remove(i);
				m_player.destroyPlayer(); // 없으면 무적
				AppManager.getInstance().getGameView().vibrator(); // 충돌 시 진동
				
				if (m_player.getLife() <= 0)
					AppManager.getInstance().getActivity().finish();
					//System.exit(0);
				}
		}

		for (int i = m_enemmslist.size() - 1; i >= 0; i--) {
			if (CollisionManager.CheckBoxToBox(m_player.m_BoundBox, m_enemmslist.get(i).m_BoundBox)) {
				m_enemmslist.remove(i);
				m_player.destroyPlayer(); // 없으면 무적
				AppManager.getInstance().getGameView().vibrator(); // 충돌 시 진동
				if (m_player.getLife() <= 0) {
					AppManager.getInstance().getActivity().finish();
					//System.exit(0);
				}

			}
		}
		for (int i = m_itemlist.size() - 1; i >= 0; i--) {
			if (CollisionManager.CheckBoxToBox(m_player.m_BoundBox,m_itemlist.get(i).m_BoundBox)) {
				m_itemlist.get(i).GetItem();
				m_itemlist.remove(i);
			}
		}
	}

	public void MakeEnemy() {

		if (System.currentTimeMillis() - LastRegenEnemy >= 1000) {
			LastRegenEnemy = System.currentTimeMillis();

			int enemtype = randEnem.nextInt(3);
			Enemy enem = null;
			if (enemtype == 0) {
				enem = new Enemy_1();
			} else if (enemtype == 1) {
				enem = new Enemy_2();
			} else if (enemtype == 2) {
				enem = new Enemy_3();
			}

			enem.SetPosition(randEnem.nextInt(280), -60);
			enem.movetype = randEnem.nextInt(3);

			m_enemlist.add(enem);
		}

		m_scroll += SCROLL_SPEED;
	}

	@Override
	public void Render(Canvas canvas) {
		m_background.Draw(canvas);
		for (Missile pms : m_pmslist) {
			pms.Draw(canvas);
		}
		for (Missile enemms : m_enemmslist) {
			enemms.Draw(canvas);
		}
		for (Enemy enem : m_enemlist) {
			enem.Draw(canvas);
		}
		for (Effect_Explosion exp : m_explist) {
			exp.Draw(canvas);
		}
		for (Item item : m_itemlist) {
			item.Draw(canvas);
		}
		m_player.Draw(canvas);

		Paint p = new Paint();
		p.setTextSize(20);
		p.setColor(Color.BLACK);
		canvas.drawText("LIFE :" + String.valueOf(m_player.getLife()), 0, 20, p);
		canvas.drawText("SCORE :" + String.valueOf(score), 0, 40, p);
		canvas.drawText("POWER :" + String.valueOf(m_power), 0, 60, p);
	}

	@Override
	public void Update() {
		long GameTime = System.currentTimeMillis();
		m_player.Update(GameTime);
		m_background.Update(GameTime);

		for (int i = m_pmslist.size() - 1; i >= 0; i--) {
			Missile pms = m_pmslist.get(i);
			pms.Update();
			if (pms.state == Missile.STATE_OUT) {
				m_pmslist.remove(i);
			}
		}
		for (int i = m_enemmslist.size() - 1; i >= 0; i--) {
			Missile enemms = m_enemmslist.get(i);
			enemms.Update();
			if (enemms.state == Missile.STATE_OUT) {
				m_enemmslist.remove(i);
			}
		}
		for (int i = m_enemlist.size() - 1; i >= 0; i--) {
			Enemy enem = m_enemlist.get(i);
			enem.Update(GameTime);
			if (enem.state == Enemy.STATE_OUT)
				m_enemlist.remove(i);
		}
		for (int i = m_explist.size() - 1; i >= 0; i--) {
			Effect_Explosion exp = m_explist.get(i);
			exp.Update(GameTime);
			if (exp.getAnimationEnd())
				m_explist.remove(i);
		}
		for (int i = m_itemlist.size() - 1; i >= 0; i--) {
			Item item = m_itemlist.get(i);
			item.Update(GameTime);
			if (item.bOut == true)
				m_itemlist.remove(i);
		}
		MakeEnemy();
		CheckCollision();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		int x = m_player.GetX();
		int y = m_player.GetY();

		if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) 
			m_player.SetPosition(x - 3, y);
		if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)
			m_player.SetPosition(x + 3, y);
		if (keyCode == KeyEvent.KEYCODE_DPAD_UP)
			m_player.SetPosition(x, y - 3);
		if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN)
			m_player.SetPosition(x, y + 3);
		if (keyCode == KeyEvent.KEYCODE_SPACE)
			m_pmslist.add(new Missile_Player(x + 10, y));

		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();

		int _x, _y;
		_x = (int) event.getX();
		_y = (int) event.getY();

		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN: {
			if (Collision.CollisionCheckPointToBox(_x, _y, m_player.GetX(),
					m_player.GetY(), m_player.GetX() + 104, m_player.GetY() + 64)) {
				playState = 1;
			}
			break;
		}
		case MotionEvent.ACTION_MOVE: {
			if (playState == 1) {
				m_player.SetPosition(_x-20, _y-20);
			}
			break;
		}
		case MotionEvent.ACTION_UP: {
			playState = 0;
			break;
		}
		}
		return true;

	}
}
