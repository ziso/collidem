package com.ziso.collidem.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.ziso.collidem.R;

/**
 * User: zisovitc
 * Date: 12/10/2014
 * Time: 2:46 PM
 */
public class GameObject {
    private int size;
    protected Bitmap bitmap;
    Rect position;

    public GameObject(int size) {
        this.size = R.integer.cell_size;
        position = new Rect(0, 0, size, size);
    }

    public void onDraw(Canvas canvas){
//        canvas.drawBitmap(bitmap, null, position, new Paint());
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.WHITE);
        canvas.drawCircle(position.left, position.top, size, p);
    };

    public void setPosition(int x, int y) {
        position.left = x;
        position.top = y;
    }
}
