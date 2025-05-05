import java.util.Arrays;

public class RadixSort_Counter {
  static int assignmentCount = 0;
  static int comparisonCount = 0;
  static int arithmeticCount = 0;
  static int arrayLookupCount = 0;
  static int methodCallCount = 0;
  public static void main(String[] args) {
    // Predefined input
    int[] numbers = { 275, 87, 426, 61, 409, 170, 677, 503 };
    assignmentCount++; // Assignment of predefined input to array numbers

    System.out.println("Example: " + Arrays.toString(numbers));
    radixSort(numbers);
    methodCallCount++; // Call radixSort() method

    // Print out the final sorted array numbers
    System.out.print("-".repeat(80));
    System.out.print("\nFinal Sorted list: ");
    assignmentCount++; // Initial assignment i = 0
    for (int i = 0; i < numbers.length; i++) {
      comparisonCount++; // Comparison of i < numbers.length
      System.out.print(numbers[i] + " ");
      arrayLookupCount++; // Lookup of numbers[i]
      arithmeticCount++; // Increment of i
      assignmentCount++; // Assignment of i
    }
    comparisonCount++; // Last comparison of i < numbers.length, cannot enter the loop

    System.out.print("\n");

    // Print out the counters
    System.out.println("-".repeat(80));
    System.out.println("Assignment Count = " + assignmentCount);
    System.out.println("Comparison Count = " + comparisonCount);
    System.out.println("Arithmetic Count = " + arithmeticCount);
    System.out.println("Array Lookup Count = " + arrayLookupCount);
    System.out.println("Method Call Count = " + methodCallCount);
  }

  public static void radixSort(int[] array) {
      // Define 2D array as buckets for 10 digits
      int[][] digitBuckets = new int[10][];
      assignmentCount += 2; // Assignment of the outer array and initial assignment of i = 0
      for (int i = 0; i < 10; i++) {
          digitBuckets[i] = new int[array.length];
          assignmentCount++; // Assignment of each row of 2D array digitBuckets
          arrayLookupCount++; // Array lookup for digitBuckets[i]
          arithmeticCount++; // Increment of i
      }
      comparisonCount++; // Last comparison of i < 10
      // Define array for a count for each digit bucket
      int[] bucketCount = new int[10];
      assignmentCount++; // Initialise all elements of the array bucketCount to 0

      // Initialise the maximum value of the array as first element of the array
      int max = array[0];
      assignmentCount++; // Initialise max to first element of array
      arrayLookupCount++; // Array lookup for array[0]
      // Find the maximum value of the array
      assignmentCount++; // Assignment of i = 1
      for (int i = 1; i < array.length; i++) {
        assignmentCount++; // Assignment of i after increment
        comparisonCount += 2; // Compares if i < array.length and if array[i] > max
        arrayLookupCount++; // Array lookup for array[i]
        if (array[i] > max) {
          max = array[i];
          assignmentCount++; // Assignment of array[i] to max
        }
        arithmeticCount++; // Increment of i
      }
      comparisonCount++; // Last comparison, cannot enter the loop

      // Get the number of digits of the maximum value of the array
      int maxDigits = 0;
      int temp = max;
      assignmentCount += 2; // Assignments of maxDigits and temp
      while (temp != 0) {
        comparisonCount++; // Compares if temp != 0
        temp /= 10;
        maxDigits++;
        arithmeticCount += 2; // temp / 10 and increment of maxDigits
        assignmentCount += 2; // Assignments of temp and updated maxDigits
      }
      comparisonCount++; // Last comparison of temp, cannot enter the loop

      // Start by initialising divisor to 1
      int divisor = 1;
      assignmentCount++; // Assignment of value of divisor

      // Iterate through each digit from LSF to MSF
      assignmentCount++; // Assignment of pass = 1
      for (int pass = 1; pass <= maxDigits; pass++) {
        assignmentCount += 2; // Assignments of pass and index = 0
        comparisonCount++; // Compares if pass <= maxDigits
        // Place elements into the buckets
        for (int index = 0; index < array.length; index++) {
          int digit = (array[index] / divisor) % 10;
          digitBuckets[digit][bucketCount[digit]++] = array[index];
          assignmentCount += 3; // Assignments of index, value of digit and value of digitBuckets[digit][bucketCount[digit]++]
          arrayLookupCount += 5; // Access array[index] (twice), bucketCount[digit], digitBuckets[digit] and digitBuckets[digit][bucketCount[digit]]
          arithmeticCount += 4; // (value / divisor), (value / divisor) % 10, bucketCount[digit]++ and increment of index
          comparisonCount++; // Comparison of index < array.length
        }
        comparisonCount++; // Last comparison of index < array.length, cannot enter the loop

        // Display current pass in clean format
        System.out.println("\nPass " + pass);
        printBuckets(digitBuckets, bucketCount);
        methodCallCount++; // Call printBuckets() method
        System.out.println("");

        // Print current pass sorted list
        System.out.print("Pass " + pass + " Sorted List: ");
        assignmentCount++; // Initialise i = 0
        for (int i = 0; i < 10; i++) {
          assignmentCount += 2; // Assignment of i after increment of i and initialise j = 0
          comparisonCount++; // Compares if i < 10
          for (int j = 0; j < bucketCount[i]; j++) {
            assignmentCount++; // Assignment of j after increment
            comparisonCount++; // Compares if j < bucketCount[i]
            System.out.print(digitBuckets[i][j] + " ");
            arrayLookupCount += 3; // Access bucketCount[i], digitBuckets[i] and digitBuckets[i][j]
            arithmeticCount++; // Increment of j
          }
          comparisonCount++; // Last comparison of j < bucketCount[i], cannot enter the loop
          arithmeticCount++; // Increment of i
        }
        comparisonCount++; // Last comparison of i < 10, cannot enter the loop
        System.out.println("");

        // Collect from buckets back to array or, flatten the 2D array to a single array
        int index = 0;
        assignmentCount += 2; // Assignment of value of index and initialise i = 0
        for (int i = 0; i < 10; i++) {
          assignmentCount += 2; // Assignment of i and initialise j = 0
          comparisonCount++; // Compares if i < 10
          for (int j = 0; j < bucketCount[i]; j++) {
            assignmentCount++; // Assignment of j
            comparisonCount++; // Compares if j < bucketCount[i]
            array[index++] = digitBuckets[i][j];
            assignmentCount++; // Assignment of array[index]
            arrayLookupCount += 4; // Access bucketCount[i], digitBuckets[i], digitBuckets[i][j], and array[index]
            arithmeticCount += 2; // Increment of index and j
          }
          comparisonCount++; // Last comparison of j < bucketCount[i]
          arithmeticCount++; // Increment of i
        }
        comparisonCount++; // Last comparison of i < 10

        // Reset bucket counts
        assignmentCount++; // Initialize i = 0
        for (int i = 0; i < bucketCount.length; i++) {
          assignmentCount++; // Assignment of i after increment
          comparisonCount++; // Compares if i < bucketCount.length
          bucketCount[i] = 0;
          assignmentCount++; // Assignment to bucketCount[i]
          arrayLookupCount++; // Access bucketCount[i]
          arithmeticCount++; // Increment of i
        }
        comparisonCount++; // Last comparison of i < bucketCount.length
        
        // Move to next significant digit
        divisor *= 10;
        assignmentCount++; // Assignment of updated value of divisor
        arithmeticCount += 2; // Increment of pass and multiplication of divisor
      }
      comparisonCount++; // Last comparison of pass <= maxDigits, cannot enter the loop
  }

