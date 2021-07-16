package arrays;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ArrayPatterns {

    //private static final Random RAND = new Random(124465767);
    private static final Random RAND = new Random();

    /**
     * A2-1-1: Generiert einen zufälligen Wert im Intervall [Short.MIN_VALUE, Short.MAX_VALUE].
     * Ein nicht typsicherer Cast auf short ist nicht erlaubt.
     *
     * @return ein gültiger short-Wert
     */
    public static short genShort() {
        int shortRangePos = Short.MAX_VALUE - Short.MIN_VALUE;
        int randShortRange = RAND.nextInt(shortRangePos + 1) + Short.MIN_VALUE;
     /*   if (!isShort(randShortRange)) {
            throw new IllegalArgumentException("not a short " + randShortRange);
        }*/
        return (short) randShortRange;
    }


    /**
     * A2-1-2 Prüft, ob ein int-Wert i im Wertebereich von short liegt
     *
     * @param i der zu prüfenden Wert
     * @return true, wenn i im Wertebereich von short liegt, sonst false
     */
    public static boolean isShort(int i) {
        return (short) i == i;
        // return (Short.MIN_VALUE <= i && i <= Short.MAX_VALUE);
    }


    /**
     * A2-1-3: Erzeugen Sie ein 2D short[][] Array der Länge n, und Anzahl Spalten im Intervall [1..m],
     * dessen Werte im Intervall [Short.MIN_VALUE, Short.MAX_VALUE] liegen.
     *
     * @param n Anzahl Zeilen
     * @param m maximale Anzahl Spalten
     * @return short[][] mit n Zeilen und Anzahl Spalten im Intervall [1..m]
     */
    public static short[][] genIrregShortArray(int n, int m) {
        short[][] ary = new short[n][]; //zwischen 1..m
        for (int i = 0; i < n; i++) {
            ary[i] = new short[(RAND.nextInt(m)) + 1];
            for (int j = 0; j < ary[i].length; j++) {
                ary[i][j] = genShort();
            }
        }
        return ary;
    }


    /**
     * A2-1-4: Erzeugen Sie ein 2D short[][] Array mit n-Zeilen und m-Spalten,
     * dessen Werte im Intervall [Short.MIN_VALUE, Short.MAX_VALUE] liegen.
     *
     * @param n Anzahl Zeilen
     * @param m Anzahl Spalten
     * @return Arrays mit n Zeilen und Anzahl Spalten
     */
    public static short[][] genRegShortArray(int n, int m) {
        short[][] ary = new short[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ary[i][j] = genShort();
            }
        }
        return ary;
    }


    /**
     * A2-1-5 Traversiert Sie ein unregelmäßiges short[][] Array von rechts nach links
     * und sammelt Sie die short-Werte in einer ArrayList<Short> ein.
     *
     * @param shortArray
     * @return eine Liste, die die Zahlen in umgekehrter Reihenfolge enthält.
     */
    public static List<Short> inListFlattenReverse(short[][] shortArray) {
        List<Short> list = new ArrayList<>();
        int n = shortArray.length;

        for (int i = n - 1; i >= 0; i--) {
            int m = shortArray[i].length;
            for (int j = m - 1; j >= 0; j--) {
                list.add(shortArray[i][j]);
            }
        }
        return list;
    }


    /**
     * A2-1-6 Bestimmt die Anzahl der Elemente eines unregelmäßigen short[][] Array.
     *
     * @param shortArray
     * @return Anzahl der Elemente
     */
    public static int sizeOf(short[][] shortArray) {
        int size = 0;
        for (int i = 0; i < shortArray.length; i++) {
            size += shortArray[i].length;
        }
        return size;
    }

    /**
     * A2-1-7 Traversiert ein unregelmäßiges short[][]-Array von rechts nach links
     * und sammelt Sie die Werte in einem  short[] Array ein.
     *
     * @param shortArray
     * @return Array der short Werte aus shortArray in umgekehrter Reihenfolge
     */
    public static short[] inArrayFlattenReverse(short[][] shortArray) {
        int index = 0;
        short[] revAry = new short[sizeOf(shortArray)];
        for (int i = shortArray.length - 1; i >= 0; i--) {
            for (int j = shortArray[i].length - 1; j >= 0; j--) {
                revAry[index++] = shortArray[i][j];  // nach der benutzung hochzahlen
                //index++;
            }
        }
        return revAry;
    }


    /**
     * A2-1-8 Gibt die gegenüberliegenden Elemente eines short[] Arrays aus.
     *
     * @param shortArray
     */
    public static void printOpposites(short[] shortArray) {
        int aryLength = shortArray.length;
        for (int i = 0; i < (aryLength + 1) / 2; i++) {
            System.out.println(shortArray[i] + "⟺︎" + shortArray[aryLength - 1 - i]);
        }
    }

    /**
     * A2-1-9 Gibt die gegenüberliegenden short-Werte eines short[][] Arrays aus.
     *
     * @param shortArray
     */
    public static void printOpposites(short[][] shortArray) {// 1--3
       /* short[] flatAry = new short[sizeOf(shortArray)];
        int k = 0;
        for (int i = 0; i < shortArray.length; i++) {
            for (int j = 0; j < shortArray[i].length; j++) {
                flatAry[k++] = shortArray[i][j];
            }
        }
        printOpposites(flatAry);*/

        for(int j=0; j<shortArray.length; j++){
            for(int i=0; i<(shortArray[j].length+1)/2; i++){
                System.out.println(String.format("%d <=> %d", shortArray[j][i], shortArray[j][shortArray[j].length-1-i]));
            }
        }
    }


    /**
     * A-2-1-10 Erstellt aus einem (unregelmäßigem) short[][] Array eine Kopie,
     * die nur die 1'te Hälfte der enthaltenen Arrays kopiert.
     *
     * @param shortAry
     * @return Kopie, die die 1'te Hälfte der enthaltenen 1D-Arrays enthält
     */
    public static short[][] kopiereHaelfte1D(short[][] shortAry) {
        short[][] halfAry = new short[shortAry.length][];
        for (int i = 0; i < shortAry.length; i++) {
            halfAry[i] = Arrays.copyOf(shortAry[i], ((shortAry[i].length + 1) / 2));
        }
        return halfAry;
    }

    /**
     * A-2-1-11 Erstellt aus (unregelmäßigem) short[][] Array eine Kopie,
     * die jeweils ab der Hälfte der enthaltenen 1D-Arrays kopiert.
     *
     * @param shortAry
     * @return Kopie, die die 2'te Hälfte der enthaltenen 1D-Arrays enthält
     */

    public static short[][] kopiereAbHaelfte1D(short[][] shortAry) {
        short[][] halfAry = new short[shortAry.length][];
        for (int i = 0; i < shortAry.length; i++) {
            halfAry[i] = Arrays.copyOfRange(shortAry[i], ((shortAry[i].length + 1) / 2), shortAry[i].length);
        }
        return halfAry;
    }

    /**
     * A-2-1-12 Fasst in einem (unregelmäßigem) short[][] Array je 2 aufeinanderfolgenden 1D Arrays zusammen.
     * Wenn das Array eine ungerade Anzahl von Elementen hat, dann ist das letzte Element des Ergebnisarrays
     * das letzte Element des Originals.
     *
     * @param shortAry
     * @return ein um die Häfte der Elemente kürzeres short[][] Array (ggf. +1)
     */

    public static short[][] reduziere(short[][] shortAry) {
        short[][] halfAry = new short[(shortAry.length + 1) / 2][];
        int lange = shortAry.length;
        for (int i = 0; i < shortAry.length - 1; i += 2) {
            halfAry[i / 2] = Arrays.copyOf(shortAry[i], shortAry[i].length + shortAry[i + 1].length);
            System.arraycopy(shortAry[i + 1], 0, halfAry[i / 2], shortAry[i].length, shortAry[i + 1].length);
        }
        if(lange%2!=0 && lange>=1){
            halfAry[halfAry.length - 1] = Arrays.copyOf(shortAry[shortAry.length - 1],
                    shortAry[shortAry.length - 1].length);
        }
        return halfAry;
    }

}

