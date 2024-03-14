/**
 * @author Karl Ragnar Kling, kakl9256
 */
public class Bid {
	private int bidAmount;
	private Owner bidder;

	public Bid(Owner user, int amount) {
		bidAmount = amount;
		bidder = user;
	}

	public int getBiddedAmount() {
		return bidAmount;
	}

	public Owner getBidder() {
		return bidder;
	}

	public String toString() {
		return "Name: " + bidder.getName() + " Bid amount: " + bidAmount + " kr";
	}
}
