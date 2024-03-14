public class DogTest {

	public static void main(String[] args) {
		Assignment assignment = new Assignment();
		// assignment.registerNewDog();
		int i = 200;
		double j = i / 10;
		System.out.println(j);
		assignment.addDog(new Dog("Sigge", "Pudel", 12, 7));
		assignment.addDog(new Dog("Zingo", "Tax", 3, 2));
		assignment.addDog(new Dog("Bosse", "Tax", 1, 1));
		assignment.addDog(new Dog("Olle", "Mops", 5, 7));
		assignment.addDog(new Dog("Lucifer", "Katt", 1, 1));
		assignment.addDog(new Dog("Castiel", "Katt", 1, 1));
		assignment.addOwner(new Owner("Isabel"));
		assignment.addOwner(new Owner("Kg"));
		assignment.addOwner(new Owner("Lovisa"));

		//assignment.listDogs();

		/*
		 * assignment.startAuction(); assignment.startAuction();
		 * 
		 * assignment.makeBid(); assignment.makeBid(); assignment.makeBid();
		 * 
		 * assignment.listAuctions();
		 * 
		 * 
		 * assignment.removeDog(); assignment.removeOwner(); assignment.listAuctions();
		 * 
		 * assignment.closeAuction(); assignment.giveDog();
		 * 
		 * assignment.listAuctions(); assignment.listOwners();
		 */

		/*
		 * assignment.listDogs(); assignment.listOwners();
		 */
		/*
		 * assignment.increaseAge(); assignment.listDogs(); assignment.removeDog();
		 * assignment.listDogs();
		 */

	}
}
