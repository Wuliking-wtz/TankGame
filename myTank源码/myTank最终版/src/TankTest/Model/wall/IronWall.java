package TankTest.Model.wall;

import TankTest.util.ResourceMgr;

import java.awt.*;

/**
 * 铁墙，不可被打破
 */
public class IronWall extends BrickWall {
    int x, y, w, h;
    private boolean isLive = true;
    private Rectangle rect = null;

    public IronWall(int x, int y) {
        this.x = x;
        this.y = y;
        this.w = ResourceMgr.ironWall.getWidth();
        this.h = ResourceMgr.ironWall.getHeight();
        rect = new Rectangle(x, y, w, h);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void paint(Graphics g) {
        if (!this.isLive())
            return;
        g.drawImage(ResourceMgr.ironWall, x, y, null);
    }

    @Override
    public boolean isLive() {
        return isLive;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void die() {
    }
    /**
     * 返回墙体图片
     * @return
     */
    public Image getImage() {
        return ResourceMgr.ironWall;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IronWall) {// 如果传入的对象是墙块或其子类对象
            IronWall w = (IronWall) obj;// 强制转为墙块对象
            if (w.x == x && w.y == y) {// 如果两个墙块坐标相同
                return true;// 两个墙块是相同的
            }
        }
        return false;// 不在同一坐标，返回false
    }
}
