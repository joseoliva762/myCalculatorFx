package Model;

public abstract class MathOperations {
    public MathOperations(){

    }
    // Methods
    public Double addValues(Double leftValue, Double rightValue){
        return leftValue + rightValue;
    }
    public Double substractValues(Double leftValue, Double rightValue){
        return leftValue - rightValue;
    }
    public Double multiplyValues(Double leftValue, Double rightValue){
        return leftValue * rightValue;
    }
    public Double divideValues(Double leftValue, Double rightValue){
        return leftValue / rightValue;
    }

    public abstract Double substractValues(String fullOperation, Double leftValue, Double rightValue);
}