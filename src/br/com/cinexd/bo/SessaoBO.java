package br.com.cinexd.bo;

import java.util.List;

import br.com.cinexd.dao.SessaoDAO;
import br.com.cinexd.models.Sessao;

public class SessaoBO {

	public List<Sessao> listar() {
		SessaoDAO sessaoDAO = new SessaoDAO();
		return sessaoDAO.listar();
	}

	public void inserir(Sessao sessao) {
		SessaoDAO sessaoDAO = new SessaoDAO();
		if (!existeCampoNuloEditar(sessao) && sessaoDAO.verificarDadosExistente(sessao) == false) {
			sessaoDAO.inserir(sessao);

		}
	}

	public void desativar(Sessao sessao) {
		if (sessao.getId() != 0 && sessao.getStatus() != 1) {
			SessaoDAO sessaoDAO = new SessaoDAO();
			sessaoDAO.desativar(sessao);
		}

	}

	public void editar(Sessao sessao) {
		if (!existeCampoNuloEditar(sessao)) {
			SessaoDAO sessaoDAO = new SessaoDAO();
			sessaoDAO.editar(sessao);
		}
	}

	public Sessao obterPorId(Sessao sessao) {
		if (sessao.getId() != 0) {
			SessaoDAO sessaoDAO = new SessaoDAO();
			return sessaoDAO.obterPorId(sessao);
		} else {
			return null;

		}
	}

	public List<Sessao> obterPorFilme(int filmeId) {
		if (filmeId > 0) {
			SessaoDAO sessaoDAO = new SessaoDAO();
			return sessaoDAO.obterPorFilme(filmeId);
		} else {
			return null;
		}
	}

	public Sessao obterFilme(int filmeId) {
		if (filmeId > 0) {
			SessaoDAO sessaoDAO = new SessaoDAO();
			return sessaoDAO.obterFilme(filmeId);
		} else {
			return null;
		}
	}

	public List<Sessao> listarHorario() {
		SessaoDAO sessaoDAO = new SessaoDAO();
		return sessaoDAO.listarHorario();
	}

	public boolean existeCampoNuloEditar(Sessao sessao) {

		if (sessao.getData().equals("") || sessao.getFilme().equals("") || sessao.getHorario().equals("")
				|| sessao.getSala().equals("")) {
			return true;
		} else {
			return false;
		}
	}
}