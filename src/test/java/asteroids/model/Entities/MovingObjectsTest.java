package asteroids.model.Entities;

import asteroids.Constants;
import asteroids.model.Position;
import asteroids.model.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MovingObjectsTest extends Assertions {

    MovingObject object;
    Position position;
    Vector2d velocity;

    @BeforeEach
    void create(){
        object = Mockito.mock(MovingObject.class, Mockito.CALLS_REAL_METHODS);
        position = Mockito.mock(Position.class);
        velocity = Mockito.mock(Vector2d.class);
        object.setWidth(10);
        object.setHeight(20);
        object.setPosition(position);
        object.setVelocity(velocity);

    }

    @Test
    void alive() {
        object.dies();
        assertFalse(object.isAlive());
    }

    @Test
    void goForward(){

        Mockito.when(velocity.getX()).thenReturn(-10.0);
        Mockito.when(velocity.getY()).thenReturn(5.0);

        Mockito.when(position.getX()).thenReturn(10.0);
        Mockito.when(position.getY()).thenReturn(20.0);


        Mockito.doNothing().when(position).setX(Mockito.anyDouble());

        Mockito.doNothing().when(position).setY(Mockito.anyDouble());


        object.goFoward(1000);
        Mockito.verify(position, Mockito.times(1)).setX(position.getX() + velocity.getX());
        Mockito.verify(position, Mockito.times(1)).setY(position.getY() + velocity.getY());
    }

    @Test
    void update(){
        Mockito.verify(object, Mockito.never()).goFoward(Mockito.anyLong());

        object.update(1000);

        Mockito.verify(object, Mockito.times(1)).goFoward(1000);
    }

    @Test
    void NotPassBorderRight(){
        Position pos = new Position(Constants.WIDTH, 0);
        object.setPosition(pos);

        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), Constants.WIDTH);
        assertEquals(object.getPosition().getY(), 0);

    }

    @Test
    void PassBorderRight(){
        Position pos = new Position(Constants.WIDTH+1, 0);

        object.setPosition(pos);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), -object.getWidth());
        assertEquals(object.getPosition().getY(), 0);
    }

    @Test
    void NotPassBorderLeft(){
        Position pos = new Position(-object.getWidth(), 0);

        object.setPosition(pos);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), -object.getWidth());
        assertEquals(object.getPosition().getY(), 0);

    }

    @Test
    void PassBorderLeft(){
        Position pos = new Position(-object.getWidth()-1, 0);

        object.setPosition(pos);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), Constants.WIDTH);
        assertEquals(object.getPosition().getY(), 0);
    }

    @Test
    void NotPassBorderUp(){
        Position pos = new Position(0, -object.getHeight());

        object.setPosition(pos);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), 0);
        assertEquals(object.getPosition().getY(), -object.getHeight());
    }


    @Test
    void PassBorderUp(){
        Position pos = new Position(0, -object.getHeight()-1);

        object.setPosition(pos);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), 0);
        assertEquals(object.getPosition().getY(), Constants.HEIGHT);
    }

    @Test
    void NotPassBorderDown(){
        Position down = new Position(0, Constants.HEIGHT);

        object.setPosition(down);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), 0);
        assertEquals(object.getPosition().getY(), Constants.HEIGHT);
    }

    @Test
    void PassBorderDown(){
        Position down = new Position(0, Constants.HEIGHT+1);

        object.setPosition(down);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), 0);
        assertEquals(object.getPosition().getY(), -object.getHeight());

    }


    @Test
    void NotPassCornerUpLeft(){
        Position pos = new Position(-object.getWidth(), -object.getHeight());

        object.setPosition(pos);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), -object.getWidth());
        assertEquals(object.getPosition().getY(), -object.getHeight());
    }

    @Test
    void PassCornerUpLeft(){
        Position pos = new Position(-object.getWidth()-1, -object.getHeight()-1);

        object.setPosition(pos);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), Constants.WIDTH);
        assertEquals(object.getPosition().getY(), Constants.HEIGHT);

    }

    @Test
    void NotPassCornerUpRigth(){
        Position pos = new Position(Constants.WIDTH, -object.getHeight());

        object.setPosition(pos);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), Constants.WIDTH);
        assertEquals(object.getPosition().getY(), -object.getHeight());
    }

    @Test
    void PassCornerUpRigth(){
        Position pos = new Position(Constants.WIDTH+1, -object.getHeight()-1);

        object.setPosition(pos);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), -object.getWidth());
        assertEquals(object.getPosition().getY(), Constants.HEIGHT);

    }

    @Test
    void NotPassCornerDownLeft(){
        Position pos = new Position(-object.getWidth(), Constants.HEIGHT);

        object.setPosition(pos);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), -object.getWidth());
        assertEquals(object.getPosition().getY(), Constants.HEIGHT);
    }

    @Test
    void PassCornerDownLeft(){
        Position pos = new Position(-object.getWidth()-1, Constants.HEIGHT+1);

        object.setPosition(pos);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), Constants.WIDTH);
        assertEquals(object.getPosition().getY(), -object.getHeight());
    }

    @Test
    void NotPassCornerDownRigth(){
        Position pos = new Position(Constants.WIDTH, Constants.HEIGHT);

        object.setPosition(pos);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), Constants.WIDTH);
        assertEquals(object.getPosition().getY(), Constants.HEIGHT);
    }

    @Test
    void PassCornerDownRigth(){
        Position pos = new Position(Constants.WIDTH+1, Constants.HEIGHT+1);

        object.setPosition(pos);
        object.fixPassScreenBorder();

        assertEquals(object.getPosition().getX(), -object.getWidth());
        assertEquals(object.getPosition().getY(), -object.getHeight());
    }
}
