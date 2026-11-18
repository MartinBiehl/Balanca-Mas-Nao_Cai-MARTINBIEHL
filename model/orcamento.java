package model;

import java.time.LocalDate;
/**
 *
 * @author martin.biehl
 */
public class orcamento {
    private int idOrcamento;
    private LocalDate dtAprovacao;
    private int idEngenheiroResponsavel;

    // Construtor vazio (mesmo nome da classe!)
    public orcamento() {
    }

    // Construtor com todos os campos
    public orcamento(int idOrcamento, LocalDate dtAprovacao, int idEngenheiroResponsavel) {
        this.idOrcamento = idOrcamento;
        this.dtAprovacao = dtAprovacao;
        this.idEngenheiroResponsavel = idEngenheiroResponsavel;
    }

    public int getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public LocalDate getDtAprovacao() {
        return dtAprovacao;
    }

    public void setDtAprovacao(LocalDate dtAprovacao) {
        this.dtAprovacao = dtAprovacao;
    }

    public int getIdEngenheiroResponsavel() {
        return idEngenheiroResponsavel;
    }

    public void setIdEngenheiroResponsavel(int idEngenheiroResponsavel) {
        this.idEngenheiroResponsavel = idEngenheiroResponsavel;
    }
    
    
}
