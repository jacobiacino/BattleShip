package battleship;

import acm.graphics.*;

public class CPUBoard extends GCompound{

	GRect[][] board;
	
	public CPUBoard (int x, int y) {
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
