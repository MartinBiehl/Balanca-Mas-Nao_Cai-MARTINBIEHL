package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.obras;

public class DaoObras {

    private final DataSource dataSource;

    public DaoObras(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Monta o objeto a partir da linha atual do ResultSet
    // Atenção: no banco a coluna se chama "dtDdmissaoEngenheiro" (com "Dd")
    private obras montaObra(ResultSet rs) throws SQLException {
        obras obra = new obras();
        obra.setIdObra(rs.getInt("idObra"));
        obra.setDescricao(rs.getString("Descricao"));
        obra.setDtInicio(rs.getObject("dtInicio", LocalDate.class));
        obra.setDtFim(rs.getObject("dtFim", LocalDate.class));
        obra.setIdEngenheiro(rs.getInt("idEngenheiro"));
        obra.setNrCrea(rs.getString("nrCrea"));
        obra.setDtAdmissaoEngenheiro(rs.getObject("dtDdmissaoEngenheiro", LocalDate.class));
        return obra;
    }

    public ArrayList<obras> consulta() {
        String sql = "SELECT * FROM obras ORDER BY idObra";
        ArrayList<obras> lista = new ArrayList<>();

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(montaObra(rs));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar obras: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return lista;
    }

    public obras consultaPorId(int idObra) {
        String sql = "SELECT * FROM obras WHERE idObra = ?";
        obras obra = null;

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idObra);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    obra = montaObra(rs);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar obra: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return obra;
    }

    public ArrayList<obras> seleciona(String descricao) {
        String sql = "SELECT * FROM obras WHERE (Descricao LIKE ?) ORDER BY idObra";
        ArrayList<obras> lista = new ArrayList<>();

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + descricao + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(montaObra(rs));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar obras: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return lista;
    }

    public boolean incluir(obras obra) {
        // idObra é AUTO_INCREMENT: o banco gera o número
        String sql = "INSERT INTO obras "
                + "(Descricao, dtInicio, dtFim, idEngenheiro, nrCrea, dtDdmissaoEngenheiro) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, obra.getDescricao());
            ps.setObject(2, obra.getDtInicio());
            ps.setObject(3, obra.getDtFim());
            ps.setInt(4, obra.getIdEngenheiro());
            ps.setString(5, obra.getNrCrea());
            ps.setObject(6, obra.getDtAdmissaoEngenheiro());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao incluir obra: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }

    public boolean alterar(obras obra) {
        String sql = "UPDATE obras SET Descricao = ?, dtInicio = ?, dtFim = ?, "
                + "idEngenheiro = ?, nrCrea = ?, dtDdmissaoEngenheiro = ? "
                + "WHERE idObra = ?";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, obra.getDescricao());
            ps.setObject(2, obra.getDtInicio());
            ps.setObject(3, obra.getDtFim());
            ps.setInt(4, obra.getIdEngenheiro());
            ps.setString(5, obra.getNrCrea());
            ps.setObject(6, obra.getDtAdmissaoEngenheiro());
            ps.setInt(7, obra.getIdObra());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao alterar obra: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }

    public boolean excluir(int idObra) {
        String sql = "DELETE FROM obras WHERE idObra = ?";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idObra);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao excluir obra: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }
}
