package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class WriteCode extends ByteCode {
    private int top;
    @Override
    public void init(ArrayList<String> bc) {

    }

    @Override
    public void execute(VirtualMachine vm) {
        top = vm.peek();
        System.out.println(top);

    }

    @Override
    public String toString(){
    return "WRITE ";
}
}
