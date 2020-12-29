package TankTest.jFrame.Menu;

import TankTest.jFrame.MapEditor.MapEditorJFrame;
import TankTest.jFrame.MapPreView.MapPreViewJFrame;
import TankTest.jFrame.StartGame.StartGameJFrame;
import TankTest.jFrame.StartGame.StartGameJPanel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * 主菜单的JPanel面板，为几个按钮设置监听以及跳转等
 */
public class MenuJPanel extends JPanel {

    public static final String Game_Title = "坦克大战";
    public static final int JFrame_Width = 1600;
    public static final int JFrame_Height = 900;
    public static final int WIDTH = JFrame_Width;
    public static final int HEIGHT = JFrame_Height;
    JButton jb_start = new JButton("开始游戏");
    JButton jb_map = new JButton("预览地图");
    JButton jb_set = new JButton("绘制地图");
    JButton jb_end = new JButton("退出游戏");
    MenuJFrame gameFrame;
    private BufferedImage img;


    public MenuJPanel(MenuJFrame GameFrame) {
        try {
            this.img = ImageIO.read(MenuJPanel.class.getClassLoader().getResourceAsStream("Image/main1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.gameFrame = GameFrame;
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT);
        Font f = new Font("宋体", Font.BOLD, 30);
        jb_start.setBounds(100, 700, 200, 50);
        jb_start.setForeground(Color.WHITE);
        jb_start.setFont(f);
        jb_start.addActionListener(new ActionListener() {//鼠标监听
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.dispose();    //释放开始菜单资源
                StartGameJPanel.INSTANCE = new StartGameJPanel(1);
                new StartGameJFrame();   //点击开始游戏，跳转到开始游戏的界面
            }
        });
        jb_start.addKeyListener(new KeyAdapter() {//键盘监听
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    gameFrame.dispose();
                    StartGameJPanel.INSTANCE = new StartGameJPanel(1);
                    new StartGameJFrame();//按下回车，跳转到开始游戏的界面
                }
            }
        });
        //地图预览
        jb_map.addActionListener(new ActionListener() {//鼠标监听
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.dispose();    //释放开始菜单资源
                new MapPreViewJFrame();
            }
        });
        jb_map.addKeyListener(new KeyAdapter() {//键盘监听
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    gameFrame.dispose();
                    new MapPreViewJFrame();   //按下回车，跳转到预览地图的界面
                }
            }
        });
        //设置地图
        jb_set.addActionListener(new ActionListener() {//鼠标监听
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.dispose();    //释放开始菜单资源
                new MapEditorJFrame();
            }
        });
        jb_set.addKeyListener(new KeyAdapter() {//键盘监听
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    gameFrame.dispose();
                    new MapEditorJFrame();   //按下回车，跳转到预览地图的界面
                }
            }
        });
        jb_end.addActionListener(new ActionListener() {//鼠标监听
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "确认退出？", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        jb_end.addKeyListener(new KeyAdapter() {    //键盘监听
            @Override
            public void keyPressed(KeyEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "确认退出？", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        jb_map.setBounds(500, 700, 200, 50);
        jb_map.setFont(f);
        jb_map.setForeground(Color.WHITE);

        jb_set.setBounds(900, 700, 200, 50);
        jb_set.setFont(f);
        jb_set.setForeground(Color.WHITE);

        jb_end.setBounds(1300, 700, 200, 50);
        jb_end.setFont(f);
        jb_end.setForeground(Color.WHITE);

        this.add(jb_start);
        this.add(jb_map);
        this.add(jb_set);
        this.add(jb_end);
        jb_start.setContentAreaFilled(false);
        jb_map.setContentAreaFilled(false);
        jb_set.setContentAreaFilled(false);
        jb_end.setContentAreaFilled(false);

    }

    /*
     * 每次调repaintComponent，都会重绘一遍组件
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//保留容器中原本组件，就需要调这个方法
        g.drawImage(img, 0, 0, JFrame_Width, JFrame_Height, this);
    }

}
