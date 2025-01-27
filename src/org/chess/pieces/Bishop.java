package org.chess.pieces;
import javax.swing.JButton;
public class Bishop extends Piece{
    public Bishop(char color) {
            super(color);
        }
        @Override
       public boolean isValid(int fromX, int fromY, int toX, int toY, JButton[][] chessboard) {
            if (Math.abs(toX - fromX) != Math.abs(toY - fromY))  return false; 
            if(!chessboard[toX][toY].getText().equals("") && chessboard[toX][toY].getText().charAt(0)==this.color)return false;
            int xstep=(toX > fromX) ? 1 : -1;
            int ystep=(toY > fromY) ? 1 : -1;
            int x=fromX +xstep;
            int y=fromY+ystep;
            while((x!= toX) && (y!=toY)){
                if(!chessboard[x][y].getText().equals(""))return false;
                x+=xstep;
                y+=ystep;
            }
            return true;
        }   
    }