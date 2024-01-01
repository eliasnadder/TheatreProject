import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CancelReservationWindow extends Thread implements ActionListener{
    JFrame frame;
    JLabel label;
    JTextField accountNum;
    JButton submit;
    JPanel panel;
    Ticketing ticketing;
    CancelReservationWindow(Ticketing ticketing){
        frame = new JFrame();
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Set the default close operation to
        // set the layout of the frame
        frame.setLayout(new FlowLayout());

        this.ticketing=ticketing;
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        frame.setTitle("Canecl Reservation");
        label = new JLabel("Enter the number of ticket");
        accountNum = new JTextField();
        submit = new JButton("Submit");
        submit.addActionListener(this);
        panel.add(label);
        panel.add(accountNum);
        panel.add(submit);
        frame.add(panel);
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
        if (e.getSource()==submit) {
            ticketing.reservationCancellation(Integer.parseInt(accountNum.getText()));
            frame.dispose();
        }
    }
}
