package assemblerxe;



import java.util.Hashtable;

public class Optab {
	
	 Hashtable< String , InstValue > optab ;
	
	public Optab(){
		optab = new Hashtable< String , InstValue>();
		
		optab.put( "ADD" ,  new InstValue("18" , 3) );
		optab.put( "ADDF" ,  new InstValue("58" , 3));
                optab.put( "ADDR" ,  new InstValue("90" , 2));
		optab.put( "AND" , new InstValue("40" , 3));
                optab.put( "CLEAR" ,  new InstValue("B4" , 2));
		optab.put( "COMP" , new InstValue("28" , 3));
                optab.put( "COMPR" , new InstValue("A0" , 2));
		optab.put( "DIV" , new InstValue("24" , 3));
                optab.put( "DIVR" , new InstValue("9C" , 2));
                optab.put( "FIX" , new InstValue("C4" ,1));
                optab.put( "FLOAT" , new InstValue("C0" ,1));
                optab.put( "HIO" , new InstValue("F4" ,1));
		optab.put(  "J" , new InstValue("3C" , 3));
		optab.put( "JEQ" ,new InstValue ("30" , 3));
		optab.put( "JGT" , new InstValue("34" , 3));
		optab.put( "JLT" , new InstValue("38" , 3));
		optab.put( "JSUB" ,new InstValue ("48" , 3));
		optab.put( "LDA" ,new InstValue ("00" , 3));
		optab.put(  "LDB" , new InstValue("68" , 3));
		optab.put( "LDCH" , new InstValue("50" , 3));
		optab.put( "LDF" ,new InstValue("70" , 3));
		optab.put( "LDL" , new InstValue("08" , 3));
		optab.put( "LDS" , new InstValue("6C" , 3));
		optab.put( "LDT" , new InstValue("74" , 3));
		optab.put( "LDX" ,new InstValue ("04" , 3));
		optab.put( "LPS" , new InstValue("D0" , 3));
		optab.put( "MUL" , new InstValue("20" , 3));
                optab.put( "MULR" , new InstValue("98" , 2));
                optab.put( "NORM" , new InstValue("C8" ,1));
		optab.put( "MULF" , new InstValue("60" , 3));
		optab.put( "OR" , new InstValue("44" , 3));
		optab.put( "RD" , new InstValue("D8" , 3));
                optab.put( "RMO" , new InstValue("AC" , 2));
		optab.put( "RSUB" , new InstValue("4C" , 3));
                optab.put( "SHIFTL" , new InstValue("A4" , 2));
                optab.put( "SHIFTR" , new InstValue("A8" , 2));
                optab.put( "SIO" , new InstValue("F0" , 1));
		optab.put( "SSK" , new InstValue("EC" , 3));
		optab.put( "STA" , new InstValue("0C" , 3));
		optab.put( "STB" , new InstValue("78" , 3));
		optab.put( "STCH" , new InstValue("54" ,3));
		optab.put( "STF" , new InstValue("80" , 3));
		optab.put( "STI" , new InstValue("D4" , 3));
		optab.put( "STL" , new InstValue("14" , 3));
		optab.put( "STS" , new InstValue("7C" , 3));
		optab.put( "STSW" , new InstValue("E8" , 3));
		optab.put( "STT" , new InstValue("84" , 3));
		optab.put( "STX" ,new InstValue ("10" , 3));
		optab.put( "SUB" , new InstValue("1C" , 3));
		optab.put( "SUBF" , new InstValue("5C" , 3));
                optab.put( "SUBR" , new InstValue("94" , 2));
                optab.put( "SVC" , new InstValue("B0" , 2));
		optab.put( "TD" , new InstValue("E0" , 3));
                optab.put( "TIO" , new InstValue("F8" , 1));
		optab.put( "TIX" , new InstValue("2C" , 3));
                optab.put( "TIXR" , new InstValue("B8" , 2));
		optab.put( "WD" , new InstValue("DC" , 3));
               
	}
	
}
