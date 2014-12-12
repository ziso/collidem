package com.ziso.collidem.entities;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import com.ziso.collidem.R;

import java.util.UUID;

/**
 * User: zisovitc
 * Date: 12/10/2014
 * Time: 2:46 PM
 */
public abstract class GameObject {
    protected int size;
    protected Point position;
    protected Resources resources;

    public UUID getId() {
        return id;
    }

    private UUID id;

    public Point getPosition() {
        return position;
    }

    private enum Direction {LEF, RIGHT, UP, DOWN, LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN};

    public GameObject(Resources resources, Point initialPosition) {
        this.resources = resources;
        this.size = resources.getInteger(R.integer.cell_size);
        this.position = initialPosition;
        this.id = UUID.randomUUID();;
    }

    public abstract void onDraw(Canvas canvas);

    public void setPosition(Point position) {
        this.position = position;
    }

    public void moveTowards(Point p) {
        Direction direction = getDirectionToPoint(p);
        switch (direction) {
            case UP:
                setPosition(new Point(position.x, position.y - size));
                break;
            case RIGHT_UP:
                setPosition(new Point(position.x + size, position.y - size));
                break;
            case RIGHT:
                setPosition(new Point(position.x + size, position.y));
                break;
            case RIGHT_DOWN:
                setPosition(new Point(position.x + size, position.y + size));
                break;
            case DOWN:
                setPosition(new Point(position.x, position.y + size));
                break;
            case LEFT_DOWN:
                setPosition(new Point(position.x - size, position.y + size));
                break;
            case LEF:
                setPosition(new Point(position.x - size, position.y));
                break;
            case LEFT_UP:
                setPosition(new Point(position.x - size, position.y - size));
                break;
        }
    }

    private Direction getDirectionToPoint(Point p) {
        float angle = getAngle(position, p);
        if (angle >= 337.5 || angle < 22.5) {
            return Direction.UP;
        } else if (angle >= 22.5 && angle < 67.5) {
            return Direction.RIGHT_UP;
        } else if (angle >= 67.5 && angle < 112.5) {
            return Direction.RIGHT;
        } else if (angle >= 112.5 && angle < 157.5) {
            return Direction.RIGHT_DOWN;
        } else if (angle >= 157.5 && angle < 202.5) {
            return Direction.DOWN;
        } else if (angle >= 202.5 && angle < 247.5) {
            return Direction.LEFT_DOWN;
        } else if (angle >= 247.5 && angle < 292.5) {
            return Direction.LEF;
        } else if (angle >= 292.5 && angle < 337.5) {
            return Direction.LEFT_UP;
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
