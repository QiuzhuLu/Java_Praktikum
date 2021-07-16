package formen;

public class FigurenSzenario {

    public static void main(String[] args) {
        // Methodeaufrufe mit TypDeklaration verbunden. Das heist:  FigurenSzenario f1 = new Kreis();
        // Dann f1 kann nur methode aus Klasse GeometrischeForm benutzen, kann nicht die Methode aus Kreis benutzen.
        Kreis k001 = new Kreis();
        Kreis k102 = new Kreis(new Punkt(1),2);
        Kreis k123 = new Kreis(new Punkt(1,2),3);
        Rechteck r0011 = new Rechteck();
        Rechteck r1011 = new Rechteck(new Punkt(1));
        Rechteck r1211 = new Rechteck(new Punkt(1,2));
        Rechteck r1231 = new Rechteck(new Punkt(1,2), 3);
        Rechteck r1234 = new Rechteck(new Punkt(1,2), 3,4);

        System.out.println(k001);
        System.out.println(k102);
        System.out.println(k123);

        System.out.println(r0011);
        System.out.println(r1011);
        System.out.println(r1211);
        System.out.println(r1231);
        System.out.println(r1234);

        k001.skaliere();
        System.out.println(k001);
        k001.skaliere(2);
        System.out.println(k001);
        k001.verschiebe();
        System.out.println(k001);
        k001.verschiebe(new Punkt(2,3));
        System.out.println(k001);

        r1011.skaliere();
        System.out.println(r1011);
        r1011.skaliere(2);
        System.out.println(r1011);
        r1011.verschiebe();
        System.out.println(r1011);
        r1011.verschiebe(new Punkt(2,3));
        System.out.println(r1011);
    }
}
