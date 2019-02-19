/* Nicholas Bravata 
 * CPS 240 Fall 2018
 * Reservation Assignment 
 */
package reservationpkg;

import java.util.ArrayList;
import java.util.Scanner;

public class MyMain {

	public static void main(String[] args) {
		
		//Initializers 
		ArrayList<Reservation> airplane = new ArrayList<Reservation>(20);
		String name;
		int numSeat, seatRow, seatRow2;
		char seatLetter, seatLetter2;
		
		//Calls the Function emptyPlane.
		emptyPlane(airplane);
		
		//Constant for the while loop that controls making additional iterations.
		int loopConstant = 1;
		
		//Reservation loop.
		Scanner userInput = new Scanner(System.in);
		while(loopConstant != 0) {
			printPlane(airplane);
			
			//Start of the loop. Asks customer how many seats they would like to reserve at a maximum of 2.
			System.out.println("How many seats do you want to reserve? (Max of 2)");
			
			numSeat = userInput.nextInt();
			while (numSeat !=1 && numSeat !=2 ) {
				System.out.println("You are only allowed a maximum of 2 seats...");
				System.out.println("How many seats do you want to reserve? (Max of 2)");
				numSeat = userInput.nextInt();
			}
			
			//For reservations of 1 seat.
			if(numSeat == 1) {
				System.out.println("Which row would you like? (1-5)");
				seatRow = userInput.nextInt();
				
				//Error check for input range.
				while(seatRow <1 && seatRow > 5) {
					System.out.println("Wrong Input!");
					System.out.println("Which row would you like? (1-5)");
					seatRow = userInput.nextInt();
				}
				System.out.println("Which seat would you like? (A-D)");
				String seatLetterStr = userInput.next().toUpperCase();
				seatLetter = seatLetterStr.charAt(0);
				boolean letterCheck = seatLetterCheck(seatLetter);
				
				//Error check to make sure the input letter is in range of A-D
				while(letterCheck == false) {
					System.out.println("Incorrect input!\nPlease select a seat A-D");
					seatLetterStr = userInput.next().toUpperCase();
					seatLetter = seatLetterStr.charAt(0);
					letterCheck = seatLetterCheck(seatLetter);
				}
				
				//Calls a function to see if the seat is available. If true this will then take the clients name and change the reservation.
				if(isEmpty(airplane, seatRow, seatLetter)) {
					System.out.println("That seat is availible!\nPlease input a name for your Reservation:");
					name = userInput.next();
					Reservation newReservation = getReservation(airplane, seatRow, seatLetter);
					newReservation.changeReservation(name, numSeat, seatRow, seatLetter);
				}
				else {
					System.out.println("That seat is NOT availible!\nPlease restart your reservation process:");
				}
			}
			// For reservations of 2. Almost identical to the one above but with some changes based if seats are together or not.
			if(numSeat == 2) {
				//Choose first seat
				System.out.println("For your first seat. Which row would you like? (1-5)");
				seatRow = userInput.nextInt();
				while(seatRow <1 && seatRow > 5) {
					System.out.println("Wrong Input!");
					System.out.println("Which row would you like? (1-5)");
					seatRow = userInput.nextInt();
				}
				System.out.println("Which seat would you like? (A-D)");
				String seatLetterStr = userInput.next().toUpperCase();
				seatLetter = seatLetterStr.charAt(0);
				boolean letterCheck = seatLetterCheck(seatLetter);
				
				while(letterCheck == false) {
					System.out.println("Incorrect input!\nPlease select a seat A-D");
					seatLetterStr = userInput.next().toUpperCase();
					seatLetter = seatLetterStr.charAt(0);
					letterCheck = seatLetterCheck(seatLetter);
				}
				//Choose second seat
				System.out.println("For your second seat. Which row would you like? (1-5)");
				seatRow2 = userInput.nextInt();
				while(seatRow2 <1 && seatRow2 > 5) {
					System.out.println("Wrong Input!");
					System.out.println("Which row would you like? (1-5)");
					seatRow2 = userInput.nextInt();
				}
				System.out.println("Which seat would you like? (A-D)");
				String seatLetter2Str = userInput.next().toUpperCase();
				seatLetter2 = seatLetter2Str.charAt(0);
				boolean letterCheck2 = seatLetterCheck(seatLetter2);
				
				while(letterCheck2 == false) {
					System.out.println("Incorrect input!\nPlease select a seat A-D");
					seatLetter2Str = userInput.next().toUpperCase();
					seatLetter2 = seatLetter2Str.charAt(0);
					letterCheck2 = seatLetterCheck(seatLetter);
				}
				//Checks to see if the seats are next to each other or apart from each other.
				char seatLetterPlus = seatLetter;
				char seatLetter2Plus = seatLetter2;
				
				//used to determine if the seats are next to each other.
				seatLetterPlus++;
				seatLetter2Plus++;
				
				if (seatRow == seatRow2) {
					//If the 2 seats selected are the exact same seat it will kick out the user to restart from the beginning.
					if(seatLetter == seatLetter2) {
						System.out.println("Seats are the same.\n Please restart your reservation process.");
					}
					else if(seatLetterPlus == seatLetter2 || seatLetter2Plus == seatLetter) {
						//Checks to see if seats are next to each other, then changes the reservations.
						if(isEmpty(airplane, seatRow, seatLetter) && isEmpty(airplane, seatRow2, seatLetter2)) {
							//Changes reservation.
							System.out.println("Your seats are availible!\nPlease input a name for your Reservation:");
							name = userInput.next();
							Reservation newReservation = getReservation(airplane, seatRow, seatLetter);
							Reservation newReservation2 = getReservation(airplane, seatRow2, seatLetter2);
							newReservation.changeReservation(name, numSeat, seatRow, seatLetter);
							newReservation2.changeReservation(name, numSeat, seatRow2, seatLetter2);
						}
						else {
							System.out.println("One of your seats is taken... Please restart the reservation process and select empty seats.");
						}
					}
					else {
						String userAnswerStr;
						char userAnswerChar;
						System.out.println("Your seats are not next to each other. Would you still like to make the reservation? (Y-N)");
						userAnswerStr = userInput.next().toUpperCase();
						userAnswerChar = userAnswerStr.charAt(0);
						while(userAnswerChar !='Y' && userAnswerChar !='N') {
							System.out.println("Incorrect input. Please input Y or N.");
							userAnswerStr = userInput.next().toUpperCase();
							userAnswerChar = userAnswerStr.charAt(0);
						}
						
						if (userAnswerChar == 'Y') {
							//Change reservations
							if(isEmpty(airplane, seatRow, seatLetter) && isEmpty(airplane, seatRow2, seatLetter2)) {
								//Changes reservation.
								System.out.println("Your seats are availible!\nPlease input a name for your Reservation:");
								name = userInput.next();
								Reservation newReservation = getReservation(airplane, seatRow, seatLetter);
								Reservation newReservation2 = getReservation(airplane, seatRow2, seatLetter2);
								newReservation.changeReservation(name, numSeat, seatRow, seatLetter);
								newReservation2.changeReservation(name, numSeat, seatRow2, seatLetter2);
							}
							else {
								System.out.println("One of your seats is taken... Please restart the reservation process and select empty seats.");
							}
						}
						else {
							System.out.println("Canceling Reservation Process...");
						}
					}
				}
				else {
					String userAnswerStr;
					char userAnswerChar;
					System.out.println("Your seats are not next to each other. Would you still like to make the reservation? (Y-N)");
					userAnswerStr = userInput.next().toUpperCase();
					userAnswerChar = userAnswerStr.charAt(0);
					while(userAnswerChar !='Y' && userAnswerChar !='N') {
						System.out.println("Incorrect input. Please input Y or N.");
						userAnswerStr = userInput.next().toUpperCase();
						userAnswerChar = userAnswerStr.charAt(0);
					}
					
					if (userAnswerChar == 'Y') {
						//Change reservations
						if(isEmpty(airplane, seatRow, seatLetter) && isEmpty(airplane, seatRow2, seatLetter2)) {
							//Changes reservation.
							System.out.println("Your seats are availible!\nPlease input a name for your Reservation:");
							name = userInput.next();
							Reservation newReservation = getReservation(airplane, seatRow, seatLetter);
							Reservation newReservation2 = getReservation(airplane, seatRow2, seatLetter2);
							newReservation.changeReservation(name, numSeat, seatRow, seatLetter);
							newReservation2.changeReservation(name, numSeat, seatRow2, seatLetter2);
						}
						else {
							System.out.println("One of your seats is taken... Please restart the reservation process and select empty seats.");
						}
					}
					else {
						System.out.println("Canceling Reservation Process...");
					}
					
				}
			}
			
			//Asks user to add another reservation to add another reservation. Going through the loop again.
			System.out.println("Add another Reservation? Press 0 for NO and 1 for YES: ");
			loopConstant = userInput.nextInt();
			while (loopConstant !=0 && loopConstant !=1 ) {
				System.out.println("Wrong input!");
				System.out.println("Add another Reservation? Press 0 for NO and 1 for YES: ");
				loopConstant = userInput.nextInt();
			}
		}
		userInput.close();
		
	}
	
