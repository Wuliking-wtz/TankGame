package TankTest.Model.bullet;

import TankTest.Enum.Dir;
import TankTest.Enum.Group;
import TankTest.util.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author huang
 * @Description
 * @create 2020-06-19-23:37
 */
public class ScatterBullet extends Bullet {
    //以Dir为参考系，side表示属于哪一边，-1为左，1为右
    private int side;

    public ScatterBullet(int x, int y, Dir dir, Group Group, int side) {
        super(x, y, dir, Group);
        super.setHurt(2);
        this.side = side;
    }

    public void paint(Graphics g) {
        switch (getDir()) {
            case L:
                g.drawImage(ResourceMgr.scatterBullet[18 + side], super.getX(), super.getY(), null);
                break;
            case R:
                g.drawImage(ResourceMgr.scatterBullet[6 + side], super.getX(), super.getY(), null);
                break;
            case U:
                g.drawImage(ResourceMgr.scatterBullet[(0 + side + 24) % 24], super.getX(), super.getY(), null);
                break;
            case D:
                g.drawImage(ResourceMgr.scatterBullet[12 + side], super.getX(), super.getY(), null);
                break;
            case LU:
                g.drawImage(ResourceMgr.scatterBullet[21 + side], super.getX(), super.getY(), null);
                break;
            case LD:
                g.drawImage(ResourceMgr.scatterBullet[15 + side], super.getX(), super.getY(), null);
                break;
            case RU:
                g.drawImage(ResourceMgr.scatterBullet[3 + side], super.getX(), super.getY(), null);
                break;
            case RD:
                g.drawImage(ResourceMgr.scatterBullet[9 + side], super.getX(), super.getY(), null);
                break;
        }
        move();
        super.getRect().x = super.getX();
        super.getRect().y = super.getY();
    }

    public void move() {
        switch (getDir()) {
            case L:
                setX((int) (getX() - SPEED * Math.cos(Math.PI / 180 * 15)));
                if (side < 0)
                    setY((int) Math.ceil(getY() - side * SPEED * Math.sin(Math.PI / 180 * 15)));
                if (side > 0)
                    setY((int) Math.floor(getY() - side * SPEED * Math.sin(Math.PI / 180 * 15)));
                break;
            case R:
                setX((int) (getX() + SPEED * Math.cos(Math.PI / 180 * 15)));
                if (side < 0)
                    setY((int) Math.ceil(getY() + side * SPEED * Math.sin(Math.PI / 180 * 15)));
                if (side > 0)
                    setY((int) Math.floor(getY() + side * SPEED * Math.sin(Math.PI / 180 * 15)));
                break;
            case U:
                if (side < 0)
                    setX((int) Math.ceil(getX() + side * SPEED * Math.sin(Math.PI / 180 * 15)));
                if (side > 0)
                    setX((int) Math.floor(getX() + side * SPEED * Math.sin(Math.PI / 180 * 15)));
                setY((int) (getY() - SPEED * Math.cos(Math.PI / 180 * 15)));
                break;
            case D:
                if (side < 0)
                    setX((int) Math.ceil(getX() - side * SPEED * Math.sin(Math.PI / 180 * 15)));
                if (side > 0)
                    setX((int) Math.floor(getX() - side * SPEED * Math.sin(Math.PI / 180 * 15)));
                setY((int) (getY() + SPEED * Math.cos(Math.PI / 180 * 15)));
                break;
            case LU:
                if (side < 0) {
                    setX((int) Math.ceil(getX() - SPEED * Math.cos(Math.PI / 180 * (45 + 15 * side))));
                    setY((int) Math.floor(getY() - SPEED * Math.sin(Math.PI / 180 * (45 + 15 * side))));
                } else if(side > 0)  {
                    setX((int) Math.floor(getX() - SPEED * Math.cos(Math.PI / 180 * (45 + 15 * side))));
                    setY((int) Math.ceil(getY() - SPEED * Math.sin(Math.PI / 180 * (45 + 15 * side))));
                } else {
                    setX((int) Math.ceil(getX() - SPEED * Math.cos(Math.PI / 180 * (45 + 15 * side))));
                    setY((int) Math.ceil(getY() - SPEED * Math.sin(Math.PI / 180 * (45 + 15 * side))));
                }
                break;
            case LD:
                setX((int) Math.floor(getX() - SPEED * Math.sin(Math.PI / 180 * (45 + 15 * side))));
                setY((int) Math.floor(getY() + SPEED * Math.cos(Math.PI / 180 * (45 + 15 * side))));
                break;
            case RU:
                setX((int) Math.floor(getX() + SPEED * Math.sin(Math.PI / 180 * (45 + 15 * side))));
                setY((int) Math.floor(getY() - SPEED * Math.cos(Math.PI / 180 * (45 + 15 * side))));
                break;
            case RD:
                if (side < 0) {
                    setX((int) Math.floor(getX() + SPEED * Math.cos(Math.PI / 180 * (45 + 15 * side))));
                    setY((int) Math.ceil(getY() + SPEED * Math.sin(Math.PI / 180 * (45 + 15 * side))));
                } else if(side > 0)  {
                    setX((int) Math.ceil(getX() + SPEED * Math.cos(Math.PI / 180 * (45 + 15 * side))));
                    setY((int) Math.floor(getY() + SPEED * Math.sin(Math.PI / 180 * (45 + 15 * side))));
                } else {
                    setX((int) Math.ceil(getX() + SPEED * Math.cos(Math.PI / 180 * (45 + 15 * side))));
                    setY((int) Math.ceil(getY() + SPEED * Math.sin(Math.PI / 180 * (45 + 15 * side))));
                }break;
        }
        super.boundsCheck();
    }
}
