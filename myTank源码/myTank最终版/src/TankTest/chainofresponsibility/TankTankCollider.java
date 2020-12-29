package TankTest.chainofresponsibility;

import TankTest.Model.AbstractGameObject;
import TankTest.Model.tank.Tank;

/**
 * @author huang
 * @Description
 * @create 2020-06-13-16:35
 */
public class TankTankCollider implements Collider {

    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Tank && go2 instanceof Tank) {
            Tank t1 = (Tank) go1;
            Tank t2 = (Tank) go2;
            if (t1 == t2) return true;
            if (t1.isLive() && t2.isLive()) {
                if (t1.getRect().intersects(t2.getRect())) {
                    t1.back();
                    t2.back();
                }
            }
        }
        return true;
    }
}
