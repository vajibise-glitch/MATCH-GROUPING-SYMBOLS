package GroupingSymbolsChecker;

import java.io.*;
import java.util.*;

public class GroupingSymbolsChecker {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java GroupingSymbolsChecker <file-name>");
            return;
        }
        
        String fileName = args[0];
        
        try {
            // Open the file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            Stack<Character> stack = new Stack<>();
            boolean isValid = true;
            
            int ch;
            while ((ch = reader.read()) != -1) {

                if (ch == '(' || ch == '{' || ch == '[') {
                    stack.push((char) ch);
                } else if (ch == ')' || ch == '}' || ch == ']') {
                    if (stack.isEmpty()) {
                        isValid = false;
                        break;
                    }
                    char top = stack.pop();
                    if (!isMatchingPair(top, (char) ch)) {
                        isValid = false;
                        break;
                    }
                }
            }
            
            reader.close();
            

            if (!stack.isEmpty()) {
                isValid = false;
            }
            
            // Output the result
            if (isValid) {
                System.out.println("The file has correctly matched grouping symbols.");
            } else {
                System.out.println("The file has unmatched grouping symbols.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
    }
    

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}