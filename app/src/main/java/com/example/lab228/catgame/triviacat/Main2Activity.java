package com.example.lab228.catgame.triviacat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lab228.catgame.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main2Activity extends AppCompatActivity {
//hi
    Button breedBtn, historyBtn, famousCatsBtn, funFactsBtn, randomBtn;
    String catbreedsText = "";
    String catfactsText = "";
    String famouscatsText = "";
    String historyText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        breedBtn = (Button)findViewById(R.id.button);
        //historyBtn = (Button)findViewById(R.id.button2);
        famousCatsBtn = (Button)findViewById(R.id.button3);
        funFactsBtn = (Button)findViewById(R.id.button4);
        randomBtn = (Button)findViewById(R.id.button5);


        //read catbreeds textfile
        InputStream inputStream1 = getResources().openRawResource(R.raw.catbreeds);
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();

        int in1;
        try {
            in1 = inputStream1.read();
            while (in1 != -1) {
                byteArrayOutputStream1.write(in1);
                in1 = inputStream1.read();
            }
            inputStream1.close();

            catbreedsText = byteArrayOutputStream1.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //read catfacts textfile
        InputStream inputStream2 = getResources().openRawResource(R.raw.catfacts);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();

        int in2;
        try {
            in2 = inputStream2.read();
            while (in2 != -1) {
                byteArrayOutputStream2.write(in2);
                in2 = inputStream2.read();
            }
            inputStream2.close();

            catfactsText = byteArrayOutputStream2.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //read famouscats textfile
        InputStream inputStream3 = getResources().openRawResource(R.raw.famouscats);
        ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();

        int in3;
        try {
            in3 = inputStream3.read();
            while (in3 != -1) {
                byteArrayOutputStream3.write(in3);
                in3 = inputStream3.read();
            }
            inputStream3.close();

            famouscatsText = byteArrayOutputStream3.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //read history textfile
        InputStream inputStream4 = getResources().openRawResource(R.raw.history);
        ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();

        int in4;
        try {
            in4 = inputStream4.read();
            while (in4 != -1) {
                byteArrayOutputStream4.write(in4);
                in4 = inputStream4.read();
            }
            inputStream4.close();

            historyText = byteArrayOutputStream4.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        breedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                exqButton1();
            }
        });


        famousCatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                exqButton3();
            }
        });

        funFactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                exqButton4();
            }
        });

        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                exqButton5();
            }
        });
    }

    private void exqButton1()
    {
        Intent i = new Intent(this, Game.class);
        i.putExtra("textFile", catbreedsText);
        startActivity(i);
    }

    private void exqButton3()
    {
        Intent i = new Intent(this, Game.class);
        i.putExtra("textFile", famouscatsText);
        startActivity(i);
    }
    private void exqButton4()
    {
        Intent i = new Intent(this, Game.class);
        i.putExtra("textFile", catfactsText);
        startActivity(i);
    }

    private void exqButton5()
    {
        Intent i = new Intent(this, Game.class);
        i.putExtra("textFile", catfactsText + famouscatsText + catbreedsText);
        startActivity(i);
    }
}