package TankTest.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 地图上一切资源图片的加载
 */
public class ResourceMgr {
    //各坦克图片
    public static BufferedImage myTankL, myTankR, myTankU, myTankD, myTankRU, myTankRD, myTankLU, myTankLD;
    public static BufferedImage generalTankL, generalTankR, generalTankU, generalTankD, generalTankRU, generalTankRD, generalTankLU, generalTankLD;
    public static BufferedImage quickTankL, quickTankR, quickTankU, quickTankD, quickTankRU, quickTankRD, quickTankLU, quickTankLD;
    public static BufferedImage strengthTankL, strengthTankR, strengthTankU, strengthTankD, strengthTankRU, strengthTankRD, strengthTankLU, strengthTankLD;


    //各导弹图片
    public static BufferedImage bulletL, bulletR, bulletU, bulletD, bulletRU, bulletRD, bulletLU, bulletLD;
    public static BufferedImage ThroughtBulletL, ThroughtBulletR, ThroughtBulletU, ThroughtBulletD, ThroughtBulletRU, ThroughtBulletRD, ThroughtBulletLU, ThroughtBulletLD;
    public static BufferedImage scatterBullet[] = new BufferedImage[24];
    public static BufferedImage XuLiBulletL, XuLiBulletR, XuLiBulletU, XuLiBulletD, XuLiBulletRU, XuLiBulletRD, XuLiBulletLU, XuLiBulletLD;
    //地雷
    public static BufferedImage Landmine;

    //坦克爆炸图片
    public static BufferedImage bombs[] = new BufferedImage[16];

    //大爆炸图片
    public static BufferedImage bigBombs[] = new BufferedImage[16];
    //可打破的墙图片
    public static BufferedImage brickWall;
    //铁墙图片
    public static BufferedImage ironWall;
    //河流图片
    public static BufferedImage river;
    //基地图片
    public static BufferedImage base;
    //草图片
    public static BufferedImage grass;
    //血条图片
    public static BufferedImage bloods2[] = new BufferedImage[2];
    public static BufferedImage bloods5[] = new BufferedImage[5];
    public static BufferedImage bloods6[] = new BufferedImage[6];

    //加血道具图片
    public static BufferedImage BloodStrage;
    //加弹药道具图片
    public static BufferedImage BulletStrage;
    //增加射速道具
    public static BufferedImage FireSpeedStrage;
    //增加移速道具
    public static BufferedImage MoveSpeedStrage;


