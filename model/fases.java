/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author martin.biehl
 */
public class fases {
    private int idFase;
    private String dsFase;
    
    
    public fases(){
        
    }
    
    public fases ( int idFase, String dsFase){
        this.idFase = idFase;
        this.dsFase = dsFase;
    }

    public int getIdFase() {
        return idFase;
    }

    public void setIdFase(int idFase) {
        this.idFase = idFase;
    }

    public String getDsFase() {
        return dsFase;
    }

    public void setDsFase(String dsFase) {
        this.dsFase = dsFase;
    }
    
    
    
}
