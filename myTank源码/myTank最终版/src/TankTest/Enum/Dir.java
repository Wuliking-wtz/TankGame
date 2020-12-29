package TankTest.Enum;

import java.util.Random;

/**
 * @author huang
 * @Description
 * @create 2020-06-08-15:45
 */
public enum Dir {
    L, U, R, D,
    RU,   //右上角
    RD,    //右下角
    LU,   //左上角
    LD;   //左下角
    private static Random r = new Random();

    public static Dir randomDir() {
        return values()[r.nextInt(values().length)];
    }
}
