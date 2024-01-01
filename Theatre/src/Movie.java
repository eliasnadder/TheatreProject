import java.util.List;

public class Movie {
  // Attributes
  private int movieID;
  private String title;
  private String genre;
  private List<ShowTimes> showtimes;

  // Constructor
  public Movie(int movieID, String title, String genre, List<ShowTimes> showtimes) {
    this.movieID = movieID;
    this.title = title;
    this.genre = genre;
    this.showtimes = showtimes;
  }

  // Getters and setters
  public int getMovieID() {
    return movieID;
  }

  public void setMovieID(int movieID) {
    this.movieID = movieID;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public List<ShowTimes> getShowtimes() {
    return showtimes;
  }

  public void setShowtimes(List<ShowTimes> showtimes) {
    this.showtimes = showtimes;
  }

  public String printShowTime(Movie movie,int i){
    return movie.showtimes.get(i).getShowTime();
  }
  public int printSeats(Movie movie,int i){
    return movie.showtimes.get(i).getAvailableSeats();
  }
}