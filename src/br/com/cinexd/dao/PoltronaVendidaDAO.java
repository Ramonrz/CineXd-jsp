package br.com.cinexd.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cinexd.dao.utils.ConnectionFactory;
import br.com.cinexd.models.PoltronaVendida;

public class PoltronaVendidaDAO {
	private Connection conexao = null;
	public List<PoltronaVendida> listar(PoltronaVendida poltronaVendida){
		List<PoltronaVendida> listar=new ArrayList<PoltronaVendida>();
		String sql="SELECT poltrona_venda.poltrona_venda_id, poltrona_venda.poltrona_venda_numero,poltrona_venda.poltrona_venda_fileira"
				+ " FROM venda "
				+ "LEFT JOIN poltrona_venda ON venda.venda_id=poltrona_venda.poltrona_venda_fkvenda "
				+ "where venda.venda_sessao=?";
		
		try{
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, poltronaVendida.getVenda().getSessao().getId());
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				PoltronaVendida pv=new PoltronaVendida();
				pv.setId(rs.getInt("poltrona_venda_id"));
				pv.setNumero(rs.getInt("poltrona_venda_numero"));
				pv.setFileira(rs.getInt("poltrona_venda_fileira"));
				
				listar.add(pv);
			}
			conexao.close();
		}catch(InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listar;
		
		
	}
	
	public boolean inserir(PoltronaVendida poltronaVendida){
		String sql="INSERT INTO poltrona_venda (poltrona_venda_numero,poltrona_venda_valor_pagor, poltrona_venda_tipo,poltrona_venda_fileira, poltrona_venda_fkvenda) VALUES (?, ?, ?, ?, ?);";
		try{
			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, poltronaVendida.getNumero());
			statement.setFloat(2, poltronaVendida.getValor());
			statement.setInt(3, poltronaVendida.getTipo());
			statement.setInt(4, poltronaVendida.getFileira());
			statement.setInt(5, poltronaVendida.getVenda().getId());
			statement.execute();
	
			conexao.close();
		}catch(InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
		
	}

}
