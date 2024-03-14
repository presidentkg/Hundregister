
import java.util.ArrayList;

public class Sortering {
	public static void main(String[] args) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(5);
		numbers.add(1);
		numbers.add(34);
		numbers.add(2);
		numbers.add(77);
		numbers.add(24);
		numbers.add(12);
		
		int numberToSort, indexToCompareTo;
		for (int i = 1; i < numbers.size(); i++) {
			numberToSort = numbers.get(i);
			indexToCompareTo = i - 1;
			while (indexToCompareTo >= 0 && numbers.get(indexToCompareTo) > numberToSort) {
				numbers.set(indexToCompareTo + 1, numbers.get(indexToCompareTo));
				//numbers.get(indexToCompareTo + 1).equals(numbers.get(indexToCompareTo));
				indexToCompareTo = indexToCompareTo - 1;
			}
			numbers.set(indexToCompareTo + 1, numberToSort);
		}
		System.out.print(numbers);
		
		ArrayList<String> names = new ArrayList<String>();
		names.add("Sigge");
		names.add("Isabel");
		names.add("KG");
		names.add("Jens");
		names.add("Ivar");
		String nameToSort;
		int indexTooCompareTo;
		for (int i = 1; i < names.size(); i++) {
			nameToSort = names.get(i);
			indexTooCompareTo = i - 1;
			while (indexTooCompareTo >= 0) {
				if (nameToSort.compareTo(names.get(indexTooCompareTo)) > 0) {
					break;
				}
				names.set(indexTooCompareTo + 1, names.get(indexTooCompareTo));
				indexTooCompareTo = indexTooCompareTo - 1;
			}
			names.set(indexTooCompareTo + 1, nameToSort);
			System.out.println(names);
		}
		System.out.println(names);
		
	}
}
