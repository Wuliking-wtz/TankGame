package TankTest.Model;

import java.awt.*;

/**
 * 所有游戏物体的抽象父类
 */
public abstract class AbstractGameObject {

    public abstract void paint(Graphics g);

    public abstract boolean isLive();

    public abstract Rectangle getRect();

    public abstract void die(int hurt);

}
