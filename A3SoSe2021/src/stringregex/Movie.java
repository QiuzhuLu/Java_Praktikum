package stringregex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Movie{

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return  Objects.equals(title, movie.title) &&
                Objects.equals(year, movie.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, title);
    }

    public String getTitle() {
        return title;
    }

    public List<Actor> getActors() {
        List<Actor> copyOfActorsList = new ArrayList<>();
        actors.forEach(actor -> {
            try {
                copyOfActorsList.add(actor.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        return copyOfActorsList;
        //---------Lösung2----------------
        //        List<Actor> list = new ArrayList<>();
//        Iterator<Actor> iterator = actors.iterator();
//        while (iterator.hasNext()){
//            try {
//                list.add(iterator.next().clone());    //???
//            } catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
    }

    public Year getYear() {
        return  new Year(year.getName()); //year; ///?????????
    }

    public String toShortString(){
        return String.format("%s, (%s)", title, year); //Suspicion (1941),
    }

//
}
