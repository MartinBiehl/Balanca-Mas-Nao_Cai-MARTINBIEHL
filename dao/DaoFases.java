package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.fases;

public class DaoFases {

    private final DataSource dataSource;

    public DaoFases(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<fases> consulta() {
        String sql = "SELECT * FROM fases ORDER BY idFase";
        ArrayList<fases> lista = new ArrayList<>();

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                fases fase = new fases();
                fase.setIdFase(rs.getInt("idFase"));
                fase.setDsFase(rs.getString("dsFase"));

                lista.add(fase);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar fases: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return lista;
    }

    public fases consultaPorId(int idFase) {
        String sql = "SELECT * FROM fases WHERE idFase = ?";
        fases fase = null;

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idFase);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    fase = new fases();
                    fase.setIdFase(rs.getInt("idFase"));
                    fase.setDsFase(rs.getString("dsFase"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar fase: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return fase;
    }

    public ArrayList<fases> seleciona(String descricao) {
        String sql = "SELECT * FROM fases WHERE (dsFase LIKE ?) ORDER BY idFase";
        ArrayList<fases> lista = new ArrayList<>();

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + descricao + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    fases fase = new fases();
                    fase.setIdFase(rs.getInt("idFase"));
                    fase.setDsFase(rs.getString("dsFase"));

                    lista.add(fase);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar fases: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return lista;
    }

    public boolean incluir(fases fase) {
        // idFase é AUTO_INCREMENT: o banco gera o número
        String sql = "INSERT INTO fases (dsFase) VALUES (?)";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, fase.getDsFase());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao incluir fase: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }

    public boolean alterar(fases fase) {
        String sql = "UPDATE fases SET dsFase = ? WHERE idFase = ?";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, fase.getDsFase());
            ps.setInt(2, fase.getIdFase());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao alterar fase: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }

    public boolean excluir(int idFase) {
        String sql = "DELETE FROM fases WHERE idFase = ?";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idFase);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao excluir fase: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }
}