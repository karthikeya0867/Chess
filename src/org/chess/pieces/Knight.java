package org.chess.pieces;
import javax.swing.JButton;
public class Knight extends Piece{

    public Knight(char color) {
        super(color);
    }

    @Override
   public boolean isValid(int fromX, int fromY, int toX, int toY, JButton[][] chessboard) {
        if((fromX+2 ==toX || fromX-2==toX) && (fromY+1 == toY || fromY-1 ==toY)&& 
        (chessboard[toX][toY].getText().equals("") ||  (chessboard[toX][toY].getText().charAt(0)!=this.color))){
            return true;
        }
        return (fromX+1 ==toX || fromX-1==toX) && (fromY+2 == toY || fromY-2 ==toY) &&
                (chessboard[toX][toY].getText().equals("") || (chessboard[toX][toY].getText().charAt(0)!=this.color));
    }   
}
