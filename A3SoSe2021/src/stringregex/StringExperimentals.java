package stringregex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExperimentals {

    public static List<Integer> alleVorkommen(String str, String subStr) {
        List<Integer> li = new ArrayList<>();
        Pattern pat = Pattern.compile(subStr);
        Matcher mat = pat.matcher(str);
        while (mat.find()) {
            li.add(mat.start());
        }
        return li;
    }

    /**
     * Geben Sie 3 Beispiele, in denen inhaltsgleiche Strings auch == sind.
     * Geben Sie 4 Beispiele, in denen inhaltsgleiche Strings nicht == sind.
     * Verwenden Sie immer die gleiche Zeichenkette und experimentieren Sie mit String-Methoden.
     */
    public static void gleichDemo() {
        //inhaltsgleich == inhaltsgleich
        String something = "irgendwas";
        String somethingSame = something;
        String somethingEqual = "irgendwas";
        String some_thing = "irgend" + "was";
        System.out.println(something == somethingSame);
        System.out.println(something == somethingEqual);
        System.out.println(something == some_thing);

        //inhaltsgleich != inhaltsgleich
        something = new String("irgendwas");
        System.out.println(something == somethingSame);
        System.out.println(something == somethingEqual);
        somethingEqual = somethingEqual.replace("irgendwas", "anderes");
        something = something.replace("irgendwas", "anderes");
        System.out.println(something == somethingEqual);
        char[] charAry = {'o', 'd', 'e', 'r', 's', 'o'};
        something = String.valueOf(charAry);
        somethingEqual = "oderso";
        System.out.println(something == somethingEqual);
    }

    private static String leipzig = "leipzig1M.txt";
    private static String tale = "tale.txt";

    /**
     * Lesen Sie die Datei "tale.txt" zeilenweise mit dem Scanner in eine große Zeichenkette.
     * Führen Sie zwei Versuche durch, die die zwei Varianten des Konstruierens großer Zeichenketten verwenden.
     * Messen Sie für beide Varianten die Zeit und erklären das Ergebnis.
     * <p>
     * Führen Sie das gleiche Experiment mit leipzig1M.txt durch.
     *
     * @throws FileNotFoundException
     */
    public static void wieSchnellLiestDu(Boolean stingbuilderMode, File file) throws FileNotFoundException { //boolean

        Scanner scan = new Scanner(file);
        long time = -System.currentTimeMillis();

        if (!stingbuilderMode) {
            String aVeryLongString = "";
            while (scan.hasNextLine()) {
                aVeryLongString += scan.nextLine();
            }
            scan.close();
            time += System.currentTimeMillis();
            System.out.println(time + " ms ⏱ mit String+=Line");
        }
        if (stingbuilderMode) {
            StringBuilder sb = new StringBuilder();
            while (scan.hasNextLine()) {
                sb.append(scan.nextLine());
            }
            scan.close();
            time += System.currentTimeMillis();
            System.out.println(time + " ms ⏱ mit StringBuilder.append(Line)");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("----------- alleVorkommen() -----------");
        String str = "aaaaaa";
        String subStr = "aaa";
        String subStr2 = "bb";
        List<Integer> li = alleVorkommen(str, subStr);
        System.out.println(li);
        String str2 = "babbacbbx";
        li = alleVorkommen(str2, subStr2);
        System.out.println(li);
        System.out.println("----------- gleichDemo() -----------");
        gleichDemo();
        System.out.println("----------- wieSchnellLiestDu(tale.txt) -----------");
        // wieSchnellLiestDu("s", new File("resources/tale.txt"));
        wieSchnellLiestDu(true, new File("resources/tale.txt"));
        System.out.println("----------- wieSchnellLiestDu(leipzig1M.txt) -----------");
        wieSchnellLiestDu(true, new File("resources/leipzig1M.txt"));
        // wieSchnellLiestDu(false, new File("resources/leipzig1M.txt"));
    }
}