	//Fills the plane with empty Reservations
	public static void emptyPlane(ArrayList<Reservation> airplane) {
		
		int row = 1;
		char seat = 'A';
		String name = "Empty";
		int numSeat = 1;
		
		while(row < 6) {
			seat = 'A';
			while(seat != 'E') {
				
				Reservation customer = new Reservation(name, numSeat , row, seat);
				airplane.add(customer);
				seat++;
			}
			row++;
		}
	}
	
	//Prints a basic diagram of the plane. If the seat is empty it prints a 'O' else it prints an 'X'
	public static void printPlane(ArrayList<Reservation> airplane) {
		int row_num = 1;
		String planeSeats = "1| ";
		System.out.println("   "+"A\t  "+"B\t  "+"C\t  "+"D");
		System.out.println("   _________________________");
		for(int i=0; i<20; i++) {
			char seat = 'O';
			Reservation customer = airplane.get(i);
			if (customer.name != "Empty") {
				seat = 'X';
			}
			if((i+1)%4 == 0) {
				planeSeats += seat;
				row_num++;
				System.out.println(planeSeats);
				planeSeats = row_num +"| ";
			}
			else {
				planeSeats += seat + "\t  ";
			}
		}
	}
	
	/*Iterates through the array list to find the specified seat using the row number and letter. 
	Once found it checks to see if the name is empty. If it is then returns true else returns false.*/
	public static boolean isEmpty(ArrayList<Reservation> airplane, int seatRow, char seatLetter) {
		boolean empty = true;
		Reservation seat;
		
		for(int i = 0; i < airplane.size(); i++) {
			seat = airplane.get(i);
			if(seat.seatRow == seatRow) {
				if (seat.seatLetter == seatLetter) {
					if(seat.name != "Empty") {
						empty = false;
						break;
					}
				}
			}
		}
		
		return empty;
	}
	
	//Error check for seat letter
	public static boolean seatLetterCheck(char seatLetter) {
		boolean chk_val = false;
		//System.out.println(seatLetter);
		
		if(seatLetter == 'A' ) {
			chk_val = true;
		}
		if(seatLetter == 'B' ) {
			chk_val = true;
		}
		if(seatLetter == 'C' ) {
			chk_val = true;
		}
		if(seatLetter == 'D' ) {
			chk_val = true;
		}
		
		return chk_val;
	}
	
	//Goes through the ArrayList to find the Reservation and returns that reservation to the temporary object.
	public static Reservation getReservation(ArrayList<Reservation> airplane, int seatRow, char seatLetter) {
		Reservation seat;
		Reservation ret_val = new Reservation();
		
		for(int i = 0; i < airplane.size(); i++) {
			seat = airplane.get(i);
			if(seat.seatRow == seatRow) {
				if (seat.seatLetter == seatLetter) {
					ret_val = seat;
					return ret_val;
				}
			}
		}
		
		return ret_val;
		
	}
	
}

