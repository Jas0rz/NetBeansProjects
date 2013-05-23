package calculator;

import java.util.Stack;

/**
 *
 * @author Jason McConnell
 */
class CalcBrain implements Calculations {
    private String currentDigits;
    private Stack numberStack;

    public CalcBrain() {
        currentDigits = "";
        numberStack = new Stack();
        
    }

    @Override
    public String digit(String digit) {
        currentDigits += digit;
        return digit;
    }

    @Override
    public String operator(String op) {
        float result = 0;
        if (numberStack.size() < 2) {
                return "\nNeed more numbers.\n";
        }
        float numTwo = (float) numberStack.pop();
        float numOne = (float) numberStack.pop();
        switch (op) {
            case "+":
                result = numOne + numTwo;
                break;
            case "-":
                result = numOne - numTwo;
                break;
            case "*":
                result = numOne * numTwo;
                break;
            case "/":
                result = numOne / numTwo;
                break;
            case "^":
                result = (float) Math.pow(numOne, numTwo);
                break;
        }
        numberStack.push(result);
        currentDigits = "";
        return "\n" + result + " ";
    }

    @Override
    public String clearEntry() {
            currentDigits = "";
            return "cleared.\n";
    }

    @Override
    public String clear() {
        numberStack.clear();
        currentDigits = "";
        return "Everything cleared.\n";
    }

    @Override
    public String enterPressed() {
        if (currentDigits.equals(".") || currentDigits.equals("")) {
            currentDigits = "0";
        }
        float number = Float.parseFloat(currentDigits);
        numberStack.push(number);
        currentDigits = "";
        return " ";
    }

    @Override
    public String addDecimal() {
        if (currentDigits.contains(".")) {
            return "No.";
        }
        currentDigits += ".";
        return ".";
    }
    
}
