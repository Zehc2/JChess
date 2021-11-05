package com.chess.engine.board;

// utilitys for the board class

public class BoardUtils {

	// defines all the columns on the board
	public static final boolean[] FIRST_COLUMN = initColumn(0);
	public static final boolean[] SECOND_COLUMN = initColumn(1);
	public static final boolean[] SEVENTH_COLUMN = initColumn(6);
	public static final boolean[] EIGHTH_COLUMN = initColumn(7);
	
	// defines the ranks on the board
	public static final boolean[] EIGHTH_RANK = initRow(0); 
	public static final boolean[] SEVENTH_RANK = initRow(8); 
	public static final boolean[] SIXTH_RANK = initRow(16); 
	public static final boolean[] FIFTH_RANK = initRow(24); 
	public static final boolean[] FOURTH_RANK = initRow(32); 
	public static final boolean[] THIRD_RANK = initRow(40); 
	public static final boolean[] SECOND_RANK = initRow(48);
	public static final boolean[] FIRST_RANK = initRow(56); 
	
	public static final int NUM_TILES = 64; 
	public static final int NUM_TILES_PER_ROW = 8; 

	// You can not instantiate this class!
	private BoardUtils() {
		throw new RuntimeException("You can't instantiate me");
	}
	
	/*
	 * Creates all the columns
	 * @var column creates a boolean with 64 propertys
	 * @param columnNumber is passed into the column and makes that property
	 * of the column true.
	 * Then it adds 8 to the columnNumber.
	 */
	private static boolean[] initColumn(int columnNumber) {
		final boolean[] column = new boolean[64]; 
			do {
				column[columnNumber] = true; 
				columnNumber += 8;
			}
				while(columnNumber < 64); 
		return column;
	} 
	
	/*
	 * @var row creates a new boolean with the @int NUM_TILES amount of propertys. (64)
	 * @param rowNumber passed into the row boolean and makes equivalent number of rowNumber
	 * in row true. Then rowNumber gets one added to it. If rowNumber divided by NUM_TILES_PER_ROW (8)
	 * does not have a remainder it returns row.
	 */
	private static boolean[] initRow(int rowNumber) {
		final boolean[] row = new boolean[NUM_TILES];
		
		do { 
			row[rowNumber] = true;
			rowNumber++;
			
		} while(rowNumber % NUM_TILES_PER_ROW != 0);
		
		return row;
	}
	
	// Checks if the tiles is valid by asking if it is more than 0 or less than 64.
	public static boolean isValidTileCoordinate(final int coordinate) {
		return coordinate >=0 && coordinate < 64; 
		
	}

}
