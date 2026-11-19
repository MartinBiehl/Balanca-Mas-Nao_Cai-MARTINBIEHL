package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.usuarios;

public class DaoUsuarios {

    private final DataSource dataSource;

    public DaoUsuarios(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Monta o objeto a partir da linha atual do ResultSet
    private usuarios montaUsuario(ResultSet rs) throws SQLException {
        usuarios usuario = new usuarios();
        usuario.setIdUsuario(rs.getInt("idUsuario"));
        usuario.setNome(rs.getString("Nome"));
        usuario.setEmail(rs.getString("Email"));
        usuario.setSenha(rs.getString("Senha"));
        return usuario;
    }

    public ArrayList<usuarios> consulta() {
        String sql = "SELECT * FROM usuarios ORDER BY idUsuario";
        ArrayList<usuarios> lista = new ArrayList<>();

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(montaUsuario(rs));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar usuários: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return lista;
    }

    public usuarios consultaPorId(int idUsuario) {
        String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";
        usuarios usuario = null;

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = montaUsuario(rs);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar usuário: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return usuario;
    }

    public ArrayList<usuarios> seleciona(String nome) {
        String sql = "SELECT * FROM usuarios WHERE (Nome LIKE ?) ORDER BY idUsuario";
        ArrayList<usuarios> lista = new ArrayList<>();

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + nome + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(montaUsuario(rs));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar usuários: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return lista;
    }

    // Login: devolve o usuário se e-mail e senha conferem, ou null se não conferem
    public usuarios autentica(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE Email = ? AND Senha = ?";
        usuarios usuario = null;

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = montaUsuario(rs);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao fazer login: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return usuario;
    }

    // O login procura pelo e-mail, então ele não pode se repetir.
    // idIgnorar = id do próprio usuário na alteração (use 0 na inclusão)
    public boolean existeEmail(String email, int idIgnorar) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE Email = ? AND idUsuario <> ?";
        boolean existe = false;

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setInt(2, idIgnorar);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    existe = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao consultar e-mail: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return existe;
    }

    public boolean incluir(usuarios usuario) {
        // idUsuario é AUTO_INCREMENT: o banco gera o número
        String sql = "INSERT INTO usuarios "
                + "(Nome, Email, Senha) "
                + "VALUES (?, ?, ?)";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao incluir usuário: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }

    public boolean alterar(usuarios usuario) {
        String sql = "UPDATE usuarios SET Nome = ?, Email = ?, Senha = ? "
                + "WHERE idUsuario = ?";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setInt(4, usuario.getIdUsuario());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao alterar usuário: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }

    public boolean excluir(int idUsuario) {
        String sql = "DELETE FROM usuarios WHERE idUsuario = ?";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idUsuario);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao excluir usuário: " + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }

        return false;
    }
}
