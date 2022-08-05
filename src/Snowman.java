
public class Snowman implements OnePlayerGame {
	private String target;
	private int numWGuesses;
	private String partialWord;
	private String wrongGuesses;
	
	public Snowman(String target, int numWGuesses) {
		setTarget(target);
		setNumWGuesses(numWGuesses);
		setPartialWord("");
		setWrongGuesses("");
	}
	
	public Snowman(String target) {
		this(target,7);
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
		if (target.contains(play)) {
			for (int i = 0; i < target.length(); i++) {
				if (target.contains(play)) {
					int location = target.indexOf(play);
//					String secFinding = partialWord.substring(location);
					int secLocation = target.indexOf(play, location+1);
					int thLocation = target.indexOf(play, secLocation+1);
					int fourLocation = target.indexOf(play, thLocation+1);
					int fifLocation = target.indexOf(play, fourLocation+1);
					int sixLocation = target.indexOf(play, fifLocation+1);
					
					if (i == location) {
						partialWord = partialWord.substring(0,i) + play + partialWord.substring(i+1);
					}
					else if (i == secLocation) {
						partialWord = partialWord.substring(0,i) + play + partialWord.substring(i+1);
					}
					else if (i == thLocation) {
						partialWord = partialWord.substring(0,i) + play + partialWord.substring(i+1);
					}
					else if (i == fourLocation) {
						partialWord = partialWord.substring(0,i) + play + partialWord.substring(i+1);
					}
					else if (i == fifLocation) {
						partialWord = partialWord.substring(0,i) + play + partialWord.substring(i+1);
					}
					else if (i == sixLocation) {
						partialWord = partialWord.substring(0,i) + play + partialWord.substring(i+1);
					}
				}
			}
		}
		else {
			wrongGuesses += play + " ";
		}
		
	}
	
	@Override
	public int validPlay(String play) {
		char cInput = play.charAt(0);
		if (play.length() > 1 || !(Character.isLowerCase(cInput))) {
			return 0;
		}
		else {
			return 1;
		}
	}
	@Override
	public int gameOver() {
		if ((wrongGuesses.length()/2) == numWGuesses) {
			System.out.println("You have built a snowman with your bad guessing!");
			System.out.println("Your word was " + target);
			return 1;
		}
		else if (!(partialWord.contains("-")) || partialWord.equals(target)) {
			System.out.println("Congrats on not building a snowman! You have won!");
			System.out.println("Your word was: " + target);
			return 1;
		}
		return 0;
	}
	@Override
	public String stateOfGame() {
		String pW = getPartialWord();
		String wG = getWrongGuesses();
		int rG = getNumWGuesses() - (wG.length()/2);
		
		String snoDisplay = "";
		double healthPer = ((double) rG / (double) getNumWGuesses());
		
		if (healthPer < 1.00 && healthPer > .850) {
			snoDisplay =	 "     ___\r\n" + 
						"   _[___]_\r\n";
				
		}
		else if (healthPer < .850 && healthPer > .700) {
			snoDisplay =	 "     ___\r\n" + 
						"   _[___]_\r\n" +
						   "   ( '' )\n";
				
		}
		else if (healthPer < .700 && healthPer > .550) {
			snoDisplay =   "     ___\r\n" + 
					  "   _[___]_\r\n" +
					   	"   ( '' )\r\n" +
					   	"   (  :  )\r\n";
				
		}
		else if (healthPer < .550 && healthPer > .400) {
			snoDisplay =   "     ___\r\n" + 
					  "   _[___]_\r\n" +
					   	"   ( '' )\r\n" +
					   	"-- (  :  ) --\r\n";
				
		}
		else if (healthPer < .400 && healthPer > .250) {
			snoDisplay =   "     ___\r\n" + 
					  "   _[___]_\r\n" +
					   	"   ( '' )\r\n" +
					   	"-- (  :  ) --\r\n" +
					   	"   /  :  \\ \n";
				
		}
		else if (healthPer < .250 && healthPer > .100) {
			snoDisplay =   "     ___\r\n" + 
					  "   _[___]_\r\n" +
					   	"   ( '' )\r\n" +
					   	"-- (  :  ) --\r\n" +
					   	"   /  :  \\ \n" +
					   	"   \\  :  /\r\n";
				
		}
		else if (healthPer < .100) {
			snoDisplay =   "     ___\r\n" + 
					  "   _[___]_\r\n" +
					   	"   ( '' )\r\n" +
					   	"-- (  :  ) --\r\n" +
					   	"   /  :  \\ \n" +
					   	"   \\  :  /\r\n" +
					   	"   '-----'";
				
		}
		
		String sOG = "Word: " + pW + "\nIncorrect letters: " + wG + "\nGuesses left: " + rG + "\n" + snoDisplay;
		System.out.println(sOG);
		return sOG;
		
	}
	
	
	
}
