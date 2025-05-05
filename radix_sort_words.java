import java.util.Arrays;

public class radix_sort_words {

  public static void main(String[] args) {
    String[] words = { "hello", "hell", "cat", "fly", "jump" };

    System.out.println("Example: " + Arrays.toString(words));

    radix_sort(words);

    System.out.print("Sorted Words: ");
    for (String word : words) {
      System.out.print(word + " ");
    }
  }

  public static void radix_sort(String[] arr) {
    String[][] Array1 = new String [26][arr.length];
    String[][] Array2 = new String [26][arr.length];

    int[] count1 = new int [26];
    int[] count2 = new int [26];

    System.out.println("\n1. Initialisation:");
    System.out.println("Array 1:");
    printBuckets(Array1, count1, "Array 1");
    System.out.println("Array 2:");
    printBuckets(Array2, count2, "Array2");

    // finds the longest string length
    int maxLength = 0;
    for (String word : arr) {
      maxLength = Math.max(maxLength, word.length());
    }

    System.out.println("Maximum Length of Word: "+ maxLength);
    System.out.println("-".repeat(100) + "\n");

    System.out.println("2. Iteration: ");

    for (int pass = maxLength - 1; pass >= 0; pass-- ) {
      if ((maxLength - pass - 1) % 2 == 0) {
        for (String word : arr) {
          int letterBucket = getCharIndex(word, pass);
          Array1[letterBucket][count1[letterBucket]++] = word;
        }

        System.out.println("Iteration of character at position " + pass + ": ");
        printBuckets(Array1, count1, "Array1");
        System.out.print("Current Array 1 List: ");
        for (String word : arr) {
          System.out.print(word + " ");
        }
        System.out.println("\n");

        int index = 0;
        for (int i = 0; i < 26; i++) {
          for (int j = 0; j < count1[i]; j++) {
            arr[index++] = Array1[i][j];
          }
          count1[i] = 0;
        }
      }
      else {
        for (String word : arr) {
          int letterBucket = getCharIndex(word, pass);
          Array2[letterBucket][count2[letterBucket]++] = word;
        }

        System.out.println("Iteration at character at position " + pass +": ");
        printBuckets(Array2, count2, "Array2");
        System.out.print("Current Array 2 List: ");
        for (String word : arr) {
          System.out.print(word + " ");
        }
        System.out.println("\n");

        int index = 0;
        for (int i = 0; i < 26; i++) {
          for (int j = 0; j < count2[i]; j++) {
            arr[index++] = Array2[i][j];
          }
          count2[i] = 0;
        }
      }
      System.out.println("-".repeat(100) + "\n");
    }
  }

  public static int getCharIndex(String word, int position) {
    if (position < word.length()) {
      return word.charAt(position) - 'a';
    }
    else {
      return 0;
    }
  }

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