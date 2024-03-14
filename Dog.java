/**
 * @author Karl Ragnar Kling, kakl9256
 */

public class Dog {

	private String dogName;
	private String breed;
	private int age;
	private int weight;
	private Owner owner;

	public Dog(String dogName, String breed, int age, int weight) {
		this.dogName = dogName;
		this.breed = breed;
		this.age = age;
		this.weight = weight;
	}

	public String getName() {
		return dogName;
	}

	public String getBreed() {
		return breed;
	}

	public int getAge() {
		return age;
	}

	public int getWeight() {
		return weight;
	}

	public void ageDog() {
		age++;
	}

	public double getTailLength() {
		final double predeterminedTailLength = 3.7;

		if (breed.equals("Tax") || breed.equals("Dachshund")) {
			return predeterminedTailLength;
		} else {
			return (double) age * (double) weight / 10;
		}
	}

	public String toString() {
		if (owner == null) {
			return "Name: " + dogName + ", Breed: " + breed + ", Age: " + age + ", Weight: " + weight
					+ ", Tail length:  " + getTailLength();
		} else {
			return "Name: " + dogName + ", Breed: " + breed + ", Age: " + age + ", Weight: " + weight
					+ ", Tail length:  " + getTailLength() + ", Owned by: " + owner.getName();
		}
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
		owner.addDog(this);
	}

	public Owner getOwner() {
		return owner;
	}
}
