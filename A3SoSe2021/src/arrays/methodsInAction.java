package arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

public class methodsInAction {

    public static void main(String[] args) {

        // ArrayPatterns
        /////////////////////////////////////////////////
        ArrayPatterns ap = new ArrayPatterns();

        System.out.println("2.1.1 ------ genShort");
        System.out.println(ap.genShort());

        System.out.println("2.1.2 ------ isShort");
        System.out.print("Short.MAX_VALUE: ");
        System.out.println(ap.isShort(Short.MAX_VALUE));
        System.out.print("Integer.MAX_VALUE: ");
        System.out.println(ap.isShort(Integer.MAX_VALUE));

        System.out.println("2.1.3 ------ genIrregShortArray(3, 4)");
        short[][] irregShortAry = ap.genIrregShortArray(3, 4);
        System.out.println(Arrays.deepToString(irregShortAry));

        System.out.println("2.1.4 ------ genRegShortArray(3, 4)");
        System.out.println(Arrays.deepToString(ap.genRegShortArray(3, 4)));

        System.out.println("2.1.5 ------ inListFlattenReverse(irregShortAry)");
        System.out.println(ap.inListFlattenReverse(irregShortAry));

        System.out.println("2.1.6 ------ sizeOf(irregShortAry)");
        System.out.println(ap.sizeOf(irregShortAry));

        System.out.println("2.1.7 ------ inArrayFlattenReverse(irregShortAry)");
        System.out.println(Arrays.toString(ap.inArrayFlattenReverse(irregShortAry)));

        System.out.println("2.1.8 ------ printOpposites(short1D)");
        short[] short1DE = {1, 2, 3, 4};
        short[] short1DO = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(short1DE));
        ap.printOpposites(short1DE);
        System.out.println(Arrays.toString(short1DO));
        ap.printOpposites(short1DO);

        System.out.println("2.1.9 ------ printOpposites(short2D)");
        short[][] short2DE = {{1, 2, 3}, {4, 5, 6}};
        short[][] short2DO = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.deepToString(short2DE));
        ap.printOpposites(short2DE);
        System.out.println(Arrays.deepToString(short2DO));
        ap.printOpposites(short2DO);

        System.out.println("2.1.10 ------ kopiereHaelfte1D(short2D)");
        short[][] short2D2E = {{1, 2, 3, 4}, {5, 6, 7, 8}};
        short[][] short2D2O = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};
        short[][] short2Dirr = {{1, 2, 3}, {4, 5, 6, 7}, {8, 8, 10, 11, 12}};
        System.out.println(Arrays.deepToString(short2D2E));
        System.out.println(Arrays.deepToString(ap.kopiereHaelfte1D(short2D2E)));
        System.out.println(Arrays.deepToString(short2D2O));
        System.out.println(Arrays.deepToString(ap.kopiereHaelfte1D(short2D2O)));
        System.out.println(Arrays.deepToString(short2Dirr));
        System.out.println(Arrays.deepToString(ap.kopiereHaelfte1D(short2Dirr)));

        System.out.println("2.1.11 ------ kopiereAbHaelfte1D(short2D)");
        System.out.println(Arrays.deepToString(short2D2E));
        System.out.println(Arrays.deepToString(ap.kopiereAbHaelfte1D(short2D2E)));
        System.out.println(Arrays.deepToString(short2D2O));
        System.out.println(Arrays.deepToString(ap.kopiereAbHaelfte1D(short2D2O)));
        System.out.println(Arrays.deepToString(short2Dirr));
        System.out.println(Arrays.deepToString(ap.kopiereAbHaelfte1D(short2Dirr)));

        System.out.println("2.1.12 ------ reduziere(short2D)");
        short[][] short2D3E = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        short[][] short2D3O = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println(Arrays.deepToString(short2D3E));
        System.out.println(Arrays.deepToString(ap.reduziere(short2D3E)));
        System.out.println(Arrays.deepToString(short2D3O));
        System.out.println(Arrays.deepToString(ap.reduziere(short2D3O)));
        /////////////////////////////////////////////////

        // Sandbox
        /////////////////////////////////////////////////
        Sandbox sb = new Sandbox();

        int[] ary1D5S = {3, 2, 1, 2, 3};
        int[] ary1D3S = {2, 1, 2};
        int[] ary1D1 = {1};
        int[] ary1D2US = {1, 2};
        int[] ary1D4US = {1, 2, 3, 4};
        int[] ary1D5US = {1, 2, 3, 4, 5, 6};
        int[][] sandbox0 = {{3, 2, 1, 2, 3}, {2, 1, 2}, {1}, {2, 1, 2}, {3, 2, 1, 2, 3}};
        int[][] sandbox1 = {{3, 2, 2, 3}, {2, 2}, {3, 2, 2, 3}};
        int[][] sandbox2 = {{7, 3, 2, 1, 2, 3, 7}, {3, 2, 1, 2, 3}, {2, 1, 2}, {1}, {2, 1, 2}, {3, 2, 1, 2, 3}, {7, 3, 2, 1, 2, 3, 7}};
        int[][] notsandbox0 = {{1, 2, 3}, {2}, {1, 2, 3}};
        int[][] notsandbox1 = {{3, 2, 1, 2, 3}, {2, 1, 2}, {2, 1, 2}, {3, 2, 1, 2, 3}};


        System.out.println("------ 2.2.1 ------ isKern(ary1, ary2)");
        System.out.println(Arrays.toString(ary1D1) + " ⎨ " + Arrays.toString(ary1D3S));
        System.out.println(sb.isKern(ary1D1, ary1D3S));
        System.out.println(Arrays.toString(ary1D3S) + " ⎨ " + Arrays.toString(ary1D5S));
        System.out.println(sb.isKern(ary1D3S, ary1D5S));
        System.out.println(Arrays.toString(ary1D5S) + " ⎨ " + Arrays.toString(ary1D3S));
        System.out.println(sb.isKern(ary1D5S, ary1D3S));

        System.out.println("------ 2.2.2 ------ isSymmetisch(ary12)");
        System.out.println(Arrays.toString(ary1D1));
        System.out.println(sb.isSymmetrisch(ary1D1));
        System.out.println(Arrays.toString(ary1D2US));
        System.out.println(sb.isSymmetrisch(ary1D2US));
        System.out.println(Arrays.toString(ary1D5S));
        System.out.println(sb.isSymmetrisch(ary1D5S));

        System.out.println("------ 2.2.3 ------ isSandbox(int[][])");
        System.out.println(sb.isSandbox(sandbox0));
        System.out.println(sb.isSandbox(sandbox1));
        System.out.println(sb.isSandbox(sandbox2));
        System.out.println(sb.isSandbox(notsandbox0));
        System.out.println(sb.isSandbox(notsandbox1));

        System.out.println("------ 2.2.4 ------ genSandbox(int[][])");
        System.out.println(Arrays.deepToString(sb.genSandbox(5, 5)));
        System.out.println(Arrays.deepToString(sb.genSandbox(5, 6)));
        System.out.println(Arrays.deepToString(sb.genSandbox(4, 1)));
        System.out.println(Arrays.deepToString(sb.genSandbox(3, 1)));

    }
}
