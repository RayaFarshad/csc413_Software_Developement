package interpreter;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Stack;
import java.util.Iterator;

public class RunTimeStack
{

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack()
    {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }
    //Void function used to dump the current state of the RuntimeStack.
    //When printing the runtime stack make sure
    //to include divisions between frames. If a frame is empty, this must be shown as well.
    public void dump() {


        //Iterate over the frame stack using Iterator
        Iterator<Integer> iter = framePointer.iterator();
        int currentFrame = iter.next();
        int nextFrame;

        int j = 0;
        while (j < framePointer.size()) {
            if (!iter.hasNext()) {
                nextFrame = stackSize();
            }else {

                nextFrame = iter.next();
            }
            System.out.print("[");
            //print all items between currentframe and nest frame
            for (int i = currentFrame; i < nextFrame; i++) {
                System.out.print(runTimeStack.get(i));
                //remove comma from the last item before the end of the window
                if(i != nextFrame-1){
                    System.out.print(",");
                }
            }
            j++;
            currentFrame = nextFrame;
            System.out.print("]");

        }
        System.out.print("\n");
//
//
//
//            while (iter.hasNext()){
//                if(currentFrame<runTimeStack.size()) {
//                    System.out.print(runTimeStack.get(currentFrame));
//                    System.out.print(",");
//
//                }
//            }
//
//        System.out.print("\n");




//        for(int j = 0; j <= framePointer.peek(); j++){
//
//            System.out.print("[");
//
//            while(j < runTimeStack.size()){
//
//                    System.out.print("," + runTimeStack.get(j));
//                    j++;
//
//            }
//            System.out.print(("]"));
//
//        }
//        System.out.print("\n");
//
    }






    public  void empty(){
        this.runTimeStack.clear();
    }
    //returns the top of the stack without removing the item.
    public int peek()
    {
        if(runTimeStack.size() == 0)
        {
            throw new IndexOutOfBoundsException("runTimeStack is empty");
        }
        return runTimeStack.get(runTimeStack.size()-1);
    }
    //removes an item from the top of the stack and returns it.
    public int pop()
    {
        if(runTimeStack.size() == 0)
        {
            throw new IndexOutOfBoundsException("runTimeStack is empty");
        }
        int temp = runTimeStack.get(runTimeStack.size()-1);
        runTimeStack.remove(runTimeStack.size()-1);
        return temp;

    }
    //creates a new frame in the RuntimeStack class.
    //The parameter offset is used to denote how many slots down from the top of RuntimeStack for starting a new frame.
    public void newFrameAt(int offset)
    {
        int frameValue = runTimeStack.size() - offset;
        if (frameValue < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        framePointer.push(frameValue);
    }
    //we pop the top frame when we return from a function. Before popping,
    //the function’s return value is at the top of the stack, so we’ll save the value,
    //then pop the top frame and then push the return value back onto the stack.
    //It is assumed return values are at the top of the stack.
    public void popFrame() {
//        int temp = runTimeStack.get(runTimeStack.size());
        int temp = this.peek();
        int pop = framePointer.pop();

        //int i = runTimeStack.size()-1;
        for (int i = runTimeStack.size() - 1; i > pop - 1; i--) {
            runTimeStack.remove(i);

        }
        runTimeStack.add(temp);
    }
    //Used to store values into variables. Store will pop the top value of the stack and
    //replace the value at the given offset in the current frame.
    //The value stored is returned.
    public int store(int offset)
    {
        int store = this.pop();
        int endOfCurrentFrame = offset + framePointer.peek();
        runTimeStack.set(endOfCurrentFrame, store);
        return store;

    }
    //Used to load variables onto the RuntimeStack from a given offset within the current frame.
    //This means we will go to the offset in the current frame,
    //copy the value and push it to the top of the stack.
    //No values should be removed with load.
    public int load(int offset)
    {
        int val = runTimeStack.get(offset + framePointer.peek());
        runTimeStack.add(val);
        return val;
    }
    //to return the stackSize
    public int stackSize()
    {
        return this.runTimeStack.size();
    }
    //Used to load literal values onto the RuntimeStack.
    //For example, LIT 5 or LIT 0 will call push with val being 5 or val being 0.
    public Integer push(Integer val)
    {
        runTimeStack.add(val);
        return this.peek();

    }

}

