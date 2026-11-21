package tictactoe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AIPlayerTest {

    @Test
    public void testAIWinningMove() {

        GameBoard game = new GameBoard();

        game.makeMove(0, 0, 'O');
        game.makeMove(0, 1, 'O');

        game.makeMove(1, 0, 'X');
        game.makeMove(1, 1, 'X');

        AIPlayer ai = new AIPlayer();

        int[] move = ai.findMove(game);

        assertArrayEquals(new int[] {0, 2}, move);
    }
    

	@Test
	public void testAIBlocksHuman() {
	
	    GameBoard game = new GameBoard();
	
	    game.makeMove(0, 0, 'X');
	    game.makeMove(0, 1, 'X');
	
	    game.makeMove(1, 1, 'O');
	
	    AIPlayer ai = new AIPlayer();
	
	    int[] move = ai.findMove(game);
	
	    assertArrayEquals(new int[] {0, 2}, move);
	}
	
	@Test
	public void testAIDoesNotModifyBoard() {

	    GameBoard game = new GameBoard();

	    game.makeMove(0, 0, 'X');

	    AIPlayer ai = new AIPlayer();

	    ai.findMove(game);

	    assertEquals('X', game.getCell(0, 0));

	    assertTrue(game.isEmpty(0, 1));
	    assertTrue(game.isEmpty(1, 1));
	    assertTrue(game.isEmpty(2, 2));
	}
	
	@Test
	public void testAIFullBoard() {

	    GameBoard game = new GameBoard();

	    game.makeMove(0, 0, 'X');
	    game.makeMove(0, 1, 'O');
	    game.makeMove(0, 2, 'X');

	    game.makeMove(1, 0, 'X');
	    game.makeMove(1, 1, 'O');
	    game.makeMove(1, 2, 'O');

	    game.makeMove(2, 0, 'O');
	    game.makeMove(2, 1, 'X');
	    game.makeMove(2, 2, 'X');

	    AIPlayer ai = new AIPlayer();

	    int[] move = ai.findMove(game);

	    assertNull(move);
	}

}
