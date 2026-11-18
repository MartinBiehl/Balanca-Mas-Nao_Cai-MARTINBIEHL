/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author martin.biehl
 */
public class Obras_Fases {
    private int idObra;
    private int idEngenheiro;
    private LocalDate dtAdmissaoEngenheiro;
    private LocalDate dtInicioTrabalho;
    private LocalDate dtFimTrabalho;
    private int idFuncao;

    // Construtor vazio (mesmo nome da classe!)
    public Obras_Fases() {
    }

    // Construtor com todos os campos
    public Obras_Fases (int idObra, int idEngenheiro, LocalDate dtAdmissaoEngenheiro,
                            LocalDate dtInicioTrabalho, LocalDate dtFimTrabalho, int idFuncao) {
        this.idObra = idObra;
        this.idEngenheiro = idEngenheiro;
        this.dtAdmissaoEngenheiro = dtAdmissaoEngenheiro;
        this.dtInicioTrabalho = dtInicioTrabalho;
        this.dtFimTrabalho = dtFimTrabalho;
        this.idFuncao = idFuncao;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public int getIdEngenheiro() {
        return idEngenheiro;
    }

    public void setIdEngenheiro(int idEngenheiro) {
        this.idEngenheiro = idEngenheiro;
    }

    public LocalDate getDtAdmissaoEngenheiro() {
        return dtAdmissaoEngenheiro;
    }

    public void setDtAdmissaoEngenheiro(LocalDate dtAdmissaoEngenheiro) {
        this.dtAdmissaoEngenheiro = dtAdmissaoEngenheiro;
    }

    public LocalDate getDtInicioTrabalho() {
        return dtInicioTrabalho;
    }

    public void setDtInicioTrabalho(LocalDate dtInicioTrabalho) {
        this.dtInicioTrabalho = dtInicioTrabalho;
    }

    public LocalDate getDtFimTrabalho() {
        return dtFimTrabalho;
    }

    public void setDtFimTrabalho(LocalDate dtFimTrabalho) {
        this.dtFimTrabalho = dtFimTrabalho;
    }

    public int getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(int idFuncao) {
        this.idFuncao = idFuncao;
    }
    
    

}
