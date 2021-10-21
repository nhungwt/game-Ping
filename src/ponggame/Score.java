/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

import java.awt.*;
/**
 *
 * @author LENOVO
 */
public class Score extends Rectangle {
    
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;
    
    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }
    
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
        
        g.setFont(new Font("Consolas", Font.PLAIN, 40));
        g.drawString(Integer.toString(player1), GAME_WIDTH/2 - 50, 50);
        g.drawString(Integer.toString(player2), GAME_WIDTH/2 + 20, 50);
    }
}
