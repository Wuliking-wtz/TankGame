package TankTest.Model.explode;

import TankTest.Model.AbstractGameObject;
import TankTest.util.ResourceMgr;
import TankTest.util.Audio;

import java.awt.*;


/**
 * @author huang
 * @Description
 * @create 2020-06-20-12:22
 */
public class BigExplode extends AbstractGameObject {
    public static int w = 70, h = 70;
    private int x, y;
    private boolean live = true;
    private int step = 0;
    private Rectangle rect;
    private int hurt = 5;

    public BigExplode(int x, int y) {
        this.x = x;
        this.y = y;
        this.w = ResourceMgr.bigBombs[0].getWidth();
        this.h = ResourceMgr.bigBombs[0].getHeight();
        rect = new Rectangle(x, y, w, h);
        new Thread(() -> new Audio("Audio/explode.wav").play()).start();
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean over) {
        this.live = live;
    }

    @Override
    public Rectangle getRect() {
        return rect;
    }

    @Override
    public void die(int hurt) {
        this.live = false;
    }

    public void paint(Graphics g) {
        if (!live) return;
        g.drawImage(ResourceMgr.bigBombs[step], x, y, null);
        step++;
        if (step >= ResourceMgr.bigBombs.length) {
            this.die(1);
        }
    }

    public int getHurt() {
        return hurt;
    }
}