  // Helper function to print the buckets
  public static void printBuckets(int[][] buckets, int[] count) {
    // Header
    System.out.print("| Digit Bucket |");
    assignmentCount++; // Initialise i = 0
    for (int i = 0; i < 10; i++) {
      assignmentCount++; // Assignment of i after increment
      comparisonCount++; // Compares if i < 10
      System.out.printf("  %d  |", i);
      arithmeticCount++; // Increment of i
    }
    comparisonCount++; // Last comparison of i < 10, cannot enter the loop
    System.out.println();

    // Find maximum number of rows to print
    int maxRows = 0;
    assignmentCount += 2; // Assignment of value of maxRows and intialise index = 0
    for (int index = 0; index < count.length; index++) {
      assignmentCount++; // Assignment of index
      comparisonCount += 2; // Compares if index < count.length and if count[index] > maxRows
      arrayLookupCount++; // Access count[index]
      if (count[index] > maxRows) {
        maxRows = count[index];
        assignmentCount++; // Assignment of maxRows
      }
      arithmeticCount++; // Increment of index
    }
    comparisonCount++; // Last comparison of index < count.length, cannot enter the loop

    // Print out each consecutive row after header
    assignmentCount++; // Initialise row = 0
    for (int row = 0; row < maxRows; row++) {
      assignmentCount++; // Assignment of row after increment of row
      comparisonCount++; // Compares if row < maxRows
      System.out.print("|              |");
      assignmentCount++; // Initialise col = 0
      for (int col = 0; col < 10; col++) {
        assignmentCount++; // Assignment of col after increment of col
        comparisonCount += 2; // Compares if col < 10 and if row < count[col]
        arrayLookupCount++; // Access count[col]
        if (row < count[col]) {
            System.out.printf(" %03d |", buckets[col][row]);
            arrayLookupCount += 2; // Access buckets[col] and buckets[col][row]
        } else {
            System.out.print("     |");
        }
        arithmeticCount++; // Increment of col
      }
      comparisonCount++; // Last comparison of col < 10, cannot enter the loop
      System.out.println();
      arithmeticCount++; // Increment of row
    }
    comparisonCount++; // Last comparison of row < maxRows, cannot enter the loop
  }
}