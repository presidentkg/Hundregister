
/**
* @author Karl Ragnar Kling, kakl9256
*/

import java.util.ArrayList;
import java.util.Arrays;

public class Auction {
	private static int auctionCounter;
	private Dog dogForAuction;
	private int auctionNumber;
	private ArrayList<Bid> bidsOnDog = new ArrayList<>();

	public Auction(Dog dog) {
		auctionCounter++;
		dogForAuction = dog;
		auctionNumber = auctionCounter;
	}

	public Dog getDogForAuction() {
		return dogForAuction;
	}

	public String toString() {
		return "Auction #" + auctionNumber + ": " + dogForAuction.getName();
	}

	public void createBid(Owner user, int amount) {
		removeBid(user);
		Bid bid = new Bid(user, amount);
		bidsOnDog.add(bid);
		sortBids();
	}

	public void removeBid(Owner user) {
		for (int i = 0; i < bidsOnDog.size(); i++) {
			if (user == (bidsOnDog.get(i).getBidder())) {
				bidsOnDog.remove(i);
			}
		}
	}

	public int getLowestAcceptedBidOnDog() {
		if (bidsOnDog.isEmpty()) {
			return 1;
		}
		return bidsOnDog.get(0).getBiddedAmount() + 1;
	}

	public String listBids() {
		ArrayList<Bid> bidsOnDogCopy = bidsOnDog;
		return bidsOnDogCopy.toString();
	}

	public String listAuction() {
		Bid[] highestBidsOnDog = new Bid[0];
		if (bidsOnDog.size() != 0) {
			int arraySize = bidsOnDog.size();
			if (bidsOnDog.size() > 3) {
				arraySize = 3;
			}
			Bid[] highestBidsOnDogCopy = new Bid[arraySize];
			for (int i = 0; i < bidsOnDog.size() && i < 3; i++) {
				if (bidsOnDog.get(i) != null) {
					highestBidsOnDogCopy[i] = bidsOnDog.get(i);
				}
			}
			highestBidsOnDog = highestBidsOnDogCopy;
		}
		return this.toString() + " Top Bids:" + Arrays.toString(highestBidsOnDog);
	}

	private void sortBids() {
		int i;
		int j;
		for (i = 1; i < bidsOnDog.size(); i++) {
			Bid bid = bidsOnDog.get(i);
			j = i - 1;
			while (j >= 0 && bidsOnDog.get(j).getBiddedAmount() < bid.getBiddedAmount()) {
				bidsOnDog.set(j + 1, bidsOnDog.get(j));
				j--;
			}
			bidsOnDog.set(j + 1, bid);
		}
	}

	public Owner getHighestBidder() {
		return bidsOnDog.get(0).getBidder();
	}
}
