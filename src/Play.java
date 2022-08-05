import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;


public class Play {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		OnePlayerGame myGame;
		
		System.out.println("Pick your poision: Wordle or Snowman?");
		String gameOfChoice = input.next();
		
		if (gameOfChoice.equals("Snowman") || gameOfChoice.equals("snowman")) {
			System.out.println();
			System.out.println("Rules: Input a lower case letter.");
			System.out.println("Figure out you word before the guess count runs out. Happy hunting!");
			System.out.println();
			myGame = new Snowman(chooseRandomSnowWord());
			playGame(myGame, input);
		}
		else if (gameOfChoice.equals("Wordle") || gameOfChoice.equals("wordle")) {
			System.out.println();
			System.out.println("Rules: Input a 5-letter word in all captial letters.");
			System.out.println("If you have a lowercase letter in your word, it means that it is in the word but in the wrong place.");
			System.out.println("Otherwise, a capital letter means a correct letter in the correct place. Happy hunting!");
			System.out.println();
			myGame = new Elordle(chooseRandomWord());
			playGame(myGame, input);
		}
		else {
			System.out.println("Run and try again.");
		}
		
		
	}
	
	public static void playGame(OnePlayerGame myGame, Scanner scnr) {
				
			myGame.stateOfGame();
	
			
			while (myGame.gameOver() == 0) {
				
				System.out.print("Make a guess: ");
				String letter = scnr.next();
				
				if (myGame.validPlay(letter) == 1) {				
					myGame.createPlay(letter);
					myGame.stateOfGame();
	
					System.out.println();
				}
				else {
					System.out.println("Invalid submission. Try again...");
					System.out.println();
				}
			
		
				
			}
	}
		
	
		
	// Code below here provided for selecting a random word.
	// You should ONLY edit the filename variable in chooseRandomWord below here!
	// ---------------------------------------------------------------------------------------
	public static String chooseRandomWord() {
		String filename = "dictionary5chars.txt";
		Scanner file = getFileScanner(filename);
		int countLines = 0;
		while (file.hasNextLine()) {
			file.nextLine();
			countLines++; 
		}
		int randLine = (int)(Math.random() * countLines);

		file = getFileScanner(filename);
		int curLine = 0;
		while (file.hasNextLine()) {
			String word = file.nextLine();
			if(curLine == randLine) {
				return word;
			}
			curLine++; 
		}

		return "";
	}
	
	public static String chooseRandomSnowWord() {
		String filename = "snowwords.txt";
		Scanner file = getFileScanner(filename);
		int countLines = 0;
		while (file.hasNextLine()) {
			file.nextLine();
			countLines++; 
		}
		int randLine = (int)(Math.random() * countLines);

		file = getFileScanner(filename);
		int curLine = 0;
		while (file.hasNextLine()) {
			String word = file.nextLine();
			if(curLine == randLine) {
				return word;
			}
			curLine++; 
		}

		return "";
	}

	public static Scanner getFileScanner(String fileName) {
		try {
			FileInputStream textFileStream = new FileInputStream(fileName);
			Scanner inputFile = new Scanner(textFileStream);
			return inputFile;
		}
		catch (IOException ex) {
			System.out.println("Warning: could not open " + fileName);
			return null;
		}
	}
}
