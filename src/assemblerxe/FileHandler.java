package assemblerxe;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author
 */
public class FileHandler {

    public ArrayList<String> readFile(String filename) throws FileNotFoundException{
        ArrayList<String> code = new ArrayList<String>();

        File f = new File(filename);
        Scanner s = new Scanner(f);
        
        while(s.hasNextLine()) // tol ma fe line gded
        {
            code.add(s.nextLine());
        }
        return code;
    }
    
    
       public void ListFile(String filename, ArrayList<AssemblyLine> lines) throws FileNotFoundException{
        PrintWriter printer = new PrintWriter(new File(filename));
        for (int i=0; i < lines.size(); i++){
            printer.println(String.format("%10s%10s%10S%10s%10s",
                    Integer.toHexString(lines.get(i).getPc()),
                    lines.get(i).getLabel(),
                    lines.get(i).getInst(),
                    lines.get(i).getOperand(),
                    lines.get(i).getObjCode()
                 ));
            
            if(lines.get(i).errorMessage.compareTo("") != 0){ //***********************ADDED
                printer.println(lines.get(i).errorMessage);
            }
        }
     printer.close();
        
    }

}
