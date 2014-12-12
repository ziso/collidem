package com.ziso.collidem.entities;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * User: zisovitc
 * Date: 12/9/2014
 * Time: 6:52 PM
 */
public class Enemy extends GameObject {

    private boolean isAlive;

    public Enemy(Resources res, Point initialPosition) {
        super(res, initialPosition);
        isAlive = true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        if(isAlive == true) {
            p.setColor(Color.WHITE);
        } else {
            p.setColor(Color.DKGRAY);
        }
        canvas.drawCircle(position.x, position.y, size / 2, p);
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
