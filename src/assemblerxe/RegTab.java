package assemblerxe;


import java.util.Hashtable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class RegTab {
    
    
    Hashtable<Character,Character> regTab;

  

    public RegTab()
    {
        regTab = new Hashtable<Character,Character>();
        
        regTab.put('a' , '0');
        regTab.put('x' , '1');
        regTab.put('l' , '2');
        regTab.put('b' , '3');
        regTab.put('s' , '4');
        regTab.put('t' , '5');
        regTab.put('f' , '6');
    }
    
  
    
    
    
}
