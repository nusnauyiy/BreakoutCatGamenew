package com.example.lab228.catgame.triviacat;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab228.catgame.R;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {


    String textFile, theQuestion, theAnswer, blankAnswer = "";
    int random, lives;

    ArrayList<String> questionList;
    ArrayList<String> answerList;

    //GifImageView gif;
    ImageView heart1, heart2, heart3, heart4, heart5, heart6, heart7, heart8, heart9;

    TextView phraseHolder, questionHint, counterView;
    Button guessLetterButton, guessPhraseButton;
    Button btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ, btnK, btnL, btnM, btnN, btnO, btnP, btnQ, btnR, btnS, btnT, btnU, btnV, btnW, btnX, btnY, btnZ;

    ArrayList<String> lettersGuessed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questionList = new ArrayList<String>();
        answerList = new ArrayList<String>();
        phraseHolder = (TextView) findViewById(R.id.phraseHolder);
        questionHint = (TextView) findViewById(R.id.questionHint);
        counterView = (TextView) findViewById(R.id.counterView);
        guessLetterButton = (Button) findViewById(R.id.guessLetterButton);
        guessPhraseButton = (Button) findViewById(R.id.guessPhraseButton);

        lettersGuessed = new ArrayList<String>();

        heart1 = (ImageView) findViewById(R.id.heart1);
        heart2 = (ImageView) findViewById(R.id.heart2);
        heart3 = (ImageView) findViewById(R.id.heart3);
        heart4 = (ImageView) findViewById(R.id.heart4);
        heart5 = (ImageView) findViewById(R.id.heart5);
        heart6 = (ImageView) findViewById(R.id.heart6);
        heart7 = (ImageView) findViewById(R.id.heart7);
        heart8 = (ImageView) findViewById(R.id.heart8);
        heart9 = (ImageView) findViewById(R.id.heart9);
        Animation animation = AnimationUtils.loadAnimation(Game.this, R.anim.rotate);
        heart1.startAnimation(animation);
        heart2.startAnimation(animation);
        heart3.startAnimation(animation);
        heart4.startAnimation(animation);
        heart5.startAnimation(animation);
        heart6.startAnimation(animation);
        heart7.startAnimation(animation);
        heart8.startAnimation(animation);
        heart9.startAnimation(animation);

        lives = 9;
        Bundle p = getIntent().getExtras();
        textFile = p.getString("textFile");

        ArrayList<String> parserList = new ArrayList<String>();
        for(int i = 0; i < textFile.length(); i++)
            parserList.add(textFile.substring(i, i + 1));

        textFileToArrayLists(parserList);
        generateWord();

        guessLetterButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Game.this);
                View mView = getLayoutInflater().inflate(R.layout.guess_letter, null);
                final EditText guessedLetterET = (EditText) mView.findViewById(R.id.editText);
                //final String guessedLetter = String.valueOf(guessedLetterET);
                Button guessLetterBtn = (Button) mView.findViewById(R.id.guessBtn);
                Button cancelBtn = (Button) mView.findViewById(R.id.cancelBtn);



                btnA = (Button)  mView.findViewById(R.id.btnA);
                btnA.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("A");
                    }
                });

                if(lettersGuessed.contains("A"))
                    btnA.setVisibility(View.INVISIBLE);

                btnB = (Button)  mView.findViewById(R.id.btnB);
                btnB.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("B");
                    }
                });

                if(lettersGuessed.contains("B"))
                    btnB.setVisibility(View.INVISIBLE);

                btnC = (Button)  mView.findViewById(R.id.btnC);
                btnC.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("C");
                    }
                });

                if(lettersGuessed.contains("C"))
                    btnC.setVisibility(View.INVISIBLE);

                btnD = (Button)  mView.findViewById(R.id.btnD);
                btnD.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("D");
                    }
                });

                if(lettersGuessed.contains("D"))
                    btnD.setVisibility(View.INVISIBLE);

                btnE = (Button)  mView.findViewById(R.id.btnE);
                btnE.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("E");
                    }
                });

                if(lettersGuessed.contains("E"))
                    btnE.setVisibility(View.INVISIBLE);

                btnF = (Button)  mView.findViewById(R.id.btnF);
                btnF.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("F");
                    }
                });

                if(lettersGuessed.contains("F"))
                    btnF.setVisibility(View.INVISIBLE);

                btnG = (Button)  mView.findViewById(R.id.btnG);
                btnG.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("G");
                    }
                });

                if(lettersGuessed.contains("G"))
                    btnG.setVisibility(View.INVISIBLE);

                btnH = (Button)  mView.findViewById(R.id.btnH);
                btnH.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("H");
                    }
                });

                if(lettersGuessed.contains("H"))
                    btnH.setVisibility(View.INVISIBLE);

                btnI = (Button)  mView.findViewById(R.id.btnI);
                btnI.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("I");
                    }
                });

                if(lettersGuessed.contains("I"))
                    btnI.setVisibility(View.INVISIBLE);

                btnJ = (Button)  mView.findViewById(R.id.btnJ);
                btnJ.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("J");
                    }
                });

                if(lettersGuessed.contains("J"))
                    btnJ.setVisibility(View.INVISIBLE);

                btnK = (Button)  mView.findViewById(R.id.btnK);
                btnK.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("K");
                    }
                });

                if(lettersGuessed.contains("K"))
                    btnK.setVisibility(View.INVISIBLE);

                btnL = (Button)  mView.findViewById(R.id.btnL);
                btnL.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("L");
                    }
                });

                if(lettersGuessed.contains("L"))
                    btnL.setVisibility(View.INVISIBLE);

                btnM = (Button)  mView.findViewById(R.id.btnM);
                btnM.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("M");
                    }
                });

                if(lettersGuessed.contains("M"))
                    btnM.setVisibility(View.INVISIBLE);

                btnN = (Button)  mView.findViewById(R.id.btnN);
                btnN.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("N");
                        btnN.setEnabled(false);
                    }
                });

                if(lettersGuessed.contains("N"))
                    btnN.setVisibility(View.INVISIBLE);

                btnO = (Button)  mView.findViewById(R.id.btnO);
                btnO.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("O");
                    }
                });

                if(lettersGuessed.contains("O"))
                    btnO.setVisibility(View.INVISIBLE);

                btnP = (Button)  mView.findViewById(R.id.btnP);
                btnP.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("P");
                    }
                });

                if(lettersGuessed.contains("P"))
                    btnP.setVisibility(View.INVISIBLE);

                btnQ = (Button)  mView.findViewById(R.id.btnQ);
                btnQ.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("Q");
                    }
                });

                if(lettersGuessed.contains("Q"))
                    btnQ.setVisibility(View.INVISIBLE);

                btnR = (Button)  mView.findViewById(R.id.btnR);
                btnR.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("R");
                    }
                });

                if(lettersGuessed.contains("R"))
                    btnR.setVisibility(View.INVISIBLE);

                btnS = (Button)  mView.findViewById(R.id.btnS);
                btnS.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("S");
                    }
                });

                if(lettersGuessed.contains("S"))
                    btnS.setVisibility(View.INVISIBLE);

                btnT = (Button)  mView.findViewById(R.id.btnT);
                btnT.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("T");
                    }
                });

                if(lettersGuessed.contains("T"))
                    btnT.setVisibility(View.INVISIBLE);

                btnU = (Button)  mView.findViewById(R.id.btnU);
                btnU.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("U");
                    }
                });

                if(lettersGuessed.contains("U"))
                    btnU.setVisibility(View.INVISIBLE);

                btnV = (Button)  mView.findViewById(R.id.btnV);
                btnV.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("V");
                    }
                });

                if(lettersGuessed.contains("V"))
                    btnV.setVisibility(View.INVISIBLE);

                btnW = (Button)  mView.findViewById(R.id.btnW);
                btnW.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("W");
                    }
                });

                if(lettersGuessed.contains("W"))
                    btnW.setVisibility(View.INVISIBLE);

                btnX = (Button)  mView.findViewById(R.id.btnX);
                btnX.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("X");
                    }
                });

                if(lettersGuessed.contains("X"))
                    btnX.setVisibility(View.INVISIBLE);

                btnY = (Button)  mView.findViewById(R.id.btnY);
                btnY.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("Y");
                    }
                });

                if(lettersGuessed.contains("Y"))
                    btnY.setVisibility(View.INVISIBLE);

                btnZ = (Button)  mView.findViewById(R.id.btnZ);
                btnZ.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        guessedLetterET.setText("Z");
                    }
                });

                if(lettersGuessed.contains("Z"))
                    btnZ.setVisibility(View.INVISIBLE);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                guessLetterBtn.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        makeGuess(String.valueOf(guessedLetterET.getText()));
                        //finish();
                        dialog.dismiss();
                    }
                });

                cancelBtn.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        //finish();
                        dialog.dismiss();
                    }
                });
            }
        });

        guessPhraseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Game.this);
                View mView = getLayoutInflater().inflate(R.layout.final_guess, null);
                final EditText guessedLetterET = (EditText) mView.findViewById(R.id.editText);
                Button guessLetterBtn = (Button) mView.findViewById(R.id.guessBtn);
                Button cancelBtn = (Button) mView.findViewById(R.id.cancelBtn);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                guessLetterBtn.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        String word = guessedLetterET.getText().toString().toUpperCase();
                        phraseHolder.setText(theAnswer);
                        if(word.equals(theAnswer))
                            youWon();
                        else
                            youLost();

                        dialog.dismiss();
                    }
                });

                cancelBtn.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        dialog.dismiss();
                    }
                });
            }
        });
    }
    private void makeGuess(String letter)
    {
        for(int i = 0; i < theAnswer.length(); i++)
        {
            if(theAnswer.substring(i, i + 1).equals(letter))
                blankAnswer = blankAnswer.substring(0, i) + letter + blankAnswer.substring(i + 1);

        }

        String message = "";
        if(!theAnswer.contains(letter))
        {
            lives--;
            message += "SORRY, YOU GUESSED AN INCORRECT LETTER, ";
            updateHearts();
        }
        else
            message += "CONGRATS! YOU GUESSED A CORRECT LETTER, ";
        message += "LIVES: " + lives;
        counterView.setText(message);

        String blankAnswerSpace = blankAnswer.substring(0, 1);
        for(int j = 1; j < blankAnswer.length(); j++)
        {
            blankAnswerSpace += " " + blankAnswer.charAt(j);
        }
        phraseHolder.setText(blankAnswerSpace);

        if(theAnswer.equals(blankAnswer))
        {
            youWon();
        }

        if(lives <= 0)
        {
            youLost();
        }

        lettersGuessed.add(letter);
    }

    private void textFileToArrayLists(ArrayList testerList)
    {
        ArrayList<String> tempList = new ArrayList<String>();
        while(testerList.size() > 0)
        {
            boolean keepGoing = true;
            String temp = "";

            while (keepGoing) {

                if (testerList.get(0).equals(".")) {
                    testerList.remove(0);
                    keepGoing = false;
                } else {
                    temp += testerList.get(0);
                    testerList.remove(0);
                }

//                if (!testerList.get(0).equals(".")) {
//                    temp += testerList.get(0);
//                    testerList.remove(0);
//                } else {
//                    testerList.remove(0);
//                    keepGoing = false;
//                }
            }
            tempList.add(temp);
        }

        for(int i = 0; i < tempList.size(); i++)
        {
            if(i % 2 == 0)
                questionList.add(tempList.get(i));
            else
                answerList.add(tempList.get(i));
        }
    }

    private void generateWord()
    {
        random = new Random().nextInt(questionList.size()); // [0, questionList.size())
        theQuestion = questionList.get(random).substring(1);
        theAnswer = answerList.get(random).toUpperCase();
        for(int i = 0; i < theAnswer.length(); i++)
        {
            if(Character.isLetter(theAnswer.charAt(i)))
                blankAnswer += "_";
            else
                blankAnswer += theAnswer.charAt(i);
        }

        String blankAnswerSpace = blankAnswer.substring(0, 1);
        for(int j = 1; j < blankAnswer.length(); j++)
        {
            blankAnswerSpace += " " + blankAnswer.charAt(j);
        }

        phraseHolder.setText(blankAnswerSpace);
        questionHint.setText("Hint: " + theQuestion);
    }



    private void youWon()
    {
        phraseHolder.setText(theAnswer);
        counterView.setText("CONGRATS, YOU WON!");
        //gif.setImageResource(R.drawable.winner);
        guessLetterButton.setText("Play Again");
        guessLetterButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finishActivity();
            }
        });
        guessPhraseButton.setVisibility(View.INVISIBLE);

    }

    private void youLost()
    {
        phraseHolder.setText(theAnswer);
        counterView.setText("SORRY, YOU LOST!");
        //gif.setImageResource(R.drawable.loser);
        guessLetterButton.setText("Play Again");
        guessLetterButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finishActivity();
            }
        });
        guessPhraseButton.setVisibility(View.INVISIBLE);
    }
    public void finishActivity()
    {
        Intent i = new Intent(this, Main2Activity.class);
        startActivity(i);
    }

    private void updateHearts()
    {
        Animation animation = AnimationUtils.loadAnimation(Game.this, R.anim.fadeout);

        if(lives == 8)
            heart9.startAnimation(animation);
        else if(lives == 7)
            heart8.startAnimation(animation);
        else if(lives == 6)
            heart7.startAnimation(animation);
        else if(lives == 5)
            heart6.startAnimation(animation);
        else if(lives == 4)
            heart5.startAnimation(animation);
        else if(lives == 3)
            heart4.startAnimation(animation);
        else if(lives == 2)
            heart3.startAnimation(animation);
        else if(lives == 1)
            heart2.startAnimation(animation);
        else
            heart1.startAnimation(animation);
    }


}
