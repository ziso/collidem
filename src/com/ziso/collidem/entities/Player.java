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
public class Player extends GameObject {

    public Player(Resources res, Point initialPosition) {
        super(res, initialPosition);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.GREEN);
        canvas.drawCircle(position.x, position.y, size / 2, p);
    }
}
