public class Ticket {
  private int seatNumber;
  private ShowTimes showtime;
  private double ticketPrice;
  private int ID;

  // Constructor
  public Ticket() {
  }

  public Ticket(int seatNumber, ShowTimes showtime, double ticketPrice) {
    
    this.seatNumber = seatNumber;
    this.showtime = showtime;
    this.ticketPrice = ticketPrice;
  }

  // Getters and setters
  public int getSeatNumber() {
    return seatNumber;
  }

  public void setID(int iD) {
    ID = iD;
  }

  public int getID() {
    return ID; 
  }

  public void setSeatNumber(int seatNumber) {
    this.seatNumber = seatNumber;
  }

  public ShowTimes getShowtime() {
    return showtime;
  }

  public void setShowtime(ShowTimes showtime) {
    this.showtime = showtime;
  }

  public double getTicketPrice() {
    return ticketPrice;
  }

  public void setTicketPrice(double ticketPrice) {
    this.ticketPrice = ticketPrice;
  }
}
