package TankTest.Model.wall;

import TankTest.Model.explode.BigExplode;
import TankTest.Model.explode.Explode;
import TankTest.jFrame.StartGame.StartGameJPanel;
import TankTest.util.ResourceMgr;

import java.awt.*;

/**
 * 基地
 */
public class Base extends BrickWall {
    int x, y, w, h;
    int health = 2;
    private boolean isLive = true;
    private Rectangle rect = null;

    public Base(int x, int y) {
        this.x = x;
        this.y = y;
        this.w = ResourceMgr.base.getWidth();
        this.h = ResourceMgr.base.getHeight();
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
        g.drawImage(ResourceMgr.base, x, y, null);
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
        health -= hurt;
        if (health <= 0) {
            StartGameJPanel.INSTANCE.getGm().add(new Explode(x, y));
            this.isLive = false;
        }
    }

    /**
     * 返回墙体图片
     *
     * @return
     */
    public Image getImage() {
        return ResourceMgr.base;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Base) {// 如果传入的对象是墙块或其子类对象
            Base w = (Base) obj;// 强制转为墙块对象
            if (w.x == x && w.y == y) {// 如果两个墙块坐标相同
                return true;// 两个墙块是相同的
            }
        }
        return false;// 不在同一坐标，返回false
    }
}