    static {
        try {
            myTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/tank/腹蛇60.png"));
            myTankL = ImageUtil.rotateImage(myTankU, -90);
            myTankR = ImageUtil.rotateImage(myTankU, 90);
            myTankD = ImageUtil.rotateImage(myTankU, 180);
            myTankRU = ImageUtil.rotateImage(myTankU, 45);
            myTankRD = ImageUtil.rotateImage(myTankU, 135);
            myTankLU = ImageUtil.rotateImage(myTankU, -45);
            myTankLD = ImageUtil.rotateImage(myTankU, -135);

            generalTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/tank/路霸60.png"));
            generalTankL = ImageUtil.rotateImage(generalTankU, -90);
            generalTankR = ImageUtil.rotateImage(generalTankU, 90);
            generalTankD = ImageUtil.rotateImage(generalTankU, 180);
            generalTankRU = ImageUtil.rotateImage(generalTankU, 45);
            generalTankRD = ImageUtil.rotateImage(generalTankU, 135);
            generalTankLU = ImageUtil.rotateImage(generalTankU, -45);
            generalTankLD = ImageUtil.rotateImage(generalTankU, -135);

            quickTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/tank/原能坦克60.png"));
            quickTankL = ImageUtil.rotateImage(quickTankU, -90);
            quickTankR = ImageUtil.rotateImage(quickTankU, 90);
            quickTankD = ImageUtil.rotateImage(quickTankU, 180);
            quickTankRU = ImageUtil.rotateImage(quickTankU, 45);
            quickTankRD = ImageUtil.rotateImage(quickTankU, 135);
            quickTankLU = ImageUtil.rotateImage(quickTankU, -45);
            quickTankLD = ImageUtil.rotateImage(quickTankU, -135);

            strengthTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/tank/灰影60.png"));
            strengthTankL = ImageUtil.rotateImage(strengthTankU, -90);
            strengthTankR = ImageUtil.rotateImage(strengthTankU, 90);
            strengthTankD = ImageUtil.rotateImage(strengthTankU, 180);
            strengthTankRU = ImageUtil.rotateImage(strengthTankU, 45);
            strengthTankRD = ImageUtil.rotateImage(strengthTankU, 135);
            strengthTankLU = ImageUtil.rotateImage(strengthTankU, -45);
            strengthTankLD = ImageUtil.rotateImage(strengthTankU, -135);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/bullet/普通子弹30.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            bulletRU = ImageUtil.rotateImage(bulletU, 45);
            bulletRD = ImageUtil.rotateImage(bulletU, 135);
            bulletLU = ImageUtil.rotateImage(bulletU, -45);
            bulletLD = ImageUtil.rotateImage(bulletU, -135);

            ThroughtBulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/bullet/穿墙子弹30.png"));
            ThroughtBulletL = ImageUtil.rotateImage(ThroughtBulletU, -90);
            ThroughtBulletR = ImageUtil.rotateImage(ThroughtBulletU, 90);
            ThroughtBulletD = ImageUtil.rotateImage(ThroughtBulletU, 180);
            ThroughtBulletRU = ImageUtil.rotateImage(ThroughtBulletU, 45);
            ThroughtBulletRD = ImageUtil.rotateImage(ThroughtBulletU, 135);
            ThroughtBulletLU = ImageUtil.rotateImage(ThroughtBulletU, -45);
            ThroughtBulletLD = ImageUtil.rotateImage(ThroughtBulletU, -135);

            scatterBullet[0] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/bullet/散射子弹30.png"));
            for (int i = 1; i < 24; i++)
                scatterBullet[i] = ImageUtil.rotateImage(scatterBullet[0], 15 * i);

            XuLiBulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/bullet/蓄力子弹30.png"));
            XuLiBulletL = ImageUtil.rotateImage(XuLiBulletU, -90);
            XuLiBulletR = ImageUtil.rotateImage(XuLiBulletU, 90);
            XuLiBulletD = ImageUtil.rotateImage(XuLiBulletU, 180);
            XuLiBulletRU = ImageUtil.rotateImage(XuLiBulletU, 45);
            XuLiBulletRD = ImageUtil.rotateImage(XuLiBulletU, 135);
            XuLiBulletLU = ImageUtil.rotateImage(XuLiBulletU, -45);
            XuLiBulletLD = ImageUtil.rotateImage(XuLiBulletU, -135);

            //地雷
            Landmine = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/bullet/地雷30.png"));


            for (int i = 0; i < bombs.length; i++)
                bombs[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/tankExplode/e" + (i + 1) + ".gif"));

            for (int i = 0; i < bigBombs.length; i++)
                bigBombs[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/bigExplode/e" + (i + 1) + ".png"));

            brickWall = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/wall/粉墙30.png"));
            ironWall = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/wall/铁墙30.png"));
            river = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/wall/河流30.png"));
            base = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/wall/基地60.png"));
            grass = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/wall/草30.png"));

            BloodStrage = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/strategy/加血道具.png"));
            BulletStrage = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/strategy/加弹药道具.png"));
            FireSpeedStrage = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/strategy/增加射速道具.png"));
            MoveSpeedStrage = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/strategy/增加移速道具.png"));

            for (int i = 0; i < bloods2.length; i++)
                bloods2[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/blood/blood2/" + (i + 1) + "-2.png"));
            for (int i = 0; i < bloods5.length; i++)
                bloods5[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/blood/blood5/" + (i + 1) + "-5.png"));
            for (int i = 0; i < bloods6.length; i++)
                bloods6[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Image/blood/blood6/" + (i + 1) + "-6.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
