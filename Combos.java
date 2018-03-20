import java.util.Random;

/* 
 * Generate permutations, generate random responses
 * 
 * 
 */
public class Combos {

	private BoardCombo[] boards;
	private int[] response;
	private int score;
	
	public Combos(Combos c) {		
		boards = new BoardCombo[197073];
		response = new int[197073];
		score = c.getScore();
		
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
			boards[index].set(i+1, 1);
			index++;
		}
		
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				for (int k = 0; k < 9; k++) {
					if (!(i == j || i == k || j == k)) {
						boards[index] = new BoardCombo();
						boards[index].set(i+1, 1);
						boards[index].set(j+1, 2);
						boards[index].set(k+1, 1);
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
								boards[index].set(i+1, 1);
								boards[index].set(j+1, 2);
								boards[index].set(k+1, 1);
								boards[index].set(l+1, 2);
								boards[index].set(m+1, 1);
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
										boards[index].set(i+1, 1);
										boards[index].set(j+1, 2);
										boards[index].set(k+1, 1);
										boards[index].set(l+1, 2);
										boards[index].set(m+1, 1);
										boards[index].set(n+1, 2);
										boards[index].set(o+1, 1);
										index++;
									}
								}
		
		for (int i = 0; i < 197073; i++) 
			response[i] = boards[i].getRandomMove();
			
		setScore();
		
		long endTime = System.nanoTime();
		//System.out.println("Combos constructor took " + (endTime - startTime) / 1000000 + " milliseconds to populate 197,073 boards and responses.");
	}
	
	// Modifies 1/freq of the responses: 1 = all, 2 = 50%, 3 = 33%...
	public void mutateResponse(int freq) {
		Random rand = new Random();
		for (int i = rand.nextInt(freq); i < 197073; i+=freq)
			response[i] = boards[i].getRandomMove();
		
		setScore();
	}
	
	public void setScore() {
		score = 0;
		BoardCombo b;
		for (int i = 0; i < 197073; i++) {
			b = new BoardCombo(boards[i].getBoard());
			b.set(response[i], 2);
			
			while (!b.isFull() && !b.checkWin())
				b.randomMove();
			
			if (b.checkWin() && b.lastMove() == 2)
				score += 5;
			else if (b.checkWin())
				score -= 5;
			else
				score++;
		}
	}
	
	public int getScore() {
		return score;
	}
	
	public BoardCombo getBoard(int index) {
		return boards[index];
	}
	
	public int getResponse(int index) {
		return response[index];
	}
	
	public int findResponse(char[] c) {
		boolean found = true;
		for (int i = 0; i < 197073; i++) {
			for (int j = 1; j <= 9; j++)
				if (boards[i].get(j) != c[j-1]) {
					found = false;
					break;
				}
			if (found)
				return response[i];
			found = true;
		}
		return -1;
	}
}
