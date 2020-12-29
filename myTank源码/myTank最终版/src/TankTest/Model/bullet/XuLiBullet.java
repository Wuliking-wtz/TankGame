package TankTest.Model.bullet;

import TankTest.Enum.Dir;
import TankTest.Enum.Group;
import TankTest.util.ResourceMgr;

import java.awt.*;

/**
 * @author huang
 * @Description
 * @create 2020-06-19-23:37
 */
public class XuLiBullet extends Bullet{

    public XuLiBullet(int x, int y, Dir dir, Group Group) {
        super(x,y,dir,Group);
        super.setHurt(5);

    }
    public void paint(Graphics g) {
        switch (getDir()){
            case L:
                g.drawImage(ResourceMgr.XuLiBulletL,super.getX(),super.getY(),null);
                break;
            case R:
                g.drawImage(ResourceMgr.XuLiBulletR,super.getX(),super.getY(),null);
                break;
            case U:
                g.drawImage(ResourceMgr.XuLiBulletU,super.getX(),super.getY(),null);
                break;
            case D:
                g.drawImage(ResourceMgr.XuLiBulletD,super.getX(),super.getY(),null);
                break;
            case LU:
                g.drawImage(ResourceMgr.XuLiBulletLU,super.getX(),super.getY(),null);
                break;
            case LD:
                g.drawImage(ResourceMgr.XuLiBulletLD,super.getX(),super.getY(),null);
                break;
            case RU:
                g.drawImage(ResourceMgr.XuLiBulletRU,super.getX(),super.getY(),null);
                break;
            case RD:
                g.drawImage(ResourceMgr.XuLiBulletRD,super.getX(),super.getY(),null);
                break;
        }

        move();
        super.getRect().x = super.getX();
        super.getRect().y = super.getY();
    }

    public void move() {
        switch (getDir()){
            case L:
                setX(getX()-SPEED);
                break;
            case R:
                setX(getX()+SPEED);
                break;
            case U:
                setY(getY()-SPEED);
                break;
            case D:
                setY(getY()+SPEED);
                break;
            case LU:
                setX((int) (getX()-Math.sqrt(2)*SPEED/2));
                setY((int) (getY()-Math.sqrt(2)*SPEED/2));
                break;
            case LD:
                setX((int) (getX()-Math.sqrt(2)*SPEED/2));
                setY((int) (getY()+Math.sqrt(2)*SPEED/2));
                break;
            case RU:
                setX((int) (getX()+Math.sqrt(2)*SPEED/2));
                setY((int) (getY()-Math.sqrt(2)*SPEED/2));
                break;
            case RD:
                setX((int) (getX()+Math.sqrt(2)*SPEED/2));
                setY((int) (getY()+Math.sqrt(2)*SPEED/2));
                break;
        }

        super.boundsCheck();
    }

}
