package com.chess.engine.board;

import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.Player;
import com.chess.engine.player.WhitePlayer;

public enum Alliance {
	WHITE
 {
		@Override
		public int getDirection() {
			// If the players turn is "WHITE" returns 0
			return -1;
		}

		@Override
		public boolean isWhite() {
			// isWhite is set to true
		
			return true;
		}

		@Override
		public boolean isBlack() {
		   // isBlack set to false because the players turn is not black
			return false;
		}

		@Override
		protected Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
			return whitePlayer;
		}
	},	
	BLACK {
		@Override
		public int getDirection() {			
			// If the players turn is "BLACK" returns 1
			return 1;
		}

		@Override
		public boolean isWhite() {
		
			// isWhite is set to false
			return false;
		}

		@Override
		public boolean isBlack() {
			// isBlack set to true
			return true;
		}

		@Override
		protected Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
			return blackPlayer;
		}
	};
	
	//defines abstract int thats overrided above
	public abstract int getDirection(); 
	
	public abstract boolean isWhite();
	public abstract boolean isBlack();

	protected abstract Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
}
