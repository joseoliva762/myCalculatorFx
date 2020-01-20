package Model;

import java.io.IOException;
import java.util.Scanner;

public class Calculator extends MathOperations{
    private String fullOperation, rigthMiddleOperation, leftMiddleOperation, signedOfResult;
    private Double rightValue, leftValue, resultOfOperation;
    Scanner in = new Scanner(System.in);

    // Constructor
    public Calculator(String fullOperation){
        System.out.println("Instantiating my calculator...");
        this.signedOfResult = "+";
        this.fullOperation = fullOperation;
        this.resultOfOperation = 0.0;
        this.calculateResultOfOperation();
    }
    // SobreCarga
    public Calculator(){
        System.out.println("Instantianting my calculator whitout parameters...");
        this.signedOfResult = "+";
        this.resultOfOperation = 0.0;
    }

    // Getters and Setters
    public String getFullOperation() { return fullOperation; }
    private void setFullOperation(String fullOperation) { this.fullOperation = "("+ fullOperation +")"; }

    public Double getResultOfOperation() { return resultOfOperation; }
    private void setResultOfOperation(Double resultOfOperation) { this.resultOfOperation = resultOfOperation; }

    public String getSignedOfResult() { return signedOfResult; }
    public void setSignedOfResult(String signedOfResult) { this.signedOfResult = signedOfResult; }

    // set fullOperation
    public String enterFullOperation(){
        this.setSignedOfResult("+");
        System.out.print("Enter Operation: ");
        String fullOperation = in.next();
        setFullOperation(fullOperation);
        return resultToString(this.calculateResultOfOperation());
    }

    private String resultToString(String resultInString) {
        return this.getSignedOfResult() + resultInString;
    }

    // Calculate
    public String calculateResultOfOperation(){
        for(Operators operator: Operators.values()) {

            int selectedOperator = operator.getOperator();
            while (true) {
                if (getFullOperation().indexOf(selectedOperator) != -1) {
                    separateTermsOfFullOperation(getFullOperation(), selectedOperator);
                    switch (selectedOperator) {
                        case '*':
                            setResultOfOperation(super.multiplyValues(this.leftValue, this.rightValue));
                            break;
                        case '/':
                            setResultOfOperation(super.divideValues(this.leftValue, this.rightValue));
                            break;
                        case '+':
                            setResultOfOperation(super.addValues(this.leftValue, this.rightValue));
                            break;
                        case '-':
                            this.resultOfOperation = this.substractValues(getFullOperation(), this.leftValue, this.rightValue);
                        default:
                            setResultOfOperation(resultOfOperation);
                    }
                    setFullOperation(this.leftMiddleOperation + String.valueOf(this.resultOfOperation) + this.rigthMiddleOperation);
                } else {
                    break;
                }
            }
        }
        return String.valueOf(this.getResultOfOperation());
    }

    private void separateTermsOfFullOperation(String fullOperation, int operator){
        if(fullOperation.length() != 4) {
            for (int charOfFullOperation = (fullOperation.length() - 1); charOfFullOperation >= 0; charOfFullOperation--) {
                if (fullOperation.charAt(charOfFullOperation) == operator) {
                    for (int idx = charOfFullOperation + 1; idx <= fullOperation.length(); idx++) {
                        if (fullOperation.charAt(idx) == '*'
                                || fullOperation.charAt(idx) == '/'
                                || fullOperation.charAt(idx) == '+'
                                || fullOperation.charAt(idx) == '-'
                                || fullOperation.charAt(idx) == ')') {
                            this.rigthMiddleOperation = fullOperation.substring(idx);
                            this.rightValue = Double.parseDouble(fullOperation.substring(charOfFullOperation + 1, idx));
                            break;
                        }
                    }

                    for (int idx = charOfFullOperation - 1; idx >= 0; idx--) {
                        if (fullOperation.charAt(idx) == '*'
                                || fullOperation.charAt(idx) == '/'
                                || fullOperation.charAt(idx) == '+'
                                || fullOperation.charAt(idx) == '-'
                                || fullOperation.charAt(idx) == '(') {

                            leftMiddleOperation = fullOperation.substring(0, idx + 1);
                            try {
                                leftValue = Double.parseDouble(fullOperation.substring(idx + 1, charOfFullOperation));
                            }catch (NullPointerException e){
                                System.out.println("Captada Exepcion.");
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }


    @Override
    public String toString() {
        return "Calculator{ \n" +
                "\tfullOperation=" + fullOperation + "\n\t" +
                leftMiddleOperation + ' ' + leftValue + ' ' +fullOperation.charAt(leftMiddleOperation.length()+1)+ ' ' + rightValue + ' ' + rigthMiddleOperation
                +"\n}";
    }

    @Override
    public Double substractValues(String fullOperation, Double leftValue, Double rightValue) {
        validationOfLeftOperation(fullOperation, leftValue, rightValue);
        return Math.abs(super.substractValues(this.leftValue, this.rightValue));
    }

    private void validationOfLeftOperation(String fullOperation, Double leftValue, Double rightValue){
        if (leftValue == null) {
            this.leftValue = 0.0;
            this.rightValue = Double.parseDouble(String.valueOf(fullOperation.charAt(2)));
            this.leftMiddleOperation = "(";
            this.rigthMiddleOperation = ")";
            this.setSignedOfResult("-");
        }else{
            this.leftValue = leftValue;
            this.rightValue = rightValue;
            if(leftValue < rightValue) setSignedOfResult("-");
            else setSignedOfResult("+");
        }
    }

    public void resetCalculator() {
        this.fullOperation = null;
        this.rigthMiddleOperation = null;
        this.leftMiddleOperation = null;
        this.signedOfResult = "+";
        this.rightValue = 0.0;
        this.leftValue = 0.0;
        this.resultOfOperation = 0.0;
    }

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows 10")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            // Handle any exceptions...
        }
    }
}