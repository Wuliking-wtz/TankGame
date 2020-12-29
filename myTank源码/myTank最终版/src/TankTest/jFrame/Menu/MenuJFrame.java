package TankTest.jFrame.Menu;

import TankTest.util.ProperMgr;

import java.awt.event.*;

import javax.swing.*;

/*
 * 游戏主窗口
 */
public class MenuJFrame extends JFrame {

    /*游戏窗口相关*/
    public static final String Game_Title = "坦克大战";
    public static final int JFrame_Width = Integer.parseInt(ProperMgr.get("gameWidth"));
    public static final int JFrame_Height = Integer.parseInt(ProperMgr.get("gameHeight"));
    private MenuJPanel tc;//在此类传递按键事件时候使用到

    /*
     * 窗口进行初始化
     */
    public MenuJFrame() {
        super(Game_Title);
        initFrame();
    }

    // 属性进行初始化
    private void initFrame() {
        this.setSize(JFrame_Width, JFrame_Height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setResizable(false); // 窗口大小不可变
        this.setLayout(null /*new BorderLayout()*/);
        Menu();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "确认退出？", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
    }


    /*
     *绘制菜单状态下的内容
     */
    private void Menu() {
        /*"开始游戏","地图绘制","游戏设置","退出游戏",};*/
        tc = new MenuJPanel(this);//这是JPanel面板
        this.getContentPane().add(tc); // 把JPanel添加到JFrame容器里
    }

}

	
