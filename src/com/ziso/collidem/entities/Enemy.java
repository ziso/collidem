package com.ziso.collidem.entities;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import com.ziso.collidem.R;

/**
 * User: zisovitc
 * Date: 12/9/2014
 * Time: 6:52 PM
 */
public class Enemy extends GameObject {

    public Enemy(int size, Resources res) {
        super(size);
        this.bitmap = BitmapFactory.decodeResource(res, R.drawable.enemy);
    }
}
