package com.ziso.collidem.entities;

import android.graphics.Canvas;
import android.graphics.Point;

/**
 * User: zisovitc
 * Date: 12/10/2014
 * Time: 2:46 PM
 */
public abstract class GameObject {
    protected int size;
    protected Point position;

    private enum Direction {LEF, RIGHT, UP, DOWN, LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN};

    public GameObject(int x, int y) {
        this.size = 20;
        position = new Point(x, y);
    }

    public abstract void onDraw(Canvas canvas);

    public void setPosition(int x, int y) {
        position.set(x, y);
    }

    public void moveTowards(Point p) {
        Direction direction = getDirectionToPoint(p);

    }

    private Direction getDirectionToPoint(Point p) {
        float angle = getAngle(position, p);
        if (angle >= 337.5 || angle < 22.5) {
            return Direction.UP;
        } else if (angle >= 22.5 && angle < 67.5) {
            return Direction.UP;
        } else if (angle >= 67.5 && angle < 112.5) {
            return Direction.UP;
        } else if (angle >= 112.5 && angle < 157.5) {
            return Direction.UP;
        } else if (angle >= 157.5 && angle < 202.5) {
            return Direction.UP;
        } else if (angle >= 202.5 && angle < 247.5) {
            return Direction.UP;
        } else if (angle >= 157.5 && angle < 180) {
            return Direction.UP;
        } else if (angle >= 180 && angle < 202.5) {
            return Direction.UP;
        }
        return null;
    }

    protected float getAngle(Point p1, Point p2) {
        float angle = (float) Math.toDegrees(Math.atan2(p1.y - p2.y, p1.x - p2.x)) - 90;

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }
}
