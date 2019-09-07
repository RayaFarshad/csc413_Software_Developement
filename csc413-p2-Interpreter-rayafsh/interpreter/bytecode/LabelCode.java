package interpreter.bytecode;
import java.util.ArrayList;
import interpreter.VirtualMachine;


public class LabelCode extends ByteCode{

    private String label;
    @Override
    public void init(ArrayList<String> bc){
        label = bc.get(0);
    }
    @Override
    public void execute(VirtualMachine vm){}

    @Override
    public String toString() {
        return "LABEL "+label;
    }

    public String getLabel(){
        return label;
    }


}