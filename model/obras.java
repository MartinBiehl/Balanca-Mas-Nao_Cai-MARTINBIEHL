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
public class obras {
     private int idObra;
    private String descricao;
    private LocalDate dtInicio;
    private LocalDate dtFim;
    private int idEngenheiro;
    private String nrCrea;
    private LocalDate dtAdmissaoEngenheiro;

    // Construtor vazio (mesmo nome da classe!)
    public obras() {
    }

    // Construtor com todos os campos
    public obras(int idObra, String descricao, LocalDate dtInicio, LocalDate dtFim,
                 int idEngenheiro, String nrCrea, LocalDate dtAdmissaoEngenheiro) {
        this.idObra = idObra;
        this.descricao = descricao;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        this.idEngenheiro = idEngenheiro;
        this.nrCrea = nrCrea;
        this.dtAdmissaoEngenheiro = dtAdmissaoEngenheiro;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(LocalDate dtInicio) {
        this.dtInicio = dtInicio;
    }

    public LocalDate getDtFim() {
        return dtFim;
    }

    public void setDtFim(LocalDate dtFim) {
        this.dtFim = dtFim;
    }

    public int getIdEngenheiro() {
        return idEngenheiro;
    }

    public void setIdEngenheiro(int idEngenheiro) {
        this.idEngenheiro = idEngenheiro;
    }

    public String getNrCrea() {
        return nrCrea;
    }

    public void setNrCrea(String nrCrea) {
        this.nrCrea = nrCrea;
    }

    public LocalDate getDtAdmissaoEngenheiro() {
        return dtAdmissaoEngenheiro;
    }

    public void setDtAdmissaoEngenheiro(LocalDate dtAdmissaoEngenheiro) {
        this.dtAdmissaoEngenheiro = dtAdmissaoEngenheiro;
    }
    
    
}
