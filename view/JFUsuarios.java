/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.DaoUsuarios;
import dao.DataSource;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.usuarios;

/**
 *
 * @author martin.biehl
 */
public class JFUsuarios extends javax.swing.JFrame {

    // Campos da tela: txtNome1 = Nome, txtNome3 = E-mail, txtNome2 = Senha
    private final DaoUsuarios dao = new DaoUsuarios(new DataSource());

    /**
     * Creates new form JFUsuarios
     */
    public JFUsuarios() {
        initComponents();
        setLocationRelativeTo(null);

        if (JFLogin.usuarioLogado != null) {
            lblUsuario1.setText(JFLogin.usuarioLogado.getNome());
        }

        btnEditar1.addActionListener(evt -> editar());
        btnExcluir1.addActionListener(evt -> excluir());
        btnSair1.addActionListener(evt -> sair());
        txtBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                carregarTabela();
            }
        });

        carregarTabela();
    }

    // Preenche a tabela com o que vem do banco (filtrando pelo campo Buscar)
    private void carregarTabela() {
        String busca = txtBuscar1.getText().trim();
        ArrayList<usuarios> lista = busca.isEmpty() ? dao.consulta() : dao.seleciona(busca);

        DefaultTableModel modelo = (DefaultTableModel) tblEngenheiros1.getModel();
        modelo.setRowCount(0);

        for (usuarios usuario : lista) {
            modelo.addRow(new Object[]{usuario.getIdUsuario(), usuario.getNome(), usuario.getEmail()});
        }
    }

    // Devolve o id da linha selecionada, ou -1 se nenhuma estiver selecionada
    private int idSelecionado() {
        int linha = tblEngenheiros1.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário na tabela.");
            return -1;
        }
        return (int) tblEngenheiros1.getValueAt(linha, 0);
    }

    // Confere os dados antes de gravar. Devolve a mensagem de erro, ou null se estiver tudo certo.
    private String validar(String nome, String email, String senha, int idUsuario) {
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            return "Preencha nome, e-mail e senha.";
        }
        if (!email.contains("@")) {
            return "Informe um e-mail válido.";
        }
        if (senha.length() > 12) {
            return "A senha pode ter no máximo 12 caracteres.";
        }
        if (nome.length() > 50 || email.length() > 80) {
            return "Nome (até 50) ou e-mail (até 80 caracteres) muito longo.";
        }
        if (dao.existeEmail(email, idUsuario)) {
            return "Já existe um usuário com o e-mail " + email + ".";
        }
        return null;
    }

    private void incluir() {
        String nome = txtNome1.getText().trim();
        String email = txtNome3.getText().trim();
        String senha = txtNome2.getText();

        String erro = validar(nome, email, senha, 0);
        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro);
            return;
        }

        usuarios usuario = new usuarios();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        if (dao.incluir(usuario)) {
            txtNome1.setText("");
            txtNome3.setText("");
            txtNome2.setText("");
            carregarTabela();
        }
    }

    private void editar() {
        int id = idSelecionado();
        if (id < 0) {
            return;
        }

        usuarios usuario = dao.consultaPorId(id);
        if (usuario == null) {
            return;
        }

        String nome = JOptionPane.showInputDialog(this, "Nome:", usuario.getNome());
        if (nome == null) {
            return; // cancelou
        }
        String email = JOptionPane.showInputDialog(this, "E-mail:", usuario.getEmail());
        if (email == null) {
            return;
        }
        String senha = JOptionPane.showInputDialog(this, "Nova senha (deixe em branco para manter a atual):");
        if (senha == null) {
            return;
        }
        if (senha.isEmpty()) {
            senha = usuario.getSenha();
        }

        String erro = validar(nome.trim(), email.trim(), senha, id);
        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro);
            return;
        }

        usuario.setNome(nome.trim());
        usuario.setEmail(email.trim());
        usuario.setSenha(senha);

        if (dao.alterar(usuario)) {
            // se alterou o próprio usuário, atualiza o nome no topo
            if (JFLogin.usuarioLogado != null && JFLogin.usuarioLogado.getIdUsuario() == id) {
                JFLogin.usuarioLogado = usuario;
                lblUsuario1.setText(usuario.getNome());
            }
            carregarTabela();
        }
    }

    private void excluir() {
        int id = idSelecionado();
        if (id < 0) {
            return;
        }

        if (JFLogin.usuarioLogado != null && JFLogin.usuarioLogado.getIdUsuario() == id) {
            JOptionPane.showMessageDialog(this, "Você não pode excluir o usuário com que está logado.");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this, "Excluir o usuário nº " + id + "?",
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

        pnlTopo1 = new javax.swing.JPanel();
        lblLogo1 = new javax.swing.JLabel();
        lblUsuario1 = new javax.swing.JLabel();
        btnSair1 = new javax.swing.JButton();
        pnlFundo1 = new javax.swing.JPanel();
        btnInicio1 = new javax.swing.JButton();
        lblTitulo1 = new javax.swing.JLabel();
        lblMensagem1 = new javax.swing.JLabel();
        pnlNovo1 = new javax.swing.JPanel();
        lblNovo1 = new javax.swing.JLabel();
        lblNome1 = new javax.swing.JLabel();
        txtNome1 = new javax.swing.JTextField();
        btnIncluir1 = new javax.swing.JButton();
        txtNome2 = new javax.swing.JTextField();
        txtNome3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlLista1 = new javax.swing.JPanel();
        lblBuscar1 = new javax.swing.JLabel();
        txtBuscar1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEngenheiros1 = new javax.swing.JTable();
        btnEditar1 = new javax.swing.JButton();
        btnExcluir1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlTopo1.setBackground(new java.awt.Color(255, 255, 255));
        pnlTopo1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(224, 224, 224)));

        lblLogo1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblLogo1.setText("Balança-Mas-Não-Cai");

        lblUsuario1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUsuario1.setForeground(new java.awt.Color(102, 102, 102));
        lblUsuario1.setText("Usuario Teste");

        btnSair1.setText("Sair");

        javax.swing.GroupLayout pnlTopo1Layout = new javax.swing.GroupLayout(pnlTopo1);
        pnlTopo1.setLayout(pnlTopo1Layout);
        pnlTopo1Layout.setHorizontalGroup(
            pnlTopo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopo1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(lblLogo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUsuario1)
                .addGap(15, 15, 15)
                .addComponent(btnSair1)
                .addGap(30, 30, 30))
        );
        pnlTopo1Layout.setVerticalGroup(
            pnlTopo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopo1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlTopo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblLogo1)
                    .addComponent(lblUsuario1)
                    .addComponent(btnSair1))
                .addGap(18, 18, 18))
        );

        btnInicio1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnInicio1.setForeground(new java.awt.Color(204, 102, 0));
        btnInicio1.setText("< Início");
        btnInicio1.setBorderPainted(false);
        btnInicio1.setContentAreaFilled(false);
        btnInicio1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicio1ActionPerformed(evt);
            }
        });

        lblTitulo1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo1.setText("Usuários");

        lblMensagem1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMensagem1.setForeground(new java.awt.Color(200, 0, 0));

        pnlNovo1.setBackground(new java.awt.Color(255, 255, 255));
        pnlNovo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 224)));

        lblNovo1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNovo1.setText("Novo Usuário");

        lblNome1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNome1.setForeground(new java.awt.Color(68, 68, 68));
        lblNome1.setText("Nome");

        txtNome1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnIncluir1.setBackground(new java.awt.Color(30, 136, 229));
        btnIncluir1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIncluir1.setForeground(new java.awt.Color(255, 255, 255));
        btnIncluir1.setText("Incluir Usuário");
        btnIncluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluir1ActionPerformed(evt);
            }
        });

        txtNome2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNome2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNome2ActionPerformed(evt);
            }
        });

        txtNome3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNome3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNome3ActionPerformed(evt);
            }
        });

        jLabel1.setText("E-mail");

        jLabel2.setText("Senha");

        javax.swing.GroupLayout pnlNovo1Layout = new javax.swing.GroupLayout(pnlNovo1);
        pnlNovo1.setLayout(pnlNovo1Layout);
        pnlNovo1Layout.setHorizontalGroup(
            pnlNovo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNovo1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlNovo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNovo1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlNovo1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlNovo1Layout.createSequentialGroup()
                        .addGroup(pnlNovo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNome2)
                            .addComponent(txtNome1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                            .addComponent(btnIncluir1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNome3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlNovo1Layout.createSequentialGroup()
                                .addGroup(pnlNovo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNovo1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNome1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))))
        );
        pnlNovo1Layout.setVerticalGroup(
            pnlNovo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNovo1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblNovo1)
                .addGap(15, 15, 15)
                .addComponent(lblNome1)
                .addGap(5, 5, 5)
                .addComponent(txtNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnIncluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pnlLista1.setBackground(new java.awt.Color(255, 255, 255));
        pnlLista1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 224)));

        lblBuscar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBuscar1.setForeground(new java.awt.Color(102, 102, 102));
        lblBuscar1.setText("Buscar Usuário:");

        txtBuscar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblEngenheiros1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblEngenheiros1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Nome", "E-mail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEngenheiros1.setRowHeight(30);
        jScrollPane2.setViewportView(tblEngenheiros1);

        btnEditar1.setText("Editar");

        btnExcluir1.setForeground(new java.awt.Color(220, 38, 38));
        btnExcluir1.setText("Excluir");

        javax.swing.GroupLayout pnlLista1Layout = new javax.swing.GroupLayout(pnlLista1);
        pnlLista1.setLayout(pnlLista1Layout);
        pnlLista1Layout.setHorizontalGroup(
            pnlLista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLista1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlLista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLista1Layout.createSequentialGroup()
                        .addComponent(lblBuscar1)
                        .addGap(10, 10, 10)
                        .addComponent(txtBuscar1))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLista1Layout.createSequentialGroup()
                        .addComponent(btnEditar1)
                        .addGap(10, 10, 10)
                        .addComponent(btnExcluir1)))
                .addGap(20, 20, 20))
        );
        pnlLista1Layout.setVerticalGroup(
            pnlLista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLista1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlLista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscar1)
                    .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addGroup(pnlLista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar1)
                    .addComponent(btnExcluir1))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout pnlFundo1Layout = new javax.swing.GroupLayout(pnlFundo1);
        pnlFundo1.setLayout(pnlFundo1Layout);
        pnlFundo1Layout.setHorizontalGroup(
            pnlFundo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFundo1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlFundo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFundo1Layout.createSequentialGroup()
                        .addGroup(pnlFundo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInicio1)
                            .addGroup(pnlFundo1Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(lblTitulo1)))
                        .addGap(726, 726, 726))
                    .addGroup(pnlFundo1Layout.createSequentialGroup()
                        .addGroup(pnlFundo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMensagem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlFundo1Layout.createSequentialGroup()
                                .addComponent(pnlNovo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(pnlLista1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(30, 30, 30))))
        );
        pnlFundo1Layout.setVerticalGroup(
            pnlFundo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFundo1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnInicio1)
                .addGap(15, 15, 15)
                .addComponent(lblTitulo1)
                .addGap(24, 24, 24)
                .addComponent(lblMensagem1)
                .addGap(15, 15, 15)
                .addGroup(pnlFundo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFundo1Layout.createSequentialGroup()
                        .addComponent(pnlLista1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30))
                    .addGroup(pnlFundo1Layout.createSequentialGroup()
                        .addComponent(pnlNovo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTopo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlFundo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTopo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlFundo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicio1ActionPerformed
        new JFInicio().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnInicio1ActionPerformed

    private void txtNome2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNome2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNome2ActionPerformed

    private void txtNome3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNome3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNome3ActionPerformed

    private void btnIncluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluir1ActionPerformed
        incluir();
    }//GEN-LAST:event_btnIncluir1ActionPerformed

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
            java.util.logging.Logger.getLogger(JFUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar1;
    private javax.swing.JButton btnExcluir1;
    private javax.swing.JButton btnIncluir1;
    private javax.swing.JButton btnInicio1;
    private javax.swing.JButton btnSair1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBuscar1;
    private javax.swing.JLabel lblLogo1;
    private javax.swing.JLabel lblMensagem1;
    private javax.swing.JLabel lblNome1;
    private javax.swing.JLabel lblNovo1;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JLabel lblUsuario1;
    private javax.swing.JPanel pnlFundo1;
    private javax.swing.JPanel pnlLista1;
    private javax.swing.JPanel pnlNovo1;
    private javax.swing.JPanel pnlTopo1;
    private javax.swing.JTable tblEngenheiros1;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtNome1;
    private javax.swing.JTextField txtNome2;
    private javax.swing.JTextField txtNome3;
    // End of variables declaration//GEN-END:variables
}
