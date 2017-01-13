package br.com.cinexd.bo;

import java.util.List;

import br.com.cinexd.dao.VendaDAO;
import br.com.cinexd.models.Venda;

public class VendaBO {

	public List<Venda> listar() {
		VendaDAO vendaDAO = new VendaDAO();
		return vendaDAO.listar();

	}

	public List<Venda> gerarRelatorioLotacao() {
		VendaDAO vendaDAO = new VendaDAO();
		return vendaDAO.gerarRelatorioLotacao();
	}
	
	public List<Venda> relatorioVenda(int ano, int mes ,Venda venda){
		
		VendaDAO vendaDAO=new VendaDAO();
		return vendaDAO.relatorioVenda(ano, mes, venda);
		
	}

	public int inserir(Venda venda) {
		VendaDAO vendaDAO = new VendaDAO();
		return vendaDAO.inserir(venda);

	}

}
