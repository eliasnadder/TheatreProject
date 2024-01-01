import java.util.List;

public class Cinema {
  // Attributes
  private String hallName;
  private List<Movie> movies;

  // Constructor
  public Cinema(String hallName, List<Movie> movies) {
    this.hallName = hallName;
    this.movies = movies;
  }

  // Getters and setters
  public String getHallName() {
    return hallName;
  }

  public void setHallName(String hallName) {
    this.hallName = hallName;
  }

  public List<Movie> getMovies() {
    return movies;
  }

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
  }
}
