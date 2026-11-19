/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.DaoEngenheiros;
import dao.DataSource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Engenheiros;

/**
 *
 * @author martin.biehl
 */
public class JFengenheiros extends javax.swing.JFrame {

    private final DaoEngenheiros dao = new DaoEngenheiros(new DataSource());
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Creates new form JFengenheiros
     */
    public JFengenheiros() {
        initComponents();

        if (JFLogin.usuarioLogado != null) {
            lblUsuario.setText(JFLogin.usuarioLogado.getNome());
        }

        btnIncluir.addActionListener(evt -> incluir());
        btnEditar.addActionListener(evt -> editar());
        btnExcluir.addActionListener(evt -> excluir());
        btnSair.addActionListener(evt -> sair());
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                carregarTabela();
            }
        });

        carregarTabela();
    }

    // Preenche a tabela com o que vem do banco (filtrando pelo campo Buscar)
    private void carregarTabela() {
        String busca = txtBuscar.getText().trim();
        ArrayList<Engenheiros> lista = busca.isEmpty() ? dao.consulta() : dao.seleciona(busca);

        DefaultTableModel modelo = (DefaultTableModel) tblEngenheiros.getModel();
        modelo.setRowCount(0);

        for (Engenheiros engenheiro : lista) {
            String admissao = engenheiro.getDtAdmissao() == null ? "" : engenheiro.getDtAdmissao().format(FORMATO_DATA);
            modelo.addRow(new Object[]{
                engenheiro.getIdEngenheiro(),
                engenheiro.getDsNome(),
                "",
                "",
                engenheiro.getNrCREA(),
                admissao
            });
        }
    }

    // Devolve o id da linha selecionada, ou -1 se nenhuma estiver selecionada
    private int idSelecionado() {
        int linha = tblEngenheiros.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um engenheiro na tabela.");
            return -1;
        }
        return (int) tblEngenheiros.getValueAt(linha, 0);
    }

    private void incluir() {
        String nome = newEng.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome do engenheiro.");
            return;
        }

        // CREA e data de admissão são obrigatórios no banco
        String crea = JOptionPane.showInputDialog(this, "Número do CREA:");
        if (crea == null) {
            return; // cancelou
        }
        crea = crea.trim();
        if (crea.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o número do CREA.");
            return;
        }

        Engenheiros engenheiro = new Engenheiros();
        engenheiro.setDsNome(nome);
        engenheiro.setNrCREA(crea);
        engenheiro.setDtAdmissao(LocalDate.now());

        if (dao.incluir(engenheiro)) {
            newEng.setText("");
            carregarTabela();
        }
    }

    private void editar() {
        int id = idSelecionado();
        if (id < 0) {
            return;
        }

        Engenheiros engenheiro = dao.consultaPorId(id);
        if (engenheiro == null) {
            return;
        }

        String nome = JOptionPane.showInputDialog(this, "Nome:", engenheiro.getDsNome());
        if (nome == null) {
            return;
        }
        String crea = JOptionPane.showInputDialog(this, "Número do CREA:", engenheiro.getNrCREA());
        if (crea == null) {
            return;
        }
        if (nome.trim().isEmpty() || crea.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome e CREA não podem ficar em branco.");
            return;
        }

        engenheiro.setDsNome(nome.trim());
        engenheiro.setNrCREA(crea.trim());

        if (dao.alterar(engenheiro)) {
            carregarTabela();
        }
    }

    private void excluir() {
        int id = idSelecionado();
        if (id < 0) {
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this, "Excluir o engenheiro nº " + id + "?",
                "Excluir", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION && dao.excluir(id)) {
            carregarTabela();
        }
    }

    private void sair() {
        JFLogin.usuarioLogado = null;
        new JFLogin().setVisible(true);
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTopo = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();
        pnlFundo = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblMensagem = new javax.swing.JLabel();
        pnlNovo = new javax.swing.JPanel();
        lblNovo = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        newEng = new javax.swing.JTextField();
        btnIncluir = new javax.swing.JButton();
        pnlLista = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEngenheiros = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Engenheiros");

        pnlTopo.setBackground(new java.awt.Color(255, 255, 255));
        pnlTopo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(224, 224, 224)));

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblLogo.setText("Balança-Mas-Não-Cai");

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(102, 102, 102));
        lblUsuario.setText("Usuario Teste");

        btnSair.setText("Sair");

        javax.swing.GroupLayout pnlTopoLayout = new javax.swing.GroupLayout(pnlTopo);
        pnlTopo.setLayout(pnlTopoLayout);
        pnlTopoLayout.setHorizontalGroup(
            pnlTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopoLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(lblLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUsuario)
                .addGap(15, 15, 15)
                .addComponent(btnSair)
                .addGap(30, 30, 30))
        );
        pnlTopoLayout.setVerticalGroup(
            pnlTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblLogo)
                    .addComponent(lblUsuario)
                    .addComponent(btnSair))
                .addGap(18, 18, 18))
        );

        btnInicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(204, 102, 0));
        btnInicio.setText("< Início");
        btnInicio.setBorderPainted(false);
        btnInicio.setContentAreaFilled(false);
        btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setText("Engenheiros");

        lblMensagem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMensagem.setForeground(new java.awt.Color(200, 0, 0));

        pnlNovo.setBackground(new java.awt.Color(255, 255, 255));
        pnlNovo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 224)));

        lblNovo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNovo.setText("Novo engenheiro");

        lblNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(68, 68, 68));
        lblNome.setText("Nome");

        newEng.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnIncluir.setBackground(new java.awt.Color(30, 136, 229));
        btnIncluir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIncluir.setForeground(new java.awt.Color(255, 255, 255));
        btnIncluir.setText("Incluir engenheiro");

        javax.swing.GroupLayout pnlNovoLayout = new javax.swing.GroupLayout(pnlNovo);
        pnlNovo.setLayout(pnlNovoLayout);
        pnlNovoLayout.setHorizontalGroup(
            pnlNovoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNovoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlNovoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNovo)
                    .addComponent(lblNome)
                    .addComponent(newEng, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(btnIncluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        pnlNovoLayout.setVerticalGroup(
            pnlNovoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNovoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblNovo)
                .addGap(15, 15, 15)
                .addComponent(lblNome)
                .addGap(5, 5, 5)
                .addComponent(newEng, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pnlLista.setBackground(new java.awt.Color(255, 255, 255));
        pnlLista.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 224)));

        lblBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBuscar.setForeground(new java.awt.Color(102, 102, 102));
        lblBuscar.setText("Buscar engenheiro:");

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblEngenheiros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblEngenheiros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Nome", "Uso", "Função", "CREA", "Admissão"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEngenheiros.setRowHeight(30);
        jScrollPane1.setViewportView(tblEngenheiros);

        btnEditar.setText("Editar");

        btnExcluir.setForeground(new java.awt.Color(220, 38, 38));
        btnExcluir.setText("Excluir");

        javax.swing.GroupLayout pnlListaLayout = new javax.swing.GroupLayout(pnlLista);
        pnlLista.setLayout(pnlListaLayout);
        pnlListaLayout.setHorizontalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addComponent(lblBuscar)
                        .addGap(10, 10, 10)
                        .addComponent(txtBuscar))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListaLayout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addGap(10, 10, 10)
                        .addComponent(btnExcluir)))
                .addGap(20, 20, 20))
        );
        pnlListaLayout.setVerticalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout pnlFundoLayout = new javax.swing.GroupLayout(pnlFundo);
        pnlFundo.setLayout(pnlFundoLayout);
        pnlFundoLayout.setHorizontalGroup(
            pnlFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFundoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFundoLayout.createSequentialGroup()
                        .addGroup(pnlFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInicio)
                            .addGroup(pnlFundoLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(lblTitulo)))
                        .addGap(726, 726, 726))
                    .addGroup(pnlFundoLayout.createSequentialGroup()
                        .addGroup(pnlFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlFundoLayout.createSequentialGroup()
                                .addComponent(pnlNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(pnlLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(30, 30, 30))))
        );
        pnlFundoLayout.setVerticalGroup(
            pnlFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFundoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnInicio)
                .addGap(15, 15, 15)
                .addComponent(lblTitulo)
                .addGap(39, 39, 39)
                .addComponent(lblMensagem)
                .addGap(15, 15, 15)
                .addGroup(pnlFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTopo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTopo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        new JFInicio().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFengenheiros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFengenheiros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFengenheiros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFengenheiros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFengenheiros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnSair;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMensagem;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNovo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField newEng;
    private javax.swing.JPanel pnlFundo;
    private javax.swing.JPanel pnlLista;
    private javax.swing.JPanel pnlNovo;
    private javax.swing.JPanel pnlTopo;
    private javax.swing.JTable tblEngenheiros;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
