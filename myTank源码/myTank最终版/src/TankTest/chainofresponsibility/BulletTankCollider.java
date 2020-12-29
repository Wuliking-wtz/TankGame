package TankTest.chainofresponsibility;

import TankTest.Model.AbstractGameObject;
import TankTest.Model.bullet.Bullet;
import TankTest.Model.bullet.XuLiBullet;
import TankTest.Model.tank.Tank;

/**
 * @author huang
 * @Description
 * @create 2020-06-12-22:15
 */
public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Bullet && go2 instanceof Tank) {
            if (go1 instanceof XuLiBullet)
                return true;
            Bullet b = (Bullet) go1;
            Tank t = (Tank) go2;
            if (!t.isLive() || !b.isLive()) return false;
            if (t.getGroup() == b.getGroup()) return true;
            ;
            if (b.getRect().intersects(t.getRect())) {
                t.die(b.getHurt());
                b.die(1);
                return false;
            }
        } else if (go1 instanceof Tank && go2 instanceof Bullet) {
            return collide(go2, go1);
        }
        return true;
    }
}
