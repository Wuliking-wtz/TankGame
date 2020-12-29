package TankTest.jFrame.MapPreView;

import TankTest.Model.Level;
import TankTest.jFrame.Menu.MenuJFrame;
import TankTest.Model.Map;
import TankTest.Model.wall.Base;
import TankTest.Model.wall.BrickWall;
import TankTest.jFrame.MapEditor.MapEditorJFrame;
import TankTest.jFrame.StartGame.StartGameJPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class MapPreViewJPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    int level = 1;
    private List<BrickWall> walls;
    private Base base;
    private Graphics gra;
    private int count = 0;
    private JFrame frame;
    private JDialog jDialog;
    private BufferedImage img;

    public MapPreViewJPanel(JFrame frame) {
        try {
            this.img = ImageIO.read(MapPreViewJPanel.class.getClassLoader().getResourceAsStream("Image/游戏背景.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.frame = frame;
        //初始化基地
        base = new Base(MapEditorJFrame.width / 2 - 30, MapEditorJFrame.height - 60);
        initWalls();

        //初始化地图

        JButton levelReduce = new JButton("上一关");
        levelReduce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                level--;
                if (level == 0) {
                    level = count;
                }
                initWalls();
                repaint();
            }
        });
        JButton levelPlus = new JButton("下一关");
        levelPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                level++;
                if (level > count) {
                    level = 1;
                }
                initWalls();
                repaint();
            }
        });
        addJDialog();
        JButton delete = new JButton("删除");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                jDialog.setVisible(true);
                Level.deleteLevel(level);
            }
        });
        JButton back = new JButton("返回");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                new MenuJFrame().setVisible(true);
                frame.dispose();
            }
        });

        this.add(back);
        this.add(levelReduce);
        this.add(levelPlus);
        this.add(delete);
    }

    private void addJDialog() {
        jDialog = new JDialog(frame, "提示", true);
        jDialog.setLayout(null);
        jDialog.setBounds(800, 400, 400, 200);
        JLabel jLabel = new JLabel("删除成功！");
        jLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        jLabel.setBounds(150, 20, 100, 50);
        jDialog.add(jLabel);
        JButton inside = new JButton("确认");
        inside.setBounds(150, 80, 80, 50);
        inside.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MenuJFrame().setVisible(true);
            }
        });
        jDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new MenuJFrame().setVisible(true);
            }
        });
        jDialog.add(inside);
        jDialog.setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        //super.setBackground(Color.LIGHT_GRAY);
        super.paintComponent(g);
        //画出背景图片
        g.drawImage(img,0,0,MapPreViewJFrame.width,MapPreViewJFrame.height,this);
        //画出地图
        paintWalls(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        g.drawString("当前关卡：" + level, 0, 22);
        g.drawString("关卡总数：" + count, 0, 48);
    }

    /**
     * 绘制墙块
     */
    private void paintWalls(Graphics g) {
        for (int i = 0; i < walls.size(); i++) {// 循环遍历墙块集合
            BrickWall w = walls.get(i);// 获取墙块对象
            if (w.getX() >= MapEditorJFrame.width) {
                w.die(1);    //设置成凋零状态，防止进入下面的循环
                walls.remove(w);
            }
            if (w.isLive() && (w.getX() < MapEditorJFrame.width)) {// 如果墙块有效
                g.drawImage(w.getImage(), w.getX(), w.getY(), this);// 绘制墙块
            } else {// 如果墙块无效
                walls.remove(i);// 在集合中刪除此墙块
                i--;// 循环变量-1，保证下次循环i的值不会变成i+1，以便有效遍历集合，且防止下标越界
            }
        }
    }

    public void initWalls() {
        Map map = new Map(level);// 获取当前关卡的地图对象
        walls = map.getWalls();
        count = Level.getCount();
        walls.add(base);// 墙块集合添加基地
    }
}
