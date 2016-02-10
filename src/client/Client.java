package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Client extends Game {

    private Socket server;
    private BufferedReader in;
    private PrintWriter out;

    public Client(String host, int port) {
        try {
            server = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            out = new PrintWriter(server.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        String host = JOptionPane.showInputDialog(null, "Enter Server IP");
        int port = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Server Port"));
        //String host = "127.0.0.1";
        //int port = 1234;
        Client client = new Client(host,port);
        JOptionPane.showMessageDialog(null, "Connected to Server: " + host + " Port: " + port);
        System.out.println("Connected to server");
        
        Thread game = new Thread(new Runnable() {
            final Game game = new Game();

            @Override
            public void run() {
                game.start(game);
                while (true) {
                    try {
                        game.move();
                        game.repaint();
                        Thread.sleep(40);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        game.start();
        
        Thread com = new Thread(new Runnable() {
            public synchronized void Parser(String stream) {
                String item[] = stream.split(":");
                Racquet2.x = Integer.parseInt(item[0]);
                Racquet2.xa = Integer.parseInt(item[1]);
                Ball.x = Integer.parseInt(item[2]);
                Ball.y = Integer.parseInt(item[3]);
                if((Integer.parseInt(item[4]) == 1)){
                    client.gameOver();
                }
                Game.serverScore = Integer.parseInt(item[5]);
                Game.clientScore = Integer.parseInt(item[6]);
                Game.turn = Integer.parseInt(item[7]);
            }
            @Override
            public void run() {
                while (true) {
                    client.out.println(Racquet.x + ":" + Racquet.xa);
                    System.out.println(Racquet.x + ":" + Racquet.xa);
                    try {
                        String stream = client.in.readLine();
                        this.Parser(stream);
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        com.start();
        try {
            game.join();
            com.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
