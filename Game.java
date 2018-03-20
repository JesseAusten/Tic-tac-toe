
public class Game {
	
	private Board board;
	private int player;
	
	// 0 is human, 1 is ai
	public Game(int mode) {
		board = new Board();
		player = 1;
	}
	
	public void setPlayer(int player) {
		this.player = player;
	}
	
	public void advancePlayer() {
		player = (player % 2) + 1;
	}
	
	public int getPlayer() {
		return player;
	}
	
	public void sendTurn(int player, int button) {
		board.set(button, player);
	}
	
	public boolean checkWin(int player) {
	    return board.checkWin();
	}
	
	public boolean isFull() {
		return board.isFull();
	}
	
	public boolean isEmpty() {
		return board.isEmpty();
	}
	
	public void resetGame() {
		board.resetBoard();
		player = 1;
	}
	
	public boolean isSquareEmpty(int square) {
		return (!board.isTaken(square));
	}
	
	public char[] getBoard() {
		return board.getBoard();
	}
	
	public void printBoard() {
		board.printBoard();
	}
}
