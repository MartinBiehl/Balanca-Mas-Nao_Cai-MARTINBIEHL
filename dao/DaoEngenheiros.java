package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Engenheiros;

public class DaoEngenheiros {

    private final DataSource dataSource;

    public DaoEngenheiros(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Monta o objeto a partir da linha atual do ResultSet
    private Engenheiros montaEngenheiro(ResultSet rs) throws SQLException {
        Engenheiros engenheiro = new Engenheiros();
        engenheiro.setIdEngenheiro(rs.getInt("idEngenheiro"));
        engenheiro.setDsNome(rs.getString("dsNome"));
        engenheiro.setNrCREA(rs.getString("nrCREA"));
        engenheiro.setDtAdmissao(rs.getObject("dtAdmissao", LocalDate.class));
        return engenheiro;
    }

    public ArrayList<Engenheiros> consulta() {
        String sql = "SELECT * FROM engenheiros ORDER BY idEngenheiro";
        ArrayList<Engenheiros> lista = new ArrayList<>();

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(montaEngenheiro(rs));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar engenheiros: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return lista;
    }

    public Engenheiros consultaPorId(int idEngenheiro) {
        String sql = "SELECT * FROM engenheiros WHERE idEngenheiro = ?";
        Engenheiros engenheiro = null;

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idEngenheiro);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    engenheiro = montaEngenheiro(rs);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar engenheiro: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return engenheiro;
    }

    public ArrayList<Engenheiros> seleciona(String nome) {
        String sql = "SELECT * FROM engenheiros WHERE (dsNome LIKE ?) ORDER BY idEngenheiro";
        ArrayList<Engenheiros> lista = new ArrayList<>();

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + nome + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(montaEngenheiro(rs));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar engenheiros: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return lista;
    }

    public boolean incluir(Engenheiros engenheiro) {
        // idEngenheiro é AUTO_INCREMENT: o banco gera o número
        String sql = "INSERT INTO engenheiros "
                + "(dsNome, nrCREA, dtAdmissao) "
                + "VALUES (?, ?, ?)";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, engenheiro.getDsNome());
            ps.setString(2, engenheiro.getNrCREA());
            ps.setObject(3, engenheiro.getDtAdmissao());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao incluir engenheiro: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }

    public boolean alterar(Engenheiros engenheiro) {
        String sql = "UPDATE engenheiros SET dsNome = ?, nrCREA = ?, dtAdmissao = ? "
                + "WHERE idEngenheiro = ?";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, engenheiro.getDsNome());
            ps.setString(2, engenheiro.getNrCREA());
            ps.setObject(3, engenheiro.getDtAdmissao());
            ps.setInt(4, engenheiro.getIdEngenheiro());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao alterar engenheiro: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }

    public boolean excluir(int idEngenheiro) {
        String sql = "DELETE FROM engenheiros WHERE idEngenheiro = ?";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idEngenheiro);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao excluir engenheiro: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }
}