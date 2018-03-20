import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class GameGUI extends JFrame implements ActionListener {

	private Game game;
	private String mode;
	
	private Image x_img;
	private Image o_img;	
	
	private JPanel panel, topPanel, gamePanel, bottomPanel, bottomLeftPanel, bottomRightPanel, bottomRTPanel, bottomRBPanel;
	private JButton[] buttons;
	private JButton humanButton, aiButton, playButton;
	private JLabel label;
	private JSlider slider;
	
	public GameGUI() {
		panel = new JPanel();
		topPanel = new JPanel();
		gamePanel = new JPanel();
		bottomPanel = new JPanel();
		bottomLeftPanel = new JPanel();
		bottomRightPanel = new JPanel();
		bottomRTPanel = new JPanel();
		bottomRBPanel = new JPanel();

		// Main Panel
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		setSize(500, 650);
		
		// Top Panel
		topPanel.setPreferredSize(new Dimension(500, 30));
		label = new JLabel("Player 1's turn.");
		label.setFont(new Font(label.getFont().getName(), Font.BOLD, 16));
		topPanel.add(label);
		
		// Game Panel
		gamePanel.setLayout(new GridLayout(3, 3));
		gamePanel.setPreferredSize(new Dimension(500, 500));
		try {
			Image x = ImageIO.read(getClass().getResource("/images/x.png"));
			Image o = ImageIO.read(getClass().getResource("/images/o.png"));
			x_img = x.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
			o_img = o.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
		} catch (IOException e1) { e1.printStackTrace(); }
		
		buttons = new JButton[9];
		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
			buttons[i].setActionCommand((i+1) + "");
			buttons[i].addActionListener(this);
			gamePanel.add(buttons[i]);
		}
	
		
		// Bottom Panels
		bottomPanel.setPreferredSize(new Dimension(500, 120));
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		
		// Bottom Left Panel
		bottomLeftPanel.setPreferredSize(new Dimension(100, 120));
		bottomLeftPanel.setLayout(new GridLayout(2, 1));	
		humanButton = new JButton("Human");
		humanButton.setActionCommand("human");
		humanButton.addActionListener(this);
		aiButton = new JButton("AI");
		aiButton.setActionCommand("ai");
		aiButton.addActionListener(this);
		
		bottomLeftPanel.add(humanButton);
		bottomLeftPanel.add(aiButton);
		
		// Bottom Right Top Panel
		bottomRightPanel.setPreferredSize(new Dimension(400, 120));
		bottomRightPanel.setLayout(new GridLayout(3, 1));	
		
		bottomRTPanel.setLayout(new GridLayout(1, 3));
		playButton = new JButton("Play Again");
		playButton.setActionCommand("playAgain");
		playButton.addActionListener(this);
		bottomRTPanel.add(new JLabel());
		bottomRTPanel.add(playButton);
		bottomRTPanel.add(new JLabel());
		bottomRightPanel.add(bottomRTPanel);
		bottomRightPanel.add(new JLabel("Number of Generations Trained", JLabel.CENTER));

		// Bottom Right Bottom Panel
		slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		slider.setMajorTickSpacing(20);
		slider.setPaintLabels(true);
		bottomRBPanel.add(slider);
		bottomRightPanel.add(bottomRBPanel);
		
		bottomPanel.add(bottomLeftPanel);
		bottomPanel.add(bottomRightPanel);
		
		panel.add(topPanel);
		panel.add(gamePanel);
		panel.add(bottomPanel);
		add(panel);
		
		setTitle("Tic-Tac-Toe");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		setup();
		Population p = new Population(10);
		for (int i = 0; i < 10; i++) {
			System.out.println("Generation: " + (i+1));
			p.evolve();
			p.printScores();
		}
	}
	
	public void setup() {
		game = new Game(0);
		game.setPlayer(1);
		mode = "human";
	}
	
	public void endCurrentGame() {
		
		// Disable all board tiles and print game message
		for (int i = 0; i < 9; i++)
			buttons[i].setEnabled(false);
		
		if (game.checkWin(game.getPlayer())) {
			System.out.println("Congratulations, Player " + game.getPlayer() + " won!");
			setLabel("Congratulations, Player " + game.getPlayer() + " won!");
		}
		else if (game.isFull()) {
			System.out.println("It's a tie!");
			setLabel("It's a tie!");
		}	
	}
	
	public void resetGame() {
		clearButtons();
		game.resetGame();
		setLabel("Game was reset. Player " + game.getPlayer() + "'s turn.");
	}
	
	public void setButton(JButton button, int button_num) {
		if (!button.isEnabled())
			return;
		
		button.setEnabled(false);
		if (game.getPlayer() == 1)
			button.setIcon(new ImageIcon(x_img));
		else
			button.setIcon(new ImageIcon(o_img));
	}
	
	public void clearButtons() {
		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(true);
			buttons[i].setIcon(null);
		}
	}
	
	public void setLabel(String text) {
		label.setText(text);
	}

	/* Action Commands for Buttons:
	 * 
	 * Play Again: 	"playAgain"
	 * Human Mode:	"human"
	 * AI Mode:		"ai"
	 * Game Tile:	Gives the tile number (1-9)
	 * 
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String command = button.getActionCommand();
		System.out.println("Button Command Pressed: " + command);
		
		if (command == "playAgain") {
			resetGame();
		}
		else if (command == "human") {
			if (mode == "human")
				setLabel("You are already in Human vs. Human mode");
			else if (!game.isEmpty())
				setLabel("You can only switch modes at the beginning of the game.");
			else {
				setLabel("NOT YET IMPLEMENTED");
			}
		}
		else if (command == "ai") {
			if (mode == "ai")
				setLabel("You are already in Human vs. AI mode");
			else if (!game.isEmpty())
				setLabel("You can only switch modes at the beginning of the game.");
			else {
				setLabel("NOT YET IMPLEMENTED");
			}
		}
		else {	// It is a game tile (1-9)
			
			
			// Set button press
			int buttonNum = Integer.parseInt(command);
			setButton(buttons[buttonNum - 1], buttonNum);
			game.sendTurn(game.getPlayer(), buttonNum);
			
			// Check for end of game
			if (game.checkWin(game.getPlayer()) || game.isFull()) {
				endCurrentGame();
				return;
			}
						
			game.advancePlayer();
			System.out.println("Player " + game.getPlayer() + "'s turn.");
			setLabel("Player " + game.getPlayer() + "'s turn.");
		}
		
	}
}
