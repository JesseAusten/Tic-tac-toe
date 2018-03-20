
public class Board {
	private char[][] board;
	
	public Board() {
		board = new char[3][3];
		for (int i = 0; i < 9; i++)
		    board[i % 3][i / 3] = ' ';
	}
	
	public void set(int row, int col, int player) {
		if (player == 1)
			board[row][col] = 'X';
		else
			board[row][col] = 'O';
	}
	
	public boolean isTaken(int row, int col) {
		return (board[row][col] != ' ');
	}
	
	public boolean checkWin() {
		if (board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != ' ' ||
				board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] != ' ' ||
				board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != ' ' ||
				board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] != ' ' ||
				board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != ' ' ||
				board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][0] != ' ' ||
				board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != ' ' ||
				board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0] != ' ')
			return true;
		return false;
	}
	
	public boolean isFull() {
		boolean full = true;
		for (int i = 0; i < 9; i++)
			if (board[i % 3][i / 3] == ' ')
				full = false;
		
		return full;
	}
	
	public boolean isEmpty() {
		boolean empty = true;
		for (int i = 0; i < 9; i++)
			if (board[i % 3][i / 3] != ' ')
				empty = false;
		
		return empty;
	}
	
	public void resetBoard() {
		for (int i = 0; i < 9; i++)
		    board[i % 3][i / 3] = ' ';
	}
	
	// Format: X | O | 
	//         O |   | X
	//           | X | O
	public void printBoard() {
		System.out.println("   " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
		System.out.println("   " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
		System.out.println("   " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + "\n\n");
	}
}
