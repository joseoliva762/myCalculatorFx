package sample.Model;

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
    public Double powerValues(Double leftValue, Double rightValue){
        Double result = 1.0;
        for(int idx = 0; idx < rightValue; idx++){
            result = this.multiplyValues(result, leftValue);
        }
        return result;
    }

    public abstract Double substractValues(String fullOperation, Double leftValue, Double rightValue);
}