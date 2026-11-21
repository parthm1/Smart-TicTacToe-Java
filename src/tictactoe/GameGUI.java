
package tictactoe;

import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame {
	
	
	private JLabel statusLabel;
	private JComboBox<String> difficultyBox;
    private JButton[][] buttons;
    private GameBoard game;
    private char currentPlayer;
    private boolean gameOver;
    private AIPlayer ai;
    
    private int humanWins = 0;
    private int computerWins = 0;
    private int draws = 0;

    private JLabel scoreLabel;

    public GameGUI() {
    	
    	game = new GameBoard();
    	currentPlayer = 'X';
    	gameOver = false;
    	ai = new AIPlayer();
    	
        setTitle("Smart Tic Tac Toe");

        setSize(400, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        

		scoreLabel = new JLabel("You: 0 | Computer: 0 | Draws: 0");
		
		scoreLabel.setHorizontalAlignment(
		    SwingConstants.CENTER
		);
		
		scoreLabel.setFont(
		    new Font("Arial", Font.BOLD, 18)
		);
		
		JPanel topPanel = new JPanel(new GridLayout(3, 1));

		topPanel.add(scoreLabel);

		difficultyBox = new JComboBox<>(
		    new String[] {"Easy", "Hard"}
		);

		difficultyBox.setSelectedItem("Hard");

		topPanel.add(difficultyBox);

		statusLabel = new JLabel("Your turn - X");

		statusLabel.setHorizontalAlignment(
		    SwingConstants.CENTER
		);

		statusLabel.setFont(
		    new Font("Arial", Font.BOLD, 16)
		);

		topPanel.add(statusLabel);
		
		add(topPanel, BorderLayout.NORTH);
		
		difficultyBox.addActionListener(e -> {

		    game = new GameBoard();
		    gameOver = false;
		    
		    

		    for (int i = 0; i < 3; i++) {
		        for (int j = 0; j < 3; j++) {
		            buttons[i][j].setText("");
		        }
		    }
		});

        buttons = new JButton[3][3];
        
        JPanel boardPanel = new JPanel();

        boardPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                buttons[i][j] = new JButton("");

                buttons[i][j].setFont(
                    new Font("Arial", Font.BOLD, 60)
                );
                
                final int row = i;
                final int col = j;

                buttons[i][j].addActionListener(e -> {

                    if (gameOver) {
                        return;
                    }

                    // Human player makes a move
                    if (game.makeMove(row, col, 'X')) {

                        buttons[row][col].setText("X");

                        // Check whether the human won
                        if (game.checkWinner('X')) {

                            gameOver = true;
                            statusLabel.setText("You win!");
                            
                            humanWins++;
                            updateScoreboard();

                            JOptionPane.showMessageDialog(
                                this, "You win!"
                            );

                            return;
                        }

                        // Check whether the game is a draw
                        if (game.isDraw()) {

                            gameOver = true;
                            statusLabel.setText("It's a draw!");
                            
                            draws++;
                            updateScoreboard();

                            JOptionPane.showMessageDialog(
                                this, "It's a draw!"
                            );

                            return;
                        }

                        // Computer chooses a move
                        int[] move;

                        String difficulty =
                            (String) difficultyBox.getSelectedItem();

                        if (difficulty.equals("Easy")) {
                            move = ai.findEasyMove(game);
                        } else {
                            move = ai.findMove(game);
                        }

                        if (move != null) {

                            int aiRow = move[0];
                            int aiCol = move[1];

                            game.makeMove(aiRow, aiCol, 'O');

                            buttons[aiRow][aiCol].setText("O");

                            // Check whether computer won
                            if (game.checkWinner('O')) {

                                gameOver = true;
                                statusLabel.setText("Computer wins!");
                                
                                computerWins++;
                                updateScoreboard();

                                JOptionPane.showMessageDialog(
                                    this, "Computer wins!"
                                );

                            } else if (game.isDraw()) {

                                gameOver = true;
                                statusLabel.setText("It's a draw!");
                                
                                draws++;
                                updateScoreboard();

                                JOptionPane.showMessageDialog(
                                    this, "It's a draw!"
                                );
                            }
                        }
                    }
                });
                
                
                boardPanel.add(buttons[i][j]);
            }
        }
        
        add(boardPanel, BorderLayout.CENTER);
        
		JButton restartButton = new JButton("Restart Game");
		
		restartButton.addActionListener(e -> {
		
		    game = new GameBoard();
		
		    currentPlayer = 'X';
		
		    gameOver = false;
		    
		    statusLabel.setText("Your turn - X");
		
		    for (int i = 0; i < 3; i++) {
		        for (int j = 0; j < 3; j++) {
		            buttons[i][j].setText("");
		        }
		    }
		});
		
		add(restartButton, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void updateScoreboard() {

        scoreLabel.setText(
            "You: " + humanWins +
            " | Computer: " + computerWins +
            " | Draws: " + draws
        );
    }
}
