
public class Population {
	private Combos c[];
	private int score[];
	private int size;
	
	public Population(int size) {
		this.size = size;
		c = new Combos[size];
		score = new int[size];
		for (int i = 0; i < size; i++) {
			c[i] = new Combos();
			score[i] = c[i].getScore();
			System.out.println("Score for member " + i + ": " + score[i]);
		}
	}
	
	public int getScore(int num) {
		return score[num];
	}
	
	public int bestScoreIndex() {
		int best = 0;
		for (int i = 0; i < size; i++)
			if (score[i] > score[best])
				best = i;
		System.out.println("Best score is: " + score[best]);
		return best;
	}
	
	public void generateElement(int index) {
		c[index] = new Combos();
		score[index] = c[index].getScore();
	}
	
	public void evolve() {
		Combos bestCombo = c[bestScoreIndex()];
		for (int i = 0; i < size; i++)
			if (i % 2 == 0) {
				c[i] = new Combos(bestCombo);
				score[i] = c[i].getScore();
			}
			else
				generateElement(i);
	}
	
	public void printScores() {
		System.out.print("Scores: ");
		for (int i = 0; i < size; i++)
			System.out.print(score[i] + ", ");
	}
}
