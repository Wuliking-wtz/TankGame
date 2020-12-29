package TankTest.Model.tank;

import TankTest.Enum.Dir;
import TankTest.Enum.Group;
import TankTest.util.ResourceMgr;

import java.awt.*;

/**
 * @author huang
 * @Description
 * @create 2020-06-08-15:45
 */
public class StrengthTank extends Tank {

    public StrengthTank(int x, int y, Dir dir, Group group) {
        super(x, y, dir, group);
        super.setSPEED(3);
        super.setHealth(5);
        super.setMoving(true);
    }


    @Override
    public void paint(Graphics g) {
        if (!this.isLive()) return;
        Dir dir = super.getDir();
        g.drawImage(ResourceMgr.bloods5[getHealth() - 1], super.getX(), super.getY() - 5, null);
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.strengthTankL, super.getX(), super.getY(), null);
                break;
            case R:
                g.drawImage(ResourceMgr.strengthTankR, super.getX(), super.getY(), null);
                break;
            case U:
                g.drawImage(ResourceMgr.strengthTankU, super.getX(), super.getY(), null);
                break;
            case D:
                g.drawImage(ResourceMgr.strengthTankD, super.getX(), super.getY(), null);
                break;
            case LU:
                g.drawImage(ResourceMgr.strengthTankLU, super.getX(), super.getY(), null);
                break;
            case LD:
                g.drawImage(ResourceMgr.strengthTankLD, super.getX(), super.getY(), null);
                break;
            case RU:
                g.drawImage(ResourceMgr.strengthTankRU, super.getX(), super.getY(), null);
                break;
            case RD:
                g.drawImage(ResourceMgr.strengthTankRD, super.getX(), super.getY(), null);
                break;
        }

        super.move();
        super.getRect().x = super.getX()+10;
        super.getRect().y = super.getY()+10;
    }
}
