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
        for(Movie m: movies) System.out.println(ct++ +" " + m);

    }

    private static Pattern moviePattern;
    private static Pattern actorPattern;
/*'Breaker' Morant (1980)*/
    public static List<Movie> readMovies(String filename) throws FileNotFoundException {
        List<Movie> movieList = new ArrayList<>();
        Scanner sc = new Scanner(new File(filename));
        //moviePattern = moviePattern.compile("(.+?)\\(((?:19|20)\\d{2})[^\\)]*\\)(.+)");   // .+? lazy matching; ?: non-capturing group
        //actorPattern = actorPattern.compile("\\/\\s*([^/,(]+)(?:[\\s\\(\\)I)V]*)(?:,([^/(]*))?");

        moviePattern = moviePattern.compile("(.*)\\((\\d{4})\s*I{0,3}\\)(.*)");  // (1999 I) Zeile 2469, leere Zeichen und I weglassen
        actorPattern = actorPattern.compile("\\/([\\p{L}\\s,.-]+)\\(?I{0,3}\\)?"); //Anfang / weglassen, falls Ende (I) ,(II) oder (III), weglassen,
        // \p{L} : alphanumerisches Zeichen aus jeder Sprache (Unicode) //https://regexr.com/38rdr   // Leerzeichen //s: [ \t\n\x0B\f\r]    V6 Seite12
        //actorPattern = actorPattern.compile("\\/([\\w\\s,.-]+)\\(?I{0,3}\\)?", Pattern.UNICODE_CHARACTER_CLASS);  // identisch wie obere Zeile //https://stackoverflow.com/questions/10894122/java-regex-for-support-unicode
        //alphanumerisches Zeichen \\w : [a-zA-Z0-9]. With Pattern.UNICODE_CHARACTER_CLASS , \w would match all letters and all digits from any languages
        // TODO
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            Matcher matcher = moviePattern.matcher(line);
            while (matcher.find()){
                Movie movie = new Movie(new Year(matcher.group(2)), matcher.group(1)); // group 2 ist Jahr , group 1 ist titel
                String movieActors = matcher.group(3);
                Matcher matcherActor = actorPattern.matcher(movieActors);
                while (matcherActor.find()){
                    String movieActor =  matcherActor.group(1);
                    String[] name = movieActor.split("\\s*,\\s*");
                    Actor actor = null;
                    if (name.length==2){
                        actor  = new Actor(name[1], name[0]);
                    }else if(name.length==1){
                        actor = new Actor("", name[0]);
                    }
                    if(actor != null){
                        movie.add(actor);
                    }
                }
                movieList.add(movie);
            }
        }
        sc.close();
        return movieList;
    }
}


