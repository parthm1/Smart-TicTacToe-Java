
package tictactoe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameBoardTest {

    @Test
    public void testValidMove() {

        GameBoard game = new GameBoard();

        boolean result = game.makeMove(0, 0, 'X');

        assertTrue(result);
    }
    

	@Test
	public void testOccupiedCell() {
	
	    GameBoard game = new GameBoard();
	
	    game.makeMove(0, 0, 'X');
	
	    boolean result = game.makeMove(0, 0, 'O');
	
	    assertFalse(result);
	}
	
	@Test
	public void testInvalidPosition() {

	    GameBoard game = new GameBoard();

	    boolean result = game.makeMove(5, 2, 'X');

	    assertFalse(result);
	}
	

	@Test
	public void testWinner() {
	
	    GameBoard game = new GameBoard();
	
	    game.makeMove(0, 0, 'X');
	    game.makeMove(0, 1, 'X');
	    game.makeMove(0, 2, 'X');
	
	    assertTrue(game.checkWinner('X'));
	    assertFalse(game.checkWinner('O'));
	}
	

	@Test
	public void testDraw() {
	
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
	
	    assertTrue(game.isBoardFull());
	    assertTrue(game.isDraw());
	}


}
