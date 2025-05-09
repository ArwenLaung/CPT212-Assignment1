import java.util.Arrays;

public class RadixSortWords_Counter {
  // Counters initialisation
  static int assignmentCount = 0;
  static int comparisonCount = 0;
  static int arithmeticCount = 0;
  static int arrayLookupCount = 0;
  static int methodCallCount = 0;
  static int returnCount = 0;

  public static void main(String[] args) {
    // Predefined input
    String[] words = { "hello", "hell", "cat", "fly", "jump", "cupcake", "cup", "cake" };
    assignmentCount++; // Assignment of predefined input to array words

    System.out.println("Example: " + Arrays.toString(words));
    
    // Sort the words by calling the predefined method
    radix_sort(words);
    methodCallCount++; // Call radix_sort() method

    // Print out the final sorted array words
    System.out.print("Sorted Words: ");
    assignmentCount++; // Assignment of i = 0
    for (int i = 0; i < words.length; i++) {
      comparisonCount++; // Comparison of i < words.length
      System.out.print(words[i] + " ");
      arrayLookupCount++; // Access words[i]
      arithmeticCount++; // Increment of i
      assignmentCount++; // Assignment of i after increment
    }
    comparisonCount++; // Last comparison of i < words.length, cannot enter the loop

    System.out.print("\n");

    // Print out the counts of each primitive operation
    System.out.println("-".repeat(80));
    System.out.println("Assignment Count = " + assignmentCount);
    System.out.println("Comparison Count = " + comparisonCount);
    System.out.println("Arithmetic Count = " + arithmeticCount);
    System.out.println("Array Lookup Count = " + arrayLookupCount);
    System.out.println("Method Call Count = " + methodCallCount);
  }

