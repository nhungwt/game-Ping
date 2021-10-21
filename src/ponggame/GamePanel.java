/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.sound.sampled.*;
/**
 *
 * @author LENOVO
 */
public class GamePanel extends JPanel implements Runnable {
    
    static final int GAME_WIDTH = 1000; 
    static final int GAME_HEIGHT = 600; 
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1, paddle2;
    Ball ball;
    Score score;
    
    
    GamePanel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        // Nhận tín hiệu từ bàn phím
        this.setFocusable(true); 
        this.addKeyListener(new AL());
        
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
        
        
    }
    
    // Tạo 1 quả bóng mới trên màn hình
    public void newBall(){
        random = new Random();
        ball = new Ball(GAME_WIDTH/2 - BALL_DIAMETER/2,random.nextInt(GAME_HEIGHT-BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }
    
    public void newPaddles(){
        paddle1 = new Paddle(0, (GAME_HEIGHT/2 - PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT,1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2 - PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }
    
    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    private void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    
    // làm paddle di chuyển mượt hơn
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    
    public void checkCollision(){
        // prevent paddlle pass over the windown
        if(paddle1.y<=0) paddle1.y = 0;
        if(paddle1.y >= GAME_HEIGHT - PADDLE_HEIGHT)
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        
        if(paddle2.y<=0) paddle2.y = 0;
        if(paddle2.y >= GAME_HEIGHT - PADDLE_HEIGHT)
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        
        // prevent ball pass over the top and bottom of the windown
        if(ball.y <= 0)
            ball.setYDirection(-ball.yVelocity);
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER)
             ball.setYDirection(-ball.yVelocity);  
        
         // check collision of ball and paddle
        if(ball.intersects(paddle1)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity > 0) 
                ball.yVelocity++;
            else 
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        
        if(ball.intersects(paddle2)){
            ball.xVelocity = -ball.xVelocity;
            ball.xVelocity--;
            if(ball.yVelocity > 0) 
                ball.yVelocity++;
            else 
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        
        // add point for pheyer
        if(ball.x <= 0){
            score.player2++;
            newPaddles();
            newBall();
        }
        if(ball.x >= GAME_WIDTH){
            score.player1++;
            newPaddles();
            newBall();
        }
        
    }
    
    public void run(){
        
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
        
    }
    
    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
    
}

