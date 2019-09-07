package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode {
    private int popNum;
    @Override
    public void init(ArrayList<String> bc) {
        popNum = Integer.parseInt(bc.get(0));

    }

    @Override
    public void execute(VirtualMachine vm) {
        if (popNum > vm.stackSize()){
            vm.empty();
        }else{
            for (int i = 0; i < popNum - 1; i++){
                vm.pop();
            }
        }

    }
    public int getPopNum(){
        return popNum;
    }

    @Override
    public String toString() {

        return "POP "+ this.getPopNum();
    }
}
