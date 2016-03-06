package shamsutdinov.samolovskih.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import shamsutdinov.samolovskih.game.FlappyDemo;

/**
 * Created by rusli_000 on 06.03.2016.
 */
public class MenuState extends State {

    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }
    //Обрабатываем нажатия
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gameStateManager.set(new PlayState(gameStateManager));//Ставим игровой экран на вершину стеку, т.е. будет виден пользователю
        }
    }
    //обновляет кадры
    @Override
    public void update(float deltaTime) {
        handleInput();
    }
    //Рисуем экран
    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);//фон
        spriteBatch.draw(playButton, (FlappyDemo.WIDTH / 2) - (playButton.getWidth() / 2), FlappyDemo.HEIGHT / 2);//кнопка PLAY
        spriteBatch.end();
    }

    //Освобождаем ресурсы
    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}