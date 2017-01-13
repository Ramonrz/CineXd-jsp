package br.com.cinexd.bo;
import java.util.List;

import br.com.cinexd.dao.PoltronaVendidaDAO;
import br.com.cinexd.models.PoltronaVendida;

public class PoltronaVendidaBO {
	
	public List<PoltronaVendida> listar(PoltronaVendida poltronaVendida){
		PoltronaVendidaDAO poltronaVendidaDAO =new PoltronaVendidaDAO(); 
		return poltronaVendidaDAO.listar(poltronaVendida);
	}
	public boolean inserir(PoltronaVendida poltronaVendida){
		PoltronaVendidaDAO poltronaVendidaDAO =new PoltronaVendidaDAO(); 
		return poltronaVendidaDAO.inserir(poltronaVendida);
	}
	

}
