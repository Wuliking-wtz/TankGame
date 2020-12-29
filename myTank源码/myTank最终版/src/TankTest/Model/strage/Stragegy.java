package TankTest.Model.strage;

import TankTest.Model.AbstractGameObject;
import TankTest.util.ResourceMgr;

import java.awt.*;

/**
 * @author huang
 * @Description
 * @create 2020-06-20-13:04
 */
//道具父类
public class Stragegy extends AbstractGameObject {
    private int x, y;
    private boolean live = true;
    private Rectangle rect = null;
    private int w = ResourceMgr.Landmine.getWidth();
    private int h = ResourceMgr.Landmine.getHeight();

    public Stragegy(int x, int y) {
        this.x = x;
        this.y = y;
        rect = new Rectangle(x, y, w, h);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    @Override
    public Rectangle getRect() {
        return rect;
    }

    @Override
    public void die(int hurt) {
        this.setLive(false);
    }

}

