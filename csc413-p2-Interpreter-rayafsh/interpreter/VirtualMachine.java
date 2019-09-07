package interpreter;

import java.util.Stack;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.DumpCode;

public class VirtualMachine {

    private RunTimeStack runStack;
    private Stack returnAddrs; //Used to store return addresses for each called function(excluding main)
    private Program program; //Reference to the program object where all bytecodes are stored.
    private int pc;  //the program counter (current bytecode being executed).
    private boolean isRunning; //to determine whether the VM should be executing bytecodes.
    private boolean dump;

    //Dump is a flag to determine whether the run time stack should be printed
    protected VirtualMachine(Program program) {

        this.program = program;
    }


    //*The returnAddrs stack stores the bytdecode index(PC) that the virtual machine should execute
    //when the current function exits.
    //*Each time a function is entered, the PC should be pushed onto the returnAddrs stack.
    //*When a function exits the PC should be restored to the value that is popped from the
    //top of the returnAddrs Stack.
    public void executeProgram(){
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();//create new empty stack to store the PC (bytecode index)
        isRunning = true;
        dump = true;
        while(isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            if(this.dump && !(code instanceof DumpCode)){
                System.out.println(code);
                runStack.dump(); // Used to dump runstack state.
            }
            pc++;
        }

    }
    public void push(int val){
        runStack.push(val);
    }
    public int peek(){
        return runStack.peek();
    }

    public void exit() {
        isRunning = false;
    }

    public void load(int offset){
        runStack.load(offset);
    }
    public void store(int offset){
        runStack.store(offset);
    }

    public void newFrameAt(int offset) {
        runStack.newFrameAt(offset);
    }
    public void pushReturnAddrs(int addrs){
        returnAddrs.push(addrs);
    }
    public int getPC(){
        return pc;
    }
    public void setPC(int address){
        this.pc = address;
    }

    public int pop() {
        return runStack.pop();
    }

    public int popReturnAddrs() {
        return (Integer) this.returnAddrs.pop();
    }

    public void popFrame() {
        runStack.popFrame();
    }
    public int stackSize(){
        return runStack.stackSize();
    }
    public void empty(){
        runStack.empty();
    }

    public void setDump(String label) {
        if(label.equals("ON")){
            dump = true;
        }else if(label.equals("OFF")){
            dump = false;
        }
    }
}
//call needs help method
//cannot call vm.dump in the bytecode class
//dump function in the vitual machine should be pivate and protected