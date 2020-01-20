package sample.Model;

public enum Operators {
    POWER('^'),
    MULTIPLY('*'),
    DIVIDE('/'),
    SUBSTRACT('-'),
    ADD('+');

    private int operator;
    Operators(int operator) {
        this.operator = operator;
    }

    public int getOperator(){
        return this.operator;
    }
}