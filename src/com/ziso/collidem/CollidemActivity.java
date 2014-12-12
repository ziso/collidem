package com.ziso.collidem;

import android.app.Activity;
import android.os.Bundle;

public class CollidemActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameBoard board = new GameBoard(this);
        setContentView(board);
    }
}
