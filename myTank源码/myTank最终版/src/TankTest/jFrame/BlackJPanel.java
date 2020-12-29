package TankTest.jFrame;

import TankTest.jFrame.StartGame.StartGameJFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @author huang
 * @Description
 * @create 2020-06-19-17:01
 */
public class BlackJPanel extends JPanel {

    String s;

    public BlackJPanel() {
        setBounds(0, 0, 1600, 850);
        setVisible(true);
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", Font.BOLD, 40));
        g.drawString(s, StartGameJFrame.width / 2 - 200, StartGameJFrame.height / 2);
    }
}
