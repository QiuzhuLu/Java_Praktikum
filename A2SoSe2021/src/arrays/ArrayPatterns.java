package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ArrayPatterns {

    private static final Random RAND = new Random(124465767);
    /**
     * A2-1-1: Generiert einen zufälligen Wert im Intervall [Character.MIN_VALUE, Character.MAX_VALUE].
     * Ein nicht typsicherer Cast auf short ist nicht erlaubt.
     * @return ein gültiger short-Wert
     */
    public static short genShort(){
        int value = RAND.nextInt(2 * Short.MAX_VALUE + 1) - Short.MAX_VALUE;
        return (short) value;
    }

    /**
     * A2-1-2 Prüft, ob ein int-Wert i im Wertebereich von short liegt
     * @param i der zu prüfenden Wert
     * @return true, wenn i im Wertebereich von short liegt, sonst false
     */
    public static boolean isShort(int i) {
        return i == (short) i;
    }


    /**
     * A2-1-3: Erzeugen Sie ein 2D short[][] Array der Länge n, und Anzahl Spalten im Intervall [1..m],
     * dessen Werte im Intervall [Short.MIN_VALUE, Short.MAX_VALUE] liegen.
     *
     * @param n Anzahl Zeilen
     * @param m maximale Anzahl Spalten
     * @return short[][] mit n Zeilen und Anzahl Spalten im Intervall [1..m]
     */
    public static short[][] genIrregShortArray(int n, int m){
        short[][] shortAry = new short[n][];
        //Random random = new Random();
        for(int i=0; i<n; i++){
            int lange = RAND.nextInt(m)+1;
            shortAry[i] = new short[lange];    //Anzahl Spalten im Intervall [1..m]
            for(int j=0; j<lange; j++){
                shortAry[i][j] = genShort();
            }
        }
        return shortAry;
    }

    /**
     * A2-1-4: Erzeugen Sie ein 2D short[][] Array mit n-Zeilen und m-Spalten,
     * dessen Werte im Intervall [Short.MIN_VALUE, Short.MAX_VALUE] liegen.
     *
     * @param n Anzahl Zeilen
     * @param m Anzahl Spalten
     * @return Arrays mit n Zeilen und Anzahl Spalten
     */
    public static short[][] genRegShortArray(int n, int m){
        short[][] short2DAry = new short[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                short2DAry[i][j] = genShort();
            }
        }
        return short2DAry;
    }


    /**
     * A2-1-5 Traversiert Sie ein unregelmäßiges short[][] Array von rechts nach links
     * und sammelt Sie die short-Werte in einer ArrayList<Short> ein.
     *
     * @param shortArray
     * @return eine Liste, die die Zahlen in umgekehrter Reihenfolge enthält.
     */

    public static List<Short> inListFlattenReverse(short[][] shortArray){
        List<Short> shortList= new ArrayList<Short>();
        for(int i = shortArray.length-1; i>=0; i--){
            for(int j= shortArray[i].length-1; j>=0 ; j--){
                shortList.add(shortArray[i][j]);
            }
        }
        return shortList;
    }
  /* public static List<Short> inListFlattenReverse(short[][] shortArray){
        List<Short> shortList= new ArrayList<Short>();
        for(int i=0; i<shortArray.length; i++){
            int zeilei = shortArray.length -1 -i;
            for(int j=0; j<shortArray[zeilei].length; j++){
                int spaltei = shortArray[zeilei].length-1-j;
                short elem = shortArray[zeilei][spaltei];
                shortList.add(elem);
            }
        }
        return shortList;
    }*/
    /**
     * A2-1-6 Bestimmt die Anzahl der Elemente eines unregelmäßigen short[][] Array.
     * @param shortArray
     * @return Anzahl der Elemente
     */
    public static int sizeOf(short[][] shortArray){
        int sum = 0;
        for(short[] short1DAry: shortArray){
            sum += short1DAry.length;
        }
        return sum;
    }

    /**
     * A2-1-7 Traversiert ein unregelmäßiges short[][]-Array von rechts nach links
     * und sammelt Sie die Werte in einem  short[] Array ein.
     * @param shortArray
     * @return Array der short Werte aus shortArray in umgekehrter Reihenfolge
     */
    public static short[] inArrayFlattenReverse(short[][] shortArray){
        short[] flacheReverseAry = new short[sizeOf(shortArray)];
        int index = 0;
        for(int i=shortArray.length-1; i>=0; i--){
            for(int j=shortArray[i].length-1; j>=0; j--){
                flacheReverseAry[index] = shortArray[i][j];
                index++;
            }
        }
        return flacheReverseAry;
    }


    /**
     * A2-1-8 Gibt die gegenüberliegenden Elemente eines short[] Arrays aus.
     * @param shortArray
     */
    public static void printOpposites(short[] shortArray){
        int mitte = (shortArray.length+1)/2;
        for(int i=0; i<mitte; i++){
            System.out.println(String.format("%d <=> %d", shortArray[i], shortArray[shortArray.length-1-i]));
        }
    }

    /**
     * A2-1-9 Gibt die gegenüberliegenden short-Werte eines short[][] Arrays aus.
     * @param shortArray
     */
    public static void printOpposites(short[][] shortArray){
        int zeileAnzahl = shortArray.length;
        int spalteAnzahl ;
        for(int i=0; i<(zeileAnzahl+1)/2; i++){
            spalteAnzahl = shortArray[i].length;
            for(int j=0; j<spalteAnzahl; j++){
                System.out.println(String.format("%d <=> %d", shortArray[i][j], shortArray[zeileAnzahl-1-i][spalteAnzahl-1-j]));
            }
        }
    }


    /**
     * A-2-1-10 Erstellt aus einem (unregelmäßigem) short[][] Array eine Kopie,
     * die nur die 1'te Hälfte der enthaltenen Arrays kopiert.
     * @param shortAry
     * @return Kopie, die die 1'te Hälfte der enthaltenen 1D-Arrays enthält
     */
    public static short[][] kopiereHaelfte1D(short[][] shortAry ){
        int zeileAnzahl = shortAry.length;
        short [][] halfAry = new short[zeileAnzahl][];
        for(int i=0; i<zeileAnzahl; i++){
           halfAry[i] = Arrays.copyOf(shortAry[i], shortAry[i].length/2);
        }
        return halfAry;
    }

    /**
     * A-2-1-11 Erstellt aus (unregelmäßigem) short[][] Array eine Kopie,
     * die jeweils ab der Hälfte der enthaltenen 1D-Arrays kopiert.
     * @param shortAry
     * @return Kopie, die die 2'te Hälfte der enthaltenen 1D-Arrays enthält
     */

    public static short[][] kopiereAbHaelfte1D(short[][] shortAry ){
        int zeileAnzahl = shortAry.length;
        short [][] halfAry = new short[zeileAnzahl][];
        for(int i=0; i<zeileAnzahl; i++){
            halfAry[i] = Arrays.copyOfRange(shortAry[i], shortAry[i].length/2, shortAry[i].length);
        }
        return halfAry;
    }

    /**
     * A-2-1-12 Fasst in einem (unregelmäßigem) short[][] Array je 2 aufeinanderfolgenden 1D Arrays zusammen.
     * Wenn das Array eine ungerade Anzahl von Elementen hat, dann ist das letzte Element des Ergebnisarrays
     * das letzte Element des Originals.
     * @param shortAry
     * @return ein um die Häfte der Elemente kürzeres short[][] Array (ggf. +1)
     */

    public static short[][] reduziere(short[][] shortAry){
        int zeileAnzahl = shortAry.length;
        short[][] reduziereAry = new short[(zeileAnzahl+1)/2][];
        for(int i=0; i<zeileAnzahl; i+=2 ){
            if(zeileAnzahl-i==1){  // für die letzte Zeile von ungeraden Ary
                reduziereAry[(zeileAnzahl+1)/2-1] = shortAry[i];
            }else{
                reduziereAry[i/2] = Arrays.copyOf(shortAry[i], shortAry[i].length + shortAry[i+1].length);
                System.arraycopy(shortAry[i+1], 0, reduziereAry[i/2], shortAry[i].length, shortAry[i+1].length);
            }
        }
        return reduziereAry;
    }

    public static void printO(Object[] ary){
        System.out.println(Arrays.deepToString(ary));
    }

    public static void trenn(int i){
        System.out.println("_____________________Test_Aufgabe: " + i + "_______________________");
    }

    public static void main(String[] args) {
        short[][] ary1 = new short[][]{{1,3},{2,5}};
        short[][] ary2 = new short[][]{{1,3},{2,5}, {1}};
        short[][] ary3 = new short[][]{{1,3},{2,5,6}, {1}};
        short[][] ary4 = new short[][]{{1}};
        short[][] ary5 = new short[][]{{}};

        trenn(1);
        System.out.println(genShort());
        trenn(2);
        System.out.println(isShort(Short.MAX_VALUE));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        trenn(3);
        printO(genIrregShortArray(3, 4));
        trenn(4);
        printO(genRegShortArray(3,5));
        trenn(5);
        System.out.println(inListFlattenReverse(genIrregShortArray(3,4)));
        printO(ary2);
        System.out.println(inListFlattenReverse(ary2));
        trenn(6);
        System.out.println(sizeOf(genIrregShortArray(3,4)));
        System.out.println(sizeOf(genRegShortArray(3,4)));
        trenn(7);
        System.out.println(Arrays.toString(inArrayFlattenReverse(genIrregShortArray(3,4))));
        printO(ary2);
        System.out.println(Arrays.toString(inArrayFlattenReverse(ary2)));
        trenn(8);
        short[] ary9 = {1,2,3,4,5};
        short[] ary10 = {1,2,3,4};
        printOpposites(ary9);
        printOpposites(ary10);
        trenn(9);
        System.out.println(Arrays.deepToString(ary1));
        printOpposites(ary1);
        short[][] ary11 = {{1,2,3},{3,4,5}, {6,7,8}};
        printOpposites(ary11);
        short[][] ary12 = {{1,2,3},{3}, {1,7,8}};
        //short[][] ary13 = {{1,2,3},{3}, {7,8}}; gibt einen Fehler
        printOpposites(ary12);
        trenn(10);
        printO(kopiereHaelfte1D(ary11));
        printO(kopiereHaelfte1D(ary12));
        trenn(11);
        printO(kopiereAbHaelfte1D(ary11));
        printO(kopiereAbHaelfte1D(ary12));
        trenn(12);
        short[][] ary13 = {{1},{2},{3},{4}};
        printO(reduziere(ary1));
        printO(reduziere(ary2));
        printO(reduziere(ary3));
        printO(reduziere(ary4));
        printO(reduziere(ary5));
        printO(reduziere(ary13));
        printO(reduziere(new short[][]{{}}));
    }
}
