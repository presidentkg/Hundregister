
/**
* @author Karl Ragnar Kling, kakl9256
*/

import java.util.ArrayList;

public class Owner {

	private Dog[] dogOwn = new Dog[0];
	private String ownerName;
	private String ownedDogsName;

	public Owner(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getName() {
		return ownerName;
	}

	public String toString() {
		return "Namn: " + ownerName;
	}

	public void addDog(Dog dog) {
		if (dog.getOwner() != this) {
			dog.setOwner(this);
			return;
		}
		Dog[] dogOwnCopy = new Dog[dogOwn.length + 1];
		for (int i = 0; i < dogOwn.length; i++) {
			dogOwnCopy[i] = dogOwn[i];
		}

		dogOwnCopy[dogOwnCopy.length - 1] = dog;
		dogOwn = dogOwnCopy;
	}

	public String getOwnedDog() {
		if (dogOwn == null) {
			return null;
		}
		for (int i = 0; i < dogOwn.length; i++) {
			if (i == 0) {
				ownedDogsName = dogOwn[i].getName();
			} else {
				ownedDogsName = ownedDogsName + ", " + dogOwn[i].getName();
			}
		}
		return ownedDogsName;
	}

	public void removeDogFromDogList(ArrayList<Dog> dogList) {
		for (int i = 0; i < dogOwn.length; i++) {
			for (int j = 0; j < dogList.size(); j++) {
				if (dogOwn[i].equals(dogList.get(j))) {
					dogList.remove(j);
				}
			}
		}
	}

	public void removeDogFromOwner(Dog dog) {
		Dog[] dogOwnCopy = new Dog[dogOwn.length - 1];
		for (int i = 0, j = 0; i < dogOwn.length; i++) {
			if (dog.equals(dogOwn[i])) {
				dogOwn[i] = null;
				continue;
			}
			dogOwnCopy[j++] = dogOwn[i];
		}
		dogOwn = dogOwnCopy;
	}

	public boolean ownerOfDog(Dog dog) {
		for (int i = 0; i < dogOwn.length; i++) {
			if (dog.equals(dogOwn[i])) {
				return true;
			}
		}
		return false;
	}
}
