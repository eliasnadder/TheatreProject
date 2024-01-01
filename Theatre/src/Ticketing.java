import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Ticketing {
  // Attributes
  private List<Ticket> tickets;
  private User user;
  private ShowTimes showTimes;
  private Movie movie;
  private JButton reserveButton;
  private JRadioButton cashButton;
  private JRadioButton creditCardButton;

  // Constructor
  public Ticketing() {
  }

  public Ticketing(List<Ticket> tickets, User user, Movie movie, ShowTimes showTimes, JButton reserveButton,
      JRadioButton cashButton, JRadioButton creditCardButton) {
    this.tickets = tickets;
    this.user = user;
    this.movie = movie;
    this.showTimes = showTimes;
    this.reserveButton = reserveButton;
    this.cashButton = cashButton;
    this.creditCardButton = creditCardButton;
  }

  // Getters and setters
  public List<Ticket> getTickets() {
    return tickets;
  }

  public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
  }

  public JButton getReserveButton() {
    return reserveButton;
  }

  public void setReserveButton(JButton reserveButton) {
    this.reserveButton = reserveButton;
  }

  public JRadioButton getCashButton() {
    return cashButton;
  }

  public void setCashButton(JRadioButton cashButton) {
    this.cashButton = cashButton;
  }

  public JRadioButton getCreditCardButton() {
    return creditCardButton;
  }

  public void setCreditCardButton(JRadioButton creditCardButton) {
    this.creditCardButton = creditCardButton;
  }

  public User getUsers() {
    return user;
  }

  public void setUsers(User users) {
    this.user = users;
  }

  public ShowTimes getShowTimes() {
    return showTimes;
  }

  public void setShowTimes(ShowTimes showTimes) {
    this.showTimes = showTimes;
  }

  public Movie getMovies() {
    return movie;
  }

  public void setMovies(Movie movie) {
    this.movie = movie;
  }

  // Methods for displaying available seats
  public int displayAvailableSeats(ShowTimes showTimes) {
    return showTimes.getAvailableSeats();
  }

  public void processTicketReservation(int showTimeIndex, int j, List<Ticket> tickets, Movie movie, User user,
      JTextField ticketField, int selectedShowTimeIndex, JRadioButton cashButton, JRadioButton creditCardButton) {
    // Check if the index is within the bounds of the showtimes list
    if (showTimeIndex < 0 || showTimeIndex >= movie.getShowtimes().size()) {
      JOptionPane.showMessageDialog(reserveButton, "Invalid showtime selection. Index out of bounds.", "Error",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Check if a payment method was selected
    if (cashButton.isSelected() || creditCardButton.isSelected()) {
      Ticket ticket = new Ticket();
      ticket.setTicketPrice(5.99);
      ticket.setID(j);

      ShowTimes selectedShowTime = movie.getShowtimes().get(showTimeIndex);
      ticket.setShowtime(selectedShowTime);

      int availableSeats = this.displayAvailableSeats(selectedShowTime);
      ticket.setSeatNumber(availableSeats);

      // Decrease the number of available seats
      selectedShowTime.decrementAvailableSeats(Integer.parseInt(ticketField.getText()));

      tickets.add(ticket);
      this.setMovies(movie);
      this.setShowTimes(selectedShowTime);
      this.setUsers(user);
      this.setTickets(tickets);
      ReservedTicketWindow x = new ReservedTicketWindow();
      x.setI(ticket.getID());
      x.setSelectedShowTimeIndex(selectedShowTimeIndex);
      
      JOptionPane.showMessageDialog(reserveButton, "Ticket's ID: " + ticket.getID(), "Ticket ID",
          JOptionPane.INFORMATION_MESSAGE);
          
    } else {
      JOptionPane.showMessageDialog(cashButton, "Choose a payment method", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  public String toString(int i, int k) {
    return "ID: " + tickets.get(i).getID() + "\nSeat number: " + tickets.get(i).getSeatNumber() + "\nTicket price: "
        + tickets.get(i).getTicketPrice() + "\nMovie: " + movie.getTitle() + "\nShowTime: "
        + movie.printShowTime(movie, k);
  }

  public void reservationCancellation(int ticketId) {
    boolean found = false;
    // Use an Iterator to safely remove items from the list
    Iterator<Ticket> iterator = tickets.iterator();
    while (iterator.hasNext()) {
      Ticket ticket = iterator.next();
      if (ticketId == ticket.getID()) {
        iterator.remove(); // Remove the ticket using Iterator's remove method
        found = true;
        JOptionPane.showMessageDialog(null, "Ticket with ID " + ticketId + " has been cancelled.", "Cancel reservation",
            JOptionPane.INFORMATION_MESSAGE);
        break; // Exit the loop as we have found and removed the ticket
      }
    }
    if (!found) {
      JOptionPane.showMessageDialog(null, "Ticket with ID " + ticketId + " not found.", "Ticket not found",
          JOptionPane.ERROR_MESSAGE);
    }
  }

}
