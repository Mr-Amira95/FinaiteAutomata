/*
	Hussam Amira
	201610567

	JAVA program to build a dynamic Finite Automata for any language with any
	alphabet and then test the machine to accept or reject any given word.
*/

import java.util.*;
import java.util.Arrays;
import java.util.List;


class Assignment2{

	public static void main (String args[]){

		do{//To Repeat The whole Program

			int alphabetNumber = getNumber("Alphabets", true);
			String[] alphabetArr = getName(alphabetNumber, "Alphabets");

			int stateNumber = getNumber("States", true);
			String[] stateArr = getName(stateNumber, "States");

			String[] startStateName = getStartFinalNames(1, stateArr, "Start State", true);
			String startState = startStateName[0];

			int finalStateNumber = getNumber("Final States", true);
			finalStateNumber = checkFinalNumber(finalStateNumber, stateNumber, true);
			String[] finalStateName = getStartFinalNames(finalStateNumber, stateArr, "Final States", true);

			String[][] transition = getTranstion(alphabetNumber, stateNumber, alphabetArr, stateArr, true);

			String word;

			do{//To Try Another word

				word = getWord();
				printArr(alphabetArr, "Alphabets");
				printArr(stateArr, "States");
				System.out.println("The Start State Name is { " + startState + " }");
				printArr(finalStateName, "Final States");
				printDimArr(transition, "Transition");

				String wordArr[] = splitWord(word, alphabetArr);

				if(wordArr != null){
					System.out.println("The Word is { " + word + " }");
					checkWord(stateArr, alphabetArr, transition, wordArr, startState, finalStateName);
				}//End if

			}while(repeat("Try Another Word"));
		}while(repeat("Repeat Program"));

		System.out.println("\nThanks for Visiting Mr.Amira World :)\n");

	}//End Main Method

//------------------------------------------------------------------------------------------------------

	public static int getNumber(String name, boolean wrong){

		String numString;

		//Get Number and Check it
		do{
			Scanner A = new Scanner(System.in);
			System.out.println("Please Enter The Number Of "+ name);
			numString = A.nextLine();

			for(int i=0; i<numString.length();i++){
				if(numString.charAt(i)>=48 && numString.charAt(i)<= 57){
					wrong = false;
				}//End IF
				else{
					wrong = true;
					System.out.println("Incorrect Number of " + name);
					System.out.println("---------------------------------------");
					break;
				}//End Else
			}//End For
		}while(wrong); //End Get Number of alphapets Loop and Check it

		int Num = Integer.parseInt(numString);
		return Num;

	}//End Get Number Method

//------------------------------------------------------------------------------------------------------

	public static String[] getName(int Num, String name){
		String alpha;
		boolean wrong = true;

		//Get Array of Strings
		String[] Arr = new String[Num];
		for (int i=0; i<Num; i++){
			List<String> list = Arrays.asList(Arr);

			do{
				System.out.println("Please Enter " + name + " Number " + (i+1));
				Scanner A = new Scanner(System.in);
				alpha = A.nextLine();
				if(!list.contains(alpha) && alpha.length()==1){
						Arr[i] = alpha;
						wrong = false;
				}// End if
				else{
						wrong = true;
						System.out.println("Incorrect Input Please Enter one character " + name + " without Duplicate");
				}//End Else
			}while(wrong);
		}//End Get Alphapets Loop

		System.out.println("---------------------------------------");

		return Arr;
	}//End getName method

//------------------------------------------------------------------------------------------------------

	public static int checkFinalNumber(int finalStateNumber, int stateNumber, boolean wrong){

		//Check Number of Final States is less than Number of States
		do{
			if(finalStateNumber > 0 && finalStateNumber <= stateNumber){
				wrong = false;
				break;
			}//End if
			else{
				System.out.println("The Number of Final States is Invalid, Please Enter Number Between 1 and " + stateNumber);
				System.out.println("----------------------------------------");
				finalStateNumber = getNumber("Final States",true);
			}//End else
		}while(wrong); // End Check Number of Final States is less than Number of States

		return finalStateNumber;
	}//End checkFinalNumber Method

//------------------------------------------------------------------------------------------------------

	public static String[] getStartFinalNames(int finalNum, String[] stateArr, String name, boolean wrong){

		String[] StateArr = new String[finalNum];
		String currentState;

		for (int i=0; i<finalNum; i++){
			wrong=true;
			do{
				List<String> list = Arrays.asList(StateArr);

				System.out.println("Please Enter The Name of The " + name + " Name Number " + (i+1));
				Scanner A = new Scanner(System.in);
				currentState = A.nextLine();

				for(int j=0; j<stateArr.length; j++){
					if (stateArr[j].equals(currentState) && !list.contains(currentState)){
						StateArr[i]=currentState;
						wrong = false;
					}//End IF
				}//End check state loop

				if (wrong){
					System.out.println("Incorrect Name of " + name + " Please Try Again without Duplicate");
					System.out.println("---------------------------------------");
				}//End IF

			}while(wrong);
		}//End Get Final States and check it

		System.out.println("---------------------------------------");

		return StateArr;
	}//End getStartFinalNames Method

//------------------------------------------------------------------------------------------------------

