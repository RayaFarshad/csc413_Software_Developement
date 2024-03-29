package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

import java.util.HashMap;

public abstract class Operator{
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.
    // ALL subclasses of operator MUST be in their own file.
    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
//     HashMap operators = new HashMap();
//     operators.put( "+", new AdditionOperator() );
//     operators.put( "-", new SubtractionOperator() );
//    AddOperator
//DivideOperator
//MultiplyOperator
//PowerOperator
//SubtractOperator
    private static HashMap<String , Operator> operat;
    static {
        operat = new HashMap<>();
        operat.put("+", new AddOperator());
        operat.put("-", new SubtractOperator());
        operat.put("*", new MultiplyOperator());
        operat.put("^", new PowerOperator());
        operat.put("/", new DivideOperator());
        operat.put(")", new RightParanthesis());
        operat.put("(", new LeftParanthesis());
        operat.put("A", new InitializeClass());


    }
    public abstract int priority();
    public abstract Operand execute(Operand op1, Operand op2 );

    /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     * for example token.equals("+") and so on.
     * Think about what happens if we add more operators.
     */
    public static boolean check( String token ) {
        return operat.containsKey(token);

    }


    public static Operator getOperator(String token){

            return operat.get(token);

    }
}
