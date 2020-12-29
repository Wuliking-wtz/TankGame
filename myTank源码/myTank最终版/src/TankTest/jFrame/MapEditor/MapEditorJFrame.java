package TankTest.jFrame.MapEditor;

import TankTest.jFrame.Menu.MenuJFrame;
import TankTest.util.ProperMgr;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 地图设置功能的JFrame窗体，内嵌JPanel面板，在MapEditorPanel类中
 */
public class MapEditorJFrame {
    public static final int width = Integer.parseInt(ProperMgr.get("gameWidth"));
    public static final int height = Integer.parseInt(ProperMgr.get("gameHeight"));

    public MapEditorJFrame() {
        JFrame jFrame = new JFrame();
        //宽度是60的倍数，但是要多30来放置右边的选择框，高度-30是60的倍数，减去的30是JFrame窗体的上边距
        jFrame.setBounds(150, 100, width + 30, height + 30);
        jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.requestFocus();

        jFrame.setContentPane(new MapEditorJPanel(jFrame));
        jFrame.setVisible(true);

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "确认回到主页面？", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    jFrame.dispose();
                    new MenuJFrame().setVisible(true);
                }
            }
        });
    }
}
