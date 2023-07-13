
import java.util.*;

public class A2p3 {
	
	
	static final String rUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //Add String of all the Alphabet

	public static void main(String[] args) {
		
		Scanner input = new Scanner (System.in);
		int m = input.nextInt(); //Command Line for input m
		int n =  input.nextInt(); //Command Line for input n

		if (m % 60 != 0) { //m is assumed to be a multiple of 60
			System.err.println("Have to be a multiple of 60");  
			System.exit(0);
		}
		if (!(n >= 2 && n <= 6)) { //n is an integer between 2 and 6 inclusive
			System.err.println("n needs be between 2-6");
			System.exit(0);
		}
		int String = m; //Generate a string of m random upper case English characters
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(String); //Add a StringBuilder
		int rightLimit = rUpper.length();

		for (int i = 0; i < String; i++) {
			int randomLimitedInt = random.nextInt(rightLimit);
			buffer.append(rUpper.charAt(randomLimitedInt)); //Store string in a char array
		}
		
		String generatedString = buffer.toString();
		char[] charArray = generatedString.toCharArray();
		
		System.out.printf("Using " + n + " threads to handle " + m + " chars.");
		System.out.println();
		
		System.out.println("Original random upper case string:");
		
		System.out.println(generatedString);
		
		System.out.println("Reverse-offset lower case string:");
		
		ArrayList<Runnable> processList = new ArrayList<Runnable>();
		ArrayList<Thread> threadList = new ArrayList<Thread>();
		
		int startingIndex = 0;
		int intervalIndex = m / n;
		for (int i = 0; i < n; i++) {
			if (intervalIndex <= m) {
				processList.add(new ThreadState(startingIndex, intervalIndex, charArray));
				startingIndex = startingIndex + m / n; 
				intervalIndex = intervalIndex + m / n;
				threadList.add(new Thread(processList.get(i)));
			}
		}
	}
}

class ThreadState implements Runnable { //Use the Thread class to create n threads to convert the string of m chars 
											//in place into a reverse-offset lower case version

	public ThreadState(int start, int end, char[] s) {
		String output = "";
		for (int i = start; i < end; i++) {
			if (s[i] == 'A') {
				s[i] = 'z';
				output = output + Character.toLowerCase(s[i]);
			} else {
				s[i] = (char) (s[i] - 1);
				output = output + Character.toLowerCase(s[i]);
			}
		}
		System.out.print(output);
	}

	public void run() { //Need in order to run class ThreadState 
	}
}