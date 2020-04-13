package com.snakeandladder.services;

import com.snakeandladder.models.*;
import com.snakeandladder.services.*;

import java.util.*;

public class SnakeAndLadderService {

		private SnakeAndLadderBoard snakeAndLadderBoard;
		private int initialNumberOfPlayers;
		private Queue<Player> players;
		private boolean isGameCompleted;
		
		private int noOfDices;
		private boolean shouldGameContinueTillTheLastPlayer;
		private boolean shouldAllowMultipleDiceRollsOnSix;
		
		private static final int DEFAULT_BOARD_SIZE = 100;
		private static final int DEFAULT_NO_OF_DICES = 1;
		
		public SnakeAndLadderService(int boardSize){
			this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);
			this.players = new LinkedList<Player>();
			this.noOfDices = DEFAULT_NO_OF_DICES;
		}
		
		public SnakeAndLadderService(){
			this(DEFAULT_BOARD_SIZE);
		}
		
		
		//Setters for making the game more extensible
		
		public void setNoOfDices(int noOfDice){
			this.noOfDices = noOfDice;
		}
		
		public void setShouldGameContinueTillTheLastPlayer(boolean shouldGameContinueTillTheLastPlayer) {
			this.shouldGameContinueTillTheLastPlayer = shouldGameContinueTillTheLastPlayer;
		}

		public void setShouldAllowMultipleDiceRollsOnSix(boolean shouldAllowMultipleDiceRollsOnSix) {
			this.shouldAllowMultipleDiceRollsOnSix = shouldAllowMultipleDiceRollsOnSix;
		}
		
		//Initialise the board
		
		public void setPlayers(List<Player> players){
			this.players = new LinkedList<Player>();
			this.initialNumberOfPlayers = players.size();
			Map<String,Integer> playerPieces = new HashMap<String, Integer>();
			
			
			for(Player player:players){
				this.players.add(player);
				playerPieces.put(player.getId(),0);  //each playes starting position is 0
			}
			snakeAndLadderBoard.setPlayerPieces(playerPieces); //Add pieces to the board
		}
		
		public void setSnakes(List<Snake> snakes){
			snakeAndLadderBoard.setSnakes(snakes);  // Add snakes to board
		}
		
		public void setLadders(List<Ladder> ladders){
			snakeAndLadderBoard.setLadders(ladders);   // Add ladders to board
		}
		
		//Core Business Logic  for the game
		
		
		private boolean isGameCompleted() {
	        // Can use shouldGameContinueTillLastPlayer to change the logic of determining if game is completed (Optional requirements)
	        int currentNumberOfPlayers = players.size();
//	        return currentNumberOfPlayers < initialNumberOfPlayers;
	        return currentNumberOfPlayers<2;
	    }
		
		public void startGame(){
			while(!isGameCompleted()){
				int totalDiceValue = getTotalValueAfterDiceRolls();
				Player currentPlayer = players.poll();
				movePlayer(currentPlayer,totalDiceValue);
				
				if(hasPlayerWon(currentPlayer)){
					System.out.println(currentPlayer.getName() + " has won");
					snakeAndLadderBoard.getPlayerPieces().remove(currentPlayer.getId());
					players.remove(currentPlayer);
				}else{
					players.add(currentPlayer);
				}
			}
		}
		
		private int getTotalValueAfterDiceRolls(){
			int totalValueAfterDiceRolls=0;
			int temp = noOfDices;
			
				while(temp-->0){
					totalValueAfterDiceRolls += DiceService.roll();
				}
		
			return totalValueAfterDiceRolls;
		}
		
		private void movePlayer(Player currentPlayer,int totalDiceValue){
			
			int boardSize = snakeAndLadderBoard.getSize();
			int oldPosition = snakeAndLadderBoard.getPlayerPieces().get(currentPlayer.getId());
		
			int newPosition = oldPosition+totalDiceValue;
			
			if (newPosition > boardSize) {
	            newPosition = oldPosition; // After the dice roll, if a piece is supposed to move outside position 100, it does not move.
	        } else {
	            newPosition = getNewPositionAfterGoingThroughSnakesAndLadders(newPosition);
	        }
			
			
			snakeAndLadderBoard.getPlayerPieces().put(currentPlayer.getId(),
					newPosition );
			
			System.out.println(currentPlayer.getName() + " has rolled a " + 
			totalDiceValue + " and moved from " + oldPosition + " to " + newPosition);
		}
		
		private boolean hasPlayerWon(Player currentPlayer){
			
			return snakeAndLadderBoard.getPlayerPieces().get(currentPlayer.getId())==snakeAndLadderBoard.getSize();	
			
		}
		
		private int getNewPositionAfterGoingThroughSnakesAndLadders(int newPosition){
			
			int prevPosition;
			
			do{
				prevPosition = newPosition;
				
				for(Ladder ladder : snakeAndLadderBoard.getLadders()){
					if(ladder.getStart()==newPosition){
						newPosition = ladder.getEnd();
						break;
					}
				}
				
				for(Snake snake : snakeAndLadderBoard.getSnakes()){
					if(snake.getStart()==newPosition){
						newPosition = snake.getEnd();
						break;
					}
				}
					
			}while(prevPosition!=newPosition);
				
			return newPosition;
		}
		
}
		
	