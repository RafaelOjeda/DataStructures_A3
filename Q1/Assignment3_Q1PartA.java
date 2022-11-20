
/************************************************************** 
          Purpose/Description: Radix Sort algorithm. It prints out an error when there is an odd integer 
          Author’s Panther ID:  6334830
          Certification:  
  I hereby certify that this work is my own and none of it is the work of  
  any other person.  
        **************************************************************/
import java.util.*;

class Assignment3_Q1PartA {

    // Returns max num of array to be used in radixSort
    public static int getMaxNum(int arr[]) {
        int max_num = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max_num) {
                max_num = arr[i];
            }
        }

        return max_num;
    }

    public static void radixSort(int[] arr) {
        final int MAX_NUM = getMaxNum(arr);
        int[] buckets = new int[10];
        int[] updatedArr = new int[arr.length];
        int current_digit; // Current_digit will hold every digit that is being accounted for to check for
                           // odd numbers

        String abortMsg = "*** Abort *** the input has at least one key with odd digits";

        for (int max_num_iterator = 1; MAX_NUM / max_num_iterator > 0; max_num_iterator *= 10) { // This loop will only
                                                                                                 // loop once for every
                                                                                                 // int in the MAX_NUM;
            Arrays.fill(buckets, 0); // Buckets are reset to 0 every iteration

            // Adds the current numbers place from the outer loop to the buckets[]
            for (int i = 0; i < arr.length; i++) {
                current_digit = (arr[i] / max_num_iterator) % 10;

                // Odd num digit check
                if (current_digit % 2 == 1) {
                    System.out.println(abortMsg);
                    return;
                }

                buckets[current_digit]++;
            }

            // The value of the count[] is the starting index of the num in the index of
            // count[] from right to left
            // When inserting remove one so the star
            for (int i = 1; i < buckets.length; i++) {
                buckets[i] = buckets[i] + buckets[i - 1];
            }

            // Adds nums to updatedArr[] and makes it sorted
            // Updates buckets[] in case of following nums with same num
            for (int i = arr.length - 1; i >= 0; i--) {
                updatedArr[buckets[(arr[i] / max_num_iterator) % 10] - 1] = arr[i];
                buckets[(arr[i] / max_num_iterator) % 10]--;
            }

            // copy all values from updatedArr to arr.
            for (int i = 0; i < arr.length; i++) {
                arr[i] = updatedArr[i];
            }
        }

        // Prints out ordered list
        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    // Main driver method
    public static void main(String[] args) { // Main method used for testing.
        int[] noError = { 222, 8, 8, 80, 6, 2, 4, 48, 4, 42, 26, 22, 20 }; // Prints out just fine.
        radixSort(noError);

        int[] error = { 10, 22, 46, 48, 202 }; // Prints out odd number error
        radixSort(error);
    }

}