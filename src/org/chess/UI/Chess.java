package org.chess.UI;
import javax.swing.*;
import org.chess.pieces.*;
import java.awt.*;
@SuppressWarnings("serial")
public class Chess extends JFrame{
    JButton[][] chessboard=new JButton[8][8];
    JButton holder;
    String selected="";
    boolean isWhiteTurn=true;
    ImageIcon piece;
    boolean wking=false;
    boolean bking=false;
    boolean wlrook=false;
    boolean wrrook=false;
    boolean blrook=false;
    boolean brrook=false;
    static ImageIcon setTheIcon(String filepath){
        return new ImageIcon(new ImageIcon(filepath).getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    }
    public Chess(){
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,800);
        setIconImage(new ImageIcon("white-knight.png").getImage());
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(30,30,30));
        setResizable(false);
        JPanel grids = new JPanel();
        grids.setLayout(new GridLayout(8,8));
        JPanel who = new JPanel();
        who.setLayout(new BorderLayout());
        who.setPreferredSize(new Dimension(200, 800));
        who.setBackground(new Color(50, 50, 50));

        JLabel turnLabel = new JLabel( "<html>" + (isWhiteTurn ? "White's" : "Black's") + "<br> Turn </html>", SwingConstants.CENTER);
        turnLabel.setForeground(Color.WHITE);
        turnLabel.setFont(new Font("Algerian", Font.BOLD, 22));
        who.add(turnLabel, BorderLayout.CENTER);
        
