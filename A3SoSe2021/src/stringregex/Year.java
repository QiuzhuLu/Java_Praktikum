package stringregex;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Year implements Comparable<Year> {

    private String name;
    //private final String yearPattern = "(20|19)\\d{2}";
    private final Pattern yearPattern = Pattern.compile("(20|19)\\d{2}"); // wenn mit matches arbeiten
    //   private final Pattern yearPattern = Pattern.compile("^(20|19)\\d{2}$");  falls Zeile 16 mit find arbeiten
    private Matcher mat;
    public Year(String name) {
        mat = yearPattern.matcher(name);
        if (!mat.matches()) { //find?(nächste find)   //matches gnaz
            throw new IllegalArgumentException(String.format("%s 4 digits starting with 19|20 are expected", name));
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Year year = (Year) o;
        return Objects.equals(name, year.name);
    }

    @Override
    public int hashCode() {return Objects.hash(name);}

    public String getName() {
        return name; //java String class is immutable.
    }

    @Override
    public int compareTo(Year o) {
        return name.compareTo(o.getName());
    }
}
//https://stackoverflow.com/questions/4450045/difference-between-matches-and-find-in-java-regex