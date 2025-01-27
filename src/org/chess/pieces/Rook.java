package org.chess.pieces;
import javax.swing.JButton;
public class Rook extends Piece{
    public Rook(char color) {
        super(color);
    }
    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY, JButton[][] chessboard) {
        if(toY==fromY){
            if(toX>fromX){
            for(int i=fromX+1;i<toX;i++){
                    if(!chessboard[i][fromY].getText().equals(""))return false;
            }
            }else if(toX < fromX){
                for(int i=fromX-1;i>toX;i--){
                    if(!chessboard[i][fromY].getText().equals(""))return false;
                } 
            }
            return (chessboard[toX][toY].getText().equals("") || (chessboard[toX][toY].getText().charAt(0)!=this.color) );
        }
        if(toX==fromX){
            if(toY>fromY){
            for(int i=fromY+1;i<toY;i++){
                    if(!chessboard[fromX][i].getText().equals(""))return false;
            }
            }else if(toY<fromY){
                for(int i=fromY-1;i>toY;i--){
                    if(!chessboard[fromX][i].getText().equals(""))return false;
            }
            }
            return(chessboard[toX][toY].getText().equals("")  || (chessboard[toX][toY].getText().charAt(0)!=this.color));
        }   
        return false;
}
}