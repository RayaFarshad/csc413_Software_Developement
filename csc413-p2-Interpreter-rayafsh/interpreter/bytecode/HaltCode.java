package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class HaltCode extends ByteCode {
    @Override
    public void init(ArrayList<String> bc) {

    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.exit();
    }

    @Override
    public String toString() {
        return "HALT ";
    }
}
