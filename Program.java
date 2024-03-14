/**
* @author Karl Ragnar Kling, kakl9256
*/

import java.util.ArrayList;

public class Program {

	private ArrayList<Dog> dogList = new ArrayList<>();
	private ArrayList<Owner> ownerList = new ArrayList<>();
	private ArrayList<Auction> auctionList = new ArrayList<>();
	
	private String[] commands = { REGISTER_NEW_DOG, LIST_DOGS, INCREASE_AGE, REMOVE_DOG, REGISTER_NEW_OWNER, GIVE_DOG,
			LIST_OWNERS, REMOVE_OWNER, START_AUCTION, MAKE_BID, LIST_BIDS, LIST_AUCTIONS, CLOSE_AUCTION, EXIT_PROGRAM };
	
	private Input input = new Input();

	private static final String REGISTER_NEW_DOG = "register new dog";
	private static final String LIST_DOGS = "list dogs";
	private static final String INCREASE_AGE = "increase age";
	private static final String REMOVE_DOG = "remove dog";
	private static final String REGISTER_NEW_OWNER = "register new owner";
	private static final String GIVE_DOG = "give dog";
	private static final String LIST_OWNERS = "list owners";
	private static final String REMOVE_OWNER = "remove owner";
	private static final String START_AUCTION = "start auction";
	private static final String MAKE_BID = "make bid";
	private static final String LIST_BIDS = "list bids";
	private static final String LIST_AUCTIONS = "list auctions";
	private static final String CLOSE_AUCTION = "close auction";
	private static final String EXIT_PROGRAM = "exit";

	private void start() {
		printCommands();
		runCommandLoop();
		closeDown();
	}

	private void printCommands() {
		System.out.println("Commands: ");
		for (int i = 0; i < commands.length; i++) {
			System.out.println(commands[i]);
		}
	}

	private void runCommandLoop() {
		String command;
		do {
			command = readCommand();
			handleCommand(command);
		} while (!command.equals(EXIT_PROGRAM));

	}

	private String readCommand() {
		return input.readCommand();
	}

	private void handleCommand(String command) {
		switch (command) {
		case REGISTER_NEW_DOG:
			registerNewDog();
			break;
		case LIST_DOGS:
			listDogs();
			break;
		case INCREASE_AGE:
			increaseAge();
			break;
		case REMOVE_DOG:
			removeDog();
			break;
		case REGISTER_NEW_OWNER:
			registerNewOwner();
			break;
		case GIVE_DOG:
			giveDog();
			break;
		case LIST_OWNERS:
			listOwners();
			break;
		case REMOVE_OWNER:
			removeOwner();
			break;
		case START_AUCTION:
			startAuction();
			break;
		case MAKE_BID:
			makeBid();
			break;
		case LIST_BIDS:
			listBids();
			break;
		case LIST_AUCTIONS:
			listAuctions();
			break;
		case CLOSE_AUCTION:
			closeAuction();
			break;
		case EXIT_PROGRAM:
			break;
		default:
			System.out.println("Error: No such command!");
		}
	}

	private void closeDown() {
		input.closeInput();
	}

	public static void main(String[] args) {
		Program program = new Program();
		program.start();
	}

	private void registerNewDog() {
		String dogName = input.readDogName();
		String breed = input.readBreed();
		int age = input.readAge();
		int weight = input.readWeight();
		Dog dog = new Dog(dogName, breed, age, weight);
		addDog(dog);
	}

	private void addDog(Dog dog) {
		dogList.add(dog);
	}

	private void listDogs() {
		if (isDogListEmpty()) {
			return;
		} else {
			sortDogs();
			double wantedTailLength = input.readTailLength();
			for (Dog dog : dogList) {
				if (dog.getTailLength() >= wantedTailLength) {
					System.out.println(dog);
				}
			}
		}

	}

