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
    private enum Direction {LEF, RIGHT, UP, DOWN, LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN};

    protected int speed;
    protected int size;
    protected Point position;
    protected Resources resources;
    private UUID id;
    protected boolean isAlive;

    public UUID getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public GameObject(Resources resources, Point initialPosition, int speed) {
        this.resources = resources;
        this.size = resources.getInteger(R.integer.cell_size);
        this.speed = speed;
        this.position = initialPosition;
        this.id = UUID.randomUUID();
        isAlive = true;
    }

    public abstract void onDraw(Canvas canvas);

    public void setPosition(Point position) {
        this.position = position;
    }

    public void moveTowards(Point p) {
        if (p.x > position.x) {
            position.x += size * speed;
        } else if (p.x < position.x) {
            position.x -= size * speed;
        }

        if (p.y > position.y) {
            position.y += size * speed;
        } else if (p.y < position.y) {
            position.y -= size * speed;
        }
    }

    protected float getAngle(Point p1, Point p2) {
        float angle = (float) Math.toDegrees(Math.atan2(p1.y - p2.y, p1.x - p2.x)) - 90;

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    public void kill() {
        isAlive = false;
    }

    public void revive() {
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

}
