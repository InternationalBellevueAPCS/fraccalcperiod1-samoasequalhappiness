import java.util.*;
public class FracCalc{
	       public static void main(String[] args) {
	             Scanner scanman = new Scanner(System.in);
	             System.out.print("INPUT your equation (unless you wanna type \"quit\" to quit): ");
	             String INPUT = scanman.nextLine();
	             while (!(INPUT.equals("quit"))) {
	                    System.out.println(produceAnswer(INPUT));// This line calls produceAnswer with the equation as the
	                                                                                             // parameter.
	                    System.out.print("INPUT your equation (unless you wanna type \"quit\" to quit): ");
	                    INPUT = scanman.nextLine(); // repeated INPUT until you FINALLY say "quit"
	             }
	       }

	       public static String produceAnswer(String INPUT) {
	             String equation[] = INPUT.split(" "); // splits the INPUT around the spaces and makes a String array to hold
	                                                                                // three values
	             int[] operation1 = operationHolder(equation[0]); // creates array 'operation1' & calls 'operationHolder' to fill
	                                                                                                    // 'operation1' with numerator, denom, and whole #
	             int[] operation2 = operationHolder(equation[2]); // creates array 'operation2' & calls 'operationHolder' to fill
	                                                                                                    // 'operation1' with numerator, denom, and whole #
	             if (operation1[0] != 0) { // checks if operation1 is a mixed fraction
	                    operation1 = improper(operation1); // calls 'improper' method to make operation1 an improper fraction
	             }
	             if (operation2[0] != 0) { // checks if operation2 is a mixed fraction
	                    operation2 = improper(operation2); // calls 'improper' method to make operation2 an improper fraction
	             }
	             if (equation[1].equals("*")) { // checks whether the equation needs to multiply operations
	                    int[] answer = multiply(operation1, operation2);// creates array 'answer' to hold values of the answer and
	                                                                                                    // calls 'multiply' to get values
	                    return answerFormatter(answer); // calls answerFormatter to get the String that needs to be returned, and
	                                                                         // returns it
	             }
	             if (equation[1].equals("/")) { // checks whether the equation needs to divide operations
	                    int[] answer = divide(operation1, operation2);// creates array 'answer' to hold values of the answer and
	                                                                                                    // calls 'divide' to get values
	                    return answerFormatter(answer);
	             }
	             if (equation[1].equals("+")) { // checks whether the plus sign is there or not
	                    int[] answer = addition(operation1, operation2);// creates array 'answer' to hold values of the answer and
	                                                                                                    // calls 'addition' to get values
	                    return answerFormatter(answer);
	             }
	             if (equation[1].equals("-")) { // checks whether the equation needs to subtract operations
	                    int[] answer = subtraction(operation1, operation2);// creates array 'answer' to hold values of the answer
	                                                                                                          // and calls 'subtraction' to get values
	                    return answerFormatter(answer);
	             } else {// returns String telling user that the operator was invalid
	                    String invalid = "Wah, wah, wah. Try adding a valid operator next time, sucker.";
	                    return invalid;
	             }
	       }

	       public static int[] operationHolder(String operation) { // returns the numerator, denominator, and whole number for
	                                                                                                    // each operation
	             int mixedChecker = operation.indexOf("_"); // checks if the operation is a mixed fraction by searching for '_'
	             if (mixedChecker == -1) { // if not a mixed fraction...
	                    int fracChecker = operation.indexOf("/"); // checks if the operation is a regular fraction by searching for
	                                                                                             // '/'
	                    if (fracChecker == -1) { // if not a regular fraction...
	                           return justAnInt(operation); // it's just an integer! calls 'justAnInt', and makes an array 'justInt' to
	                                                                                // hold the values returned by 'justAnInt'
	                    } else { // if a regular fraction...
	                           return fraction(operation); // calls 'fraction', and makes an array 'fraction' to hold the values
	                                                                         // returned by 'fraction'
	                    }
	             } else { // if a mixed fraction
	                    return mixedFraction(operation); // calls mixedFraction method
	             }
	       }

