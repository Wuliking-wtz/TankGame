package TankTest.Model.wall;

import TankTest.Model.AbstractGameObject;
import TankTest.util.ResourceMgr;

import java.awt.*;

/**
 * 可打碎的墙
 */
public class BrickWall extends AbstractGameObject {

    int x, y, w, h;
    private boolean isLive = true;
    private Rectangle rect = null;

    public BrickWall() {
    }

    public BrickWall(int x, int y) {
        this.x = x;
        this.y = y;
        this.w = ResourceMgr.brickWall.getWidth();
        this.h = ResourceMgr.brickWall.getHeight();
        rect = new Rectangle(x, y, w, h);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void paint(Graphics g) {
        if (!this.isLive())
            return;
        g.drawImage(ResourceMgr.brickWall, x, y, null);
    }

    @Override
    public boolean isLive() {
        return isLive;
    }


    public Rectangle getRect() {
        return rect;
    }

    @Override
    public void die(int hurt) {
        isLive = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BrickWall) {// 如果传入的对象是墙块或其子类对象
            BrickWall w = (BrickWall) obj;// 强制转为墙块对象
            if (w.x == x && w.y == y) {// 如果两个墙块坐标相同
                return true;// 两个墙块是相同的
            }
        }
        return false;// 不在同一坐标，返回false
    }

    /**
     * 返回墙体图片
     *
     * @return
     */
    public Image getImage() {
        return ResourceMgr.brickWall;
    }
}
