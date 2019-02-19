/* Nicholas Bravata 
 * CPS 240 Fall 2018
 * Reservation Assignment 
 */
package reservationpkg;


public class Reservation {

	public String name;
	public int num_seats;
	public int seatRow;
	public char seatLetter;
	
	//Basic constructor
	Reservation(){
		
		name = "Test Name";
		num_seats = 1;
		seatRow = 1;
		seatLetter = 'A';
		
	}
	//Constructor with name and other information passed into it.
	public Reservation(String name, int num_seats, int seatRow, char seatLetter) {
		
		this.name= name;
		this.num_seats = num_seats;
		this.seatRow = seatRow;
		this.seatLetter = seatLetter;
		
	}
	//Changes a specific reservation to whatever is passed into it.
	public void changeReservation(String name, int num_seats, int seatRow, char seatLetter) {
		this.name= name;
		this.num_seats = num_seats;
		this.seatRow = seatRow;
		this.seatLetter = seatLetter;
	}
	
}