	       public static int[] mixedFraction(String operation) {// returns whole, numerator, & denominator if operation is
	                                                                                                    // mixed fraction
	             String mixed[] = operation.split("_"); // splits an operation around the "_" symbol
	             int[] mixedList = fraction(mixed[1]); // makes array for the values returned by 'fraction' (num and denom),
	                                                                                // fills index 1 and 2
	             mixedList[0] = Integer.parseInt(mixed[0]);
	             return mixedList;
	       }

	       public static int[] fraction(String operation) {// returns whole, numerator, & denominator if operation is
	                                                                                      // regular/improper fraction
	             String fraction[] = operation.split("/"); // splits the simple fraction (x/y) around / and makes array
	                                                                                      // 'fraction' with the split parts
	             int[] fracList = new int[3]; // makes an array to hold the whole, numerator and denominator
	             fracList[1] = Integer.parseInt(fraction[0]);
	             fracList[2] = Integer.parseInt(fraction[1]);
	             return fracList;
	       }

	       public static int[] justAnInt(String operation) {// returns whole, numerator, & denominator if operation is integer
	             int[] whole = new int[3]; // makes an array to hold the whole, num and denom
	             whole[0] = Integer.parseInt(operation); // turns String with the integer into integer value, assigns it to
	                                                                                      // index 0 in 'wholeList'
	             whole[1] = 0;
	             whole[2] = 1;
	             return whole; // returns array with whole, numerator, & denominator
	       }

	       public static int[] improper(int[] array1) { // makes a mixed fraction improper and returns an array
	             int numerator = (array1[0] * array1[2]) + array1[1]; // changes numerator so that the fraction becomes improper
	             if (array1[0] < 0) { // checks if fraction is negative
	                    numerator = (array1[0] * array1[2]) - array1[1];
	             }
	             if (array1[1] == 1 && array1[2] == 1) {
	                    numerator = numerator - 1;
	             }
	             int[] arrayFinal = { 0, numerator, array1[2] }; // makes array with whole = 0, new numerator, and original
	                                                                                             // denom.
	             return arrayFinal; // returns array with whole, numerator, and denominator values
	       }

	       public static int[] multiply(int[] array1, int[] array2) { // multiplies two operations
	             int numerator = array1[1] * array2[1];
	             int denominator = array1[2] * array2[2];
	             if (array1[1] == 0 || array2[1] == 0) {
	                    int[] zero = { 0, 0, 0 };
	                    return zero;
	             }
	             int[] answer = { 0, numerator, denominator };// makes array with final whole, numerator, & denominator
	             return answer;
	       }

	       public static int[] divide(int[] array1, int[] array2) { // divide two operations
	             int num2New = array2[2]; // flips numerator and denominator
	             int denom2New = array2[1];
	             if (denom2New < 0) {
	                    num2New *= -1;
	                    denom2New *= -1;
	             }
	             array2[1] = num2New;
	             array2[2] = denom2New;
	             int[] answer = multiply(array1, array2); // calls multiply and puts final values in array 'answer'
	             return answer;// returns your array with the final whole, numerator, & denominator
	       }

	       

	       public static int[] addition(int[] addArray1, int[] addArray2) { // adds two operations
	             int[] new1 = commonDenominator(addArray1, addArray2);// makes the first operation have a common denominator
	             int[] new2 = commonDenominator(addArray2, addArray1);// makes the second operation have a common denominator
	             int numerator = new1[1] + new2[1]; // adds numerators
	             int denominator = new1[2];
	             int[] last = { 0, numerator, denominator };// makes array with final whole, numerator, and denominator
	             return last;
	       }

	       public static int[] subtraction(int[] subArray1, int[] subArray2) { // subtracts two operations
	             int[] new1 = commonDenominator(subArray1, subArray2);// makes the first operation have a common denominator
	             int[] new2 = commonDenominator(subArray2, subArray1);// makes the second operation have a common denominator
	             int numerator = new1[1] - new2[1];
	             int denominator = new1[2];
	             int[] last = { 0, numerator, denominator };// makes array with final whole, numerator, and denominator
	             return last;
	       }

