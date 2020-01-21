import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BullsCowsMethods {

	private BullsCowsMethods() {
		// do not create class
	}

	// returns the number of green and red gumballs after comparing two numbers
	public static int[] printResult(int num1, int num2, int digits) {
		if (num1 > Math.pow(10, digits) || num2 > Math.pow(10, digits)) {
			throw new IllegalArgumentException("nums contains more digits than allowed");
		}
		if (num1 > num2) {
			// swap values so that its ascending order
			return printResult(num2, num1, digits);
		}
		ArrayList<Integer> digitSet1 = new ArrayList<>();
		ArrayList<Integer> digitSet2 = new ArrayList<>();
		int green = 0;
		int red = 0;
		int digitPlace = 0;
		// compare digits
		while (digitPlace < digits) {
			int digit1 = num1 % 10;
			int digit2 = num2 % 10;
			if (digit1 == digit2) {
				green++;
			} else {
				if (digitSet2.contains(digit1)) {
					// matched digit1 to another digit in digitSet2
					red++;
					// remove the integer, not the position
					digitSet2.remove(new Integer(digit1));
				} else {
					// did not match, store in the list of unmatched digits
					digitSet1.add(digit1);
				}
				if (digitSet1.contains(digit2)) {
					red++;
					digitSet1.remove(new Integer(digit2));
				} else {
					// did not match, store in the list of unmatched digits
					digitSet2.add(digit2);
				}
			}
			num2 /= 10;
			num1 /= 10;
			digitPlace++;
		}
		return new int[] { green, red };
	}

	public static double calculateSD(int mat[][]) {
		int sum = 0;
		double standardDeviation = 0.0;
		int size = mat.length * (mat[0].length - 1);
		for (int x = 0; x < mat.length; x++) {
			if (x != mat.length - 1) {
				for (int num : mat[x]) {
					sum += num;
				}
			}
		}
		double mean = (double) sum / size;
		for (int[] arr : mat) {
			for (int num : arr) {
				standardDeviation += Math.pow(num - mean, 2);
			}
		}
		return Math.sqrt(standardDeviation / size);
	}

	public static boolean hasUniqueNumbers(int x) {
		int largestDigit = 10;
		while (x > 0) {
			// System.out.println(x+" "+largestDigit);
			if (largestDigit <= x % 10) {
				return false;
			}
			largestDigit = x % 10;
			x /= 10;
		}
		return true;
	}

	public static boolean isNotRepeating(int x, int digits) {
		int[] digitList = new int[10];
		int countWhileLoops = 0;
		while (x > 0) {
			countWhileLoops++;
			digitList[x % 10]++;
			x /= 10;
		}
		digitList[0] += digits - countWhileLoops;
		for (int c : digitList)
			if (c > 1)
				return false;
		return true;
	}

	public static int randomGuess(ArrayList<Integer> list) {
		return list.get(list.size() / 2);
	}

	public static int greedyGuessTiebreak(OutputPossibilities[][] solver, ArrayList<Integer> guessList,
			ArrayList<Integer> List) {
		if (List.size() == 2)
			return List.get(0);
		int min = 100000000;
		int min2 = 100000000;
		int min3 = 100000000;
		int minNumber = -1;
		for (int a = 0; a < guessList.size(); a++) {
			int[][] newSolver = new int[solver.length][solver.length];
			int guess = guessList.get(a);

			for (int i = 0; i < List.size(); i++) {
				int[] result = BullsCowsMethods.printResult(guess, List.get(i), solver.length - 1);
				newSolver[result[0]][result[1]]++;
			}
			int tempMax = 0;
			int tempMax2 = 0;
			int tempMax3 = 0;
			for (int x = 0; x < solver.length; x++)
				for (int y = 0; y < solver.length; y++) {
					if (tempMax < newSolver[x][y]) {
						tempMax3 = tempMax2;
						tempMax2 = tempMax;
						tempMax = newSolver[x][y];
					} else if (tempMax2 < newSolver[x][y]) {
						tempMax3 = tempMax2;
						tempMax2 = newSolver[x][y];
					} else if (tempMax3 < newSolver[x][y])
						tempMax3 = newSolver[x][y];

				}
			// System.out.print(a+" "+tempMax+" / ");
			if (min > tempMax) {
				min = tempMax;
				min2 = tempMax2;
				min3 = tempMax3;
				minNumber = guess;
			} else if (min == tempMax) {
				if (min2 > tempMax2) {
					min2 = tempMax2;
					min3 = tempMax3;
					minNumber = guess;
				} else if (min2 == tempMax2 && min3 > tempMax3) {
					min3 = tempMax3;
					minNumber = guess;
				}
			}
		}
		if (List.size() < 1000) {
			for (int a = 0; a < List.size(); a++) {
				int[][] newSolver = new int[solver.length][solver.length];
				int guess = List.get(a);
				for (int i = 0; i < List.size(); i++) {
					int[] result = BullsCowsMethods.printResult(guess, List.get(i), solver.length - 1);
					newSolver[result[0]][result[1]]++;
				}
				int tempMax = 0;
				int tempMax2 = 0;
				int tempMax3 = 0;
				for (int x = 0; x < solver.length; x++)
					for (int y = 0; y < solver.length; y++) {
						if (tempMax < newSolver[x][y]) {
							tempMax3 = tempMax2;
							tempMax2 = tempMax;
							tempMax = newSolver[x][y];
						} else if (tempMax2 < newSolver[x][y]) {
							tempMax3 = tempMax2;
							tempMax2 = newSolver[x][y];
						} else if (tempMax3 < newSolver[x][y])
							tempMax3 = newSolver[x][y];

					}
				// System.out.print(a+" "+tempMax+" / ");
				if (min > tempMax) {
					min = tempMax;
					min2 = tempMax2;
					min3 = tempMax3;
					minNumber = guess;
				} else if (min == tempMax) {
					if (min2 > tempMax2) {
						min2 = tempMax2;
						min3 = tempMax3;
						minNumber = guess;
					} else if (min2 == tempMax2 && min3 > tempMax3) {
						min3 = tempMax3;
						minNumber = guess;
					}
				}
			}
		}
		return minNumber;
	}

	public static int greedyGuessNoTies(OutputPossibilities[][] solver, ArrayList<Integer> guessList,
			ArrayList<Integer> List) {
		if (List.size() <= 2)
			return List.get(0);
		int min = 100000000;
		int minNumber = -1;
		for (int a = 0; a < guessList.size(); a++) {
			int[][] newSolver = new int[solver.length][solver.length];
			int guess = guessList.get(a);
			for (int i = 0; i < List.size(); i++) {
				int[] result = BullsCowsMethods.printResult(guess, List.get(i), solver.length - 1);
				newSolver[result[0]][result[1]]++;
			}
			int tempMax = 0;
			for (int x = 0; x < solver.length; x++)
				for (int y = 0; y < solver.length; y++) {
					tempMax = Math.max(tempMax, newSolver[x][y]);
				}
			// System.out.print(a+" "+tempMax+" / ");
			if (min > tempMax) {
				min = tempMax;
				minNumber = guess;
			}
		}
		if (List.size() < 1000) {
			for (int a = 0; a < List.size(); a++) {
				int[][] newSolver = new int[solver.length][solver.length];
				int guess = List.get(a);
				for (int i = 0; i < List.size(); i++) {
					int[] result = BullsCowsMethods.printResult(guess, List.get(i), solver.length - 1);
					newSolver[result[0]][result[1]]++;
				}
				int tempMax = 0;
				for (int x = 0; x < solver.length; x++)
					for (int y = 0; y < solver.length; y++) {
						if (tempMax < newSolver[x][y]) {
							tempMax = newSolver[x][y];
						}
					}
				if (min > tempMax) {
					min = tempMax;
					minNumber = guess;
				}
			}
		}
		return minNumber;
	}

	public static int smartGuessSD(OutputPossibilities[][] solver, ArrayList<Integer> guessList,
			ArrayList<Integer> List) {
		if (List.size() <= 2) {
			return List.get(0);
		}
		double minSD = Double.MAX_VALUE;
		int minNumber = -1;

		for (int a = 0; a < guessList.size(); a++) {
			int[][] newSolver = new int[solver.length][solver.length];
			int guess = guessList.get(a);
			for (int i = 0; i < List.size(); i++) {
				int[] result = BullsCowsMethods.printResult(guess, List.get(i), solver.length - 1);
				newSolver[result[0]][result[1]]++;
			}
			double stnDeviation = BullsCowsMethods.calculateSD(newSolver);
			if (minSD > stnDeviation) {
				minSD = stnDeviation;
				minNumber = guess;
			}
		}
		if (List.size() < 2000) {
			for (int a = 0; a < List.size(); a++) {
				int[][] newSolver = new int[solver.length][solver.length];
				int guess = List.get(a);
				for (int i = 0; i < List.size(); i++) {
					int[] result = BullsCowsMethods.printResult(guess, List.get(i), solver.length - 1);
					newSolver[result[0]][result[1]]++;
				}
				double stnDeviation = BullsCowsMethods.calculateSD(newSolver);
				if (minSD > stnDeviation) {
					minSD = stnDeviation;
					minNumber = guess;
				}
			}
		}
		return minNumber;
	}

	public static OutputPossibilities[][] createSolver(OutputPossibilities[][] oldSolver, int x, int y, int guess) {
		OutputPossibilities[][] newSolver = new OutputPossibilities[oldSolver.length][oldSolver.length];
		for (int r = 0; r < newSolver.length; r++) {
			for (int c = 0; c < newSolver.length; c++) {
				newSolver[r][c] = new OutputPossibilities();
			}
		}
		for (int i = 0; i < oldSolver[x][y].possibleNumSet.size(); i++) {
			int[] result = BullsCowsMethods.printResult(guess, oldSolver[x][y].possibleNumSet.get(i),
					oldSolver.length - 1);
			newSolver[result[0]][result[1]].possibleNumSet.add(oldSolver[x][y].possibleNumSet.get(i));
		}
		return newSolver;
	}

	public static int displayResults(int[] tries) {
		int[] displayResults = new int[20];
		int total = 0;
		for (int num : tries) {
			total += num;
			displayResults[num]++;
		}
		System.out.println();
		for (int x = 1; x < displayResults.length; x++) {
			if (x == 1)
				System.out.println("Numbers guessed in " + x + " attempt: " + displayResults[x]);
			else
				System.out.println("Numbers guessed in " + x + " attempts: " + displayResults[x]);
			if (displayResults[x] == 0)
				break;
		}
		System.out.println();
		return total;
	}

	public static OutputPossibilities[][] createFirstSolver(int length, ArrayList<Integer> list, int guess) {
		OutputPossibilities[][] solver = new OutputPossibilities[length][length];
		for (int r = 0; r < solver.length; r++) {
			for (int c = 0; c < solver.length; c++) {
				solver[r][c] = new OutputPossibilities();
			}
		}
		for (int x = 0; x < list.size(); x++) {
			int[] result = BullsCowsMethods.printResult(guess, list.get(x), length - 1);
			solver[result[0]][result[1]].possibleNumSet.add(x);
		}
		return solver;
	}

	public static int getDigitInput(Scanner sc, int lower, int higher, String msg) {
		int digits = Integer.MIN_VALUE;
		while (digits < lower || digits > higher) {
			System.out.print(msg + " ");
			try {
				digits = sc.nextInt();
			} catch (InputMismatchException e) {
				sc.nextLine();
			}
			if (digits < lower || digits > higher) {
				System.out.println("Must enter a digit between " + lower + " and " + higher + " inclusive.");
			}
		}
		return digits;
	}

	public static boolean getRepeatingInput(Scanner sc) {
		System.out.print("\nEnter \"n\" to disable repeating digits. Otherwise, enter any other key: ");
		String noRepeatingInputs = "nN";
		if (noRepeatingInputs.contains(sc.next())) {
			return false;
		}
		return true;
	}

	public static boolean getUndoInput(Scanner sc) {
		System.out.print("\nEnter \"u\" to undo last move. Otherwise, enter any other key: ");
		String noRepeatingInputs = "uU";
		if (noRepeatingInputs.contains(sc.next())) {
			return true;
		}
		return false;
	}

	public static OutputPossibilities[][] createFirstSolver(int length, ArrayList<Integer> arrayList) {
		OutputPossibilities[][] solver = new OutputPossibilities[length][length];
		for (int r = 0; r < solver.length; r++) {
			for (int c = 0; c < solver.length; c++) {
				solver[r][c] = new OutputPossibilities();
			}
		}
		return solver;
	}

	public static String get100Values(ArrayList<Integer> possibleNumSet) {

		if (possibleNumSet.size() <= 100) {
			return possibleNumSet.toString();
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			int skip = possibleNumSet.size() / 100;
			for (int i = 0; i < possibleNumSet.size(); i += skip) {
				if (i + skip < possibleNumSet.size()) {
					sb.append(possibleNumSet.get(i) + ", ");
				} else {
					sb.append(possibleNumSet.get(i) + "]");
				}
			}
			return sb.toString();
		}

	}

}
