package asteroids.model.Creator;

import asteroids.model.Entities.MovingObject;
import asteroids.model.Position;

import java.util.List;

public abstract class LaserBeamCreator extends Creator {
    private final List<MovingObject> entities;
    protected final static int laserWidth = 3;
    protected final static int laserHeight = 3;

    public LaserBeamCreator(List<MovingObject> entities){
        this.entities = entities;
    }

    public List<MovingObject> getEntities() {
        return entities;
    }

    public void addLaserBeam(MovingObject object){
        entities.add(object);
    }

    public Position ajustPosition(double angle, MovingObject shooter){
        Position laserPosition = shooter.getPosition().clone();
        double distance = Math.sqrt(shooter.getWidth()*shooter.getWidth() + shooter.getHeight()*shooter.getHeight());
        double addX = Math.cos(angle)*(distance/2 + laserWidth+1);
        double addY = Math.sin(angle)*(distance/2 + laserHeight+1);

        laserPosition.setX(laserPosition.getX() + addX);
        laserPosition.setY(laserPosition.getY() + addY);
        return  laserPosition;
    }


}