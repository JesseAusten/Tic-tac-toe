
public class Population {
	private Combos c[];
	private int size;
	
	public Population(int size) {
		this.size = size;
		c = new Combos[size];
		for (int i = 0; i < size; i++)
			c[i] = new Combos();
	}
	
	public int getScore(int num) {
		return c[num].getScore();
	}
	
	public int getBestScoreIndex() {
		int best = 0;
		for (int i = 0; i < size; i++)
			if (c[i].getScore() > c[best].getScore())
				best = i;
		System.out.println("Best score is: " + c[best].getScore());
		return best;
	}
	
	private void generateElement(int index) {
		c[index] = new Combos();
	}
	
	public void evolve() {
		Combos bestCombo = c[getBestScoreIndex()];
		c[0] = bestCombo;
		for (int i = 1; i < size; i++) {
				c[i] = new Combos(bestCombo);
				c[i].mutateResponse(100);
		}
	}
	
	public int findResponse(char[] inputBoard) {
		Combos best = c[getBestScoreIndex()];
		return best.findResponse(inputBoard);
	}
	public void printScores() {
		System.out.print("Scores: ");
		for (int i = 0; i < size; i++)
			System.out.print(c[i].getScore() + ", ");
	}
}
