package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.chess.engine.board.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

public class BlackPlayer extends Player {

	public BlackPlayer(final Board board, final Collection<Move> whiteStandardLegalMoves,
			final Collection<Move> blackStandardLegalMoves) {
		
		super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
	}

	@Override
	public Collection<Piece> getActivePieces() {
		return this.board.getBlackPieces();
	}

	@Override
	public Alliance getAlliance() {
		// TODO Auto-generated method stub
		return Alliance.BLACK;
	}

	@Override
	public Player getOpponent() {
		// TODO Auto-generated method stub
		return this.board.whitePlayer();
	}

	@Override
	protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals, final Collection<Move> opponentLegals) {
		final List<Move> kingCastles = new ArrayList<>();
		
		if(this.playerKing.isFirstMove() && !this.isInCheck()) {
			//BLACK king side castle
			if(!this.board.getTile(5).isTileOccupied() && !this.board.getTile(6).isTileOccupied()) {
				final Tile rookTile = this.board.getTile(7); 
				if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
					 if(Player.calculateAttacksOnTile(5, opponentLegals).isEmpty() &&
							 Player.calculateAttacksOnTile(6, opponentLegals).isEmpty() &&
							 rookTile.getPiece().getPieceType().isRook()) {
					
				
						 kingCastles.add(new Move.KingSideCastleMove(this.board,
										playerKing, 6, 
										(Rook)rookTile.getPiece(),
										rookTile.getTileCoordinate(),
										5));
					 }
				}
			}		
			 if(!this.board.getTile(1).isTileOccupied() && 
				!this.board.getTile(2).isTileOccupied() &&
			    !this.board.getTile(3).isTileOccupied())	{
				 
				 final Tile rookTile = this.board.getTile(0);
				 if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove() &&
					Player.calculateAttacksOnTile(2, opponentLegals).isEmpty() &&
					Player.calculateAttacksOnTile(3, opponentLegals).isEmpty() &&
					rookTile.getPiece().getPieceType().isRook()) {
					 kingCastles.add(new Move.QueenSideCastleMove(this.board,
									 playerKing, 2, 
									 (Rook)rookTile.getPiece(),
									 rookTile.getTileCoordinate(),
									 3));
					 }	 
				 }	
		    }
		return Collections.unmodifiableList(kingCastles);
	}

}

		

