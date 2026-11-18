package model;

import java.math.BigDecimal;
import java.time.LocalDate;



/**
 *
 * @author martin.biehl
 */
public class Orcamentos_Obras {
    private int idOrcamento;
    private int idObra;
    private LocalDate dtInicio;
    private LocalDate dtFim;
    private BigDecimal vlPrevisto;
    private BigDecimal vlRealizado;

    // Construtor vazio (mesmo nome da classe!)
    public Orcamentos_Obras() {
    }

    // Construtor com todos os campos
    public Orcamentos_Obras(int idOrcamento, int idObra, LocalDate dtInicio, LocalDate dtFim,
                      BigDecimal vlPrevisto, BigDecimal vlRealizado) {
        this.idOrcamento = idOrcamento;
        this.idObra = idObra;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        this.vlPrevisto = vlPrevisto;
        this.vlRealizado = vlRealizado;
    }

    public int getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
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

    public BigDecimal getVlPrevisto() {
        return vlPrevisto;
    }

    public void setVlPrevisto(BigDecimal vlPrevisto) {
        this.vlPrevisto = vlPrevisto;
    }

    public BigDecimal getVlRealizado() {
        return vlRealizado;
    }

    public void setVlRealizado(BigDecimal vlRealizado) {
        this.vlRealizado = vlRealizado;
    }
    
    
    
}
