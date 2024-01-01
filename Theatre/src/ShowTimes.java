import javax.swing.JOptionPane;

public class ShowTimes {
    private String showTime;
    private int availableSeats;

    public ShowTimes() {
    }

    public ShowTimes(String showTime, int availableSeats) {
        this.showTime = showTime;
        this.availableSeats = availableSeats;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void decrementAvailableSeats(int x) {
        if (this.availableSeats > 0) {
            this.availableSeats-=x;
        } else if (availableSeats <= 0) {
            JOptionPane.showMessageDialog(null, "There is no avialable seats in this show", "No avialable seats",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
