package com.example.administrator.catgame;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab228.catgame.R;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


import static android.widget.Toast.LENGTH_SHORT;



public class MainActivityTicTacToe extends AppCompatActivity {

    ImageButton imgButton1, imgButton2, imgButton3, imgButton4, imgButton5, imgButton6, imgButton7, imgButton8, imgButton9;
    TextView gameMessage;
    final int HUMAN = 1, COMP = 2, TIED = 3;
    Boolean playing;
    int turnTrack;
    Grid[][] board;
    //String playerChoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tic_tac_toe);


        imgButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imgButton1.setBackgroundDrawable(null);
        imgButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imgButton2.setBackgroundDrawable(null);
        imgButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imgButton3.setBackgroundDrawable(null);
        imgButton4 = (ImageButton) findViewById(R.id.imageButton4);
        imgButton4.setBackgroundDrawable(null);
        imgButton5 = (ImageButton) findViewById(R.id.imageButton5);
        imgButton5.setBackgroundDrawable(null);
        imgButton6 = (ImageButton) findViewById(R.id.imageButton6);
        imgButton6.setBackgroundDrawable(null);
        imgButton7 = (ImageButton) findViewById(R.id.imageButton7);
        imgButton7.setBackgroundDrawable(null);
        imgButton8 = (ImageButton) findViewById(R.id.imageButton8);
        imgButton8.setBackgroundDrawable(null);
        imgButton9 = (ImageButton) findViewById(R.id.imageButton9);
        imgButton9.setBackgroundDrawable(null);

        gameMessage = (TextView) findViewById(R.id.gameMessage);

        //initialize board
        board = new Grid[3][3];
        board[0][0] = new Grid(imgButton1);
        board[0][1] = new Grid(imgButton2);
        board[0][2] = new Grid(imgButton3);
        board[1][0] = new Grid(imgButton4);
        board[1][1] = new Grid(imgButton5);
        board[1][2] = new Grid(imgButton6);
        board[2][0] = new Grid(imgButton7);
        board[2][1] = new Grid(imgButton8);
        board[2][2] = new Grid(imgButton9);

