
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> possible = new ArrayList<Integer>();
		System.out.println("Number of digits: ");
		int digits = sc.nextInt();
		Scanner num = new Scanner(System.in);
		System.out.println("Enter guess: ");
		int guess = num.nextInt();
		System.out.println("Enter Number of Green gumballs: ");
		int greengums = num.nextInt();
		System.out.println("Enter Number of Red gumballs: ");
		int redgums = num.nextInt();
		String solver = greengums + "G" + redgums + "R";
		int max = (int) (Math.pow(10, digits));
		for (int x = 0; x < max; x++) {
			if (solver.equals(printResult(guess, x, digits)))
				possible.add(x);
		}
		System.out.println("There are " + possible.size() + " possible numbers");
		System.out.println("Try guessing: " + possible.get(possible.size() / 3));

		while (possible.size() > 1) {
			System.out.println("Enter NEW guess: ");
			guess = num.nextInt();
			System.out.println("Enter Number of Green gumballs: ");
			greengums = num.nextInt();
			System.out.println("Enter Number of Red gumballs: ");
			redgums = num.nextInt();
			solver = greengums + "G" + redgums + "R";
			for (int x = 0; x < possible.size(); x++) {
				if (!solver.equals(printResult(guess, possible.get(x), digits))) {
					possible.remove(x);
					x--;
				}
			}
			if (possible.size() < 200) {
				for (int c : possible) {
					System.out.println(c);
				}
			} else {
				System.out.println("There are " + possible.size() + " possible numbers");
				System.out.println("Try guessing: " + possible.get(10));
			}

		}
		if (possible.size() == 1)
			System.out.println("The number is: " + possible.get(0));
		else
			System.out.println("Error");
	}

	public static String printResult(int a, int b, int digits) {

		int tempA = a;
		int tempB = b;
		String aa = a + "";
		String bb = b + "";
		char[] alist = aa.toCharArray();
		char[] blist = bb.toCharArray();
		ArrayList<Character> aLists = new ArrayList<Character>();
		ArrayList<Character> bLists = new ArrayList<Character>();
		for (char c : alist)
			aLists.add(c);
		for (char cc : blist)
			bLists.add(cc);
		while (aLists.size() < digits)
			aLists.add(0, '0');
		while (bLists.size() < digits)
			bLists.add(0, '0');
		int green = 0;
		int red = 0;

		for (int xx = 0; xx < aLists.size(); xx++) {
			if (aLists.get(xx) == bLists.get(xx)) {
				green++;
				aLists.remove(xx);
				bLists.remove(xx);
				xx--;
			}
		}

		for (int x = 0; x < aLists.size(); x++) {
			for (int y = 0; y < aLists.size(); y++) {
				if (aLists.get(x) == bLists.get(y)) {
					red++;
					aLists.remove(x);
					bLists.remove(y);
					x = 0;
					y = -1;
				}
			}
		}

		return green + "G" + red + "R";
	}
}