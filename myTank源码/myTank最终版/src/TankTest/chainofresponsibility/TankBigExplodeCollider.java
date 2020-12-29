package TankTest.chainofresponsibility;

import TankTest.Model.AbstractGameObject;
import TankTest.Model.explode.BigExplode;
import TankTest.Model.tank.PlayerTank;
import TankTest.Model.tank.Tank;

public class TankBigExplodeCollider implements Collider {

    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Tank && go2 instanceof BigExplode) {
            if (go1 instanceof PlayerTank)
                return true;
            BigExplode bigExplode = (BigExplode) go2;
            Tank tank = (Tank) go1;
            if (!bigExplode.isLive() || !tank.isLive()) return false;
            if (bigExplode.getRect().intersects(tank.getRect())) {
                tank.die(bigExplode.getHurt());
                return false;
            }
        } else if (go1 instanceof BigExplode && go2 instanceof Tank) {
            return collide(go2, go1);
        }
        return true;
    }
}
