package formen;

public class Kreis extends GeometrischeForm {
    private int radius;

    public Kreis(Punkt refPunkt, int radius){
        super(refPunkt);
        this.radius = radius;
    }

 /*   super() im Konstruktor ruft den Konstruktor der Superklasse ohne Argumente auf.
        super(...) mit mehr als einem Argument ist zulässig. Nur im Konstruktor zulässig. V1.folie23 */

    public Kreis(Punkt refPunkt){
        this(refPunkt, 1);
    }

    public Kreis(){
        this(new Punkt());
    }
// ############################################# ????????????????????
    public Kreis(int radius){
        this(new Punkt(), radius);
    }
// ############################################## ?????????
    @Override
    public void skaliere(int faktor) {
        this.radius *= faktor;
    }

    public void skaliere(){
        skaliere(1);
    }
    
    @Override
    public String toString() {
        return  super.toString() + String.format("r=%d", radius); // super ist die Referenz auf den • Objektkontext der Superklasse. V1.folie23
    }
}
