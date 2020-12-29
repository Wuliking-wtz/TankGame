package TankTest.Model.tank;

import TankTest.Enum.Dir;
import TankTest.Enum.Group;
import TankTest.Model.AbstractGameObject;
import TankTest.Model.bullet.GeneralBullet;
import TankTest.Model.explode.Explode;
import TankTest.jFrame.StartGame.StartGameJPanel;
import TankTest.util.ResourceMgr;

import java.awt.*;
import java.util.Random;

/**
 * @author huang
 * @Description
 * @create 2020-06-13-13:18
 */
public class Tank extends AbstractGameObject {
    private int x, y;
    private Dir dir;
    private boolean moving;
    private Group group;
    private int oldX, oldY;
    private boolean live;
    private int SPEED;
    private int health;
    private int width, height;
    private Rectangle rect;
    private Random r = new Random();
    private int whichDir = 0;
    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.oldX = x;
        this.oldY = y;
        this.live = true;
        this.width = ResourceMgr.generalTankU.getWidth();
        this.height = ResourceMgr.generalTankU.getHeight();
        //图像大小60*60，内部坦克50*50，多出来的10像素忽略不计了，
        // 要记的话可以把rect的大小改一下，在判collider时更精确
        //Wu：已改
        this.rect = new Rectangle(x+10, y+10, width-10, height-10);
        whichDir=r.nextInt(2);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


    public void setOldX(int oldX) {
        this.oldX = oldX;
    }


    public void setOldY(int oldY) {
        this.oldY = oldY;
    }

    @Override
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getSPEED() {
        return SPEED;
    }

    public void setSPEED(int SPEED) {
        this.SPEED = SPEED;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void paint(Graphics g) {
    }

    public void move() {
        if (!moving || !live) return;
        oldX = x;
        oldY = y;
        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case D:
                y += SPEED;
                break;
            case LU:
                x -= Math.sqrt(2) * SPEED / 2;
                y -= Math.sqrt(2) * SPEED / 2;
                break;
            case LD:
                x -= Math.sqrt(2) * SPEED / 2;
                y += Math.sqrt(2) * SPEED / 2;
                break;
            case RU:
                x += Math.sqrt(2) * SPEED / 2;
                y -= Math.sqrt(2) * SPEED / 2;
                break;
            case RD:
                x += Math.sqrt(2) * SPEED / 2;
                y += Math.sqrt(2) * SPEED / 2;
                break;
        }
        boundsCheck();
        RandomDir();
        fire();
    }

    private void fire() {
        if (!live) return;
        int tx = x + ResourceMgr.generalTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int ty = y + ResourceMgr.generalTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        if (r.nextInt(100) > 97)
            StartGameJPanel.INSTANCE.getGm().add(new GeneralBullet(tx, ty, dir, group));
    }

    public void boundsCheck() {
        if (x < 0 || (x + width) > StartGameJPanel.WIDTH || y < 0 || (y + height) > StartGameJPanel.HEIGHT)
            this.back();
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

    private void RandomDir() {
        if (r.nextInt(100) > 98)
            this.dir = Dir.randomDir();
    }

    public void back() {
        int which = r.nextInt(3);
        if(which>1) {
            switch (whichDir) {
                case 0:
                    clockWiseBack();
                    break;
                case 1:
                    anticlockWiseBack();
                    break;
            }
        }
        x = oldX;
        y = oldY;
    }

    /**
     * 逆时针旋转
     */
    private void anticlockWiseBack() {
        switch(dir) {
            case L:
                dir=Dir.D;
                break;
            case R:
                dir=Dir.U;
                break;
            case U:
                dir=Dir.L;
                break;
            case D:
                dir=Dir.R;
                break;
            case LU:
                dir=Dir.D;
                break;
            case LD:
                dir=Dir.R;
                break;
            case RU:
                dir=Dir.L;
                break;
            case RD:
                dir=Dir.U;
                break;
        }
    }

    /**
     * 顺时针旋转
     */
    private void clockWiseBack() {
        switch(dir) {
            case L:
                dir=Dir.U;
                break;
            case R:
                dir=Dir.D;
                break;
            case U:
                dir=Dir.R;
                break;
            case D:
                dir=Dir.L;
                break;
            case LU:
                dir=Dir.R;
                break;
            case LD:
                dir=Dir.U;
                break;
            case RU:
                dir=Dir.D;
                break;
            case RD:
                dir=Dir.L;
                break;
        }
    }

    public void die(int hurt) {
        health -= hurt;
        StartGameJPanel.INSTANCE.getGm().add(new Explode(x, y));
        if (health <= 0) {
            this.setLive(false);
        }
    }
}
