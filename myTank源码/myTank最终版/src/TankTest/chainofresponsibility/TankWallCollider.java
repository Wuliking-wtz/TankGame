package TankTest.chainofresponsibility;

import TankTest.Model.AbstractGameObject;
import TankTest.Model.tank.Tank;
import TankTest.Model.wall.BrickWall;
import TankTest.Model.wall.Grass;

/**
 * @author huang
 * @Description
 * @create 2020-06-12-23:01
 */
public class TankWallCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Tank && go2 instanceof BrickWall) {
            Tank t = (Tank) go1;
            BrickWall w = (BrickWall) go2;
            if (t.isLive() && !(w instanceof Grass)) {
                if (t.getRect().intersects(w.getRect())) {
                    t.back();
                }
            }
        } else if (go1 instanceof BrickWall && go2 instanceof Tank) {
            collide(go2, go1);
        }
        return true;
    }
}
