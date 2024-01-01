import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreditWindow implements ActionListener {
    JFrame frame;
    JLabel label;
    JTextField accountNum;
    JButton submit;
    User user;
    JPanel panel;

    CreditWindow(User user) {
        frame=new JFrame();
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Set the default close operation to
        // set the layout of the frame
        frame.setLayout(new FlowLayout());
        this.user=user;
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        frame.setTitle("Account Number");
        label = new JLabel("Enter your account number");
        accountNum = new JTextField();
        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setFocusable(false);
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
        if (e.getSource() == submit) {
            if (accountNum != null) {
                AccountDetails accountDetails = new AccountDetails();
                accountDetails.setAccountNum(accountNum.getText());
                user.setAccountDetails(accountDetails);
                frame.dispose();
                
            }else{
                JOptionPane.showMessageDialog(null, "Please fill your account number", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

}
