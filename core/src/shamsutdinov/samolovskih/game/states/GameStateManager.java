package shamsutdinov.samolovskih.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by rusli_000 on 06.03.2016.
 */
public class GameStateManager {
    //Стек состояний ваш к.о.
    private Stack<State> states;

    //Конструктор который создает пустой стек
    public GameStateManager(){
        states = new Stack<State>();
    }

    //Метод, помещает элемент в вершину стека
    public void push(State state){
        states.push(state);
    }

    //Метод извлекает верхний элемент, удаляя его из стека
    public void pop(){
        states.pop().dispose();//освобожждаем ресурсы
    }

    //Метод, который будет удалять из стека верхний экран, очищать его ресурсы и помещать его в вершину стека
    public void set (State state){
        states.pop().dispose();//очищаем
        states.push(state);//помещаем в верх
    }

    //Через определенное время будет обновлять верхний элемент в стеке, то есть экран, который сейчас виден ползователю
    public void update(float deltaTime){
        states.peek().update(deltaTime);//Метод, возвращает верхний элемент не удаляя его из стека
    }

    //Принимает состояние из верхней части стека и отрисовывает его
    public void render(SpriteBatch spriteBatch){
        states.peek().render(spriteBatch);
    }
}
