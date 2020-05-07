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
public class Pass2 {

    public void run(Hashtable<String, Integer> symtab, ArrayList<AssemblyLine> lines) {
        Optab optab = new Optab();
        String instCode;
        String opCode;
        String curOperand;
        int e, n, im, b, p, x;
        int curFormat = -1;
        int disp = 0, ta, base = 0;
        RegTab regTab = new RegTab();
        for (int i = 0; i < lines.size(); i++) {
            if(lines.get(i).obj){
            opCode = "";
            instCode = "";
            curFormat = -1; // no format 
            e = im = n = b = p = x = 0;
            // Code directives // no obj code 
            if (lines.get(i).TrnsltedFlg == false) {
                if (lines.get(i).inst.equalsIgnoreCase("base")) {
                    base = symtab.get(lines.get(i).operand);
                }
                continue;
            }

            //Word Condition
            if (lines.get(i).getInst().equalsIgnoreCase("Word")) {
                opCode = Integer.toHexString(Integer.parseInt(lines.get(i).operand));
                lines.get(i).objCode = formatZeros(opCode, 6);

            } // Byte Condition
            else if (lines.get(i).getInst().equalsIgnoreCase("Byte")) {
                if (lines.get(i).operand.startsWith(" C' ") || lines.get(i).operand.startsWith("c'")) {
                    String byteVal = lines.get(i).operand.substring(2, lines.get(i).operand.length() - 1);
                    for (int j = 0; j < byteVal.length(); j++) {
                        opCode += Integer.toHexString((int) (byteVal.charAt(j)));

                    }
                }
                if (lines.get(i).operand.startsWith(" X' ") || lines.get(i).operand.startsWith("x'")) {
                    opCode = lines.get(i).operand.substring(2, lines.get(i).operand.length() - 1);
                }

                lines.get(i).objCode = opCode.toUpperCase();
            } else {

                if (lines.get(i).inst.equalsIgnoreCase("RSUB")) {
                    lines.get(i).objCode = "4F0000";
                    continue;
                }
                if (lines.get(i).inst.startsWith("+")) //F4
                {
                    curFormat = 4;
                    e = 1;
                    b = 0;
                    p = 0;
                    instCode = optab.optab.get(lines.get(i).inst.substring(1).toUpperCase()).code;

                } else {
                    e = 0;
                    instCode = optab.optab.get(lines.get(i).inst.toUpperCase()).code;
                    curFormat = optab.optab.get(lines.get(i).inst.toUpperCase()).format;
                }
                if (curFormat == 1) {
                    lines.get(i).objCode = instCode;

                } else if (curFormat == 2) {
                    lines.get(i).objCode = instCode;
                    lines.get(i).operand = lines.get(i).operand.toUpperCase();

                    lines.get(i).objCode += regTab.regTab.get(lines.get(i).operand.charAt(0));
                    lines.get(i).objCode += regTab.regTab.get(lines.get(i).operand.charAt(2));
                } else {

                    curOperand = lines.get(i).operand;

                    // check for immidiate and indirect
                    if (lines.get(i).operand.startsWith("@")) {
                        n = 1;
                        im = 0;
                        curOperand = curOperand.substring(1);
                    } else if (lines.get(i).operand.startsWith("#")) {
                        n = 0;
                        im = 1;
                        curOperand = curOperand.substring(1);

                    } else {
                        n = 1;
                        im = 1;
                    }

                    // check for indexed
                    if (lines.get(i).operand.endsWith(",X") || lines.get(i).operand.endsWith(",x")) {
                        x = 1;
                        curOperand = curOperand.substring(0, curOperand.length() - 2); // ashel el , w el X 
                    }
                    // Format 3 
                    if (curFormat == 3) {
                        if (symtab.containsKey(curOperand)) {
                            disp = symtab.get(curOperand) - lines.get(i + 1).pc;

                            if (disp >= -2048 && disp < 2047) {
                                b = 0;
                                p = 1;
                            } else {
                                disp = symtab.get(curOperand) - base;
                                b = 1;
                                p = 0;
                            }

                        } else if (im == 1 && n == 0) {
                            b = 0;
                            p = 0;
                            disp = Integer.parseInt(curOperand);
                        }
                        lines.get(i).objCode = formatZeros(Integer.toBinaryString(Integer.parseInt(instCode, 16)), 8).substring(0, 6);
                        lines.get(i).objCode += Integer.toBinaryString(n);
                        lines.get(i).objCode += Integer.toBinaryString(im);
                        lines.get(i).objCode += Integer.toBinaryString(x);
                        lines.get(i).objCode += Integer.toBinaryString(b);
                        lines.get(i).objCode += Integer.toBinaryString(p);
                        lines.get(i).objCode += Integer.toBinaryString(e);
                        if (disp < 0) {
                            disp = Integer.parseInt("FFF", 16) + disp +1;//negative no sub from fff
                        }

                        lines.get(i).objCode += formatZeros(Integer.toBinaryString(disp), 12);
                        lines.get(i).objCode = formatZeros(Integer.toHexString(Integer.parseInt(lines.get(i).objCode, 2)), 6);//change from binary to dec to hex

                    } else // Format 4
                    {
                        if (symtab.containsKey(curOperand)) {

                            disp = symtab.get(curOperand);
                            b = 0;
                            p = 0;
                        } else // handle immediate number
                         if (im == 1 && n == 0) {
                                b = 0;
                                p = 0;
                                disp = Integer.parseInt(curOperand);

                            }

                        lines.get(i).objCode = formatZeros(Integer.toBinaryString(Integer.parseInt(instCode, 16)), 8).substring(0, 6);
                        lines.get(i).objCode += Integer.toBinaryString(n);
                        lines.get(i).objCode += Integer.toBinaryString(im);
                        lines.get(i).objCode += Integer.toBinaryString(x);
                        lines.get(i).objCode += Integer.toBinaryString(b);
                        lines.get(i).objCode += Integer.toBinaryString(p);
                        lines.get(i).objCode += Integer.toBinaryString(e);
                        lines.get(i).objCode += formatZeros(Integer.toBinaryString(disp), 20);
                        lines.get(i).objCode = formatZeros(Integer.toHexString(Integer.parseInt(lines.get(i).objCode, 2)), 8);

                    }

                }

            }
        }
            else
                continue;
    }
    }

    /**
     * Format zeros String function is try to add "0" to each string toformat it
     * as file format
     */
    String formatZeros(String string, int reqDigits
    ) {
        while (string.length() < reqDigits) {
            string = "0" + string;
        }
        return string.toUpperCase();
    }

}
