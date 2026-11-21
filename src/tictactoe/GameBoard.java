
package tictactoe;

public class GameBoard {

    private char[][] board;

    public GameBoard() {
        board = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }
    
	public void displayBoard() {
	    for (int i = 0; i < 3; i++) {
	        System.out.println(" " + board[i][0] + " | "
	                + board[i][1] + " | " + board[i][2]);
	
	        if (i < 2) {
	            System.out.println("-----------");
	        }
	    }
	}
	
	public boolean makeMove(int row, int col, char player) {
	
	    if (row < 0 || row >= 3 || col < 0 || col >= 3) {
	        return false;
	    }
	
	    if (board[row][col] != ' ') {
	        return false;
	    }
	
	    if (player != 'X' && player != 'O') {
	        return false;
	    }
	
	    board[row][col] = player;
	    return true;
	}
	

	public boolean checkWinner(char player) {
	
	    // Check all rows
	    for (int i = 0; i < 3; i++) {
	        if (board[i][0] == player &&
	            board[i][1] == player &&
	            board[i][2] == player) {
	            return true;
	        }
	    }
	
	    // Check all columns
	    for (int i = 0; i < 3; i++) {
	        if (board[0][i] == player &&
	            board[1][i] == player &&
	            board[2][i] == player) {
	            return true;
	        }
	    }
	
	    // Check both diagonals
	    if (board[0][0] == player &&
	        board[1][1] == player &&
	        board[2][2] == player) {
	        return true;
	    }
	
	    if (board[0][2] == player &&
	        board[1][1] == player &&
	        board[2][0] == player) {
	        return true;
	    }
	
	    return false;
	}
	
	public boolean isBoardFull() {

	    for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < 3; j++) {

	            if (board[i][j] == ' ') {
	                return false;
	            }
	        }
	    }

	    return true;
	}
	
	public boolean isDraw() {

	    return isBoardFull() &&
	           !checkWinner('X') &&
	           !checkWinner('O');
	}
	

	public boolean isEmpty(int row, int col) {
	
	    if (row < 0 || row >= 3 || col < 0 || col >= 3) {
	        return false;
	    }
	
	    return board[row][col] == ' ';
	}
	

	public void clearCell(int row, int col) {
	    board[row][col] = ' ';
	}
	
	public char getCell(int row, int col) {
	    return board[row][col];
	}



}
