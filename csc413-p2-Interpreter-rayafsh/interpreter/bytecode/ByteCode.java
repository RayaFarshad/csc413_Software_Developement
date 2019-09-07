package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public abstract class ByteCode {

    public abstract void init(ArrayList<String> bc);
    public abstract void execute(VirtualMachine vm);
    public abstract String toString();


}
