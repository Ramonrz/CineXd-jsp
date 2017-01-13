package br.com.cinexd.bo;

import java.util.List;

import br.com.cinexd.dao.SalaDAO;
import br.com.cinexd.models.Sala;

public class SalaBO {

	public boolean inserir(Sala sala) {

		SalaDAO salaDAO = new SalaDAO();
		if (!existeCampoNulo(sala) && salaDAO.verificarRegistroExistente(sala) == false) {
			salaDAO.inserir(sala);
			return true;
		} else {
			return false;
		}
	}

	public boolean desativar(int status, int id) {

		if (status == 0) {
			SalaDAO salaDAO = new SalaDAO();
			salaDAO.desativar(status, id);
			return true;
		} else {
			return false;
		}
	}

	public boolean editar(Sala sala) {

		SalaDAO salaDAO = new SalaDAO();
		if (!existeCampoNulo(sala)) {
			salaDAO.editar(sala);
			return true;
		} else {
			return false;
		}
	}

	public List<Sala> listar() {
		SalaDAO salaDAO = new SalaDAO();
		return salaDAO.listar();
	}

	public Sala obterPorId(int id) {
		SalaDAO salaDAO = new SalaDAO();
		return salaDAO.obterPorId(id);
	}

	public boolean existeCampoNulo(Sala sala) {
		if (sala.getNumero() < 0 || sala.getCategoria().equals("") || sala.getQuantidadeFileira() > 10
				|| sala.getQuantidadePoltronaFileira() > 10) {
			return true;
		} else {
			return false;
		}
	}
}