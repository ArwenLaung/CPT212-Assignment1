import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RadixSort_Analysis {
    // Counter for primitive operations
    private static int operationCount = 0;

    public static void main(String[] args) throws IOException {
        // Create an array that contains sizes of input
        int[] inputSizes = {100, 500, 1000, 5000, 10000, 15000};

        // Run multiple trials for each size of input to get average
        int numberTrials = 10; 

        // Create a CSV file to store experiment results for graph plotting purpose
        FileWriter csvResults = new FileWriter("RadixSort_Analysis.csv");
        csvResults.append("Array Size,Operations,Operations/InputSize,Operations/(MaxDigit*InputSize)\n"); // First row of the CSV file

        // Print table header to display in the terminal
        System.out.println("-".repeat(98));
        System.out.printf("| %-10s | %-18s | %-21s | %-32s |\n", "Input Size", "Average Operations", "Operations/Input Size", "Operations/(Input Size * Max Digits)");
        System.out.println("|" + "-".repeat(12) + "|" + "-".repeat(20) + "|" + "-".repeat(23) + "|" + "-".repeat(38) + "|");

        
        // For each size of input in array inputSizes...
        for (int i = 0; i < inputSizes.length; i++) {
            int totalOperations = 0;
            int totalDigits = 0;
            
            // Perform 10 times of sorting trials on this size of input
            for (int trial = 0; trial < numberTrials; trial++) {
                // Generate random array of this size, with the inputs ranging from 1 to 999999
                int[] array = generateRandomArray(inputSizes[i], 1, 99999999); // Generate numbers up to 8 digits
                
                // Find maximum value in this array
                int max = array[0];

                // Find maximum value in this array
                for (int j = 1; j < array.length; j++) {
                    if (array[j] > max) {
                        max = array[j];
                    }
                }
                
                // Determine the maximum digits (k) of the maximum value of the array
                int maxDigits = String.valueOf(max).length();

                // Accumulate the total maximum digits of the arrays of the trials
                totalDigits += maxDigits;

                // Reset static class-level variable operationCount everytime before sorting next array
                operationCount = 0;
                
                // Sort and count operations
                radixSort(array);
                
                // Accumulate the total operations of the trials
                totalOperations += operationCount;
            }
            
            // Calculate averages of operations and digits
            int averageOperations = totalOperations / numberTrials;
            double averageDigits = (double) totalDigits / numberTrials;
            
            // Write results to CSV file
            csvResults.append(String.format("%d,%d,%.2f,%.2f\n", 
                inputSizes[i], averageOperations, (double) averageOperations / inputSizes[i], 
                (double) averageOperations / (inputSizes[i] * averageDigits)));

            // Printing each row with aligned columns
            System.out.printf("| %-10d | %-18d | %-21.2f | %-36.2f |\n", 
            inputSizes[i], 
            averageOperations, 
            (double) averageOperations / inputSizes[i], 
            (double) (averageOperations / (inputSizes[i] * averageDigits)));
        }

        System.out.println("-".repeat(98));
        System.out.println("");
        
        // Force any unsaved data to be written immediately to the file
        csvResults.flush(); 

        // Close CSV file and release resources
        csvResults.close(); 

        System.out.println("Data saved to RadixSort_Analysis.csv");
    }

    public static void radixSort(int[] array) {
        int[][] digitBuckets = new int[10][array.length];
        operationCount++; // Assignment to digitBuckets

        int[] bucketCount = new int[10];
        operationCount++; // Assignment to bucketCount

        int max = array[0];
        operationCount += 2; // Assignment of max, array lookup for array[0]

        for (int i = 1; i < array.length; i++) {
            operationCount += 2; // Comparison of array[i] > max, array lookup for array[i]
            if (array[i] > max) {
                max = array[i];
                operationCount++; // Assignment of max
            }
            operationCount += 3; // Comparison of i < array.length, increment of i, assignment of i
        }
        operationCount += 2; // Initialisation of i, last comparison of i < array.length

        int maxDigits = String.valueOf(max).length();
        operationCount++; // Assignment of maxDigits

        int divisor = 1;
        operationCount++; // Assignment of divisor

        for (int pass = 1; pass <= maxDigits; pass++) {
            for (int index = 0; index < array.length; index++) {
                int digit = (array[index] / divisor) % 10;
                operationCount += 4; // Array lookup for array[index], division, modulus, assignment to digit
                digitBuckets[digit][bucketCount[digit]++] = array[index];
                operationCount += 6; // Array lookups for array[index], bucketCount[digit], digitBuckets[digit], digitBuckets[digit][bucketCount[digit]], increment of bucketCount[digit], assignment of digitBuckets[digit][bucketCount[digit]++]
                operationCount += 3; // Comparison of index < array.length, increment of index, assignment of index
            }
            operationCount += 2; // Initialisation of index, last comparison of index < array.length

            int index = 0;
            operationCount++; // Assignment to index

            for (int i = 0; i < 10; i++) {
                operationCount += 3; // Comparison of i < 10, increment of i, assignment of i

                for (int j = 0; j < bucketCount[i]; j++) {
                    array[index++] = digitBuckets[i][j];
                    operationCount += 9; // Comparison of j < bucketCount[i], increment of j, assignment of j, array lookups for bucketCount[i], digitBuckets[i], digitBuckets[i][j], array[index], increment of array[index++], assignment of array[index++]
                }
                operationCount += 2; // Initialisation of j, last comparison of j < bucketCount[i]
            }
            operationCount += 2; // Initialisation of i, last comparison of i < 10

            for (int i = 0; i < bucketCount.length; i++) {
                bucketCount[i] = 0;
                operationCount += 5; // Comparison of i < bucketCount.length, increment of i, assignment of i, array lookup for bucketCount[i], assignment of bucketCount[0]
            }
            operationCount += 2; // Initialisation of i, last comparison of i < bucketCount.length

            divisor *= 10;
            operationCount += 2; // Multiplication, assignment of divisor

            operationCount += 3; // Comparison of pass <= maxDigits, increment of pass, assignment of pass
        }
        operationCount += 2; // Initialisation of pass, last comparison of pass <= maxDigits
    }  
    
    // Method to generate random array of specified size
    public static int[] generateRandomArray(int size, int min, int max) {
        int[] array = new int[size];
        Random random = new Random();
        
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        
        return array;
    }
}