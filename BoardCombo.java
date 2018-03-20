import java.util.Random;

public class BoardCombo {
	private char[] board;
	private int size;
	
	public BoardCombo() {
		board = new char[9];
		size = 0;
		for (int i = 0; i < 9; i++)
			board[i] = ' ';
	}
	
	public BoardCombo(char[] c) {
		board = new char[9];
		size = 0;
		for (int i = 0; i < 9; i++)
			if (c[i] == ' ')
				board[i] = ' ';
			else {
				board[i] = c[i];
				size++;
			}
	}
	
	public char[] getBoard() {
		return board;
	}
	
	public void set(int square, int player) {
		if (board[square-1] == ' ')
			size++;
		if (player == 1)
			board[square-1] = 'X';
		else
			board[square-1] = 'O';
	}
	
	public char get(int square) {
		return board[square-1];
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isFull() {
		return (size == 9);
	}
	
	public int lastMove() {
		if (size % 2 == 1)
			return 1;
		else
			return 2;
	}
	
	public int[] getEmpty() {
		int empty[] = new int[9 - size];
		int index = 0;
		for (int i = 0; i < 9; i++)
			if (board[i] == ' ') {
				empty[index] = i + 1;
				index++;
			}
		return empty;
	}
	
	public void randomMove() {
		int[] empty = getEmpty();
		Random rand = new Random();
		int r = rand.nextInt(empty.length);
		int square = empty[r];
		if (size % 2 == 1)
			board[square-1] = 'O';
		else
			board[square-1] = 'X';
		size++;
	}
	
	public int getRandomMove() {
		int[] empty = getEmpty();
		Random rand = new Random();
		int r = rand.nextInt(empty.length);
		return empty[r];
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
	
	public void printBoard() {
		System.out.println("   " + board[0] + " | " + board[1] + " | " + board[2]);
		System.out.println("   " + board[3] + " | " + board[4] + " | " + board[5]);
		System.out.println("   " + board[6] + " | " + board[7] + " | " + board[8] + "\n\n");
	}
}
