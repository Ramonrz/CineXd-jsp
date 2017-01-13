package br.com.cinexd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.ant.SessionsTask;

import br.com.cinexd.dao.utils.ConnectionFactory;
import br.com.cinexd.models.Filme;
import br.com.cinexd.models.Sala;
import br.com.cinexd.models.Venda;
import br.com.cinexd.models.Usuario;
import br.com.cinexd.models.Sessao;

public class VendaDAO {

	private Connection conexao = null;

	public List<Venda> listar() {
		List<Venda> listar = new ArrayList<Venda>();
		String sql = "SELECT * FROM venda " + "LEFT JOIN usuario ON venda.venda_atendente=usuario.id "
				+ "LEFT JOIN sessao ON venda.venda_sessao=sessao.sessao_id "
				+ "LEFT JOIN filme ON sessao.sessao_filme=filme.id " + "LEFT JOIN sala ON sessao.sessao_sala=sala.id WHERE sessao.sessao_status = 1";
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				Venda venda = new Venda();
				venda.setId(rs.getInt("venda_id"));
				venda.setUsuario(new Usuario());
				venda.getUsuario().setNome(rs.getString("nome"));
				venda.setData(rs.getDate("venda_data"));
				venda.setSessao(new Sessao());
				venda.getSessao().setHorario(rs.getString("sessao_horario"));
				venda.getSessao().setFilme(new Filme());
				venda.getSessao().getFilme().setTitulo(rs.getString("titulo"));
				venda.getSessao().setSala(new Sala());
				venda.getSessao().getSala().setNumero(rs.getInt("numero"));
				venda.setValor(rs.getFloat("venda_valor_pago"));
				listar.add(venda);
			}
			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listar;

	}

	public List<Venda> gerarRelatorioLotacao() {

		List<Venda> lista = new ArrayList<Venda>();
		
		String sql = "select venda_sessao, filme.titulo, sessao_data, sessao_horario, sala.numero, sum(venda_qtde_ingresso_total) as 'Poltronas Ocupadas', sala.qtd_poltrona - sum(venda_qtde_ingresso_total) as 'Poltronas Livres', sala.qtd_poltrona "
				+ "from venda "
				+ "inner join sessao on venda_sessao = sessao_id "
				+ "inner join filme on sessao_filme = filme.id "
				+ "inner join sala on sessao_sala = sala.id "
				+ "group by venda_sessao;";

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Venda venda = new Venda();
				venda.setSessao(new Sessao());
				venda.getSessao().setId(rs.getInt("venda_sessao"));
				venda.getSessao().setHorario(rs.getString("sessao_horario"));
				venda.getSessao().setData(rs.getDate("sessao_data"));
				venda.setQtdeIngressoTotal(rs.getInt("Poltronas Ocupadas"));
				venda.setQtdeIngressoInteira(rs.getInt("Poltronas Livres"));
				venda.getSessao().setFilme(new Filme());
				venda.getSessao().getFilme().setTitulo(rs.getString("filme.titulo"));
				venda.getSessao().setSala(new Sala());
				venda.getSessao().getSala().setQuantidadePoltrona(rs.getInt("sala.qtd_poltrona"));
				venda.getSessao().getSala().setNumero(rs.getInt("sala.numero"));
				lista.add(venda);
			}
			conexao.close();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public int inserir(Venda venda) {
		int result = 0;
		String sql = "INSERT INTO venda (venda_atendente, venda_valor_pago, venda_data, venda_sessao, venda_qtde_ingresso_meia, venda_qtde_ingresso_inteira, venda_qtde_ingresso_total) VALUES (?, ?, NOW(), ?, ?, ?, ?);";
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, venda.getUsuario().getId());
			statement.setFloat(2, venda.getValor());
			statement.setInt(3, venda.getSessao().getId());
			statement.setInt(4, venda.getQtdeIngressoMeia());
			statement.setInt(5, venda.getQtdeIngressoInteira());
			statement.setInt(6, venda.getQtdeIngressoTotal());
			statement.execute();
			String sql1 = "SELECT venda_id FROM  venda order by venda_id desc";
			PreparedStatement query = conexao.prepareStatement(sql1);
			ResultSet array = query.executeQuery();
			if (array.next()) {
				result = array.getInt("venda_id");
			}

			conexao.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return result;

	}
	
	public List<Venda> relatorioVenda(int ano, int mes ,Venda venda){
		   String dados="";
		   String groupBy="";
		   if(ano>0){
			   dados+=" AND  date_format(venda_data,'%Y') ="+ano;
			   
		   }
		   if(mes>0){
			   dados+=" AND  date_format(venda_data,'%m') ="+mes;
			   
		   }
		   if(venda.getSessao().getHorario()!=""){
			   dados+=" AND  sessao_horario ='"+venda.getSessao().getHorario()+"'";
		   }
		   if(venda.getSessao().getFilme().getId()>0){
			   dados+=" AND  filme.id ="+venda.getSessao().getFilme().getId();
		   }
		   if(venda.getUsuario().getId()>0){
			   
			   dados+=" AND  venda_atendente ="+venda.getUsuario().getId();
			   groupBy=", usuario.nome ";
		   }
		   if(venda.getUsuario().getId()==0){
			   
		   }
		   List<Venda> listar=new ArrayList<Venda>();
		   
			String sql = "select venda_data,sessao_horario,filme.titulo,"
					+ "sum(venda_qtde_ingresso_inteira) as 'qtde_ingresso_inteira',"
					+ "sum(venda_qtde_ingresso_meia) as 'qtde_ingresso_meia',"
					+ "sum(venda_qtde_ingresso_total) as 'qtde_ingresso_total',"
					+ "sum(venda_valor_pago) as 'valor total' "
					+ "from venda "
					+ "inner join sessao on venda_sessao = sessao_id "
					+ "inner join filme on sessao_filme = filme.id "
					+ "inner join usuario on venda_atendente = usuario.id "
					+ "where venda_id > 0"+dados+" group by venda_sessao, sessao_horario, venda_data"+groupBy+"";
			try{
				conexao = ConnectionFactory.getConnection();
				PreparedStatement statement = conexao.prepareStatement(sql);
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					
					Venda relatorio  = new Venda();
					relatorio.setUsuario(new Usuario()); 
					relatorio.setData(rs.getDate("venda_data"));
					relatorio.setQtdeIngressoInteira(rs.getInt("qtde_ingresso_inteira"));
					relatorio.setQtdeIngressoMeia(rs.getInt("qtde_ingresso_meia"));
					relatorio.setQtdeIngressoTotal(rs.getInt("qtde_ingresso_total"));
					
					relatorio.setSessao(new Sessao());
					relatorio.getSessao().setHorario(rs.getString("sessao_horario"));
					relatorio.getSessao().setFilme(new Filme());
					relatorio.getSessao().getFilme().setTitulo(rs.getString("filme.titulo"));
					relatorio.setValor(rs.getFloat("valor total"));
					listar.add(relatorio);
				}
				conexao.close();
			}catch(InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			return  listar;
			
	  }
}
