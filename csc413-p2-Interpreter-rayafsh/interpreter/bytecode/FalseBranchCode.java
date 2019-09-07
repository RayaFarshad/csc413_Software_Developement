package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode {
    private String label;
    private int labelAddress;
    @Override
    public void init(ArrayList<String> bc) {

        label = bc.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {

        if(vm.pop() == 0){
            vm.setPC(labelAddress-1);
        }

    }

    @Override
    public String toString() {

        return "FALSEBRANCH "+ this.label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabelAddress(int index) {
        this.labelAddress = index;
    }

}
