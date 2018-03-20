
public class Board {
	private char[] board;
	
	public Board() {
		board = new char[9];
		for (int i = 0; i < 9; i++)
		    board[i] = ' ';
	}
	
	public void set(int square, int player) {
		if (player == 1)
			board[square-1] = 'X';
		else
			board[square-1] = 'O';
	}
	
	public boolean isTaken(int square) {
		return (board[square-1] != ' ');
	}
	
	public boolean checkWin() {
		if (board[0] == board[3] && board[0] == board[6] && board[0] != ' ' ||
				board[1] == board[4] && board[1] == board[7] && board[1] != ' ' ||
				board[2] == board[5] && board[2] == board[8] && board[2] != ' ' ||
				board[0] == board[1] && board[0] == board[2] && board[0] != ' ' ||
				board[3] == board[4] && board[3] == board[5] && board[3] != ' ' ||
				board[6] == board[7] && board[6] == board[8] && board[6] != ' ' ||
				board[0] == board[4] && board[0] == board[8] && board[0] != ' ' ||
				board[6] == board[4] && board[6] == board[2] && board[6] != ' ')
			return true;
		return false;
	}
	
	public boolean isFull() {
		for (int i = 0; i < 9; i++)
			if (board[i] == ' ')
				return false;
		
		return true;
	}
	
	public boolean isEmpty() {
		for (int i = 0; i < 9; i++)
			if (board[i] != ' ')
				return false;
		
		return true;
	}
	
	public void resetBoard() {
		for (int i = 0; i < 9; i++)
		    board[i] = ' ';
	}
	
	public char[] getBoard() {
		return board;
	}
	
	// Format: X | O | 
	//         O |   | X
	//           | X | O
	public void printBoard() {
		System.out.println("   " + board[0] + " | " + board[1] + " | " + board[2]);
		System.out.println("   " + board[3] + " | " + board[4] + " | " + board[5]);
		System.out.println("   " + board[6] + " | " + board[7] + " | " + board[8] + "\n\n");
	}
}
