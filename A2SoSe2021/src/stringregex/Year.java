package stringregex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Year {

    private String name;
    // TODO private final String yearPattern;
    private static final String yearPattern  = "^(19|20)(\\d{2})$" ;
    public Year(String name){
        Pattern pattern = Pattern.compile(yearPattern);
        Matcher matcher = pattern.matcher(name);
        if (!matcher.find()){
            throw new IllegalArgumentException(String.format("%s 4 digits starting with 19|20 are expected", name));
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
//https://stackoverflow.com/questions/4374185/regular-expression-match-to-test-for-a-valid-year
  /*  public static void main(String[] args) {
        Year year = new Year ("1922");
        System.out.println(year);
    }*/
}
