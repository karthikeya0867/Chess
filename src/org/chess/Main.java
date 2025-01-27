package org.chess;
import org.chess.UI.*;
import javax.swing.*;
import java.awt.*;
public class Main {

	public static void main(String args[]){
        Chess chess=new Chess();
        JFrame frame=new JFrame("Chess");
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(51,54,54));
        frame.setVisible(true);
        JButton btn=new JButton();
        frame.add(btn);
        btn.setText("Start A New Game");
        btn.setFont(new Font("ALGERIAN",Font.BOLD,20));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(55,155,55));
        btn.setBounds(120,150,250,50);
        btn.addMouseListener(new java.awt.event.MouseAdapter(){
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e){
                            btn.setBackground(new Color(0,255,255));
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e){
                    btn.setBackground(new Color(55,155,55));
                    }
                });
        btn.addActionListener(_->{
                chess.setVisible(true);
                frame.dispose();
            });
        JButton loadgame=new JButton("Load A Saved Game");
        frame.add(loadgame);
        loadgame.setBounds(120, 240, 250, 50);
        loadgame.setFocusPainted(false);
        loadgame.setFont(new Font("ALGERIAN",Font.BOLD,20));
        loadgame.setOpaque(true);
        loadgame.setForeground(Color.WHITE);
        loadgame.setBackground(new Color(55,155,55));
         loadgame.addMouseListener(new java.awt.event.MouseAdapter(){
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e){
                            loadgame.setBackground(new Color(0,255,255));
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e){
                    loadgame.setBackground(new Color(55,155,55));
                    }
                });      
    }
}
                                        