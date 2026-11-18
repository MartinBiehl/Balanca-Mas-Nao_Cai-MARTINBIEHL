package model;

import java.time.LocalDate;

/**
 *
 * @author martin.biehl
 */
public class obras_engenheiros {
    private int idObra;
    private int idEngenheiro;
    private LocalDate dtAdmissaoEngenheiro;
    private LocalDate dtInicioTrabalho;
    private LocalDate dtFimTrabalho;
    private int idFuncao;

    // Construtor vazio (mesmo nome da classe!)
    public obras_engenheiros() {
    }

    // Construtor com todos os campos
    public obras_engenheiros (int idObra, int idEngenheiro, LocalDate dtAdmissaoEngenheiro,
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