	// Jag använde mig av Insertion sort och hittade information om den
	// sorteringsmetoden på: https://www.geeksforgeeks.org/insertion-sort/.
	private void sortDogs() {
		Dog dog;
		int i;
		int j;
		for (i = 1; i < dogList.size(); i++) {
			dog = dogList.get(i);
			for (j = i - 1; j >= 0; j--) {
				if (dog.getName().compareTo(dogList.get(j).getName()) > 0) {
					break;
				}
			}
			dogList.add(j + 1, dog);
			dogList.remove(i + 1);
		}
		for (i = 1; i < dogList.size(); i++) {
			Dog dogToSort = dogList.get(i);
			j = i - 1;
			while (j >= 0 && dogList.get(j).getTailLength() > dogToSort.getTailLength()) {
				dogList.set(j + 1, dogList.get(j));
				j--;
			}
			dogList.set(j + 1, dogToSort);
		}
	}

	private Dog findDog(String dogName) {
		for (Dog dog : dogList) {
			if (dogName.equals(dog.getName())) {
				return dog;
			}
		}
		return null;
	}

	private void increaseAge() {
		String dogName = input.readDogName();
		if (isDogListEmpty()) {
			return;
		} else if (isFindDogNull(dogName)) {
			return;
		} else {
			findDog(dogName).ageDog();
			System.out.println(dogName + " is now one year older.");
		}
	}

	private void removeDog() {
		String dogName = input.readDogName();
		if (isDogListEmpty()) {
			return;
		} else if (isFindDogNull(dogName)) {
			return;
		} else if (findDog(dogName).getOwner() != null) {
			findDog(dogName).getOwner().removeDogFromOwner(findDog(dogName));
		}
		auctionList.remove(findAuction(findDog(dogName)));
		dogList.remove(findDog(dogName));
		System.out.println(dogName + " is removed from the register.");

	}

	private void registerNewOwner() {
		String ownerName = input.readOwnerName();
		Owner owner = new Owner(ownerName);
		addOwner(owner);
		System.out.println(ownerName + " is now registered");
	}

	private void addOwner(Owner owner) {
		ownerList.add(owner);
	}

	private Owner findOwner(String ownerName) {
		for (Owner owner : ownerList) {
			if (ownerName.equals(owner.getName())) {
				return owner;
			}
		}
		return null;
	}

	private void giveDog() {
		String dogName = input.readDogName();
		if (isFindDogNull(dogName)) {
			return;
		}
		else if (hasDogAnOwner(dogName)) {
			return;
		}
		String ownerName = input.readOwnerName();
		if (isFindOwnerNull(ownerName)) {
			return;
		}
		findOwner(ownerName).addDog(findDog(dogName));
		if (findAuction(findDog(dogName)) != null) {
			removeAuction(findAuction(findDog(dogName)));
		}
		System.out.println(findOwner(ownerName).getName() + " now owns " + findDog(dogName).getName());
	}

	private void listOwners() {
		if (isOwnerListEmpty()) {
			return;
		}
		for (int i = 0; i < ownerList.size(); i++) {
			if (ownerList.get(i).getOwnedDog() == null) {
				System.out.println(ownerList.get(i).getName() + " []");
			}
			else {
				String s = ownerList.get(i).getName() + " [" + ownerList.get(i).getOwnedDog() + "]";
				System.out.println(s);
			}
		}
	}

	private void removeOwner() {
		String ownerName = input.readOwnerName();
		if (isOwnerListEmpty()) {
			return;
		}
		else if (isFindOwnerNull(ownerName)) {
			return;
		}
		Owner ownerToRemove = findOwner(ownerName);
		ownerToRemove.removeDogFromDogList(dogList);
		for (int i = 0; i < auctionList.size(); i++) {
			auctionList.get(i).removeBid(ownerToRemove);
		}
		ownerList.remove(ownerToRemove);
		System.out.println(ownerToRemove.getName() + " is removed from the register.");
	}

	private void startAuction() {
		String dogName = input.readDogName();
		if (isDogListEmpty()) {
			return;
		}
		else if (isFindDogNull(dogName)) {
			return;
		}
		else if (hasDogAnOwner(dogName)) {
			return;
		}
		else if (findAuction(findDog(dogName)) != null) {
			System.out.println("Error: Dog already up for auction!");
			return;
		}
		Auction auction = new Auction(findDog(dogName));
		addAuction(auction);
		System.out.println(dogName + " has been put in " + auction.toString());
	}

	private void addAuction(Auction auction) {
		auctionList.add(auction);
	}

