package TankTest.chainofresponsibility;

import TankTest.Model.AbstractGameObject;
import TankTest.util.ProperMgr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huang
 * @Description
 * @create 2020-06-12-23:18
 */
public class ColliderChain {
    private List<Collider> colliders = null;

    public ColliderChain() {
        initColliders();
    }

    private void initColliders() {

        colliders = new ArrayList<>();
        String[] colliderSName = ProperMgr.get("colliders").split(",");
        try {
            for (String name : colliderSName) {
                Class clazz = Class.forName("TankTest.chainofresponsibility." + name);
                Collider c = (Collider) (clazz.getConstructor().newInstance());
                colliders.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collide(AbstractGameObject go1, AbstractGameObject go2) {
        for (Collider collider : colliders) {
            if (!collider.collide(go1, go2))
                break;
        }
    }
}
