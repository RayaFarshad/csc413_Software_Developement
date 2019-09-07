package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;
import java.util.Scanner;


public class ReadCode extends ByteCode {
    Scanner sc = new Scanner(System.in);
    //private int number;
    @Override
    public void init(ArrayList<String> bc) {


    }

    @Override
    public void execute(VirtualMachine vm) {
        try {
            System.out.println("Enter an integer: ");
           int number = sc.nextInt();

            vm.push(number);
        }catch(Exception e){
            System.out.println("Input Error: "+ e);
        }

    }

    @Override
    public String toString() {
        return "READ ";
    }


}
