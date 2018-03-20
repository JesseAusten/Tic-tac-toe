import java.util.Random;

public class BoardCombo {
	private char[][] board;
	private int size;
	
	public BoardCombo() {
		board = new char[3][3];
		for (int i = 0; i < 9; i++)
		    board[i % 3][i / 3] = ' ';
		size = 0;
	}
	
	public BoardCombo(char[][] c) {
		board = new char[3][3];
		for (int i = 0; i < 9; i++)
			board[i / 3][i % 3] = c[i / 3][i % 3];
	}
	
	public char[][] getBoard() {
		return board;
	}
	
	public void set(int row, int col, int player) {
		if (player == 1)
			board[row][col] = 'X';
		else
			board[row][col] = 'O';
		size++;
	}
	
	public char get(int row, int col) {
		return board[row][col];
	}
	
	public int[] getEmpty() {
		int empty[] = new int[9 - getSize()];
		int count = 0;
		for (int i = 0; i < 9; i++)
			if (board[i % 3][i / 3] == ' ') {
				empty[count] = i + 1;
				count++;
			}
		return empty;
	}
	
	public int getSize() {
		//return size;
		int count = 0;
		for (int i = 0; i < 9; i++)
			if (board[i % 3][i / 3] != ' ')
				count++;
		return count;
				
	}
	
	public void randomMove() {
		int[] empty = getEmpty();
		Random rand = new Random();
		int r = rand.nextInt(9 - getSize());
		int square = empty[r] - 1;
		if (size % 2 == 1)
			board[square / 3][square % 3] = 'X';
		else
			board[square / 3][square % 3] = 'O';
		size++;
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
		return (getSize() == 9);
	}
	
	public int lastMove() {
		if (getSize() % 2 == 1)
			return 1;
		else
			return 2;
	}
	
	public void printBoard() {
		System.out.println("   " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
		System.out.println("   " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
		System.out.println("   " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + "\n\n");
	}
}
