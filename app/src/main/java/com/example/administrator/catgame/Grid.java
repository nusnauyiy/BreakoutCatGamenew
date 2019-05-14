package com.example.administrator.catgame;

import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

public class Grid {

    private ImageButton image;
    private int status;
    private boolean locked;

    public Grid(ImageButton image){
        this.image = image;
        this.status = 0;
        this.locked = false;
    }

    public int getStatus() {
        return status;
    }

    public ImageButton getImage() {
        return image;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean setStatus(int newStatus){
        if(locked == true){
            return false;
        }
        status = newStatus;
        return true;
    }

    public void setImage(int id){
        if(locked == true){
            return;
        }
        this.image.setImageResource(id);
        image.setImageAlpha(255);
        locked = true;
    }

    public void reset(){
        status = 0;
        locked = false;
        image.setImageAlpha(0);
    }
}
