package interpreter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import interpreter.bytecode.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class ByteCodeLoader extends Object
{

    private BufferedReader byteSource;
    private Program program;


    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN LOADCODES.
     */


    public ByteCodeLoader(String file) throws IOException
    {
        this.byteSource = new BufferedReader(new FileReader(file));
        this.program = new Program();
    }
    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */
    public Program loadCodes()
    {
        String line;
        String key, className;

        ArrayList<String> args=new ArrayList<>();//Creating arraylist

        try
        {

        line = byteSource.readLine();
       while (line != null)
       {


           //Reads in the next bytecode from the source file.
           StringTokenizer strtoken = new StringTokenizer(line);
           key = strtoken.nextToken();
           className = CodeTable.getClassName(key);
           //using the string, retrieve the class name from the CodeTable.
           //With the class name, retrieve the Class blueprint object.
           Class c = Class.forName("interpreter.bytecode." + className);
           ByteCode byteCode = (ByteCode) c.getDeclaredConstructor().newInstance();
           //Read in any additional arguments for the given bytecode if any exists.
           //Once all arguments are parsed, we will pass these arguments to the bytecode’s init function.

           while (strtoken.hasMoreTokens())
           {
               args.add(strtoken.nextToken());
           }

           byteCode.init(args);
           //Store the fully initialized bytecode instance into the program data-structure.
           program.addBCinstance(byteCode);
           args.clear();
           line = byteSource.readLine();
       }
        } catch (Exception e) {
            System.out.println("Reading file errors: " + e);
        }
           //Once all bytecodes are loaded, we will resolve all symbolic addresses
        try {
            program.resolveAddrs(program);
        }catch (Exception e){
            System.out.println("Resolving error: " + e);
        }



        return program;
    }
}
