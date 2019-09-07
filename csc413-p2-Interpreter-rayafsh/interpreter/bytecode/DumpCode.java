package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class DumpCode extends ByteCode {
    private String label;
    @Override
    public void init(ArrayList<String> bc) {
        label = bc.get(0);

    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setDump(label);

    }

    @Override
    public String toString() {
        return null;
    }
}
