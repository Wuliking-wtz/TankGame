package TankTest.jFrame.StartGame;

import TankTest.Model.GameModel;
import TankTest.jFrame.Menu.MenuJPanel;
import TankTest.util.ProperMgr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 开始游戏的JPanel面板
 */
public class StartGameJPanel extends JPanel {
    public static final int WIDTH = Integer.parseInt(ProperMgr.get("gameWidth"));
    public static final int HEIGHT = Integer.parseInt(ProperMgr.get("gameHeight"));
    public static StartGameJPanel INSTANCE = new StartGameJPanel(1);
    private GameModel gm;
    private int level;
    private boolean end = false;
    private BufferedImage img;

    public StartGameJPanel(int level) {
        try {
            this.img = ImageIO.read(StartGameJPanel.class.getClassLoader().getResourceAsStream("Image/游戏背景.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gm = new GameModel(level);
        this.level = level;
        this.setBounds(0, 0, WIDTH, HEIGHT);
        setFocusable(true);
        this.addKeyListener(new TankKeyListener());
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);  //更新图像
        g.drawImage(img,0,0,WIDTH,HEIGHT,this);
        gm.paint(g);   //传递画笔给模块
        this.setVisible(true);

    }

    public GameModel getGm() {
        return gm;
    }

    public int getLevel() {
        return level;
    }

    private class TankKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            gm.getMyTank().keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            gm.getMyTank().keyReleased(e);
        }
    }
}
