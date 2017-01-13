package br.com.cinexd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cinexd.dao.utils.ConnectionFactory;
import br.com.cinexd.models.Sala;

public class SalaDAO {

	private Connection conexao = null;

	public void inserir(Sala sala) {
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(
					"INSERT INTO sala (numero, categoria ,qtd_poltrona, qtd_fileira, qtd_poltrona_fileira ,status) VALUES (?,?,?,?,?,?)");
			statement.setInt(1, sala.getNumero());
			statement.setString(2, sala.getCategoria());
			statement.setInt(3, sala.getQuantidadePoltrona());
			statement.setInt(4, sala.getQuantidadeFileira());
			statement.setInt(5, sala.getQuantidadePoltronaFileira());
			statement.setInt(6, sala.getStatus());
			statement.execute();
			conexao.close();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void desativar(int status, int id) {

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement("UPDATE sala SET status = ? WHERE id = ?");
			statement.setInt(1, status);
			statement.setInt(2, id);
			statement.executeUpdate();
			conexao.close();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void editar(Sala sala) {
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao
					.prepareStatement("UPDATE sala SET numero=?, categoria=?, qtd_fileira=?, qtd_poltrona_fileira=?, qtd_poltrona=? WHERE id=?");
			statement.setInt(1, sala.getNumero());
			statement.setString(2, sala.getCategoria());
			statement.setInt(3, sala.getQuantidadeFileira());
			statement.setInt(4, sala.getQuantidadePoltronaFileira());
			statement.setInt(5, sala.getQuantidadePoltrona());
			statement.setInt(6, sala.getId());
			statement.executeUpdate();
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Sala> listar() {

		List<Sala> lista = new ArrayList<Sala>();

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao
					.prepareStatement("SELECT * FROM sala WHERE status = 1 ORDER BY numero");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Sala sala = new Sala();
				sala.setId(rs.getInt("id"));
				sala.setNumero(rs.getInt("numero"));
				sala.setCategoria(rs.getString("categoria"));
				sala.setQuantidadePoltrona(rs.getInt("qtd_poltrona"));
				sala.setQuantidadeFileira(rs.getInt("qtd_fileira"));
				sala.setQuantidadePoltronaFileira(rs.getInt("qtd_poltrona_fileira"));
				lista.add(sala);
			}
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Sala obterPorId(int id) {

		Sala sala = new Sala();

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement("SELECT * FROM sala WHERE id = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				sala.setId(rs.getInt("id"));
				sala.setNumero(rs.getInt("numero"));
				sala.setCategoria(rs.getString("categoria"));
				sala.setQuantidadePoltrona(rs.getInt("qtd_poltrona"));
				sala.setQuantidadeFileira(rs.getInt("qtd_fileira"));
				sala.setQuantidadePoltronaFileira(rs.getInt("qtd_poltrona_fileira"));
			}
			conexao.close();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return sala;
	}

	public boolean verificarRegistroExistente(Sala sala) {

		boolean retorno = false;

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement("SELECT * FROM sala WHERE numero = ?");
			statement.setInt(1, sala.getNumero());
			ResultSet rs = statement.executeQuery();
			retorno = rs.next();
			conexao.close();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println(retorno);
		return retorno;
	}
}