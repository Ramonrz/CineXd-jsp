package br.com.cinexd.bo;

import java.util.List;

import br.com.cinexd.dao.FilmeDAO;
import br.com.cinexd.models.Filme;

public class FilmeBO {

	public boolean inserir(Filme filme) {

		FilmeDAO filmeDAO = new FilmeDAO();
		if (!existeCampoNulo(filme) && filmeDAO.verificarRegistroExistente(filme) == false) {
			filmeDAO.inserir(filme);
			return true;
		} else {
			return false;
		}
	}

	public boolean desativar(int status, int id) {

		if (status == 0) {
			FilmeDAO filmeDAO = new FilmeDAO();
			filmeDAO.desativar(status, id);
			return true;
		} else {
			return false;
		}
	}

	public boolean editar(Filme filme) {

		FilmeDAO filmeDAO = new FilmeDAO();
		if (!existeCampoNulo(filme)) {
			filmeDAO.editar(filme);
			return true;
		} else {
			return false;
		}
	}

	public List<Filme> listar() {

		FilmeDAO filmeDAO = new FilmeDAO();
		return filmeDAO.listar();
	}

	public Filme obterPorId(int id) {

		FilmeDAO filmeDAO = new FilmeDAO();
		return filmeDAO.obterPorId(id);
	}

	public boolean existeCampoNulo(Filme filme) {

		if (filme.getTitulo().equals("") || filme.getGenero().equals("") || filme.getIdioma().equals("")
				|| filme.getDuracao() < 0 || filme.getFaixaEtaria() < 0) {
			return true;
		} else {
			return false;
		}
	}
}