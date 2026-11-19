package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.funcoes;

public class Daofuncoes {

    private final DataSource dataSource;

    public Daofuncoes(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Monta o objeto a partir da linha atual do ResultSet
    private funcoes montaFuncao(ResultSet rs) throws SQLException {
        funcoes funcao = new funcoes();
        funcao.setIdFuncao(rs.getInt("idFuncao"));
        funcao.setDsFuncao(rs.getString("dsFuncao"));
        return funcao;
    }

    public ArrayList<funcoes> consulta() {
        String sql = "SELECT * FROM funcoes ORDER BY idFuncao";
        ArrayList<funcoes> lista = new ArrayList<>();

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(montaFuncao(rs));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar funções: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return lista;
    }

    public funcoes consultaPorId(int idFuncao) {
        String sql = "SELECT * FROM funcoes WHERE idFuncao = ?";
        funcoes funcao = null;

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idFuncao);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    funcao = montaFuncao(rs);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar função: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return funcao;
    }

    public ArrayList<funcoes> seleciona(String descricao) {
        String sql = "SELECT * FROM funcoes WHERE (dsFuncao LIKE ?) ORDER BY idFuncao";
        ArrayList<funcoes> lista = new ArrayList<>();

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + descricao + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(montaFuncao(rs));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar funções: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return lista;
    }

    public boolean incluir(funcoes funcao) {
        // idFuncao é AUTO_INCREMENT: o banco gera o número
        String sql = "INSERT INTO funcoes (dsFuncao) VALUES (?)";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, funcao.getDsFuncao());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao incluir função: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }

    public boolean alterar(funcoes funcao) {
        String sql = "UPDATE funcoes SET dsFuncao = ? WHERE idFuncao = ?";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, funcao.getDsFuncao());
            ps.setInt(2, funcao.getIdFuncao());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao alterar função: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }

    public boolean excluir(int idFuncao) {
        String sql = "DELETE FROM funcoes WHERE idFuncao = ?";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idFuncao);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao excluir função: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }
}
