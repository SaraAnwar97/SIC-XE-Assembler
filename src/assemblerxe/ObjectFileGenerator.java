package assemblerxe;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
public class ObjectFileGenerator {
    
    
    public void run(String fileName,String progName , ArrayList<AssemblyLine> lines) throws IOException
    {
        String H= HeaderRecord(progName, lines.get(0).pc, lines.get(lines.size()-1).pc -  lines.get(0).pc);
        String T = TextRecord (lines.get(0).pc, lines.get(lines.size()-1).pc -  lines.get(0).pc, lines);
        String E = EndRecord(lines.get(0).pc);
       
   
         File file = new File (fileName);
         BufferedWriter out = new BufferedWriter(new FileWriter(file));
         out.write(H);
         out.write(T);
         out.write(E);
         out.close();
    }
    public String HeaderRecord (String progName, Integer startAddress , Integer progLength)
    {
        return "H"+ formatSpaces(progName,7)+ formatZeros(Integer.toHexString(startAddress),6)+ formatZeros(Integer.toHexString(progLength),6) + "\n";
                
                
    }
    public String TextRecord (Integer startAdress, Integer progLength,ArrayList<AssemblyLine> lines)
    {
        
        int startT = startAdress;
        int lineCount = 0;
        String textRec =  "";
        
        while (true)
        {
           String codeLine ="";
           int count = 0 ;
           while(count < 10 && lineCount < lines.size())
           {
               if(lines.get(lineCount).inst.equalsIgnoreCase("resw") || lines.get(lineCount).inst.equalsIgnoreCase("resb"))
                   break;
               if(lines.get(lineCount).TrnsltedFlg == false)
               {
                        lineCount++;          
                       continue;
               }
               codeLine += lines.get(lineCount).objCode;
               
                
               
               lineCount++;
               count++;
            //   if( lines.get(lineCount-1).objCode.equalsIgnoreCase("4C0000"))
              //     break;
              
           }
           
           String lineLen = Integer.toHexString(codeLine.length() / 2); //2 hex = 1 byte
           
           textRec += "T" + formatZeros(Integer.toHexString(startT),6) + formatZeros(lineLen,2) + codeLine+"\n";
           
           
           if (lineCount >= lines.size())
               break;
           
           // skip untranslated line
           while(lineCount < lines.size() && !lines.get(lineCount).TrnsltedFlg)
               lineCount++;
           
           if (lineCount >= lines.size())
               break; 
           startT =lines.get(lineCount).pc;
        }  
        return textRec;
    }
    public String EndRecord (Integer startAdress)
    {
        return "E"+ formatZeros(Integer.toHexString(startAdress),6);
    }
    
    /**
     * Format zeros String function is try to add "0" to each string to format it as file format
     */
    String formatZeros(String string , int reqDigits)
    {
        while (string.length() < reqDigits)
        {
            string = "0" + string;
        }
        return string.toUpperCase();
    }
    
    String formatSpaces(String string , int reqDigits)
    {
        while (string.length() < reqDigits)
        {
            string += " ";
        }
        return string;
    }
    
                
    
     
        
}
