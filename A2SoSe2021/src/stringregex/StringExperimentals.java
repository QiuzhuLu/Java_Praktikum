package stringregex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExperimentals {
    //https://shekhargulati.com/2010/05/04/finding-all-the-indexes-of-a-whole-word-in-a-given-string-using-java/
    public static List<Integer> alleVorkommmen(String str, String subStr) {
        List<Integer> li = new ArrayList<>();
        Pattern pattern = Pattern.compile(subStr);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            li.add( matcher.start());
        }
        return li;
    }

    /**
     * Geben Sie 3 Beispiele, in denen inhaltsgleiche Strings auch == sind.
     * Geben Sie 4 Beispiele, in denen inhaltsgleiche Strings nicht == sind.
     * Verwenden Sie immer die gleiche Zeichenkette und experimentieren Sie mit String-Methoden.
     */
    public static void gleichDemo(){
        System.out.println("inhaltsgleiche Strings auch == sind.");
        String str = "irgendwas";
        String str1 = "irgendwas";
        String str2 =  "irgend" + "was";
        String str3 = "ir" + "gendwas";
        System.out.println(str==str1); //Compiler machen aus Platzgründen inhaltsgleiche Strings zu identischen Strings. Sie verwenden dazu einen Stringpool. Seite 9
        System.out.println(str == str2);
        System.out.println(str == str3);
        System.out.println("inhaltsgleiche Strings nicht == sind.");
        String str4 = new String("irgendwas");
        String str5 = new String("irgend") + new String("was");
        String str6 = new String("irgend") + "was";
        String str7 = String.copyValueOf(new char[] {'i', 'r', 'g', 'e', 'n', 'd', 'w', 'a', 's', '4'}, 0, 9);
        String str8 = String.copyValueOf(new char[] {'i', 'r', 'g', 'e', 'n', 'd', 'w', 'a', 's'});
        System.out.println(str == str4);
        System.out.println(str == str5);
        System.out.println(str == str6);
        //System.out.println(str7);
        System.out.println(str == str7);
        System.out.println(str == str8);
    }

    private static String leipzig = "leipzig1M.txt";
    private static String tale = "tale.txt";
    /**
     * Lesen Sie die Datei "tale.txt" zeilenweise mit dem Scanner in eine grosse Zeichenkette.
     * Fuehren Sie zwei Versuche durch, die die zwei Varianten des Konstruierens grosser Zeichenketten verwenden.
     * Messen Sie fuer beide Varianten die Zeit und erklaeren das Ergebnis.
     *
     * Fuehren Sie das gleiche Experiment mit leipzig1M.txt durch.
     * @throws FileNotFoundException
     */
    public static void wieSchnellLiestDu() throws FileNotFoundException {
        String dateiName ;
        //dateiName = leipzig;
        dateiName = tale;
        Scanner scanner = new Scanner(new File(dateiName));

        System.out.println("Variante 1 : String");
        long timeMs = -System.currentTimeMillis();
        String string = "";
        while (scanner.hasNextLine()){
            string += scanner.nextLine();
        }
        timeMs +=  System.currentTimeMillis();
        System.out.println("String Iterationen: " + dateiName + " " + timeMs + " ms");

        System.out.println("Variante 2 : StringBuilder");
        long time = -System.nanoTime();
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()){
            sb.append(scanner.nextLine());
        }
        time +=  System.nanoTime();
        System.out.println("Builder Iterationen: " + dateiName + " " + time + " ns");
    }

    public static void wieSchnellLiestDu1() throws FileNotFoundException {
        String dateiName ;
        dateiName = leipzig;
        //dateiName = tale;
        System.out.println("Variante 1 : StringBuilder");
        long time = -System.nanoTime();
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(new File(dateiName));
        while (scanner.hasNextLine()){
            sb.append(scanner.nextLine());
        }
        time +=  System.nanoTime();
        System.out.println("Builder Iterationen: " + dateiName + " " + time + " ns");
        System.out.println("Variante 2 : String");
        long timeMs = -System.nanoTime();
        String string = "";
        while (scanner.hasNextLine()){
            string += scanner.nextLine();
        }
        timeMs +=  System.nanoTime();
        System.out.println("String Iterationen: " + dateiName + " " + timeMs + " ns");
    }
    
    // "+ " zeugen neue Objekte, und verursacht viele Speichemüll
    // apend, keine neue Objekte erzeugen, keine Speichermüll erzeugen, schneller
    // Zuerst performanteren Methode mal durchführen, dann sind diese String in Stringpool, dann läuft später die Methode mit "+" auch schnellner.


    public static void main(String[] args) throws FileNotFoundException {
        String str = "aaaaaa";
        String subStr = "aaa";
        String subStr2 = "bb";
        List<Integer> li = alleVorkommmen(str, subStr);
        System.out.println(li);
        String str2 = "babbacbbx";
        li = alleVorkommmen(str2, subStr2);
        System.out.println(li);
        gleichDemo();
        wieSchnellLiestDu();
        wieSchnellLiestDu1();
     }


}
