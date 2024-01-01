import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReservedTicketWindow extends Thread {
    JFrame frame;
    JLabel label;
    JLabel[] ticketLabel;
    User user;
    JPanel panel;
    Ticketing ticketing;
    Ticket ticket;
    int i, selectedShowTimeIndex;

    ReservedTicketWindow() {
    }

    ReservedTicketWindow(User user, Ticketing ticketing, Ticket ticket) {
        frame = new JFrame();
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Set the default close operation to
        // set the layout of the frame
        frame.setLayout(new FlowLayout());

        this.ticket = ticket;
        this.ticketing = ticketing;
        panel = new JPanel();
        panel.setLayout(new GridLayout(100, 1));
        frame.setTitle("Reserved tickets");
        if (ticketing.getTickets() == null) {
            label = new JLabel("No reserved tickets");
            panel.add(label);
        } else {
            ticketLabel = new JLabel[ticketing.getTickets().size()];
            for (int i = 0; i < ticketLabel.length; i++) {
                ticketLabel[i] = new JLabel(ticketing.toString(i, selectedShowTimeIndex));
                panel.add(ticketLabel[i]);
            }
        }
        JPanel[] ticketPanel = new JPanel[ticketing.getTickets().size()];
        for (int i = 0; i < ticketPanel.length; i++) {
            ticketPanel[i] = new JPanel();
            ticketPanel[i].setLayout(new GridLayout(5, 1));
            ticketPanel[i].setBorder(BorderFactory.createTitledBorder("Ticket " + (i + 1)));
            JLabel IDLabel = new JLabel("ID: " + String.valueOf(ticketing.getTickets().get(i).getID()));
            ticketPanel[i].add(IDLabel);
            JLabel movieTitlLabel = new JLabel("Movie title: " + ticketing.getMovies().getTitle());
            ticketPanel[i].add(movieTitlLabel);
            JLabel seatNumLabel = new JLabel("Seat number: " +
                    String.valueOf(ticketing.getTickets().get(i).getSeatNumber()));
            ticketPanel[i].add(seatNumLabel);
            JLabel ticektPriceLabel = new JLabel("Ticket price: " +
                    String.valueOf(ticketing.getTickets().get(i).getTicketPrice()));
            ticketPanel[i].add(ticektPriceLabel);
            JLabel showTimeLabel = new JLabel(
                    "Showtime: " + ticketing.getMovies().printShowTime(ticketing.getMovies(), selectedShowTimeIndex));
            ticketPanel[i].add(showTimeLabel);
            frame.add(ticketPanel[i]);
        }

        // frame.add(panel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Handle the window closing event
                frame.setVisible(false); // Hide the second window
            }
        });
        frame.setVisible(true);
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setSelectedShowTimeIndex(int selectedShowTimeIndex) {
        this.selectedShowTimeIndex = selectedShowTimeIndex;
    }

}
