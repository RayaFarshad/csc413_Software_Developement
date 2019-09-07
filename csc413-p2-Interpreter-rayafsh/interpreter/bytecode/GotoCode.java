package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class GotoCode extends ByteCode {
    private String label;
    private int labelAddress;
    @Override
    public void init(ArrayList<String> bc) {

        label = bc.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setPC(labelAddress-1);

    }

    @Override
    public String toString() {
        return "GOTO "+this.label;
    }

    public  String getLabel(){
        return label;
    }

    public void setLabelAddress(int index) {
        labelAddress = index;
    }
}
