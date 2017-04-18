import java.util.Scanner;

public class ModeFinder {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter some number of integers, separated by spaces: ");
		String nums = scanner.nextLine();
		scanner.close();
		
		int numVals = 0;
		int maxEntry = 0;
		
		for (int i = 0; i < nums.length(); i++) {
			if (!nums.substring(i, i + 1).equals(" ")) {
				numVals++;
				int currVal = Integer.parseInt(nums.substring(i, i + 1));
				while (++i < nums.length() && !nums.substring(i, i + 1).equals(" ")) {
					currVal *= 10;
					currVal += Integer.parseInt(nums.substring(i, i + 1));
				}
				if (currVal > maxEntry) {
					maxEntry = currVal;
				}
			}
		}
		
		int[] allValues = new int[numVals];
		int[] occurrencesArray = new int[maxEntry];
		
		int currIndex = 0;
		
		for (int i = 0; i < nums.length(); i++) {
			if (!nums.substring(i, i + 1).equals(" ")) {
				int currVal = Integer.parseInt(nums.substring(i, i + 1));
				while (++i < nums.length() && !nums.substring(i, i + 1).equals(" ")) {
					currVal *= 10;
					currVal += Integer.parseInt(nums.substring(i, i + 1));
				}
				allValues[currIndex] = currVal;
				currIndex++;
			}
		}
		
		for (int x : allValues) {
			occurrencesArray[x - 1]++;
		}
		
		int maxOccurrence = 0;
		int mode = 0;
		int numModes = 1;
		boolean multipleMode = false;
		
		for (int i = 0; i < occurrencesArray.length; i++) {
			if (occurrencesArray[i] > maxOccurrence) {
				maxOccurrence = occurrencesArray[i];
				mode = i + 1;
				multipleMode = false;
				numModes = 1;
			} else if (occurrencesArray[i] == maxOccurrence) {
				multipleMode = true;
				numModes++;
			}
		}
		
		if (multipleMode) {
			System.out.println("There were " + numModes + " modes");
			int numPrinted = 0;
			for (int i = 0; i < occurrencesArray.length; i++) {
				if (occurrencesArray[i] == maxOccurrence) {
					if (numPrinted < numModes - 1) {
						System.out.print(i + 1 + ", ");
						numPrinted++;
					} else {
						System.out.print(i + 1);
					}
				}
			}
		} else {
			System.out.println("There was 1 mode:");
			System.out.println(mode);
		}
	}
}
