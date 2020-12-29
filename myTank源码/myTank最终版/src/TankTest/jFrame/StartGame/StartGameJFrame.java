package TankTest.jFrame.StartGame;

import TankTest.Model.AbstractGameObject;
import TankTest.Model.Level;
import TankTest.Model.explode.Explode;
import TankTest.Model.tank.Tank;
import TankTest.jFrame.BlackJPanel;
import TankTest.jFrame.Menu.MenuJFrame;
import TankTest.util.ProperMgr;
import TankTest.util.Audio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * 开始游戏的界面
 */
public class StartGameJFrame implements Runnable {
    public static final int width = Integer.parseInt(ProperMgr.get("gameWidth"));
    public static final int height = Integer.parseInt(ProperMgr.get("gameHeight")) + 30;
    public static final int FRESH_TIME = 25;//画面刷新时间，单位：毫秒
    public static JFrame jf = null;
    private boolean b = true;
    private Thread t1;
    Audio audio = new Audio("Audio/war1.wav");

    public StartGameJFrame() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        if (jf == null) {
            jf = new JFrame("坦克大战");
            jf.setBounds(150, 100, width, height);
            jf.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            jf.setResizable(false);
            jf.requestFocus();

            jf.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int result = JOptionPane.showConfirmDialog(null, "确认回到主页面？", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        b = false;
                        jf.dispose();
                        jf = null;
                        if (t1 != null) {
                            audio.getClip().stop();
                            t1.stop();
                            t1 = null;
                        }
                        new MenuJFrame().setVisible(true);
                    }
                }
            });
        }
        showLevelMessage("即将开始第" + StartGameJPanel.INSTANCE.getLevel() + "关");//展示关卡开场
        if (b) {
            jf.setContentPane(StartGameJPanel.INSTANCE);
            StartGameJPanel.INSTANCE.requestFocus();
            jf.setVisible(true);
            t1 = new Thread(() -> audio.loopPlay());
            t1.start();
        }
        while (b) {
            try {
                Thread.sleep(FRESH_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            StartGameJPanel.INSTANCE.repaint();
            int sum = 0;
            List<AbstractGameObject> objects = StartGameJPanel.INSTANCE.getGm().getObjects();
            boolean flag = false;
            if (!StartGameJPanel.INSTANCE.getGm().getMyTank().isLive() || !StartGameJPanel.INSTANCE.getGm().getBase().isLive()) {
                for (int i = 0; i < objects.size(); i++) {
                    if (objects.get(i) instanceof Explode) {
                        flag = true;
                    }
                }
                if (flag == true)
                    continue;
                showLevelMessage("通关失败，请重头再来！");
                StartGameJPanel.INSTANCE = new StartGameJPanel(1);
                if (jf != null)
                    jf.getContentPane().removeAll();//清理掉之前的JPanel
                if (t1 != null) {
                    audio.getClip().stop();
                    t1.stop();  //关掉音乐线程
                    t1 = null;
                }
                break;
            }
            for (int i = 0; i < objects.size(); i++) {
                if (i >= objects.size())//偶尔可能会刚好移除坦克或是爆炸，导致IndexOutOfBoundsException
                    continue;
                if (objects.get(i) instanceof Tank) {
                    sum++;
                }
                if (objects.get(i) instanceof Explode) {
                    flag = true;
                }
            }
            if (flag == true)
                continue;
            //到达下一关
            if (sum == 1 && StartGameJPanel.INSTANCE.getGm().getMyTank().isLive()) {
                int level = StartGameJPanel.INSTANCE.getLevel() + 1;
                if (level > Level.getCount()) {
                    showLevelMessage("恭喜您成功通过所有关卡！");
                    b = false;
                    jf.dispose();
                    jf = null;
                    new MenuJFrame().setVisible(true);
                }
                if (level <= Level.getCount()) {
                    StartGameJPanel.INSTANCE = new StartGameJPanel(level);
                }
                if (jf != null)
                    jf.getContentPane().removeAll();//清理掉之前的JPanel
                if (t1 != null) {
                    audio.getClip().stop();
                    t1.stop();  //关掉音乐线程
                    t1 = null;
                }
                break;
            }
        }
        if (b) {
            new Thread(this).start();
        }
    }

    /**
     * 在关卡开始时，展示第几关
     */
    private void showLevelMessage(String s) {
        BlackJPanel blackJPanel = new BlackJPanel();
        blackJPanel.setS(s);
        jf.setContentPane(blackJPanel);
        blackJPanel.setBackground(Color.BLACK);
        blackJPanel.repaint();
        jf.setVisible(true);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