	private Auction findAuction(Dog dog) {
		for (Auction auction : auctionList) {
			if (dog.equals(auction.getDogForAuction())) {
				return auction;
			}
		}
		return null;
	}

	// Pga alla vilkor och inmatningar blir metoden lite väl lång, men jag har
	// kortat ner metoden med hjälp av hjälp metoder så mycket jag anser det är
	// möjligt att korta ner den.
	private void makeBid() {
		String userName = input.readOwnerName();
		if (isOwnerListEmpty()) {
			return;
		}
		else if (isFindOwnerNull(userName)) {
			return;
		}
		String dogName = input.readDogName();
		if (isDogListEmpty()) {
			return;
		}
		else if (isFindDogNull(dogName)) {
			return;
		}
		else if (isDogNotUpForAuction(dogName)) {
			return;
		}
		int lowestAmountBid = findAuction(findDog(dogName)).getLowestAcceptedBidOnDog();
		System.out.print("Amount to bid (min " + lowestAmountBid + ")?> ");
		int bidAmount = input.readBid();
		while (bidAmount < lowestAmountBid) {
			System.out.println("Error: Bid too low!");
			System.out.print("Amount to bid (min " + lowestAmountBid + ")?> ");
			bidAmount = input.readBid();
		}
		findAuction(findDog(dogName)).createBid(findOwner(userName), bidAmount);
		System.out.println("Bid made");
	}

	private void listBids() {
		String dogName = input.readDogName();
		if (!isDogListEmpty()) {
			if (!isFindDogNull(dogName)) {
				if (findAuction(findDog(dogName)) != null) {
					System.out.println(findAuction(findDog(dogName)).listBids());
				}
				else {
					System.out.println("Error: Dog not up for auction!");
				}

			}
		}
	}

	private void listAuctions() {
		if (auctionList.isEmpty()) {
			System.out.println("Error: No auction in progress!");
			return;
		}
		for (Auction auction : auctionList) {
			System.out.println(auction.listAuction());
		}
	}

	private void closeAuction() {
		String dogName = input.readDogName();
		if (isDogListEmpty()) {
			return;
		}
		else if (isFindDogNull(dogName)) {
			return;
		}
		else if (isDogNotUpForAuction(dogName)) {
			return;
		}
		Dog dogOnThisAuction = findDog(dogName);
		Auction auctionToRemove = findAuction(dogOnThisAuction);
		int highestBid = auctionToRemove.getLowestAcceptedBidOnDog() - 1;
		if (highestBid == 0) {
			removeAuction(auctionToRemove);
			System.out.println("The auction is closed. No bids on " + dogOnThisAuction.getName());
			return;
		}
		Owner winningBidder = auctionToRemove.getHighestBidder();
		winningBidder.addDog(dogOnThisAuction);
		removeAuction(auctionToRemove);
		System.out.println("The auction is closed. " + dogOnThisAuction.getName() + " was sold for " + highestBid
				+ " kr to " + winningBidder.getName());
	}

	private void removeAuction(Auction auction) {
		auctionList.remove(auction);
	}

	private boolean isDogListEmpty() {
		if (dogList.isEmpty()) {
			System.out.println("Error: The list of dogs is empty!");
			return true;
		}
		return false;
	}

	private boolean isFindDogNull(String dogName) {
		if (findDog(dogName) == null) {
			System.out.println("Error: No such dog!");
			return true;
		}
		return false;
	}

	private boolean isOwnerListEmpty() {
		if (ownerList.isEmpty()) {
			System.out.println("Error: The list of owners is empty");
			return true;
		}
		return false;
	}

	private boolean isFindOwnerNull(String ownerName) {
		if (findOwner(ownerName) == null) {
			System.out.println("Error: No such user!");
			return true;
		}
		return false;
	}

	private boolean hasDogAnOwner(String dogName) {
		if (findDog(dogName).getOwner() != null) {
			System.out.println("Error: Dog already has an owner!");
			return true;
		}
		return false;
	}

	private boolean isDogNotUpForAuction(String dogName) {
		if (findAuction(findDog(dogName)) == null) {
			System.out.println("Error: Dog not up for auction!");
			return true;
		}
		return false;
	}

}
