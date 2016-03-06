package shamsutdinov.samolovskih.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by rusli_000 on 06.03.2016.
 */
public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gameStateManager;

    public State(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }
    //Опрашивает пользователский ввод
    protected abstract void handleInput ();

    //Обновляет картинку через определенный промежуток времени
    public abstract void update(float deltaTime);

    //Рисует экран
    public abstract void render (SpriteBatch spriteBatch);

    //Удаляет ненжуные экземпляры класса
    public abstract void dispose();

}
