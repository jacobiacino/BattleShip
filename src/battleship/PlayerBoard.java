package battleship;

import acm.graphics.*;

public class PlayerBoard extends GCompound{
	GRect[][] board;
	
	/*
	 * Initializes the Player's Board with the upper
	 * left corner at coordinates x and y
	 */
	public PlayerBoard(int x, int y) {
		board = new GRect[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				GRect boardElem = new GRect(x + 60*i, y + 60*j, 60, 60);
				board[i][j] = boardElem;
				this.add(boardElem);
			}
		}
	}
	
}
