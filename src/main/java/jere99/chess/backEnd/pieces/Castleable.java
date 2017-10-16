package jere99.chess.backEnd.pieces;

/**
 * Indicates pieces whose lack of previous movement is a condition for castling, i.e. Kings and Rooks.
 * 
 * @author JeremiahDeGreeff
 */
public interface Castleable {
	
	/**
	 * @return true if this piece has moved and is thus ineligible for castling, false otherwise.
	 */
	public boolean hasMoved();
	
	/**
	 * Should be called whenever a piece that implements this interface is moved.
	 * Once this method is called this piece will no longer be able to be involved in a castle.
	 */
	public void castleableMove();
	
}
