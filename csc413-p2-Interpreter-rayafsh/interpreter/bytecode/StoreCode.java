package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode {
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
        vm.store(value);

    }

    @Override
    public String toString() {
        if (variable == "") {
            return "STORE: " + value;
        }
        return "STORE: "+value+" "+variable;
    }
}
