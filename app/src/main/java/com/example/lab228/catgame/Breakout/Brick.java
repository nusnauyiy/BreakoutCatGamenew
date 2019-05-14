package com.example.lab228.catgame.Breakout;

import android.graphics.RectF;

public class Brick {

    private RectF rect;
    private int padding;
    private float speed, height;

    private boolean isVisible;

    public Brick(int row, int column, int width, int height){

        isVisible = true;
        this.height = height;
        padding = 5;
        speed = 5;

        rect = new RectF(column * width + padding,
                row * height + padding,
                column * width + width - padding,
                row * height + height - padding);
    }

    public RectF getRect(){
        return this.rect;
    }

    public void setInvisible(){
        isVisible = false;
    }

    public boolean getVisibility(){
        return isVisible;
    }

    public void update(long fps){

        rect.top = rect.top + (speed / fps);
        rect.bottom = rect.top + height - padding;
    }

    public int getPadding(){return padding;}
    public float getHeight(){return height;}

}