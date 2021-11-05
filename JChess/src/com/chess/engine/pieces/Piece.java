package com.chess.engine.pieces;

import java.util.Collection;

import com.chess.engine.board.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public abstract class Piece {
	
	protected final PieceType pieceType;
	protected final int piecePosition; 
	protected final Alliance pieceAlliance;
	protected final boolean isFirstMove;
	
	Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance) {
		this.pieceType = pieceType;
		this.pieceAlliance = pieceAlliance;
		this.piecePosition = piecePosition;
		this.isFirstMove = false;
	}
	
	public int getPiecePosition() {
		return this.piecePosition; 
	}
	public Alliance getPieceAlliance() {
	  return this.pieceAlliance; 
		
	}
	
	public boolean isFirstMove() {
		return this.isFirstMove; 
	}
	
	public PieceType getPieceType() {
		return this.pieceType;
	}
	public abstract Collection<Move> calculateLegalMoves(final Board board);
	
	public abstract Piece movePiece(Move move);
	
	public enum PieceType {
		
		PAWN("P") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public boolean isRook() {
				return false;
			}
		},
		KNIGHT("K") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public boolean isRook() {
				return false;
			}
		},
		BISHOP("B") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public boolean isRook() {
				return false;
			}
		},
		ROOK("R") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public boolean isRook() {
				return true;
			}
		},
		QUEEN("Q") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public boolean isRook() {
				return false;
			}
		},
		KING("K") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isRook() {
				return false;
			}
		};
		
		
		private String pieceName;
		
		PieceType( String pieceName) {
			this.pieceName = pieceName;
		}
		
		@Override
		public String toString() {
			return this.pieceName;
		}
		public abstract boolean isKing();

		public abstract boolean isRook(); 
	}
}
