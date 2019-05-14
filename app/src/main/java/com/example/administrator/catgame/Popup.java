package com.example.administrator.catgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lab228.catgame.R;

import org.w3c.dom.Text;

public class Popup extends AppCompatActivity {

    ImageButton imgCat1, imgCat2,image;
    TextView choiceBox, cat1Text, cat2Text;
    Button continueButton;
    String playerChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        imgCat1 = (ImageButton) findViewById(R.id.cat1);
        imgCat2 = (ImageButton) findViewById(R.id.cat2);

        choiceBox = (TextView) findViewById(R.id.choiceBox);
        cat1Text = (TextView) findViewById(R.id.cat1Text);
        cat2Text = (TextView) findViewById(R.id.cat2Text);
        continueButton = (Button) findViewById(R.id.continueButton);
    }

    public void onClick(View v){
        if(v.getId() == imgCat1.getId()){
            choiceBox.setVisibility(View.VISIBLE);
            continueButton.setVisibility(View.VISIBLE);
            cat1Text.setVisibility(View.INVISIBLE);
            cat2Text.setVisibility(View.INVISIBLE);
            playerChoice = "catOne";
            choiceBox.setText("You have chosen: SHERLOCK");
        }
        else if(v.getId() == imgCat2.getId()){
            choiceBox.setVisibility(View.VISIBLE);
            continueButton.setVisibility(View.VISIBLE);
            cat1Text.setVisibility(View.INVISIBLE);
            cat2Text.setVisibility(View.INVISIBLE);
            playerChoice = "catTwo";
            choiceBox.setText("You have chosen: ZUMI");

        }

    }

    public void buttonOnClick(View v){
        if(v.getId() == continueButton.getId()){
            Intent intent = new Intent(this, MainActivityTicTacToe.class);
            intent.putExtra("PLAYER_CHOICE", playerChoice);
            startActivity(intent);
        }
    }

}