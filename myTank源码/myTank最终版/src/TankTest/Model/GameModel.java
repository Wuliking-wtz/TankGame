package TankTest.Model;

import TankTest.Enum.Dir;
import TankTest.Enum.Group;
import TankTest.Model.strage.BloodStrage;
import TankTest.Model.strage.BulletStrage;
import TankTest.Model.strage.FireSpeedStrage;
import TankTest.Model.strage.MoveSpeedStrage;
import TankTest.Model.tank.*;
import TankTest.Model.wall.Base;
import TankTest.Model.wall.Grass;
import TankTest.chainofresponsibility.ColliderChain;
import TankTest.jFrame.MapEditor.MapEditorJFrame;
import TankTest.jFrame.StartGame.StartGameJFrame;
import TankTest.jFrame.StartGame.StartGameJPanel;
import TankTest.util.ProperMgr;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author huang
 * @Description
 * @create 2020-06-14-15:18
 */
public class GameModel {

    public PlayerTank myTank;
    private List<AbstractGameObject> objects = null;//物体集合
    private ColliderChain chain = null;
    private int[] boxTank = null;
    private Base base;
    /**
     * 初始化坦克数量
     */
    private int level;
    private int createBotTimer;
    private int TanksSum = Integer.parseInt(ProperMgr.get("initTankCount"));           //当前关卡的坦克总数
    private int onWarTanksNum = 0;      //当前场上坦克的存活数
    private int maxOnWarTanksNum = 9;   //场上坦克的最多存活数
    private int readyOnWarTanksNum = TanksSum; //准备上场的坦克数
    private StartGameJFrame frame;
    private int createStrageCount = 0;  //生成道具数量
    private List<Grass> grass = new ArrayList<>();

    public GameModel(int level) {
        this.level = level;
        TanksSum += level * 2;
        initGameObjects();

    }

    public Base getBase() {
        return base;
    }

    public int getCreateStrageCount() {
        return createStrageCount;
    }

    public void setCreateStrageCount(int createStrageCount) {
        this.createStrageCount = createStrageCount;
    }

    private void initGameObjects() {
        objects = new ArrayList<>();//游戏物体
        chain = new ColliderChain();//责任链，碰撞检测
        //level = 1;
        base = new Base(MapEditorJFrame.width / 2 - 30, MapEditorJFrame.height - 60);
        boxTank = new int[3];
        //初始化三个坦克出生点的横坐标
        boxTank[0] = 0;
        boxTank[1] = MapEditorJFrame.width / 2 - 30;
        boxTank[2] = MapEditorJFrame.width - 60;
        //左下角产生玩家坦克
        myTank = new PlayerTank(0, StartGameJPanel.HEIGHT - 60, Dir.U, Group.GOOD);

        objects.add(myTank);

        objects.add(new GeneralTank(boxTank[0], 0, Dir.D, Group.BAD));
        objects.add(new QuickTank(boxTank[1], 0, Dir.D, Group.BAD));
        objects.add(new StrengthTank(boxTank[2], 0, Dir.D, Group.BAD));
        onWarTanksNum = 3;
        initWalls();//增加地图上的墙块
    }

    /**
     * 初始化地图中的墙块
     */
    public void initWalls() {
        Map map = new Map(level);// 获取当前关卡的地图对象
        objects.addAll(map.getWalls());// 墙块集合添加当前地图中所有墙块
        for (int i = 0; i < objects.size(); i++) {
            AbstractGameObject ago = objects.get(i);
            if (ago instanceof Grass) {
                objects.remove(i);
                grass.add((Grass) ago);
                i--;
            }
        }
        objects.add(base);// 墙块集合添加基地
    }

    public void add(AbstractGameObject go) {
        objects.add(go);
    }

    public void paint(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            AbstractGameObject w = objects.get(i);
            if (!w.isLive()) {
                if (w instanceof Tank && !(w instanceof PlayerTank))//如果是敌方坦克被攻击
                    onWarTanksNum--;//场上坦克数量减一
                objects.remove(i);
                i--;
                continue;
            }
            AbstractGameObject go1 = objects.get(i);
            for (int j = i + 1; j < objects.size(); j++) {
                AbstractGameObject go2 = objects.get(j);
                chain.collide(go1, go2);
            }
            if (objects.get(i).isLive())
                objects.get(i).paint(g);
        }
        createBotTank();//生产坦克
        for (int i = 0; i < grass.size(); i++) {
            grass.get(i).paint(g);
        }
        creatStrage();  //随机生成道具
        g.setFont(new Font("微软雅黑", Font.BOLD, 16));
        g.setColor(Color.white);
        g.drawString("地雷数:" + myTank.getLandmineCount(), 0, 15);
        g.drawString("散弹数:" + myTank.getScatterBulletCount(), 0, 35);
        g.drawString("穿墙炮弹数:" + myTank.getThroughBulletCount(), 0, 55);
        g.drawString("蓄力子弹数:" + myTank.getXuLiBulletCount(), 0, 75);
        g.drawString("剩余坦克数:" + onWarTanksNum, 0, 95);
    }

    private void creatStrage() {
        Random r = new Random();
        int index = r.nextInt(4) + 1;  //随机选择四种道具
        int isCreate = r.nextInt(500);  //随机是否生成
        int xx = r.nextInt(1500);
        int yy = r.nextInt(800);
        if (isCreate > 496 && createStrageCount < 5) {
            switch (index) {
                case 1:
                    objects.add(new BloodStrage(xx, yy));
                    break;
                case 2:
                    objects.add(new BulletStrage(xx, yy));
                    break;
                case 3:
                    objects.add(new FireSpeedStrage(xx, yy));
                    break;
                case 4:
                    objects.add(new MoveSpeedStrage(xx, yy));
                    break;
            }
            createStrageCount++;
        }

    }

    /**
     * 在三个出生点生产坦克
     */
    private void createBotTank() {
        Random r = new Random();
        int index = r.nextInt(3);
        createBotTimer += StartGameJFrame.FRESH_TIME;// 计时器按照刷新时间递增
        // “当场上电脑小于场上最大数时” 并且 “准备上场的坦克数量大于0” 并且 “计时器记录已过去1.5秒钟”
        if (onWarTanksNum < maxOnWarTanksNum && readyOnWarTanksNum > 0 && createBotTimer >= 1500) {
            Rectangle rect = new Rectangle(boxTank[index], 0, 60, 60);// 创建坦克随机出生区域
            for (int i = 0, lengh = objects.size(); i < lengh; i++) {// 循环遍历所有物体集合
                AbstractGameObject t = objects.get(i);// 获取坦克对象
                if (t.isLive() && rect.intersects(t.getRect()) == true) {// 如果场上存在与随机位置重合并存活的坦克
                    return;// 结束方法
                }
            }
            // 创造电脑坦克
            int which = r.nextInt(3);//生产随机坦克
            switch (which) {
                case 0:
                    objects.add(new GeneralTank(boxTank[index], 0, Dir.D, Group.BAD));
                    break;
                case 1:
                    objects.add(new StrengthTank(boxTank[index], 0, Dir.D, Group.BAD));
                    break;
                case 2:
                    objects.add(new QuickTank(boxTank[index], 0, Dir.D, Group.BAD));
                    break;
            }
            readyOnWarTanksNum--;// 准备上场电脑数量-1
            onWarTanksNum++;
            createBotTimer = 0;// 产生电脑计时器重新计时
        }
    }

    public PlayerTank getMyTank() {
        return myTank;
    }

    public List<AbstractGameObject> getObjects() {
        return objects;
    }
}

