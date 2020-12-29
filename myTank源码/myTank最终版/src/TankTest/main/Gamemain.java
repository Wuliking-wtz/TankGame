package TankTest.main;

import TankTest.jFrame.Menu.MenuJFrame;
import TankTest.util.Audio;

/**
 * 程序入口
 */
public class Gamemain {
    public static void main(String[] args) {
        new MenuJFrame().setVisible(true);
        new Thread(()-> new Audio("Audio/start.wav").play()).start();
    }
}
