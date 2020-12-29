package TankTest.chainofresponsibility;

import TankTest.Model.AbstractGameObject;
import TankTest.Model.strage.*;
import TankTest.Model.tank.PlayerTank;
import TankTest.jFrame.StartGame.StartGameJPanel;

/**
 * @author huang
 * @Description
 * @create 2020-06-20-13:25
 */
public class stragegyTankCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Stragegy && go2 instanceof PlayerTank) {
            Stragegy stragegy = (Stragegy) go1;
            PlayerTank playerTank = (PlayerTank) go2;
            if (!stragegy.isLive() || !playerTank.isLive()) return false;
            if (playerTank.getRect().intersects(stragegy.getRect())) {
                if (stragegy instanceof BloodStrage) {
                    playerTank.setHealth(playerTank.getHealth() + 2);
                    if (playerTank.getHealth() > 6) {
                        playerTank.setHealth(6);
                    }
                } else if (stragegy instanceof BulletStrage) {
                    if (playerTank.getThroughBulletCount() < 18) {
                        playerTank.setThroughBulletCount(playerTank.getThroughBulletCount() + 1);
                    }
                    if (playerTank.getScatterBulletCount() < 25) {
                        playerTank.setScatterBulletCount(playerTank.getScatterBulletCount() + 1);
                    }

                } else if (stragegy instanceof FireSpeedStrage) {
                    StartGameJPanel.INSTANCE.getGm().getMyTank().setFireSpeedGetReady(true);
                    StartGameJPanel.INSTANCE.getGm().getMyTank().setFireSpeedTime(0);
                } else if (stragegy instanceof MoveSpeedStrage) {
                    StartGameJPanel.INSTANCE.getGm().getMyTank().setMoveSpeedGetReady(true);
                    StartGameJPanel.INSTANCE.getGm().getMyTank().setMoveSpeedTime(0);
                }
                stragegy.die(1);
                StartGameJPanel.INSTANCE.getGm().setCreateStrageCount(StartGameJPanel.INSTANCE.getGm().getCreateStrageCount() - 1);
                return false;
            }
        } else if (go1 instanceof PlayerTank && go2 instanceof Stragegy) {
            return collide(go2, go1);
        }
        return true;
    }

}
