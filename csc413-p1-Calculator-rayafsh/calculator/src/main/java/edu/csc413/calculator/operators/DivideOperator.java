
package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;


public class DivideOperator extends Operator{

    public Operand execute(Operand op1, Operand op2 ){

        Operand div= new Operand(op1.getValue()/op2.getValue());
        return div;

    }
    public int priority(){

        return 2;
    }
}
