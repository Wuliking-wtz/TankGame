package TankTest.Model.wall;

import TankTest.util.ResourceMgr;

import java.awt.*;

public class River extends BrickWall {
    int x, y, w, h;
    private boolean isLive = true;
    private Rectangle rect = null;

    public River(int x, int y) {
        this.x = x;
        this.y = y;
        this.w = ResourceMgr.river.getWidth();
        this.h = ResourceMgr.river.getHeight();
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
        g.drawImage(ResourceMgr.river, x, y, null);
    }

    @Override
    public boolean isLive() {
        return isLive;
    }

    @Override
    public Rectangle getRect() {
        return rect;
    }

    @Override
    public void die(int hurt) {
    }
    /**
     * 返回墙体图片
     * @return
     */
    public Image getImage() {
        return ResourceMgr.river;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof River) {// 如果传入的对象是墙块或其子类对象
            River w = (River) obj;// 强制转为墙块对象
            if (w.x == x && w.y == y) {// 如果两个墙块坐标相同
                return true;// 两个墙块是相同的
            }
        }
        return false;// 不在同一坐标，返回false
    }
}
