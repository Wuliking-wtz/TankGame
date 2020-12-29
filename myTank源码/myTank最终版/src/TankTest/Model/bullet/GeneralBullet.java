package TankTest.Model.bullet;

import TankTest.Enum.Dir;
import TankTest.Enum.Group;
import TankTest.jFrame.StartGame.StartGameJPanel;
import TankTest.util.ResourceMgr;

import java.awt.*;

/**
 * @author huang
 * @Description
 * @create 2020-06-19-23:38
 */
public class GeneralBullet extends Bullet{

    public GeneralBullet(int x, int y, Dir dir, Group Group) {
       super(x,y,dir,Group);
       super.setHurt(2);

    }
    public void paint(Graphics g) {
        switch (getDir()){
            case L:
                g.drawImage(ResourceMgr.bulletL,super.getX(),super.getY(),null);
                break;
            case R:
                g.drawImage(ResourceMgr.bulletR,super.getX(),super.getY(),null);
                break;
            case U:
                g.drawImage(ResourceMgr.bulletU,super.getX(),super.getY(),null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD,super.getX(),super.getY(),null);
                break;
            case LU:
                g.drawImage(ResourceMgr.bulletLU,super.getX(),super.getY(),null);
                break;
            case LD:
                g.drawImage(ResourceMgr.bulletLD,super.getX(),super.getY(),null);
                break;
            case RU:
                g.drawImage(ResourceMgr.bulletRU,super.getX(),super.getY(),null);
                break;
            case RD:
                g.drawImage(ResourceMgr.bulletRD,super.getX(),super.getY(),null);
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
