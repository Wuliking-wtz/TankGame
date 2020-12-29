package TankTest.Model;

import TankTest.Model.wall.BrickWall;
import TankTest.util.MapIO;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取文件中砖块数据的地图类，
 */
public class Map {
    public static List<BrickWall> walls = new ArrayList<>();// 地图中所有墙块的集合


    public Map(String level) {
        walls.clear();// 墙块集合清空
        walls.addAll(MapIO.readMap(level));// 读取指定关卡的墙块集合
    }

    public Map(int level) {
        new Map("level"+String.valueOf(level));// 调用重载方法
    }

    /**
     * 获取地图对象中的所有墙块
     * @return 墙块集合
     */
    public List<BrickWall> getWalls() {
        return walls;
    }
}
