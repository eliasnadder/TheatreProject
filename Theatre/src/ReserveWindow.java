import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReserveWindow extends Thread implements ActionListener {
    private JFrame frame;
    private JLabel movieCover, movieTitle, movieInfo, ticketLabel, username, usernameLabel, cinemLabel, ticketPrice;
    private JTextField ticketField;
    private JButton reserveButton, cancelButton;
    private JRadioButton cashButton, creditCardButton;
    // private JCheckBox extractToFile;
    private JRadioButton[] showTimeButton;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JPanel bottomPanel, rightPanel;
    private User user;
    private Ticketing ticketing;
    private Movie movie;
    private List<Ticket> tickets;
    private int selectedShowTimeIndex = -1;
    private static int j;
    static int i = 0;

    ReserveWindow(Movie movie, String path, User user, int k,
            List<Ticket> tickets, Ticketing ticketing) {
        frame = new JFrame();
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // set the layout of the frame
        frame.setLayout(new BorderLayout());
        frame.setTitle(movie.getTitle());

        this.movie = movie;
        this.tickets = tickets;
        this.user = user;
        this.ticketing = ticketing;

        // Create an empty border with a 5-pixel margin
        EmptyBorder labelFieldMargin = new EmptyBorder(0, 0, 0, 5);

        // initialize the components
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(400, 600, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        movieCover = new JLabel(icon);

        movieTitle = new JLabel(movie.getTitle());
        movieInfo = new JLabel("Genre: " + movie.getGenre());
        if (k == 0 || k == 1 || k == 2) {
            cinemLabel = new JLabel("Hall: Hall-1");
        } else if (k == 3 || k == 4 || k == 5) {
            cinemLabel = new JLabel("Hall: Hall-2");
        } else if (k == 6 || k == 7 || k == 8) {
            cinemLabel = new JLabel("Hall: Hall-3");
        }
        ticketPrice = new JLabel("Ticket price: " + 5.99);

        // Set the font size for movieTitle to 36
        Font movieTitleFont = movieTitle.getFont();
        movieTitle.setFont(movieTitleFont.deriveFont(36f));

        // Set the font size for movieInfo to 24
        Font movieInfoFont = movieInfo.getFont();
        movieInfo.setFont(movieInfoFont.deriveFont(24f));

        Font cinemaFont = cinemLabel.getFont();
        cinemLabel.setFont(cinemaFont.deriveFont(24f));

        usernameLabel = new JLabel("Username: ");
        username = new JLabel(user.getUsername());
        ticketLabel = new JLabel("Tickets :");
        ticketLabel.setBorder(labelFieldMargin);
        ticketField = new JTextField("1");

        reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(this);
        reserveButton.setFocusable(false);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setFocusable(false);

        // JPanel extractPanel = new JPanel();
        // extractPanel.setLayout(new BorderLayout());
        // extractPanel.setBorder(BorderFactory.createTitledBorder("Extract to file"));
        // extractToFile = new JCheckBox("Extract to file");
        // extractToFile.addActionListener(this);
        // extractPanel.add(extractToFile);

        // create a panel for the right side of the frame
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 1)); // use a grid layout with 3 rows and 1 column
        rightPanel.add(movieTitle); // add the movie title to the first row
        rightPanel.add(movieInfo); // add the movie info to the second row
        rightPanel.add(cinemLabel);
        rightPanel.add(ticketPrice);

        // Create a panel for showTime
        ButtonGroup showTimeGroup = new ButtonGroup();
        JPanel showTimePanel = new JPanel();
        showTimeButton = new JRadioButton[3];
        showTimePanel.setLayout(new GridLayout(1, 3, 10, 5));
        for (int i = 0; i < 3; i++) {
            showTimeButton[i] = new JRadioButton(
                    movie.printShowTime(movie, i) + "(" + movie.getShowtimes().get(i).getAvailableSeats() + " seats)");
            showTimeButton[i].addActionListener(this);
            showTimeGroup.add(showTimeButton[i]);
            showTimePanel.add(showTimeButton[i]);
        }

        // Create a panel for option to pay
        JPanel payPanel = new JPanel();
        payPanel.setLayout(new GridLayout(1, 1));
        cashButton = new JRadioButton("Cash");
        creditCardButton = new JRadioButton("Credit Card");
        cashButton.addActionListener(this);
        creditCardButton.addActionListener(this);
        buttonGroup.add(cashButton);
        buttonGroup.add(creditCardButton);
        payPanel.add(cashButton);
        payPanel.add(creditCardButton);

        // Create an empty space
        JPanel empty = new JPanel();
        empty.setLayout(new GridLayout(1, 1));
        JPanel empty2 = new JPanel();
        empty2.setLayout(new GridLayout(1, 1));
        // JPanel empty3 = new JPanel();
        // empty3.setLayout(new GridLayout(1, 1));

        // Create a panel for the bottom of the frame
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(5, 2)); // use a flow layout
        bottomPanel.add(ticketLabel);
        bottomPanel.add(usernameLabel);
        bottomPanel.add(username);
        bottomPanel.add(ticketLabel); // add the ticket label
        bottomPanel.add(ticketField); // add the ticket field
        bottomPanel.add(showTimePanel);
        bottomPanel.add(empty);
        bottomPanel.add(payPanel);
        bottomPanel.add(empty2);
        // bottomPanel.add(extractPanel);
        // bottomPanel.add(empty3);
        bottomPanel.add(reserveButton);
        bottomPanel.add(cancelButton);

        // add the components to the frame
        frame.add(movieCover, BorderLayout.WEST); // add the movie cover to the left side of the frame
        frame.add(rightPanel, BorderLayout.CENTER); // add the right panel to the center of the frame
        frame.add(bottomPanel, BorderLayout.SOUTH); // add the bottom panel to the bottom of the frame

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Handle the window closing event
                frame.setVisible(false); // Hide the second window
            }
        });
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == cashButton) {
            double price = 5.99 * Double.parseDouble(ticketField.getText());
            JOptionPane.showMessageDialog(null, "You need to pay S.P. " + price, "Cash",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (source == creditCardButton) {
            new CreditWindow(user);
        } else {
            // Check if any showtime button was clicked
            for (int i = 0; i < showTimeButton.length; i++) {
                if (source == showTimeButton[i]) {
                    selectedShowTimeIndex = i;
                    return; // Exit the method after selecting a showtime
                }
            }

            // Handle reserve button click
            if (source == reserveButton) {
                if (selectedShowTimeIndex == -1) {
                    JOptionPane.showMessageDialog(reserveButton, "Choose showTime", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    ticketing.processTicketReservation(selectedShowTimeIndex, j, tickets, movie, user, ticketField,
                            selectedShowTimeIndex, cashButton, creditCardButton);
                    j++;
                    writeToStringFile(
                            "C:\\Users\\VISION\\Desktop\\output" + ticketing.getTickets().get(i).getID() + ".txt",
                            ticketing.getTickets().get(i).getID());
                    i++;
                    // Reset the selected showtime
                    selectedShowTimeIndex = -1;
                    frame.dispose();
                }
            }

        }

    }

    public void writeToStringFile(String filename, int i) {
        // Use try-with-resources for automatic resource management
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename,
                false))) {
            writer.write(ticketing.toString(i, selectedShowTimeIndex));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}