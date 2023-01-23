import java.io.*;

/** Methods for performing arithmetic calculations. */
class Arithmetic {
  public static float add(float num1, float num2) {
    return num1 + num2;
  }

  public static float subtract(float num1, float num2) {
    return num1 - num2;
  }

  public static float multiply(float num1, float num2) {
    return num1 * num2;
  }

  public static float division(float num1, float num2) {
    return num1 / num2;
  }
  
  public static float modulo(float num1, float num2) {
    return num1 % num2;
  }

  public static float square(float num1) {
    return num1 * num1;
  }

  public static float cube(float num1) {
    return (float) Math.pow(num1, 3); 
  }
}

/** The calculator program. */
public class Calculator {
  public static void main(String[] args) {
    while (true) {                                                  // Main loop to run calculator program
      String input = getUserInput("Enter your equation:");  // getUserInput() behaves like python's input() function
      
      // Input validation to check for empty input
      if (input == null) {                                          
        System.out.println("Please enter an equation.");
        continue;
      }

      // Creates string array by splitting input on empty spaces
      String[] tokens = tokenize(input);

      // checks first index for operator, quits program if "q"
      String operator = extractOperator(tokens);
      if (shouldQuit(operator)) {
        System.out.println("Quitting the program. Goodbye!");
        break;
      }

      // Declares variables
      Float[] nums;

      // Tries to convert strings in array to floats
      try {
        nums = extractNums(tokens);

        // Validates for no numbers being entered
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Error: enter at least 1 number.");
        continue;

        // validates any other error
      } catch (NumberFormatException e) {
        System.out.println("Error: not able to parse the numbers you entered.");
        continue;
      }

      // declares result variable
      Float result = evaluate(operator, nums[0], nums[1]);

      // displays results of operation, results in error message if operation not supported
      if (result == null) {
        System.out.println("Invalid operator.");
      } else {
        System.out.println("=> " + result);
      }
    }
  }

  /** Works exactly like Python's input() function. */
  static String getUserInput(String prompt) {
    String inputLine = null;
    System.out.print(prompt + " ");
    try {
      BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
      inputLine = is.readLine();
      if (inputLine.length() == 0) {
        return null;
      }
    } catch (IOException e) {
      System.out.println("IOException: " + e);
    }
    return inputLine;
  }

  static String[] tokenize(String input) {
    return input.split(" ");
  }

  static String extractOperator(String[] tokens) {
    return tokens[0];
  }

  static boolean shouldQuit(String operator) {
    return operator.toLowerCase().equals("q");
  }

  static Float[] extractNums(String[] tokens) {
    Float[] nums = new Float[2];
    nums[0] = Float.parseFloat(tokens[1]);

    if (tokens.length >= 3) {
      nums[1] = Float.parseFloat(tokens[2]);
    } else {
      nums[1] = 0f;
    }
    return nums;
  }

  static Float evaluate(String operator, Float num1, Float num2) {
    Float result;
    switch (operator) {
      case "+":
        result = Arithmetic.add(num1, num2);
        break;

      case "-":
        result = Arithmetic.subtract(num1, num2);
        break;  

      case "*":
        result = Arithmetic.multiply(num1, num2);
        break;

      case "/":
        result = Arithmetic.division(num1, num2);
        break;

      case "%":
        result = Arithmetic.modulo(num1, num2);
        break;

      case "square":
        result = Arithmetic.square(num1);
        break;

      case "cube":
        result = Arithmetic.cube(num1);
        break;
        
      // validates if operation is supported
      default:
        result = null;
        break;
    }

    return result;
  }
}
