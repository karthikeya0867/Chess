package org.chess.pieces;
import java.awt.Image;

import javax.swing.*;
public class Pawn extends Piece {
    public Pawn( char color) {
        super(color);
    }
    @Override
	public
    boolean isValid(int fromX, int fromY, int toX, int toY, JButton[][] chessboard) {
        if (this.color=='W') {
            if (fromX + 1 == toX && fromY == toY && chessboard[toX][toY].getText().equals("")) {
                return true;
            }
        if (fromX == 1 && fromX + 2 == toX && fromY == toY && chessboard[toX][toY].getText().equals("") &&  
        (chessboard[fromX+1][fromY].getText().equals("")) ) {
                return true;
            }
        if (fromX + 1 == toX && Math.abs(fromY - toY) == 1 && chessboard[toX][toY].getText().startsWith("B")) {
                return true;
            }
        }
        if (this.color=='B') {
            if (fromX - 1 == toX && fromY == toY && chessboard[toX][toY].getText().equals("")) {
                return true;
            }
            if (fromX == 6 && fromX - 2 == toX && fromY == toY && chessboard[toX][toY].getText().equals("") && 
            (chessboard[fromX-1][fromY].getText().equals(""))) {
                return true;
            }
            if (fromX - 1 == toX && Math.abs(fromY - toY) == 1 && chessboard[toX][toY].getText().startsWith("W")) {
                return true;
            }
        }
        return false;
    }
    static ImageIcon setTheIcon(String filepath){
        return new ImageIcon(new ImageIcon(filepath).getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    }
public void promotePawn(int x, int y,JButton[][] chessboard) {
String[] options = {"Queen", "Rook", "Bishop", "Knight"};
String promotionChoice =null;
while(promotionChoice == null){
 promotionChoice=(String) JOptionPane.showInputDialog(
        null, "Choose a piece for promotion:", "Pawn Promotion", 
        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
}
char pieceColor = this.color;

chessboard[x][y].setText(""); 
chessboard[x][y].setIcon(null); 
switch(promotionChoice) {
    case "Queen" -> {
        chessboard[x][y].setText(pieceColor + "Q"); 
        if (pieceColor == 'W') {
            chessboard[x][y].setIcon(setTheIcon("white-queen.png"));
        } else {
            chessboard[x][y].setIcon(setTheIcon("black-queen.png"));
        }   }
    case "Rook" -> {
        chessboard[x][y].setText(pieceColor + "R");
        if (pieceColor == 'W') {
            chessboard[x][y].setIcon(setTheIcon("white-rook.png"));
        } else {
            chessboard[x][y].setIcon(setTheIcon("black-rook.png"));
        }   }
    case "Bishop" -> {
        chessboard[x][y].setText(pieceColor + "B");
        if (pieceColor == 'W') {
            chessboard[x][y].setIcon(setTheIcon("white-bishop.png"));
        } else {
            chessboard[x][y].setIcon(setTheIcon("black-bishop.png"));
        }   }
    case "Knight" -> {
        chessboard[x][y].setText(pieceColor + "N");
        if (pieceColor == 'W') {
            chessboard[x][y].setIcon(setTheIcon("white-knight.png"));
        } else {
            chessboard[x][y].setIcon(setTheIcon("black-knight.png"));
        }   }
}
}

}