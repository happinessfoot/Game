package shamsutdinov.samolovskih.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by rusli_000 on 06.03.2016.
 */
public class Bird {
    private static final int MOVEMENT = 100;
    public static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;

    private Texture bird;

    //Конструктор, который будет задавать направление птички и тексутру
    public Bird (int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture("bird.png");
    }

    public Texture getBird() {
        return bird;
    }

    public Vector3 getPosition() {
        return position;
    }

    //Обновляем кадры
    public void  update(float deltaTime){
        if(position.y > 0){
            velocity.add(0, GRAVITY, 0); // добоваляем GRAVITY к координате Y
        }
        velocity.scl(deltaTime);//а затем умножаем на время
        position.add(MOVEMENT * deltaTime, velocity.y, 0);
        //Чтобы птичка не могла улететь за экран вниз :)
        if(position.y < 0){
            position.y = 0;
        }

        velocity.scl(1 / deltaTime); //Изменение скорости с течением времени
    }

    public void jump(){
        velocity.y = 250;
    }
}
