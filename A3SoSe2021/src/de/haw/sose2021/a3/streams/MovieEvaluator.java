package de.hawhh.sose2021.a3.streams;

// TODO Entweder die Lösung der Aufgabe 2 als Modul einbinden, oder die Sourcen
//  aus Aufgabe 2 in dieses Projekt kopieren, um die import Fehler zu beseitigen.
import stringregex.Actor;
import stringregex.Movie;
import stringregex.MovieReader;
import stringregex.Year;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MovieEvaluator {

    public static void main(String[] args) throws FileNotFoundException {
        // TODO Sukzessive mit Teillösungen auskommentieren.
        MovieEvaluator me = new MovieEvaluator("movies.txt");
        // A.3.2.1
        System.out.println("A.3.2.1");
        List<Movie> meSorted = me.sortByTitleThenNumberOfActors();
        // A.3.2.3
        System.out.println("A.3.2.3");
        MovieEvaluator.print(meSorted,0,10);
        //me.print(100,10);
//        // A.3.2.4
        System.out.println("A.3.2.4");
        List<String> shortList = me.mapToShortList();
        MovieEvaluator.print(shortList,110,5);
//        // A.3.2.5
       System.out.println("A.3.2.5");
        System.out.println(me.movies.size());
        MovieEvaluator.print(me.movies, 0, 70);
       int tIndex = me.firstIndexOfMovieWith("T");
       MovieEvaluator.print(MovieEvaluator.mapToShortList(meSorted),tIndex-1,2);
//        // A.3.2.6
        System.out.println("A.3.2.6");
        int tLIndex = me.lastIndexOfMovieWith("T");
        MovieEvaluator.print(MovieEvaluator.mapToShortList(meSorted),tLIndex,2);
//        // A.3.2.7
        System.out.println("A.3.2.7");
        Set<Year> years = me.years();
        MovieEvaluator.print(new ArrayList<>(years),0,2);
//        // A.3.2.8
        System.out.println("A.3.2.8");
        Set<Actor> actors = me.actors();
        //MovieEvaluator.print(new ArrayList<>(actors));
        //System.out.println(actors.size());
        MovieEvaluator.print(new ArrayList<>(actors),1000,40);
//        // A.3.2.9 und A.3.2.2
        System.out.println("A.3.2.9");
        SortedMap<Year,List<String>> yearMovieMapShort = me.inYearMovieMapShort();
        MovieEvaluator.print(yearMovieMapShort,0,3);
        SortedMap<Year,List<Movie>> yearMovieMap = me.inYearMovieMap();
        MovieEvaluator.print(yearMovieMap,0,3);
//        // A.3.2.10
        System.out.println("A.3.2.10");
        Year year = me.yearOfMovies();
        System.out.println(year);
//        // A.3.2.11
        System.out.println("A.3.2.11");
        SortedMap<Actor,List<Movie>> actorMovieMap = me.inActorMovieMap();
        MovieEvaluator.print(actorMovieMap,100,3);
        SortedMap<Actor,List<String>> actorMovieMapShort = me.inActorMovieMapShort();
        MovieEvaluator.print(actorMovieMapShort,100,3);
//        // A.3.2.12
        System.out.println("A.3.2.12");
        Actor actor = me.favourite();
        System.out.println(actor);
        System.out.println(actorMovieMapShort.get(actor).size());
        //MovieEvaluator.print(me.inActorMovieMapShort(),0, me.inActorMovieMapShort().size());
//        // A.3.2.13
        System.out.println("A.3.2.13");
        System.out.println(me.anyWithMoreFilmsThen(100));
        System.out.println(me.anyWithMoreFilmsThen(10));
//        // A.3.2.14
        System.out.println("A.3.2.14");
       List<Map.Entry<Actor,List<Movie>>> actorMoviesSorted = me.sortByNameThenNumberOfFilms();
        MovieEvaluator.print(actorMoviesSorted,10,4);
        List<Map.Entry<Actor,List<String>>> actorMoviesSortedShort = me.sortByNameThenNumberOfFilmsShort();
        MovieEvaluator.print(actorMoviesSortedShort,10,4);
    }

    private List<Movie> movies;

    private int n=0;

    public MovieEvaluator(String filename) throws FileNotFoundException {
        movies = MovieReader.readMovies(getFileName(filename));
        n = movies.size();
       // das bitte auskommentieren, wenn Methode sortByTitleThenNumberOfActors korrekt implementiert ist.
        movies = sortByTitleThenNumberOfActors();

    }

    public int size(){ return n; }

    /**
     * Gegeben. Erzeugt den absoluten Dateinamen für eine Datei mit Name "relName" und liest den
     * Präfix aus dem ersten Pfad des CLASSPATH.
     *
     * @param relName Name der Datei.
     * @return absoluter Dateiname.
     */
    private static String getFileName(String relName) {
        String[] paths = System.getProperty("java.class.path").split(File.pathSeparator);
        return paths[0] + File.separator + relName;
    }

    /**
     * A.3.2.1
     * TODO
     * Sortiert die Liste der movies zuerst nach Titel und dann nach Anzahl der Schauspieler/Schauspielerinnen
     *
     * @return Sortierte Liste von Filmen
     */
    public List<Movie> sortByTitleThenNumberOfActors() {
        return movies.stream().sorted(Comparator.comparing((Movie m)-> m.getTitle().toLowerCase()).thenComparingInt(e -> e.getActors().size()))
                .collect(Collectors.toList());
    }
    // max(Comparator.comparing())
    /**
     * A.3.2.2
     * TODO
     * Druckt den Inhalte einer SortedMap im Bereich [from, howMany) elementweise.
     * Dabei steht jedes key-value Paar getrennt durch "=>" in einer separaten Zeile
     *
     * Beispiel-Ausgabe: Wenn der Methode eine SortedMap<Actor,List<String>> mit from = 100 und howMany = 3 übergeben wird
     * Bernard Abbou => [Quatre cents coups, Les (1959)]
     * Damien Abbou => [Sous le sable (2000)]
     * Patrice Abbou => [Taxi 3 (2003)]
     *
     * @param m       Map mit unbekanntem Typ für Key und Value.
     * @param from    erstes Element, das ausgegeben wird. Das i'te Element eines Stream.
     * @param howMany Anzahl der Elemente, die ausgegeben werden.
     */
    public static void print(SortedMap<?, ?> m, int from, int howMany) {
        m.entrySet().stream().skip(from).limit(howMany).map(entry -> entry.getKey() + " => " + entry.getValue()).forEach(System.out::println);
        m.entrySet();
        //--------Lösung2------------
//        Set<? extends Map.Entry<?, ?>> entrySet = m.entrySet();
//        Map.Entry<?,?>[] entyArray = entrySet.toArray(new Map.Entry[entrySet.size()]);
//        for(int i=from; i<from+howMany && from >= 0 && i<m.size(); i++){
//            System.out.println(entyArray[i].getKey() + "=>" + entyArray[i].getValue());
//        }
    }
    //https://www.geeksforgeeks.org/how-to-get-treemap-key-or-value-using-index-in-java/
    //https://javahungry.blogspot.com/2015/11/difference-between-hashmap-and-treemap-with-example.html
    /**
     * A.3.2.3
     * TODO
     * Gibt die Elemente einer Liste im Bereich [from,from+howMany) in der Form
     * <Position>:<Element> aus. Jedes Element steht in einer separaten Zeile.
     *
     * @param l       eine Liste mit unbekanntem Elementtyp
     * @param from    Startindex für die Ausgabe
     * @param howMany Anzahl der Elemente, die ausgegeben werden sollen.
     */
    public static void print(List<?> l, int from, int howMany) {
        AtomicInteger index = new AtomicInteger(from);
        l.stream().skip(from).limit(howMany).map(elem -> index.getAndIncrement() + " : " + elem).
                forEach(System.out::println);
        //-------Lösung2-------- //spalist
//        for(int index=from; index < from+howMany && from>=0 && index < l.size(); index++){
//                System.out.println( (index) + " :  " + l.get(index) + "\n"); //"Position " +
//        }
        //------Lösung3----------
//        Iterator<?> iterator = l.iterator();
//        for(int index=0; index+1 < from+howMany && iterator.hasNext(); index++){
//            if(index+1 >= from){
//                System.out.println( (index+1) + " :  " + iterator.next() + "\n"); //"Position " +
//            }else{
//                iterator.next();
//            }
//        }
    }

    /**
     * Gegeben
     * Gibt alle Elemente der Liste l aus. Dazu muss zunächst die Methode unter A.3.2.3 implementiert sein.
     * @param l
     */
    public static void print(List<?> l){
        print(l,0,l.size());
    }

    /**
     * Gegeben
     * Gibt die Liste der Movies im Bereich [from,from+howMany) in Kurzdarstellung aus. Dazu müssen zunächst
     * die Methoden A.3.2.3 und A.3.2.4 implementiert sein.
     *
     * Bsp.: Wenn me ein MovieEvaluator ist, dann erzeugt me.print(20,5) die Ausgabe
     *
     * 20:2 Days in the Valley (1996)
     * 21:2 Fast 2 Furious (2003)
     * 22:200 Cigarettes (1999)
     * 23:20000 Leagues Under the Sea (1954)
     * 24:2001: A Space Odyssey (1968)
     *
     * @param from    Startindex für die Ausgabe
     * @param howMany Anzahl der Elemente, die ausgegeben werden sollen.
     */
     public void print(int from, int howMany) {
        MovieEvaluator.print(mapToShortList(movies), from, howMany);
    }


    /**
     * A.3.2.4
     * TODO
     * Bildet die Liste der Filme auf eine Liste von Strings ab, die eine Kurzdarstellung der Filme enthalten.
     * Die Kurzdarstellung eines Films liefert die Methode toShortString der Klasse Movie.
     *
     * Auszug aus einem Ergebnis: [... Suspicion (1941), Suspiria (1977), Sweet Hereafter, The (1997),
     * Sweet Home Alabama (2002), Sweet November (2001), Sweet Smell of Success (1957), Sweet and Lowdown (1999),
     * Sweetest Thing, The (2002), Swept Away (2002), Swimfan (2002), Swimming Pool (2003), Swimming with Sharks (1994),
     * ...]
     *
     * @param movies
     * @return Liste mit Kurzdarstellungen der Filme.
     */
    public static List<String> mapToShortList(List<Movie> movies) {
        return movies.stream().map(Movie::toShortString).collect(Collectors.toList());
        //---------Lösung2-----------
//    Iterator<Movie> iterator = movies.iterator();
//    List<String> list = new ArrayList<>();
//    while (iterator.hasNext()){
//        list.add(iterator.next().toShortString());
//    }
//        return list;
    }

    /**
     * Gegegeben. Zuvor muss A.3.2.4 implementiert sein.
     * Bildet alle Filme auf eine Liste von Strings ab, die eine Kurzdarstellung der Filme enthalten.
     *
     * Auszug aus einem Ergebnis: [... Suspicion (1941), Suspiria (1977), Sweet Hereafter, The (1997),
     * Sweet Home Alabama (2002), Sweet November (2001), Sweet Smell of Success (1957), Sweet and Lowdown (1999),
     * Sweetest Thing, The (2002), Swept Away (2002), Swimfan (2002), Swimming Pool (2003), Swimming with Sharks (1994),
     * ...]
     */
    public List<String> mapToShortList() {
        return mapToShortList(movies);
    }

    /**
     * A.3.2.5
     * TODO
     * Berechnet den ersten Film, dessen Titel mit prefix beginnt.
     *
     * Bsp.: Index des ersten Films in einer sortierten Liste der movies, der mit "T" beginnt, ist 3583
     *
     * @param prefix: Präfix des Filmtitels
     * @return Index aus [0,Länge der Movie-Liste) oder -1, wenn es keinen Film mit dem Präfix gibt.
     */
    public int firstIndexOfMovieWith(String prefix) {
        return movies.stream().filter(m -> m.getTitle().startsWith(prefix)).map(e-> movies.indexOf(e)).findFirst().orElse(-1);
        //return movies.indexOf(movie);
        //--------------Version2-----------------
//        int size = prefix.length();
//        List<String> titelList = movies.stream().map(Movie::getTitle).collect(Collectors.toList());
//        Optional<String> opt = titelList.stream().filter(e-> (e.length() >= size &&
//                Arrays.equals(e.toCharArray(), 0, size, prefix.toCharArray(), 0, size))).findFirst();
//        if (opt.isPresent()){
//            return titelList.indexOf(opt.get());
//        }
//        return -1;
        //----------------Version3------------------
        //ListIterator<Movie> iterator = sortByTitleThenNumberOfActors().listIterator();   // sortierten Liste??????????
//        ListIterator<Movie> iterator = movies.stream().sorted(Comparator.comparing(Movie::getTitle)).collect(Collectors.toList()).listIterator();
//        while (iterator.hasNext()){
//            int index = iterator.nextIndex();
//            String titel = iterator.next().getTitle();
//            int size = prefix.length();
//            if (size <= titel.length() && Arrays.equals(titel.toCharArray(), 0, size, prefix.toCharArray(), 0, size)){
//                return index;
//            }
//        }
//        return -1;
    }

    /**
     * A.3.2.6
     * TODO
     * Berechnet den letzten Film, dessen Titel mit prefix beginnt.
     * Bsp.: Index des letzten Films in einer sortierten Liste der movies, der mit "T" beginnt, ist 3798.
     * @param prefix: Präfix des Filmtitels
     * @return Index aus [0,Länge der Movie-Liste) oder -1, wenn es keinen Film mit dem Präfix gibt.
     */
    public int lastIndexOfMovieWith(String prefix) {
//        return movies.lastIndexOf(movies.stream().filter(m -> m.getTitle().startsWith(prefix)).f);
        //-----------Lösung2----------------
        return movies.indexOf(movies.stream().filter(m -> m.getTitle().startsWith(prefix)). //lastindex
                reduce((acc, elem)-> acc=elem).orElse(null));
          //---------Lösung3---------------
//        Movie movie = movies.stream().filter(m -> m.getTitle().startsWith(prefix)). //lastindex
//                reduce((acc, elem)-> acc=elem).orElse(null);
//        return movies.indexOf(movie);
        //----------Lösung4-----------
//        ListIterator<Movie> backIterator = movies.listIterator(movies.size());
//        while (backIterator.hasPrevious()){
//            int index = backIterator.previousIndex();
//            String titel = backIterator.previous().getTitle();
//            int size = prefix.length() ;
//            if ( size <= titel.length() && Arrays.equals(titel.toCharArray(), 0, size, prefix.toCharArray(), 0, size)){
//                return index;
//            }
//        }
 //     return -1;
    }
//V7_S42

    /**
     * A.3.2.7
     * TODO
     * Berechnet die Menge der Jahre, in denen Filme gedreht wurden.
     *
     * @return Menge der Filmjahre
     */
    public Set<Year> years() {
        return movies.stream().map(Movie::getYear).collect(Collectors.toSet());
    }

    /**
     * A.3.2.8
     * TODO
     * Sammelt alle Schauspieler/Schauspielerinnen in einer Menge ein
     *
     * @return Menge der Acteure
     */
    public Set<Actor> actors() {
        return movies.stream().map(Movie::getActors)
                .flatMap(List::stream).collect(Collectors.toSet());
    }

    /**
     * A.3.2.9
     * TODO
     * Bildet die Filmliste auf eine  geordnete Tabelle (SortedMap) ab, in der die Schlüssel die Jahre und
     * die Werte die Liste der Filme in diesem Jahr sind
     *
     * Beispiel-Ergebnis vgl. inYearMovieMapShort(). Nur dass hier die Langform der Filme stehen würde.
     *
     * @return SortedMap, deren Schlüssel die Jahre und deren Werte die Filme in dem Jahr sind.
     */
    public SortedMap<Year, List<Movie>> inYearMovieMap() {
        return new TreeMap<>(movies.stream().collect(Collectors.groupingBy(Movie::getYear))); //Year comparable
    }

    /**
     * Gegeben!
     * Bildet die Filmliste auf eine geordnete Tabelle(SortedMap) ab, in der die Schlüssel die Jahre und
     * die Werte die Liste der Filme als String in Kurzdarstellung sind ("<Name des Films> (>Jahr des Films>)")
     * in diesem Jahr sind.
     *
     * Beispiel-Ergebnis: Wenn nur 3 Elemente der Map ausgegeben werden. (Methode print aus A.3.2.2)
     * 1902 => [Voyage dans la lune, Le (1902)]
     * 1915 => [Birth of a Nation, The (1915)]
     * 1916 => [Intolerance: Love's Struggle Throughout the Ages (1916)]
     *
     * @return SortedMap, deren Schlüssel die Jahre und deren Werte die die Liste der Filme Kurzdarstellung sind.
     */
    public SortedMap<Year, List<String>> inYearMovieMapShort() {
        Map<Year, List<String>> mylmShort = inYearMovieMap().entrySet().stream().collect(Collectors.toMap(e -> e.getKey(),
                e -> mapToShortList(e.getValue())));
        return new TreeMap<>(mylmShort);
    }

    /**
     * A.3.2.10
     * TODO
     * Berechnet für die Filmliste das Jahr, in dem die meisten Filme gedreht wurden.
     *
     * @return Jahr mit den meisten Filmen
     */
    public Year yearOfMovies() {
        return inYearMovieMapShort().entrySet().stream().max(Comparator.comparing(e -> e.getValue().size()))
                .map(Map.Entry::getKey).orElse(null);
        //-----------Version2-----------
//        Optional<Map.Entry<Year, List<String>>> opt = inYearMovieMapShort().entrySet().stream().max(Comparator.comparing(e -> e.getValue().size()));
//        if(opt.isPresent()){
//            return opt.get().getKey();     // Mit opt.get()  bekommt man Map.Entry<Year, List<String>>;
//        }else return null;
    }

    /**
     * A.3.2.11
     * TODO
     * Bildet die Filmliste auf eine geordnete Tabelle (SortedMap) ab, in der die Schlüssel die Schauspieler/Schauspielerinnen und
     * die Werte die Liste der Filme sind, in denen die Actor gespielt haben.
     *
     * Beispiel-Ergebnis: wie für inActorMovieMapShort() nur dass hier die Langform der Filme ausgegeben wird.
     *
     * @return SortedMap, deren Schlüssel die Schauspieler/Schauspielerinnen und deren Werte deren Filme sind.
     */
    public SortedMap<Actor, List<Movie>> inActorMovieMap() {
//        return new TreeMap<>(movies.stream().flatMap(movie -> movie.getActors().stream().
//                map(actor -> Map.entry(actor, movie)))
//                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList()))));
        //----------Lösung2----------------
//        HashMap<Actor, List<Movie>> map = new HashMap<>();
//        for(Movie movie : movies){
//            for(Actor actor : movie.getActors()){
//                map.putIfAbsent(actor, new ArrayList<Movie>());
//                map.get(actor).add(movie);
//            }
//        }
//        return new TreeMap<>(map);
        //--------Lösung3------------------
        return new TreeMap<Actor, List<Movie>>(movies.stream()
                .flatMap(movie -> movie.getActors().stream()
                        .map(actor -> Map.entry(actor, movie)))
                .collect(Collectors
                        .groupingBy(Map.Entry::getKey, Collectors
                                .mapping(Map.Entry::getValue, Collectors.toList()))));
    }

    /**
     * Gegeben
     * Wandelt die Map aus A.3.2.1 in eine Map mit Kurzdarstellungen für die Filme um.
     *
     * Beispiel-Ergebnis: Wenn nur 3 Elemente der Map ausgegeben werden. (Methode print aus A.3.2.2)
     *
     * Bernard Abbou => [Quatre cents coups, Les (1959)]
     * Damien Abbou => [Sous le sable (2000)]
     * Patrice Abbou => [Taxi 3 (2003)]
     *
     * @return SortedMap, deren Schlüssel die Schauspieler/Schauspielerinnen und
     * deren Werte die Liste der Filme in Kurzdarstellung sind.
     */
    public SortedMap<Actor, List<String>> inActorMovieMapShort() {
        return new TreeMap<>(inActorMovieMap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> mapToShortList(e.getValue()))));
    }

    /**
     * A.3.2.12
     * TODO
     * Gibt den/die Schauspieler/Schauspielerin zurück, die in den meisten Filmen gespielt hat.
     * Ergebnis: Frank Welker
     * @return Schauspieler/Schauspielerin mit den meisten Filmen
     */
    public Actor favourite() {
        return inActorMovieMapShort().entrySet().stream().max(Comparator.comparing(e -> e.getValue().size()))
                        .map(Map.Entry::getKey).orElse(null);
        //-----------Version2----------------
//        Optional<Map.Entry<Actor, List<String>>> opt = inActorMovieMapShort().entrySet().stream().max(Comparator.comparing(e->e.getValue().size()));
//        //Optional<Map.Entry<Actor, List<Movie>>> opt = inActorMovieMap().entrySet().stream().max(Comparator.comparing(e->e.getValue().size()));
//        if(opt.isPresent()){
//            //System.out.println(opt.get().getValue().size());
//            return opt.get().getKey();
//        }else return null;
    }

    /**
     * A.3.2.13
     * Prüft, ob es einen/eine Schauspieler/Schauspielerin gibt, die mehr als "num" Filme gedreht hat.
     *
     * @param num
     * @return true, wenn es eine solche Person gibt, ansonsten false.
     */
    public boolean anyWithMoreFilmsThen(int num) {
        return inActorMovieMapShort().entrySet().stream().anyMatch(e->e.getValue().size() > num);
    }

    /**
     * TODO
     * A.3.2.14
     * Wandelt movies in eine Actor-Movie-Map um und sortiert
     * nach Name des/der Schauspieler/Schauspielerin, dann nach Anzahl ihrer Filme.
     * Das Ergebnis ist eine Liste.
     *
     * Beispiel-Ergebnis: Wenn nur 4 Elemente der Liste ausgegeben werden (vgl. print/3 Methode für Listen):
     * wie für sortByNameThenNumberOfFilmsShort() nur dass hier die Langform der Filme ausgegeben wird.
     *
     * @return Eine Liste mit Map.Entry Elementen, die nach Vorgabe sortiert sind.
     */
    public List<Map.Entry<Actor, List<Movie>>> sortByNameThenNumberOfFilms() {
        return inActorMovieMap().entrySet().stream().
                sorted(Comparator.comparing((Map.Entry<Actor, List<Movie>> e)-> e.getKey()).
                        thenComparing((Map.Entry<Actor, List<Movie>> e) -> e.getValue().size())).collect(Collectors.toList());

        //---------Lösung2-----------
//        return inActorMovieMap().entrySet().stream().
//                sorted(Comparator.comparing((Map.Entry<Actor, List<Movie>> e)-> e.getKey()).
//                        thenComparing(e -> e.getValue().size())).collect(Collectors.toList());
    }
    //-----------------Problem----------------
    //it stop working when start chaining. (thenComparing)
    // While lambda expressions and generic method invocations are poly expressions (their type is context-sensitive)
    //-----------------Solution---------------
    //    Use an exact method reference (one with no overloads), like Song::getTitle. This then gives enough type information to infer the type variables for the comparing() call, and therefore give it a type, and therefore continue down the chain.
    //    Use an explicit lambda (What sortByNamThenNumberOfFilms used : (Typ e).
    //    Provide a type witness for the comparing() call: Comparator.<Song, String>comparing(...).
    //    Provide an explicit target type with a cast, by casting the receiver expression to Comparator<Song>.
    //Quelle : //https://stackoverflow.com/questions/24436871/very-confused-by-java-8-comparator-type-inference

    /**
     * Gegeben!
     * Wandelt die Liste aus A.3.2.14 in eine Liste um, die für File die Kurzdarstellung verwendet.
     *
     * Beispiel-Ergebnis: Wenn nur 4 Elemente der Liste ausgegeben werden (vgl. print/3 Methode für Listen):
     *
     * 10:Sigrid Aalbæk=[Festen (1998)]
     * 11:Mariann Aalda=[Beaches (1988), Pretty Woman (1990)]
     * 12:Aaliyah=[Queen of the Damned (2002), Romeo Must Die (2000)]
     * 13:Minna Aaltonen=[Tomorrow Never Dies (1997)]
     *
     * @return Eine Liste mit Map.Entry Elementen, die nach Vorgabe sortiert sind.
     */
    public List<Map.Entry<Actor, List<String>>> sortByNameThenNumberOfFilmsShort() {
        return sortByNameThenNumberOfFilms().stream()
                .map((Map.Entry<Actor, List<Movie>> e) -> Map.entry(e.getKey(), mapToShortList(e.getValue())))
                .collect(Collectors.toList());
    }
}
