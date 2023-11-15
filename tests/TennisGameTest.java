import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		// This statement should cause an exception
		game.player2Scored();
	}
	
	@Test
	public void testTennisGame_Player1_Walkover_Victory() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		String firstPoint = game.getScore();
		assertEquals("Player 1 First Point Incorrect", "love - 15", firstPoint);
		
		game.player1Scored();
		String secondPoint = game.getScore();
		assertEquals("Player 1 Second Point Incorrect", "love - 30", secondPoint);
		
		game.player1Scored();
		String thirdPoint = game.getScore();
		assertEquals("Player 1 Third Point Incorrect", "love - 40", thirdPoint);
		//Final Score
		game.player1Scored();
		String finalScore = game.getScore();
		assertEquals("Player 1 Win Incorrect", "player1 wins", finalScore);
	}
	
	@Test
	public void testTennisGame_Player2_Walkover_Victory() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		String firstPoint = game.getScore();
		assertEquals("Player 2 First Point Incorrect", "15 - love", firstPoint);
		
		game.player2Scored();
		String secondPoint = game.getScore();
		assertEquals("Player 2 Second Point Incorrect", "30 - love", secondPoint);
		
		game.player2Scored();
		String thirdPoint = game.getScore();
		assertEquals("Player 2 Third Point Incorrect", "40 - love", thirdPoint);
		//Final Score
		game.player2Scored();
		String finalScore = game.getScore();
		assertEquals("Player 2 Win Incorrect", "player2 wins", finalScore);
	}
	
	@Test
	public void testTennisGame_tiebreaks() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player2Scored();

		game.player1Scored();
		game.player2Scored();
		
		game.player1Scored(); 
		game.player2Scored();

		game.player1Scored();
		String score = game.getScore();
		assertEquals("Player1 Tiebreak Advantage Incorrect", "player1 has advantage", score);
		
		game.player2Scored();
		game.player2Scored();
		score = game.getScore();
		assertEquals("Player2 Tiebreak Advantage Incorrect", "player2 has advantage", score);
	}
	
}
