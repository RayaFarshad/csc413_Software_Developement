package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode {
    private String label;
    private int val;
    @Override
    public void init(ArrayList<String> bc) {
        if(bc.isEmpty())
            label = null;
        else
            label = bc.get(0);


    }

    @Override
    public void execute(VirtualMachine vm) {

        vm.setPC(vm.popReturnAddrs());
        vm.popFrame();
        val = vm.peek();


    }
    public String getLabel(){
        return label;
    }
    public String getmodifiedLabel(String label){
        String temp [] = label.split("<<");
        return temp[0];

    }
    public int returnval(){
        return val;
    }

    @Override
    public String toString() {
        if(this.getLabel() == null)
            return "RETURN " ;
        return  "RETURN "+this.getmodifiedLabel(this.getLabel())+" exit "+this.getmodifiedLabel(this.getLabel())+": "+this.returnval();
    }
}