        add(who,BorderLayout.WEST);

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                JButton btn =new JButton();
                btn.setFont(new Font("ALGERIAN",Font.BOLD,1));
                btn.setFocusPainted(false);
                btn.setOpaque(true);
                chessboard[i][j]=btn;
                btn.setBackground((i+j)%2 == 0 ? Color.WHITE: new Color(55,155,55));
                if(i==1){
                    btn.setText("WP");
                    btn.setIcon(setTheIcon("white-pawn.png"));
                }
                if(i==6){
                btn.setText("BP");
                btn.setIcon(setTheIcon("black-pawn.png"));
            }
                if(i==0 && j==3){
                    btn.setText("WK");
                    btn.setIcon(setTheIcon("white-king.png"));
                }
                if(i==7 && j==3){
                    btn.setText("BK");
                    btn.setIcon(setTheIcon("black-king.png"));
                }
                if(i==0 && j==4){
                    btn.setText("WQ");
                    btn.setIcon(setTheIcon("white-queen.png"));
                }
                if(i==7 && j==4){
                    btn.setText("BQ");
                    btn.setIcon(setTheIcon("black-queen.png"));
                }
                if(i==0 && (j==0 || j==7)){
                    btn.setText("WR");
                    btn.setIcon(setTheIcon("white-rook.png"));
                }
                if(i==7 && (j==0 || j==7)){
                    btn.setText("BR");
                    btn.setIcon(setTheIcon("black-rook.png"));
                }
                if(i==0 && (j==1 || j==6)){
                    btn.setText("WN");
                    btn.setIcon(setTheIcon("white-knight.png"));
                }
                if(i==7 && (j==1 || j==6)){
                    btn.setText("BN");
                    btn.setIcon(setTheIcon("black-knight.png"));
                }
                if(i==0 && (j==2 || j==5)){
                    btn.setText("WB");
                    btn.setIcon(setTheIcon("white-bishop.png"));
                }
                if(i==7 && (j==2 || j==5)){
                    btn.setText("BB");
                    btn.setIcon(setTheIcon("black-bishop.png"));
                }
                final int i1=i;
                final int j1=j;
                btn.addMouseListener(new java.awt.event.MouseAdapter(){
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e){
                            btn.setBackground(new Color(0,255,255));
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e){
                    btn.setBackground((i1+j1)%2 == 0 ? Color.WHITE : new Color(55,155,55));
                    }
                });
                btn.addActionListener(_ -> {
                       if(selected.equals("")){
                        if(!btn.getText().equals("") && (isWhiteTurn ? btn.getText().charAt(0)=='W' : btn.getText().charAt(0)=='B')){
                            selected=btn.getText();
                            piece=(ImageIcon) btn.getIcon();
                            holder=btn;
                        }
                    }else{
                        int fromX=-1,fromY=-1,toX=-1,toY=-1;
                        boolean isValid=false;
                        for(int out=0 ;out<8;out++){
                            for(int in=0;in<8;in++){
                                if(chessboard[out][in]==holder){
                                    fromX=out;
                                    fromY=in;
                                }
                                if(chessboard[out][in]==btn){
                                    toX=out;
                                    toY=in;
                                }
                            }
                        }
                        if(selected.endsWith("P")){ 
                            Pawn pawn=new Pawn(selected.charAt(0));
                            isValid=pawn.isValid(fromX, fromY, toX, toY, chessboard);
                        }
                        else if(selected.endsWith("R")){
                            Rook rook=new Rook(selected.charAt(0));
                            isValid=rook.isValid(fromX, fromY, toX, toY, chessboard);
                            if(fromX==0 && fromY ==0 && isValid)wlrook=true;
                            if(fromX==0 && fromY ==7 && isValid)wrrook=true;
                            if(fromX==7 && fromY ==0 && isValid)blrook=true;
                            if(fromX==7 && fromY ==7 && isValid)brrook=true;
                        }
                        else if(selected.endsWith("Q")){
                            Rook rook=new Rook(selected.charAt(0));
                            Bishop bishop=new Bishop(selected.charAt(0));
                            if(rook.isValid(fromX, fromY, toX, toY, chessboard) 
                            || bishop.isValid(fromX, fromY, toX, toY, chessboard))
                            isValid=true;
                        }
                        else if(selected.endsWith("N")){
                            Knight knight=new Knight(selected.charAt(0));
                            isValid=knight.isValid(fromX, fromY, toX, toY, chessboard);
                        }
                        else if(selected.endsWith("B")){
                            Bishop bishop=new Bishop(selected.charAt(0));
                            isValid=bishop.isValid(fromX, fromY, toX, toY, chessboard);
                        }
                        else if(selected.endsWith("K")){
                            King king=new King(selected.charAt(0));
                            isValid=king.isValid(fromX,fromY,toX,toY,chessboard);
                            if(king.color=='W' && isValid)wking=true;
                            if(king.color=='B' && isValid)bking=true;
                            if(!isValid && king.color=='W' && !wking && !((toY>fromY)?wrrook:wlrook)){
                            isValid=king.castling(fromX, fromY, toX, toY, chessboard);
                            }
                            if(!isValid && king.color=='B' && !bking && !((toY>fromY)?brrook:blrook)){
                            isValid=king.castling(fromX, fromY, toX, toY, chessboard);
                            }
                        }
                       King king= new King(!isWhiteTurn ? 'W' :'B');
                        if (king.inCheck(chessboard)) {
                            if (king.checkForMate(chessboard)) {
                                JOptionPane.showMessageDialog(null, (isWhiteTurn ? "WHITE" : "BLACK") + " WON BY CHECKMATE");
                                disableAllMoves(chessboard); 
                            }
                        } 
                    
                        if(isValid){
                        holder.setText("");
                        btn.setText(selected);
                        King playerking = new King(isWhiteTurn ? 'W' : 'B');
                        if(playerking.inCheck(chessboard)) {
                        	JOptionPane.showMessageDialog(null,"King Will Be in Check");
                        	holder.setText(selected);
                        	btn.setText("");
                        }
                        holder.setIcon(null);
                        btn.setIcon(piece);
                       
                        if(selected.endsWith("P") && (toX==0 || toX==7)){
                            Pawn pawn=new Pawn(selected.charAt(0));
                            pawn.promotePawn(toX, toY, chessboard);
                        }
                        selected="";
                        isWhiteTurn=!isWhiteTurn;
                        turnLabel.setText("<html>" + (isWhiteTurn ? "White's" : "Black's") + "<br> Turn </html>");
                          }else{
                            selected="";
                        }
                    }
            });
                grids.add(btn);
            }   
        }
        add(grids,BorderLayout.CENTER);
       
    }
private void disableAllMoves(JButton[][] chessboard) {
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            chessboard[i][j].setEnabled(false);
        }
    }
}
}