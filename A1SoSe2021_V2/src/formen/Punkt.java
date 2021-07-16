package formen;

public class Punkt {
    private int x;
    private int y;

 /*   protected int getX() {
        return x;
    }*/

/*/The private modifier specifies that the member can only be accessed in its own class.
    The protected modifier specifies that the member can only be accessed within its own package (as with package-private) and,
    in addition, by a subclass of its class in another package.*/


/*    protected int getY() {
        return y;
    }*/

    public Punkt(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Punkt(int x){
        this(x, 1);
    }

    public Punkt() {
        this(1);
    }

    public Punkt plus(Punkt other){
        return new Punkt(this.x + other.x, this.y + other.y);
    }
    /* private in ruby ist vertical    falls other.y    y Reader muss protected sein.
     * private in java ist horizontal in der selben Klasse sichtbar, deshalb gilt this.y    , hier y braucht kein Reader
     * The protected modifier specifies that the member can only be accessed within its own package (as with package-private) and,
    in addition, by a subclass of its class in another package.*/

 /*   Sichtbarkeit von Methoden
    In Java bedeutet public, dass jeder eine Methode aufrufen darf.
     protected bedeutet, dass nur die Klasse selbst, ihre Instanzen und von ihr abgeleitete Klassen und deren Instanzen Zugriff haben.
     private beschränkt den Zugriff ganz auf die Klasse und ihre Instanzen.

    Ruby verhält sich anders. public ist natürlich öffentlich.
    private bedeutet, dass die Methode nicht mit einem expliziten Empfänger aufgerufen werden kann, mit anderen Worten: Der Empfänger ist immer self.
 Bei protected muss man aufpassen: Eine so geschützte Methode kann ohne Empfänger in der Klasse und abgeleiteten Klassen aufgerufen werden (wie bei private),
  aber zusätzlich auch mit einer anderen Instanz dieser Klasse als Empfänger.
  https://www.ruby-lang.org/de/documentation/ruby-from-other-languages/*/

    @Override
    public String toString() {
        return String.format("(%d,%d)",x,y);
    }
}
