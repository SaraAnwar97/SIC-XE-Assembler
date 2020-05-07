package assemblerxe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author
 */
public class AssemblyLine {

    protected String operand;
    protected String operand2;
    protected String inst;
    protected int pc;
    protected String objCode;
    protected String label;
    protected boolean TrnsltedFlg ; //start
    protected String errorMessage;
    protected boolean obj; //To validate insruction
    public AssemblyLine() {
    }

    public AssemblyLine(String inst, String operand, int pc) {
        this.pc = pc;
        this.operand = operand;
        this.inst = inst;
        this.errorMessage = "";//*******************ADDED
        this.obj = true; //**********ADDED
    }
      public AssemblyLine(String inst, String operand,String operand2, int pc) {
        this.pc = pc;
        this.operand = operand;
        this.operand2=operand2;
        this.inst = inst;
        this.errorMessage = "";//*******************ADDED
        this.obj = true; //**********ADDED
    }

    public AssemblyLine(String label, String inst, String operand, int pc, boolean TrnsltedFlg) {
        this.operand = operand;
        this.inst = inst;
        this.pc = pc;
        this.label = label;
        this.TrnsltedFlg = TrnsltedFlg;
        this.objCode = "";
        this.errorMessage = "";//****************************ADDED
        this.obj = true; //*************ADDED
    }
   /*   public AssemblyLine(String label, String inst, String operand,String operand2 ,int pc, boolean TrnsltedFlg) {
        this.operand = operand;
        this.inst = inst;
        this.pc = pc;
        this.label = label;
        this.TrnsltedFlg = TrnsltedFlg;
        this.objCode = "";
        this.errorMessage = "";//****************************ADDED
        this.obj = true; //*************ADDED
    }*/

   
    public String getInst() {
        return inst;
    }

    public void setInst(String inst) {
        this.inst = inst;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getObjCode() {
        return objCode;
    }

    public void setObjCode(String objCode) {
        this.objCode = objCode;
    }

    public String getOperand() {
        return operand;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    public int getPc() {
        return pc;
    }

    public void setTrnsltedFlg(boolean TrnsltedFlg) {
        this.TrnsltedFlg = TrnsltedFlg;
    }

    public boolean getTrnsltedFlg()
    {
        return TrnsltedFlg;
    }
    
    
}
