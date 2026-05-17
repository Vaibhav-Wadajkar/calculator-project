package calculator.logic;

public class CalculatorEngine {
    private double currentValue = 0;
    private double storedValue = 0;
    private String pendingOperator = null;
    private boolean startNewNumber = true;
    private boolean errorFlag = false;

    public void inputDigit(String digit) {
        if (errorFlag) clearAll();
        String currentDisplay = getDisplayValue();
        if (startNewNumber || currentDisplay.equals("0")) {
            if (digit.equals(".")) currentDisplay = "0.";
            else currentDisplay = digit;
            startNewNumber = false;
        } else {
            if (digit.equals(".") && currentDisplay.contains(".")) return;
            currentDisplay += digit;
        }
        try {
            currentValue = Double.parseDouble(currentDisplay);
        } catch (NumberFormatException e) {
            errorFlag = true;
        }
    }

    public void inputOperator(String op) {
        if (errorFlag) return;
        if (pendingOperator != null && !startNewNumber) compute();
        if (op.equals("=")) {
            pendingOperator = null;
            startNewNumber = true;
        } else {
            pendingOperator = op;
            storedValue = currentValue;
            startNewNumber = true;
        }
    }

    private void compute() {
        switch (pendingOperator) {
            case "+": currentValue = storedValue + currentValue; break;
            case "-": currentValue = storedValue - currentValue; break;
            case "*": currentValue = storedValue * currentValue; break;
            case "/":
                if (currentValue == 0) errorFlag = true;
                else currentValue = storedValue / currentValue;
                break;
        }
        storedValue = currentValue;
        startNewNumber = true;
    }

    public String getDisplayValue() {
        if (errorFlag) return "Error";
        if (Double.isInfinite(currentValue) || Double.isNaN(currentValue)) return "Error";
        if (currentValue == (long) currentValue) return String.format("%d", (long) currentValue);
        return String.valueOf(currentValue);
    }

    public void clearAll() {
        currentValue = 0;
        storedValue = 0;
        pendingOperator = null;
        startNewNumber = true;
        errorFlag = false;
    }

    public void clearEntry() {
        currentValue = 0;
        startNewNumber = true;
    }
}