	       public static int[] commonDenominator(int[] array1, int[] array2) { // makes the first operation have a common
	                                                                                                                        // denominator
	             int numerator = array1[1] * array2[2]; // multiplies numerator of first operation by denominator of the other
	             int denominator = array1[2] * array2[2]; // multiplies denominator of first operation by the denominator of the
	                                                                                      // other
	             int[] last = { 0, numerator, denominator };// makes array with final whole, numerator, and denominator
	             return last;
	       }

	       public static int[] reduce(int[] array1) { // reduces a fraction
	             if (canItReduce(array1)) {
	                    int divisor = greatestCommonDivisor(array1[1], array1[2]);// finds GCD for the fraction represented by
	                                                                                                                        // array1
	                    array1[1] = array1[1] / divisor;// divides numerator by GCD
	                    array1[2] = array1[2] / divisor;// divides denominator by GCDF
	             }
	             if (Math.abs(array1[1]) > array1[2]) {// if the fraction is improper, turn it into mixed
	                    if (array1[2] == 1) { // checks if the INPUTed array represents an integer
	                           return array1;
	                    }
	                    boolean isNegative = false;
	                    if (array1[1] < 0) {// checks if the numerator is negative
	                           isNegative = true;// makes the fact that the number is negative true
	                           array1[1] *= -1;// makes numrator positive
	                    }
	                    while (array1[1] > array1[2]) {
	                           array1[1] = array1[1] - array1[2];// subtracts denominator from numerator
	                           array1[0]++;// adds one to the whole number value
	                    }
	                    if (isNegative == true && array1[0] != 0) {// checks if the numerator was negative and not zero
	                           array1[0] *= -1;// makes whole number negative
	                    } else if (isNegative == true) {// checks if numerator was negative
	                           array1[1] *= -1;// makes numerator negative
	                    }
	             }
	             return array1;// returns array with whole, nuemrator, and denominator values
	       }

	       public static boolean canItReduce(int[] array1) { // checks if a fraction can be reduced
	             for (int i = 2; i <= Math.abs(array1[1]); i++) {
	                    if (array1[1] % i == 0 && array1[2] % i == 0) { // checks which number the num and denom have a common
	                                                                                                    // divisor
	                           return true;
	                    }
	             }
	             return false;
	       }

	       public static String answerFormatter(int[] answer) { // This will formats your answer as a fraction or an integer.
	             reduce(answer);// reduces answer
	             if (answer[2] == 1 || answer[1] == 0) { // checks if answer is a non-zero integer
	                    return Integer.toString(answer[1]);// returns integer
	             } else if (answer[0] != 0) {// checks if fraction is improper
	                    String whole = Integer.toString(answer[0]);// assigns first value in array to 'whole'
	                    String numerator = Integer.toString(answer[1]);// assigns second value in array to 'numerator'
	                    String denominator = Integer.toString(answer[2]);// assigns third value in array to 'denominator'
	                    return whole + "_" + numerator + "/" + denominator;// returns String with formatted mixed fraction
	             } else {// returns a regular fraction (one that's mixed)
	                    String numerator = Integer.toString(answer[1]);// assigns second value in 'answer' to numerator
	                    String denominator = Integer.toString(answer[2]);// assigns third value in 'answer' to denominator
	                    return numerator + "/" + denominator;// returns String with formatted regular fraction
	             }
	       }

	       public static int greatestCommonDivisor(int one, int two)
	       /*
	       * This'll figure out your wonderful GCD by testing each value and finding the
	       * remainder to get the max
	       */
	       {
	             one = Math.abs(one);
	             two = Math.abs(two);
	             int max = Math.max(one, two);
	             int min = Math.min(one, two);
	             while (min != 0) {
	                    int tmp = min;
	                    min = max % min;
	                    max = tmp;
	             }
	             return max;
	       }
	}
