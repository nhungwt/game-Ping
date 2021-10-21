/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author LENOVO
 */
public class GameFrame extends JFrame {
    
    GamePanel panel;
    
    GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game ");
        this.setResizable(false); // false: ko cho điều chỉnh kích thước cửa sổ
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Tự động điều chỉnh khung sao cho vùa với bố cục trong bảng
        this.pack(); 
        this.setVisible(true); //Hiển thị cửa sổ trò chơi
        this.setLocationRelativeTo(null); // hiển thị cửa sổ ở giữa màn hình
        
    }
}
