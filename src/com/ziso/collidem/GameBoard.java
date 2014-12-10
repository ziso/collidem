package com.ziso.collidem;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import com.ziso.collidem.entities.Enemy;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zisovitc
 * Date: 12/10/2014
 * Time: 2:40 PM
 */
public class GameBoard extends View{
    Canvas canvas;
    List<Enemy> enemies;
    public GameBoard(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(enemies != null) {
            drawEnemies();
        }
    }

    private void drawEnemies() {
        for (Enemy enemy : enemies) {
            enemy.onDraw(canvas);
        }
    }

    private void createRound() {
        generateEnemies();
    }

    private void generateEnemies() {
        enemies = new ArrayList<Enemy>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        enemy = new Enemy(20, getResources());
        enemy.setPosition((int)event.getX(), (int)event.getY());
        this.invalidate();
        return super.onTouchEvent(event);
    }
}
