import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class infoNewWindow extends Thread implements ActionListener {
    JFrame frame;
    JButton button;
    JLabel movieCover, movieTitle, movieInfo, cinemLabel, ticketPrice, showTimeLabel;
    JLabel[] numOfChairs = new JLabel[3];
    JButton reserveButton;
    Movie movie;
    String path;
    User user;
    int k;
    List<Ticket> tickets;
    Ticketing ticketing;

    infoNewWindow(Movie movie, String path, User user, int k, List<Ticket> tickets,
            Ticketing ticketing) {
        // Create a frame
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // initialize the components
        this.movie = movie;
        this.path = path;
        this.user = user;
        this.k = k;
        this.tickets = tickets;
        this.ticketing = ticketing;
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(400, 600, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        movieCover = new JLabel(icon);
        movieTitle = new JLabel(movie.getTitle());
        movieTitle.setLayout(new BorderLayout());
        movieInfo = new JLabel(movie.getGenre());
        showTimeLabel = new JLabel("ShowTimes:");
        if (k == 0 || k == 1 || k == 2) {
            cinemLabel = new JLabel("Hall: Hall-1");
        } else if (k == 3 || k == 4 || k == 5) {
            cinemLabel = new JLabel("Hall: Hall-2");
        } else if (k == 6 || k == 7 || k == 8) {
            cinemLabel = new JLabel("Hall: Hall-3");
        }
        for (int i = 0; i < 3; i++) {
            numOfChairs[i] = new JLabel(
                    movie.printShowTime(movie, i) + "(" + movie.getShowtimes().get(i).getAvailableSeats() + " seats)");
        }

        ticketPrice = new JLabel("Ticket price: " + 5.99);

        // Apply an empty border to movieTitle
        EmptyBorder movieTitleMargin = new EmptyBorder(0, 0, 5, 0);
        movieTitle.setBorder(movieTitleMargin);

        // Set the font size for movieTitle to 36
        Font movieTitleFont = movieTitle.getFont();
        movieTitle.setFont(movieTitleFont.deriveFont(32f));

        // Set the font size for movieInfo to 24
        Font movieInfoFont = movieInfo.getFont();
        movieInfo.setFont(movieInfoFont.deriveFont(26f));

        // Set the font size for cinemLabel to 24
        Font cinemaFont = cinemLabel.getFont();
        cinemLabel.setFont(cinemaFont.deriveFont(26f));

        // Set the font size for showTimeLabel to 24
        Font showTimeFont = showTimeLabel.getFont();
        showTimeLabel.setFont(showTimeFont.deriveFont(26f));

        // Set the font size for numOfChairs to 24
        for (int i = 0; i < numOfChairs.length; i++) {
            Font numOfChairsFont = numOfChairs[i].getFont();
            numOfChairs[i].setFont(numOfChairsFont.deriveFont(16f));
        }
        reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(this);
        reserveButton.setFocusable(false);

        // create a panel for the right side of the frame
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(9, 1));
        rightPanel.add(movieTitle); // add the movie title to the first row
        rightPanel.add(movieInfo); // add the movie info to the second row
        rightPanel.add(cinemLabel);
        rightPanel.add(showTimeLabel);
        for (int i = 0; i < numOfChairs.length; i++) {
            rightPanel.add(numOfChairs[i]);
        }
        rightPanel.add(ticketPrice);

        // add the reserve button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());
        buttonPanel.add(reserveButton);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        // add the components to the frame
        frame.add(movieCover, BorderLayout.WEST); // add the movie cover to the left side of the frame
        frame.add(rightPanel, BorderLayout.CENTER); // add the right panel to the center of the frame

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
        if (e.getSource() == reserveButton && user != null) {
            new ReserveWindow(movie, path, user, k, tickets, ticketing);
            frame.dispose();
        } else if (e.getSource() == reserveButton && user == null) {
            JOptionPane.showMessageDialog(null, "Please sign in first!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}