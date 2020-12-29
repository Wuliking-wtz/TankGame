package TankTest.Model.bullet;

import TankTest.Enum.Dir;
import TankTest.Enum.Group;
import TankTest.Model.AbstractGameObject;
import TankTest.util.ResourceMgr;
import TankTest.jFrame.StartGame.StartGameJPanel;

import java.awt.*;

/**
 * @author huang
 * @Description
 * @create 2020-06-09-14:24
 */
public class Bullet extends AbstractGameObject {
    private int x,y;
    private int hurt; //伤害

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getHurt() {
        return hurt;
    }

    public void setHurt(int hurt) {
        this.hurt = hurt;
    }

    private Dir dir;
    private Group group;
    private boolean live = true;
    public int SPEED = 10;
    private Rectangle rect = null;
    private int w = ResourceMgr.bulletU.getWidth();
    private int h = ResourceMgr.bulletU.getHeight();

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Group getGroup() {
        return group;
    }

    public Bullet(int x, int y, Dir dir, Group Group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = Group;
        rect = new Rectangle(x,y,w,h);
    }
    public void paint(Graphics g) {
    }

    public void move() {

    }

    public Rectangle getRect(){
        return rect;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    @Override
    public void die(int hurt) {
        this.setLive(false);
    }

    public void boundsCheck() {
        if(x<0||x> StartGameJPanel.WIDTH||y<0||y> StartGameJPanel.HEIGHT)
            live = false;
    }

}
