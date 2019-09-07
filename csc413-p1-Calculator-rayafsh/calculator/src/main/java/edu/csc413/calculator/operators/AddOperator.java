package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;


public class AddOperator extends Operator{

    public Operand execute(Operand op1, Operand op2 ){

        Operand sum= new Operand(op1.getValue()+op2.getValue());
        return sum;

    }
    public int priority(){
        return 1;
    }
}