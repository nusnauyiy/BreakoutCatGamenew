package com.example.lab228.catgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.catgame.MainActivityTicTacToe;
import com.example.administrator.catgame.Popup;
import com.example.lab228.catgame.Breakout.BreakoutGame;
import com.example.lab228.catgame.triviacat.Main2Activity;

public class Navigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    public void onBreakoutClicked(View v){
        Intent intent = new Intent(this, BreakoutGame.class);
        startActivity(intent);
    //hi
    }

    public void onTTTClicked(View v){
        Intent intent = new Intent(this, Popup.class);
        startActivity(intent);

    }

    public void onTriviaClicked(View v){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
        //hihfhghghghg
    }
}
