import Model.Calculator;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //"1+2*3+5-7
        Calculator myCalculator = new Calculator();
        Scanner in = new Scanner(System.in);
        char goOn = 's';
        while(goOn != 'n') {
            myCalculator.resetCalculator();
            System.out.println("Result: ~|" + myCalculator.enterFullOperation() + "|~");

            System.out.print("Desea Realizar otra Operacion? [s/n]: ");
            goOn = in.next().charAt(0);
            Calculator.clearConsole();
        }
        //System.out.println(example);
    }
}