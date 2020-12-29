package TankTest.util;

import TankTest.Enum.WallType;
import TankTest.Model.Level;
import TankTest.Model.wall.*;
import TankTest.jFrame.MapEditor.MapEditorJPanel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 加载地图数据工具类
 */
public class MapIO {
    // 地图数据文件路径
    public final static String DATA_PATH = "profile/map/data/";
    // 地图数据文件后缀
    public final static String DATA_SUFFIX = ".map";

    /**
     * 加载指定名称的地图
     *
     * @param mapName 在"profile/map/data/"路径下的地图名称，不用带后缀
     * @return 一个游戏物体的List集合
     */
    public static List<BrickWall> readMap(String mapName) {
        Properties pros = new Properties();
        List<BrickWall> walls = new ArrayList<>();// 创建总墙块集合
        try {
            File f = new File(DATA_PATH);
            if (!f.exists()) {
                f.mkdirs(); //创建文件夹
                String f1 = String.valueOf(Level.class.getClassLoader().getResourceAsStream(MapIO.DATA_PATH + "level1" + MapIO.DATA_SUFFIX));// 创建地图文件
                String f2 = String.valueOf(Level.class.getClassLoader().getResourceAsStream(MapIO.DATA_PATH + "level2" + MapIO.DATA_SUFFIX));// 创建地图文件
                Level.copyFile(MapIO.DATA_PATH + "level1" + MapIO.DATA_SUFFIX);
                Level.copyFile(MapIO.DATA_PATH + "level2" + MapIO.DATA_SUFFIX);
            }
            pros.load(new FileInputStream(DATA_PATH + mapName + DATA_SUFFIX));// 属性集对象读取地图文件
            String brickStr = (String) pros.get("BrickWall");// 读取地图文件中砖墙名称属性的字符串数据
            String grassStr = (String) pros.get("Grass");// 读取地图文件中草地名称属性的字符串数据
            String riverStr = (String) pros.get("River");// 读取地图文件中河流名称属性的字符串数据
            String ironStr = (String) pros.get("IronWall");// 读取地图文件中铁墙名称属性的字符串数据
            if (brickStr != null) {// 如果读取的砖墙数据不是null
                walls.addAll(readWall(brickStr, WallType.BRICK));// 解析数据，并将数据中解析出的墙块集合添加到总墙块集合中
            }
            if (grassStr != null) {// 如果读取的草地数据不是null
                walls.addAll(readWall(grassStr, WallType.GRASS));// 解析数据，并将数据中解析出的墙块集合添加到总墙块集合中
            }
            if (riverStr != null) {// 如果读取的河流数据不是null
                walls.addAll(readWall(riverStr, WallType.RIVER));// 解析数据，并将数据中解析出的墙块集合添加到总墙块集合中
            }
            if (ironStr != null) {// 如果读取的铁墙数据不是null
                walls.addAll(readWall(ironStr, WallType.IRON));// 解析数据，并将数据中解析出的墙块集合添加到总墙块集合中
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return walls;// 返回总墙块集合
        }
    }

    /**
     * 对从地图配置文件中读取的墙块进行解析，得出坐标
     *
     * @param data 墙块坐标数据字符串
     * @param type 墙块类型
     * @return 墙块集合
     */
    private static List<BrickWall> readWall(String data, WallType type) {
        String walls[] = data.split(";");// 使用“;”分割字符串
        List<BrickWall> objects = new ArrayList<>();// 创建游戏物体，只添加砖块对象
        switch (type) {
            case GRASS:// 如果是草
                for (String s : walls) {
                    String axes[] = s.split(",");// 使用“,”分割字符串
                    // 创建墙块对象，分割的第一个值为横坐标，分割的第二个值为纵坐标
                    objects.add(new Grass(Integer.parseInt(axes[0]), Integer.parseInt(axes[1])));// 在此坐标上创建草对象
                }
                break;
            case RIVER:// 如果是河流
                for (String wStr : walls) {// 遍历分割结果
                    String axes[] = wStr.split(",");// 使用“,”分割字符串
                    // 创建墙块对象，分割的第一个值为横坐标，分割的第二个值为纵坐标
                    objects.add(new River(Integer.parseInt(axes[0]), Integer.parseInt(axes[1])));// 在此坐标上创建河流对象
                }
                break;
            case IRON:// 如果是铁墙
                for (String wStr : walls) {// 遍历分割结果
                    String axes[] = wStr.split(",");// 使用“,”分割字符串
                    // 创建墙块对象，分割的第一个值为横坐标，分割的第二个值为纵坐标
                    objects.add(new IronWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1])));// 在此坐标上创建铁墙对象
                }
                break;
            case BRICK:// 如果是粉墙
                for (String s : walls) {
                    String axes[] = s.split(",");// 使用“,”分割字符串
                    // 创建墙块对象，分割的第一个值为横坐标，分割的第二个值为纵坐标
                    objects.add(new BrickWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1])));// 在此坐标上创建砖墙对象
                }
                break;
            default:
                break;
        }
        return objects;
    }

    private static Boolean writeMap(File file) {
        StringBuffer brickBuffer = new StringBuffer("BrickWall=");//保存所有砖墙的坐标
        StringBuffer grassBuffer = new StringBuffer("Grass=");//保存所有草地的坐标
        StringBuffer ironBuffer = new StringBuffer("IronWall=");//保存所有钢砖的坐标
        StringBuffer riverBuffer = new StringBuffer("River=");//保存所有河流的坐标
        List<BrickWall> walls = MapEditorJPanel.getWalls();//获取到地图编辑器面板中所有墙块的集合
        //遍历集合，判断每个墙块坐标应该追加到哪种类型墙块后面
        for (int i = 0; i < walls.size(); i++) {
            BrickWall w = walls.get(i);//获取到每个墙块对象
            if (w.isLive()) {//如果墙块对象是存活状态
                if (w instanceof Base) {
                    continue;
                } else if (w instanceof Grass) {
                    grassBuffer.append(w.getX() + "," + w.getY() + ";");
                } else if (w instanceof IronWall) {
                    ironBuffer.append(w.getX() + "," + w.getY() + ";");
                } else if (w instanceof River) {
                    riverBuffer.append(w.getX() + "," + w.getY() + ";");
                } else if (w instanceof BrickWall) {//如果是砖墙的对象
                    brickBuffer.append(w.getX() + "," + w.getY() + ";");//追加到砖墙对象坐标的后面
                }
            }
        }
        BufferedWriter writer = null;//新建字符输出流对象
        try {
            writer = new BufferedWriter(new FileWriter(file));//实例化字符输出流对象
            if (!brickBuffer.toString().equals("BrickWall=")) {//判断砖墙对象坐标为不为null，也就是前面有没有在这个StringBuffer后面追加坐标
                writer.write(brickBuffer.toString().toCharArray());//写出对应数据到缓冲区
                writer.newLine();//换行，写其他的墙块
                writer.flush();//刷新缓冲区，写出数据到文件
            }
            if (!grassBuffer.toString().equals("Grass=")) {
                writer.write(grassBuffer.toString().toCharArray());
                writer.newLine();
                writer.flush();
            }
            if (!ironBuffer.toString().equals("IronWall=")) {
                writer.write(ironBuffer.toString().toCharArray());
                writer.newLine();
                writer.flush();
            }
            if (!riverBuffer.toString().equals("River=")) {
                writer.write(riverBuffer.toString().toCharArray());
                writer.newLine();
                writer.flush();
            }
            if (writer != null) {//如果流对象不为空，关闭流
                writer.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Boolean writeMap(String s) {
        File file = new File(DATA_PATH + s + DATA_SUFFIX);
        return writeMap(file);
    }
}
