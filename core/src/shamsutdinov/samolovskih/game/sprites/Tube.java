package shamsutdinov.samolovskih.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by rusli_000 on 06.03.2016.
 */
public class Tube {

    public static final int TUBE_WIDTH = 52;

    public static final int FLUCTUATION = 130;//Диапозон в котором будут создаваться трубы по высоте
    public static final int TUBE_GAP = 80; //Высота просвета, ну в которую может птичка пролететь :D
    public static final int LOWEST_OPENING = 120; //Нижняя граница просвета

    private Texture topTube;
    private Texture bottomTube;
    private Vector2 positionTopTube;
    private Vector2 positionBottomTube;
    private Vector2 postionGap;
    private Vector2 gap;
    private Random random;
    private Rectangle boundsTop, boundsBottom,boundsMiddle;


    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPositionTopTube() {
        return positionTopTube;
    }

    public Vector2 getPositionBottomTube() {
        return positionBottomTube;
    }

    //Здесь мы задаем параметры для труб
    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        random = new Random();
        //Верхняя труба создается по рандому, нижняя труба будет созадваться на фиксированной высте
        positionTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube = new Vector2(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());


        boundsTop = new Rectangle(positionTopTube.x,positionTopTube.y, topTube.getWidth(), topTube.getHeight()); //верхний премоугольник для проверки стокновения
        boundsBottom = new Rectangle(positionBottomTube.x, positionBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight()); //нижний премоугольник для проверки столкновения
        boundsMiddle = new Rectangle(positionBottomTube.x,positionBottomTube.y,bottomTube.getWidth(), bottomTube.getHeight() + TUBE_GAP);
    }
    //Что делает метод, вроде как из названия понятно :D
    public void reposition(float x){
        positionTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube.set(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());
        // начальное расположение премоугольников совподает с начальным расположением труб
        boundsTop.setPosition(positionTopTube.x, positionTopTube.y);
        boundsBottom.setPosition(positionBottomTube.x, positionBottomTube.y);

    }

    public boolean collides(Rectangle player){

        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }

    public boolean collideGap(Rectangle player){
        return  player.overlaps(boundsMiddle);
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
