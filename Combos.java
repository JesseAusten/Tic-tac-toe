import java.util.Random;

/* 
 * Generate permutations, generate random responses
 * 
 * 
 */
public class Combos {

	private BoardCombo[] boards;
	private int[] response;
	
	public Combos(Combos c) {		
		boards = new BoardCombo[197073];
		response = new int[197073];
		
		for (int i = 0; i < 197073; i++) {
			boards[i] = new BoardCombo(c.getBoard(i).getBoard());
			response[i] = c.getResponse(i);
		}
	}
	
	public Combos() {
		boards = new BoardCombo[197073];
		response = new int[197073];
		
		
		long startTime = System.nanoTime();
		
		int index = 0;
		for (int i = 0; i < 9; i++) {
			boards[index] = new BoardCombo();
			boards[index].set(i % 3, i / 3, 1);
			index++;
		}
		
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				for (int k = 0; k < 9; k++) {
					if (!(i == j || i == k || j == k)) {
						boards[index] = new BoardCombo();
						boards[index].set(i % 3, i / 3, 1);
						boards[index].set(j % 3, j / 3, 2);
						boards[index].set(k % 3, k / 3, 1);
						index++;
					}
				}
		
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				for (int k = 0; k < 9; k++)
					for (int l = 0; l < 9; l++)
						for (int m = 0; m < 9; m++) {
							if (!(i == j || i == k || i == l || i == m || j == k || j == l || j == m || k == l || k == m || l == m)) {
								boards[index] = new BoardCombo();
								boards[index].set(i % 3, i / 3, 1);
								boards[index].set(j % 3, j / 3, 2);
								boards[index].set(k % 3, k / 3, 1);
								boards[index].set(l % 3, l / 3, 2);
								boards[index].set(m % 3, m / 3, 1);
								index++; 
							}
						}
		
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				for (int k = 0; k < 9; k++)
					for (int l = 0; l < 9; l++)
						for (int m = 0; m < 9; m++)
							for (int n = 0; n < 9; n++)
								for (int o = 0; o < 9; o++) {
									if (!(i == j || i == k || i == l || i == m || i == n || i == o || j == k || j == l || j == m || j == n || j == o ||
											k == l || k == m || k == n || k == o || l == m || l == n || l == o || m == n || m == o || n == o)) {
										boards[index] = new BoardCombo();
										boards[index].set(i % 3, i / 3, 1);
										boards[index].set(j % 3, j / 3, 2);
										boards[index].set(k % 3, k / 3, 1);
										boards[index].set(l % 3, l / 3, 2);
										boards[index].set(m % 3, m / 3, 1);
										boards[index].set(n % 3, n / 3, 2);
										boards[index].set(o % 3, o / 3, 1);
										index++;
									}
								}
		
		Random rand = new Random();
		int r;
		for (int i = 0; i < 197073; i++) {
			r = rand.nextInt(9 - boards[i].getSize());
			response[i] = boards[i].getEmpty()[r ];
		}
		
		long endTime = System.nanoTime();
		//System.out.println("Combos constructor took " + (endTime - startTime) / 1000000 + " milliseconds to populate 197,073 boards and responses.");
	}
	
	public int getScore() {
		int score = 0;
		for (int i = 0; i < 197073; i++) {
			while (!boards[i].isFull() && !boards[i].checkWin()) {
				//System.out.println("Board " + i);
				boards[i].randomMove();
			}
			if (boards[i].checkWin() && boards[i].lastMove() == 2)
				score += 5;
			else if (boards[i].isFull())
				score++;
			else
				score -= 5;
		}
		return score;
	}
	
	public BoardCombo getBoard(int index) {
		return boards[index];
	}
	
	public int getResponse(int index) {
		return response[index];
	}
}
