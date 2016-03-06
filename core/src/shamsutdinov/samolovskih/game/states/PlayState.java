package shamsutdinov.samolovskih.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import shamsutdinov.samolovskih.game.FlappyDemo;
import shamsutdinov.samolovskih.game.sprites.Bird;
import shamsutdinov.samolovskih.game.sprites.Tube;

/**
 * Created by rusli_000 on 06.03.2016.
 */
public class PlayState extends State {

    public static final int TUBE_SPACING = 125; //Растояние между трубами
    public static final  int TUBE_COUNT = 4;

    private Bird bird;
    private Texture background;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2); //Задаем область обзора для камеры
        background = new Texture("bg.png");

        tubes = new Array<Tube>();

        for (int i = 1; i < TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }
    //Обновляем кадры
    @Override
    public void update(float deltaTime) {
        handleInput();
        bird.update(deltaTime);
        camera.position.x = bird.getPosition().x + 80;

        //Обновляем трубы, тобишь создаем новые
        for (Tube tube : tubes){
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPositionTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPositionTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
        }
        camera.update();
    }

    //Рисуем игровой экран
    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined); // создаем матрицу проекции
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);//Рисуем фон
        spriteBatch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);//Рисуем птичку
        for (Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPositionBottomTube().x, tube.getPositionTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
        }
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
