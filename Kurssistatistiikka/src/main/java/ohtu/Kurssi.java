/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

/**
 *
 * @author paavo
 */
public class Kurssi {
    private String name;
    private String term;
    private int[] exercises;
    
    
    public int harjoitustenMaara(int viikko) {
        return exercises[viikko];
    }
    
    public String getKurssi() {
        return name;
    }
    
    
    public String getTerm() {
        return term;
    }
}
