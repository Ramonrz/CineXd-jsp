package br.com.cinexd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cinexd.dao.utils.ConnectionFactory;
import br.com.cinexd.models.Filme;

public class FilmeDAO {

	private Connection conexao = null;

	public void inserir(Filme filme) {

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(
					"INSERT INTO filme (titulo,genero,idioma,duracao,faixa_etaria,status) VALUES (?,?,?,?,?,?)");
			statement.setString(1, filme.getTitulo());
			statement.setString(2, filme.getGenero());
			statement.setString(3, filme.getIdioma());
			statement.setInt(4, filme.getDuracao());
			statement.setInt(5, filme.getFaixaEtaria());
			statement.setInt(6, filme.getStatus());
			statement.execute();
			conexao.close();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void desativar(int status, int id) {

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement("UPDATE filme SET status = ? WHERE id = ?");
			statement.setInt(1, status);
			statement.setInt(2, id);
			statement.executeUpdate();
			conexao.close();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void editar(Filme filme) {

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(
					"UPDATE filme SET titulo=?, genero=?, idioma=?, duracao=?, faixa_etaria=? WHERE id=?");
			statement.setString(1, filme.getTitulo());
			statement.setString(2, filme.getGenero());
			statement.setString(3, filme.getIdioma());
			statement.setInt(4, filme.getDuracao());
			statement.setInt(5, filme.getFaixaEtaria());
			statement.setInt(6, filme.getId());
			statement.executeUpdate();
			conexao.close();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Filme> listar() {

		List<Filme> lista = new ArrayList<Filme>();

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao
					.prepareStatement("SELECT * FROM filme WHERE status = 1 ORDER BY titulo");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Filme filme = new Filme();
				filme.setId(rs.getInt("id"));
				filme.setTitulo(rs.getString("titulo"));
				filme.setGenero(rs.getString("genero"));
				filme.setIdioma(rs.getString("idioma"));
				filme.setDuracao(rs.getInt("duracao"));
				filme.setFaixaEtaria(rs.getInt("faixa_etaria"));
				lista.add(filme);
			}
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Filme obterPorId(int id) {

		Filme filme = new Filme();

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement("SELECT * FROM filme WHERE id = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				filme.setId(rs.getInt("id"));
				filme.setTitulo(rs.getString("titulo"));
				filme.setGenero(rs.getString("genero"));
				filme.setIdioma(rs.getString("idioma"));
				filme.setDuracao(rs.getInt("duracao"));
				filme.setFaixaEtaria(rs.getInt("faixa_etaria"));
			}
			conexao.close();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return filme;
	}
	
	public boolean verificarRegistroExistente(Filme filme) {

		boolean retorno = false;

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement("SELECT * FROM filme WHERE titulo = ? AND idioma =?");
			statement.setString(1, filme.getTitulo());
			statement.setString(2, filme.getIdioma());
			ResultSet rs = statement.executeQuery();
			retorno = rs.next();
			conexao.close();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}
}