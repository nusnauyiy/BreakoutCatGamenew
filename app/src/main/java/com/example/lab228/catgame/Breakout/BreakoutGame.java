package com.example.lab228.catgame.Breakout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.lab228.catgame.R;

//sample code from http://gamecodeschool.com/android/coding-a-breakout-game-for-android/
public class BreakoutGame extends Activity {

    // gameView will be the view of the game
    // It will also hold the logic of the game
    // and respond to screen touches as well
    BreakoutView breakoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize gameView and set it as the view
        breakoutView = new BreakoutView(this);
        setContentView(breakoutView);

    }

    // Here is our implementation of GameView
    // It is an inner class.
    // Note how the final closing curly brace }
    // is inside SimpleGameEngine

    // Notice we implement runnable so we have
    // A thread and can override the run method.
    class BreakoutView extends SurfaceView implements Runnable {

        // This is our thread
        Thread gameThread = null;

        // This is new. We need a SurfaceHolder
        // When we use Paint and Canvas in a thread
        // We will see it in action in the draw method soon.
        SurfaceHolder ourHolder;

        // A boolean which we will set and unset
        // when the game is running- or not.
        volatile boolean playing;

        // Game is paused at the start
        boolean paused = true;

        // A Canvas and a Paint object
        Canvas canvas;
        Paint paint;


        // This variable tracks the game frame rate
        long fps;

        // This is used to help calculate the fps
        private long timeThisFrame;

        // The size of the screen in pixels
        int screenX;
        int screenY;

        // The player's paddle

        Paddle paddle;
        Ball ball;
        // Up to 200 bricks
        Brick[] bricks = new Brick[200];
        int numBricks = 0;

        int brickWidth, brickHeight, brickColumn;

        // The score
        int score = 0;
        int highScore = 0;

        // Lives
        int lives = 3;

        // When the we initialize (call new()) on gameView
        // This special constructor method runs
        public BreakoutView(Context context) {
            // The next line of code asks the
            // SurfaceView class to set up our object.
            // How kind.
            super(context);

            // Initialize ourHolder and paint objects
            ourHolder = getHolder();
            paint = new Paint();

            // Get a Display object to access screen details
            Display display = getWindowManager().getDefaultDisplay();
// Load the resolution into a Point object
            Point size = new Point();
            display.getSize(size);

            screenX = size.x;
            screenY = size.y;

            brickColumn = (int)(Math.random() * 4) + 7;
            brickWidth = screenX / brickColumn;
            brickHeight = screenY / 10;

            paddle = new Paddle(screenX, screenY);
            ball = new Ball(screenX, screenY);
        }

        public void createBricksAndRestart(){

            // Put the ball back to the start
            ball.reset(screenX, screenY);
            paddle.reset();

// Build a wall of bricks
            numBricks = 0;

            for(int row = 2; row > 0; row -- ){
                for(int column = 0; column < brickColumn; column ++ ){
                    bricks[numBricks] = new Brick(row, column, brickWidth, brickHeight);
                    numBricks ++;
                }
            }
            // Reset scores and lives

            score = 0;
            lives = 3;


        }

        @Override
        public void run() {
            while (playing) {

                // Capture the current time in milliseconds in startFrameTime
                long startFrameTime = System.currentTimeMillis();

                // Update the frame
                // Update the frame
                if (!paused) {
                    update();
                }

                // Draw the frame
                draw();

                // Calculate the fps this frame
                // We can then use the result to
                // time animations and more.
                timeThisFrame = System.currentTimeMillis() - startFrameTime;
                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame;
                }

                //increase difficulties
                if(System.currentTimeMillis() % 250 == 0 && paused == false && paddle.getPaddleSpeed() <= 600){
                    ball.speedUp();
                    paddle.speedUp();
                }

            }

        }

        // Everything that needs to be updated goes in here
        // Movement, collision detection etc.
        public void update() {
            // Move the paddle if required
            paddle.update(fps, screenX);
            ball.update(fps);

            // Check for ball colliding with a brick
            for(int i = 0; i < numBricks; i++){
                bricks[i].update(fps);
                if (bricks[i].getVisibility()){

                    if(RectF.intersects(bricks[i].getRect(),ball.getRect())) {
                        bricks[i].setInvisible();
                        ball.reverseYVelocity();
                        score = score + 10;
                        if(score>highScore){
                            highScore = score;
                        }
                    }

                    //check if bricks collide with bottom of screen
                    //end game
                    if(bricks[i].getRect().bottom > screenY){

                        paused = true;
                        lives = 0;
                        createBricksAndRestart();
                    }

                    if(bricks[numBricks-1].getRect().top - bricks[numBricks-1].getHeight() - 2 * bricks[numBricks-1].getPadding() > 0){
                        for(int column = 0; column < brickColumn; column ++ ){
                            bricks[numBricks] = new Brick(0, column, brickWidth, brickHeight);
                            numBricks ++;
                        }
                        break;
                    }
                }
            }

            // Check for ball colliding with paddle
            if(RectF.intersects(paddle.getRect(),ball.getRect())) {
                ball.setRandomXVelocity();
                ball.reverseYVelocity();
                ball.clearObstacleY(paddle.getRect().top - 1);
            }

            // Bounce the ball back when it hits the bottom of screen
            // And deduct a life
            if(ball.getRect().bottom > screenY){
                ball.reverseYVelocity();
                ball.clearObstacleY(screenY - 1);

                // Lose a life
                lives --;

                if(lives == 0){
                    paused = true;
                    createBricksAndRestart();
                }

            }


            // Bounce the ball back when it hits the top of screen
            if(ball.getRect().top < 0){
                ball.reverseYVelocity();
                ball.clearObstacleY(ball.ballHeight+1);
            }

            // If the ball hits left wall bounce
            if(ball.getRect().left < 0){
                ball.reverseXVelocity();
                ball.clearObstacleX(1);
            }

            // If the ball hits right wall bounce
            if(ball.getRect().right >= screenX){
                ball.reverseXVelocity();
                ball.clearObstacleX(screenX - ball.ballWidth-1);
            }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
            // Pause if cleared screen
            if(numBricks == 0){
                paused = true;
                createBricksAndRestart();
            }

        }

        // Draw the newly updated scene
        public void draw() {

            // Make sure our drawing surface is valid or we crash
            if (ourHolder.getSurface().isValid()) {
                // Lock the canvas ready to draw\
                canvas = ourHolder.lockCanvas();

                Drawable d = getResources().getDrawable(R.drawable.sky);
                d.setBounds(0, 0, screenX, screenY);
                d.draw(canvas);

                // Draw the background color
                //canvas.drawColor(Color.argb(255, 26, 128, 182));


                // Draw the paddle
                paint.setColor(Color.argb(0, 255, 255, 255));
                Bitmap imagePaddle = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.breakout_paddle);
                canvas.drawRect(paddle.getRect(), paint);
                canvas.drawBitmap(imagePaddle, null, paddle.getRect(), null);

                // Draw the ball
                // Choose the brush color for drawing
                paint.setColor(Color.argb(255, 255, 255, 255));
                Bitmap imageBall = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.breakout_ball);
                canvas.drawRect(ball.getRect(), paint);
                canvas.drawBitmap(imageBall, null, ball.getRect(), null);

                // Draw the bricks
                // Change the brush color for drawing
                // Draw the bricks if visible
                for(int i = 0; i < numBricks; i++){
                    if(bricks[i].getVisibility()) {
                        if(i%3 == 0) {
                            paint.setColor(Color.argb(240,  249, 129, 0));
                        }
                        else{
                            paint.setColor(Color.argb(240,  230, 200, 0));
                        }
                        canvas.drawRect(bricks[i].getRect(), paint);
                    }
                }
                // Draw the HUD
                // Choose the brush color for drawing
                paint.setColor(Color.argb(255,  255, 255, 255));

                // Draw the score
                paint.setTextSize(40);
                canvas.drawText("Score: " + score + "   Lives: " + lives + "   Highest score: "+highScore+" " + ball.yVelocity, 10,50, paint);

