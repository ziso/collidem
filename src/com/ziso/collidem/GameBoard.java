package com.ziso.collidem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import com.ziso.collidem.entities.Enemy;
import com.ziso.collidem.entities.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zisovitc
 * Date: 12/10/2014
 * Time: 2:40 PM
 */
public class GameBoard extends View{
    private List<Enemy> enemies;
    private Player player;

    public GameBoard(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(enemies != null) {
            drawEnemies(canvas);
        }

        if (player != null) {
            player.onDraw(canvas);
        }
    }

    private void drawEnemies(Canvas canvas) {
        for (Enemy enemy : enemies) {
            enemy.onDraw(canvas);
        }
    }

    private void createRound() {
        generateEnemies();
        generatePlayer();
        this.invalidate();
    }

    private void generatePlayer() {
        player = new Player(getResources(), (int)getMeasuredWidth() / 2, (int)getMeasuredHeight() / 2);
    }

    private void generateEnemies() {
        enemies = new ArrayList<Enemy>();
        int x, y;
        x = 0;
        y = 0;
        for (int i = 0 ; i < 10 ; i++) {
            enemies.add(new Enemy(getResources(), x, y));
            x +=60;
            if (x > 200) {
                x = 0;
                y += 60;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        player.moveTowards(new Point((int)event.getX(), (int)event.getY()));

        this.invalidate();
        return super.onTouchEvent(event);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        player.setPosition((int)getMeasuredWidth() / 2, (int)getMeasuredHeight() / 2);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        createRound();
    }
}
