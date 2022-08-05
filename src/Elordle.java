
public class Elordle implements OnePlayerGame{
	private String target;
	private int numWGuesses;
	private String partialWord;
	private String wrongGuesses;
	private int count;
	
	public Elordle(String target, int numWGuesses) {
		setTarget(target.toUpperCase());
		setNumWGuesses(numWGuesses);
		setPartialWord("");
		setWrongGuesses("");
	}
	
	public Elordle(String target) {
		this(target.toUpperCase(),6);
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		if (target == null) {
			this.target = "";
		}
		else if(target.contains(" ")) {
			String nTarget = "";
			for (int i = 0; i < target.length(); i++) {
				if (target.charAt(i) != ' ') {
					nTarget += target.charAt(i);
				}
				else {
					break;
				}
			}
			this.target = nTarget;
		}
		else {
			this.target = target;
		}
	}

	public int getNumWGuesses() {
		return numWGuesses;
	}

	public void setNumWGuesses(int numWGuesses) {
		if (numWGuesses > 0 && numWGuesses < 26) {
			this.numWGuesses = numWGuesses;
		}
		else {
			this.numWGuesses = 7;
		}
	}

	public String getPartialWord() {
		return partialWord;
	}

	public void setPartialWord(String partialWord) {
		if (partialWord == null || !(partialWord.length() == target.length())) {
			this.partialWord = "";
			while (this.partialWord.length() != target.length()) {
				this.partialWord += "-";
			}
		}
		else {
			this.partialWord = partialWord;
		}
	}

	public String getWrongGuesses() {
		return wrongGuesses;
	}

	public void setWrongGuesses(String wrongGuesses) {
		if (wrongGuesses == null) {
			wrongGuesses = "";
		}
		else {
			this.wrongGuesses = wrongGuesses;
		}
	}

	@Override
	public void createPlay(String play) {
		for (int i = 0; i < play.length(); i++) {
			char letter = play.charAt(i);
			char tLetter = target.charAt(i);
			String lString = String.valueOf(letter);
			
				if (letter == tLetter) {
					partialWord = partialWord.substring(0,i) + lString.toUpperCase() + partialWord.substring(i+1);
				}
				
				else if (target.contains(lString)){
					partialWord = partialWord.substring(0,i) + lString.toLowerCase() + partialWord.substring(i+1);
				}
				else {
					if (wrongGuesses.contains(lString)) {
						
					}
					else {
						wrongGuesses += lString + " ";
					}

				}
			
		}
		
	}

	@Override
	public int validPlay(String play) {
		boolean upperCase = true;
		
		for(char search : play.toCharArray()) {
		       if(Character.isLetter(search) && Character.isLowerCase(search)) {
		           upperCase = false;
		           break;
		        }
		}
		  
		if (play.length() != target.length() || upperCase == false) {
			return 0;
		}
		else {
			setPartialWord("");
			return 1;
		}
	}

	@Override
	public int gameOver() {
		if (count == numWGuesses + 1 && !(partialWord.equals(target))) {
			System.out.println("Sorry, you did not get the word!");
			System.out.println("Your word was " + target);
			return 1;
		}
		else if (partialWord.equals(target)) {
			System.out.println("Impressive! You have won!");
			count--;
			System.out.printf("You won in %d guess(es)", count);
			return 1;
		}
		return 0;
	}

	@Override
	public String stateOfGame() {
		
		String pW = getPartialWord();
		int rG = getNumWGuesses() - count;
		
		count++;
		
		
		
		System.out.println("WRONG LETTERS: " + wrongGuesses);
		String sOG = "Word: " + pW + "\nGuesses left: " + rG;
		System.out.println(sOG);
		return sOG;
	}

}