// Has the player cleared the screen?
                if(score == 0 && paused == true){
                    paint.setTextSize(90);
                    if(highScore == 0) {
                        canvas.drawText("START", 20, screenY / 2 - 30, paint);
                        paint.setTextSize(40);
                        canvas.drawText("press left side of screen to move left", 20, screenY * 2/3, paint);
                        canvas.drawText("press right side of screen to move right", 20, screenY * 2/3 - 50, paint);
                        canvas.drawText("tap the score displays to pause", 20, screenY * 2/3 - 100, paint);
                    }
                    else{
                        canvas.drawText("RETRY", 20, screenY / 2, paint);
                    }
                }
/**
                if(lives <= 0){
                    paint.setTextSize(90);
                    canvas.drawText("YOU HAVE LOST!", 10,screenY/2, paint);
                }**/
                // Draw everything to the screen
                ourHolder.unlockCanvasAndPost(canvas);
            }

        }

        // If SimpleGameEngine Activity is paused/stopped
        // shutdown our thread.
        public void pause() {
            playing = false;

            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("Error:", "joining thread");
            }

        }

        // If SimpleGameEngine Activity is started then
        // start our thread.
        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        // The SurfaceView class implements onTouchListener
        // So we can override this method and detect screen touches.
        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

                // Player has touched the screen
                case MotionEvent.ACTION_DOWN:

                    if(motionEvent.getY() < screenY/6){
                        paused = !paused;
                        break;
                    }

                    paused = false;
                    if (motionEvent.getX() > screenX / 2) {
                        paddle.setMovementState(paddle.RIGHT);
                    } else {
                        paddle.setMovementState(paddle.LEFT);
                    }

                    break;

                // Player has removed finger from screen
                case MotionEvent.ACTION_UP:

                    paddle.setMovementState(paddle.STOPPED);
                    break;
            }
            return true;
        }
    }
    // This is the end of our BreakoutView inner class

    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();

        // Tell the gameView resume method to execute
        breakoutView.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the gameView pause method to execute
        breakoutView.pause();
    }

}
// This is the end of the BreakoutGame class