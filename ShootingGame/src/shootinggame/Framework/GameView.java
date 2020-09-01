package shootinggame.Framework; 

import org.Framework.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import shootinggame.game.BackGround;
import shootinggame.game.GameState;
import shootinggame.game.Player;


public class GameView extends SurfaceView implements SurfaceHolder.Callback{

	private GameViewThread _thread;
	
	private IState m_state;
	Vibrator m_vibrator;
	MediaPlayer m_Sound_BackGround;
	SoundPool m_Sound_Pool;
	
    public GameView(Context context) {
        super(context);
        m_Sound_Pool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        m_Sound_BackGround = MediaPlayer.create(context, R.raw.christ);
        m_Sound_BackGround.start();
    	setFocusable(true);
    	m_vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
 
    	AppManager.getInstance().setGameView(this);
    	AppManager.getInstance().setResources(getResources());
    	AppManager.getInstance().setSize(getWidth(), getHeight());
  
    	ChangeGameState(GameState.getInstance());
    	
        getHolder().addCallback(this);
        _thread = new GameViewThread(getHolder(),this);
        getHolder().setFixedSize(getWidth(),getHeight());
    }
    @Override
      public void onDraw(Canvas canvas) {
    	canvas.drawColor(Color.BLACK);
    	m_state.Render(canvas);    	
    }
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	}
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		_thread.setRunning(true);
	    _thread.start(); 		
	}
	
	public void Update() {
		m_state.Update();
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
	    _thread.setRunning(false);
	    while (retry) {
	        try {
	            _thread.join();
	            retry = false;
	        } catch (InterruptedException e) {
	        }
	    }
	}
	public void vibrator(){
		m_vibrator.vibrate(200);
	}
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		m_state.onKeyDown(keyCode, event);
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		m_state.onTouchEvent(event);
		return true;

	}
	
	public void ChangeGameState(IState _state){
		if(m_state!=null)
			m_state.Destroy();
		_state.Init();
		m_state = _state;
	}
	

}