	public static String[][] getTranstion(int alphabetNumber,int stateNumber,String[] alphabetArr, String stateArr[], boolean wrong){

		String[][] transition = new String[stateNumber][alphabetNumber];
		String currentTransition ;

		for (int i=0; i < stateNumber; i++){//Row Loop
			for(int j=0; j < alphabetNumber; j++){//Colomn Loop
				do{
					wrong=true;

					System.out.println("If you are in state " + stateArr[i] + " and the alphapet was \""
					+ alphabetArr[j] + "\" you will go to state?");

					Scanner A = new Scanner(System.in);
					currentTransition = A.nextLine();

					for(int k=0; k<stateNumber; k++){
						if (stateArr[k].equals(currentTransition)){
							transition [i][j] = currentTransition;
							wrong=false;
							break;
						}//End If
					}//End check state loop
				}while(wrong);
			}//End Colomn Loop
		}//End Row Loop

		System.out.println("---------------------------------------");

		return transition;
	}//End getTransition Method

//------------------------------------------------------------------------------------------------------

	public static void printArr(String[] Arr, String name){

		System.out.println("The Number of " + name + " = " + Arr.length);
		System.out.print("The " + name + " are { ");
		for(int i=0; i<Arr.length; i++){
			if(i==(Arr.length -1))
				System.out.print(Arr[i]);
			else
				System.out.print(Arr[i]+", ");
		}//End For Loop
		System.out.println(" }");
	}//End printArr Method

//------------------------------------------------------------------------------------------------------

	public static void printDimArr(String[][] Arr, String name){

		System.out.println("The " + name + " Table:");
		for(int i=0; i<Arr.length; i++){
			for(int j=0; j<Arr[i].length; j++)
				System.out.print(Arr [i][j] +" ");
			System.out.println();
		}//End For
	}//End printDimArr Method

//------------------------------------------------------------------------------------------------------

	public static String getWord(){

		System.out.println("Please Enter a Word to verify");
		Scanner A = new Scanner(System.in);
		String word = A.nextLine();

		return word;
	}//End getWord Method

//------------------------------------------------------------------------------------------------------

	public static boolean repeat(String name){

        System.out.println("---------------------------------------");
		Scanner A = new Scanner(System.in);
		System.out.println("Please Enter (Y or y) To " + name + " OR any another Word to Exit");
        String repeat = A.nextLine();
        System.out.println("---------------------------------------");

        return (repeat.equals("Y")||repeat.equals("y"));
	}//End repeat Method

//------------------------------------------------------------------------------------------------------

	public static String[] splitWord(String word, String[] alphabetArr){

		String [] wordArr = new String[word.length()];
		String currentWord;

		for(int i=0; i<word.length(); i++){

			boolean found = false;
			do{
				currentWord = String.valueOf(word.charAt(i));
				for(int j=0; j<alphabetArr.length; j++){
					if(alphabetArr[j].equals(currentWord)){
						wordArr[i] = currentWord;
						found = true;
					}//End if
				}//End j loop (search in alphabets)

				if(!found){
					System.out.println("The word is Rejected (Not from the Alphabets)");
					return null;
				}//End else

			}while(!found);
		}//End i loop

		return wordArr;
	}//End splitWord Method

//------------------------------------------------------------------------------------------------------

	public static void checkWord(String[] stateArr, String[] alphabet, String[][] transition, String[] word, String startState, String[] finalStates){

		boolean found = false;
		String state = startState;

		for(int i=0; i<word.length; i++){
			String alpha = word[i];
			for(int j=0; j<stateArr.length; j++){
				for(int k=0; k<alphabet.length; k++){
					if (state.equals(stateArr[j]) && alpha.equals(alphabet[k])){
						state = transition[j][k];
						j = stateArr.length;
						k = alphabet.length;
					}
				}//End k loop (#alphabets)
			}//End j Loop (#states)
		}//End i loob (word length)

		for(int i=0; i<finalStates.length; i++){
			if(state.equals(finalStates[i])){
				System.out.println("The Word is Acceptable");
				found = true;
			}//End if
		}//End search in final loop


		if(!found)
			System.out.println("The Word is Rejected");

	}//End checkWord Method

}//End Class