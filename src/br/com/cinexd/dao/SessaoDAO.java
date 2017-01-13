package br.com.cinexd.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.cinexd.dao.utils.ConnectionFactory;
import br.com.cinexd.models.Filme;
import br.com.cinexd.models.Sala;
import br.com.cinexd.models.Sessao;

public class SessaoDAO {
	private Connection conexao = null;

	public void inserir(Sessao sessao) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		String sql = "INSERT INTO sessao ( sessao_data, " + " sessao_sala, " + " sessao_status, " + " sessao_filme, "
				+ " sessao_horario, " + " sessao_observacao, " + " sessao_valor_Inteiro,"
				+ " sessao_valor_meia) VALUES (" + "DATE_FORMAT(STR_TO_DATE('" + formato.format(sessao.getData())
				+ "', '%d/%m/%Y'), '%Y-%m-%d') ," + " " + sessao.getSala().getId() + "," + " " + sessao.getStatus()
				+ "," + " " + sessao.getFilme().getId() + "," + " '" + sessao.getHorario() + "'," + " '"
				+ sessao.getObservacao() + "'," + " '" + sessao.getValorInteira() + "'," + "'" + sessao.getValorMeia()
				+ "');";

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.execute();
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void desativar(Sessao sessao) {
		String sql = "UPDATE sessao SET sessao_status=? WHERE sessao_id=?; ";

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, sessao.getStatus());
			statement.setInt(2, sessao.getId());
			statement.execute();
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void editar(Sessao sessao) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "UPDATE sessao SET sessao_data=DATE_FORMAT(STR_TO_DATE('" + formato.format(sessao.getData())
				+ "', '%d/%m/%Y'), '%Y-%m-%d')," + "sessao_sala='" + sessao.getSala().getId() + "'," + " sessao_filme='"
				+ sessao.getFilme().getId() + "'," + " sessao_horario='" + sessao.getHorario() + "',"
				+ " sessao_observacao='" + sessao.getObservacao() + "'," + " sessao_valor_Inteiro='"
				+ sessao.getValorInteira() + "'," + " sessao_valor_meia='" + sessao.getValorMeia() + "' "
				+ "WHERE sessao_id='" + sessao.getId() + "';";
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.execute();
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Sessao> obterPorFilme(int filmeId) {
		List<Sessao> listar = new ArrayList<Sessao>();
		SalaDAO salaDAO = new SalaDAO();
		String sql = "SELECT sessao_id, sessao_sala, sessao_horario, sessao_data FROM sessao WHERE  sessao_filme= ? and sessao_status = 1";

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.setInt(1, filmeId);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Sessao sessao = new Sessao();
				sessao.setId(rs.getInt("sessao_id"));
				sessao.setHorario(rs.getString("sessao_horario"));
				sessao.setData(rs.getDate("sessao_data"));
				sessao.setSala(salaDAO.obterPorId(rs.getInt("sessao.sessao_sala")));
				listar.add(sessao);
			}
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return listar;

	}

	public boolean verificarDadosExistente(Sessao sessao) {

		boolean retorno = false;

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");// %d/%m/%y

		String sql = "SELECT sessao_id FROM sessao WHERE DATE_FORMAT(sessao_data, '%y-%m-%d') = "
				+ formato.format(sessao.getData())
				+ " AND sessao_horario = ? AND sessao_sala = ? AND sessao_status = 1";

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, sessao.getHorario());
			statement.setInt(2, sessao.getSala().getId());
			ResultSet rs = statement.executeQuery();
			retorno = rs.next();
			conexao.close();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		System.out.println(retorno);
		return retorno;

	}

	public Sessao obterFilme(int filmeId) {

		String sql = "SELECT * FROM sessao " + "LEFT JOIN  sala ON sessao.sessao_sala=sala.id "
				+ "LEFT JOIN filme ON  sessao.sessao_filme = filme.id "
				+ " WHERE sessao.sessao_filme=? AND sessao.sessao_status=1";

		Sessao sessao = new Sessao();
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.setInt(1, filmeId);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				sessao.setId(rs.getInt("sessao_id"));
				sessao.setHorario(rs.getString("sessao_horario"));
				sessao.setData(rs.getDate("sessao_data"));
				sessao.setFilme(new Filme());
				sessao.getFilme().setId(rs.getInt("sessao_filme"));
				sessao.getFilme().setTitulo(rs.getString("titulo"));

				sessao.setObservacao(rs.getString("sessao_observacao"));
				sessao.setSala(new Sala());
				sessao.getSala().setId(rs.getInt("sessao_sala"));
				sessao.getSala().setNumero(rs.getInt("numero"));
				sessao.setValorInteira(rs.getFloat("sessao_valor_Inteiro"));

			}
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return sessao;

	}

	public Sessao obterPorId(Sessao sessaoObj) {

		String sql = "SELECT * FROM sessao " + "LEFT JOIN  sala ON sessao.sessao_sala=sala.id "
				+ "LEFT JOIN filme ON  sessao.sessao_filme = filme.id "
				+ " WHERE sessao.sessao_id=? AND sessao.sessao_status=1";

		Sessao sessao = new Sessao();
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.setInt(1, sessaoObj.getId());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				sessao.setId(rs.getInt("sessao_id"));
				sessao.setHorario(rs.getString("sessao_horario"));

				sessao.setData(rs.getDate("sessao_data"));
				sessao.setFilme(new Filme());
				sessao.getFilme().setId(rs.getInt("sessao_filme"));
				sessao.getFilme().setTitulo(rs.getString("titulo"));
				sessao.getFilme().setGenero(rs.getString("genero"));
				sessao.getFilme().setIdioma(rs.getString("idioma"));
				sessao.getFilme().setDuracao(rs.getInt("duracao"));
				sessao.getFilme().setFaixaEtaria(rs.getInt("faixa_etaria"));

				sessao.setObservacao(rs.getString("sessao_observacao"));
				sessao.setSala(new Sala());
				sessao.getSala().setId(rs.getInt("sessao_sala"));
				sessao.getSala().setNumero(rs.getInt("numero"));
				sessao.getSala().setQuantidadeFileira(rs.getInt("qtd_fileira"));
				sessao.getSala().setQuantidadePoltronaFileira(rs.getInt("qtd_poltrona_fileira"));

				sessao.setValorInteira(rs.getFloat("sessao_valor_Inteiro"));
				sessao.setValorMeia(rs.getFloat("sessao_valor_meia"));
			}

			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return sessao;

	}

	public List<Sessao> listar() {
		List<Sessao> listar = new ArrayList<Sessao>();
		String sql = "SELECT * FROM sessao " + "LEFT JOIN  sala ON sessao.sessao_sala=sala.id "
				+ "LEFT JOIN filme ON  sessao.sessao_filme = filme.id "
				+ " WHERE sessao.sessao_status=1 ORDER BY sessao.sessao_data DESC";
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Sessao sessao = new Sessao();

				sessao.setId(rs.getInt("sessao_id"));
				sessao.setHorario(rs.getString("sessao_horario"));
				sessao.setData(rs.getDate("sessao_data"));
				sessao.setFilme(new Filme());
				sessao.getFilme().setTitulo(rs.getString("titulo"));
				sessao.setObservacao(rs.getString("sessao_observacao"));
				sessao.setSala(new Sala());
				sessao.getSala().setNumero(rs.getInt("numero"));

				listar.add(sessao);

			}
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listar;
	}

	public List<Sessao> listarHorario() {
		List<Sessao> listar = new ArrayList<Sessao>();
		String sql = "SELECT * FROM sessao "
				+ "LEFT JOIN  sala ON sessao.sessao_sala=sala.id "
				+ "LEFT JOIN filme ON  sessao.sessao_filme = filme.id "
				+ "WHERE sessao.sessao_status = 1 "
				+ "GROUP BY sessao.sessao_id "
				+ "ORDER BY sessao.sessao_horario DESC";
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Sessao sessao = new Sessao();

				sessao.setId(rs.getInt("sessao_id"));
				sessao.setHorario(rs.getString("sessao_horario"));
				sessao.setData(rs.getDate("sessao_data"));
				sessao.setFilme(new Filme());
				sessao.getFilme().setTitulo(rs.getString("titulo"));
				sessao.setObservacao(rs.getString("sessao_observacao"));
				sessao.setSala(new Sala());
				sessao.getSala().setNumero(rs.getInt("numero"));
				sessao.setValorInteira(rs.getFloat("sessao_valor_Inteiro"));

				listar.add(sessao);

			}
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listar;
	}
}
