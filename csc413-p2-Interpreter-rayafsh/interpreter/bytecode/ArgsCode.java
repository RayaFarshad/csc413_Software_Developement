package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode {
    private int value;
    @Override
    public void init(ArrayList<String> bc) {
        value = Integer.parseInt(bc.get(0));

    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.newFrameAt(value);

    }

    @Override
    public String toString() {
        return "ARGS: " + value;
    }
}
