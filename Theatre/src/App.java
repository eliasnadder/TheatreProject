import java.util.*;

public class App {
        public static void main(String[] args) {
                ShowTimes[] showTimes = new ShowTimes[27]; // 27 ShowTimes for 9 movies, each with 3 showtimes

                // Initialize showtimes with non-negative seat capacities
                for (int i = 0; i < 27; i++) {
                        String time = (i % 3 == 0) ? "9:00 A.M." : (i % 3 == 1) ? "1:00 P.M." : "7:45 P.M.";
                        int capacity = 100 - (i % 9) * 5; // Reducing capacity by 5 for each set of 9 showtimes to
                                                          // ensure it's non-negative
                        showTimes[i] = new ShowTimes(time, capacity);
                }

                List<ShowTimes> showTimesList = Arrays.asList(showTimes);

                Movie[] movies = new Movie[9];

                // Assign unique showtimes to each movie
                for (int i = 0; i < movies.length; i++) {
                        int startIndex = i * 3;
                        int endIndex = startIndex + 3;
                        List<ShowTimes> uniqueSubList = new ArrayList<>(showTimesList.subList(startIndex, endIndex));
                        movies[i] = new Movie(i, "Movie " + i, "Genre " + i, uniqueSubList);
                }

                // Set movie titles and genres
                movies[0].setTitle("The Da Vinci Code");
                movies[0].setGenre("Mystery, Thriller");
                movies[1].setTitle("Angels & Demons");
                movies[1].setGenre("Mystery, Thriller, Action");
                movies[2].setTitle("Avatar: The Way of Water");
                movies[2].setGenre("Action, Adventure, Fantasy");
                movies[3].setTitle("For All Mankind");
                movies[3].setGenre("Documentary, History");
                movies[4].setTitle("Barbie");
                movies[4].setGenre("Adventure, Fantasy, Comedy");
                movies[5].setTitle("Swimmers");
                movies[5].setGenre("Biography, Drama, Sport");
                movies[6].setTitle("Fall");
                movies[6].setGenre("Action, Thriller");
                movies[7].setTitle("Zone 414");
                movies[7].setGenre("Adventure, Crime, Drama");
                movies[8].setTitle("The Maze Runner");
                movies[8].setGenre("Action, Mystery, Sci-Fi");

                // Cinemas
                Cinema[] cinemas = new Cinema[3];
                cinemas[0] = new Cinema("Hall-1", Arrays.asList(movies[0], movies[1], movies[2]));
                cinemas[1] = new Cinema("Hall-2", Arrays.asList(movies[3], movies[4], movies[5]));
                cinemas[2] = new Cinema("Hall-3", Arrays.asList(movies[6], movies[7], movies[8]));

                String[] title = new String[9];
                for (int i = 0; i < title.length; i++) {
                        title[i] = movies[i].getTitle();
                }
                new MyFrame(movies, title, cinemas);
        }
}
