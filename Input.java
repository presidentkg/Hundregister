
/**
* @author Karl Ragnar Kling, kakl9256
*/
import java.util.Scanner;

public class Input {
	private Scanner input = new Scanner(System.in);
	private String command;
	private String name;
	private String breed;
	private int age;
	private int weight;
	private String enteredWantedTailLength;
	private String replaceString;
	private double wantedTailLength;
	private int bidAmount;
	
	public String readCommand() {
		System.out.print("Command?> ");
		command = input.nextLine().toLowerCase().trim();
		return command;
	}
	public String readDogName() {
		System.out.print("Enter the name of the dog?> ");
		name = input.nextLine();
		while (name.isBlank()) {
			System.out.println("Error: the name can't be empty");
			System.out.print("Enter the name of the dog?> ");
			name = input.nextLine();
		}
		name = capitalizeFirstLetter(name);
		return name;
	}

	public String readBreed() {
		System.out.print("Breed?> ");
		breed = input.nextLine();
		while (breed.isBlank()) {
			System.out.println("Error: the name can't be empty");
			System.out.print("breed?> ");
			name = input.nextLine();
		}
		breed = capitalizeFirstLetter(breed);
		return breed;
	}

	public int readAge() {
		System.out.print("Age?> ");
		age = input.nextInt();
		nextLine();
		return age;
	}

	public int readWeight() {
		System.out.print("Weight?> ");
		weight = input.nextInt();
		nextLine();
		return weight;
	}

	public double readTailLength() {
		System.out.print("Smallest tail length to display?> ");
		enteredWantedTailLength = input.nextLine();
		replaceString = enteredWantedTailLength.replaceAll(",", ".");
		wantedTailLength = Double.valueOf(replaceString);
		return wantedTailLength;
	}

	public String readOwnerName() {
		System.out.print("Enter the name of the owner?> ");
		name = input.nextLine();
		while (name.isBlank()) {
			System.out.println("Error: the name can't be empty");
			System.out.print("Enter the name of the owner?> ");
			name = input.nextLine();
		}
		name = capitalizeFirstLetter(name);
		return name;
	}

	private String capitalizeFirstLetter(String name) {
		name = name.toLowerCase().trim();
		String firstLetter = name.substring(0, 1).toUpperCase();
		String theRestOfTheLetters = name.substring(1);
		name = firstLetter + theRestOfTheLetters;
		return name;
	}

	public int readBid() {
		bidAmount = input.nextInt();
		nextLine();
		return bidAmount;
	}

	private void nextLine() {
		input.nextLine();
	}
	
	public void closeInput() {
		input.close();
	}

}
