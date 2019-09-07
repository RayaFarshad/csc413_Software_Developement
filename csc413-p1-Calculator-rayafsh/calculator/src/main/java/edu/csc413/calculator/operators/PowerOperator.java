
package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;


public class PowerOperator extends Operator{

    public Operand execute(Operand op1, Operand op2 ){


        Operand pow= new Operand((int) Math.pow((double)op1.getValue(),(double)op2.getValue()));
        return pow;

    }
    public int priority(){
        return 3;
    }
}
