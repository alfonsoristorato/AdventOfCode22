package Day17;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.awt.*;

public class Rock {

    private int maxY;
    private int minY;
    private int maxX;
    private int minX;
    List<Point> points = new ArrayList<>();

    public Rock() {
    }

    public Rock(int maxY, int minY, int maxX, int minX, List<Point> points) {
        this.maxY = maxY;
        this.minY = minY;
        this.maxX = maxX;
        this.minX = minX;
        this.points = points;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public void moveRight(Set<Point> rocksStacked) {
        for (Point point : points) {
            if (rocksStacked.contains(new Point(point.x + 1, point.y))) {
                return;
            }
        }
        for (Point point : points) {
            point.x += 1;
        }

        this.maxX += 1;
        this.minX += 1;
    }

    public void moveLeft(Set<Point> rocksStacked) {
        for (Point point : points) {
            if (rocksStacked.contains(new Point(point.x - 1, point.y))) {
                return;
            }
        }
        for (Point point : points) {
            point.x -= 1;
        }
        this.maxX -= 1;
        this.minX -= 1;
    }

    public void moveDown(Set<Point> rocksStacked) {
        for (Point point : points) {
            if (rocksStacked.contains(new Point(point.x, point.y + 1))) {
                return;
            }
            if (point.y + 1 == 0) {
                return;
            }
        }
        for (Point point : points) {
            point.y += 1;
        }
        this.maxY += 1;
        this.minY += 1;
    }

    public List<Point> getPoints() {
        return points;
    }

}