        //initialize other values
        playing = true;
        turnTrack = 0;


    }



    public String getPlayerChoice(){
        String playerChoice = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            playerChoice = extras.getString("PLAYER_CHOICE");
            //The key argument here must match that used in the other activity
        }
        return playerChoice;
    }


    public void Move(View v){
        if(!playing){
            return;
        }

        // check every grid
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++)
                if ((ImageButton) v == board[i][j].getImage()) {


                    //if move was valid
                    if(board[i][j].setStatus(HUMAN)){
                        String playerChoice = getPlayerChoice();
                        System.out.println(playerChoice);
                        if(playerChoice.equalsIgnoreCase("catOne")){
                            board[i][j].setImage(R.drawable.mycat);
                        }
                        else{
                            board[i][j].setImage(R.drawable.leiacat);
                        }

                        //if human does not win after this move, computer play
                        if(checkWin()==0)
                        {
                            if(!computerMove()) {   //if the computer cannot play a winning/blocking move, move randomly
                                randomMove();
                            }
                        }
                    };
                }
        }
        conclude(checkWin());
    }

    public void restartGame(View view)
    {
        for(Grid[] i: board){
            for(Grid j: i){
                j.reset();
            }
        }
        playing = true;
    }

    public boolean checkBoardFill() {
        for(Grid[] i: board){
            for(Grid j: i){
                if(j.getStatus() == 0){
                    return false;
                }
            }
        }
        return true;
    }


    public int checkWin(){
        //horizontal check
        for(int i = 0; i < board.length; i++) {
            if(board[i][0].getStatus() != 0 && board[i][0].getStatus() == board[i][1].getStatus() && board[i][1].getStatus() == board[i][2].getStatus()){
                return board[i][0].getStatus();
            }
        }

        //vertical check
        for(int i = 0; i < board.length; i++) {
            if(board[0][i].getStatus() != 0 && board[0][i].getStatus() == board[1][i].getStatus() && board[1][i].getStatus() == board[2][i].getStatus()){
                return board[0][i].getStatus();
            }
        }

        //diagonal check
        if(board[1][1].getStatus() != 0) {
            if (board[0][0].getStatus() == board[1][1].getStatus() && board[1][1].getStatus() == board[2][2].getStatus()) {
                return board[1][1].getStatus();
            }
            else if (board[0][2].getStatus() == board[1][1].getStatus() && board[1][1].getStatus() == board[2][0].getStatus()) {
                return board[1][1].getStatus();
            }
        }

        //when all conditions above not satisfied, it might be tie
        if(checkBoardFill()){
            return TIED;
        }
        return 0;
    }

    public boolean randomMove(){
        if(checkBoardFill()){
            return false;
        }
        Random rand = new Random();
        int r = rand.nextInt(3);
        int c = rand.nextInt(3);
        if (board[r][c].setStatus(COMP)){
            board[r][c].setImage(R.drawable.piggy);
            turnTrack++;
        }
        else{
            randomMove();
        }
        return true;
    }


    public boolean computerMove(){
        if(checkBoardFill()){
            return false;
        }
        if(!computerAnalysis(COMP)){            //if there is no winning move for the computer
            return computerAnalysis(HUMAN);     //return whether computer played a move to block player
        }
        return true;
    }

    //parameter = comp: check for win
    //parameter = human: check to block
    //return true: a move is made
    //return false: no move is made
    private boolean computerAnalysis(int side){
        int potentMove;
        for(int i = 0; i< board.length; i++){
            potentMove = checkRow(i, side);
            if(potentMove >= 0){
                if (board[i][potentMove].setStatus(COMP)) {
                    board[i][potentMove].setImage(R.drawable.piggy);
                    turnTrack++;
                    return true;
                }
            }

            potentMove = checkCol(i, side);
            if(potentMove >= 0){
                if (board[potentMove][i].setStatus(COMP)) {
                    board[potentMove][i].setImage(R.drawable.piggy);
                    turnTrack++;
                    return true;
                }
            }
        }
        potentMove = checkDia(1, side);
        if(potentMove >= 0){

            if (board[potentMove][potentMove].setStatus(COMP)) {
                board[potentMove][potentMove].setImage(R.drawable.piggy);
                turnTrack++;
                return true;
            }
        }
        potentMove = checkDia(2, side);
        if(potentMove >= 0){
            if (board[2-potentMove][potentMove].setStatus(COMP)) {
                board[2-potentMove][potentMove].setImage(R.drawable.piggy);
                turnTrack++;
                return true;
            }
        }
        return false;
    }

    //check whether there's a move on the specified row
    private int checkRow(int rowNum, int userStatus){
        int count = 0;
        int zeroCount = 0;
        int[] line = new int[board.length];
        for(int i = 0; i< board.length; i++){
            line[i] = board[rowNum][i].getStatus();
            if(line[i] == userStatus){
                count ++;
            }
            else if(line[i] ==0){
                zeroCount ++;
            }
        }
        if(count == 2 && zeroCount == 1){

            return checkEmpty(line);
        }
        return -1;
    }

    //check whether there's a move on the specified column
    private int checkCol(int colNum, int userStatus){
        int count = 0;
        int zeroCount = 0;
        int[] line = new int[board.length];
        for(int i = 0; i< board.length; i++){
            line[i] = board[i][colNum].getStatus();
            if(line[i] == userStatus){
                count ++;
            }
            else if(line[i] ==0){
                zeroCount ++;
            }
        }
        if(count == 2 && zeroCount == 1){

            return checkEmpty(line);
        }
        return -1;
    }

    //dia1 = 00, 11, 22
    //dia2 = 20, 11, 02
    //check whether there's a winning/blocking move on the diagonal
    private int checkDia(int diaNum, int userStatus){
        int count = 0;
        int zeroCount = 0;
        int[] line = new int[board.length];
        if(diaNum == 1) {
            for (int i = 0; i < board.length; i++) {
                line[i] = board[i][i].getStatus();
                if (line[i] == userStatus) {
                    count++;
                }
                else if(line[i] ==0){
                    zeroCount ++;
                }
            }
        }
        else if(diaNum == 2){
            for (int i = 0; i < board.length; i++) {
                line[i] = board[2-i][i].getStatus();
                if (board[2-i][i].getStatus() == userStatus) {
                    count++;
                }
                else if(line[i] ==0){
                    zeroCount ++;
                }
            }
        }
        if(count == 2 && zeroCount == 1){

            return checkEmpty(line);
        }
        return -1;
    }

    //check which spot is empty in a line (which is granted to have two of the same values and an empty spot)
    private int checkEmpty(int[] line){
        if(line[0] == line[1]){
            return 2;
        }
        if(line[0] == line[2]){
            return 1;
        }
        if(line[1] == line[2]){
            return 0;
        }
        return -1;
    }

    //display winning messages
    public void conclude(int winner){
        String message = "";

        switch (winner){
            case HUMAN:
                playing = false;
                message = "You won!";
                break;
            case COMP:
                playing = false;
                message = "Sorry, you lost!";
                break;
            case TIED:
                playing = false;
                message = "Tie!";
                break;
            default:
                return;
        }
        gameMessage.setText(message);

    }

}