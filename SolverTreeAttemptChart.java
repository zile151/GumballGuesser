import java.util.ArrayList;
import java.util.Scanner;

public class SolverTreeAttemptChart {
	static int[] tries;
	static ArrayList<Integer> guessList = new ArrayList<Integer>();;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<Integer>();
		int digits = BullsCowsMethods.getDigitInput(sc, 1, 9, "Enter number of digits:");
		boolean repeating = BullsCowsMethods.getRepeatingInput(sc);
		int max = (int) (Math.pow(10, digits));
		tries = new int[max];
		sc.close();
		long start = System.currentTimeMillis();
		for (int x = 0; x < max; x++) {
			if (repeating || BullsCowsMethods.isNotRepeating(x, digits)) {
				list.add(x);
				if (BullsCowsMethods.hasUniqueNumbers(x)) {
					guessList.add(x);
				}
			}
		}
		int guess = BullsCowsMethods.smartGuessSD(new OutputPossibilities[digits + 1][digits + 1], guessList, list);
		System.out.println("\nGuessed number: " + guess);
		OutputPossibilities[][] solver = BullsCowsMethods.createFirstSolver(digits + 1, list, guess);
		solve(solver, 1);
		int total = BullsCowsMethods.displayResults(tries);
		System.out.println("Average: " + (double) total / list.size() + " attempts");
		long end = System.currentTimeMillis();
		System.out.println("Took " + (end - start) + " milliseconds");
	}

	public static void solve(OutputPossibilities[][] solver, int attempts) {
		for (int x = 0; x < solver.length; x++) {
			for (int y = 0; y < solver.length; y++) {
				if (solver[x][y].possibleNumSet.size() > 1) {
					int guess = BullsCowsMethods.smartGuessSD(solver, guessList, solver[x][y].possibleNumSet);
					OutputPossibilities[][] newSolver = BullsCowsMethods.createSolver(solver, x, y, guess);
					solve(newSolver, attempts + 1);
				} else if (solver[x][y].possibleNumSet.size() == 1) {
					if (x == solver.length - 1) { // all green gumballs
						tries[solver[x][y].possibleNumSet.get(0)] = attempts;
					} else { // takes one more guess to guess number even though we know what it is
						tries[solver[x][y].possibleNumSet.get(0)] = attempts + 1;
					}
				}
			}
		}
	}
}