package stringregex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieReader {

    private static String movies = "movies.txt";

    public static void main(String[] args) throws FileNotFoundException {
        String pathToMovies = System.getProperty("java.class.path").split(File.pathSeparator)[0];
        String filename = pathToMovies + File.separator + movies;
        List<Movie> movies = readMovies(filename);
        System.out.println("NUM of Movies:" + movies.size());
        int ct = 1;
        for (Movie m : movies) System.out.println(ct++ + " " + m);

    }

    private static Pattern moviePattern = Pattern.compile("(?<titel>\\s*(.+?))"
            + "\\((?<jahr>\\d{4})" +
            "(?:\\s[^\\/]*)*\\)" + "(?<actors>(.*))");

    private static Pattern actorPattern = Pattern.compile("\\/(?<lastname>\\p{L}+)" +
            "(?:\\s*,\\s*(?<firstname>[\\p{L}\\-]+))");
    // \p{L} : alphanumerisches Zeichen aus jeder Sprache (Unicode) //https://regexr.com/38rdr
    //  \\w  :  A word character: [a-zA-Z_0-9]

    public static List<Movie> readMovies(String filename) throws FileNotFoundException {
        List<Movie> movieList = new ArrayList<>();
        Scanner sc = new Scanner(new File(filename));
        Matcher movMatch;
        Matcher actMatch;
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            movMatch = moviePattern.matcher(line);
            while (movMatch.find()){
                Movie movie = new Movie(new Year(movMatch.group("jahr")), movMatch.group("titel"));
                actMatch = actorPattern.matcher(movMatch.group("actors"));
                while (actMatch.find()){
                    Actor actor = new Actor(actMatch.group("firstname") == null ? "" : actMatch.group("firstname"),
                            (actMatch.group("lastname")) );
                    movie.add(actor);
                }
                movieList.add(movie);
            }
        }
        sc.close();
        return movieList;
        //private static void addActor(String Ac)

/*        while (sc.hasNextLine()) { // && sc.skip(EMPTY_LINE).hasNextLine
            String line = sc.nextLine();
            movMatch = moviePattern.matcher(line);
            while (movMatch.find()) {
                Movie foundMov = new Movie(new Year(movMatch.group(2)), movMatch.group(1));
                actMatch = actorPattern.matcher(movMatch.group(3));
                while (actMatch.find()) {
                    String name = actMatch.group(1);
                    String firstName = "";
                    if(actMatch.group(2) != null){
                        firstName = actMatch.group(2);
                    }
                    foundMov.add(new Actor(firstName, name));
                }
                movieList.add(foundMov);
            }
        }
        sc.close();
        return movieList;*/

    }

}
