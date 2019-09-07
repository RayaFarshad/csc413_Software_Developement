package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends ByteCode{
    private String label;
    private int labelAddress;
    private int element;
    @Override
    public void init(ArrayList<String> bc) {
        label = bc.get(0);


    }

    @Override
    public void execute(VirtualMachine vm) {
        //push the address to the returnAddr stack
        //returnAddr stack Used to store return addresses for each called function
        //PC is the (current bytecode being executed)
        //we get the bytecode address currently executed and push it into the returnstack
        vm.pushReturnAddrs(vm.getPC());
        vm.setPC(labelAddress-1);
        if (vm.stackSize() > 0) {
            element = vm.peek();
        }

    }
    public String removeParanthesis(String label){
        String tempLabel [] = label.split("<<");
        return tempLabel[0];
    }


    @Override
    public String toString() {
        return "CALL " +this.removeParanthesis(label)+"   "+ this.removeParanthesis(label) + "("+this.getElementFromVM()+")";
    }

    public String getLabel(){

        return label;
    }
    public int getElementFromVM(){
        return element;
    }

    public void setLabelAddress(int index) {

        this.labelAddress = index;
    }


}
