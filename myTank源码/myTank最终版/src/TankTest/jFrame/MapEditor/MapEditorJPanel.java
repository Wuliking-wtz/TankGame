package TankTest.jFrame.MapEditor;

import TankTest.Enum.WallType;
import TankTest.Model.Level;
import TankTest.jFrame.Menu.MenuJFrame;
import TankTest.Model.Map;
import TankTest.Model.wall.*;
import TankTest.util.MapIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static TankTest.util.ResourceMgr.*;

/**
 * 地图编辑器面板
 */
public class MapEditorJPanel extends JPanel implements MouseListener {
    private BufferedImage[] wallImgs = {brickWall, ironWall, river, grass};//四个墙块集合
    private Base base;//基地
    private static List<BrickWall> walls;
    WallType wallType;
    private int level = 1;
    private int count = Level.getCount();//获取当前文件夹下的关卡数

    /**
     * MapIO类将墙块集合写入地图，需要获取这个类里的walls
     *
     * @return
     */
    public static List<BrickWall> getWalls() {
        return walls;
    }

    public MapEditorJPanel(JFrame jFrame) {
        //创建墙体集合
        walls = new ArrayList<>();
        //初始化基地
        base = new Base(MapEditorJFrame.width / 2 - 30, MapEditorJFrame.height - 60);
        initWalls();
        //添加鼠标监听
        JButton save = new JButton("保存");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                count++;
                Boolean b = MapIO.writeMap("level" + count);
                if (b) {
                    JOptionPane.showMessageDialog(null, "保存成功");
                } else {
                    JOptionPane.showMessageDialog(null, "保存失败");
                }
                repaint();
            }
        });
        JButton back = new JButton("返回");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                jFrame.dispose();
                new MenuJFrame().setVisible(true);
            }
        });
        this.add(save);
        this.add(back);
        this.addMouseListener(this);
    }

    private void initWalls() {
        Random r = new Random();
        level = count == 0 ? 1 : r.nextInt(count) + 1;// 随机获取一个关卡
        Map map = new Map(level);
        walls = map.getWalls();//加载当前地图内的数据
        walls.add(base);// 墙块集合添加基地
    }

    @Override
    public void paint(Graphics g) {
        super.setBackground(Color.BLACK);
        super.paint(g);

        g.setColor(Color.ORANGE);

        g.drawString("当前关卡：" + level, 0, 12);
        g.drawString("关卡总数：" + count, 0, 24);
        g.setColor(Color.CYAN);
        // 画出横向线段，参考线
        for (int i = 0; i <= MapEditorJFrame.height; i += 60) {
            g.drawLine(0, i, MapEditorJFrame.width, i);
        }
        // 画出纵向线段
        for (int j = 0; j <= MapEditorJFrame.width; j += 60) {
            //g.drawLine(x1, y1, x2, y2);
            g.drawLine(j, 0, j, MapEditorJFrame.height);
        }
        // 画出几个固定的墙块图
        g.drawImage(wallImgs[0], MapEditorJFrame.width, 0, this);
        g.drawImage(wallImgs[1], MapEditorJFrame.width, 30, this);
        g.drawImage(wallImgs[2], MapEditorJFrame.width, 60, this);
        g.drawImage(wallImgs[3], MapEditorJFrame.width, 90, this);
        // 画一个擦子
        g.setColor(Color.MAGENTA);
        g.drawRect(MapEditorJFrame.width, 120, 30, 30);
        g.setColor(Color.white);    //清空地图
        g.drawRect(MapEditorJFrame.width, 150, 30, 30);
        paintWalls(g);//在地图上画出当前集合中的墙体
    }

    private void paintWalls(Graphics g) {
        if (walls == null)
            return;
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

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point clickedPoint = e.getPoint();
        if (clickedPoint.x >= MapEditorJFrame.width && clickedPoint.y <= 180) {
            if (clickedPoint.y > 0 && clickedPoint.y < 30) {
                wallType = WallType.BRICK;
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            } else if (clickedPoint.y > 30 && clickedPoint.y < 60) {
                wallType = WallType.IRON;
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            } else if (clickedPoint.y > 60 && clickedPoint.y < 90) {
                wallType = WallType.RIVER;
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            } else if (clickedPoint.y > 90 && clickedPoint.y < 120) {
                wallType = WallType.GRASS;
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            } else if (clickedPoint.y > 120 && clickedPoint.y < 150) {
                wallType = null;
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else if (clickedPoint.y > 150 && clickedPoint.y < 180) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                try {
                    Thread.sleep(125);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                walls.clear();
                walls.add(base);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point p = new Point();
        p = e.getPoint();//获取到鼠标的当前释放点
        p = new Point((p.x - p.x % 30), (p.y - p.y % 30));//将鼠标的当前按下点格式化为30的倍数即块的左上角，因为墙块是30x30的
        //不能在基地画
        Point base1 = new Point(MapEditorJFrame.width / 2 - 30, MapEditorJFrame.height - 60);
        Point base2 = new Point(MapEditorJFrame.width / 2, MapEditorJFrame.height - 60);
        Point base3 = new Point(MapEditorJFrame.width / 2 - 30, MapEditorJFrame.height - 30);
        Point base4 = new Point(MapEditorJFrame.width / 2, MapEditorJFrame.height - 30);
        //不能在三个坦克出生点画
        Point base5 = new Point(0, 0);
        Point base6 = new Point(0, 30);
        Point base7 = new Point(30, 30);
        Point base8 = new Point(30, 0);

        Point base9 = new Point(MapEditorJFrame.width / 2 - 30, 0);
        Point base10 = new Point(MapEditorJFrame.width / 2, 0);
        Point base11 = new Point(MapEditorJFrame.width / 2 - 30, 30);
        Point base12 = new Point(MapEditorJFrame.width / 2, 30);

        Point base13 = new Point(MapEditorJFrame.width - 60, 0);
        Point base14 = new Point(MapEditorJFrame.width - 30, 0);
        Point base15 = new Point(MapEditorJFrame.width - 60, 30);
        Point base16 = new Point(MapEditorJFrame.width - 30, 30);

        if ((p.x < MapEditorJFrame.width) && !p.equals(base1) && !p.equals(base2) && !p.equals(base3) && !p.equals(base4) &&
                !p.equals(base5) && !p.equals(base6) && !p.equals(base7) && !p.equals(base8) &&
                !p.equals(base9) && !p.equals(base10) && !p.equals(base11) && !p.equals(base12) &&
                !p.equals(base13) && !p.equals(base14) && !p.equals(base15) && !p.equals(base16)) {
            //遍历集合，判断释放点是否有墙块，如果有就擦掉
            for (int i = 0; i < walls.size(); i++) {
                BrickWall w = walls.get(i);
                //集合中已经有基地了，为了防止把基地擦除，需要加上!w.equals(base)的判断
                if (w.getX() >= MapEditorJFrame.width || (w.getX() == p.x && w.getY() == p.y && !w.equals(base))) {
                    walls.remove(w);
                    repaint();
                }
            }
            if (wallType != null) {//如果墙块类型不为空，添加墙块
                addWall(wallType, p);
            }
        }
    }

    /**
     * 当鼠标左键在地图上松开时，判断当前墙块类型，往地图上进行墙块的增删
     *
     * @param type
     * @param p
     */
    private void addWall(WallType type, Point p) {
        switch (type) {
            case GRASS:
                Grass grass = new Grass(p.x, p.y);
                for (int i = 0; i < walls.size(); i++) {
                    BrickWall w = walls.get(i);
                    if (w.equals(grass)) {
                        walls.remove(w);
                    }
                }
                walls.add(grass);
                break;
            case IRON:
                IronWall iron = new IronWall(p.x, p.y);
                for (int i = 0; i < walls.size(); i++) {
                    BrickWall w = walls.get(i);
                    if (w.equals(iron)) {
                        walls.remove(w);
                    }
                }
                walls.add(iron);
                break;
            case RIVER:
                River river = new River(p.x, p.y);
                for (int i = 0; i < walls.size(); i++) {
                    BrickWall w = walls.get(i);
                    if (w.equals(river)) {
                        walls.remove(w);
                    }
                }
                walls.add(river);
                break;
            case BASE:
                break;
            case BRICK:
                BrickWall brickWall = new BrickWall(p.x, p.y);
                for (int i = 0; i < walls.size(); i++) {
                    BrickWall w = walls.get(i);
                    if (w.equals(brickWall)) {
                        walls.remove(w);
                    }
                }
                walls.add(brickWall);
                break;
            default:
                break;
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
