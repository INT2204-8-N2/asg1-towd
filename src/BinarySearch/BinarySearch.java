package BinarySearch;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {

        String array[] ={"a","a b c","a b c book","abcd","WRTG","FF","ERF","FED","TGH"};
        String search = "a";

        Arrays.sort(array);

        int searchIndex = binarySearch(array,search);
        System.out.println(searchIndex);

    }

    public static int binarySearch(String[] a, String x) {
        int low = 0;
        int high = a.length - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            if (a[mid].compareTo(x) < 0) {
                low = mid + 1;
            } else if (a[mid].compareTo(x) > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

}


