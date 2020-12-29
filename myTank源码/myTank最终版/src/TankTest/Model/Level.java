package TankTest.Model;

import TankTest.util.MapIO;

import java.io.*;

public class Level {
    private static int count = 0;// 关卡总数

    /**
     * 读取关卡
     */
    private static void readLevel() {
        File f = new File("profile/map/data");
        if (!f.exists()) {
            f.mkdirs(); //创建文件夹
            String f1 = String.valueOf(Level.class.getClassLoader().getResource(MapIO.DATA_PATH + "level1" + MapIO.DATA_SUFFIX));// 创建地图文件
            String f2 = String.valueOf(Level.class.getClassLoader().getResource(MapIO.DATA_PATH + "level2" + MapIO.DATA_SUFFIX));// 创建地图文件
            copyFile(MapIO.DATA_PATH + "level1" + MapIO.DATA_SUFFIX);
            copyFile(MapIO.DATA_PATH + "level2" + MapIO.DATA_SUFFIX);
        } else {
            File fs[] = f.listFiles();// 获取地图文件目录文件夹下的所有文件对象
            count = fs.length;// 将文件数量作为关卡总数
            //System.out.println(count);
            if (count == 0) {// 如果目录下没有任何文件
                File nowFile = new File(MapIO.DATA_PATH + "level1.map");// 创建地图文件目录文件夹
                count++;
                try {
                    nowFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void copyFile(String file) {
        BufferedInputStream fis = null;//新建字符输出流对象
        BufferedOutputStream fos = null;//新建字符输出流对象
        try {
            fis = new BufferedInputStream(Level.class.getClassLoader().getResourceAsStream(file));//实例化字符输出流对象
            fos = new BufferedOutputStream(new FileOutputStream(file));//实例化字符输出流对象
            byte[] ch = new byte[1024];
            int len = 0;
            while ((len = fis.read(ch)) != -1) {
                fos.write(ch, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void deleteLevel(int level) {
        File f = new File(MapIO.DATA_PATH);// 创建地图文件目录文件夹
        File fs[] = f.listFiles();// 获取地图文件目录文件夹下的所有文件对象
        for (int i = 0; i < fs.length; i++) {
            if (fs[i].getName().equals("level" + level + ".map")) {
                fs[i].delete();
                for (int j = i; j < fs.length; j++)
                    fs[j].renameTo(new File("level" + j + ".map"));
                break;
            }
        }
    }

    /**
     * 获取关卡总数
     *
     * @return 当前关卡总数
     */
    public static int getCount() {
        readLevel();
        return count;
    }
}
