import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RadixSortWords_Analysis {
  // Counter for primitive operations
  private static long operationCount = 0;

  public static void main(String[] args) throws IOException {
    // Create an array that contains sizes of input
    int[] inputSizes = {100, 500, 1000, 5000, 10000, 15000};

    // Run multiple trials for each size of input to get average
    int numberTrials = 10; 

    // Create a CSV file to store experiment results for graph plotting purpose
    FileWriter csvResults = new FileWriter("RadixSortWords_Analysis.csv");
    csvResults.append("Array Size,Operations,Operations/InputSize,Operations/(MaxAlphabets*InputSize)\n"); // First row of the CSV file

    // Print table header to display in the terminal
    System.out.println("-".repeat(101));
    System.out.printf("| %-10s | %-18s | %-21s | %-36s |\n", "Input Size", "Average Operations", "Operations/Input Size", "Operations/(Input Size * Max Alphabets)");
    System.out.println("|" + "-".repeat(12) + "|" + "-".repeat(20) + "|" + "-".repeat(23) + "|" + "-".repeat(41) + "|");


    // For each size of input in array inputSizes...
    for (int i = 0; i < inputSizes.length; i++) {
      long totalOperations = 0;
      int totalMaxLength = 0;
      
      // Perform 10 times of sorting trials on this size of input
      for (int trial = 0; trial < numberTrials; trial++) {
          // Generate random array of this size, with the inputs ranging from 2 to 10 letters
          String[] wordArray = generateRandomWordArray(inputSizes[i], 2, 8); // Generate words up to 10 letters
          
          // Find maximum word length in this array
          int maxLength = findMaxLength(wordArray);

          // Accumulate the total maximum digits of the arrays of the trials
          totalMaxLength += maxLength;

          // Reset static class-level variable operationCount everytime before sorting next array
          operationCount = 0;
          
          // Sort and count operations
          radixSortWords(wordArray);
          
          // Accumulate the total operations of the trials
          totalOperations += operationCount;
      }
      
      // Calculate averages of operations and digits
      long averageOperations = totalOperations / numberTrials;
      double averageAlphabets = (double) totalMaxLength / numberTrials;
      
      // Write results to CSV file
      csvResults.append(String.format("%d,%d,%.2f,%.2f\n", 
          inputSizes[i], averageOperations, (double) averageOperations / inputSizes[i], 
          (double) averageOperations / (inputSizes[i] * averageAlphabets)));

      // Printing each row with aligned columns
      System.out.printf("| %-10d | %-18d | %-21.2f | %-39.2f |\n", 
      inputSizes[i], 
      averageOperations, 
      (double) averageOperations / inputSizes[i], 
      (double) (averageOperations / (inputSizes[i] * averageAlphabets)));
    }

    System.out.println("-".repeat(101));
    System.out.println("");
    
    // Force any unsaved data to be written immediately to the file
    csvResults.flush(); 

    // Close CSV file and release resources
    csvResults.close(); 

    System.out.println("Data saved to RadixSortWords_Analysis.csv");
  }

  public static void radixSortWords(String[] arr) {
    String[][] Array1 = new String [26][arr.length];
    operationCount++; // Assignment to Array1

    String[][] Array2 = new String [26][arr.length]; 
    operationCount++; // Assignment to Array2

    int[] count1 = new int [26];
    operationCount++; // Assignment to count1

    int[] count2 = new int [26];
    operationCount++; // Assignment to count2

    int maxLength = findMaxLength(arr);
    operationCount += 2; // Method call of findMaxLength(), assignment of maxLength

    for (int pass = maxLength - 1; pass >= 0; pass-- ) {
      operationCount += 4; // 2 subtractions, modulus, comparison
      if ((maxLength - pass - 1) % 2 == 0) {
        for (int i = 0; i < arr.length; i++) {
          int letterBucket = getCharIndex(arr[i], pass);
          operationCount += 3; // Array lookup for arr[i], method call of getCharIndex(), assignment of letterBucket
          Array1[letterBucket][count1[letterBucket]++] = arr[i];
          operationCount += 6; // Array lookups for count1[letterBucket], Array1[letterBucket], Array1[letterBucket][count1[letterBucket]], arr[i], assignment of Array1[letterBucket][count1[letterBucket]++], increment of count1[letterBucket]
          operationCount += 3; // Comparison of i < arr.length, increment of i, assignment of i
        }
        operationCount += 2; // Initialisation of i, last comparison of i < arr.length

        int index = 0;
        operationCount++; // Assignment of index
        for (int i = 0; i < 26; i++) {
          for (int j = 0; j < count1[i]; j++) {
            arr[index++] = Array1[i][j];
            operationCount += 8; // Comparison of j < count1[i], increment of j, assignment of j, array lookups for Array1[i], Array1[i][j], arr[index], increment of arr[index++], assignment of arr[index++]
          }
          operationCount += 2; // Initialisation of j, last comparison of j < count1[i]
          count1[i] = 0;
          operationCount += 5; // Comparison of i < 26, increment of i, assignment of i, array lookup for count1[i], assignment of count1[i]
        }
        operationCount += 2; // Initialisation of i, last comparison of i < 26
      }
      else {
        for (int i = 0; i < arr.length; i++) {
          int letterBucket = getCharIndex(arr[i], pass);
          operationCount += 3; // Array lookup for arr[i], method call of getCharIndex(), assingment of letterBucket
          Array2[letterBucket][count2[letterBucket]++] = arr[i];
          operationCount += 6; // Array lookups for arr[i], count2[letterBucket], Array2[letterBucket], Array2[letterBucket][count2[letterBucket]++], assignment of Array2[letterBucket][count2[letterBucket]++], increment of count2[letterBucket]
          operationCount += 3; // Comparison of r < arr.length, increment of i, assignment of i
        }
        operationCount += 2; // Initialisation of i, last comparison of i < arr.length

        int index = 0;
        operationCount++; // Assignment of index
        for (int i = 0; i < 26; i++) {
          for (int j = 0; j < count2[i]; j++) {
            arr[index++] = Array2[i][j];
            operationCount += 8; // Comparison of j < count2[i], increment of j, assignment of j, array lookups for Array2[i], Array2[i][j], arr[index], increment of arr[index++], assignment of arr[index++]
          }
          operationCount += 2; // Initialisation of j, last comparison of j < count2[i]
          count2[i] = 0;
          operationCount += 5; // Comparison of i < 26, increment of i, assignment of i, array lookup for count2[i], assignment of count2[i]
        }
        operationCount += 2; // Initialisation of i, last comparison of i < 26
      }
      operationCount += 3; // Comparison of pass >= 0, decrement of pass, assignment of pass
    }
    operationCount += 2; // Initialisation of pass, decrement of pass
  }

  // Method to generate random word array of specified size
  public static String[] generateRandomWordArray(int size, int minLength, int maxLength) {
    String[] array = new String[size];
    Random random = new Random();
    
    // Lower characters to generate random words
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    
    for (int i = 0; i < size; i++) {
      // Determine random length for this word
      int wordLength = random.nextInt(maxLength - minLength + 1) + minLength;
      
      // Create a char array for the word
      char[] wordChars = new char[wordLength];
      
      // Fill with random letters
      for (int j = 0; j < wordLength; j++) {
          wordChars[j] = alphabet[random.nextInt(26)];
      }
      
      // Convert to string and add to array
      array[i] = new String(wordChars);
    }
    
    return array;
  }

  public static int findMaxLength(String[] arr) {
    int maxLength = 0;
    operationCount++;
    for (int i = 0; i < arr.length; i++) {
      operationCount += 2; // Array lookup for arr[i],comparison of arr[i].length() > maxLength
      if (arr[i].length() > maxLength) {
          maxLength = arr[i].length();
          operationCount += 2; // Array lookup for arr[i], assignment of maxLength
      }
      operationCount += 3; // Comparison of i < arr.length, increment of i, assignment of i
    }
    operationCount += 3; // Initialisation of i, last comparison of i < arr.length, return
    return maxLength;
  }

  // Get the position of the letter in the alphabet (a = 0, b = 1, ..., z = 25)
  // Pads short words by treating missing characters as 'a' (index 0)
  public static int getCharIndex(String word, int position) {
    operationCount++; // Comparison of position < word.length()
    if (position < word.length()) {
      operationCount++; // Return
      return word.charAt(position) - 'a';
    }
    else {
      operationCount++; // Return
      return 0;
    }
  }
}