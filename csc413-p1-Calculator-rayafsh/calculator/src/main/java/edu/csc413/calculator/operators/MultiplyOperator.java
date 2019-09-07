
package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;


public class MultiplyOperator extends Operator{

    public Operand execute(Operand op1, Operand op2 ){

        Operand multiply= new Operand(op1.getValue() * op2.getValue());
        return multiply;

    }
    public int priority(){

        return 2;
    }
}