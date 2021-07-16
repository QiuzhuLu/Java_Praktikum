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
     * @param ary1
     * @param ary2
     * @return true, wenn ary1 ein Kern von ary2 ist, sonst false
     */
//    public static boolean isKern(int[] ary1, int[] ary2) {
//        boolean iskern = true;
//        if(ary1.length != ary2.length - 2){
//            iskern = false;
//        }
//        for(int i = 1; i<ary2.length-1; i++){
//            if(ary1[i-1] != ary2[i]){
//              iskern = false;
//            }
//        }
//        return iskern;
//    }
    public static boolean isKern(int[] ary1, int[] ary2) {
        return Arrays.equals(ary1, 0, ary1.length,  ary2, 1, ary2.length-1);
    }
    /**
     * Prüft ob ary symmetrisch ist. Die Länge von ary kann sowohl gerade als auch ungerade sein.
     * @param ary
     * @return true, wenn ary1 symmetrisch ist, sonst false
     */
    public static boolean isSymmetrisch(int[] ary) {
        boolean isSymmetrisch = true;
        int mitte = (ary.length+1)/2;
        for(int i=0; i<mitte; i++){
            if(ary[i] != ary[ary.length-1-i]){
                isSymmetrisch = false;
                break;
            }
        }
        return isSymmetrisch;
    }

    /**
     * Prüft ob ein unregelmäßiges int[][]-Array ein Sanduhrmuster hat.
     * Ein Sanduhrmuster ist wie folgt definiert:
     *
     * 1.	Die Mitte ist das kleinste Array und tritt nur einmal auf. Die Mitte ist ein symmetrisches int[]-Array.
     * 2.	In der oberen Hälfte gilt für je zwei aufeinanderfolgende Arrays a[i] und a[i+1]:
     *      a.	a[i] und a[i+1] sind symmetrisch;
     *      b.	a[i+1] ist ein Kern von a[i].
     * 3.	In der unteren Hälfte gilt für je zwei aufeinanderfolgende Arrays a[i] und a[i+1]:
     *      a.	a[i] und a[i+1] sind symmetrisch;
     *      b.	a[i] ist ein Kern von a[i+1].
     * @param iAry
     * @return true, wenn iAry ein Sanduhrmuster enthält, sonst false
     */
 /*   public static Boolean isSandbox(int[][] iAry) {
        Boolean isSandbox = true;
        if(iAry.length%2==0){
            return null;
        }
        int mitte = iAry.length/2;
        for(int i=0; i<mitte; i++){
            if(!isSymmetrisch(iAry[i]) || !isSymmetrisch(iAry[i+1]) || !isKern(iAry[i+1], iAry[i])){
                isSandbox = false;
            }
        }
        for (int i=mitte+1; i<iAry.length; i++ ){
            if(!isSymmetrisch(iAry[i-1]) || !isSymmetrisch(iAry[i]) || !isKern(iAry[i-1], iAry[i])){
                isSandbox = false;
            }
        }
        return isSandbox;
    }*/
      public static Boolean isSandbox(int[][] iAry) {
        Boolean isSandbox = true;
        int lange = iAry.length;
        if(lange%2==0) return null;
        if(!isSymmetrisch(iAry[0])) return false;
        for (int i=0; i < (lange)/2; i++){
            if(!isKern(iAry[i+1], iAry[i]) || !Arrays.equals(iAry[i], iAry[lange-1-i])){
                return false;
            }
        }
        return isSandbox;
    }



    /**
     * Generiert ein int[][] Array mit einem Sanduhrmuster.
     * @param n Anzahl der Elemente des int[][] Arrays
     * @param max Länge der längsten Zeile
     * @return int[][] Array mit Sanduhrmuster oder null, wenn n gerade oder max < n
     */



    public static int[][] genSandbox(int n, int max) {
        if(n%2==0 || max<n){
            return null;
        }
        int[][] ary = new int[n][];
        ary[n-1] = genSymmetric(max);
        ary[0] = ary[n-1].clone();
        int mitte = (n+1)/2;
        for(int j=1; j<mitte; j++){
            ary[j] = Arrays.copyOfRange(ary[j-1], 1, max-1);
            ary[n-1-j] = ary[j].clone();
        }
        return ary;
    }

    /**
     * Generiert ein symmetrisches int[] Array der Länge n. n kann gerade und ungerade sein.
     * @param n Anzahl der Elemente des int[] Arrays
     * @return symmetrisches int[] Array der Länge n.
     */
    private static int[] genSymmetric(int n) {
        int[] ary = new int[n];
        int mitte = (n+1)/2;
        for(int i=0; i<mitte; i++){
            int value = rand.nextInt(Integer.MAX_VALUE) + 1 - Integer.MAX_VALUE ;
            ary[i] = value;
            ary[n-1-i] = value;
        }
        return ary;
    }


    public static void main(String[] args) {
        ArrayPatterns.trenn(1);
        int[] ary1 = new int[]{1,2,3};
        int[] ary2 = new int[]{1,1,2,3,4};
        int[] ary3 = new int[]{1,1,2,3};
        int[] ary4 = new int[]{1,1,3,1,1};
        int[] ary5 = new int[]{1,2,1};
        int[] ary6 = new int[]{};
        int[] ary7 = new int[]{1,2,2,1};
        int[] ary8 = new int[]{1};
        int[] ary9 = new int[]{1,3,3,4};
        System.out.println(isKern(ary1, ary2));
        System.out.println(isKern(ary1, ary3));
        ArrayPatterns.trenn(2);
        System.out.println(isSymmetrisch(ary4));
        System.out.println(isSymmetrisch(ary2));
        System.out.println(isSymmetrisch(ary5));
        System.out.println(isSymmetrisch(ary6));
        System.out.println(isSymmetrisch(ary7));
        System.out.println(isSymmetrisch(ary8));
        System.out.println(isSymmetrisch(ary9));
        ArrayPatterns.trenn(3);
        int[][] ary10 = new int[][] {{3,2,1,2,3}, {2,1,2}, {1}, {2,1,2}, {3,2,1,2,3}};
        int[][] ary11 = new int[][]{{3,2,2,3}, {2,2}, {3,2,2,3}};
        int[][] ary12 = new int[][] {{7,3,2,1,2,3,7}, {3,2,1,2,3}, {2,1,2}, {1}, {2,1,2}, {3,2,1,2,3}, {7,3,2,1,2,3,7}};
        int[][] ary13 = new int[][] {{1,2,3}, {2}, {1,2,3}};
        int[][] ary14 = new int[][]{{3,2,1,2,3}, {2,1,2}, {2,1,2}, {3,2,1, 2,3}};
        int[][] ary15 = new int[][] {{3,2,1,2,3}, {2,7,2}, {3,2,1,2,3}};
        int[][] ary16 = new int[][] {{7,3,2,1,2,3,7}, {3,2,1,2,3}, {2,1,2}, {3,2,1,2,3}, {7,3,2,1,2,3,7}};
        int[][] ary17 = new int[][] {{1,2,3,2,1}, {2,3,2}, {3}, {2,3,2}, {1,2,3,2,1}};
        System.out.println(isSandbox(ary10));
        System.out.println(isSandbox(ary11));
        System.out.println(isSandbox(ary12));
        System.out.println(isSandbox(ary16));
        System.out.println(isSandbox(ary17));
        System.out.println(isSandbox(ary13));
        System.out.println(isSandbox(ary14));
        System.out.println(isSandbox(ary15));
        ArrayPatterns.trenn(4);
        ArrayPatterns.printO(genSandbox(3,5));
        ArrayPatterns.printO(genSandbox(5, 6));
        ArrayPatterns.printO(genSandbox(4, 10));
        ArrayPatterns.trenn(5);
        System.out.println(Arrays.toString(genSymmetric(8)));
        System.out.println(Arrays.toString(genSymmetric(7)));
    }
// https://www.baeldung.com/java-invert-array
}
