import java.util.Arrays;

public class RadixSortWords {

  public static void main(String[] args) {
    // Predefined input
    String[] words = { "hello", "hell", "cat", "fly", "jump", "cupcake", "cup", "cake" };

    System.out.println("Example: " + Arrays.toString(words));

    // Sort the words by calling the predefined method
    radixSortWords(words);

    // Print out the final sorted array words
    System.out.print("Sorted Words: ");
    for (int i = 0; i < words.length; i++) {
      System.out.print(words[i] + " ");
    }
  }

  public static void radixSortWords(String[] arr) {
    // Create two 2D arrays
    String[][] Array1 = new String [26][]; // Outer array
    // Inner array
    for (int i = 0; i < 26; i++) {
      Array1[i] = new String[arr.length];
    }
    String[][] Array2 = new String [26][]; // Outer array
    // Inner array
    for (int i = 0; i < 26; i++) {
      Array2[i] = new String[arr.length];
    }

    // Create two arrays for each alphabet bucket in each 2D array to track number of counts in each bucket
    int[] count1 = new int [26];
    int[] count2 = new int [26];

    // Print out initialisation buckets
    System.out.println("\n1. Initialisation:");
    System.out.println("Array 1:");
    // Print the buckets in format by calling predefined method
    printBuckets(Array1, count1, "Array 1");
    System.out.println("Array 2:");
    printBuckets(Array2, count2, "Array2");

    // Finds the longest string length
    int maxLength = 0;
    for (int i = 0; i < arr.length; i++) {
      maxLength = Math.max(maxLength, arr[i].length());
    }

    // Print the maximum length of word
    System.out.println("Maximum Length of Word: "+ maxLength);
    System.out.println("-".repeat(100) + "\n");

    // Print the iterations
    System.out.println("2. Iteration: ");

    // Start iterating from the LSF, right most alphabet of the word
    for (int pass = maxLength - 1; pass >= 0; pass-- ) {
      if ((maxLength - pass - 1) % 2 == 0) {
        // Distribute elements to alphabet buckets
        for (int i = 0; i < arr.length; i++) {
          int letterBucket = getCharIndex(arr[i], pass);
          Array1[letterBucket][count1[letterBucket]++] = arr[i];
        }

        System.out.println("Iteration of character at position " + pass + ": ");
        printBuckets(Array1, count1, "Array1");

        // Collect elements from buckets back into array
        int index = 0;
        for (int i = 0; i < 26; i++) {
          for (int j = 0; j < count1[i]; j++) {
            arr[index++] = Array1[i][j];
          }
          count1[i] = 0;
        }

        // Print the updated array after collecting from alphabet buckets
        System.out.print("Current Array 1 List: ");
        for (int i = 0; i < arr.length; i++) {
          System.out.print(arr[i] + " ");
        }
        System.out.println("\n");
      }
      else {
        // Distribute elements to alphabet buckets
        for (int i = 0; i < arr.length; i++) {
          int letterBucket = getCharIndex(arr[i], pass);
          Array2[letterBucket][count2[letterBucket]++] = arr[i];
        }

        System.out.println("Iteration at character at position " + pass +": ");
        printBuckets(Array2, count2, "Array2");

        // Collect elements from buckets back into array
        int index = 0;
        for (int i = 0; i < 26; i++) {
          for (int j = 0; j < count2[i]; j++) {
            arr[index++] = Array2[i][j];
          }
          count2[i] = 0;
        }

        // Print the updated array after collecting from buckets
        System.out.print("Current Array 2 List: ");
        for (String word : arr) {
          System.out.print(word + " ");
        }
        System.out.println("\n");
      }
      System.out.println("-".repeat(100) + "\n");
    }
  }

  // Get the position of the letter in the alphabet (a = 0, b = 1, ..., z = 25)
  // Pads short words by treating missing characters as 'a' (index 0)
  public static int getCharIndex(String word, int position) {
    if (position < word.length()) {
      return word.charAt(position) - 'a';
    }
    else {
      return 0;
    }
  }

  // Helper function to print the alphabet buckets in format, print array contents vertically (one bucket per line)
  private static void printBuckets(String[][] array, int[] count, String arrayName) {
    System.out.println("\n---" + arrayName + " Alphabet Buckets ---");

    for (int i = 0; i < 26; i++) {
      char bucketLabel = (char) ('a' + i);
      System.out.print(bucketLabel + ": ");

      for (int j = 0; j < count[i]; j++) {
        System.out.print(array[i][j] + " ");
      }

      System.out.println();
    }
    System.out.println();
  }
}