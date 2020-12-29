package TankTest.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProperMgr {

    private static Properties pros;

    static {
        pros = new Properties();
        try {
            pros = new Properties();
            pros.load(ProperMgr.class.getClassLoader().getResourceAsStream("profile/config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return (String) pros.get(key);
    }
}
