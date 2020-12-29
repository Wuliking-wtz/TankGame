package TankTest.Model.strage;

import TankTest.util.ResourceMgr;

import java.awt.*;

/**
 * @author huang
 * @Description
 * @create 2020-06-20-13:03
 */
public class BulletStrage extends Stragegy {

    public BulletStrage(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.BulletStrage, super.getX(), super.getY(), null);

    }
}
