import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame implements ActionListener {
  JButton[] reserveButton = new JButton[9];
  JButton[] infoButton = new JButton[9];
  ImageIcon[] icon = new ImageIcon[9];
  String[] title;
  Movie[] movies;
  private JLabel nameLabel, usernameLabel, emailLabel;
  private JTextField nameField, usernameField, emailField;
  private JButton signInButton, reservedTickets, cancelReservation;
  User user;
  AccountDetails accountDetails;
  Cinema[] cinema;
  Ticket ticket;
  List<Ticket> tickets;
  Ticketing ticketing;

  MyFrame(Movie[] movies, String[] title, Cinema[] cinema) {
    this.setSize(1080, 1920);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.cinema = cinema;
    this.movies = movies;
    this.title = title;

    // main panels
    JPanel moviPanel = new JPanel();
    JPanel sideBar = new JPanel();

    // Movie panel
    moviPanel.setPreferredSize(new Dimension(800, 800));
    moviPanel.setLayout(new BorderLayout()); // Change the layout to BorderLayout
    JLabel movietitle = new JLabel("Movies", SwingConstants.CENTER);
    movietitle.setFont(new Font("Arial", Font.BOLD, 45));
    moviPanel.add(movietitle, BorderLayout.NORTH); // Add the title to the NORTH position

    // Create a new panel for the movies
    JPanel moviesPanel = new JPanel();
    moviesPanel.setLayout(new GridLayout(3, 3, 10, 10));

    for (int i = 0; i < title.length; i++) {
      /* make a panel for icon, title, and buttons */
      JPanel panel = new JPanel();
      panel.setLayout(new BorderLayout());

      JLabel label = new JLabel();
      // Add icon
      icon[i] = new ImageIcon("E:\\Theatre\\photo\\movie" + (i + 1) + ".jpg");
      Image image = icon[i].getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
      icon[i] = new ImageIcon(image);
      label.setIcon(icon[i]);
      label.setHorizontalAlignment(JLabel.CENTER);
      label.setVerticalAlignment(JLabel.CENTER);
      panel.add(label, BorderLayout.CENTER);

      // Adjust the size of the label to fit the icon
      Dimension labelSize = new Dimension(icon[i].getIconWidth(), icon[i].getIconHeight());
      label.setPreferredSize(labelSize);
      label.setMinimumSize(labelSize);
      label.setMaximumSize(labelSize);

      JPanel contentPanel = new JPanel();
      contentPanel.setLayout(new BorderLayout()); // Create a new panel for the content

      JLabel titleLabel = new JLabel(title[i % title.length], SwingConstants.CENTER);
      contentPanel.add(titleLabel, BorderLayout.NORTH); // Move the titleLabel to the NORTH position

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Change the layout to FlowLayout with
                                                                        // center alignment
      reserveButton[i] = new JButton("Reserve");
      infoButton[i] = new JButton("Info");
      reserveButton[i].addActionListener(this);
      infoButton[i].addActionListener(this);
      reserveButton[i].setFocusable(false);
      infoButton[i].setFocusable(false);
      buttonPanel.add(reserveButton[i]);
      buttonPanel.add(infoButton[i]);
      contentPanel.add(buttonPanel, BorderLayout.CENTER); // Move the buttonPanel to the CENTER position
      panel.add(contentPanel, BorderLayout.SOUTH); // Move the contentPanel to the SOUTH position
      moviesPanel.add(panel);
    }
    // Create a scroll panel
    JScrollPane scrollPane = new JScrollPane(moviesPanel);
    moviPanel.add(scrollPane, BorderLayout.CENTER);

    // Create a panel for the sidebar
    sideBar.setLayout(new FlowLayout());
    sideBar.setPreferredSize(new Dimension(280, 800));

    // Create a label for the sidebar title
    JLabel sideBarTitle = new JLabel("SideBar");
    sideBarTitle.setFont(new Font("Arial", Font.BOLD, 24));
    sideBarTitle.setHorizontalAlignment(SwingConstants.CENTER);

    // Create a label for the account text
    JLabel account = new JLabel("Account");
    account.setFont(new Font("Arial", Font.PLAIN, 18));
    account.setHorizontalAlignment(SwingConstants.CENTER);
    account.setVerticalAlignment(SwingConstants.CENTER);
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2, 1));
    panel.add(sideBarTitle);
    panel.add(account);

    // Initializing the components
    nameLabel = new JLabel("Name:");
    usernameLabel = new JLabel("Username:");
    emailLabel = new JLabel("Email:");
    nameField = new JTextField(5);
    usernameField = new JTextField(5);
    emailField = new JTextField(5);
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1, 1));
    signInButton = new JButton("Sign In");
    buttonPanel.add(signInButton);
    signInButton.setPreferredSize(new Dimension(100, signInButton.getPreferredSize().height));
    signInButton.setFocusable(false);

    // Create a panel for the labels and buttons
    JPanel labelsAndButtons = new JPanel();
    labelsAndButtons.setLayout(new GridLayout(4, 1));

    // Add the labels to the labelsAndButtons panel
    labelsAndButtons.add(nameLabel);
    labelsAndButtons.add(nameField);
    labelsAndButtons.add(usernameLabel);
    labelsAndButtons.add(usernameField);
    labelsAndButtons.add(emailLabel);
    labelsAndButtons.add(emailField);

    // Add a vertical space between the labels and buttons
    labelsAndButtons.add(Box.createVerticalStrut(10));

    // Add the sign in button to the labelsAndButtons panel
    labelsAndButtons.add(buttonPanel);

    // Adding an action listener to the sign in button
    signInButton.addActionListener(this);

    // Create a panel for the buttons
    JPanel buttons = new JPanel();
    buttons.setLayout(new GridLayout(2, 1));

    // Create a button for the reserved tickets
    reservedTickets = new JButton("Reserved Tickets");
    reservedTickets.setFont(new Font("Arial", Font.PLAIN, 16));
    reservedTickets.setFocusable(false);
    reservedTickets.addActionListener(this);

    // Create a button for the cancel reservation
    cancelReservation = new JButton("Cancel Reservation");
    cancelReservation.setFont(new Font("Arial", Font.PLAIN, 16));
    cancelReservation.setFocusable(false);
    cancelReservation.addActionListener(this);

    // Add the buttons to the buttons panel
    buttons.add(reservedTickets);
    buttons.add(cancelReservation);

    // Add some vertical space between the components
    sideBar.add(Box.createRigidArea(new Dimension(0, 10)));

    // Add the title, account, labels, and buttons to the sidebar panel
    sideBar.add(panel);

    // Add the labelsAndButtons panel to the sidebar panel
    sideBar.add(labelsAndButtons);
    sideBar.add(buttons);

    // Add an empty spacer panel to the EAST position of moviPanel
    JPanel spacerPanel = new JPanel();
    spacerPanel.setPreferredSize(new Dimension(10, 800)); // Adjust the preferred width as needed
    moviPanel.add(spacerPanel, BorderLayout.EAST);
    this.add(moviPanel, BorderLayout.CENTER);
    this.add(sideBar, BorderLayout.EAST);
    this.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Add actionListener for signButton
    if (e.getSource() == signInButton) {
      // Getting the text from the text fields
      user = new User();
      accountDetails = new AccountDetails();
      accountDetails.setEmail(emailField.getText());
      accountDetails.setName(nameField.getText());
      user.setUsername(usernameField.getText());
      user.setAccountDetails(accountDetails);
      ticketing = new Ticketing();
      tickets = new ArrayList<>();

      // Checking if the text fields are not empty
      if (!nameField.getText().isEmpty() && !usernameField.getText().isEmpty() && !emailField.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null,
            "You entered:\nName: " + nameField.getText() + "\nUsername: " + usernameField.getText() + "\nEmail: "
                + emailField.getText());
      } else if (nameField.getText().isEmpty() && usernameField.getText().isEmpty() && emailField.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill all the fields", "Error", JOptionPane.WARNING_MESSAGE);
      }
    }

    // Add actionListener for reserveButton
    int minLength = Math.min(reserveButton.length, movies.length);
    for (int i = 0; i < minLength; i++) {
      if (e.getSource() == reserveButton[i]) {
        if (user != null) {
          ReserveWindow newWindow = new ReserveWindow(movies[i], "E:\\\\Theatre\\\\photo\\\\movie" + (i + 1) + ".jpg",
              user, i, tickets, ticketing);
          newWindow.start();
        } else {
          JOptionPane.showMessageDialog(null, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    }

    // Add actionListener for infoButton
    for (int i = 0; i < 9; i++) {
      if (e.getSource() == infoButton[i]) {
        infoNewWindow newWindow = new infoNewWindow(movies[i], "E:\\\\Theatre\\\\photo\\\\movie" + (i + 1) + ".jpg",
            user, i, tickets, ticketing);
        newWindow.start();
      }
    }

    // Add actionListener for reservedTicket
    if (e.getSource() == reservedTickets && user == null) {
      JOptionPane.showMessageDialog(null, "Please sign in first!", "SignIn", JOptionPane.WARNING_MESSAGE);
    } else if (e.getSource() == reservedTickets && user != null) {
      ReservedTicketWindow newWindow = new ReservedTicketWindow(user, ticketing, ticket);
      newWindow.start();
    }

    // Add actionListener for cancelReservation
    if (e.getSource() == cancelReservation && user == null) {
      JOptionPane.showMessageDialog(null, "Please sign in first!", "Error", JOptionPane.WARNING_MESSAGE);
    } else if (e.getSource() == cancelReservation && user != null) {
      CancelReservationWindow newWindow = new CancelReservationWindow(ticketing);
      newWindow.start();
    }

  }
}