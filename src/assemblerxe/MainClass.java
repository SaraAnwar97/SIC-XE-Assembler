package assemblerxe;


import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author
 */
public class MainClass {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FileHandler x= new FileHandler(); 
        ArrayList<String> a = x.readFile("SRCFILE_SICXE.txt"); // esm el file eli ha2rah 
        Pass1 pass1 = new Pass1();
        pass1.symbols(a);
        pass1.run(a);
        Pass2 pass2 = new Pass2();
        pass2.run(pass1.symtab,pass1.lines);
        ObjectFileGenerator gen = new ObjectFileGenerator();
        x.ListFile("LISTFILE.txt", pass1.lines);
        gen.run("OBJECTFILE.txt" , pass1.progName , pass1.lines);
}
                
    }
    

