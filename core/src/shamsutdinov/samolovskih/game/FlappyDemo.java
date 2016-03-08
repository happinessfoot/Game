package shamsutdinov.samolovskih.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import shamsutdinov.samolovskih.game.states.GameStateManager;
import shamsutdinov.samolovskih.game.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {
	//Размеры экрана
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Flappy Demo";

	private GameStateManager gameStateManager;
	private SpriteBatch batch;
	private Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3")); // выбираем музыку которая у нас будет играть фоном
		music.setLooping(true); //Повтор
		music.setVolume(0.3f); //Громкость 30%
		music.play(); // начать возпроизведение
		Gdx.gl.glClearColor(1, 0, 0, 1);//очищает экран
		gameStateManager.push(new MenuState(gameStateManager));//создает новый экран меню и помещает его в вершину стека
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());//Возвращает время, которое прошло между последним и текущем кадром в секундах
		gameStateManager.render(batch);

	}

	public  void dispose(){
		super.dispose();
		music.dispose();
	}
}
