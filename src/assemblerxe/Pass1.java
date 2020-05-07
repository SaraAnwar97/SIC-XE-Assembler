package assemblerxe;

import java.util.ArrayList;
import java.util.Hashtable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author
 */
public class Pass1 {

    String progName = "";
    String progEnd = "";
    Hashtable< String, Integer> symtab;
    ArrayList<AssemblyLine> lines;
    ArrayList<String> symbols = new ArrayList<String>();
    Optab optab;
    String Error;
    String Error1;
    String comma = ",";
    int seperate;
    RegTab regtab;
    String[] tokens;

    private boolean TestHex(String value) {
        boolean ret;
        try {
            int t = Integer.parseInt(value, 16);
            ret = true;
        } catch (NumberFormatException e) {
            ret = false;
        }
        return ret;
    }

    private boolean Format(String value) {
        if (value.charAt(0) == '+') {
            return true;
        } else {
            return false;
        }

    }

    public void symbols(ArrayList<String> labels) {

        for (int i = 0; i < labels.size(); i++) {
            String x = "";
            boolean done = false;
            int linelen = labels.get(i).length();
            if (labels.get(i).charAt(0) == '.') {
                continue;
            }
            

            if (linelen > 8 && labels.get(i).charAt(0) != ' ') {
                x = labels.get(i).substring(0, 8);
                done = true;
                // symbols.add(labels.get(i).substring(0, 8));  
            } else if (linelen < 8 && labels.get(i).charAt(0) != ' ') {
                x = labels.get(i).substring(0, linelen);
                done = true;
                //symbols.add(labels.get(i).substring(0, linelen));

            }
            x.trim();
            if (done) {

                symbols.add(x);
                // System.out.println(symbols.get(i));
            }

        }
        for (int i = 0; i < symbols.size(); i++) {
            System.out.println(symbols.get(i));
        }

    }

