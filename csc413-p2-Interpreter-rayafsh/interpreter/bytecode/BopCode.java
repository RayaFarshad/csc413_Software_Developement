package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;
public class BopCode extends ByteCode {
    private String operator;
    @Override
    public void init(ArrayList<String> bc) {
        operator = bc.get(0);

    }

    @Override
    public void execute(VirtualMachine vm) {
        int operand2 = vm.pop();
        int operand1 = vm.pop();
        int result = 0;
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;

            case "*":
                result = operand1 * operand2;
                break;

            case "/":
                result = operand1 / operand2;
                break;

            case "==":
                if (operand1 == operand2) {
                    result = 1;
                } else {
                    result = 0;
                }
                break;
            case "!=":
                if (operand1 != operand2) {
                    result = 1;
                } else {
                    result = 0;
                }
                break;
            case "<=":
                if (operand1 <= operand2) {
                    result = 1;
                } else {
                    result = 0;
                }
                break;

            case ">":
                if (operand1 > operand2) {
                    result = 1;
                } else {
                    result = 0;
                }
                break;
            case ">=":
                if (operand1 >= operand2) {
                    result = 1;
                } else {
                    result = 0;
                }
                break;

            case "<":
                if (operand1 < operand2) {
                    result = 1;
                } else {
                    result = 0;
                }
                break;
            case "|":
                if (operand1 == 0 && operand2 ==0) {
                    result = 0;
                } else {
                    result = 1;
                }
                break;
            case "&":
                if (operand1 == 1 && operand2 == 1) {
                    result = 1;
                } else {
                    result = 0;
                }
                break;
        }
        vm.push(result);
    }



    @Override
    public String toString() {
        return "BOP "+ operator;
    }
}
