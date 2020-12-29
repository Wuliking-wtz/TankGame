package TankTest.Model.strage;

import TankTest.util.ResourceMgr;

import java.awt.*;

/**
 * @author huang
 * @Description
 * @create 2020-06-20-13:03
 */
public class BloodStrage extends Stragegy {

    public BloodStrage(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.BloodStrage, super.getX(), super.getY(), null);
    }
}
