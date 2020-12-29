package TankTest.Model.tank;

import TankTest.Enum.Dir;
import TankTest.Enum.Group;
import TankTest.Model.bullet.*;
import TankTest.Model.explode.BigExplode;
import TankTest.jFrame.StartGame.StartGameJFrame;
import TankTest.jFrame.StartGame.StartGameJPanel;
import TankTest.util.Audio;
import TankTest.util.ResourceMgr;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author huang
 * @Description
 * @create 2020-06-08-15:45
 */
public class PlayerTank extends Tank {
    private boolean bL, bR, bU, bD;

    //地雷数量
    private int landmineCount = 3;
    //蓄力子弹的对象
    private XuLiBullet xuLiBullet = null;
    //是否正在蓄力，false表示还没开始蓄力或者蓄力结束，true表示正在蓄力
    private boolean xuLiBulletGetReady = false;
    // 蓄力时间
    private int xuLiBulletTime = 0;
    //蓄力子弹数量
    private int xuLiBulletCount = 3;

    //穿墙子弹数量
    private int throughBulletCount = 18;
    //散射子弹数量
    private int scatterBulletCount = 25;

    //是否子弹加速，false表示没有加速，true表示正在加速
    private boolean FireSpeedGetReady = false;
    //是否坦克加速，false表示没有加速，true表示正在加速
    private boolean MoveSpeedGetReady = false;
    // 子弹加速时间
    private int FireSpeedTime = 0;
    // 坦克加速时间
    private int MoveSpeedTime = 0;


    public PlayerTank(int x, int y, Dir dir, Group group) {
        super(x, y, dir, group);
        setHealth(6);//血量6
        setSPEED(5);//移速5
        setMoving(false);
    }

    public int getLandmineCount() {
        return landmineCount;
    }

    public int getXuLiBulletCount() {
        return xuLiBulletCount;
    }

    public void setFireSpeedTime(int fireSpeedTime) {
        FireSpeedTime = fireSpeedTime;
    }

    public void setMoveSpeedTime(int moveSpeedTime) {
        MoveSpeedTime = moveSpeedTime;
    }

    public void setFireSpeedGetReady(boolean fireSpeedGetReady) {
        FireSpeedGetReady = fireSpeedGetReady;
    }

    public void setMoveSpeedGetReady(boolean moveSpeedGetReady) {
        MoveSpeedGetReady = moveSpeedGetReady;
    }

    public int getThroughBulletCount() {
        return throughBulletCount;
    }

    public void setThroughBulletCount(int throughBulletCount) {
        this.throughBulletCount = throughBulletCount;
    }

    public int getScatterBulletCount() {
        return scatterBulletCount;
    }

    public void setScatterBulletCount(int scatterBulletCount) {
        this.scatterBulletCount = scatterBulletCount;
    }

