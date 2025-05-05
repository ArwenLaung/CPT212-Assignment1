import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        // Predefined input
        int[] numbers = { 275, 87, 426, 61, 409, 170, 677, 503 };

        System.out.println("Example: " + Arrays.toString(numbers));
        radixSort(numbers);

        // Print out the final sorted array numbers
        System.out.print("-".repeat(80));
        System.out.print("\nFinal Sorted list: ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }

    public static void radixSort(int[] array) {
        // Define 2D array as buckets for 10 digits
        int[][] digitBuckets = new int[10][];
        for (int i = 0; i < 10; i++) {
            digitBuckets[i] = new int[array.length];
        }
        // Define array for a count for each digit bucket
        int[] bucketCount = new int[10];

        // Initialise the maximum value of the array as first element of the array
        int max = array[0];
        // Find the maximum value of the array
        for (int i = 1; i < array.length; i++) {
          if (array[i] > max) {
            max = array[i];
          }
        }
        // Get the number of digits of the maximum value of the array
        int maxDigits = String.valueOf(max).length();

        // Start by initialising divisor to 1
        int divisor = 1;

        // Iterate through each digit from LSD to MSD
        for (int pass = 1; pass <= maxDigits; pass++) {
        // Place elements into the buckets
            for (int index = 0; index < array.length; index++) {
                int digit = (array[index] / divisor) % 10; // Get the digit starting from LSD
                digitBuckets[digit][bucketCount[digit]++] = array[index]; // Place the number into the correct digit bucket and increase its bucketCount
            }

            // Display current pass in clean format
            System.out.println("\nPass " + pass);
            printBuckets(digitBuckets, bucketCount);
            System.out.println("");

            // Print current pass sorted list
            System.out.print("Pass " + pass + " Sorted List: ");
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < bucketCount[i]; j++) {
                    System.out.print(digitBuckets[i][j] + " ");
                }
            }
            System.out.println("");

            // Collect from buckets back to array or, flatten the 2D array to a single array
            int index = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < bucketCount[i]; j++) {
                    array[index++] = digitBuckets[i][j];
                }
            }

            // Reset bucket counts
            for (int i = 0; i < bucketCount.length; i++) {
                bucketCount[i] = 0;
            }
            
            // Move to next significant digit
            divisor *= 10;
        }
    }

    // Helper function to print the buckets
    public static void printBuckets(int[][] buckets, int[] count) {
        // Print out the header row
        System.out.print("| Digit Bucket |");
        for (int i = 0; i < 10; i++) {
            System.out.printf("  %d  |", i);
        }
        System.out.println();

    // Find maximum number of rows to print
    int maxRows = 0; // Initialise maxRows to 0
    // Iterate through each element in count of each digit bucket to find the bucket with the highest number of count
    for (int index = 0; index < count.length; index++) {
      if (count[index] > maxRows) {
        maxRows = count[index]; // If the count in the digit bucket is higher than previous digit bucket count, then update the current digit bucket count as maxRows
      }
    }
        // Print out each consecutive row after header with format
        for (int row = 0; row < maxRows; row++) {
            System.out.print("|              |");
            for (int col = 0; col < 10; col++) {
                if (row < count[col]) {
                    System.out.printf(" %03d |", buckets[col][row]);
                } else {
                    System.out.print("     |");
                }
            }
            System.out.println();
        }
    }
}