  public static void radix_sort(String[] arr) {
    // Create two 2D arrays
    String[][] Array1 = new String [26][]; // Outer array
    assignmentCount += 2; // Assignment of the outer array and initial assignment of i = 0
    // Inner array
    for (int i = 0; i < 26; i++) {
        Array1[i] = new String[arr.length];
        assignmentCount++; // Assignment of each row of 2D array Array1
        arrayLookupCount++; // Array lookup for Array1[i]
        arithmeticCount++; // Increment of i
    }
    comparisonCount++; // Last comparison of i < 26, cannot enter the loop
    String[][] Array2 = new String [26][];
    assignmentCount += 2; // Assignment of each row of 2D array Array2
    for (int i = 0; i < 26; i++) {
      Array2[i] = new String[arr.length];
      assignmentCount++; // Array lookup for Array2[i]
      arithmeticCount++; // Increment of i
    }
    comparisonCount++; // Last comparison of i < 26, cannot enter the loop

    // Create two arrays for each alphabet bucket in each 2D array to track number of counts in each bucket
    int[] count1 = new int [26];
    int[] count2 = new int [26];
    assignmentCount += 2; // Initiallise all elements of the array count1 and array count2

    // Print out initialisation buckets
    System.out.println("\n1. Initialisation:");
    System.out.println("Array 1:");
    // Print the buckets in format by calling predefined method
    printBuckets(Array1, count1, "Array 1");
    System.out.println("Array 2:");
    printBuckets(Array2, count2, "Array2");
    methodCallCount += 2; // Call printBuckets() twice

    // Finds the longest string length
    int maxLength = 0;
    assignmentCount += 2; // Initialise maxLength = 0 and i = 0
    for (int i = 0; i < arr.length; i++) {
      comparisonCount++; // Comparison of i < arr.length
      maxLength = Math.max(maxLength, arr[i].length());
      arrayLookupCount++; // Access arr[i]
      arithmeticCount++; // Increment of i
      assignmentCount += 2; // Assignments of maxLength and i after increment
    }
    comparisonCount++; // Last comparison of i < arr.length, cannot enter the loop

    // Print the maximum length of word
    System.out.println("Maximum Length of Word: "+ maxLength);
    System.out.println("-".repeat(100) + "\n");

    // Print the iterations
    System.out.println("2. Iteration: ");

    assignmentCount++; // Initialise pass = maxLength
    // Start iterating from the LSF, right most alphabet of the word
    for (int pass = maxLength - 1; pass >= 0; pass-- ) {
      assignmentCount++; // Assignment of pass after decrement
      comparisonCount += 2; // Compares if pass >= 0 and if (maxLength - pass - 1) % 2 == 0
      arithmeticCount += 3; // maxLength - pass, maxLength - pass - 1, (maxLength - pass - 1) % 2
      if ((maxLength - pass - 1) % 2 == 0) {
        assignmentCount++; // Initialise i = 0
        for (int i = 0; i < arr.length; i++) {
          assignmentCount += 3; // Assignments of i after increment, letterBucket and Array1[letterBucket][count1[letterBucket]++]
          comparisonCount++; // Compares if i < arr.length
          int letterBucket = getCharIndex(arr[i], pass);
          methodCallCount++; // Call getCharIndex()
          Array1[letterBucket][count1[letterBucket]++] = arr[i];
          arrayLookupCount += 5; // Access array arr[i] twice, Array1[letterBucket], count1[letterBucket] and Array1[letterBucket][count1[letterBucket]]
          arithmeticCount += 2; //Increments of i and count1[letterBucket]
        }
        comparisonCount++; // Last comparison of i, cannot enter the loop

        System.out.println("Iteration of character at position " + pass + ": ");
        printBuckets(Array1, count1, "Array1");
        methodCallCount++; // Call printBuckets()

        // Collect elements from buckets back into array
        int index = 0;
        assignmentCount += 2; // Assignment of index and initialise i = 0
        for (int i = 0; i < 26; i++) {
          assignmentCount += 2; // Assignment of i after increment and initialise j = 0
          comparisonCount++; // Compares if i < 26
          for (int j = 0; j < count1[i]; j++) {
            assignmentCount += 2; // Assignment of j after increment and arr[index++]
            arr[index++] = Array1[i][j];
            arrayLookupCount += 4; // Access count1[i], arr[index], Array1[i] and Array1[i][j]
            arithmeticCount += 2; // Increments of j and arr[index++]
          }
          count1[i] = 0;
          arrayLookupCount++; // Access array count1[i]
          assignmentCount++; // Assignment of count1[i] = 0
          arithmeticCount++; // Increment of j
        }
        arithmeticCount++; // Increment of i

        // Print the updated array after collecting from alphabet buckets
        System.out.print("Current Array 1 List: ");
        assignmentCount++; // Initialise i = 0
        for (int i = 0; i < arr.length; i++) {
          assignmentCount++; // Assignment of i after increment
          comparisonCount++; // Compares if i < arr.length
          System.out.print(arr[i] + " ");
          arrayLookupCount++; // Access array arr[i]
          arithmeticCount++; // Increment of i
        }
        comparisonCount++; // Last comparison of i, cannot enter the loop
        System.out.println("\n");
      }
      else {
        // Distribute elements to alphabet buckets
        assignmentCount++; // Initialise i = 0
        for (int i = 0; i < arr.length; i++) {
          assignmentCount += 3; // Assignment of i after increment, letterBucket and Array2[letterBucket][count2[letterBucket]++]
          comparisonCount++; // Compares if i < arr.length
          int letterBucket = getCharIndex(arr[i], pass);
          methodCallCount++; // Call getCharIndex()
          Array2[letterBucket][count2[letterBucket]++] = arr[i];
          arrayLookupCount += 4; // Access array arr[i], Array2[letterBucket], count2[letterBucket] and Array2[letterBucket][count2[letterBucket]]
          arithmeticCount += 2; // Increments of i and count2[letterBucket]
        }
        comparisonCount++; // Last comparison of i < arr.length, cannot enter the loop

        System.out.println("Iteration at character at position " + pass +": ");
        printBuckets(Array2, count2, "Array2");
        methodCallCount++; // Call printBuckets()

        // Collect elements from buckets back into array
        int index = 0;
        assignmentCount += 2; // Assignment of index and initialise i = 0
        for (int i = 0; i < 26; i++) {
          assignmentCount += 3; // Assignments of i after increment, j = 0 and count2[i]
          comparisonCount++; // Compares if i < 26
          for (int j = 0; j < count2[i]; j++) {
            assignmentCount += 2; // Assignments of j after increment and arr[index++]
            arr[index++] = Array2[i][j];
            arrayLookupCount += 4; // Access array count2[i], arr[index++], Array2[i] and Array2[i][j]
            arithmeticCount += 2; // Increments of index and j
          }
          count2[i] = 0;
          arrayLookupCount++; // Access array count2[i]
          arithmeticCount++; // Increment of i
          comparisonCount++; // Last comaprison of j < count2[i], cannot enter the loop
        }
        comparisonCount++; // Last comparison of i < 26, cannot enter the loop

        System.out.print("Current Array 2 List: ");
        assignmentCount++; // Initialise i = 0
        for (int i = 0; i < arr.length; i++) {
          assignmentCount++; // Assignment of i after increment
          System.out.print(arr[i] + " ");
          arrayLookupCount++; // Access array arr[i]
          arithmeticCount++; // Increment of i
        }
        comparisonCount++; // Last comparison of i < arr.length, cannot enter the loop
        System.out.println("\n");
      }
      System.out.println("-".repeat(100) + "\n");
      arithmeticCount++; // Decrement of pass
    }
    comparisonCount++; // Last comparison of pass, cannot enter the loop
  }

  // Get the position of the letter in the alphabet (a = 0, b = 1, ..., z = 25)
  // Pads short words by treating missing characters as 'a' (index 0)
  public static int getCharIndex(String word, int position) {
    comparisonCount++; // Comparison of position < word.length()
    if (position < word.length()) {
      arithmeticCount++; // word.charAt(position) - 'a'
      returnCount++;
      return word.charAt(position) - 'a';
    }
    else {
      returnCount++;
      return 0;
    }
  }

  // Helper function to print the alphabet buckets in format, print array contents vertically (one bucket per line)
  private static void printBuckets(String[][] array, int[] count, String arrayName) {
    System.out.println("\n---" + arrayName + " Alphabet Buckets ---");

    assignmentCount++; // Initialise i = 0
    for (int i = 0; i < 26; i++) {
      assignmentCount += 2; // Assignments of i after increment and bucketLabel
      comparisonCount++; // Comparison of i < 26
      char bucketLabel = (char) ('a' + i);
      arithmeticCount++; // 'a' + i
      System.out.print(bucketLabel + ": ");

      assignmentCount++; // Initialise j = 0
      for (int j = 0; j < count[i]; j++) {
        assignmentCount++; // Assignment of j after increment
        comparisonCount++; // Comparison of j < count[i]
        System.out.print(array[i][j] + " ");
        arrayLookupCount += 2; // Access array array[i] and array[i][j]
        arithmeticCount++; // Increment of j
      }
      comparisonCount++; // Last comparison of j < count[i], cannot enter the loop

      System.out.println();
      arithmeticCount++; // Increment of i
    }
    comparisonCount++; // Last comparison of i < 26, cannot enter the loop
    System.out.println();
  }
}