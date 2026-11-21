package tictactoe;

public class AIPlayer {

	public int[] findMove(GameBoard game) {

	    int bestScore = Integer.MIN_VALUE;
	    int[] bestMove = null;

	    for (int row = 0; row < 3; row++) {
	        for (int col = 0; col < 3; col++) {

	            if (game.isEmpty(row, col)) {

	                // Try placing O
	                game.makeMove(row, col, 'O');

	                // Evaluate this move
	                int score = minimax(game, false, 0);

	                // Undo the move
	                game.clearCell(row, col);

	                // Remember the best move
	                if (score > bestScore) {
	                    bestScore = score;
	                    bestMove = new int[] {row, col};
	                }
	            }
	        }
	    }

	    return bestMove;
	}
	
	public int[] findEasyMove(GameBoard game) {

	    for (int row = 0; row < 3; row++) {
	        for (int col = 0; col < 3; col++) {

	            if (game.isEmpty(row, col)) {
	                return new int[] {row, col};
	            }
	        }
	    }

	    return null;
	}
    
	private int minimax(GameBoard game, boolean isMaximizing, int depth) {

		if (game.checkWinner('O')) {
		    return 10 - depth;
		}

		if (game.checkWinner('X')) {
		    return depth - 10;
		}

        if (game.isDraw()) {
            return 0;
        }

        if (isMaximizing) {

            int bestScore = Integer.MIN_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    if (game.isEmpty(row, col)) {

                        game.makeMove(row, col, 'O');

                        int score = minimax(game, false, depth + 1);

                        game.clearCell(row, col);

                        bestScore = Math.max(bestScore, score);
                    }
                }
            }

            return bestScore;

        } else {

            int bestScore = Integer.MAX_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    if (game.isEmpty(row, col)) {

                        game.makeMove(row, col, 'X');

                        int score = minimax(game, true, depth + 1);

                        game.clearCell(row, col);

                        bestScore = Math.min(bestScore, score);
                    }
                }
            }

            return bestScore;
        }
    }
}
