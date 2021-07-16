package leser;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import formen.*;

public class FormenLeser {

    private static final String KREIS_ID = "Kreis";
    private static final String RECHTECK_ID = "Rechteck";
    //private static String dateiname = "geodata";
    private static String dateiname = System.getProperty("java.class.path") + "/geodata"; // falls nur ein Path, dann wird so funktionieren. Sonst nicht

    public static void main(String[] args) throws FileNotFoundException {
           // TODO Einkommentieren, wenn die Klassen im Package formen implementiert sind.
        System.out.println("Path" + System.getProperty("java.class.path"));
        if(args.length == 0){
            System.out.println("Fehler: FormenLeser ohne Programmparameter aufgerufen. Erwartet wird:\n" +
                    "        v für das Lesen der vollständig spezifierten Formen\n" +
                    "        a für das Lesen der Formen mit Defaults\n");
        }else {
            if(args[0].equals("v") && args.length==1){
                List<GeometrischeForm> vollStaendigeFormen = leseNurVoellstaendige(dateiname);
                System.out.println("Liste der vollständig spezifizierten Formen");
                printList(vollStaendigeFormen);
            }else if(args[0].equals("a") && args.length==1){
                List<GeometrischeForm> formenMitDefaults = leseAlle(dateiname);
                System.out.println("Liste der Formen mit Defaults");
                printList(formenMitDefaults);
            }else{
                System.out.println("Falsches Argument '" + args[0] + "' für FormenLeser. Erwartet wird:\n" +
                        "        v für das Lesen der vollständig spezifierten Formen\n" +
                        "        a für das Lesen der Formen mit Defaults\n" );
            }
        }
    }

    private static List<Integer> readInt(Scanner scanner){
        int counter = 0;
        List<Integer> data = new ArrayList<Integer>();
        while (scanner.hasNextInt()){
            data.add(scanner.nextInt());
        }
        //if(counter == length){ //vollständige Data
            return data;
  /*      }else{
            return null;   // unvollständige Data
        }*/

    }

 //TODO Einkommentieren, wenn die Klassen im Package formen implementiert sind.
 private static List<GeometrischeForm> leseNurVoellstaendige(String dateiname) throws FileNotFoundException {
        List<GeometrischeForm> lgf = new ArrayList<>();
        Scanner formenScanner = new Scanner(new File(dateiname));
        //formenScanner.useDelimiter(" ");
        while (formenScanner.hasNext()){
            String nextWord = formenScanner.next();
            switch (nextWord){
                case KREIS_ID:
                   List<Integer> kreisData = readInt(formenScanner);
                    if(kreisData.size()==3){     // Fehlerhaften Fälle behandeln/vermeiden
                        lgf.add(new Kreis(new Punkt(kreisData.get(0), kreisData.get(1)), kreisData.get(2)));
                    }
                    break;
                case RECHTECK_ID:
                    List<Integer> rechteckData = readInt(formenScanner);;
                    if(rechteckData.size() == 4){     // Fehlerhaften Fälle behandeln/vermeiden
                        lgf.add(new Rechteck(new Punkt(rechteckData.get(0), rechteckData.get(1)), rechteckData.get(2), rechteckData.get(3)));
                    }
            }
        }
        return lgf;
    }

    private static List<GeometrischeForm> leseAlle(String dateiname) throws FileNotFoundException {
        List<GeometrischeForm> lgf = new ArrayList<>();
        Scanner formenScanner = new Scanner(new File(dateiname));
        //formenScanner.useDelimiter(" ");
        while (formenScanner.hasNext()){
            String nextword = formenScanner.next();
            switch (nextword){
                case KREIS_ID:
                    List<Integer> kreisData = readInt(formenScanner);
                    switch (kreisData.size()){
                        case 2 -> lgf.add(new Kreis(new Punkt(kreisData.get(0), kreisData.get(1))));
                        case 1 -> lgf.add(new Kreis(new Punkt(kreisData.get(0))));
                        case 0 -> lgf.add(new Kreis());
                        default -> lgf.add(new Kreis(new Punkt(kreisData.get(0), kreisData.get(1)), kreisData.get(2)));
                        //Arrow labels
                        //In addition to traditional "case L :" labels in a switch block, we define a new simplified form, with "case L ->" labels.
                        // If a label is matched, then only the expression or statement to the right of the arrow is executed; there is no fall through.
                        //https://openjdk.java.net/jeps/361
                    }
                    break;
                case RECHTECK_ID:
                    List<Integer> rechteckData = readInt(formenScanner);
                    switch (rechteckData.size()){     // Fehlerhaften Fälle behandeln/vermeiden
                       
                        case 3 -> lgf.add(new Rechteck(new Punkt(rechteckData.get(0), rechteckData.get(1)), rechteckData.get(2)));
                        case 2 -> lgf.add(new Rechteck(new Punkt(rechteckData.get(0), rechteckData.get(1))));
                        case 1 -> lgf.add(new Rechteck(new Punkt(rechteckData.get(0))));
                        case 0 -> lgf.add(new Rechteck());
                        default -> lgf.add(new Rechteck(new Punkt(rechteckData.get(0), rechteckData.get(1)), rechteckData.get(2), rechteckData.get(3)));
                    }
                break;
                default:
                    throw new IllegalStateException("Unexpected value: " + nextword);
            }
        }
        return lgf;
    }

    private static void printList(List<GeometrischeForm> lgf) {
        for (int i = 0; i < lgf.size(); i++) {
            System.out.printf("%4d:%s\n", i, lgf.get(i));
        }
    }

}