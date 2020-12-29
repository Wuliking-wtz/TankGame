package TankTest.Model.bullet;

import TankTest.Enum.Group;
import TankTest.Model.AbstractGameObject;
import TankTest.util.ResourceMgr;

import java.awt.*;

/**
 * @author huang
 * @Description
 * @create 2020-06-19-22:55
 */
/*
  地雷类
* */
public class Landmine extends AbstractGameObject {
    private int x,y;
    private Group group ;
    private boolean live = true;
    private int hurt;
    private Rectangle rect = null;
    private int w = ResourceMgr.Landmine.getWidth();
    private int h =  ResourceMgr.Landmine.getHeight();

    public Landmine(int x, int y) {
        this.x = x;
        this.y = y;
        rect = new Rectangle(x,y,w,h);
        this.hurt = 2;
        this.group = Group.GOOD;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.Landmine,x,y,null);
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

    public Group getGroup() {
        return group;
    }
}
