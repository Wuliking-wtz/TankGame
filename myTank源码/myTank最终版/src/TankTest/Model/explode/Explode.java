package TankTest.Model.explode;

import TankTest.util.Audio;
import TankTest.Model.AbstractGameObject;
import TankTest.util.ResourceMgr;
import sun.audio.AudioStream;

import java.awt.*;
import java.io.FileInputStream;

/**
 * @author huang
 * @Description
 * @create 2020-06-08-15:45
 */
public class Explode extends AbstractGameObject {
    private int x, y;
    private boolean live = true;
    private int w = 30, h = 30;
    private int step = 0;
    private Rectangle rect;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        rect = new Rectangle(x, y, w, h);
        this.w = ResourceMgr.bombs[0].getWidth();
        this.h = ResourceMgr.bombs[0].getHeight();
        new Thread(() -> new Audio("Audio/explode.wav").play()).start();
    }

    public boolean isLive() {
        return live;
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
        g.drawImage(ResourceMgr.bombs[step], x, y, null);
        step++;
        if (step >= ResourceMgr.bombs.length) {
            this.die(1);
        }

    }

}
