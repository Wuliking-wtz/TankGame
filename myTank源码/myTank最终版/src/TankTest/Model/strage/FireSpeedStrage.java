package TankTest.Model.strage;

import TankTest.util.ResourceMgr;

import java.awt.*;

/**
 * @author huang
 * @Description
 * @create 2020-06-20-14:46
 */
public class FireSpeedStrage extends Stragegy {
    public FireSpeedStrage(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.FireSpeedStrage, super.getX(), super.getY(), null);

    }
}
