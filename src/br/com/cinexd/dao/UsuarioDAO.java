package br.com.cinexd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cinexd.dao.utils.ConnectionFactory;
import br.com.cinexd.models.Usuario;

public class UsuarioDAO {

	private Connection conexao = null;

	public void inserir(Usuario usuario) {

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(
					"INSERT INTO usuario (nome,cpf,login,senha,funcao,perfil,status) VALUES (?,?,?,?,?,?,?)");
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getCpf());
			statement.setString(3, usuario.getLogin());
			statement.setString(4, usuario.getSenha());
			statement.setString(5, usuario.getFuncao());
			statement.setString(6, usuario.getPerfil());
			statement.setInt(7, usuario.getStatus());
			statement.execute();
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void desativar(int status, int id) {

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement("UPDATE usuario SET status = ? WHERE id = ?");
			statement.setInt(1, status);
			statement.setInt(2, id);
			statement.executeUpdate();
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void editar(Usuario usuario) {

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao
					.prepareStatement("UPDATE usuario SET nome=?, cpf=?, login=?, funcao=?, perfil=? WHERE id =?");
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getCpf());
			statement.setString(3, usuario.getLogin());
			statement.setString(4, usuario.getFuncao());
			statement.setString(5, usuario.getPerfil());
			statement.setInt(6, usuario.getId());
			statement.executeUpdate();
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterarSenha(Usuario usuario) {

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement("UPDATE usuario SET senha = ? WHERE id = ?");
			statement.setString(1, usuario.getSenha());
			statement.setInt(2, usuario.getId());
			statement.executeUpdate();
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Usuario> listar() {

		List<Usuario> lista = new ArrayList<Usuario>();

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao
					.prepareStatement("SELECT * FROM usuario WHERE status = 1 ORDER BY nome");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setLogin(rs.getString("login"));
				usuario.setFuncao(rs.getString("funcao"));
				usuario.setPerfil(rs.getString("perfil"));
				lista.add(usuario);
			}
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Usuario obterPorId(int id) {

		Usuario usuario = new Usuario();
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement("SELECT * FROM usuario WHERE id = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setFuncao(rs.getString("funcao"));
				usuario.setPerfil(rs.getString("perfil"));
			}
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}
	
	public boolean verificarRegistroExistente(Usuario usuario) {

		boolean retorno = false;

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement("SELECT * FROM usuario WHERE cpf = ?");
			statement.setString(1, usuario.getCpf());
			ResultSet rs = statement.executeQuery();
			retorno = rs.next();
			conexao.close();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println(retorno);
		return retorno;
	}

	public Usuario autenticar(String login, String senha) {
		Usuario usuario = null;

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao
					.prepareStatement("SELECT * FROM usuario WHERE login =? AND senha = ?");
			statement.setString(1, login);
			statement.setString(2, senha);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPerfil(rs.getString("perfil"));
			}
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
}