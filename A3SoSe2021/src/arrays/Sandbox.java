package arrays;

import java.util.Arrays;
import java.util.Random;

public class Sandbox {
    private static Random rand = new Random(1212132423);

    /**
     * Prüft ob ary1 ein Kern von ary2 ist. ary1 ist ein Kern von ary2, wenn
     * a. ary1 genau um zwei Elemente kürzer ist als ary2 ist und
     * b.der Inhalt von ary1 mit dem Inhalt von ary2 im Intervall [1..ary2.length-1] übereinstimmt.
     * Symmetrie ist nicht verlangt!
     *
     * @param ary1
     * @param ary2
     * @return true, wenn ary1 ein Kern von ary2 ist, sonst false
     */
    public static boolean isKern(int[] ary1, int[] ary2) {
        return Arrays.equals(ary1, 0, ary1.length , ary2, 1, ary2.length - 1);
    }

    /**
     * Prüft ob ary symmetrisch ist. Die Länge von ary kann sowohl gerade als auch ungerade sein.
     *
     * @param ary
     * @return true, wenn ary1 symmetrisch ist, sonst false
     */
    public static boolean isSymmetrisch(int[] ary) {
        int mitte = ary.length / 2;
        for (int i = 0; i <= mitte; i++) {
            if (ary[i] != ary[ary.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prüft ob ein unregelmäßiges int[][]-Array ein Sanduhrmuster hat.
     * Ein Sanduhrmuster ist wie folgt definiert:
     * <p>
     * 1.	Die Mitte ist das kleinste Array und tritt nur einmal auf. Die Mitte ist ein symmetrisches int[]-Array.
     * 2.	In der oberen Hälfte gilt für je zwei aufeinanderfolgende Arrays a[i] und a[i+1]:
     * a.	a[i] und a[i+1] sind symmetrisch;
     * b.	a[i+1] ist ein Kern von a[i].
     * 3.	In der unteren Hälfte gilt für je zwei aufeinanderfolgende Arrays a[i] und a[i+1]:
     * a.	a[i] und a[i+1] sind symmetrisch;
     * b.	a[i] ist ein Kern von a[i+1].
     *
     * @param iAry
     * @return true, wenn iAry ein Sanduhrmuster enthält, sonst false
     */
    public static boolean isSandbox(int[][] iAry) {
        if (iAry.length % 2 == 0) return false;
        int mitte = iAry.length / 2;
        if (!isSymmetrisch(iAry[0])) return false;
        for (int i = 0; i < mitte; i++) {
            if (!((Arrays.equals(iAry[i], iAry[iAry.length - 1 - i])) &&
                    (isKern(iAry[i + 1], iAry[i])))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generiert ein int[][] Array mit einem Sanduhrmuster.
     *
     * @param n   Anzahl der Elemente des int[][] Arrays
     * @param max Länge der längsten Zeile
     * @return int[][] Array mit Sanduhrmuster oder null, wenn n gerade oder max < n
     */
    public static int[][] genSandbox(int n, int max) {
        int[][] sandboxAry = new int[n][];
        if (max < n || n % 2 == 0) return null;

        sandboxAry[0] = genSymmetric(max);
        sandboxAry[n-1] = sandboxAry[0].clone();
        for (int i = 1; i < (n + 1) / 2; i++) {
            int coreSize = sandboxAry[i-1].length-2;
            sandboxAry[i] = new int[coreSize];
            System.arraycopy(sandboxAry[i-1], 1, sandboxAry[i],0, coreSize);
            sandboxAry[sandboxAry.length-1-i] = sandboxAry[i].clone();

        }
        return sandboxAry;
    }

    /**
     * Generiert ein symmetrisches int[] Array der Länge n. n kann gerade und ungerade sein.
     *
     * @param n Anzahl der Elemente des int[] Arrays
     * @return symmetrisches int[] Array der Länge n.
     */
    public static int[] genSymmetric(int n) {
        Random rand = new Random();
        int[] symAry = new int[n];

        for (int i = 0; i < (n + 1) / 2; i++) {
            int k = rand.nextInt(9);
            symAry[i] = k;
            symAry[symAry.length - 1 - i] = k;
            //k = rand.nextInt(9);
        }
        return symAry;
    }

}
