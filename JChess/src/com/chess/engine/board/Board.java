package com.chess.engine.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chess.engine.pieces.Bishop;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Knight;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Queen;
import com.chess.engine.pieces.Rook;
import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.Player;
import com.chess.engine.player.WhitePlayer;


public class Board {
	
	// A list is a data structure, in this case contains "Tile"
	private final List<Tile> gameBoard;
	
	// Collections are made for whitePieces and blackPieces
	private final Collection<Piece> whitePieces;
	private final Collection<Piece> blackPieces;
	
	// Creates all the players
	
	private final WhitePlayer whitePlayer;
	private final BlackPlayer blackPlayer;
	private final Player currentPlayer;
	
	
	// Creates the board
	private Board(final Builder builder) {
		
		this.gameBoard = createGameBoard(builder);
		this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
		this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);
		
		// grabs all the legal moves passing it all the pieces on the board
		final Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
		final Collection<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);
		
		// Creates a new whitePlayer and gets all of its and blacks legal moves
		this.whitePlayer = new WhitePlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
		// Creates a new blackPlayer and gets all of its and blacks legal moves
		this.blackPlayer = new BlackPlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
		
		this.currentPlayer = builder.nextMoveMaker.choosePlayer(this.whitePlayer, this.blackPlayer);
	}
	
	public static String testing() {
		return "tested!";				
	}
	
		@Override 
		/* Creates a toString method of the board so you can print it
		 */
		public String toString() {
			final StringBuilder builder = new StringBuilder();
			for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
				final String tileText = this.gameBoard.get(i).toString();
				builder.append(String.format("%3s", tileText)); 
				if((i + 1) % BoardUtils.NUM_TILES_PER_ROW == 0) {
					builder.append("\n");
				}
			}
			return builder.toString();
		}
		
		// creates a new Player called whitePlayer
		public Player whitePlayer() {
			return this.whitePlayer;
		}
		
		//creates new player called blackPlayer
		public Player blackPlayer() {
			return this.blackPlayer;
		}
		
		//creates new currentPlayer
		public Player currentPlayer() {
			return this.currentPlayer;
		}
		
		//creates new Collection returning all the blackPieces
		public Collection<Piece> getBlackPieces() {
			return this.blackPieces;
		}
		
		//creates the Collection returning the whitePieces
		public Collection<Piece> getWhitePieces() {
			return this.whitePieces;
		}
		
		// Calculates all the legal moves
		private Collection<Move> calculateLegalMoves(Collection<Piece> pieces) {
		
		//legalMoves list
		 List<Move> legalMoves = new ArrayList<>();
		
		 /*
		  * calculateLegalMoves is a abstract method in the Piece class
		  * it's overriden by the subclasses and caluclate their legal moves
		  */
		for(final Piece piece : pieces) {
			legalMoves.addAll(piece.calculateLegalMoves(this));
		}
		// returns immutable list of legalMoves
		return legalMoves;
	}
	
		// Returns a Collection 
	/*private Collection<Move> Collections(List<Move> legalMoves) {
		return legalMoves;
	}*/

	/* gets all active pieces on the board
	 * @var activePieces gets all the active pieces
	 * @param gameBoard is a list that contains Tiles. 
	 * @Param alliance creates a new final alliance
	 */
	private Collection<Piece> calculateActivePieces(final List<Tile> gameBoard, final Alliance alliance) {
	
			final List<Piece> activePieces = new ArrayList<>(); 
			
			/* First it finds every tile in gameBoard then it asks if the tile is Occupied.
			 * After it gets the piece and the alliance. 
			 * getPiece is a method in the tile class that 
			 */
			for(final Tile tile : gameBoard) {
				if(tile.isTileOccupied()) {
					final Piece piece = tile.getPiece();
					if(piece.getPieceAlliance() == alliance) {
						activePieces.add(piece);
					}
				}
			}
			// return Collections.unmodifiableList(Arrays.asList(activePieces));
			// returns the unmodifiableList of activepieces (immutable)
			 return Collections.unmodifiableList(activePieces);
			
			
	}
	
	 /*creates getTile method
	 * @param tileCoordinate is the tile that you pass in to find.
	 */
	public Tile getTile(final int tileCoordinate) {
		return gameBoard.get(tileCoordinate);
	}
	
	// Creates the classic gameboard
	private static List<Tile> createGameBoard(final Builder builder) {
		final Tile[] tiles = new Tile[BoardUtils.NUM_TILES]; 
		for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
			tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
			
			 
		}
		
		 // Returns all the tiles
		return Collections.unmodifiableList(Arrays.asList(tiles));
		
	}
	
	//puts all the pieces on the board
	
	public static Board createStandardBoard()  {
		
		//makes the new builder used above
		final Builder builder = new Builder(); 
		
		
		
		 builder.setPiece(new Rook(Alliance.BLACK, 0));
	        builder.setPiece(new Knight(Alliance.BLACK, 1));
	        builder.setPiece(new Bishop(Alliance.BLACK, 2));
	        builder.setPiece(new Queen(Alliance.BLACK, 3));
	        builder.setPiece(new King(Alliance.BLACK, 4));
	        builder.setPiece(new Bishop(Alliance.BLACK, 5));
	        builder.setPiece(new Knight(Alliance.BLACK, 6));
	        builder.setPiece(new Rook(Alliance.BLACK, 7));
	        builder.setPiece(new Pawn(Alliance.BLACK, 8));
	        builder.setPiece(new Pawn(Alliance.BLACK, 9));
	        builder.setPiece(new Pawn(Alliance.BLACK, 10));
	        builder.setPiece(new Pawn(Alliance.BLACK, 11));
	        builder.setPiece(new Pawn(Alliance.BLACK, 12));
	        builder.setPiece(new Pawn(Alliance.BLACK, 13));
	        builder.setPiece(new Pawn(Alliance.BLACK, 14));
	        builder.setPiece(new Pawn(Alliance.BLACK, 15));
	        // White Layout
	        builder.setPiece(new Pawn(Alliance.WHITE, 48));
	        builder.setPiece(new Pawn(Alliance.WHITE, 49));
	        builder.setPiece(new Pawn(Alliance.WHITE, 50));
	        builder.setPiece(new Pawn(Alliance.WHITE, 51));
	        builder.setPiece(new Pawn(Alliance.WHITE, 52));
	        builder.setPiece(new Pawn(Alliance.WHITE, 53));
	        builder.setPiece(new Pawn(Alliance.WHITE, 54));
	        builder.setPiece(new Pawn(Alliance.WHITE, 55));
	        builder.setPiece(new Rook(Alliance.WHITE, 56));
	        builder.setPiece(new Knight(Alliance.WHITE, 57));
	        builder.setPiece(new Bishop(Alliance.WHITE, 58));
	        builder.setPiece(new Queen(Alliance.WHITE, 59));
	        builder.setPiece(new King(Alliance.WHITE, 60));
	        builder.setPiece(new Bishop(Alliance.WHITE, 61));
	        builder.setPiece(new Knight(Alliance.WHITE, 62));
	        builder.setPiece(new Rook(Alliance.WHITE, 63));
		
	
		// whites move
		builder.setMoveMaker(Alliance.WHITE); 
		
		return builder.build();
		
	}
	
	public static class Builder {
		// Inner class that defines the board
		
		// Creates a map so you can find all the pieces and tiles on the board
		Map<Integer, Piece> boardConfig; 
		// Creates an alliance to see who will get the next move
		Alliance nextMoveMaker;
		// Creates the enPassantPawn
	    Pawn enPassantPawn;
		
	    //Constructor, just creates the boardConfig into a hashmap
		public Builder() {
			this.boardConfig = new HashMap<>(); 
		}
		
		/*Sets the pieces on the board
		 * @param piece is the piece that you want to set on the board.
		 * @method getPiecePosition is a method inside piece class returning the tile that the 
		 * piece is pm
		 */
		public Builder setPiece(final Piece piece) {
			this.boardConfig.put(piece.getPiecePosition(), piece);
			return this;
		}
		
		/*
		 * Sets which players move it is
		 */
		public Builder setMoveMaker(final Alliance alliance) {
			this.nextMoveMaker = alliance;
			return this;
			
		}
		
		/*
		 * Uses the methods listed above to create a new Board
		 */
		public Board build() {
			return new Board(this);
		}
		
		//sets if enPPassantPawn is valid
		public void setEnPassantPawn(Pawn enPassantPawn) {
			this.enPassantPawn = enPassantPawn;
		}
	}
	
	
	/* Creates an iterable to go through all the moves and see if they are legal. This uses the piece classes and player classes.
	* The player classes have an easy method which allows you to get all of their legal moves.
	* @var allLegalMoves creates a ArrayList of both the players legal moves.
	*/
	public Iterable<Move> getAllLegalMoves() {
		List<Move> allLegalMoves = new ArrayList<>();
		allLegalMoves.addAll(this.whitePlayer.getLegalMoves());
		allLegalMoves.addAll(this.blackPlayer.getLegalMoves());
		return Collections.unmodifiableList(allLegalMoves);
	
	}
}
