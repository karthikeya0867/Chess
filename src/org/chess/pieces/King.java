package org.chess.pieces;
import javax.swing.ImageIcon;
import  javax.swing.JButton;
public class King extends Piece{
	public boolean checkForMate(JButton[][] chessboard) {
	    int kingX = -1, kingY = -1;
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            String text = chessboard[i][j].getText();
	            if ((text.equals("WK") && this.color == 'W') || (text.equals("BK") && this.color == 'B')) {
	                kingX = i;
	                kingY = j;
	            }
	        }
	    }

	  
	    for (int dx = -1; dx <= 1; dx++) {
	        for (int dy = -1; dy <= 1; dy++) {
	            if (dx == 0 && dy == 0) continue;
	            int newX = kingX + dx, newY = kingY + dy;
	            if (isInBounds(newX, newY) && isValid(kingX, kingY, newX, newY, chessboard)) {
	                return false; 
	            }
	        }
	    }

	  
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            String piece = chessboard[i][j].getText();
	            if (!piece.isEmpty() && piece.charAt(0) == this.color) {
	                for (int k = 0; k < 8; k++) {
	                    for (int l = 0; l < 8; l++) {
	                        if (isValidFor(chessboard, i, j, k, l, piece.charAt(1))) {
	                            // Simulate the move
	                            JButton temp = chessboard[k][l];
	                            chessboard[k][l] = chessboard[i][j];
	                            chessboard[i][j] = new JButton("");

	                            boolean stillInCheck = inCheck(chessboard);

	                            // Undo the move
	                            chessboard[i][j] = chessboard[k][l];
	                            chessboard[k][l] = temp;

	                            if (!stillInCheck) return false; 
	                        }
	                    }
	                }
	            }
	        }
	    }

	    return true;
	}


