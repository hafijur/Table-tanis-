package client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;

@SuppressWarnings("serial")
public class Game extends JPanel {

    Racquet racquet = new Racquet(this);
    public Racquet2 cracquet = new Racquet2(this);
    Ball ball = new Ball(this);
    
    //Score
    public static int serverScore = 0;
    public static int clientScore = 0;
    public static int turn = 0;
    
    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                racquet.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                racquet.keyPressed(e);
            }
        });
        setFocusable(true);
    }

    public void move() {
        racquet.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        racquet.paint(g2d);
        cracquet.paint(g2d);
        ball.paint(g2d);
        
    }

    public void gameOver() {
             this.GameResult();
             System.exit(ABORT);   
    }
    public void turnServer(){
        JOptionPane.showMessageDialog(this, "Server Turn", "Server Turn", JOptionPane.YES_NO_OPTION);
    }
    public void turnClient(){
        JOptionPane.showMessageDialog(this, "Client Turn", "Client Turn", JOptionPane.YES_NO_OPTION);
    }
    public void GameResult(){
        if(Game.serverScore < Game.clientScore){
            JOptionPane.showMessageDialog(this, "You win!!!!!!! (Oposite:You) = (" + Game.serverScore + ":" + Game.clientScore + ")", "Game Over", JOptionPane.YES_NO_OPTION);
        }
        else if(Game.serverScore == Game.clientScore){
            JOptionPane.showMessageDialog(this, "Game is Draw (Oposite:You) = (" + Game.serverScore + ":" + Game.clientScore + ")", "Game Over", JOptionPane.YES_NO_OPTION);
        }
        else{
            JOptionPane.showMessageDialog(this, "You loss!!!!!! (Oposite:You) = (" + Game.serverScore + ":" + Game.clientScore + ")", "Game Over", JOptionPane.YES_NO_OPTION);
        }
    }
    public void start(Game game) {
        JFrame frame = new JFrame("Mini Tenis Client");
        frame.add(game);
        frame.setSize(450, 650);
        frame.setLocation(600, 20);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
