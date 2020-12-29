package TankTest.Model.strage;

import TankTest.util.ResourceMgr;

import java.awt.*;

/**
 * @author huang
 * @Description
 * @create 2020-06-20-14:46
 */
public class MoveSpeedStrage extends Stragegy {
    public MoveSpeedStrage(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.MoveSpeedStrage, super.getX(), super.getY(), null);

    }
}
