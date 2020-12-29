package TankTest.chainofresponsibility;

import TankTest.Model.AbstractGameObject;
import TankTest.Model.bullet.Bullet;
import TankTest.Model.bullet.ThroughBullet;
import TankTest.Model.bullet.XuLiBullet;
import TankTest.Model.explode.BigExplode;
import TankTest.Model.wall.*;
import TankTest.jFrame.StartGame.StartGameJPanel;

public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go2 instanceof Bullet && !(go1 instanceof Bullet)) //把顺序转置为go1为bullet
            return collide(go2, go1);
        if (go1 instanceof XuLiBullet) {  //蓄力子弹不考虑
            return true;
        }
        if (go1 instanceof Bullet && go2 instanceof Base) {//子弹和水晶
            Bullet bullet = (Bullet) go1;
            Base ago = (Base) go2;
            if (!ago.isLive() || !bullet.isLive()) return false;
            if (bullet.getRect().intersects(ago.getRect())) {
                bullet.die(1);
                ago.die(bullet.getHurt());
                return false;
            }
        } else if (go1 instanceof Bullet && (go2 instanceof IronWall || go2 instanceof River)) {//子弹和铁墙或河流
            Bullet bullet = (Bullet) go1;
            AbstractGameObject ago = go2;
            if (bullet.isLive()) {
                if (bullet.getRect().intersects(ago.getRect())) {
                    bullet.die(1);
                    return false;
                }
            }
        } else if (go1 instanceof Bullet && go2 instanceof Grass) {//子弹和草
            return true;
        } else if (go1 instanceof Bullet && go2 instanceof BrickWall) {//子弹和粉墙
            Bullet bullet = (Bullet) go1;
            BrickWall ago = (BrickWall) go2;
            if (!ago.isLive() || !bullet.isLive()) return false;
            if (bullet.getRect().intersects(ago.getRect())) {
                ago.die(1);
                if (!(bullet instanceof ThroughBullet))
                    bullet.die(1);
                return false;
            }
        }
        return true;
    }
}
