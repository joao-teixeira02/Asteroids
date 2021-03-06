package asteroids.model;

import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest extends Assertions {

    @Test
    void testPosition(){
        Position position = new Position(10, 20);

        assertEquals(10, position.getX());
        assertEquals(20, position.getY());

    }

    @Test
    void testSets(){
        Position position = new Position(10, 20);

        assertEquals(10, position.getX());
        assertEquals(20, position.getY());

        position.setX(20);
        position.setY(40);

        assertEquals(20, position.getX());
        assertEquals(40, position.getY());
    }

    @Test
    void cloneTest(){
        //given
        Position pos = new Position(20.0,23.1);

        //when
        Position cloned = pos.clone();

        //then
        assertEquals(pos.getX(),cloned.getX());
        assertEquals(pos.getY(),cloned.getY());
    }

    @Property
    public void equals(@ForAll int x, @ForAll int y) {
        Position p1 = new Position(x, y);
        Position p2 = new Position(x, y);
        assert( p1.equals(p2));
    }

    @Property
    public void equals(@ForAll int x1, @ForAll int y1, @ForAll int x2, @ForAll int y2) {
        Position p1 = new Position(x1, y1);
        Position p2 = new Position(x2, y2);
        assert( p1.equals(p2) == (x1==x2 && y1==y2));
    }

    @Property
    public void equalsNull(@ForAll int x, @ForAll int y) {
        Position p1 = new Position(x, y);
        assert( !p1.equals(null));
    }

    @Property
    public void equalsThis(@ForAll int x, @ForAll int y) {
        Position p1 = new Position(x, y);
        Position p2 = p1;
        assert( p1.equals(p2));
    }

    @Property
    public void equalsDiferentClasses(@ForAll int x, @ForAll int y) {
        Position p1 = new Position(x, y);
        String p2 = new String();
        assert( !p1.equals(p2));
    }

}