    @Override
    public void paint(Graphics g) {
        if (!this.isLive()) return;
        if (xuLiBulletGetReady) {    //如果正在蓄力
            xuLiBulletTime += StartGameJFrame.FRESH_TIME;//蓄力时间加上画面刷新时间
        }
        if (FireSpeedGetReady) {  //如果子弹加速
            FireSpeedTime += StartGameJFrame.FRESH_TIME;  //子弹加速时间加上画面刷新时间
            if (FireSpeedTime > 13000) {
                FireSpeedGetReady = false;
                FireSpeedTime = 0;
            }

        }
        if (MoveSpeedGetReady) {   //坦克加速
            MoveSpeedTime += StartGameJFrame.FRESH_TIME;  //坦克加速时间机上画面刷新时间
            if (MoveSpeedTime >= 13000) {
                MoveSpeedGetReady = false;
                MoveSpeedTime = 0;
            }

        }
        g.drawImage(ResourceMgr.bloods6[getHealth() - 1], super.getX(), super.getY() - 5, null);
        switch (getDir()) {
            case L:
                g.drawImage(ResourceMgr.myTankL, getX(), getY(), null);
                break;
            case R:
                g.drawImage(ResourceMgr.myTankR, getX(), getY(), null);
                break;
            case U:
                g.drawImage(ResourceMgr.myTankU, getX(), getY(), null);
                break;
            case D:
                g.drawImage(ResourceMgr.myTankD, getX(), getY(), null);
                break;
            case LU:
                g.drawImage(ResourceMgr.myTankLU, getX(), getY(), null);
                break;
            case LD:
                g.drawImage(ResourceMgr.myTankLD, getX(), getY(), null);
                break;
            case RU:
                g.drawImage(ResourceMgr.myTankRU, getX(), getY(), null);
                break;
            case RD:
                g.drawImage(ResourceMgr.myTankRD, getX(), getY(), null);
                break;
        }
        this.move();
        super.getRect().x = super.getX() + 7;
        super.getRect().y = super.getY() + 7;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_S:
                bD = true;
                break;
            case KeyEvent.VK_W:
                bU = true;
                break;
            case KeyEvent.VK_A:
                bL = true;
                break;
            case KeyEvent.VK_D:
                bR = true;
                break;
            case KeyEvent.VK_K:
                beginToXuLiFire();
                break;
        }
        setMainDir();

    }

    /**
     * K键按下，开始蓄力
     */
    private void beginToXuLiFire() {
        if (xuLiBullet == null || !(xuLiBullet.isLive())) {  //还没创建或已经引爆，需要重新蓄力
            xuLiBulletGetReady = true;    //开始蓄力
        } else if (xuLiBullet.isLive()) { //此时子弹正在场上，引爆
            StartGameJPanel.INSTANCE.getGm().add(new BigExplode(xuLiBullet.getX() - BigExplode.w / 2, xuLiBullet.getY() - BigExplode.h / 2));
            xuLiBullet.die(1);  //设置成死亡
        }
    }

    private void setMainDir() {
        if (!bL && !bR && !bU && !bD) {
            setMoving(false);
        } else {
            setMoving(true);
            if (bL && !bR && !bU && !bD) {
                setDir(Dir.L);
            }
            if (!bL && bR && !bU && !bD) {
                setDir(Dir.R);
            }
            if (!bL && !bR && bU && !bD) {
                setDir(Dir.U);
            }
            if (!bL && !bR && !bU && bD) {
                setDir(Dir.D);
            }
            if (bL && !bR && bU && !bD) {
                setDir(Dir.LU);
            }
            if (bL && !bR && !bU && bD) {
                setDir(Dir.LD);
            }
            if (!bL && bR && bU && !bD) {
                setDir(Dir.RU);
            }
            if (!bL && bR && !bU && bD) {
                setDir(Dir.RD);
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_S:
                bD = false;
                break;
            case KeyEvent.VK_W:
                bU = false;
                break;
            case KeyEvent.VK_A:
                bL = false;
                break;
            case KeyEvent.VK_D:
                bR = false;
                break;
            case KeyEvent.VK_J:
                fire(KeyEvent.VK_J);
                break;
            case KeyEvent.VK_U:
                fire(KeyEvent.VK_U);
                break;
            case KeyEvent.VK_I:
                fire(KeyEvent.VK_I);
                break;
            case KeyEvent.VK_K:
                endToXuLiFire();
                break;
            case KeyEvent.VK_O:
                fire(KeyEvent.VK_O);
                break;

        }
        setMainDir();

    }

    /**
     * K键松开，结束蓄力
     */
    private void endToXuLiFire() {
        if (xuLiBulletTime >= 1000 && xuLiBulletCount > 0) {   //大于2秒,可蓄力炮弹发射
            int tx = getX() + ResourceMgr.myTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
            int ty = getY() + ResourceMgr.myTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
            xuLiBullet = new XuLiBullet(tx, ty, getDir(), getGroup());
            StartGameJPanel.INSTANCE.getGm().add(xuLiBullet);
            new Thread(() -> new Audio("Audio/tank_fire.wav").play()).start();
            xuLiBulletCount--;  //蓄力炮弹数减1
        }
        xuLiBulletTime = 0;   //蓄力时间重新初始化
        xuLiBulletGetReady = false; //结束蓄力状态
    }

    private void fire(int k) {
        if (!isLive()) return;
        int tx = getX() + ResourceMgr.myTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int ty = getY() + ResourceMgr.myTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        if (k == KeyEvent.VK_J) { //普通开火
            GeneralBullet g = new GeneralBullet(tx, ty, getDir(), getGroup());
            if (FireSpeedGetReady && FireSpeedTime < 13000)
                g.SPEED = 20;
            StartGameJPanel.INSTANCE.getGm().add(g);
            new Thread(() -> new Audio("Audio/tank_fire.wav").play()).start();
        } else if (k == KeyEvent.VK_U) { //穿墙炮弹
            if (throughBulletCount > 0) {
                ThroughBullet t = new ThroughBullet(tx, ty, getDir(), getGroup());
                if (FireSpeedGetReady && FireSpeedTime < 13000)
                    t.SPEED = 20;
                StartGameJPanel.INSTANCE.getGm().add(t);
                throughBulletCount--;
                new Thread(() -> new Audio("Audio/tank_fire.wav").play()).start();
            }
        } else if (k == KeyEvent.VK_I) {  //散射炮弹
            if (scatterBulletCount > 0) {
                ScatterBullet s1 = new ScatterBullet(tx, ty, getDir(), getGroup(), -1);
                ScatterBullet s2 = new ScatterBullet(tx, ty, getDir(), getGroup(), 0);
                ScatterBullet s3 = new ScatterBullet(tx, ty, getDir(), getGroup(), 1);
                if (FireSpeedGetReady && FireSpeedTime < 13000) {
                    s1.SPEED = 20;
                    s2.SPEED = 20;
                    s3.SPEED = 20;
                }

                StartGameJPanel.INSTANCE.getGm().add(s1);
                StartGameJPanel.INSTANCE.getGm().add(s2);
                StartGameJPanel.INSTANCE.getGm().add(s3);
                scatterBulletCount--;
                new Thread(() -> new Audio("Audio/tank_fire.wav").play()).start();
            }
        } else if (k == KeyEvent.VK_O) {   //地雷
            if (landmineCount > 0) {
                StartGameJPanel.INSTANCE.getGm().add(new Landmine(tx, ty));
                landmineCount--;
            }
        }
    }

    @Override
    public void move() {
        if (!isMoving() || !isLive()) return;
        setOldX(getX());
        setOldY(getY());
        int speed;
        if (MoveSpeedGetReady && MoveSpeedTime <= 13000) {
            speed = getSPEED() * 2;
        } else {
            speed = getSPEED();
        }
        switch (getDir()) {
            case L:
                setX(getX() - speed);
                break;
            case R:
                setX(getX() + speed);
                break;
            case U:
                setY(getY() - speed);
                break;
            case D:
                setY(getY() + speed);
                break;
            case LU:
                setX((int) (getX() - Math.sqrt(2) * speed / 2));
                setY((int) (getY() - Math.sqrt(2) * speed / 2));
                break;
            case LD:
                setX((int) (getX() - Math.sqrt(2) * speed / 2));
                setY((int) (getY() + Math.sqrt(2) * speed / 2));
                break;
            case RU:
                setX((int) (getX() + Math.sqrt(2) * speed / 2));
                setY((int) (getY() - Math.sqrt(2) * speed / 2));
                break;
            case RD:
                setX((int) (getX() + Math.sqrt(2) * speed / 2));
                setY((int) (getY() + Math.sqrt(2) * speed / 2));
                break;
        }
        this.boundsCheck();
    }

    @Override
    public void boundsCheck() {
        if (getX() < 0 || (getX() + getWidth()) > StartGameJPanel.WIDTH || getY() < 0 || (getY() + getHeight()) > StartGameJPanel.HEIGHT)
            this.back();
    }

    @Override
    public void back() {
        setX(getOldX());
        setY(getOldY());
    }
}
