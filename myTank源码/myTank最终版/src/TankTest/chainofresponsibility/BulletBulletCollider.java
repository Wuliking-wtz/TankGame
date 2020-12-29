package TankTest.chainofresponsibility;

import TankTest.Model.AbstractGameObject;
import TankTest.Model.bullet.Bullet;
import TankTest.Model.bullet.XuLiBullet;

public class BulletBulletCollider implements Collider {

    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Bullet && go2 instanceof Bullet) {
            if (go1 instanceof XuLiBullet || go2 instanceof XuLiBullet)
                return true;
            Bullet bullet1 = (Bullet) go1;
            Bullet bullet2 = (Bullet) go2;
            if (!bullet2.isLive() || !bullet1.isLive()) return false;
            if (bullet2.getGroup() == bullet1.getGroup()) return true;
            if (bullet1.getRect().intersects(bullet2.getRect())) {
                bullet1.die(1);
                bullet2.die(1);
                return false;
            }
        }
        return true;
    }
}
