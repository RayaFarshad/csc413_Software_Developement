package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LitCode extends ByteCode {
    protected int value;
    protected String variable = "";
    @Override
    public void init(ArrayList<String> bc) {
        //LIT 2 or LIT 2 i
        //convert String to int
        value = Integer.parseInt(bc.get(0));
        if(bc.size()>1){
            variable = bc.get(1);
        }


    }

    @Override
    public void execute(VirtualMachine vm) {
        if(variable.equals("")){
            vm.push(value);
        }else{
            vm.push(0);
        }



    }

    @Override
    public String toString() {
        if (variable == ""){
            return "LIT: "+ value ;
        }else{
            return "LIT: "+ value + " " + variable;
        }
    }

}
