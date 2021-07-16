package stringregex;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String title;
    private List<Actor> actors;
    private Year year;

    public Movie(Year year, String title){
        this.year=year;
        this.title=title;
        actors = new ArrayList<>();
    }

    public void add(Actor actor){
        actors.add(actor);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) actors:%s",title,year,actors);
    }
}
