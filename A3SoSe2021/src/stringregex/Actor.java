package stringregex;

import java.util.Objects;

public class Actor implements Comparable<Actor>, Cloneable{
    private String firstname;
    private String name;

    public Actor(String firstName, String name){
        this.firstname =firstName;
        this.name = name;
    }

    @Override
    public String toString() {
        return  (firstname.isEmpty() ? "" : firstname + " ") + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return  Objects.equals(firstname, actor.firstname) &&
                Objects.equals(name, actor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, name);
    }

    @Override
    public int compareTo(Actor o) {
        return firstname.compareTo(o.firstname) == 0 ? name.compareTo(o.name) : firstname.compareTo(o.firstname);
        //--------Oder--------------
        //return name.compareTo(o.name) == 0 ? firstname.compareTo(o.firstname) : name.compareTo(o.name);
        // Zuerst vergleiche den Nachname, falls gleich, dann vergleiche den Vorname. //compareToIgnoreCase()
    }

//    public String getFirstname() {
//        return firstname;
//    }

//    public String getName() {
//        return name;
//    }

    @Override
    public Actor clone() throws CloneNotSupportedException{
        return  (Actor)super.clone();
    }
//-----------Oder---------
//    public Actor clone() {
//        try {
//            return (Actor)super.clone();
//        } catch (CloneNotSupportedException e) {
//            // will never happen
//            throw new InternalError();
//        }
//    }
 //<https://javarevisited.blogspot.com/2013/09/how-clone-method-works-in-java.html#axzz6wf7vcXu2
    // V4_S62
//    Cloneable ist ein Interface in Java, das für die Kopierbarkeit eines Objektes steht. In Object ist die Methode clone bereits implementiert.
//    Da Kopien eine teure Operation sind, sind nicht standardmäßig alle Objekte tief kopierbar. Daher ist die Methode protected.
//    Bei Bedarf kann sie von Subklassen überschrieben und freigegeben werden (durch Erweitern der Sichtbarkeit auf public) werden kann.
//V8_S95
}
