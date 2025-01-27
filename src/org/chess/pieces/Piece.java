package org.chess.pieces;
import javax.swing.JButton;
abstract class Piece{
    public char color;
    Piece(char color){
        this.color=color;
    }
    abstract boolean isValid(int fromX,int fromY,int toX,int toY,JButton[][] chessboard);
}