    public void run(ArrayList<String> codeLines) {
        int pc = 0;
        String label;
        String inst;
        String operand;
        String operand2;
        String checkString;
        String checkError;

        progName = "";
        progEnd = "";
        regtab = new RegTab();
        symtab = new Hashtable< String, Integer>();
        lines = new ArrayList<AssemblyLine>();
        optab = new Optab();

        for (int i = 0; i < codeLines.size(); i++) {

            label = "";
            inst = "";
            operand = "";

            int linelen = codeLines.get(i).length();

            if (codeLines.get(i).charAt(0) == '.') {
                continue; // comment
            }
             AssemblyLine w = new AssemblyLine(label, inst, operand, pc, false);
            if (((String)codeLines.get(i)).charAt(8) != ' ' ){
                   
                        Error = "Error: Misplaced operand and operation ";
                        w.setErrorMessage(Error);
                        w.obj = false;
            }  
                 lines.add(w);

            // extract label
            /*if (linelen > 8) {
                label = codeLines.get(i).substring(0, 8); // 3shan ya5od men 0 le 7 
               
            } else {
                label = codeLines.get(i).substring(0, linelen); // low kda 5od ba2et el length & lazm a handle error 
            } 

            // extract instruction 
            // lazm at2ked en lsa fe instruction 
            if (linelen > 8) {
                if (linelen > 15) {
                    inst = codeLines.get(i).substring(9, 15);
                    
                } else {
                    inst = codeLines.get(i).substring(9, linelen);
                }
            }

            // extract operand
            if (linelen > 15) {
                if (linelen > 35) {

                    operand = codeLines.get(i).substring(15, 35);

                } else {
                    operand = codeLines.get(i).substring(15, linelen);
                }
            }*/
            
            
          tokens = codeLines.get(i).split("\\s+");//split by one space
                if (tokens.length == 3) {
                    label = tokens[0];
                    inst = tokens[1];
                    operand = tokens[2];
                } else if (tokens.length == 2) {
                    label = "";
                    inst = tokens[0];
                    operand = tokens[1];
                } else if (tokens.length == 1) {
                    label = "";
                    operand = "";
                    inst = tokens[0];
                } 
            label = label.trim(); // betndef ay spaces 3shan mmken maykontsh maly kolo be char w el ba2y spaces 
            inst = inst.trim();
            operand = operand.trim();

            if (inst.compareToIgnoreCase("start") == 0) {
                AssemblyLine l = new AssemblyLine(label, inst, operand, pc, false);
                if (Format(operand)) {
                    Error = "\t\t****Can't be format 4 instruction";
                    l.setErrorMessage(Error);
                    l.obj = false;
                }
                
                pc = Integer.parseInt(operand, 16);

                progName = label;

                if (progName.isEmpty()) {
                    Error = "\t\t****ERROR:Please specify program Name ";
                    checkString = l.getErrorMessage();

                    if (checkString == null) {

                        l.setErrorMessage(Error);
                    } else {
                        l.setErrorMessage(Error + "\n" + checkString);
                    }

                }

                lines.add(l);

            } else if (inst.compareToIgnoreCase("end") == 0) {
                AssemblyLine l = new AssemblyLine(label, inst, operand, pc, false);
                if (Format(operand)) {
                    Error = "\t\t****Can't be format 4 instruction";
                    l.setErrorMessage(Error);
                    l.obj = false;
                }
                 progEnd = label;
                if (progEnd != null){
                    Error= "\t\t****Error: this statement can’t have a label! ";
                    l.setErrorMessage(Error);
                    l.obj = false;
                    
                }
                
                   lines.add(l);
                
                continue;

            } else if (inst.compareToIgnoreCase("base") == 0) {
                AssemblyLine l = new AssemblyLine(label, inst, operand, pc, false);
                if (Format(operand)) {
                    Error = "\t\t****Can't be format 4 instruction";
                    l.setErrorMessage(Error);
                    l.obj = false;
                }
                lines.add(l);
                continue;
            


            } else if (inst.compareToIgnoreCase("resw") == 0) {
                AssemblyLine l = new AssemblyLine(label, inst, operand, pc, false);
                if (Format(operand)) {
                    Error = "\t\t****Can't be format 4 instruction";
                    l.setErrorMessage(Error);
                    l.obj = false;
                }
                checkString = l.getErrorMessage();
                if (symtab.containsKey(label)) {
                    Error = "\t\t****ERROR:Duplicate Label";

                    if (checkString.compareTo("") == 0) {

                        l.setErrorMessage(Error);
                    } else {
                        l.setErrorMessage(Error + "\n" + checkString);
                    }

                }

                symtab.put(label, pc);

                lines.add(l);

                pc = pc + 3 * Integer.parseInt(operand);
                continue;
            } else if (inst.compareToIgnoreCase("resb") == 0) {
                AssemblyLine l = new AssemblyLine(label, inst, operand, pc, false);
                if (Format(operand)) {
                    Error = "\t\t****Can't be format 4 instruction";
                    l.setErrorMessage(Error);
                    l.obj = false;
                }
                if (symtab.containsKey(label)) {
                    Error = "\t\t****ERROR:Duplicate Label ";
                    l.setErrorMessage(Error);
                }
                symtab.put(label, pc);
                lines.add(l);
                pc = pc + Integer.parseInt(operand);

                continue;

            } else if (inst.compareToIgnoreCase("word") == 0) {
                AssemblyLine l = new AssemblyLine(label, inst, operand, pc, true);
                if (Format(operand)) {
                    Error = "\t\t****Can't be format 4 instruction";
                    l.setErrorMessage(Error);
                    l.obj = false;
                }
                lines.add(l);
                if (symtab.containsKey(label)) {
                    Error = "\t\t****ERROR:Duplicate Label ";
                    l.setErrorMessage(Error);
                }

                symtab.put(label, pc);
                pc = pc + 3;

                continue;

            } else if (inst.compareTo("byte") == 0) {
                AssemblyLine l = new AssemblyLine(label, inst, operand, pc, true);
                if (Format(operand)) {
                    Error = "\t\t****Error: Can't be format 4 instruction";
                    l.setErrorMessage(Error);
                    l.obj = false;
                }
                lines.add(l);
                if (symtab.containsKey(label)) {
                    Error = "\t\t****ERROR:Duplicate Label ";
                    l.setErrorMessage(Error);
                }

                symtab.put(label, pc);
                if (operand.contains("c'") || operand.contains("c'")) {
                    pc = pc + operand.length() - 3;

                } else {
                    checkString = operand.substring(2, operand.lastIndexOf("'"));
                    if (TestHex(checkString)) {
                        pc = pc + (operand.length() - 3) / 2;
                        symtab.put(label, pc);
                    } else {
                        Error = "\t\t****ERROR:String must be hexadecimal ";
                        l.setErrorMessage(Error);
                    }

                }
                continue;
            } else {
                AssemblyLine l = new AssemblyLine(label, inst, operand, pc, true);
                if (operand.contains(comma)) {
                    Character c1 = operand.charAt(0);
                    Character c2 = operand.charAt(2);
                    if (regtab.regTab.get(c1) == null || regtab.regTab.get(c2) == null) {
                        Error = "\t\t****Illegal address for Register";

                        l.setErrorMessage(Error);
                    }
                    
                }
                if (Format(operand)) {
                    Error = "\t\t****Error: Can't be format 4 instruction";
                    l.setErrorMessage(Error);
                    l.obj = false;
                }
                if (inst.isEmpty()) {
                          Error = "\t\t****Error: Missing operation";
                         checkString = w.getErrorMessage();

                    if (checkString == null) {

                        w.setErrorMessage(Error);
                    } else {
                        w.setErrorMessage(Error + "\n" + checkString);
                    }

                 
                    } 

                if (!label.equalsIgnoreCase("")) {
                    if (symtab.containsKey(label)) {
                        Error = "\t\t****ERROR:Duplicate Label ";
                        l.setErrorMessage(Error);
                    }
                    symtab.put(label, pc);
                    System.out.println(label + "  " + pc);
                }
                if(operand.compareTo("")== 0)
                {
                     Error = "\t\t****Error: Missing operand";
                    l.setErrorMessage(Error);
                }
                if (l.inst.compareTo("rsub") == 0 && operand.compareTo("") != 0) {
                    Error = "\t\t****Error: Can't contain an operand";
                    l.setErrorMessage(Error);
                }
                if (!optab.optab.containsKey(inst.toUpperCase())) {
                    Error = "\t\t****ERROR: Unrecognized Operation Code";
                    checkString = l.getErrorMessage();

                    if (checkString.compareTo("") == 0) {
                        
                        l.setErrorMessage(Error);
                    } else {
                        l.setErrorMessage(Error  + checkString);
                    }

                    l.obj = false;
                }
                lines.add(l);
                if (operand.contains(comma)) {
                    Character c1 = operand.charAt(0);
                    Character c2 = operand.charAt(2);
                    if (regtab.regTab.get(c1) == null || regtab.regTab.get(c2) == null) {
                        Error = "\t\t**** Error: Undefined symbol in Operand";
                        l.setErrorMessage(Error);
                        l.obj = false;
                    } else {
                        continue;
                    }
                } else {
                    if (l.operand.charAt(0) != '#' && l.operand.charAt(0) != '@' && symbols.contains(l.operand)) {
                        Error = "\t\t**** Error: Undefined symbol in Operand";
                        l.setErrorMessage(Error);
                        l.obj = false;
                    } else {
                        continue;
                    }
                }
            }

            if (inst.startsWith("+")) {
                pc = pc + 4;
            } else if (optab.optab.containsKey(inst.toUpperCase())) {
                pc = pc + optab.optab.get(inst.toUpperCase()).format;
            }

        }

    }

}
