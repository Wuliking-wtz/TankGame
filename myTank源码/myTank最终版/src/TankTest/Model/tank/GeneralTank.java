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
public class GeneralTank extends Tank {

    public GeneralTank(int x, int y, Dir dir, Group group) {
        super(x, y, dir, group);
        super.setSPEED(5);
        super.setHealth(2);
        super.setMoving(true);
    }


    @Override
    public void paint(Graphics g) {
        if (!this.isLive()) return;
        Dir dir = super.getDir();
        g.drawImage(ResourceMgr.bloods2[getHealth() - 1], super.getX(), super.getY() - 5, null);
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.generalTankL, super.getX(), super.getY(), null);
                break;
            case R:
                g.drawImage(ResourceMgr.generalTankR, super.getX(), super.getY(), null);
                break;
            case U:
                g.drawImage(ResourceMgr.generalTankU, super.getX(), super.getY(), null);
                break;
            case D:
                g.drawImage(ResourceMgr.generalTankD, super.getX(), super.getY(), null);
                break;
            case LU:
                g.drawImage(ResourceMgr.generalTankLU, super.getX(), super.getY(), null);
                break;
            case LD:
                g.drawImage(ResourceMgr.generalTankLD, super.getX(), super.getY(), null);
                break;
            case RU:
                g.drawImage(ResourceMgr.generalTankRU, super.getX(), super.getY(), null);
                break;
            case RD:
                g.drawImage(ResourceMgr.generalTankRD, super.getX(), super.getY(), null);
                break;
        }

        super.move();
        super.getRect().x = super.getX()+10;
        super.getRect().y = super.getY()+10;
    }
}
