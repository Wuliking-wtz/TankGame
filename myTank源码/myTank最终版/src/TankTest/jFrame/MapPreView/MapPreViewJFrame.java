package TankTest.jFrame.MapPreView;

import TankTest.jFrame.Menu.MenuJFrame;
import TankTest.util.ProperMgr;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MapPreViewJFrame {
    public static final int width = Integer.parseInt(ProperMgr.get("gameWidth"));
    public static final int height = Integer.parseInt(ProperMgr.get("gameHeight"));

    public MapPreViewJFrame() {
        JFrame jFrame = new JFrame("地图预览");
        jFrame.setBounds(150, 100, width, height + 30);
        jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.requestFocus();

        jFrame.setContentPane(new MapPreViewJPanel(jFrame));
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
