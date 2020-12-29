package TankTest.chainofresponsibility;

import TankTest.Model.AbstractGameObject;

/**
 * @author huang
 * @Description
 * @create 2020-06-12-22:13
 */
public interface Collider {
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2);
}
