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
import java.util.Random;

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
        player = new Player(getResources(), putOnGrid(new Point((int)getMeasuredWidth() / 2, (int)getMeasuredHeight() / 2)));
    }

    private Point putOnGrid(Point point) {
        int x = Math.round((point.x) / getResources().getInteger(R.integer.cell_size)) * getResources().getInteger(R.integer.cell_size);
        int y = Math.round((point.y) / getResources().getInteger(R.integer.cell_size)) * getResources().getInteger(R.integer.cell_size);
        return new Point(x, y);
    }

    private void generateEnemies() {
        enemies = new ArrayList<Enemy>();
        for (int i = 0 ; i < getResources().getInteger(R.integer.initial_number_of_enemies) ; i++) {
            Point point = getRandomPoint();
            enemies.add(new Enemy(getResources(), putOnGrid(point)));
        }
    }

    /*
    * return a valid random point for placing enemy at the start of a round
    * valid point must be follow min_enemy_distance from the player and be rounded to cell_size
    * */
    private Point getRandomPoint() {
        Random r = new Random();
        int r1 = r.nextInt((int)getMeasuredWidth() / 2 - getResources().getInteger(R.integer.min_enemy_distance));
        int r2 = r.nextInt(2) + 1;
        int x = r1 * r2;

        int r3 = r.nextInt((int)getMeasuredHeight() / 2 - getResources().getInteger(R.integer.min_enemy_distance));
        int r4 = r.nextInt(2) + 1;
        int y = r3 * r4;
        return new Point(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        player.moveTowards(new Point((int)event.getX(), (int)event.getY()));
        moveEnemiesToPlayer();
        checkCollisions();
        this.invalidate();
        return super.onTouchEvent(event);
    }

    private void checkCollisions() {
        for (Enemy enemy : enemies) {
            for (Enemy enemy1 : enemies) {
                if (enemy.getId() != enemy1.getId()) {
                    if (enemy.getPosition().equals(enemy1.getPosition())){
                        enemy.kill();
                        enemy1.kill();
                    }
                }
            }
        }
    }

    private void moveEnemiesToPlayer() {
        for (Enemy enemy : enemies) {
            if(enemy.isAlive()) {
                enemy.moveTowards(player.getPosition());
            }
        }
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
