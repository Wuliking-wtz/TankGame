package TankTest.chainofresponsibility;

import TankTest.Model.AbstractGameObject;
import TankTest.Model.bullet.Landmine;
import TankTest.Model.tank.Tank;

/**
 * @author huang
 * @Description
 * @create 2020-06-19-23:05
 */
public class LandmineTankCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Landmine && go2 instanceof Tank) {
            Landmine L = (Landmine) go1;
            Tank t = (Tank) go2;
            if (!t.isLive() || !L.isLive()) return false;
            if (t.getGroup() == L.getGroup()) return true;
            if (L.getRect().intersects(t.getRect())) {
                L.die(1);
                t.die(2);
                return false;
            }
        } else if (go1 instanceof Tank && go2 instanceof Landmine) {
            return collide(go2, go1);
        }
        return true;
    }
}
