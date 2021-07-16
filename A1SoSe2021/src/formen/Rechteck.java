package formen;

public class Rechteck extends GeometrischeForm {

    private int breite;
    private int hoehe;

    public Rechteck(Punkt refPunkt, int breite, int hoehe){
        super(refPunkt);
        this.breite = breite;
        this.hoehe = hoehe;
    }
    // Konstruktor Überladen : "this" bezieht sich auf die original Konstruktor
    // andere Methode Überladen: Die Methode sind mit dem gleichen Name. von die Anzahl und Typ von Parameter underscheiden die Methoden.??

    public Rechteck(Punkt ref_punkt, int breite){
        this(ref_punkt, breite, 1);
    }

    public Rechteck(Punkt ref_punkt){
        this(ref_punkt, 1);
    }

    public Rechteck(){
        this(new Punkt());
    }

    @Override
    public void skaliere(int faktor) {
       this.breite *= faktor;
       this.hoehe *= faktor;
    }

    public void skaliere(){
        skaliere(1);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(",b=%d, h=%d",breite,hoehe) ;
    }
    //String.format
}
