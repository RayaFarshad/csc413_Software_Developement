package interpreter;

import java.util.ArrayList;
import java.util.HashMap;

import interpreter.bytecode.*;

//This class should at least contain two instance functions: getCode
public class Program {

    private ArrayList<ByteCode> program;
    private HashMap<String, Integer> map;

    public Program() {
        this.program = new ArrayList<>();
        this.map = new HashMap<>();
    }


//this function returns the ByteCode at a given index
    protected ByteCode getCode(int pc) {

        return this.program.get(pc);
    }

    public int getSize() {

        return this.program.size();
    }
    public void addBCinstance (ByteCode byteCode){
        if (byteCode instanceof LabelCode){
           LabelCode label = (LabelCode)byteCode;
           map.put(label.getLabel(), this.getSize());
        }
        program.add(byteCode);
    }



    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-stucture bytecodes are stored in.
     *
     * @param program Program object that holds a list of ByteCodes
     */
    //this function will resolve all symbolic addresses in the program.
    //you want to match f<<3>> with LABEL f<<3>> and to do this you should
    //go through the file to find all the unique labels and mark line numbers(address)
    //which each label resides, Then you will go through the file again and give each
    //CALL, FALSEBRANCH and GOTO byteCode the line number (address) that matches those labels.

    public void resolveAddrs(Program program) {
        int address;
        for (int i = 0; i < program.getSize(); i++){
            if (program.getCode(i) instanceof GotoCode){
                GotoCode gtcBytecode = (GotoCode) program.getCode(i);
                address = map.get(gtcBytecode.getLabel());
                gtcBytecode.setLabelAddress(address);

            }
            else if (program.getCode(i) instanceof CallCode){
                CallCode ccBytecode = (CallCode) program.getCode(i);
                address = map.get(ccBytecode.getLabel());
                ccBytecode.setLabelAddress(address);

            }
            else if (program.getCode(i) instanceof FalseBranchCode){
                FalseBranchCode fbcBytecode = (FalseBranchCode) program.getCode(i);
                address = map.get(fbcBytecode.getLabel());
                fbcBytecode.setLabelAddress(address);
            }
        }
    }
}