boolean isValidFor(JButton[][] chessboard,int i,int j,int toX,int toY,char selectedPiece) {
    switch (selectedPiece) {
    
    case 'P' ->{
       Pawn pawn=new Pawn(this.color);
       if(pawn.isValid(i, j, toX, toY, chessboard))return false;
    }
    case 'R' ->{
        Rook rook =new Rook(this.color);
        if(rook.isValid(i, j, toX, toY, chessboard))return false;
    }
    case 'N' ->{
        Knight knight =new Knight(this.color);
        if(knight.isValid(i, j, toX, toY, chessboard))return false;
    }
    case 'B' ->{
        Bishop bishop =new Bishop(color);
        if(bishop.isValid(i, j, toX, toY, chessboard))return false;
    }
    case 'K' ->{
        if((i+1 == toX || i-1 == toX) && toY == j)return false;
        else if(toX == i && (j+1 == toY || j-1 == toY))return false;
        int xstep=(toX > i) ? 1 : -1;
        int ystep=(toY > j) ? 1 : -1;
        if(toX==i+xstep && toY==j+ystep)return false;
    }
    case 'Q' ->{
        Rook rook =new Rook(color);
        Bishop bishop =new Bishop(color);
        if(rook.isValid(i, j, toX, toY, chessboard) || bishop.isValid(i, j, toX, toY, chessboard))return false;
    }
}

return true;
}
	private boolean isInBounds(int x, int y) {
	    return x >= 0 && x < 8 && y >= 0 && y < 8;
	}

    public boolean inCheck(JButton[][] chessboard){
        int kx=0,ky=3;
       outer2: for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(chessboard[i][j].getText().equals("WK") && this.color=='W'){
                     kx=i;
                     ky=j;
                     break outer2;
                }
                 if(chessboard[i][j].getText().equals("BK") && this.color=='B'){
                     kx=i;
                     ky=j;
                     break outer2;
                }           
           }
        }
        return !(isSafe(kx, ky, chessboard));
    }
    public King(char color) {
        super(color);
    }

    @Override
	public
    boolean isValid(int fromX, int fromY, int toX, int toY, JButton[][] chessboard) {
        if(!isSafe(toX, toY, chessboard))return false;
        if(((chessboard[toX][toY].getText().equals("")) || 
        chessboard[toX][toY].getText().charAt(0)!=this.color)){
        if((fromX+1 == toX || fromX-1 == toX) && toY == fromY)return true;
        else if(toX == fromX && (fromY+1 == toY || fromY-1 == toY))return true;
        int xstep=(toX > fromX) ? 1 : -1;
        int ystep=(toY > fromY) ? 1 : -1;
        if(toX==fromX+xstep && toY==fromY+ystep)return true;
        }
        return false;
    }
    public boolean castling(int fromX,int fromY,int toX,int toY,JButton chessboard[][]){
        int ystep=(toY > fromY) ? 1 : -1;
        if(isSafe(toX, toY, chessboard) && chessboard[toX][toY].getText().equals("") && isSafe(toX, fromY+ystep, chessboard)){
             if((this.color=='W' && fromX==0 && fromY==3) || (this.color=='B' && fromX==7 && fromY==3)){
            if(fromX==toX && (toY == fromY +2 || toY == fromY -2)){
                if(chessboard[fromX][fromY+ystep].getText().equals("")){
                    String selected;
                    ImageIcon icon;
                if(ystep==-1){
                    selected=chessboard[fromX][0].getText();
                    if(!selected.endsWith("R"))return false;
                    icon=(ImageIcon) chessboard[fromX][0].getIcon();
                    chessboard[fromX][0].setText("");
                    chessboard[fromX][0].setIcon(null);
                }else{
                    selected=chessboard[fromX][7].getText();
                    if(!selected.endsWith("R"))return false;
                    icon=(ImageIcon) chessboard[fromX][7].getIcon();
                    chessboard[fromX][7].setText("");
                    chessboard[fromX][7].setIcon(null);
                }
                chessboard[fromX][fromY+ystep].setText(selected);
                chessboard[fromX][fromY+ystep].setIcon(icon);
                return true;
                }
            }
             }
        }
        return false;
    }
    boolean isSafe(int toX, int toY, JButton[][] chessboard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String piece = chessboard[i][j].getText();
                if (!piece.isEmpty() && piece.charAt(0) != this.color) {
                    char enemyPiece = piece.charAt(1);
                    switch (enemyPiece) {
                        case 'P':
                            if (Math.abs(j - toY) == 1 && ((this.color == 'W' && toX == i + 1) || (this.color == 'B' && toX == i - 1))) {
                                return false;
                            }
                            break;
                        case 'R':
                            if (new Rook(piece.charAt(0)).isValid(i, j, toX, toY, chessboard)) return false;
                            break;
                        case 'N':
                            if (new Knight(piece.charAt(0)).isValid(i, j, toX, toY, chessboard)) return false;
                            break;
                        case 'B':
                            if (new Bishop(piece.charAt(0)).isValid(i, j, toX, toY, chessboard)) return false;
                            break;
                        case 'Q':
                            if (new Rook(piece.charAt(0)).isValid(i, j, toX, toY, chessboard) || new Bishop(piece.charAt(0)).isValid(i, j, toX, toY, chessboard) ) return false;
                            break;
                        case 'K':
                            if (Math.abs(toX - i) <= 1 && Math.abs(toY - j) <= 1) return false;
                            break;
                    }
                }
            }
        }
        return true;
    }

    public boolean anyValidMoves(JButton[][] chessboard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String piece = chessboard[i][j].getText();
                if (!piece.isEmpty() && piece.charAt(0) == this.color) {
                    char selectedPiece = piece.charAt(1);
                    if (validForPiece(chessboard, selectedPiece, this.color, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
  public boolean validForPiece(JButton[][] chessboard, char selectedPiece, char pieceColor, int i, int j) {
    	    for (int x = 0; x < 8; x++) {
    	        for (int y = 0; y < 8; y++) {
    	            switch (selectedPiece) {
    	                case 'P':  // Pawn
    	                    Pawn pawn = new Pawn(pieceColor);
    	                    if (pawn.isValid(i, j, x, y, chessboard)) return true;
    	                    break;
    	                case 'R':  // Rook
    	                    Rook rook = new Rook(pieceColor);
    	                    if (rook.isValid(i, j, x, y, chessboard)) return true;
    	                    break;
    	                case 'N':  // Knight
    	                    Knight knight = new Knight(pieceColor);
    	                    if (knight.isValid(i, j, x, y, chessboard)) return true;
    	                    break;
    	                case 'B':  // Bishop
    	                    Bishop bishop = new Bishop(pieceColor);
    	                    if (bishop.isValid(i, j, x, y, chessboard)) return true;
    	                    break;
    	                case 'Q':  // Queen
    	                	Rook rookQ = new Rook(pieceColor);
    	                	 Bishop bishopQ = new Bishop(pieceColor);
    	                    if(rookQ.isValid(i, j, x, y, chessboard) || bishopQ.isValid(i, j, x, y, chessboard)) return true;
    	                    break;
    	                case 'K':  // King
    	                    King king = new King(pieceColor);
    	                    if (king.isValid(i, j, x, y, chessboard)) return true;
    	                    break;
    	            }
    	        }
    	    }
    	    return false;
    	}

}
