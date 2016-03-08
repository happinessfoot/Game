package shamsutdinov.samolovskih.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
    private static final int GROUND_Y_OFFSET = -30; // уменьшили высоту земли:D

    private Bird bird;
    private Texture background;
    private Texture ground;
    private Vector2 groundPosition1, groundPosition2;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2); //Задаем область обзора для камеры
        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPosition1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPosition2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Tube>();

        for (int i = 0; i < TUBE_COUNT; i++){
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
        updateGround();
        bird.update(deltaTime);
        camera.position.x = bird.getPosition().x + 80;

        //Обновляем трубы, тобишь создаем новые
        for (int i = 0; i < tubes.size; i++){

            Tube tube = tubes.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPositionTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPositionTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            //проверка столкновения (если оно произошло игра просто перезапусится)
            if (tube.collides(bird.getBounds()))
                gameStateManager.set(new GameOver(gameStateManager));
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

        spriteBatch.draw(ground, groundPosition1.x, groundPosition1.y);
        spriteBatch.draw(ground, groundPosition2.x, groundPosition2.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        ground.dispose();

        for (Tube tube : tubes) {
            tube.dispose();
        }
    }
    // создаем эффект не прерывности текстуры земли
    private void updateGround(){
        if (camera.position.x - (camera.viewportWidth / 2) > groundPosition1.x + ground.getWidth())
            groundPosition1.add(ground.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > groundPosition2.x + ground.getWidth())
            groundPosition2.add(ground.getWidth() * 2, 0);
    }
}
