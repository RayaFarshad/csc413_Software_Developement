package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LoadCode extends ByteCode {
    private int value;
    private String variable;
    @Override
    public void init(ArrayList<String> bc) {
        value = Integer.parseInt(bc.get(0));
        if(bc.size() > 1) {
            variable = bc.get(1);
        }

    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.load(value);

    }

    @Override
    public String toString() {
        if(variable == ""){
            return "LOAD: "+ value ;
        }
        return "LOAD: "+ value + " " + variable;
    }
}
